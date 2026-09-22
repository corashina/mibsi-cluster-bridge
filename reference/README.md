# Reference material

This directory supports inspection and compatibility work. It is not an additional
buildable application module.

Java package names in the readable sources, disassembly and resources are normalized
to `com.mibsi.carplay`. Original binary inputs and their hashes remain unchanged.

| Directory | Contents |
| --- | --- |
| `java/application/` | Wider application Java, excluding the two maintained navigation classes |
| `java/comparison/` | Alternative Java output for comparison |
| `native/` | Native pseudocode and function indexes; not buildable C |
| `bytecode/` | Baseline Java disassembly with project package names |
| `inventory/` | Input hashes, binary metadata, symbol indexes, and strings |
| `resources/` | Module manifests and resources with project package names |
| `distribution/` | Original deployment text and configuration |
| `binaries/` | Local binary copies; excluded from Git and source archives |

See [project structure and test scope](../README.md#project-structure-and-test-scope). Do not edit baseline hashes to make a
changed release pass verification. Reference material is marked as generated in
`.gitattributes` so it does not dominate GitHub's language statistics.
