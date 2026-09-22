import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;
import org.objectweb.asm.util.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;

public final class PatchJar {
    static final String HANDLER = "com/mibsi/carplay/core/CarPlaySteeringMenuInput";
    static final String LISTENER = "de/audi/kbd/dsi/KbdListener";
    static String trace(MethodNode method) {
        Textifier text = new Textifier();
        method.accept(new TraceMethodVisitor(text));
        StringWriter result = new StringWriter();
        text.print(new PrintWriter(result));
        return result.toString();
    }
    static ClassNode parse(byte[] bytes) {
        ClassNode node = new ClassNode();
        new ClassReader(bytes).accept(node, 0);
        return node;
    }
    static byte[] legacyHandler(byte[] input) {
        ClassNode node = parse(input);
        if (!node.name.equals(HANDLER)) throw new IllegalStateException("wrong replacement class");
        // The source contains no generics, class literals, Java 5 APIs, or modern bytecodes.
        Set<String> allowed = new HashSet<>(Arrays.asList("java/lang/Object", "java/lang/Class",
            "java/lang/Throwable", "java/lang/IllegalStateException", "java/lang/System",
            "java/lang/StringBuffer", "java/lang/reflect/Field", "java/io/PrintStream",
            HANDLER, "com/mibsi/carplay/core/CarPlayApp", "com/mibsi/carplay/core/Mhi2qAaClusterComposition"));
        for (MethodNode method : node.methods) {
            for (AbstractInsnNode insn : method.instructions.toArray()) {
                if (insn instanceof FrameNode) method.instructions.remove(insn);
                if (insn instanceof InvokeDynamicInsnNode || insn instanceof MultiANewArrayInsnNode)
                    throw new IllegalStateException("unsupported modern instruction");
                if (insn instanceof LdcInsnNode && !(((LdcInsnNode)insn).cst instanceof String) &&
                    !(((LdcInsnNode)insn).cst instanceof Integer)) throw new IllegalStateException("unexpected constant");
                String owner = insn instanceof MethodInsnNode ? ((MethodInsnNode)insn).owner :
                    insn instanceof FieldInsnNode ? ((FieldInsnNode)insn).owner :
                    insn instanceof TypeInsnNode ? ((TypeInsnNode)insn).desc : null;
                if (owner != null && !allowed.contains(owner)) throw new IllegalStateException("non-legacy owner: " + owner);
            }
        }
        node.version = Opcodes.V1_4;
        node.sourceFile = null;
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        node.accept(writer);
        byte[] result = writer.toByteArray();
        new ClassReader(result).accept(new CheckClassAdapter(new ClassWriter(0), true), 0);
        return result;
    }
    static byte[] patchListener(byte[] input) {
        ClassNode node = parse(input);
        if (node.version != Opcodes.V1_4) throw new IllegalStateException("unexpected listener version");
        Map<String, String> originalMethods = new HashMap<>();
        int changed = 0;
        for (MethodNode method : node.methods) {
            originalMethods.put(method.name + method.desc, trace(method));
            if (!method.name.equals("updateKeyInternal") || !method.desc.equals("(III)V")) continue;
            for (AbstractInsnNode insn : method.instructions.toArray()) {
                if (!(insn instanceof MethodInsnNode)) continue;
                MethodInsnNode call = (MethodInsnNode)insn;
                if (call.getOpcode() != Opcodes.INVOKESTATIC || !call.owner.equals(HANDLER) ||
                    !call.name.equals("onKeyState") || !call.desc.equals("(III)V")) continue;
                AbstractInsnNode prior = call.getPrevious();
                for (int slot = 3; slot >= 1; slot--) {
                    if (!(prior instanceof VarInsnNode) || prior.getOpcode() != Opcodes.ILOAD || ((VarInsnNode)prior).var != slot)
                        throw new IllegalStateException("unexpected hook argument sequence");
                    prior = prior.getPrevious();
                }
                call.name = "handleKeyState";
                call.desc = "(III)Z";
                LabelNode pass = new LabelNode();
                InsnList branch = new InsnList();
                branch.add(new JumpInsnNode(Opcodes.IFEQ, pass));
                branch.add(new InsnNode(Opcodes.RETURN));
                branch.add(pass);
                method.instructions.insert(call, branch);
                changed++;
            }
        }
        if (changed != 1) throw new IllegalStateException("expected exactly one existing hook, got " + changed);
        ClassWriter writer = new ClassWriter(new ClassReader(input), ClassWriter.COMPUTE_MAXS);
        node.accept(writer);
        byte[] result = writer.toByteArray();
        ClassNode reparsed = parse(result);
        for (MethodNode method : reparsed.methods) {
            if (!method.name.equals("updateKeyInternal")) {
                if (!trace(method).equals(originalMethods.get(method.name + method.desc)))
                    throw new IllegalStateException("unrelated method changed: " + method.name);
            }
        }
        new ClassReader(result).accept(new CheckClassAdapter(new ClassWriter(0), true), 0);
        System.out.println("PASS: one OEM dispatch hook changed; all other listener methods have identical instruction traces");
        return result;
    }
    public static void main(String[] args) throws Exception {
        byte[] handler = legacyHandler(Files.readAllBytes(Path.of(args[1])));
        Path classes = Path.of(args[3]);
        Files.createDirectories(classes.resolve(HANDLER).getParent());
        Files.write(classes.resolve(HANDLER + ".class"), handler);
        int changed = 0;
        try (ZipFile original = new ZipFile(args[0]); ZipOutputStream output = new ZipOutputStream(Files.newOutputStream(Path.of(args[2])))) {
            for (Enumeration<? extends ZipEntry> entries = original.entries(); entries.hasMoreElements();) {
                ZipEntry entry = entries.nextElement();
                byte[] bytes = original.getInputStream(entry).readAllBytes();
                if (entry.getName().equals(HANDLER + ".class")) { bytes = handler; changed++; }
                if (entry.getName().equals(LISTENER + ".class")) { bytes = patchListener(bytes); changed++; }
                ZipEntry replacement = new ZipEntry(entry.getName());
                replacement.setTime(entry.getTime());
                replacement.setMethod(entry.getMethod());
                if (entry.getComment() != null) replacement.setComment(entry.getComment());
                if (entry.getMethod() == ZipEntry.STORED) {
                    CRC32 crc = new CRC32(); crc.update(bytes);
                    replacement.setSize(bytes.length); replacement.setCompressedSize(bytes.length); replacement.setCrc(crc.getValue());
                }
                output.putNextEntry(replacement); output.write(bytes); output.closeEntry();
            }
        }
        if (changed != 2) throw new IllegalStateException("expected exactly two replaced entries");
        System.out.println("PASS: rebuilt JAR with exactly two changed class entries; replacement targets class version 48");
    }
}
