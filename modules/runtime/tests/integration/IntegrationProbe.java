// Offline keyboard integration test; firmware services are simulated.
import java.io.*;
import java.lang.reflect.*;
import java.nio.file.*;
import java.security.*;
import java.util.*;
import java.util.zip.*;
import org.objectweb.asm.*;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.tree.*;

public final class IntegrationProbe {
    static final String LISTENER = "de/audi/kbd/dsi/KbdListener";
    static final String HANDLER = "com/mibsi/carplay/core/CarPlaySteeringMenuInput";
    static final String CONSTANTS = "org/dsi/ifc/keypanel/Constants";
    static final String APP = "com/mibsi/carplay/core/CarPlayApp";
    static final String VIEW = "com/mibsi/carplay/core/Mhi2qAaClusterComposition";
    static final String FRAMEWORK = "de/audi/atip/base/IFrameworkAccess";
    static final String KEYBOARD = "de/audi/atip/hmi/view/IKeyBoardManager";
    static final String KEYMAP = "de/audi/kbd/KeyMap";
    static final org.objectweb.asm.Type OBJECT = org.objectweb.asm.Type.getType(Object.class);
    static Model model;
    static int assertions, scenarios;
    static final List<String> passed = new ArrayList<>();
    static final List<String> simulationErrors = new ArrayList<>();

    static final class Model {
        boolean active = true, large = true;
        int toggles, selections;
        final List<String> keys = new ArrayList<>(), wheels = new ArrayList<>();
        final Set<String> calls = new TreeSet<>();
        final Loader loader;
        Model(Loader loader) { this.loader = loader; }
    }

    // Every exercised simulation boundary is explicit. Unexpected calls fail.
    public static Object simulatedCall(String owner, String name, String desc, Object[] args) throws Exception {
        try {
            Object result = simulate(owner, name, desc, args);
            int sort = org.objectweb.asm.Type.getReturnType(desc).getSort();
            if (sort >= org.objectweb.asm.Type.BOOLEAN && sort <= org.objectweb.asm.Type.DOUBLE && result == null)
                throw new AssertionError("Null primitive result from simulated dependency");
            return result;
        } catch (Throwable error) {
            // Production catches Throwable; keep evidence even if it swallows a bad test double.
            simulationErrors.add(owner + "." + name + desc + ": " + error);
            throw error;
        }
    }
    static Object simulate(String owner, String name, String desc, Object[] args) throws Exception {
        model.calls.add(owner + "." + name + desc);
        if (owner.equals(APP)) {
            if (name.equals("isActive")) return model.active;
            if (name.equals("toggleVcSettingsMenu")) { model.toggles++; return null; }
            if (name.equals("selectVcSettingsItem")) { model.selections++; return true; }
        }
        if (owner.equals(VIEW) && name.equals("isLargeView")) return model.large;
        if (owner.equals("de/audi/atip/log/LogChannel") && name.equals("log")) return false;
        if (owner.equals(FRAMEWORK)) {
            if (name.equals("isFrontMU")) return true;
            if (name.equals("isPorsche") || name.equals("isShowDDP2Combi")) return false;
            if (name.equals("getKombiType")) return 4;
            if (name.equals("getMonotonicTime")) return 123456L;
            if (Set.of("getLogChannel", "getStorageMgr", "getBundleCxt").contains(name))
                return model.loader.instance(org.objectweb.asm.Type.getReturnType(desc).getClassName());
        }
        if (owner.equals("de/audi/atip/storage/IStorageAccess") && name.equals("getInt")) return 0;
        if (owner.equals("org/osgi/util/tracker/ServiceTracker") && name.equals("open")) return null;
        if (owner.equals(KEYMAP)) {
            if (name.equals("kbdID2termID")) return 0;
            if (name.equals("getHmiKeyCode")) return args[0]; // deliberate identity mapping, not OEM key codes
            if (name.equals("isVolumeKey") || name.equals("isSdsAbortKey")) return false;
        }
        if (owner.equals("de/audi/kbd/dsi/ComboKeyDetector") &&
                Set.of("keyPressedConsumed", "keyReleasedConsumed").contains(name)) return false;
        if (owner.equals(KEYBOARD) && name.equals("fireKeyEvent")) {
            model.keys.add(args[0] + ":" + args[2]);
            return null;
        }
        if (owner.equals(KEYBOARD) && name.equals("fireWheelButtonEvent")) {
            model.wheels.add(args[2] + ":" + args[3] + ":" + args[4]);
            return null;
        }
        throw new AssertionError("Unmodeled dependency call: " + owner + "." + name + desc);
    }

    static final class Shape {
        boolean isInterface;
        String parent = "java/lang/Object";
        final Set<String> interfaces = new TreeSet<>();
        final Map<String, Integer> methods = new TreeMap<>();
        final Map<String, Integer> fields = new TreeMap<>();
    }
    static final class Loader extends ClassLoader {
        final Map<String, byte[]> real = new TreeMap<>();
        final Map<String, Shape> shapes = new TreeMap<>();
        final Set<String> definedReal = new TreeSet<>(), definedStubs = new TreeSet<>();
        final String constantsMode;
        Loader(Map<String, byte[]> source, String constantsMode, String mutation) {
            super(IntegrationProbe.class.getClassLoader());
            this.constantsMode = constantsMode;
            real.putAll(source);
            if (!mutation.equals("none")) mutate(mutation);
            for (byte[] bytes : real.values()) scan(bytes);
            shape(CONSTANTS);
            // Required assignment relationships visible in the listener's bytecode.
            shape("de/audi/kbd/dsi/KbdListener$1").interfaces.add("java/lang/Runnable");
            shape("de/audi/atip/hmi/event/RunnableEvent").parent = "de/audi/atip/hmi/event/ATIPEvent";
            shape("de/audi/atip/hmi/event/ATIPEvent");
        }
        Shape shape(String name) { return shapes.computeIfAbsent(name, x -> new Shape()); }
        void type(org.objectweb.asm.Type type) {
            if (type.getSort() == org.objectweb.asm.Type.METHOD) {
                for (org.objectweb.asm.Type arg : type.getArgumentTypes()) type(arg);
                type(type.getReturnType());
            } else if (type.getSort() == org.objectweb.asm.Type.ARRAY) type(type.getElementType());
            else if (type.getSort() == org.objectweb.asm.Type.OBJECT && !type.getInternalName().startsWith("java/")) shape(type.getInternalName());
        }
        void scan(byte[] bytes) {
            ClassNode node = parse(bytes);
            for (String name : node.interfaces) shape(name).isInterface = true;
            for (FieldNode field : node.fields) type(org.objectweb.asm.Type.getType(field.desc));
            for (MethodNode method : node.methods) {
                type(org.objectweb.asm.Type.getMethodType(method.desc));
                for (AbstractInsnNode insn : method.instructions) {
                    if (insn instanceof MethodInsnNode call && !call.owner.startsWith("java/")) {
                        Shape target = shape(call.owner);
                        if (call.itf) target.isInterface = true;
                        target.methods.put(call.name + call.desc, call.getOpcode() == Opcodes.INVOKESTATIC ? Opcodes.ACC_STATIC : 0);
                        type(org.objectweb.asm.Type.getMethodType(call.desc));
                    } else if (insn instanceof FieldInsnNode field && !field.owner.startsWith("java/")) {
                        shape(field.owner).fields.put(field.name + ":" + field.desc,
                            field.getOpcode() == Opcodes.GETSTATIC || field.getOpcode() == Opcodes.PUTSTATIC ? Opcodes.ACC_STATIC : 0);
                        type(org.objectweb.asm.Type.getType(field.desc));
                    } else if (insn instanceof TypeInsnNode t) type(t.desc.startsWith("[") ? org.objectweb.asm.Type.getType(t.desc) : org.objectweb.asm.Type.getObjectType(t.desc));
                }
            }
        }
        void mutate(String mutation) {
            String target = mutation.equals("leak-oem") ? LISTENER : HANDLER;
            ClassNode node = parse(real.get(target));
            int changes = 0;
            for (MethodNode method : node.methods) for (AbstractInsnNode insn : method.instructions.toArray()) {
                if (insn instanceof MethodInsnNode call) {
                    if (mutation.equals("leak-oem") && call.name.equals("handleKeyState")) {
                        AbstractInsnNode branch = call.getNext();
                        if (!(branch instanceof JumpInsnNode) || branch.getNext().getOpcode() != Opcodes.RETURN)
                            throw new AssertionError("unexpected hook");
                        method.instructions.set(branch.getNext(), new InsnNode(Opcodes.NOP)); changes++;
                    }
                    if (mutation.equals("no-toggle") && call.owner.equals(APP) && call.name.equals("toggleVcSettingsMenu")) {
                        method.instructions.remove(call); changes++;
                    }
                }
            }
            if (changes == 0) throw new AssertionError("mutation did not change anything");
            ClassWriter writer = new ClassWriter(0); node.accept(writer); real.put(target, writer.toByteArray());
        }
        @Override protected Class<?> findClass(String name) throws ClassNotFoundException {
            String key = name.replace('.', '/');
            if (key.equals(CONSTANTS) && constantsMode.equals("missing")) throw new ClassNotFoundException(name);
            byte[] bytes = real.get(key);
            if (bytes != null) definedReal.add(key);
            else {
                if (!shapes.containsKey(key)) throw new ClassNotFoundException(name);
                bytes = stub(key); definedStubs.add(key);
            }
            return defineClass(name, bytes, 0, bytes.length);
        }
        Object instance(String name) throws Exception {
            Class<?> cls = loadClass(name);
            if (!cls.isInterface()) return cls.getConstructor().newInstance();
            return Proxy.newProxyInstance(this, new Class<?>[]{cls}, (proxy, method, args) ->
                simulatedCall(name.replace('.', '/'), method.getName(), org.objectweb.asm.Type.getMethodDescriptor(method), args == null ? new Object[0] : args));
        }
        byte[] stub(String name) {
            Shape s = shape(name);
            ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            writer.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC | (s.isInterface ? Opcodes.ACC_INTERFACE | Opcodes.ACC_ABSTRACT : Opcodes.ACC_SUPER),
                name, null, s.parent, s.interfaces.toArray(new String[0]));
            if (name.equals(CONSTANTS)) {
                Map<String,Integer> keys = new TreeMap<>();
                keys.put("KBD_MFW", constantsMode.equals("mismatch") ? 99 : 4);
                keys.put("KEY_MFW_ROLLER_LEFT", 40);
                keys.put("KEY_MFW_JOKER1", 158);
                if (!constantsMode.equals("one-key")) keys.put("KEY_MFW_JOKER2", 159);
                for (var field : keys.entrySet()) writer.visitField(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC | Opcodes.ACC_FINAL, field.getKey(), "I", null, field.getValue()).visitEnd();
            }
            for (var field : s.fields.entrySet()) {
                String[] parts = field.getKey().split(":", 2);
                writer.visitField(Opcodes.ACC_PUBLIC | field.getValue(), parts[0], parts[1], null, null).visitEnd();
            }
            Map<String,Integer> methods = new TreeMap<>(s.methods);
            if (!s.isInterface) methods.putIfAbsent("<init>()V", 0);
            for (var entry : methods.entrySet()) {
                int split = entry.getKey().indexOf('(');
                String method = entry.getKey().substring(0, split), desc = entry.getKey().substring(split);
                int access = Opcodes.ACC_PUBLIC | entry.getValue();
                if (s.isInterface) {
                    writer.visitMethod(access | Opcodes.ACC_ABSTRACT, method, desc, null, null).visitEnd(); continue;
                }
                GeneratorAdapter gen = new GeneratorAdapter(access, new org.objectweb.asm.commons.Method(method, desc), null, null, writer);
                if (method.equals("<init>")) {
                    gen.loadThis(); gen.invokeConstructor(org.objectweb.asm.Type.getObjectType(s.parent), new org.objectweb.asm.commons.Method("<init>", "()V"));
                } else {
                    gen.push(name); gen.push(method); gen.push(desc);
                    org.objectweb.asm.Type[] args = org.objectweb.asm.Type.getArgumentTypes(desc);
                    gen.push(args.length); gen.newArray(OBJECT);
                    for (int i = 0; i < args.length; i++) { gen.dup(); gen.push(i); gen.loadArg(i); gen.box(args[i]); gen.arrayStore(OBJECT); }
                    gen.invokeStatic(org.objectweb.asm.Type.getType(IntegrationProbe.class), new org.objectweb.asm.commons.Method("simulatedCall", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;"));
                    org.objectweb.asm.Type result = org.objectweb.asm.Type.getReturnType(desc);
                    if (result.equals(org.objectweb.asm.Type.VOID_TYPE)) gen.pop(); else gen.unbox(result);
                }
                gen.returnValue(); gen.endMethod();
            }
            writer.visitEnd(); return writer.toByteArray();
        }
    }
    static ClassNode parse(byte[] bytes) { ClassNode node = new ClassNode(); new ClassReader(bytes).accept(node, 0); return node; }
    static void check(boolean value, String message) { assertions++; if (!value) throw new AssertionError(message); }
    static Object listener;
    static Method key, external, encoder;
    static final Set<String> allCalls = new TreeSet<>(), allStubs = new TreeSet<>();
    static Map<String,byte[]> originalBytes;
    static String mutation;
    static void start(String mode) throws Exception {
        if (model != null) { allCalls.addAll(model.calls); allStubs.addAll(model.loader.definedStubs); }
        Loader loader = new Loader(originalBytes, mode, mutation);
        model = new Model(loader);
        Class<?> cls = loader.loadClass(LISTENER.replace('/', '.'));
        Class<?> framework = loader.loadClass(FRAMEWORK.replace('/', '.'));
        Class<?> keyboard = loader.loadClass(KEYBOARD.replace('/', '.'));
        listener = cls.getConstructor(framework, keyboard).newInstance(loader.instance(framework.getName()), loader.instance(keyboard.getName()));
        key = cls.getMethod("updateKey2", int.class, int.class, int.class, int.class, int.class);
        external = cls.getMethod("supplyExternalKey", int.class, int.class, int.class, int.class);
        encoder = cls.getMethod("updateEncoder2", int.class, int.class, int.class, int.class, int.class);
    }
    static void event(int board, int code, int state) throws Exception { key.invoke(listener, board, code, state, 0, 1); }
    static void click(int board, int code) throws Exception { event(board, code, 1); event(board, code, 0); }
    static void passed(String name) { scenarios++; passed.add(name); System.out.println("PASS: " + name); }
    static void suite() throws Exception {
        for (int joker : new int[]{158,159}) {
            start("resolved"); click(4, joker);
            check(model.toggles == 1, "short input must request exactly one menu toggle");
            check(model.keys.isEmpty(), "captured input must not reach OEM event sink");
            check(model.calls.stream().noneMatch(call -> call.startsWith(KEYMAP+".")), "captured input must return before OEM mapping");
            click(4, joker); check(model.toggles == 2 && model.keys.isEmpty(), "second click toggle and suppression");
            passed("public updateKey2 -> input " + joker + " -> menu request, no OEM key events");
            start("resolved"); external.invoke(listener, 4, joker, 1, 1); external.invoke(listener, 4, joker, 0, 1);
            check(model.toggles == 1 && model.keys.isEmpty(), "external input path"); passed("supplyExternalKey input " + joker);
            for (int release : new int[]{0,5}) {
                start("resolved"); event(4,joker,1); event(4,joker,4); event(4,joker,3); event(4,joker,3); event(4,joker,release);
                check(model.toggles == 0 && model.keys.isEmpty(), "long input suppressed without toggling");
                check(model.calls.stream().noneMatch(call -> call.startsWith(KEYMAP+".")), "all long-press states must return before OEM mapping");
                passed("long input " + joker + " release " + release);
            }
            start("resolved"); event(4,joker,1); event(4,joker,1); event(4,joker,0);
            check(model.toggles == 1 && model.keys.isEmpty(), "repeated press"); passed("repeat input " + joker);
            for (String condition : new String[]{"inactive", "small-view", "other-keyboard"}) {
                start("resolved"); model.active = !condition.equals("inactive"); model.large = !condition.equals("small-view");
                click(condition.equals("other-keyboard") ? 1 : 4, joker);
                check(model.toggles == 0 && model.keys.equals(List.of("10401:"+joker, "10402:"+joker)), "OEM forwarding " + condition);
                passed("OEM input " + joker + " forwarding: " + condition);
            }
            for (boolean disconnect : new boolean[]{true,false}) {
                start("resolved"); event(4,joker,1);
                if (disconnect) model.active=false; else model.large=false;
                event(4,joker,0);
                check(model.toggles == 0 && model.keys.isEmpty(), "drain release after eligibility lost"); passed("mid-press " + (disconnect?"disconnect":"view change") + " " + joker);
            }
            start("resolved"); key.invoke(listener,4,joker,1,0,0); key.invoke(listener,4,joker,0,0,0);
            external.invoke(listener,4,joker,1,0); external.invoke(listener,4,joker,0,0);
            check(model.toggles == 0 && model.keys.isEmpty(), "invalid events ignored upstream"); passed("invalid input flags " + joker);
        }
        start("resolved"); click(4,40);
        check(model.selections == 1 && model.keys.equals(List.of("10401:40","10402:40")), "roller confirmation and original OEM forwarding");
        event(4,40,1); event(4,40,3); event(4,40,0);
        check(model.toggles == 0 && model.selections == 1, "roller hold no longer toggles"); passed("roller select and long-press behavior");
        start("resolved"); encoder.invoke(listener,4,40,1,0,1); encoder.invoke(listener,4,40,-1,0,1);
        check(model.wheels.equals(List.of("20:1:1","20:0:1")), "roller encoder forwarding unchanged"); passed("roller encoder directions reach simulated OEM wheel sink");
        start("resolved"); click(4,55);
        check(model.keys.equals(List.of("10401:55","10402:55")) && model.toggles == 0 && model.selections == 0, "unrelated input"); passed("unrelated key forwarding");
        for (String mode : new String[]{"missing","mismatch"}) {
            start(mode); click(4,158);
            check(model.keys.equals(List.of("10401:158","10402:158")) && model.toggles == 0, "fallback OEM input");
            event(4,40,1); event(4,40,3); event(4,40,3); event(4,40,5);
            check(model.toggles == 1 && model.selections == 0, "fallback roller long once"); passed("constant lookup fallback: " + mode);
        }
        start("one-key"); click(4,158); click(4,159);
        check(model.toggles == 1 && model.keys.equals(List.of("10401:159","10402:159")), "one available symbolic key"); passed("only one named Joker key available");
        allCalls.addAll(model.calls); allStubs.addAll(model.loader.definedStubs);
        check(model.loader.definedReal.equals(Set.of(LISTENER,HANDLER)), "both packaged classes actually loaded");
        check(simulationErrors.isEmpty(), "no swallowed simulation errors: " + simulationErrors);
    }
    public static void main(String[] args) throws Exception {
        mutation = args.length > 1 ? args[1] : "none";
        originalBytes = new TreeMap<>();
        try (ZipFile jar = new ZipFile(args[0])) {
            for (String name : List.of(LISTENER,HANDLER)) originalBytes.put(name, jar.getInputStream(jar.getEntry(name+".class")).readAllBytes());
        }
        for (var entry : originalBytes.entrySet()) System.out.println("PACKAGED_CLASS_SHA256 " + entry.getKey() + " " + HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(entry.getValue())));
        suite();
        System.out.println("PASS_TOTAL scenarios=" + scenarios + " assertions=" + assertions);
        System.out.println("SIMULATED_CLASSES " + String.join(",",allStubs));
        for (String call : allCalls) System.out.println("SIMULATED_CALL " + call);
    }
}
