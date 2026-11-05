# Notices

MIBSI Cluster Bridge is licensed under the GNU General Public License, version 3 or
later. See [LICENSE](LICENSE).

## Upstream attribution

This project builds on MIBSI P5092 release `7e792409480209467f17fb27`, whose Java
application used the `com.nicolas.carplay` package. The build moves it to
`com.mibsi.carplay`. Renaming the package does not transfer authorship.

`com.mibsi.carplay.framework.Log` (formerly `com.nicolas.carplay.framework.Log`, the
inherited logger) is Copyright (c) 2026 LuKa (@LuKa_dev), from
[luka-dev/mib2q-carplay-rgi](https://github.com/luka-dev/mib2q-carplay-rgi), licensed
GPL-3.0. The baseline JAR's `META-INF/NOTICE.txt` records this and is kept in built
packages.

## Third-party material

- The turn-card renderer in the baseline embeds a Noto font, Copyright 2022 The Noto
  Project Authors, under the SIL Open Font License 1.1.
- The build downloads ASM 9.7.1 (BSD-3-Clause) as a tool. It is not shipped in packages.
- The native binaries in the baseline (`libmhi2qcarplaycluster.so`, `libmhi2qaacluster.so`,
  the loaders, display and renderer tools) are shipped as received. The baseline states
  no license for them.
- Classes in the `de.audi`, `de.esolutions` and `org.dsi` packages are OEM firmware
  code. They remain the property of their owners and are not covered by this
  project's license.
