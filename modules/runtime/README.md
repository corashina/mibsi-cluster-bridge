# Runtime integration

Java runtime compatibility code, package assembly, and integration verification.
Application behavior is retained while installation assets use project-level naming.

- `src/main/java/`: firmware-compatible input handler.
- `package/mibsi-support.sh`: status report, guarded cleanup, uninstall preflight and
  menu staging cleanup. Packaging hooks it into the upstream `install_product.sh`
  with three lines (`scripts/support_patch.py`); everything else in that script is unchanged.
- `scripts/sd_package.py`: SD checksums (`hashes.txt`, `metainfo2.txt`, `SHA256SUMS`) and
  the uninstall package, derived from the install package.
- `scripts/PatchJar.java`: validates two behavior changes after the JAR namespace is relocated.
- `tests/`: input tests with service doubles.
- `tests/integration/`: packaged-class tests with simulated firmware services.

Run `python scripts/verify.py` from the repository root for standalone checks.
Run `python scripts/release.py` with the matching original inputs and pinned ASM
libraries to build and verify the installation package. Generated files live under
`build/`; final release assets are staged in `dist/release/`.

See [build and verification](../../README.md#build-and-verify).
