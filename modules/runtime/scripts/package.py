"""Assemble a separate SD package; never modify the supplied installer."""
import hashlib
import json
import re
import zipfile
import subprocess
import os
import sys
from pathlib import Path
sys.path.insert(0, str(Path(__file__).resolve().parents[3] / 'scripts'))
from project import ROOT, UPSTREAM, asm_classpath, java_tool, require, reset_build, validate_baseline
import support_patch
import sd_package

MOD = ROOT / 'modules/runtime'
BUILD = ROOT / 'build/runtime'
BASELINE = validate_baseline()
# Direct invocation also rebuilds and tests; saved PASS logs are never sufficient.
subprocess.run([sys.executable, str(MOD / 'scripts/build_java.py')], check=True)
PACKAGE = reset_build(ROOT / 'build/install-package')
UNINSTALL_PACKAGE = ROOT / 'build/uninstall-package'
DIST = reset_build(ROOT / 'build/release-candidate')
NAV = 'mhi2q-aa-navigation-proxy.jar'
# Maintained files added to the upstream inventory, and the SD markers that
# turn the install package into a status or cleanup package.
ADDED = {support_patch.SUPPORT_PATH}
MARKER_PACKAGES = {'MIBSI-Cluster-Bridge-status.zip': 'MIBSI-STATUS',
                   'MIBSI-Cluster-Bridge-cleanup.zip': 'MIBSI-CLEANUP'}
UNINSTALL_ARCHIVE = 'MIBSI-Cluster-Bridge-uninstall.zip'


digest = sd_package.digest
write = sd_package.write
write_json = sd_package.write_json


for rel, expected in BASELINE.items():
    require(digest(UPSTREAM / rel) == expected, f'Original changed: {rel}')
    destination = PACKAGE / rel
    destination.parent.mkdir(parents=True, exist_ok=True)
    destination.write_bytes((UPSTREAM / rel).read_bytes())
require({p.relative_to(PACKAGE).as_posix() for p in PACKAGE.rglob('*') if p.is_file()} == set(BASELINE), 'Validation failed')

product = PACKAGE / 'Bootstrap/final/product'
final = product.parent
# Support operations: the only install script change is three hook lines.
install_script = PACKAGE / support_patch.INSTALL_SCRIPT
install_script.write_bytes(support_patch.apply(install_script.read_bytes().decode('utf-8')).encode('utf-8'))
(PACKAGE / support_patch.SUPPORT_PATH).write_bytes(support_patch.SUPPORT_SOURCE.read_bytes())
(product / 'files' / NAV).write_bytes((BUILD / NAV).read_bytes())
nav_sha = digest(product / 'files' / NAV)
release_id = hashlib.sha256(('MIBSI-local-runtime-v1:' + nav_sha).encode()).hexdigest()[:22]
env = dict(line.split('=', 1) for line in (product / 'release.env').read_text().splitlines() if line)
upstream_id = env['MIBSI_RELEASE_ID']
env['MIBSI_RELEASE_ID'] = release_id
env['MIBSI_NAV_PROXY_SHA'] = nav_sha
write(product / 'release.env', ''.join(f'{key}={value}\n' for key, value in env.items()))

manifest_names = [line.split(None, 1)[1] for line in (product / 'install.sha256').read_text().splitlines() if line]
write(product / 'install.sha256', ''.join(f'{digest(product / "files" / name)}  {name}\n' for name in manifest_names))

known = (product / 'known-releases.txt').read_text()
known += '\n' if not known.endswith('\n') else ''
known += ' '.join([release_id] + [env['MIBSI_' + key + '_SHA'] for key in
    ('GAL', 'DIO', 'NAV_PROXY', 'AA_MODULE', 'CARPLAY_MODULE', 'TURN_CARD', 'DISPLAY')]) + '\n'
write(product / 'known-releases.txt', known)
known_count = len([line for line in known.splitlines() if line and not line.startswith('#')])
for path in (product / 'VERSION.txt', PACKAGE / 'MIBSI-VERSION.txt'):
    version = path.read_text().replace(upstream_id, release_id)
    version = re.sub(r'known_releases=\d+', f'known_releases={known_count}', version)
    write(path, version)

receipt = json.loads((product / 'release-receipt.json').read_text())
receipt['upstream_provenance'] = {'release_id': upstream_id, 'digests': receipt.pop('digests'),
    'release_receipt_sha256': BASELINE['Bootstrap/final/product/release-receipt.json']}
receipt['release_id'] = release_id
receipt['hashes'][NAV] = nav_sha
receipt['checks'] = {'build_mode': 'local-runtime-build', 'dev_tools': False,
    'gps_replay_tool': False, 'executed': ['ASM-bytecode-validation', 'JVM-Xverify-all',
    'input-handler-2073-assertions', 'package-integrity-validation'],
    'reused': ['upstream-native-binaries', 'upstream-install-scripts-with-support-hooks', 'namespace-relocated-JAR-entries'],
    'added': ['mibsi-support-status-cleanup-uninstall-preflight'],
    'vehicle_tested': False}
write_json(product / 'release-receipt.json', receipt)

guide = f'''MIBSI Cluster Bridge - INSTALLATION
Firmware target: MHI2Q_ER_AUG22_P5092_MU1329
Release: {release_id}
Based on release: {upstream_id}

INSTALLATION AND UPDATES
1. Extract MIBSI-Cluster-Bridge-install.zip to an empty FAT32 SD card.
   metainfo2.txt and Bootstrap must be at the card root.
2. Use the red update menu: Update > SD card > Standard.
3. Keep the unit powered and the card inserted until the operation completes.
4. Remove the card and restart the MMI after completion.
Updates retain the original installation backup; no prior uninstall is needed.

WHERE THE BACKUP IS
The installation backup is stored inside the unit, in /mnt/app/root/mibsi/.
It is not on the SD card. Formatting the install card does not lose it.

STATUS REPORT (changes nothing)
Use MIBSI-Cluster-Bridge-status.zip the same way as the installer, or add an
empty file named MIBSI-STATUS at the card root. The report is written to
MIBSI-Logs/status.log: every MIBSI path, its SHA-256, and a verdict.

UNINSTALLATION
This archive installs or updates MIBSI Cluster Bridge. It is not an uninstaller.
Use MIBSI-Cluster-Bridge-uninstall.zip from the same release the same way as
this installer. It restores the files saved in /mnt/app/root/mibsi/ when
MIBSI was first installed. Do not add an uninstall marker to this install
package by hand: operation metadata and checksums must also match.
An uninstall run on a unit with no active installation stops with an error
and writes MIBSI-Logs/status.log instead of reporting success.

LEFTOVERS AFTER UNINSTALL
A completed uninstall keeps its backup history in /mnt/app/root/mibsi/.
Older releases could also leave mibsi-main.esd.new and the GEM menu files in
/mnt/app/eso/hmi/engdefs/. Other CarPlay patches may detect these files.
MIBSI-Cluster-Bridge-cleanup.zip (or an empty MIBSI-CLEANUP file) copies
/mnt/app/root/mibsi/ to MIBSI-Logs/backup-archive-*/ on the card, checks the
copy, then removes those leftovers. It refuses while any MIBSI binary, a MIBSI
system file, an active installation or an interrupted operation is present.
Keep the archive folder from the card.

VERIFICATION
The release workflow checks runtime compatibility, package integrity, and
integration behavior using desktop tests. Physical MMI installation and
uninstallation have not been verified in this project.
'''
write(PACKAGE / 'README.txt', guide)
package_receipt = json.loads((product / 'package-receipt.json').read_text())
package_receipt['release_id'] = release_id
package_receipt['known_release_count'] = known_count
package_receipt['hashes'] = {name: digest(product / name) for name in
    [*package_receipt['hashes'], support_patch.SUPPORT_NAME]}
write_json(product / 'package-receipt.json', package_receipt)

hash_names = re.findall(r'FileName = "([^"]+)"', (final / 'hashes.txt').read_text())
hash_names += [name.removeprefix('Bootstrap/final/') for name in sorted(ADDED)]
PACKAGE_NAMES = sorted(set(BASELINE) | ADDED)
sd_package.refresh(PACKAGE, hash_names, PACKAGE_NAMES)

sd_package.derive_uninstall(PACKAGE, UNINSTALL_PACKAGE, hash_names, PACKAGE_NAMES,
                            sd_package.uninstall_guide(release_id, upstream_id))

DIST.mkdir(parents=True, exist_ok=True)
archives = [('MIBSI-Cluster-Bridge-install.zip', PACKAGE, PACKAGE_NAMES, None),
            ('MIBSI-Cluster-Bridge-baseline-install.zip', UPSTREAM, sorted(BASELINE), None),
            *[(filename, PACKAGE, PACKAGE_NAMES, marker) for filename, marker in MARKER_PACKAGES.items()],
            (UNINSTALL_ARCHIVE, UNINSTALL_PACKAGE, PACKAGE_NAMES, sd_package.UNINSTALL_MARKER)]
for filename, source, names, marker in archives:
    with zipfile.ZipFile(DIST / filename, 'w', compression=zipfile.ZIP_DEFLATED, compresslevel=9) as archive:
        entries = [(name, (source / name).read_bytes()) for name in names]
        if marker:
            entries = sorted(entries + [(marker, b'')])
        for name, data in entries:
            info = zipfile.ZipInfo(name, date_time=(2026, 9, 22, 0, 0, 0))
            info.compress_type = zipfile.ZIP_DEFLATED
            info.external_attr = (0o100644 << 16)
            archive.writestr(info, data)
write(DIST / 'SHA256SUMS.txt', ''.join(f'{digest(path)}  {path.name}\n' for path in sorted(DIST.glob('*.zip'))))
print(f'Packaged local release {release_id}; {len(PACKAGE_NAMES)} files per install archive; {known_count} recognized releases.')

subprocess.run([sys.executable, str(MOD / 'scripts/verify_package.py')], check=True)
