"""Run standalone checks; --full also tests and packages against the original bundle."""
import argparse
import os
from pathlib import Path
import sys
from project import ROOT, java_tool, reset_build, run


def standalone():
    run([sys.executable, '-m', 'unittest', 'discover', '-s', ROOT / 'tests', '-v'])
    build = reset_build(ROOT / 'build/standalone')
    navigation = ROOT / 'modules/navigation-core'
    run([java_tool('javac'), '--release', '8', '-d', build / 'navigation',
         *sorted((navigation / 'src/main/java').rglob('*.java')), navigation / 'tests/NavigationCoreTest.java'])
    run([java_tool('java'), '-Xverify:all', '-cp', build / 'navigation', 'NavigationCoreTest'])
    runtime = ROOT / 'modules/runtime'
    test_sources = [p for p in (runtime / 'tests').rglob('*.java') if 'integration' not in p.parts]
    run([java_tool('javac'), '--release', '8', '-d', build / 'input',
         *sorted((runtime / 'src/main/java').rglob('*.java')), *sorted(test_sources)])
    run([java_tool('java'), '-Xverify:all', '-cp', build / 'input', 'InputHandlerTest'])
    run([sys.executable, ROOT / 'scripts/check_repository.py'])
    print('PASS: standalone verification (no original firmware bundle required).')


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--full', action='store_true')
    args = parser.parse_args()
    if args.full:
        run([sys.executable, ROOT / 'scripts/release.py'])
    else:
        standalone()
