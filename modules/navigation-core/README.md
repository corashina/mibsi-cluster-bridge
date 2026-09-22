# Navigation core

Pure Java maneuver mapping and turn-card presentation policy.

- `src/main/java/`: `ManeuverMapper` and `TurnCardPolicy`.
- `tests/NavigationCoreTest.java`: standalone behavior and threshold checks.
- `tests/JavaCompatibilityTest.java`: differential comparison with the original JAR.

Run `python scripts/verify.py` from the repository root for standalone tests.
With original inputs available, `python scripts/release.py` also runs the baseline
comparison. The module does not require or implement OEM display services.
