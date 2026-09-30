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


for name, expected in baseline.items():
    check(sha((UPSTREAM / name).read_bytes()) == expected, f'Original unchanged: {name}')

artifacts = {}
for name in ['MIBSI-Cluster-Bridge-baseline-install.zip', 'MIBSI-Cluster-Bridge-install.zip']:
    path = DIST / name
    artifacts[name] = sha(path.read_bytes())
    with zipfile.ZipFile(path) as archive:
        check(archive.testzip() is None, f'ZIP CRC: {name}')
        check(set(archive.namelist()) == set(baseline) and len(archive.namelist()) == len(baseline), f'ZIP inventory: {name}')
        contents = {entry: archive.read(entry) for entry in archive.namelist()}
    if 'baseline-install' in name:
        check(all(sha(data) == baseline[entry] for entry, data in contents.items()), 'Restore archive matches all original bytes')
    else:
        package = contents

allowed_changes = {nav_path, 'README.txt', 'MIBSI-VERSION.txt', 'metainfo2.txt', 'SHA256SUMS',
    'Bootstrap/final/hashes.txt', *[prefix + name for name in ['install.sha256', 'VERSION.txt',
    'release.env', 'known-releases.txt', 'release-receipt.json', 'package-receipt.json']]}
changed = {name for name, data in package.items() if sha(data) != baseline[name]}
check(changed == allowed_changes, 'Only planned Java payload and metadata changed; all native files and install scripts identical')
check(all((ROOT / 'build/install-package' / name).read_bytes() == data for name, data in package.items()), 'ZIP matches assembled package')

with zipfile.ZipFile(UPSTREAM / nav_path) as original, zipfile.ZipFile(io.BytesIO(package[nav_path])) as patched:
    retained_entries = [name for name in original.namelist() if name != 'META-INF/NOTICE.txt']
    check([rename_path(name) for name in retained_entries] == patched.namelist(),
          'JAR entry inventory and order preserved under project namespace, excluding removed notice')
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

sw = package['Bootstrap/final/hashes.txt'].decode()
blocks = re.findall(r'FileName = "([^"]+)"\s+FileSize = "(\d+)"\s+CheckSum = "([0-9a-f]+)"', sw)
for name, size, expected in blocks:
    data = package['Bootstrap/final/' + name]
    check(len(data) == int(size) and sha(data, 'sha1') == expected, f'SWDL size and SHA1: {name}')
check({'Bootstrap/final/' + item[0] for item in blocks} == {name for name in package if name.startswith('Bootstrap/final/')} - {'Bootstrap/final/hashes.txt'}, 'SWDL hash coverage complete')
meta = package['metainfo2.txt'].decode()
block = meta.split('[Bootstrap\\final\\Dir]', 1)[1].split('[', 1)[0]
size = int(re.search(r'FileSize = "(\d+)"', block)[1])
checksum = re.search(r'CheckSum = "([0-9A-F]+)"', block)[1]
check(size == sum(len(data) for name, data in package.items() if name.startswith('Bootstrap/final/')), 'SWDL directory total includes hash manifest')
check(checksum.lower() == sha(package['Bootstrap/final/hashes.txt'], 'sha1'), 'SWDL directory checksum')
script_checksum = re.search(r'FinalScriptChecksum = "([0-9a-f]+)"', meta)[1]
check(script_checksum == sha(package['Bootstrap/final/finalScript.sh'], 'sha1'), 'SWDL final script checksum')

for log, count in [('test-resolved.txt', 2067), ('test-missing.txt', 3), ('test-mismatch.txt', 3)]:
    text = (ROOT / 'build/runtime/logs' / log).read_text()
    check(f'PASS: {count} assertions' in text, f'Handler test evidence: {log}')
report = {'status': 'PASS', 'checks': len(checks), 'handler_assertions': 2073, 'vehicle_tested': False,
    'release_id': release_id, 'artifact_sha256': artifacts, 'changed_files': sorted(changed), 'details': checks}
report['baseline_comparison_sha256'] = artifacts.pop('MIBSI-Cluster-Bridge-baseline-install.zip')
(DIST / 'verification.json').write_text(json.dumps(report, indent=2) + '\n')
print(json.dumps({key: report[key] for key in ['status', 'checks', 'handler_assertions', 'vehicle_tested', 'release_id', 'artifact_sha256']}, indent=2))
