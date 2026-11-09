"""Exercise mibsi-support.sh against a simulated unit file system.

The script is sourced the same way install_product.sh sources it. run_mmx is
replaced by a shim that maps /mnt and /eso into a temporary directory, records
mounts, and runs the real ls/cat/cp/rm/mkdir. Requires POSIX sh and bash.
"""
import hashlib
from pathlib import Path
import shutil
import subprocess
import sys
import tempfile
import unittest

ROOT = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(ROOT / 'modules/runtime/scripts'))
sys.path.insert(0, str(ROOT / 'scripts'))
import support_patch
from project import UPSTREAM

SUPPORT = ROOT / 'modules/runtime/package/mibsi-support.sh'
# The upstream install scripts are not in the repository; import the bundle first
# (scripts/import_upstream.py) or set MIBSI_UPSTREAM. Without them these tests skip.
PRODUCT = UPSTREAM / 'Bootstrap/final/product'
NEEDS_UPSTREAM = unittest.skipUnless((PRODUCT / 'install_product.sh').is_file(),
                                     'original bundle not imported (scripts/import_upstream.py)')
GAL_MIBSI_SHA = '35586d98a70909cdd1cae18a876a6b7b0141866080c0c903a4182d51c1fda254'

SHIM = r'''#!/bin/bash
cmd=$1; shift
case "$cmd" in
  /bin/mount) echo "mount $*" >> "$FAKE_LOG"; exit 0 ;;
  /bin/sync) exit 0 ;;
esac
args=()
for a in "$@"; do
  case "$a" in /mnt/*|/eso/*) a="$FAKE_ROOT$a" ;; esac
  args+=("$a")
done
case "$cmd" in
  /bin/*) exec "${cmd#/bin/}" "${args[@]}" ;;
  *) exec "$cmd" "${args[@]}" ;;
esac
'''

TOOL = r'''#!/bin/sh
test "$1" = sha256 && test -f "$2" || exit 1
sha256sum "$2" | cut -d' ' -f1
'''

DRIVER = r'''
set -u
VOLUME="$1"; PRODUCT_DIR="$2"; TOOL="$3"; VERSION_FILE="$4"; ACTION="$5"
OPERATION="${OPERATION:-install}"
SUPPORTED_TRAIN="MHI2Q_ER_AUG22_P5092"
SUPPORTED_TRAIN_ALIAS="MIB2Q_ER_AUG22_P5092"
SUPPORTED_TRAIN_P5152="MHI2Q_ER_AUG22_P5152"
SUPPORTED_MU="1329"
MIBSI_RELEASE_ID=test-release
APP_RW=0
run_mmx() { "$SHIM" "$@"; }
fail() { echo "MIBSI: operation refusee: $*"; exit 60; }
. "$SUPPORT"
case "$ACTION" in
  marker) if mibsi_support_operation; then echo "SUPPORT-HANDLED"; else echo "NO-MARKER"; fi ;;
  preflight) mibsi_uninstall_preflight; echo "PREFLIGHT-PASSED" ;;
  stage) mibsi_remove_menu_stage ;;
esac
echo "APP_RW=$APP_RW"
'''


@NEEDS_UPSTREAM
@unittest.skipIf(shutil.which('bash') is None or shutil.which('sh') is None or sys.platform == 'win32',
                 'POSIX shell required')
class SupportScriptTests(unittest.TestCase):
    def setUp(self):
        self.temp = tempfile.TemporaryDirectory()
        base = Path(self.temp.name)
        self.fs = base / 'unit'
        self.volume = base / 'sd'
        (self.volume / 'MIBSI-Logs').mkdir(parents=True)
        self.log = base / 'mounts.log'
        self.log.write_text('')
        bin_dir = base / 'bin'
        bin_dir.mkdir()
        self.shim = bin_dir / 'run_mmx'
        self.shim.write_text(SHIM)
        self.tool = bin_dir / 'tool'
        self.tool.write_text(TOOL)
        for path in (self.shim, self.tool):
            path.chmod(0o755)
        self.driver = base / 'driver.sh'
        self.driver.write_text(DRIVER)
        self.version = base / 'version.txt'
        self.version.write_text("Current train = 'MHI2Q_ER_AUG22_P5092'\nMainUnit version = '1329'\n")
        self.stock()

    def tearDown(self):
        self.temp.cleanup()

    def put(self, path, data=b'x'):
        target = self.fs / path.lstrip('/')
        target.parent.mkdir(parents=True, exist_ok=True)
        target.write_bytes(data)
        return target

    def exists(self, path):
        return (self.fs / path.lstrip('/')).exists()

    def stock(self):
        for path in ('/mnt/system/etc/eso/production/dio_manager.json', '/eso/bin/apps/gal',
                     '/eso/bin/apps/dio_manager'):
            self.put(path, b'stock ' + path.encode())
        self.put('/mnt/app/eso/hmi/lsd/lsd.sh', b'# Find and append jar files\n')

    def leftovers(self):
        self.put('/mnt/app/root/mibsi/installer.lock', b'')
        self.put('/mnt/app/root/mibsi/history/1793-12-0/before/manifest', b'MIBSI_BACKUP_V2 17\n')
        self.put('/mnt/app/root/mibsi/history/1793-12-0/after/manifest', b'after\n')
        self.put('/mnt/app/eso/hmi/engdefs/mibsi-main.esd.new', b'menu')

    def run_support(self, action, marker=None, operation='install', product=PRODUCT, path='/usr/bin:/bin'):
        if marker:
            (self.volume / marker).write_bytes(b'')
        env = {'PATH': path, 'SHIM': str(self.shim), 'SUPPORT': str(SUPPORT),
               'FAKE_ROOT': str(self.fs), 'FAKE_LOG': str(self.log), 'OPERATION': operation}
        result = subprocess.run(['sh', str(self.driver), str(self.volume), str(product), str(self.tool),
                                 str(self.version), action], env=env, capture_output=True, text=True)
        return result.returncode, result.stdout + result.stderr

    def status_log(self):
        return (self.volume / 'MIBSI-Logs/status.log').read_text()

    def test_no_marker_falls_through(self):
        code, out = self.run_support('marker')
        self.assertEqual(code, 0, out)
        self.assertIn('NO-MARKER', out)

    def test_status_on_clean_unit_changes_nothing(self):
        code, out = self.run_support('marker', 'MIBSI-STATUS')
        self.assertEqual(code, 0, out)
        self.assertIn('VERDICT: no MIBSI files found.', self.status_log())
        self.assertIn('not MIBSI    /eso/bin/apps/gal', self.status_log())
        self.assertEqual(self.log.read_text(), '')

    def test_status_reports_leftovers_after_uninstall(self):
        self.leftovers()
        code, out = self.run_support('marker', 'MIBSI-STATUS')
        self.assertEqual(code, 0, out)
        report = self.status_log()
        self.assertIn('LEFTOVER     /mnt/app/root/mibsi (inert)', report)
        self.assertIn('LEFTOVER     /mnt/app/eso/hmi/engdefs/mibsi-main.esd.new (inert)', report)
        self.assertIn('1793-12-0', report)
        self.assertIn('only inert MIBSI leftovers remain', report)
        self.assertTrue(self.exists('/mnt/app/root/mibsi/installer.lock'))
        self.assertEqual(self.log.read_text(), '')

    def test_status_works_without_sed(self):
        # The MMX has no sed: a real status run printed "sed: cannot execute".
        no_sed = Path(self.temp.name) / 'no-sed-bin'
        no_sed.mkdir()
        for directory in ('/usr/bin', '/bin'):
            for tool in Path(directory).iterdir():
                link = no_sed / tool.name
                if tool.name != 'sed' and not link.exists():
                    link.symlink_to(tool)
        self.leftovers()
        code, out = self.run_support('marker', 'MIBSI-STATUS', path=str(no_sed))
        self.assertEqual(code, 0, out)
        report = self.status_log()
        self.assertNotIn('sed', out + report)
        self.assertIn("firmware: Current train = 'MHI2Q_ER_AUG22_P5092'", report)
        self.assertIn("firmware: MainUnit version = '1329'", report)
        self.assertIn('    1793-12-0', report)

    def test_status_flags_mibsi_hashes_and_v1_lsd(self):
        self.put('/eso/bin/apps/gal', b'patched gal')
        self.put('/mnt/app/eso/hmi/lsd/lsd.sh', b'# MIBSI_JAR_BOOTSTRAP_V1\n')
        self.put('/mnt/system/etc/eso/production/dio_manager.json', (PRODUCT / 'files/dio_manager.json').read_bytes())
        code, out = self.run_support('marker', 'MIBSI-STATUS')
        report = self.status_log()
        self.assertIn('MIBSI        /mnt/app/eso/hmi/lsd/lsd.sh (MIBSI_JAR_BOOTSTRAP_V1 line)', report)
        self.assertIn('MIBSI        /mnt/system/etc/eso/production/dio_manager.json', report)
        self.assertIn('not MIBSI    /eso/bin/apps/gal', report)
        self.assertIn('active without a V2 backup pointer', report)

    def test_cleanup_archives_then_removes_inert_leftovers(self):
        self.leftovers()
        self.put('/mnt/app/eso/hmi/engdefs/mibsi-main.esd', b'menu')
        self.put('/mnt/app/eso/hmi/engdefs/scripts/mibsi/v1/status.sh', b'echo')
        code, out = self.run_support('marker', 'MIBSI-CLEANUP')
        self.assertEqual(code, 0, out)
        self.assertIn('SUPPORT-HANDLED', out)
        self.assertIn('APP_RW=1', out)
        self.assertIn('mount -uw /mnt/app', self.log.read_text())
        for path in ('/mnt/app/root/mibsi', '/mnt/app/eso/hmi/engdefs/mibsi-main.esd',
                     '/mnt/app/eso/hmi/engdefs/mibsi-main.esd.new', '/mnt/app/eso/hmi/engdefs/scripts/mibsi'):
            self.assertFalse(self.exists(path), path)
        archives = list((self.volume / 'MIBSI-Logs').glob('backup-archive-*/mibsi'))
        self.assertEqual(len(archives), 1)
        self.assertEqual((archives[0] / 'history/1793-12-0/before/manifest').read_bytes(), b'MIBSI_BACKUP_V2 17\n')
        self.assertIn('VERDICT: no MIBSI files found.', self.status_log())
        self.assertTrue(self.exists('/eso/bin/apps/gal'))

    def test_cleanup_refuses_while_mibsi_binary_is_active(self):
        self.leftovers()
        self.put('/eso/lib/libmhi2qcarplaycluster.so', b'module')
        code, out = self.run_support('marker', 'MIBSI-CLEANUP')
        self.assertEqual(code, 60, out)
        self.assertIn('cleanup refused', out)
        self.assertTrue(self.exists('/mnt/app/root/mibsi/installer.lock'))
        self.assertEqual(self.log.read_text(), '')

    def test_cleanup_refuses_when_stock_file_holds_mibsi_hash(self):
        self.leftovers()
        gal = self.put('/eso/bin/apps/gal', b'gal')
        product = Path(self.temp.name) / 'product'
        shutil.copytree(PRODUCT, product)
        digest = hashlib.sha256(gal.read_bytes()).hexdigest()
        with open(product / 'known-releases.txt', 'a') as known:
            known.write('testrelease ' + digest + ' ' + GAL_MIBSI_SHA + '\n')
        code, out = self.run_support('marker', 'MIBSI-CLEANUP', product=product)
        self.assertEqual(code, 60, out)
        self.assertIn('MIBSI        /eso/bin/apps/gal', out)
        self.assertTrue(self.exists('/mnt/app/root/mibsi'))

    def test_cleanup_refuses_active_v2_install(self):
        self.leftovers()
        self.put('/mnt/app/root/mibsi/origin', b'1793-12-0')
        code, out = self.run_support('marker', 'MIBSI-CLEANUP')
        self.assertEqual(code, 60, out)
        self.assertIn('Use the matching uninstall package', out)
        self.assertTrue(self.exists('/mnt/app/root/mibsi/origin'))

    def test_cleanup_refuses_unsupported_firmware(self):
        self.leftovers()
        self.version.write_text("Current train = 'MHI2Q_ER_AUG22_P4000'\nMainUnit version = '1329'\n")
        code, out = self.run_support('marker', 'MIBSI-CLEANUP')
        self.assertEqual(code, 60, out)
        self.assertTrue(self.exists('/mnt/app/root/mibsi'))

    def test_conflicting_markers_are_refused(self):
        (self.volume / 'MIBSI-STATUS').write_bytes(b'')
        code, out = self.run_support('marker', 'MIBSI-CLEANUP')
        self.assertEqual(code, 60, out)
        self.assertIn('marqueurs SD incoherents', out)

    def test_uninstall_without_origin_is_refused_before_any_write(self):
        self.leftovers()
        code, out = self.run_support('preflight', operation='uninstall')
        self.assertEqual(code, 60, out)
        self.assertIn('no active V2 installation; nothing to restore', out)
        self.assertNotIn('PREFLIGHT-PASSED', out)
        self.assertIn('only inert MIBSI leftovers remain', self.status_log())
        self.assertEqual(self.log.read_text(), '')

    def test_uninstall_with_origin_or_pending_journal_proceeds(self):
        for name in ('origin', 'pending'):
            with self.subTest(name=name):
                shutil.rmtree(self.fs / 'mnt/app/root', ignore_errors=True)
                self.put('/mnt/app/root/mibsi/' + name, b'1793-12-0')
                code, out = self.run_support('preflight', operation='uninstall')
                self.assertEqual(code, 0, out)
                self.assertIn('PREFLIGHT-PASSED', out)

    def test_install_preflight_is_a_no_op(self):
        code, out = self.run_support('preflight', operation='install')
        self.assertEqual(code, 0, out)
        self.assertIn('PREFLIGHT-PASSED', out)

    def test_successful_operation_drops_menu_stage(self):
        self.put('/mnt/app/eso/hmi/engdefs/mibsi-main.esd.new', b'menu')
        code, out = self.run_support('stage')
        self.assertEqual(code, 0, out)
        self.assertFalse(self.exists('/mnt/app/eso/hmi/engdefs/mibsi-main.esd.new'))


@NEEDS_UPSTREAM
class InstallerPatchTests(unittest.TestCase):
    def setUp(self):
        self.original = (PRODUCT / 'install_product.sh').read_text()

    def test_patch_round_trips_to_upstream_bytes(self):
        patched = support_patch.apply(self.original)
        self.assertNotEqual(patched, self.original)
        self.assertEqual(support_patch.strip(patched), self.original)

    def test_hooks_run_in_the_required_order(self):
        patched = support_patch.apply(self.original)
        support = patched.index('if mibsi_support_operation; then exit 0; fi')
        self.assertLess(patched.index('. "$PRODUCT_DIR/release.env"'), support)
        self.assertLess(support, patched.index('case "$MIBSI_OPERATION:'))
        preflight = patched.index('mibsi_uninstall_preflight')
        self.assertLess(patched.index('check-payload'), preflight)
        self.assertLess(preflight, patched.index('run_mmx /bin/mount -uw /mnt/app'))
        stage = patched.index('mibsi_remove_menu_stage')
        self.assertLess(patched.index('RESULT=$?'), stage)
        self.assertLess(stage, patched.index('if ! cleanup; then'))

    def test_patch_rejects_changed_upstream_script(self):
        with self.assertRaisesRegex(RuntimeError, 'anchor'):
            support_patch.apply(self.original.replace('RESULT=$?\n', ''))

    @unittest.skipIf(shutil.which('sh') is None or sys.platform == 'win32', 'POSIX shell required')
    def test_patched_script_and_support_script_parse(self):
        with tempfile.TemporaryDirectory() as directory:
            script = Path(directory) / 'install_product.sh'
            script.write_text(support_patch.apply(self.original))
            for path in (script, SUPPORT):
                result = subprocess.run(['sh', '-n', str(path)], capture_output=True, text=True)
                self.assertEqual(result.returncode, 0, result.stderr)


if __name__ == '__main__':
    unittest.main()
