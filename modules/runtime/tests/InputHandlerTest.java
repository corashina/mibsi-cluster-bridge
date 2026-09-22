import com.mibsi.carplay.core.CarPlayApp;
import com.mibsi.carplay.core.CarPlaySteeringMenuInput;
import com.mibsi.carplay.core.Mhi2qAaClusterComposition;
import java.lang.reflect.Field;

public final class InputHandlerTest {
    private static int checks;
    private static void check(boolean value, String message) {
        if (!value) throw new AssertionError(message);
        checks++;
    }
    private static boolean event(int keyboard, int key, int state) {
        return CarPlaySteeringMenuInput.handleKeyState(keyboard, key, state);
    }
    private static void reset() throws Exception {
        Field[] fields = CarPlaySteeringMenuInput.class.getDeclaredFields();
        for (Field f : fields) {
            if (java.lang.reflect.Modifier.isFinal(f.getModifiers())) continue;
            f.setAccessible(true);
            if (f.getType() == boolean.class) f.setBoolean(null, false);
            else if (f.getType() == int.class) f.setInt(null, -1);
        }
        CarPlayApp.active = true;
        CarPlayApp.toggles = 0;
        CarPlayApp.selections = 0;
        Mhi2qAaClusterComposition.large = true;
    }
    public static void main(String[] args) throws Exception {
        boolean available = args.length == 0;
        if (!available) {
            reset();
            check(!event(4, 158, 1), "unresolved key must pass through");
            check(!event(4, 158, 0), "unresolved release must pass through");
            event(4, 40, 1); event(4, 40, 3); event(4, 40, 3); event(4, 40, 5);
            check(CarPlayApp.toggles == 1 && CarPlayApp.selections == 0, "original fallback shortcut");
        } else {
            for (int key : new int[]{158, 159}) {
                reset();
                check(event(4, key, 1), "input press consumed");
                check(CarPlayApp.toggles == 0, "toggle only on release");
                check(event(4, key, 1), "repeat consumed");
                check(event(4, key, 0), "input release consumed");
                check(CarPlayApp.toggles == 1, "exactly one toggle");
                check(!event(4, key, 0), "orphan release passes through");
                event(4, key, 1); event(4, key, 0);
                check(CarPlayApp.toggles == 2, "second short press toggles closed");
                for (int end : new int[]{0, 5}) {
                    reset();
                    event(4, key, 1);
                    check(event(4, key, 4) && event(4, key, 3) && event(4, key, 3), "long states consumed");
                    check(event(4, key, end), "long release consumed");
                    check(CarPlayApp.toggles == 0, "long press must not toggle");
                }
                reset();
                CarPlayApp.active = false;
                check(!event(4, key, 1) && !event(4, key, 0), "OEM when disconnected");
                check(CarPlayApp.toggles == 0, "no inactive toggle");
                CarPlayApp.active = true;
                Mhi2qAaClusterComposition.large = false;
                check(!event(4, key, 1) && !event(4, key, 0), "OEM in small view");
                reset();
                event(4, key, 1); CarPlayApp.active = false;
                check(event(4, key, 0) && CarPlayApp.toggles == 0, "disconnect during press drains release");
                reset();
                event(4, key, 1); Mhi2qAaClusterComposition.large = false;
                check(event(4, key, 0) && CarPlayApp.toggles == 0, "view change during press drains release");
                reset();
                event(4, key, 1); event(4, key, 2); event(4, key, 0);
                check(CarPlayApp.toggles == 0, "double event has no extra action");
                reset();
                check(!event(1, key, 1) && !event(1, key, 0), "other keyboards unaffected");
            }
            reset();
            for (int key = 0; key < 256; key++) {
                if (key == 158 || key == 159 || key == 40) continue;
                for (int state = 0; state < 8; state++) check(!event(4, key, state), "unrelated key consumed");
            }
            check(CarPlayApp.toggles == 0 && CarPlayApp.selections == 0, "unrelated keys have no action");
            event(4, 40, 1); event(4, 40, 0);
            check(CarPlayApp.selections == 1, "roller still selects");
            event(4, 40, 1); event(4, 40, 3); event(4, 40, 0);
            check(CarPlayApp.toggles == 0 && CarPlayApp.selections == 1, "roller hold no longer opens menu");
        }
        System.out.println("PASS: " + checks + " assertions (" + (available ? "resolved firmware constants" : "missing/mismatched constants fallback") + ")");
    }
}
