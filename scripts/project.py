"""Shared paths, toolchain resolution, and input validation."""
import hashlib
import json
import os
from pathlib import Path
import shutil
import subprocess

ROOT = Path(__file__).resolve().parents[1]
BUILD = ROOT / 'build'
UPSTREAM = Path(os.environ.get('MIBSI_UPSTREAM', ROOT / 'fixtures/upstream')).resolve()
BASELINE = ROOT / 'reference/inventory/original-sha256.json'


def digest(path):
    return hashlib.sha256(Path(path).read_bytes()).hexdigest()


def require(condition, message):
    if not condition:
        raise RuntimeError(message)


def validate_baseline(base=UPSTREAM, manifest=None):
    base = Path(base).resolve()
    if manifest is None:
        manifest = json.loads(BASELINE.read_text(encoding='utf-8'))
    for name, expected in manifest.items():
        path = (base / name).resolve()
        if not path.is_relative_to(base):
            raise ValueError('Input path escapes baseline directory: ' + name)
        require(path.is_file() and digest(path) == expected,
                f'Missing or changed original input: {name}. Import the matching bundle with scripts/import_upstream.py.')
    return manifest


def java_tool(name):
    executable = name + ('.exe' if os.name == 'nt' else '')
    home = os.environ.get('JAVA_HOME')
    if home:
        candidate = Path(home) / 'bin' / executable
        require(candidate.is_file(), f'JAVA_HOME does not contain {executable}: {home}')
        return candidate
    candidate = shutil.which(executable)
    require(candidate is not None, 'Install JDK 25 and set JAVA_HOME or add its bin directory to PATH.')
    return Path(candidate)


def validate_dependencies(directory, artifacts):
    paths = []
    for artifact in artifacts:
        path = Path(directory) / artifact['name']
        require(path.is_file() and digest(path) == artifact['sha256'],
                f'Missing or corrupt dependency: {path.name}. Run python scripts/setup.py.')
        paths.append(path)
    return paths


def asm_classpath():
    lock = json.loads((ROOT / 'dependencies.lock.json').read_text(encoding='utf-8'))
    return os.pathsep.join(map(str, validate_dependencies(ROOT / '.tools/asm', lock['artifacts'])))


def reset_build(path):
    path = Path(path).resolve()
    # Recursive cleanup is confined to a named child of this repository's build directory.
    if path == BUILD.resolve() or not path.is_relative_to(BUILD.resolve()):
        raise ValueError('Refusing to clear a path outside a build subdirectory')
    if path.exists():
        shutil.rmtree(path)
    path.mkdir(parents=True)
    return path


def run(args, log=None):
    result = subprocess.run(list(map(str, args)), cwd=ROOT, capture_output=True, text=True, encoding='utf-8', errors='replace')
    output = result.stdout + result.stderr
    if log:
        log = Path(log)
        log.parent.mkdir(parents=True, exist_ok=True)
        log.write_text(output, encoding='utf-8')
    print(output, end='', flush=True)
    result.check_returncode()
    return output
