// Exports Ghidra's reconstructed C-like pseudocode, not original source.
// @category MIBSI
import ghidra.app.script.GhidraScript;
import ghidra.app.decompiler.DecompInterface;
import ghidra.app.decompiler.DecompileResults;
import ghidra.program.model.listing.Function;
import ghidra.program.model.listing.FunctionIterator;
import ghidra.program.model.mem.MemoryBlock;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class ExportNativeCode extends GhidraScript {
    @Override
    public void run() throws Exception {
        File outputDir = new File(getScriptArgs()[0]);
        outputDir.mkdirs();
        String name = currentProgram.getName();
        DecompInterface decompiler = new DecompInterface();
        int success = 0, failed = 0, skipped = 0;
        if (!decompiler.openProgram(currentProgram)) {
            throw new IOException("Decompiler could not open " + name);
        }
        try (PrintWriter code = new PrintWriter(new File(outputDir, name + ".c"), StandardCharsets.UTF_8);
             PrintWriter index = new PrintWriter(new File(outputDir, name + ".functions.tsv"), StandardCharsets.UTF_8)) {
            code.println("/* RECONSTRUCTED PSEUDOCODE - not original source, not build-ready.");
            code.println(" * Input: " + name);
            code.println(" * Language: " + currentProgram.getLanguageID());
            code.println(" * Generated with Ghidra. Types, signatures and control flow may be inaccurate.");
            code.println(" */\n");
            index.println("address\tname\tstatus");
            FunctionIterator functions = currentProgram.getFunctionManager().getFunctions(true);
            while (functions.hasNext() && !monitor.isCancelled()) {
                Function function = functions.next();
                MemoryBlock block = currentProgram.getMemory().getBlock(function.getEntryPoint());
                if (function.isExternal() || block == null || !block.isInitialized() || "EXTERNAL".equals(block.getName())) {
                    skipped++;
                    continue;
                }
                DecompileResults result = decompiler.decompileFunction(function, 45, monitor);
                code.println("\n/* Function: " + function.getName() + " @ " + function.getEntryPoint() + " */");
                if (result.decompileCompleted() && result.getDecompiledFunction() != null) {
                    code.println(result.getDecompiledFunction().getC());
                    index.println(function.getEntryPoint() + "\t" + function.getName() + "\tok");
                    success++;
                } else {
                    String error = result.getErrorMessage().replace("*/", "* /").replace('\n', ' ');
                    code.println("/* DECOMPILATION FAILED: " + error + " */");
                    index.println(function.getEntryPoint() + "\t" + function.getName() + "\tfailed: " + error);
                    failed++;
                }
            }
        } finally {
            decompiler.dispose();
        }
        try (PrintWriter summary = new PrintWriter(new File(outputDir, name + ".summary.txt"), StandardCharsets.UTF_8)) {
            summary.println("Input: " + name);
            summary.println("Language: " + currentProgram.getLanguageID());
            summary.println("Functions decompiled: " + success);
            summary.println("Functions failed: " + failed);
            summary.println("Skipped external/uninitialized functions: " + skipped);
        }
        println("EXPORT " + name + ": " + success + " succeeded, " + failed + " failed");
    }
}
