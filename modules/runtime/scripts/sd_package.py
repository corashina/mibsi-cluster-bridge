"""SD package metadata shared by the install and uninstall packages.

`refresh` rewrites the checksums the MMI checks before it runs the final
script: Bootstrap/final/hashes.txt (SHA-1 and size per file), the SWDL
directory block in metainfo2.txt, and the root SHA256SUMS. metainfo2.txt is
CRLF and its [Signature] covers the exact bytes, so only the directory size
and checksum values change.

`derive_uninstall` copies an assembled install package and turns it into the
matching uninstall package. install_product.sh accepts an uninstall only when
release.env says MIBSI_OPERATION=uninstall and an empty MIBSI-UNINSTALL file
is at the card root; the native files and install manifest stay identical.
"""
import hashlib
import json
import re
import shutil
from pathlib import Path

FINAL = 'Bootstrap/final'
PRODUCT = FINAL + '/product'
UNINSTALL_MARKER = 'MIBSI-UNINSTALL'
DIR_SECTION = '[Bootstrap\\final\\Dir]'
# Files that differ between an install package and its uninstall package.
UNINSTALL_CHANGES = frozenset({'README.txt', 'SHA256SUMS', 'metainfo2.txt', FINAL + '/hashes.txt',
                               PRODUCT + '/release.env', PRODUCT + '/package-receipt.json'})


def digest(path, algorithm='sha256'):
    return hashlib.new(algorithm, Path(path).read_bytes()).hexdigest()


def write(path, text):
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_bytes(text.encode('utf-8'))


def write_json(path, value):
    write(path, json.dumps(value, sort_keys=True, separators=(',', ':')) + '\n')


def refresh(package, hash_names, package_names):
    """Rewrite hashes.txt, the metainfo2.txt directory block and SHA256SUMS."""
    final = package / FINAL
    write(final / 'hashes.txt', ''.join(f'FileName = "{name}"\nFileSize = "{(final / name).stat().st_size}"\n'
        f'CheckSum = "{digest(final / name, "sha1")}"\n\n' for name in hash_names))
    dir_size = sum(p.stat().st_size for p in final.rglob('*') if p.is_file())
    meta = (package / 'metainfo2.txt').read_bytes().decode('utf-8')
    before, after = meta.split(DIR_SECTION, 1)
    block, rest = after.split('[Bootstrap]', 1)
    block = re.sub(r'FileSize = "\d+"', f'FileSize = "{dir_size}"', block)
    block = re.sub(r'CheckSum = "[0-9A-Fa-f]+"', f'CheckSum = "{digest(final / "hashes.txt", "sha1").upper()}"', block)
    write(package / 'metainfo2.txt', before + DIR_SECTION + block + '[Bootstrap]' + rest)
    write(package / 'SHA256SUMS', ''.join(f'{digest(package / name)}  {name}\n'
                                          for name in package_names if name != 'SHA256SUMS'))


def set_operation(env_text, operation):
    lines = env_text.splitlines()
    matches = [i for i, line in enumerate(lines) if line.startswith('MIBSI_OPERATION=')]
    if len(matches) != 1:
        raise RuntimeError('release.env must contain MIBSI_OPERATION exactly once')
    lines[matches[0]] = 'MIBSI_OPERATION=' + operation
    return ''.join(line + '\n' for line in lines)


def uninstall_guide(release_id, upstream_id):
    """README.txt for the uninstall package."""
    return f'''MIBSI Cluster Bridge - UNINSTALLATION
Firmware target: MHI2Q_ER_AUG22_P5092_MU1329
Release: {release_id}
Based on release: {upstream_id}

WHAT THIS DOES
This package removes MIBSI Cluster Bridge and puts back the original files that
were saved inside the unit, in /mnt/app/root/mibsi/, when MIBSI was first
installed. It works for any MIBSI V2 installation that still has that backup,
including earlier MIBSI releases.

BEFORE YOU START
Run MIBSI-Cluster-Bridge-status.zip first and keep MIBSI-Logs/status.log.
Its verdict should say "active V2 installation with its backup".
If the unit has no backup, this package stops with an error, changes nothing,
and writes MIBSI-Logs/status.log.

STEPS
1. Extract MIBSI-Cluster-Bridge-uninstall.zip to an empty FAT32 SD card.
   metainfo2.txt, Bootstrap and the empty MIBSI-UNINSTALL file must be at the
   card root.
2. Use the red update menu: Update > SD card > Standard.
3. Keep the unit powered and the card inserted until the operation completes.
4. Remove the card and restart the MMI after completion.
Keep MIBSI-Logs/install.log from the card.

AFTERWARDS
A completed uninstall keeps its backup history in /mnt/app/root/mibsi/.
Running this package again changes nothing: it stops with an error and writes
MIBSI-Logs/status.log.
To remove the history and other inert leftovers, use
MIBSI-Cluster-Bridge-cleanup.zip.

VERIFICATION
Uninstallation uses the original MIBSI install tool's restore step. This
package has been checked with desktop tests only. It has not been run on a
vehicle.
'''


def derive_uninstall(install, uninstall, hash_names, package_names, guide):
    """Copy the install package at `install` to `uninstall` as an uninstall package."""
    if uninstall.exists():
        shutil.rmtree(uninstall)
    shutil.copytree(install, uninstall)
    product = uninstall / PRODUCT
    env = (product / 'release.env').read_text(encoding='utf-8')
    if 'MIBSI_OPERATION=install\n' not in env:
        raise RuntimeError('install package release.env does not say MIBSI_OPERATION=install')
    write(product / 'release.env', set_operation(env, 'uninstall'))
    receipt = json.loads((product / 'package-receipt.json').read_text(encoding='utf-8'))
    receipt['operation'] = 'uninstall'
    receipt['hashes'] = {name: digest(product / name) for name in receipt['hashes']}
    write_json(product / 'package-receipt.json', receipt)
    write(uninstall / 'README.txt', guide)
    refresh(uninstall, hash_names, package_names)
