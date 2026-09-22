"""Read-only inspection of package inputs; writes package reference data."""
import hashlib
import json
import re
import shutil
import struct
import zipfile
from pathlib import Path
import sys
sys.path.insert(0, str(Path(__file__).resolve().parents[2] / 'scripts'))
from project import validate_baseline

REPOSITORY = Path(__file__).resolve().parents[2]
ROOT = REPOSITORY / 'fixtures/upstream'
OUT = REPOSITORY
PAYLOAD = ROOT / 'Bootstrap/final/product/files'


def elf_info(data):
    endian = '<' if data[5] == 1 else '>'
    if data[4] != 1:
        raise ValueError('Expected ELF32')
    header = struct.unpack_from(endian + 'HHIIIIIHHHHHH', data, 16)
    kind, machine, version, entry, phoff, shoff, flags, ehsize, phsize, phnum, shsize, shnum, shstr = header
    sections = []
    for i in range(shnum):
        values = struct.unpack_from(endian + 'IIIIIIIIII', data, shoff + i * shsize)
        sections.append(dict(zip(('name_offset', 'type', 'flags', 'address', 'offset', 'size', 'link', 'info', 'alignment', 'entry_size'), values)))
    names = sections[shstr]
    names = data[names['offset']:names['offset'] + names['size']]

    def string(blob, offset):
        return blob[offset:blob.find(b'\0', offset)].decode('utf-8', 'replace')

    for section in sections:
        section['name'] = string(names, section['name_offset'])
    symbols = []
    dependencies = []
    for section in sections:
        if section['type'] not in (2, 6, 11):
            continue
        linked = sections[section['link']]
        strings = data[linked['offset']:linked['offset'] + linked['size']]
        step = section['entry_size'] or (8 if section['type'] == 6 else 16)
        for offset in range(section['offset'], section['offset'] + section['size'], step):
            if section['type'] == 6:
                tag, value = struct.unpack_from(endian + 'iI', data, offset)
                if tag == 1:
                    dependencies.append(string(strings, value))
            else:
                name, value, size, info, other, index = struct.unpack_from(endian + 'IIIBBH', data, offset)
                if name:
                    symbols.append({'name': string(strings, name), 'address': hex(value), 'size': size,
                                    'type': info & 15, 'binding': info >> 4, 'section': index,
                                    'table': section['name']})
    return {'format': 'ELF32', 'endianness': 'little' if endian == '<' else 'big',
            'machine': machine, 'architecture': {40: 'ARM', 3: 'x86', 62: 'x86-64'}.get(machine, str(machine)),
            'elf_type': kind, 'entry': hex(entry), 'flags': hex(flags),
            'sections': sections, 'symbols': symbols, 'dependencies': dependencies,
            'debug_sections': [s['name'] for s in sections if s['name'].startswith('.debug')],
            'has_static_symbols': any(s['name'] == '.symtab' for s in sections)}


def main():
    validate_baseline(ROOT)
    for subdir in ('reference/inventory', 'reference/binaries', 'reference/distribution', 'reference/resources', 'build/reports'):
        (OUT / subdir).mkdir(parents=True, exist_ok=True)
    original_hashes = {}
    baseline = OUT / 'reference/inventory/original-sha256.json'
    if baseline.exists():
        input_names = json.loads(baseline.read_text(encoding='utf-8'))
        input_paths = [ROOT / name for name in sorted(input_names)]
    else:
        input_paths = sorted((ROOT / 'Bootstrap').rglob('*')) + [ROOT / name for name in ('README.txt', 'MIBSI-VERSION.txt', 'metainfo2.txt', 'SHA256SUMS')]
    for path in input_paths:
        if not path.is_file() or '.git' in path.parts:
            continue
        relative = path.relative_to(ROOT)
        original_hashes[relative.as_posix()] = hashlib.sha256(path.read_bytes()).hexdigest()
        if path.suffix in ('.sh', '.txt', '.json', '.env', '.esd') or path.name in ('SHA256SUMS', 'install.sha256'):
            dest = OUT / 'reference/distribution' / relative
            dest.parent.mkdir(parents=True, exist_ok=True)
            shutil.copyfile(path, dest)
    if baseline.exists():
        expected = json.loads(baseline.read_text(encoding='utf-8'))
        if original_hashes != expected:
            raise RuntimeError('Package inputs differ from the saved baseline')
    else:
        baseline.write_text(json.dumps(original_hashes, indent=2), encoding='utf-8')
    inventory = []
    for path in sorted(PAYLOAD.iterdir()):
        data = path.read_bytes()
        info = {'file': path.name, 'bytes': len(data), 'sha256': hashlib.sha256(data).hexdigest()}
        if data.startswith(b'\x7fELF'):
            info.update(elf_info(data))
            shutil.copyfile(path, OUT / 'reference/binaries' / path.name)
            symbols = ['table\taddress\tsize\ttype\tsection\tname']
            symbols += ['\t'.join(str(s[k]) for k in ('table', 'address', 'size', 'type', 'section', 'name')) for s in info['symbols']]
            (OUT / 'reference/inventory' / (path.name + '.symbols.tsv')).write_text('\n'.join(symbols) + '\n', encoding='utf-8')
            text = '\n'.join(f'{match.start():08x}\t{match.group().decode("ascii")}' for match in re.finditer(rb'[\x20-\x7e]{5,}', data))
            (OUT / 'reference/inventory' / (path.name + '.strings.txt')).write_text(text + '\n', encoding='utf-8')
        elif zipfile.is_zipfile(path):
            info['format'] = 'JAR/ZIP'
            with zipfile.ZipFile(path) as jar:
                info['entries'] = []
                for entry in jar.infolist():
                    item = {'name': entry.filename, 'bytes': entry.file_size}
                    if entry.filename.endswith('.class'):
                        content = jar.read(entry)
                        item['class_major_version'] = struct.unpack_from('>H', content, 6)[0]
                        item['source_file_attribute_present'] = b'SourceFile' in content
                        item['local_variable_table_present'] = b'LocalVariableTable' in content
                    elif not entry.is_dir():
                        base = (OUT / 'reference/resources' / path.stem).resolve()
                        dest = (base / entry.filename).resolve()
                        if not dest.is_relative_to(base):
                            raise ValueError('Archive path escapes output directory')
                        dest.parent.mkdir(parents=True, exist_ok=True)
                        dest.write_bytes(jar.read(entry))
                    info['entries'].append(item)
        else:
            info['format'] = 'text'
        inventory.append(info)
    (OUT / 'reference/inventory/binaries.json').write_text(json.dumps(inventory, indent=2), encoding='utf-8')
    for item in inventory:
        if item['format'] == 'ELF32':
            print(item['file'], item['architecture'], 'symbols:', len(item['symbols']), 'debug:', item['debug_sections'])
        elif item['format'] == 'JAR/ZIP':
            classes = [e for e in item['entries'] if e['name'].endswith('.class')]
            print(item['file'], 'classes:', len(classes), 'local variable tables:', sum(e['local_variable_table_present'] for e in classes))


if __name__ == '__main__':
    main()
