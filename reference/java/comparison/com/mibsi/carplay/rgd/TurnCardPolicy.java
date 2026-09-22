/*
 * Decompiled with CFR 0.152.
 */
package com.mibsi.carplay.rgd;

import com.mibsi.carplay.aa.ManeuverMapper;

public final class TurnCardPolicy {
    private static final int PREPARE_CITY_M = 1500;
    private static final int PREPARE_HIGHWAY_M = 3000;
    private static final int ACTION_PERCENT = 15;
    private static final int OPEN_CITY_SECONDS = 35;
    private static final int CLOSE_CITY_SECONDS = 45;
    private static final int OPEN_HIGHWAY_SECONDS = 45;
    private static final int CLOSE_HIGHWAY_SECONDS = 55;
    private static final int OPEN_CITY_M = 450;
    private static final int CLOSE_CITY_M = 560;
    private static final int OPEN_HIGHWAY_M = 1200;
    private static final int CLOSE_HIGHWAY_M = 1500;

    private TurnCardPolicy() {
    }

    public static int actionThreshold(int n) {
        int n2 = ManeuverMapper.isHighway(n) ? 3000 : 1500;
        return n2 * 15 / 100;
    }

    public static boolean shouldOpen(int n, int n2, int n3, boolean bl) {
        int n4;
        if (n3 == 0) {
            return false;
        }
        if (n3 >= 39 && n3 <= 42) {
            return true;
        }
        boolean bl2 = ManeuverMapper.isHighway(n3);
        int n5 = bl2 ? (bl ? 55 : 45) : (n4 = bl ? 45 : 35);
        int n6 = bl2 ? (bl ? 1500 : 1200) : (bl ? 560 : 450);
        return n >= 0 && n <= n4 || n2 >= 0 && n2 <= n6;
    }

    public static int bapManeuverState(boolean bl, boolean bl2) {
        if (!bl) {
            return 0;
        }
        return bl2 ? 4 : 2;
    }
}

