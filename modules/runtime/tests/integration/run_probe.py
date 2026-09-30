"""Offline integration test. Reads the finished ZIP; never rewrites it."""
import hashlib
import json
import subprocess
import zipfile
import os
import sys
from pathlib import Path
sys.path.insert(0, str(Path(__file__).resolve().parents[4] / 'scripts'))
from project import ROOT, asm_classpath, java_tool, require, reset_build

HERE = Path(__file__).resolve().parent
CP = asm_classpath()
BUILD = reset_build(ROOT / 'build/integration')
ARCHIVE = ROOT / 'build/release-candidate/MIBSI-Cluster-Bridge-install.zip'
before = hashlib.sha256(ARCHIVE.read_bytes()).hexdigest()
with zipfile.ZipFile(ARCHIVE) as archive:
    jar = archive.read('Bootstrap/final/product/files/mhi2q-aa-navigation-proxy.jar')
(BUILD / 'packaged.jar').write_bytes(jar)


def run(args, log):
    result = subprocess.run([str(a) for a in args], capture_output=True, text=True)
    output = result.stdout + result.stderr
    (BUILD / log).write_text(output, encoding='utf-8')
    print(output)
    return result, output


result, _ = run([java_tool('javac'), '-cp', CP, '-d', BUILD, HERE / 'IntegrationProbe.java'], 'compile.log')
result.check_returncode()
base = [java_tool('java'), '-Xverify:all', '-cp', str(BUILD) + os.pathsep + CP, 'IntegrationProbe', BUILD / 'packaged.jar']
result, output = run(base, 'integration.log')
result.check_returncode()
require('PASS_TOTAL' in output, 'Validation failed')
controls = {}
for mutation, expected in [('leak-oem', 'captured input must not reach OEM event sink'), ('no-toggle', 'short input must request exactly one menu toggle')]:
    result, text = run(base + [mutation], mutation + '.log')
    require(result.returncode != 0 and 'AssertionError: ' + expected in text, 'Negative control must fail at the expected behavioral assertion')
    controls[mutation] = 'Expected behavioral failure detected'
require(hashlib.sha256(ARCHIVE.read_bytes()).hexdigest() == before, 'Validation failed')
report = {'status': 'PASS', 'kind': 'offline Java integration test, not QNX/MMI emulation',
          'installer_sha256': before, 'packaged_jar_sha256': hashlib.sha256(jar).hexdigest(),
          'summary': next(line for line in output.splitlines() if line.startswith('PASS_TOTAL')),
          'negative_controls': controls, 'installer_unchanged': True, 'vehicle_tested': False,
          'simulated_boundaries': [line.removeprefix('SIMULATED_CALL ') for line in output.splitlines() if line.startswith('SIMULATED_CALL ')]}
(ROOT / 'build/release-candidate/integration.json').write_text(json.dumps(report, indent=2) + '\n')
print(json.dumps({k:v for k,v in report.items() if k != 'simulated_boundaries'}, indent=2))
