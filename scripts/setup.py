"""Download and verify the locked ASM dependencies; inspect the selected JDK."""
import json
import urllib.request
from project import ROOT, digest, java_tool, require, run, validate_dependencies


def main():
    run([java_tool('javac'), '-version'])
    artifacts = json.loads((ROOT / 'dependencies.lock.json').read_text(encoding='utf-8'))['artifacts']
    directory = ROOT / '.tools/asm'
    directory.mkdir(parents=True, exist_ok=True)
    for artifact in artifacts:
        target = directory / artifact['name']
        if target.is_file() and digest(target) == artifact['sha256']:
            continue
        temporary = target.with_suffix('.download')
        try:
            with urllib.request.urlopen(artifact['url'], timeout=60) as response:
                temporary.write_bytes(response.read())
            require(digest(temporary) == artifact['sha256'], 'Dependency hash mismatch: ' + target.name)
            temporary.replace(target)
        finally:
            temporary.unlink(missing_ok=True)
    validate_dependencies(directory, artifacts)
    print('Ready: JDK available; all 5 ASM 9.7.1 artifacts verified.')


if __name__ == '__main__':
    main()
