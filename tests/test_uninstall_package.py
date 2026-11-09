"""Uninstall package derivation, without the original firmware bundle.

A small synthetic package tree (CRLF metainfo2.txt with a signature block) is
turned into an uninstall package the way package.py does it. The install
script check uses the upstream install_product.sh and runs only when the
original bundle has been imported.
"""
import hashlib
import json
from pathlib import Path
import re
import shutil
import subprocess
import sys
import tempfile
import unittest

ROOT = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(ROOT / 'modules/runtime/scripts'))
sys.path.insert(0, str(ROOT / 'scripts'))
import sd_package
import support_patch
from project import UPSTREAM

UPSTREAM_INSTALL = UPSTREAM / 'Bootstrap/final/product/install_product.sh'
# Shape of the real file: CRLF lines, a signature over the exact bytes, and
# one SWDL directory block whose size and checksum the build rewrites.
METAINFO = (
    '[common]\r\nvendor = "ESO"\r\n\r\n[Signature]\r\nsignature1 = "11583e2be1780d5ee04eb62c71e0d2f1"\r\n\r\n'
    '[common]\r\nFinalScript = "./Bootstrap/final/finalScript.sh"\r\n\r\n'
    '[Bootstrap\\final\\Dir]\r\nFileSize = "1"\r\nCheckSum = "0A9A338B84E6A11D4E248477A7781FFAE28F2769"\r\n\r\n'
    '[Bootstrap]\r\nVendorInfo = "MIBSI"\r\n')
RELEASE_ENV = 'MIBSI_GAL_SHA=35586d98\nMIBSI_RELEASE_ID=test\nMIBSI_OPERATION=install\n'
INSTALL_SCRIPT = '#!/bin/sh\n. "$PRODUCT_DIR/release.env"\nRESULT=$?\n'
DIR_VALUES = rb'(\[Bootstrap\\final\\Dir\]\r\nFileSize = ")\d+("\r\nCheckSum = ")[0-9A-F]+"'
HASH_NAMES = ['finalScript.sh', 'product/release.env', 'product/package-receipt.json',
              'product/install_product.sh', 'product/mibsi-support.sh', 'product/files/payload.bin']


def sha(data, algorithm='sha256'):
    return hashlib.new(algorithm, data).hexdigest()


class UninstallPackageTests(unittest.TestCase):
    def setUp(self):
        self.temp = tempfile.TemporaryDirectory()
        base = Path(self.temp.name)
        self.install = base / 'install'
        self.uninstall = base / 'uninstall'
        final = self.install / 'Bootstrap/final'
        product = final / 'product'
        (product / 'files').mkdir(parents=True)
        (self.install / 'metainfo2.txt').write_bytes(METAINFO.encode())
        (final / 'finalScript.sh').write_bytes(b'#!/bin/sh\nexit 0\n')
        script = (support_patch.apply(UPSTREAM_INSTALL.read_text()) if UPSTREAM_INSTALL.is_file()
                  else INSTALL_SCRIPT)
        (product / 'install_product.sh').write_bytes(script.encode())
        shutil.copyfile(support_patch.SUPPORT_SOURCE, product / 'mibsi-support.sh')
        (product / 'release.env').write_bytes(RELEASE_ENV.encode())
        (product / 'files/payload.bin').write_bytes(b'\x00binary\r\n')
        sd_package.write_json(product / 'package-receipt.json', {
            'operation': 'install', 'release_id': 'test',
            'hashes': {'release.env': sd_package.digest(product / 'release.env')}})
        (self.install / 'README.txt').write_text('install guide\n')
        self.names = sorted(p.relative_to(self.install).as_posix() for p in self.install.rglob('*') if p.is_file())
        self.names = sorted(set(self.names) | {'SHA256SUMS', 'Bootstrap/final/hashes.txt'})
        sd_package.refresh(self.install, HASH_NAMES, self.names)
        sd_package.derive_uninstall(self.install, self.uninstall, HASH_NAMES, self.names, 'uninstall guide\n')

    def tearDown(self):
        self.temp.cleanup()

    def files(self, base):
        return {p.relative_to(base).as_posix(): p.read_bytes() for p in base.rglob('*') if p.is_file()}

    def test_only_operation_metadata_and_checksums_change(self):
        install, uninstall = self.files(self.install), self.files(self.uninstall)
        self.assertEqual(set(install), set(uninstall))
        changed = {name for name in install if install[name] != uninstall[name]}
        self.assertEqual(changed, sd_package.UNINSTALL_CHANGES)

    def test_release_env_switches_operation_only(self):
        product = 'Bootstrap/final/product/'
        before = (self.install / product / 'release.env').read_text().splitlines()
        after = (self.uninstall / product / 'release.env').read_text().splitlines()
        self.assertEqual([line for line in after if line not in before], ['MIBSI_OPERATION=uninstall'])
        self.assertEqual(len(before), len(after))
        receipt = json.loads((self.uninstall / product / 'package-receipt.json').read_text())
        self.assertEqual(receipt['operation'], 'uninstall')
        self.assertEqual(receipt['hashes']['release.env'], sd_package.digest(self.uninstall / product / 'release.env'))

    def test_swdl_checksums_match_contents(self):
        for base in (self.install, self.uninstall):
            files = self.files(base)
            hashes = files['Bootstrap/final/hashes.txt'].decode()
            blocks = re.findall(r'FileName = "([^"]+)"\s+FileSize = "(\d+)"\s+CheckSum = "([0-9a-f]+)"', hashes)
            self.assertEqual([name for name, _, _ in blocks], HASH_NAMES)
            for name, size, expected in blocks:
                data = files['Bootstrap/final/' + name]
                self.assertEqual((len(data), sha(data, 'sha1')), (int(size), expected), name)
            meta = files['metainfo2.txt']
            block = meta.split(b'[Bootstrap\\final\\Dir]', 1)[1].split(b'[', 1)[0]
            final_size = sum(len(data) for name, data in files.items() if name.startswith('Bootstrap/final/'))
            self.assertIn(f'FileSize = "{final_size}"'.encode(), block)
            self.assertIn(f'CheckSum = "{sha(files["Bootstrap/final/hashes.txt"], "sha1").upper()}"'.encode(), block)
            for line in files['SHA256SUMS'].decode().splitlines():
                expected, name = line.split(None, 1)
                self.assertEqual(sha(files[name]), expected, name)

    def test_metainfo_keeps_crlf_and_signature_bytes(self):
        original = METAINFO.encode()
        derived = (self.uninstall / 'metainfo2.txt').read_bytes()
        self.assertEqual(re.sub(DIR_VALUES, rb'\1\2"', derived, count=1),
                         re.sub(DIR_VALUES, rb'\1\2"', original, count=1))

    def test_rejects_package_that_is_not_an_install(self):
        with self.assertRaises(RuntimeError):
            sd_package.derive_uninstall(self.uninstall, Path(self.temp.name) / 'again', HASH_NAMES,
                                        self.names, 'guide\n')

    @unittest.skipUnless(UPSTREAM_INSTALL.is_file(), 'original bundle not imported (scripts/import_upstream.py)')
    @unittest.skipIf(shutil.which('sh') is None or sys.platform == 'win32', 'POSIX shell required')
    def test_install_script_accepts_uninstall_only_with_marker(self):
        product = self.uninstall / 'Bootstrap/final/product'
        tool = product / 'files/mibsi-install-tool'
        tool.write_text('#!/bin/sh\nexit 1\n')
        tool.chmod(0o755)

        def run(marker):
            marker_path = self.uninstall / sd_package.UNINSTALL_MARKER
            if marker:
                marker_path.write_bytes(b'')
            elif marker_path.exists():
                marker_path.unlink()
            result = subprocess.run(['sh', str(product / 'install_product.sh'), str(self.uninstall)],
                                    capture_output=True, text=True)
            return result.stdout + result.stderr

        # Without the marker the operation metadata is inconsistent.
        self.assertIn('marqueurs SD incoherents', run(False))
        # With it, the script passes the operation check and stops at the
        # firmware identity, which this desktop cannot provide.
        output = run(True)
        self.assertNotIn('marqueurs SD incoherents', output)
        self.assertIn('firmware hors liste', output)


if __name__ == '__main__':
    unittest.main()
