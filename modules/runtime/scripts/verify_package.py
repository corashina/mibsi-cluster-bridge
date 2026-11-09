"""Read-only verification of final archives, independent of the assembler."""
import hashlib
import io
import json
import re
import struct
import zipfile
import os
import sys
from pathlib import Path
sys.path.insert(0, str(Path(__file__).resolve().parents[3] / 'scripts'))
from project import ROOT, UPSTREAM, asm_classpath, java_tool, require, reset_build, validate_baseline
from java_namespace import UPSTREAM_PACKAGE, PROJECT_PACKAGE, relocate_entry, rename_path
import support_patch
import sd_package

MOD = ROOT / 'modules/runtime'
DIST = ROOT / 'build/release-candidate'
baseline = validate_baseline()
nav_path = 'Bootstrap/final/product/files/mhi2q-aa-navigation-proxy.jar'
prefix = 'Bootstrap/final/product/'
checks = []


def sha(data, algorithm='sha256'):
    return hashlib.new(algorithm, data).hexdigest()


def check(condition, message):
    if not condition:
        raise AssertionError(message)
    checks.append(message)


def check_swdl(pkg, label):
    """Checks the MMI runs before the final script: hashes.txt, metainfo2.txt, final script."""
    sw = pkg['Bootstrap/final/hashes.txt'].decode()
    blocks = re.findall(r'FileName = "([^"]+)"\s+FileSize = "(\d+)"\s+CheckSum = "([0-9a-f]+)"', sw)
    for name, size, expected in blocks:
        data = pkg['Bootstrap/final/' + name]
        check(len(data) == int(size) and sha(data, 'sha1') == expected, f'SWDL size and SHA1: {label}: {name}')
    check({'Bootstrap/final/' + item[0] for item in blocks} == {name for name in pkg if name.startswith('Bootstrap/final/')} - {'Bootstrap/final/hashes.txt'}, f'SWDL hash coverage complete: {label}')
    meta = pkg['metainfo2.txt'].decode()
    block = meta.split('[Bootstrap\\final\\Dir]', 1)[1].split('[', 1)[0]
    size = int(re.search(r'FileSize = "(\d+)"', block)[1])
    checksum = re.search(r'CheckSum = "([0-9A-F]+)"', block)[1]
    check(size == sum(len(data) for name, data in pkg.items() if name.startswith('Bootstrap/final/')), f'SWDL directory total includes hash manifest: {label}')
    check(checksum.lower() == sha(pkg['Bootstrap/final/hashes.txt'], 'sha1'), f'SWDL directory checksum: {label}')
    original_meta = (UPSTREAM / 'metainfo2.txt').read_bytes()
    dir_values = rb'(\[Bootstrap\\final\\Dir\]\r\nFileSize = ")\d+("\r\nCheckSum = ")[0-9A-F]+"'
    check(len(re.findall(dir_values, original_meta)) == 1, 'Original metainfo2.txt has one CRLF SWDL directory block')
    check(re.sub(dir_values, rb'\1\2"', pkg['metainfo2.txt'], count=1) == re.sub(dir_values, rb'\1\2"', original_meta, count=1),
          f'metainfo2.txt keeps every original byte, CRLF line endings included, except the SWDL directory size and checksum: {label}')
    script_checksum = re.search(r'FinalScriptChecksum = "([0-9a-f]+)"', meta)[1]
    check(script_checksum == sha(pkg['Bootstrap/final/finalScript.sh'], 'sha1'), f'SWDL final script checksum: {label}')


for name, expected in baseline.items():
    check(sha((UPSTREAM / name).read_bytes()) == expected, f'Original unchanged: {name}')

added = {support_patch.SUPPORT_PATH}
markers = {'MIBSI-Cluster-Bridge-status.zip': 'MIBSI-STATUS', 'MIBSI-Cluster-Bridge-cleanup.zip': 'MIBSI-CLEANUP'}
artifacts = {}
marker_packages = {}
uninstall_name = 'MIBSI-Cluster-Bridge-uninstall.zip'
for name in ['MIBSI-Cluster-Bridge-baseline-install.zip', 'MIBSI-Cluster-Bridge-install.zip', *markers, uninstall_name]:
    path = DIST / name
    artifacts[name] = sha(path.read_bytes())
    with zipfile.ZipFile(path) as archive:
        check(archive.testzip() is None, f'ZIP CRC: {name}')
        contents = {entry: archive.read(entry) for entry in archive.namelist()}
        expected_names = set(baseline) if 'baseline-install' in name else set(baseline) | added
        if name in markers:
            expected_names.add(markers[name])
        if name == uninstall_name:
            expected_names.add(sd_package.UNINSTALL_MARKER)
        check(set(contents) == expected_names and len(archive.namelist()) == len(expected_names), f'ZIP inventory: {name}')
    if 'baseline-install' in name:
        check(all(sha(data) == baseline[entry] for entry, data in contents.items()), 'Restore archive matches all original bytes')
    elif name in markers:
        marker_packages[name] = contents
    elif name == uninstall_name:
        uninstall = contents
    else:
        package = contents
for name, contents in marker_packages.items():
    check(contents[markers[name]] == b'' and {entry: data for entry, data in contents.items()
          if entry != markers[name]} == package, f'Marker archive is the install package plus {markers[name]}: {name}')

allowed_changes = {nav_path, 'README.txt', 'MIBSI-VERSION.txt', 'metainfo2.txt', 'SHA256SUMS',
    'Bootstrap/final/hashes.txt', support_patch.INSTALL_SCRIPT, *[prefix + name for name in ['install.sha256', 'VERSION.txt',
    'release.env', 'known-releases.txt', 'release-receipt.json', 'package-receipt.json']]}
changed = {name for name, data in package.items() if name in baseline and sha(data) != baseline[name]}
check(changed == allowed_changes, 'Only planned Java payload, support hooks and metadata changed; all native files identical')
check(package[support_patch.SUPPORT_PATH] == support_patch.SUPPORT_SOURCE.read_bytes(), 'Support script matches maintained source')
check(support_patch.strip(package[support_patch.INSTALL_SCRIPT].decode('utf-8')).encode('utf-8')
      == (UPSTREAM / support_patch.INSTALL_SCRIPT).read_bytes(), 'Install script equals upstream plus support hooks only')
check(all((ROOT / 'build/install-package' / name).read_bytes() == data for name, data in package.items()), 'ZIP matches assembled package')

with zipfile.ZipFile(UPSTREAM / nav_path) as original, zipfile.ZipFile(io.BytesIO(package[nav_path])) as patched:
    retained_entries = original.namelist()
    check([rename_path(name) for name in retained_entries] == patched.namelist(),
          'JAR entry inventory and order preserved under project namespace')
    check('META-INF/NOTICE.txt' not in retained_entries or b'LuKa' in patched.read('META-INF/NOTICE.txt'),
          'Upstream source attribution notice retained')
    expected = {'com/mibsi/carplay/core/CarPlaySteeringMenuInput.class', 'de/audi/kbd/dsi/KbdListener.class'}
    altered = {rename_path(name) for name in retained_entries
               if relocate_entry(name, original.read(name)) != patched.read(rename_path(name))}
    check(altered == expected, 'Exactly two Java classes changed beyond namespace relocation')
    old_names = [UPSTREAM_PACKAGE.replace('.', sep).encode() for sep in ('.', '/', '$')]
    check(all(not any(old in name.encode() or old in patched.read(name) for old in old_names)
              for name in patched.namelist()), 'No upstream namespace remains in packaged JAR paths or contents')
    class_prefix = PROJECT_PACKAGE.replace('.', '/') + '/'
    check(sum(name.startswith(class_prefix) and name.endswith('.class') for name in patched.namelist()) == 31,
          'All 31 application classes use the project namespace')
    for name in expected:
        data = patched.read(name)
        magic, minor, major = struct.unpack('>IHH', data[:8])
        check(magic == 0xCAFEBABE and major == 48, f'Java 1.4 class format: {name}')
    check(patched.testzip() is None, 'Patched JAR CRC')

for manifest, base in [('SHA256SUMS', ''), (prefix + 'install.sha256', prefix + 'files/')]:
    covered = []
    for line in package[manifest].decode().splitlines():
        expected, name = line.split(None, 1)
        check(sha(package[base + name]) == expected, f'Manifest hash: {base + name}')
        covered.append(name)
    if not base:
        check(set(covered) == set(package) - {'SHA256SUMS'}, 'Root SHA256 coverage complete')
    else:
        original_names = [line.split(None, 1)[1] for line in (UPSTREAM / manifest).read_text().splitlines()]
        check(covered == original_names, 'Native install manifest filename order preserved')

env = dict(line.split('=', 1) for line in package[prefix + 'release.env'].decode().splitlines())
release_id = env['MIBSI_RELEASE_ID']
env_files = {'GAL': 'gal.mhi2q_aa_cluster_loader', 'DIO': 'dio_manager.mhi2q_carplay_loader',
    'NAV_PROXY': 'mhi2q-aa-navigation-proxy.jar', 'AA_MODULE': 'libmhi2qaacluster.so',
    'CARPLAY_MODULE': 'libmhi2qcarplaycluster.so', 'TURN_CARD': 'mhi2q-aa-turn-card-renderer',
    'DISPLAY': 'mhi2q-aa-cluster-display'}
for key, name in env_files.items():
    check(env[f'MIBSI_{key}_SHA'] == sha(package[prefix + 'files/' + name]), f'Release environment hash: {name}')
known = [line.split() for line in package[prefix + 'known-releases.txt'].decode().splitlines() if line and not line.startswith('#')]
check(len({row[0] for row in known}) == len(known), 'No duplicate release IDs')
check([row for row in known if row[0] == release_id] == [[release_id] + [env[f'MIBSI_{key}_SHA'] for key in env_files]], 'Known-release record matches new payload')
for name in ['MIBSI-VERSION.txt', prefix + 'VERSION.txt']:
    version = dict(line.split('=', 1) for line in package[name].decode().splitlines())
    check(version['release_id'] == release_id and int(version['known_releases']) == len(known), f'Version consistency: {name}')
for name, base in [('package-receipt.json', prefix), ('release-receipt.json', prefix + 'files/')]:
    receipt = json.loads(package[prefix + name])
    check(receipt['release_id'] == release_id, f'Receipt release: {name}')
    for file, expected in receipt['hashes'].items():
        check(sha(package[base + file]) == expected, f'Receipt hash: {base + file}')
    if 'known_release_count' in receipt:
        check(receipt['known_release_count'] == len(known), 'Receipt release count')
    else:
        check(receipt['checks']['vehicle_tested'] is False and receipt['checks']['build_mode'].startswith('local-'), 'Receipt accurately identifies local untested-on-vehicle modification')

check_swdl(package, 'install')
check_swdl(uninstall, 'uninstall')
check(uninstall[sd_package.UNINSTALL_MARKER] == b'', 'Uninstall marker is an empty file')
uninstall_changed = {entry for entry, data in uninstall.items()
                     if entry != sd_package.UNINSTALL_MARKER and package.get(entry) != data}
check(uninstall_changed == sd_package.UNINSTALL_CHANGES and set(uninstall) - {sd_package.UNINSTALL_MARKER} == set(package),
      'Uninstall archive differs from the install package only in operation metadata and checksums')
uninstall_env = uninstall[prefix + 'release.env'].decode().splitlines()
install_env = package[prefix + 'release.env'].decode().splitlines()
check([line for line in uninstall_env if line not in install_env] == ['MIBSI_OPERATION=uninstall']
      and [line for line in install_env if line not in uninstall_env] == ['MIBSI_OPERATION=install'],
      'Uninstall release.env changes only MIBSI_OPERATION')
uninstall_receipt = json.loads(uninstall[prefix + 'package-receipt.json'])
check(uninstall_receipt['operation'] == 'uninstall' and all(sha(uninstall[prefix + file]) == expected
      for file, expected in uninstall_receipt['hashes'].items()), 'Uninstall package receipt')
for line in uninstall['SHA256SUMS'].decode().splitlines():
    expected, name = line.split(None, 1)
    check(sha(uninstall[name]) == expected, f'Uninstall manifest hash: {name}')

for log, count in [('test-resolved.txt', 2067), ('test-missing.txt', 3), ('test-mismatch.txt', 3)]:
    text = (ROOT / 'build/runtime/logs' / log).read_text()
    check(f'PASS: {count} assertions' in text, f'Handler test evidence: {log}')
report = {'status': 'PASS', 'checks': len(checks), 'handler_assertions': 2073, 'vehicle_tested': False,
    'release_id': release_id, 'artifact_sha256': artifacts, 'changed_files': sorted(changed), 'details': checks}
report['baseline_comparison_sha256'] = artifacts.pop('MIBSI-Cluster-Bridge-baseline-install.zip')
(DIST / 'verification.json').write_text(json.dumps(report, indent=2) + '\n')
print(json.dumps({key: report[key] for key in ['status', 'checks', 'handler_assertions', 'vehicle_tested', 'release_id', 'artifact_sha256']}, indent=2))
