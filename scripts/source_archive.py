"""Export a clean source tree without local inputs, tools, or build output."""
import zipfile
from check_repository import check, repository_files
from project import ROOT, require


def main():
    check()
    destination = ROOT / 'dist/mibsi-cluster-bridge-source.zip'
    destination.parent.mkdir(parents=True, exist_ok=True)
    files = repository_files()
    with zipfile.ZipFile(destination, 'w', zipfile.ZIP_DEFLATED, compresslevel=7) as archive:
        for path in files:
            info = zipfile.ZipInfo(path.relative_to(ROOT).as_posix(), date_time=(2026, 9, 22, 0, 0, 0))
            info.compress_type = zipfile.ZIP_DEFLATED
            info.external_attr = 0o100644 << 16
            archive.writestr(info, path.read_bytes())
    with zipfile.ZipFile(destination) as archive:
        require(archive.testzip() is None, 'Source archive integrity failure')
    print(f'Created {destination.name}: {len(files)} files, {destination.stat().st_size:,} bytes.')


if __name__ == '__main__':
    main()
