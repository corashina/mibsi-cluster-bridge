"""Exercise namespace relocation with classes loaded and verified by the JVM."""
from pathlib import Path
import subprocess
import sys
import tempfile
import unittest
import zipfile

sys.path.insert(0, str(Path(__file__).resolve().parents[1] / 'scripts'))
from java_namespace import PROJECT_PACKAGE, UPSTREAM_PACKAGE, relocate_jar, relocate_entry
from project import java_tool


class NamespaceTests(unittest.TestCase):
    def test_relocated_classes_link_and_support_reflection(self):
        source = '''package PACKAGE;
import java.util.List;
public class RenameProbe {
    public static class Child { public long number = 1234567890123L; }
    public Child[] children;
    public List<Child> generic;
    public static void main(String[] args) throws Exception {
        Class<?> child = Class.forName("PACKAGE.RenameProbe$Child");
        if (child != Child.class) throw new AssertionError("reflective lookup");
        if (RenameProbe.class.getField("children").getType().getComponentType() != child)
            throw new AssertionError("array descriptor");
        if (!RenameProbe.class.getField("generic").getGenericType().toString().contains(child.getName()))
            throw new AssertionError("generic signature");
        if (new Child().number != 1234567890123L || Double.doubleToLongBits(1.25) == 0)
            throw new AssertionError("wide constants");
        Runnable action = () -> System.out.println("PASS namespace relocation \\u03bb");
        action.run();
    }
}'''.replace('PACKAGE', UPSTREAM_PACKAGE)
        with tempfile.TemporaryDirectory() as directory:
            base = Path(directory)
            java = base / 'RenameProbe.java'
            java.write_text(source, encoding='utf-8')
            compiled = base / 'classes'
            compilation = subprocess.run([str(java_tool('javac')), '--release', '8', '-d', str(compiled), str(java)],
                                         capture_output=True, text=True)
            self.assertEqual(compilation.returncode, 0, compilation.stdout + compilation.stderr)
            original, relocated = base / 'original.jar', base / 'relocated.jar'
            with zipfile.ZipFile(original, 'w') as archive:
                for path in compiled.rglob('*.class'):
                    archive.write(path, path.relative_to(compiled).as_posix())
            before = original.read_bytes()
            relocate_jar(original, relocated)
            self.assertEqual(before, original.read_bytes())
            result = subprocess.run([str(java_tool('java')), '-Xverify:all', '-cp', str(relocated),
                                     PROJECT_PACKAGE + '.RenameProbe'], check=True,
                                    capture_output=True, text=True, encoding='utf-8')
            self.assertIn('PASS namespace relocation', result.stdout)
            with zipfile.ZipFile(relocated) as archive:
                self.assertTrue(all(name.startswith(PROJECT_PACKAGE.replace('.', '/')) for name in archive.namelist()))

    def test_unknown_resource_with_namespace_is_rejected(self):
        with self.assertRaisesRegex(ValueError, 'Unsupported resource'):
            relocate_entry('unknown.bin', UPSTREAM_PACKAGE.encode())
