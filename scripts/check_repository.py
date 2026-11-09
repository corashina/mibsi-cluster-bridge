"""Validate maintained Python and local documentation links without firmware inputs."""
import ast
from pathlib import Path
import re
from project import ROOT, require

ROOT_FILES = ('README.md', 'LICENSE', 'NOTICE.md', '.gitignore', '.gitattributes',
              '.editorconfig', 'dependencies.lock.json')


def repository_files():
    files = [ROOT / name for name in ROOT_FILES]
    for directory in ('.github', 'docs', 'modules', 'scripts', 'tests', 'reference'):
        for path in sorted((ROOT / directory).rglob('*')):
            if not path.is_file():
                continue
            relative = path.relative_to(ROOT).as_posix()
            if '__pycache__' in path.parts or relative.startswith('docs/superpowers/'):
                continue
            if path.suffix in ('.pyc', '.class'):
                continue
            files.append(path)
    return sorted(set(files))


def check():
    links = 0
    for path in repository_files():
        require(path.is_file(), 'Missing repository file: ' + str(path))
        relative = path.relative_to(ROOT).as_posix()
        require(path.stat().st_size < 50 * 1024 * 1024, 'Oversized source file: ' + relative)
        if path.suffix == '.py':
            ast.parse(path.read_text(encoding='utf-8'), filename=str(path))
        if path.suffix not in ('.md', '.html'):
            continue
        text = path.read_text(encoding='utf-8')
        targets = re.findall(r'\]\(([^)]+)\)', text) if path.suffix == '.md' else re.findall(r'href="([^\"]+)"', text)
        for target in targets:
            if '://' in target or target.startswith(('#', 'mailto:')):
                continue
            target = target.split('#')[0]
            if not target:
                continue
            resolved = (path.parent / target).resolve()
            require(resolved.is_relative_to(ROOT) and resolved.exists(), f'Broken local link: {relative} -> {target}')
            links += 1
    print(f'PASS: repository inventory, Python syntax, and {links} local documentation links.')


if __name__ == '__main__':
    check()
