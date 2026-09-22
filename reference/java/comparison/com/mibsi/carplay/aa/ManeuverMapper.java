/*
 * Decompiled with CFR 0.152.
 */
package com.mibsi.carplay.aa;

public final class ManeuverMapper {
    private static final int ICON_NONE = 0;
    private static final int ICON_APPROACH = 1;
    private static final int ICON_TURN = 2;
    private static final int ICON_UTURN = 3;
    private static final int ICON_MERGE = 4;
    private static final int ICON_EXIT = 5;
    private static final int ICON_ROUNDABOUT = 6;
    private static final int ICON_ARRIVED = 7;
    private static final int ICON_KEEP = 8;
    private static final int ICON_FORK = 9;

    private ManeuverMapper() {
    }

    public static boolean isHighway(int n) {
        return n >= 13 && n <= 29;
    }

    public static int[] bapDescriptor(int n, int n2) {
        int n3 = 11;
        int n4 = ManeuverMapper.direction(n);
        if (n == 0) {
            n3 = 0;
        } else if (n == 1) {
            n3 = 11;
        } else if (n == 2 || n == 36) {
            n3 = 11;
        } else if (n == 3 || n == 4 || n >= 27 && n <= 29) {
            n3 = 12;
        } else if (n >= 5 && n <= 10) {
            n3 = 13;
        } else if (n == 11 || n == 12) {
            n3 = 25;
        } else if (n >= 13 && n <= 20) {
            n3 = ManeuverMapper.isLeft(n) ? 18 : 17;
        } else if (n >= 21 && n <= 24) {
            n3 = ManeuverMapper.isLeft(n) ? 16 : 15;
        } else if (n == 25 || n == 26) {
            n3 = 19;
        } else if (n >= 32 && n <= 35) {
            n3 = 21;
        } else if (n >= 39 && n <= 42) {
            n3 = 3;
        }
        if ((n == 33 || n == 35) && n2 >= 1 && n2 <= 360) {
            int n5 = n == 33 ? n2 - 180 : 180 - n2;
            n4 = -n5 * 256 / 360 & 0xFF;
        }
        return new int[]{n3, n4};
    }

    public static int[] rendererManeuver(int n, int n2, int n3) {
        int n4 = 1;
        int n5 = ManeuverMapper.isLeft(n) ? -1 : 1;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        if (n == 0) {
            n4 = 0;
            n5 = 0;
        } else if (n == 1 || n == 2 || n == 30 || n == 31 || n == 36 || n == 37 || n == 38 || n == 43 || n == 45 || n == 47 || n == 48 || n == 49 || n == 50) {
            n4 = 1;
            n5 = 0;
        } else if (n == 3 || n == 4) {
            n4 = 8;
        } else if (n >= 5 && n <= 10) {
            n4 = 2;
            n6 = n <= 6 ? n5 * 45 : (n <= 8 ? n5 * 90 : n5 * 135);
            n5 = 0;
        } else if (n == 11 || n == 12) {
            n4 = 3;
            n6 = n5 * 180;
            n7 = n5 > 0 ? 1 : 0;
        } else if (n >= 13 && n <= 18 || n == 27 || n == 28) {
            n4 = 4;
        } else if (n == 19 || n == 20) {
            n4 = 3;
            n6 = n5 * 180;
            n7 = n5 > 0 ? 1 : 0;
        } else if (n >= 21 && n <= 24) {
            n4 = 5;
        } else if (n == 25 || n == 26) {
            n4 = 9;
        } else if (n == 29) {
            n4 = 1;
            n5 = 0;
        } else if (n >= 32 && n <= 35) {
            n4 = 6;
            n5 = 0;
            boolean bl = n == 32 || n == 33;
            int n9 = n7 = bl ? 1 : 0;
            if ((n == 33 || n == 35) && n2 >= 1 && n2 <= 360) {
                n6 = bl ? n2 - 180 : 180 - n2;
                n8 = 1;
            }
        } else if (n >= 39 && n <= 42) {
            n4 = 7;
            if (n == 39 || n == 40) {
                n5 = 0;
            }
        } else if (n == 44) {
            n4 = 5;
            n5 = -1;
        } else if (n == 46) {
            n4 = 5;
            n5 = 1;
        }
        return new int[]{n4, n5, n6, n7, n8};
    }

    private static boolean isLeft(int n) {
        return n == 3 || n == 5 || n == 7 || n == 9 || n == 11 || n == 13 || n == 15 || n == 17 || n == 19 || n == 21 || n == 23 || n == 25 || n == 27 || n == 34 || n == 41 || n == 45 || n == 47;
    }

    private static int direction(int n) {
        switch (n) {
            case 3: 
            case 5: {
                return 32;
            }
            case 7: 
            case 13: 
            case 15: 
            case 17: 
            case 19: 
            case 21: 
            case 23: 
            case 25: 
            case 27: 
            case 41: 
            case 45: 
            case 47: {
                return 64;
            }
            case 9: {
                return 96;
            }
            case 11: 
            case 12: {
                return 128;
            }
            case 10: {
                return 160;
            }
            case 8: 
            case 14: 
            case 16: 
            case 18: 
            case 20: 
            case 22: 
            case 24: 
            case 26: 
            case 28: 
            case 42: 
            case 44: 
            case 46: 
            case 48: 
            case 50: {
                return 192;
            }
            case 4: 
            case 6: {
                return 224;
            }
        }
        return 0;
    }
}

