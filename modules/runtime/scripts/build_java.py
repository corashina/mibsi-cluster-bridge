import subprocess
import os
import sys
from pathlib import Path
sys.path.insert(0, str(Path(__file__).resolve().parents[3] / 'scripts'))
from project import ROOT, UPSTREAM, asm_classpath, java_tool, require, reset_build, validate_baseline
from java_namespace import relocate_jar

MOD = ROOT / 'modules/runtime'
ASM_CP = asm_classpath()
validate_baseline()
ORIGINAL = UPSTREAM / 'Bootstrap/final/product/files/mhi2q-aa-navigation-proxy.jar'
BUILD = reset_build(ROOT / 'build/runtime')
for folder in ('compiled', 'patcher', 'patched', 'tests', 'no-constants', 'mismatch-constants', 'logs'):
    (BUILD / folder).mkdir(parents=True, exist_ok=True)
PROJECT_JAR = BUILD / 'baseline-project-namespace.jar'
relocate_jar(ORIGINAL, PROJECT_JAR)


def run(args, log):
    result = subprocess.run([str(arg) for arg in args], stdout=subprocess.PIPE, stderr=subprocess.STDOUT, text=True)
    (BUILD / 'logs' / log).write_text(result.stdout, encoding='utf-8')
    print(result.stdout)
    result.check_returncode()


run([java_tool('javac'), '--release', '8', '-g:none', '-implicit:none', '-cp', PROJECT_JAR,
     '-d', BUILD/'compiled', MOD/'src/main/java/com/mibsi/carplay/core/CarPlaySteeringMenuInput.java'], 'compile-handler.txt')
run([java_tool('javac'), '-cp', ASM_CP, '-d', BUILD/'patcher', MOD/'scripts/PatchJar.java'], 'compile-patcher.txt')
run([java_tool('java'), '-cp', str(BUILD/'patcher') + os.pathsep + ASM_CP, 'PatchJar', PROJECT_JAR,
     BUILD/'compiled/com/mibsi/carplay/core/CarPlaySteeringMenuInput.class',
     BUILD/'mhi2q-aa-navigation-proxy.jar', BUILD/'patched'], 'patch-jar.txt')
run([java_tool('javac'), '-cp', BUILD/'patched', '-d', BUILD/'tests', *sorted(p for p in (MOD/'tests').rglob('*.java') if 'integration' not in p.parts)], 'compile-tests.txt')
run([java_tool('java'), '-Xverify:all', '-cp', str(BUILD/'tests') + os.pathsep + str(BUILD/'patched'), 'InputHandlerTest'], 'test-resolved.txt')
# Copy only test harness and service doubles, deliberately leaving out Constants.
for path in (BUILD/'tests').rglob('*.class'):
    rel = path.relative_to(BUILD/'tests')
    if rel.as_posix().startswith('org/'):
        continue
    target = BUILD/'no-constants'/rel
    target.parent.mkdir(parents=True, exist_ok=True)
    target.write_bytes(path.read_bytes())
run([java_tool('java'), '-Xverify:all', '-cp', str(BUILD/'no-constants') + os.pathsep + str(BUILD/'patched'),
     'InputHandlerTest', 'missing'], 'test-missing.txt')
bad_constants = BUILD/'mismatch-source/org/dsi/ifc/keypanel/Constants.java'
bad_constants.parent.mkdir(parents=True, exist_ok=True)
bad_constants.write_text((MOD/'tests/org/dsi/ifc/keypanel/Constants.java').read_text().replace('KBD_MFW = 4', 'KBD_MFW = 99'))
run([java_tool('javac'), '-d', BUILD/'mismatch-constants', bad_constants], 'compile-mismatch.txt')
run([java_tool('java'), '-Xverify:all', '-cp', str(BUILD/'mismatch-constants') + os.pathsep + str(BUILD/'no-constants') + os.pathsep + str(BUILD/'patched'),
     'InputHandlerTest', 'mismatch'], 'test-mismatch.txt')
run([java_tool('javap'), '-classpath', BUILD/'mhi2q-aa-navigation-proxy.jar', '-verbose', '-c', '-p',
     'com.mibsi.carplay.core.CarPlaySteeringMenuInput'], 'handler-bytecode.txt')
