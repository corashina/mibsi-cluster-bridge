package com.mibsi.carplay.core;
public final class CarPlayApp {
    public static boolean active = true;
    public static int toggles;
    public static int selections;
    public static boolean isActive() { return active; }
    public static void toggleVcSettingsMenu() { toggles++; }
    public static boolean selectVcSettingsItem() { selections++; return true; }
}
