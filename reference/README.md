# Reference material

`inventory/original-sha256.json` lists the 34 files of the original MIBSI P5092
release `7e792409480209467f17fb27` with their SHA-256. `scripts/import_upstream.py`,
the build and the tests check the imported bundle against it. Do not edit these
hashes to make a changed release pass verification.

The original bundle, its binaries and anything decompiled or extracted from them are
not kept in this repository. See [docs/upstream.md](../docs/upstream.md).
