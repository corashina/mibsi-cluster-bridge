// Differential check of two pure computation classes; no vehicle APIs used.
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Random;

public class JavaCompatibilityTest {
    static long checks;
    static void compare(Method original, Method candidate, Object... args) throws Exception {
        Object expected = original.invoke(null, args);
        Object actual = candidate.invoke(null, args);
        if (!Objects.deepEquals(expected, actual)) {
            throw new AssertionError(original.getName() + " differs for " + java.util.Arrays.toString(args));
        }
        checks++;
    }
    public static void main(String[] args) throws Exception {
        try (URLClassLoader original = new URLClassLoader(new URL[]{Path.of(args[0]).toUri().toURL()}, null);
             URLClassLoader candidate = new URLClassLoader(new URL[]{Path.of(args[1]).toUri().toURL()}, null)) {
            String mapper = "com.mibsi.carplay.aa.ManeuverMapper";
            String policy = "com.mibsi.carplay.rgd.TurnCardPolicy";
            Class<?> om = original.loadClass(args[2] + ".aa.ManeuverMapper"), rm = candidate.loadClass(mapper);
            Class<?> op = original.loadClass(args[2] + ".rgd.TurnCardPolicy"), rp = candidate.loadClass(policy);
            Method oh = om.getMethod("isHighway", int.class), rh = rm.getMethod("isHighway", int.class);
            Method od = om.getMethod("bapDescriptor", int.class, int.class), rd = rm.getMethod("bapDescriptor", int.class, int.class);
            Method or = om.getMethod("rendererManeuver", int.class, int.class, int.class), rr = rm.getMethod("rendererManeuver", int.class, int.class, int.class);
            Method oa = op.getMethod("actionThreshold", int.class), ra = rp.getMethod("actionThreshold", int.class);
            Method oo = op.getMethod("shouldOpen", int.class, int.class, int.class, boolean.class);
            Method ro = rp.getMethod("shouldOpen", int.class, int.class, int.class, boolean.class);
            Method ob = op.getMethod("bapManeuverState", boolean.class, boolean.class);
            Method rb = rp.getMethod("bapManeuverState", boolean.class, boolean.class);
            int[] bounds = {Integer.MIN_VALUE, -1, 0, 1, 34, 35, 36, 44, 45, 46, 54, 55, 56,
                            449, 450, 451, 559, 560, 561, 1199, 1200, 1201, 1499, 1500, 1501, Integer.MAX_VALUE};
            for (int maneuver = -5; maneuver <= 60; maneuver++) {
                compare(oh, rh, maneuver);
                compare(oa, ra, maneuver);
                for (int angle = -1; angle <= 361; angle++) {
                    compare(od, rd, maneuver, angle);
                    compare(or, rr, maneuver, angle, 0);
                }
                for (int seconds : bounds) for (int meters : bounds) for (boolean open : new boolean[]{false, true}) {
                    compare(oo, ro, seconds, meters, maneuver, open);
                }
            }
            Random random = new Random(5092);
            for (int i = 0; i < 10000; i++) {
                int maneuver = random.nextInt(), angle = random.nextInt();
                compare(oh, rh, maneuver);
                compare(oa, ra, maneuver);
                compare(od, rd, maneuver, angle);
                compare(or, rr, maneuver, angle, random.nextInt());
                compare(oo, ro, random.nextInt(), random.nextInt(), maneuver, random.nextBoolean());
            }
            for (boolean active : new boolean[]{false, true}) for (boolean action : new boolean[]{false, true}) compare(ob, rb, active, action);
            System.out.println("PASS: " + checks + " baseline/project result comparisons across ManeuverMapper and TurnCardPolicy.");
            System.out.println("Scope: two pure computation classes only; not a whole-program or hardware verification.");
        }
    }
}
