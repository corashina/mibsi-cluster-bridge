"""Build, verify, and stage local releases. This command never publishes."""
import json
import shutil
import sys
import uuid
from project import ROOT, digest, require, run, validate_baseline
from verify import standalone


def retire_previous_release():
    final = ROOT / 'dist/release'
    if final.exists():
        destination = ROOT / '.local/previous-releases' / uuid.uuid4().hex
        destination.parent.mkdir(parents=True, exist_ok=True)
        final.rename(destination)


def source_inputs():
    inputs = {}
    for base in ('scripts', 'modules', 'tests'):
        for path in sorted((ROOT / base).rglob('*')):
            if path.is_file() and path.suffix in ('.py', '.java', '.sh'):
                inputs[path.relative_to(ROOT).as_posix()] = digest(path)
    for name in ('dependencies.lock.json', 'reference/inventory/original-sha256.json'):
        inputs[name] = digest(ROOT / name)
    return inputs


def main():
    # A failed run must not leave a previous release at the advertised path.
    retire_previous_release()
    validate_baseline()
    inputs = source_inputs()
    standalone()
    run([sys.executable, ROOT / 'modules/navigation-core/scripts/verify_baseline.py'])
    run([sys.executable, ROOT / 'modules/runtime/scripts/package.py'])
    run([sys.executable, ROOT / 'modules/runtime/tests/integration/run_probe.py'])
    require(source_inputs() == inputs, 'Source inputs changed during verification; rerun the release build.')
    validate_baseline()
    candidate = ROOT / 'build/release-candidate'
    # The baseline reinstall exists only for comparison; it is not an uninstaller.
    baseline_archive = candidate / 'MIBSI-Cluster-Bridge-baseline-install.zip'
    if baseline_archive.exists():
        history = ROOT / 'build/baseline-comparison'
        history.mkdir(parents=True, exist_ok=True)
        baseline_archive.replace(history / baseline_archive.name)
    (candidate / 'build-inputs.json').write_text(json.dumps(inputs, indent=2) + '\n', encoding='utf-8')
    shutil.copyfile(ROOT / 'build/reports/navigation-baseline.txt', candidate / 'navigation-baseline.txt')
    (candidate / 'SHA256SUMS.txt').write_text(''.join(
        f'{digest(path)}  {path.name}\n' for path in sorted(candidate.iterdir())
        if path.is_file() and path.name != 'SHA256SUMS.txt'), encoding='utf-8')
    final = ROOT / 'dist/release'
    final.parent.mkdir(parents=True, exist_ok=True)
    candidate.rename(final)
    print('PASS: fresh build, baseline, package, and integration checks. Local release:', final)


if __name__ == '__main__':
    main()
