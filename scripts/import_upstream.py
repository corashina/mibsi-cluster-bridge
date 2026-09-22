"""Import only expected files from a matching release ZIP or directory."""
import argparse
import json
from pathlib import Path
import shutil
import zipfile
from project import BASELINE, ROOT, digest, require, reset_build, validate_baseline


def import_bundle(source):
    source = Path(source).resolve()
    manifest = json.loads(BASELINE.read_text(encoding='utf-8'))
    staging = reset_build(ROOT / 'build/import-upstream').resolve()
    archive = zipfile.ZipFile(source) if source.is_file() else None
    try:
        if archive:
            require(len(archive.namelist()) == len(set(archive.namelist())), 'Duplicate paths in input archive')
        for name in manifest:
            target = (staging / name).resolve()
            require(target.is_relative_to(staging), 'Invalid baseline path')
            target.parent.mkdir(parents=True, exist_ok=True)
            target.write_bytes(archive.read(name) if archive else (source / name).read_bytes())
        validate_baseline(staging, manifest)
        destination = ROOT / 'fixtures/upstream'
        # Do not replace any input until the entire candidate has passed validation.
        for name in manifest:
            target = destination / name
            target.parent.mkdir(parents=True, exist_ok=True)
            shutil.copyfile(staging / name, target)
        print(f'Imported and verified {len(manifest)} original files.')
    finally:
        if archive:
            archive.close()


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('source', help='Original SD bundle directory or original baseline ZIP')
    import_bundle(parser.parse_args().source)
