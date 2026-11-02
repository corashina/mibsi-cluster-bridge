# MIBSI Cluster Bridge

MIBSI Cluster Bridge integrates Android Auto and CarPlay with the instrument
cluster on MHI2Q P5092 firmware, with tools for installation and maintenance.

**Firmware:** `MHI2Q_ER_AUG22_P5092_MU1329`  
**Status:** experimental; desktop checks pass, physical MMI validation is pending.

## Installation and updates

Download `MIBSI-Cluster-Bridge-install.zip` and `SHA256SUMS.txt` from a published release.
The repository source ZIP is not an installer. Verify the downloaded archive:

```powershell
Get-FileHash .\MIBSI-Cluster-Bridge-install.zip -Algorithm SHA256
```

On Linux use `sha256sum`; on macOS use `shasum -a 256`.

1. Extract the installer to an empty FAT32 SD card. `metainfo2.txt` and `Bootstrap`
   must be directly at the card root.
2. Open the MMI red update menu and select **Update → SD card → Standard**.
3. Start the update. Keep the unit powered and the card inserted until completion.
4. Remove the card and restart the MMI after the update completes.

Updates preserve the original installation backup and do not require uninstalling
first. If installation fails, retain the displayed error and `MIBSI-Logs/install.log`
from the SD card when available.

## Status report

`MIBSI-Cluster-Bridge-status.zip` is used like the installer, but it changes nothing on
the unit. It writes `MIBSI-Logs/status.log` to the card with every MIBSI path, its
SHA-256, any remaining backup history, and a verdict. The same report runs when an empty
file named `MIBSI-STATUS` is placed at the root of an install card. Ask for this log
before giving advice about a unit.

## Uninstallation

The installation backup is stored inside the unit, in `/mnt/app/root/mibsi/`. It is not
on the SD card, so formatting the install card does not lose it.

Uninstallation requires that saved backup and a matching uninstall package from the
provider of your installed release. This project currently builds installation packages
only; no uninstall archive or hardware restoration has been validated. Reinstalling
MIBSI Cluster Bridge does not uninstall it.

Do not add `MIBSI-UNINSTALL` to the installation archive: uninstall operation metadata
and checksums must also match. Keep the original backup available for restoration.

`MIBSI: no V2 installation to uninstall; existing files kept` means the unit has no
`/mnt/app/root/mibsi/origin` backup pointer. A completed uninstall removes that pointer,
so running uninstall a second time prints this message. Uninstall packages built from
this project's installer script now stop with an error in that case and write the status
report, instead of reporting success.

### Leftovers after uninstall

A completed uninstall restores every replaced file but keeps its backup history and lock
file in `/mnt/app/root/mibsi/`. Every SD run also used to leave
`/mnt/app/eso/hmi/engdefs/mibsi-main.esd.new`, and older bootstrap releases left
`mibsi-main.esd` and `engdefs/scripts/mibsi/`. None of these are loaded, but other CarPlay
patches may detect them and refuse to install. Packages from this project now remove
`mibsi-main.esd.new` after a successful operation.

`MIBSI-Cluster-Bridge-cleanup.zip` (or an empty `MIBSI-CLEANUP` file on an install card)
removes these leftovers. It first copies `/mnt/app/root/mibsi/` to
`MIBSI-Logs/backup-archive-<time>/` on the card and checks every file in the copy. It
refuses, and changes nothing, on other firmware, while a backup pointer or interrupted
operation exists, while any MIBSI binary is present, or while `gal`, `dio_manager`,
`dio_manager.json` or `lsd.sh` still holds MIBSI content. Keep the archive folder from
the card. Do not delete files under `/eso` or `lsd.sh` by hand. Cleanup has not been
tested on a vehicle.

## Build and verify

Install **Python 3.10+** and **JDK 25**. Set `JAVA_HOME` or add the JDK's `bin` directory
to `PATH`. Run from the repository root:

```sh
python scripts/verify.py
```

Standalone checks need no firmware bundle or third-party Python packages. CI is
configured to run them on Windows and Linux.

For a complete installer build:

```sh
python scripts/setup.py
python scripts/import_upstream.py /path/to/original-bundle
python scripts/release.py
```

Setup downloads SHA-256-verified ASM 9.7.1 dependencies. Import accepts an original
SD directory or baseline ZIP and verifies all 34 input files against release
`7e792409480209467f17fb27` before storing them in `fixtures/upstream/`. Alternatively,
set `MIBSI_UPSTREAM` to the matching input directory.

The release command runs fresh build, baseline, package, and integration checks.
Successful outputs go to `dist/release/`: the installer, the status and cleanup
packages, checksums, and verification reports. Failed runs leave no release at that path. Source changes during verification
reject the build. `python scripts/verify.py --full` runs the same workflow.

To export the repository sources:

```sh
python scripts/source_archive.py
```

This creates `dist/mibsi-cluster-bridge-source.zip`. Tools, original inputs, and build outputs
are excluded. Release commands build locally; they do not publish or install on a unit.

## Project structure and test scope

| Location | Purpose |
| --- | --- |
| `modules/navigation-core/` | Maneuver mapping and turn-card calculations |
| `modules/runtime/` | Runtime integration, package assembly, and input tests |
| `scripts/` | Setup, input import, verification, packaging, and optional analysis |
| `reference/` | Wider Java application, native pseudocode, and input inventory |
| `build/`, `dist/`, `.tools/` | Generated outputs and dependencies; excluded from Git |

Tests cover navigation boundaries, comparison with the original JAR, runtime fallback
behavior, package hashes and receipts, and offline integration with simulated services.
They do not validate physical controls, installation, projection rendering, or the MMI
JVM. The complete firmware application is not rebuilt: OEM dependencies are absent,
and native reference files are pseudocode rather than compiler inputs.

Application Java packages use `com.mibsi.carplay`. The build translates the baseline
JAR to this namespace before applying the runtime changes; OEM API names and original
input hashes remain unchanged.
