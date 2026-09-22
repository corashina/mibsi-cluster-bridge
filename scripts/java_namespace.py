"""Translate the upstream Java namespace when importing the runtime JAR."""
import struct
import zipfile

UPSTREAM_PACKAGE = 'com.nicolas.carplay'
PROJECT_PACKAGE = 'com.mibsi.carplay'


def rename_bytes(data):
    # Old Java compilers also encode package names in synthetic class$ fields.
    for separator in ('.', '/', '$'):
        old = UPSTREAM_PACKAGE.replace('.', separator).encode('ascii')
        new = PROJECT_PACKAGE.replace('.', separator).encode('ascii')
        data = data.replace(old, new)
    return data


def rename_path(name):
    return rename_bytes(name.encode('utf-8')).decode('utf-8')


def relocate_class(data):
    """Rewrite UTF-8 constants without changing indexes, code, or attributes.

    Class names, descriptors, signatures and reflective lookup strings all use
    constant-pool UTF-8 entries. ASCII substitution also preserves Java's
    modified UTF-8 encoding for unrelated characters.
    """
    if data[:4] != b'\xca\xfe\xba\xbe':
        raise ValueError('Invalid Java class')
    count = struct.unpack_from('>H', data, 8)[0]
    output = bytearray(data[:10])
    offset, index = 10, 1
    sizes = {3: 4, 4: 4, 5: 8, 6: 8, 7: 2, 8: 2, 9: 4,
             10: 4, 11: 4, 12: 4, 15: 3, 16: 2, 17: 4, 18: 4,
             19: 2, 20: 2}
    while index < count:
        tag = data[offset]
        output.append(tag)
        offset += 1
        if tag == 1:
            size = struct.unpack_from('>H', data, offset)[0]
            offset += 2
            value = rename_bytes(data[offset:offset + size])
            output.extend(struct.pack('>H', len(value)))
            output.extend(value)
        else:
            if tag not in sizes:
                raise ValueError('Unknown constant-pool tag: ' + str(tag))
            size = sizes[tag]
            output.extend(data[offset:offset + size])
        offset += size
        index += 2 if tag in (5, 6) else 1
    output.extend(data[offset:])
    return bytes(output)


def relocate_entry(name, data):
    if name.endswith('.class'):
        return relocate_class(data)
    # The upstream JAR's remaining resources are manifests and plain text.
    if name.lower().endswith(('.mf', '.txt', '.properties', '.xml')):
        return rename_bytes(data)
    if rename_bytes(data) != data:
        raise ValueError('Unsupported resource references upstream namespace: ' + name)
    return data


def relocate_jar(source, destination):
    with zipfile.ZipFile(source) as original, zipfile.ZipFile(destination, 'w') as output:
        names = set()
        for entry in original.infolist():
            if entry.filename == 'META-INF/NOTICE.txt':
                continue
            data = relocate_entry(entry.filename, original.read(entry))
            entry.filename = rename_path(entry.filename)
            if entry.filename in names:
                raise ValueError('Namespace relocation causes duplicate JAR entry')
            names.add(entry.filename)
            output.writestr(entry, data)
