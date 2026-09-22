/*
 * Decompiled with CFR 0.152.
 */
package com.mibsi.carplay.core;

import com.mibsi.carplay.core.CarPlayApp;

public final class CarPlaySteeringMenuInput {
    private static final int KEYBOARD_MFW = 4;
    private static final int KEY_MFW_ROLLER_LEFT = 40;
    private static final int KEY_STATE_RELEASED = 0;
    private static final int KEY_STATE_PRESSED = 1;
    private static final int KEY_STATE_LONG_PRESSED = 3;
    private static final int KEY_STATE_LONG_STARTED = 4;
    private static final int KEY_STATE_LONG_RELEASED = 5;
    private static boolean rollerPressed;
    private static boolean rollerLongPressed;
    private static boolean rollerLongHandled;

    private CarPlaySteeringMenuInput() {
    }

    public static synchronized void onKeyState(int n, int n2, int n3) {
        if (n != 4 || n2 != 40) {
            return;
        }
        try {
            if (n3 == 1) {
                if (!rollerPressed) {
                    rollerLongPressed = false;
                    rollerLongHandled = false;
                }
                rollerPressed = true;
            } else if (n3 == 3) {
                boolean bl = !rollerLongHandled;
                rollerPressed = true;
                rollerLongPressed = true;
                rollerLongHandled = true;
                if (bl) {
                    CarPlayApp.toggleVcSettingsMenu();
                }
            } else if (n3 == 4) {
                rollerLongPressed = true;
            } else if (n3 == 0 || n3 == 5) {
                boolean bl = rollerPressed && !rollerLongPressed && n3 == 0;
                rollerPressed = false;
                rollerLongPressed = false;
                rollerLongHandled = false;
                if (bl) {
                    CarPlayApp.selectVcSettingsItem();
                }
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }
}

