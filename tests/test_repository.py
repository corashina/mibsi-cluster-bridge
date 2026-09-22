import hashlib
import json
import os
from pathlib import Path
import sys
import tempfile
import unittest
from unittest.mock import patch

sys.path.insert(0,str(Path(__file__).resolve().parents[1]/'scripts'))
import project

class RepositoryTests(unittest.TestCase):
    def test_baseline_rejects_modified_and_missing_inputs(self):
        with tempfile.TemporaryDirectory() as directory:
            base=Path(directory)
            (base/'input').write_bytes(b'expected')
            manifest={'input':hashlib.sha256(b'expected').hexdigest()}
            project.validate_baseline(base,manifest)
            (base/'input').write_bytes(b'changed')
            with self.assertRaisesRegex(RuntimeError,'input'):
                project.validate_baseline(base,manifest)
            (base/'input').unlink()
            with self.assertRaisesRegex(RuntimeError,'input'):
                project.validate_baseline(base,manifest)

    def test_corrupt_dependency_is_rejected(self):
        with tempfile.TemporaryDirectory() as directory:
            base=Path(directory)
            (base/'asm.jar').write_bytes(b'wrong')
            lock=[{'name':'asm.jar','sha256':hashlib.sha256(b'right').hexdigest()}]
            with self.assertRaisesRegex(RuntimeError,'setup.py'):
                project.validate_dependencies(base,lock)

    def test_invalid_java_home_has_actionable_error(self):
        with patch.dict(os.environ,{'JAVA_HOME':'/missing/mibsi-jdk'}):
            with self.assertRaisesRegex(RuntimeError,'JAVA_HOME'):
                project.java_tool('javac')

    def test_reset_cannot_delete_outside_build(self):
        with self.assertRaises(ValueError): project.reset_build(project.ROOT/'docs')
        with self.assertRaises(ValueError): project.reset_build(project.ROOT/'build')

    def test_input_manifest_cannot_escape_directory(self):
        with tempfile.TemporaryDirectory() as directory:
            with self.assertRaises(ValueError):
                project.validate_baseline(Path(directory),{'../outside':'0'*64})

    def test_failed_release_retires_previous_success(self):
        import release
        with tempfile.TemporaryDirectory() as directory:
            base=Path(directory)
            previous=base/'dist/release'
            previous.mkdir(parents=True)
            (previous/'old.zip').write_bytes(b'old release')
            with patch.object(release, 'ROOT', base), patch.object(release, 'validate_baseline', side_effect=RuntimeError('bad input')):
                with self.assertRaisesRegex(RuntimeError,'bad input'):
                    release.main()
            self.assertFalse(previous.exists())
            self.assertEqual(len(list((base/'.local/previous-releases').glob('*/old.zip'))),1)

    def test_import_rejects_bad_bundle_without_touching_existing_input(self):
        import import_upstream
        with tempfile.TemporaryDirectory() as directory:
            base=Path(directory)
            original=base/'fixtures/upstream/input'
            original.parent.mkdir(parents=True)
            original.write_bytes(b'valid')
            source=base/'source'
            source.mkdir()
            (source/'input').write_bytes(b'corrupt')
            baseline=base/'baseline.json'
            baseline.write_text(json.dumps({'input':hashlib.sha256(b'valid').hexdigest()}))
            staging=base/'staging'
            staging.mkdir()
            with patch.object(import_upstream,'ROOT',base), patch.object(import_upstream,'BASELINE',baseline), patch.object(import_upstream,'reset_build',return_value=staging):
                with self.assertRaisesRegex(RuntimeError,'input'):
                    import_upstream.import_bundle(source)
            self.assertEqual(original.read_bytes(),b'valid')

    def test_release_rejects_source_changes_during_verification(self):
        import release
        with tempfile.TemporaryDirectory() as directory:
            base=Path(directory)
            source=base/'scripts/example.py'
            source.parent.mkdir()
            source.write_text('before')
            (base/'dependencies.lock.json').write_text('{}')
            baseline=base/'reference/inventory/original-sha256.json'
            baseline.parent.mkdir(parents=True)
            baseline.write_text('{}')
            (base/'build/release-candidate').mkdir(parents=True)
            report=base/'build/reports/navigation-baseline.txt'
            report.parent.mkdir()
            report.write_text('PASS')
            with patch.object(release,'ROOT',base), patch.object(release,'validate_baseline'), patch.object(release,'run'), patch.object(release,'standalone',side_effect=lambda: source.write_text('after')):
                with self.assertRaisesRegex(RuntimeError,'changed during'):
                    release.main()
            self.assertFalse((base/'dist/release').exists())

if __name__=='__main__': unittest.main()
