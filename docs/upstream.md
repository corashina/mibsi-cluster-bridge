# Upstream input

The installer is built from the original MIBSI P5092 SD bundle, release
`7e792409480209467f17fb27`. The bundle is not part of this repository and is not
published here: it names no author, carries no license for its native binaries
or OEM classes, and part of it credits GPL-3.0 code (see [NOTICE.md](../NOTICE.md)).
Obtain it from its original source.

## Files and SHA-256

| File | Size (bytes) | SHA-256 |
| --- | ---: | --- |
| `mibsi-p5092-7e792409480209467f17fb27-a215f63ae2304ec2-sd-bundle.zip` | 2162938 | `616b6a5b5c28d301844e874fbe6bccc3c46b00e475df534192671dc37c6f40cd` |
| `install.zip` (inside the bundle) | 1086259 | `69a186d31b320b922e536581fb2f3fe4bdce2517c7dae34fbd8ae6b0dd9bd60b` |
| `uninstall.zip` (inside the bundle) | 1086406 | `e24da44fa92ee27ec8d01e79177fbdb813dd9a6dec66dc3457c18e6e22933528` |
| `README.txt` (inside the bundle) | 1114 | `bd7cb77597d2c19e2f6aed5aebfd5fe29896c23cffd2338aaed52fef6add67fd` |

The 34 individual files the build uses are listed with their hashes in
[`reference/inventory/original-sha256.json`](../reference/inventory/original-sha256.json).

## Importing

The bundle is a wrapper. Extract it and pass the inner `install.zip`:

```sh
python scripts/import_upstream.py path/to/install.zip
```

Passing the outer bundle ZIP fails with
`There is no item named 'Bootstrap/final/finalScript.sh' in the archive`.

## Uninstalling

This project does not build an uninstaller. Units installed from release
`7e792409480209467f17fb27` are restored with the bundle's own `uninstall.zip`.
`fixtures/upstream/` is git-ignored, so imported files are never committed.
