import com.mibsi.carplay.aa.ManeuverMapper;
import com.mibsi.carplay.rgd.TurnCardPolicy;
import java.util.Arrays;

public final class NavigationCoreTest {
    private static int checks;
    private static void check(boolean value, String message) {
        if (!value) throw new AssertionError(message);
        checks++;
    }
    public static void main(String[] args) {
        check(!ManeuverMapper.isHighway(12) && ManeuverMapper.isHighway(13), "highway lower boundary");
        check(ManeuverMapper.isHighway(29) && !ManeuverMapper.isHighway(30), "highway upper boundary");
        check(!TurnCardPolicy.shouldOpen(0, 0, 0, false), "no maneuver hides card");
        for (int maneuver = 39; maneuver <= 42; maneuver++)
            check(TurnCardPolicy.shouldOpen(-1, -1, maneuver, false), "arrival always visible");
        check(TurnCardPolicy.actionThreshold(7) == 225, "city action distance");
        check(TurnCardPolicy.actionThreshold(13) == 450, "highway action distance");
        check(TurnCardPolicy.shouldOpen(35, -1, 7, false), "city opens at time threshold");
        check(!TurnCardPolicy.shouldOpen(36, -1, 7, false), "city stays closed beyond threshold");
        check(TurnCardPolicy.shouldOpen(45, -1, 7, true), "open city card has hysteresis");
        check(!TurnCardPolicy.shouldOpen(46, -1, 7, true), "city card closes beyond hysteresis");
        check(TurnCardPolicy.shouldOpen(-1, 450, 7, false), "city distance opens card");
        check(!TurnCardPolicy.shouldOpen(-1, 451, 7, false), "city distance boundary");
        check(TurnCardPolicy.shouldOpen(45, -1, 13, false), "highway opens at 45 seconds");
        check(!TurnCardPolicy.shouldOpen(46, 1201, 13, false), "highway outside both limits");
        check(TurnCardPolicy.shouldOpen(55, 1500, 13, true), "highway open-card limits");
        check(!TurnCardPolicy.shouldOpen(56, 1501, 13, true), "highway closes beyond limits");
        check(!TurnCardPolicy.shouldOpen(-1, -1, 7, false), "unavailable distance and time");
        check(Arrays.equals(ManeuverMapper.rendererManeuver(7, 0, 0), new int[]{2,0,-90,0,0}), "left turn");
        check(Arrays.equals(ManeuverMapper.rendererManeuver(8, 0, 0), new int[]{2,0,90,0,0}), "right turn");
        check(Arrays.equals(ManeuverMapper.bapDescriptor(0, 0), new int[]{0,0}), "empty descriptor");
        check(ManeuverMapper.rendererManeuver(33, 0, 0)[4] == 0, "invalid roundabout angle");
        check(ManeuverMapper.rendererManeuver(33, 360, 0)[4] == 1, "valid roundabout angle");
        check(TurnCardPolicy.bapManeuverState(false, true) == 0, "inactive maneuver");
        check(TurnCardPolicy.bapManeuverState(true, false) == 2, "preparation state");
        check(TurnCardPolicy.bapManeuverState(true, true) == 4, "action state");
        System.out.println("PASS: " + checks + " navigation boundary assertions");
    }
}
