package com.mibsi.carplay.core;

/** Local runtime modification. Source intentionally uses Java 1.4 APIs. */
public final class CarPlaySteeringMenuInput {
    private static final int KEYBOARD_MFW = 4;
    private static final int KEY_MFW_ROLLER_LEFT = 40;
    private static boolean keysResolved;
    private static int joker1 = -1;
    private static int joker2 = -1;
    private static int capturedKey = -1;
    private static boolean shortcutLongPressed;
    private static boolean rollerPressed;
    private static boolean rollerLongPressed;
    private static boolean rollerLongHandled;

    private CarPlaySteeringMenuInput() {}

    private static void resolveKeys() {
        if (keysResolved) return;
        keysResolved = true;
        try {
            // Never use the numeric indices from a different firmware family.
            Class constants = Class.forName("org.dsi.ifc.keypanel.Constants");
            if (constants.getField("KBD_MFW").getInt(null) != KEYBOARD_MFW ||
                constants.getField("KEY_MFW_ROLLER_LEFT").getInt(null) != KEY_MFW_ROLLER_LEFT) {
                throw new IllegalStateException("incompatible key constants");
            }
            joker1 = readJoker(constants, "KEY_MFW_JOKER1");
            joker2 = readJoker(constants, "KEY_MFW_JOKER2");
            System.out.println(new StringBuffer("MIBSI input: firmware Joker keys=").append(joker1).append(',').append(joker2).toString());
        } catch (Throwable error) {
            joker1 = joker2 = -1;
            System.out.println("MIBSI input: key resolution unavailable; original roller shortcut retained");
        }
    }

    private static int readJoker(Class constants, String name) {
        try {
            int key = constants.getField(name).getInt(null);
            return key > 0 && key != KEY_MFW_ROLLER_LEFT ? key : -1;
        } catch (Throwable error) {
            return -1;
        }
    }

    private static boolean canUseMenu() {
        return CarPlayApp.isActive() && Mhi2qAaClusterComposition.isLargeView();
    }

    /** Compatibility entry point retained for any existing callers. */
    public static synchronized void onKeyState(int keyboard, int key, int state) {
        handleKeyState(keyboard, key, state);
    }

    /** True means the event belongs to the custom menu and must not reach OEM. */
    public static synchronized boolean handleKeyState(int keyboard, int key, int state) {
        if (keyboard != KEYBOARD_MFW) return false;
        try {
            resolveKeys();
            if (key > 0 && (key == joker1 || key == joker2)) {
                if (state == 1) {
                    if (capturedKey == key) return true; // repeated press
                    if (capturedKey != -1 || !canUseMenu()) return false;
                    capturedKey = key;
                    shortcutLongPressed = false;
                    return true;
                }
                if (capturedKey != key) return false;
                if (state == 3 || state == 4) {
                    shortcutLongPressed = true;
                    return true;
                }
                if (state == 0 || state == 5) {
                    boolean activate = state == 0 && !shortcutLongPressed && canUseMenu();
                    capturedKey = -1;
                    shortcutLongPressed = false;
                    if (activate) CarPlayApp.toggleVcSettingsMenu();
                    return true;
                }
                if (state == 2) shortcutLongPressed = true; // no extra double-press action
                return true;
            }
            if (key == KEY_MFW_ROLLER_LEFT) {
                if (state == 1) {
                    if (!rollerPressed) {
                        rollerLongPressed = false;
                        rollerLongHandled = false;
                    }
                    rollerPressed = true;
                } else if (state == 3) {
                    boolean firstLong = !rollerLongHandled;
                    rollerPressed = true;
                    rollerLongPressed = true;
                    rollerLongHandled = true;
                    // Recovery path only if the firmware's key names are unavailable.
                    if (firstLong && joker1 < 0 && joker2 < 0) CarPlayApp.toggleVcSettingsMenu();
                } else if (state == 4) {
                    rollerLongPressed = true;
                } else if (state == 0 || state == 5) {
                    boolean select = rollerPressed && !rollerLongPressed && state == 0;
                    rollerPressed = false;
                    rollerLongPressed = false;
                    rollerLongHandled = false;
                    if (select) CarPlayApp.selectVcSettingsItem();
                }
            }
        } catch (Throwable error) {
            capturedKey = -1;
            shortcutLongPressed = false;
        }
        return false;
    }
}
