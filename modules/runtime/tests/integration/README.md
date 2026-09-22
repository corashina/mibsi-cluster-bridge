# Offline keyboard integration tests

The suite loads the keyboard listener and replacement handler from the candidate
installer JAR without changing their bytes in its normal test run. OEM services,
key constants, display state, and event sinks are simulated.

Scenarios cover short/long/repeated input presses, both key entry points, OEM forwarding,
layout and disconnect transitions, roller events, missing/mismatched constants, and
single-Joker firmware shapes. Two negative controls deliberately break OEM suppression
and menu toggling; each must fail at the expected behavioral assertion.

Run the complete suite through `python scripts/release.py` from the repository root.
During a build, logs are written to `build/integration/`; a successful release includes
`integration.json` in `dist/release/`. No report or compiled output is committed here.

This is not QNX/MMI emulation. Synthetic key values are test fixtures, not claimed
vehicle values. Real display services, native code, installation, and physical button
delivery are not exercised. See [verification scope](../../../../README.md#project-structure-and-test-scope).
