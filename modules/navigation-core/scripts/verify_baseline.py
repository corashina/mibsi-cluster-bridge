"""Compare the navigation module with the hash-verified original release."""
from pathlib import Path
import sys
sys.path.insert(0, str(Path(__file__).resolve().parents[3] / 'scripts'))
from project import ROOT, UPSTREAM, java_tool, reset_build, run, validate_baseline
from java_namespace import UPSTREAM_PACKAGE

validate_baseline()
module = ROOT / 'modules/navigation-core'
build = reset_build(ROOT / 'build/navigation-baseline')
run([java_tool('javac'), '-d', build,
     *sorted((module / 'src/main/java').rglob('*.java')), module / 'tests/JavaCompatibilityTest.java'])
run([java_tool('java'), '-Xverify:all', '-cp', build, 'JavaCompatibilityTest',
     UPSTREAM / 'Bootstrap/final/product/files/mhi2q-aa-navigation-proxy.jar', build, UPSTREAM_PACKAGE],
    ROOT / 'build/reports/navigation-baseline.txt')
