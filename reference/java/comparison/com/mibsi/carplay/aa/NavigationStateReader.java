/*
 * Decompiled with CFR 0.152.
 */
package com.mibsi.carplay.aa;

import com.mibsi.carplay.aa.NavigationSnapshot;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public final class NavigationStateReader {
    private static final String AA_STATE_FILE = "/tmp/mhi2q_aa_nav_proxy.state";
    private static final String CARPLAY_STATE_FILE = "/tmp/mhi2q_carplay_nav_proxy.state";
    private static final String MODE_FILE = "/tmp/mhi2q_aa_nav_proxy.mode";
    private static final String TEST_STATE_FILE = "/tmp/mhi2q_aa_turn_card_test.state";
    private static final String TEST_STATE_VERSION_V1 = "MHI2Q_AA_TURN_TEST_V1";
    private static final String TEST_STATE_VERSION_V2 = "MHI2Q_AA_TURN_TEST_V2";
    private static final long POLL_MS = 250L;
    private static final long TTL_MS = 3000L;
    private static volatile String projection = "stock";
    private final NavigationSnapshot snapshot = new NavigationSnapshot();
    private long nextPoll;
    private boolean testOverrideActive;
    private String appliedProjection = "stock";

    public NavigationSnapshot refresh() {
        return this.refresh(false);
    }

    public NavigationSnapshot refresh(boolean bl) {
        String string;
        long l = System.currentTimeMillis();
        if (l < this.nextPoll) {
            return this.snapshot;
        }
        this.nextPoll = l + 250L;
        if (bl && this.readTestState(l)) {
            this.testOverrideActive = true;
            return this.snapshot;
        }
        if (this.testOverrideActive) {
            this.snapshot.sequence = -1;
            this.snapshot.updated = 0L;
            this.snapshot.active = false;
            this.snapshot.maneuver = 0;
            this.snapshot.laneGuidance = new byte[0];
            this.testOverrideActive = false;
        }
        if (!(string = projection).equals(this.appliedProjection)) {
            this.clearSnapshot(string);
            this.appliedProjection = string;
        }
        this.snapshot.mode = "carplay".equals(string) ? "carplay" : this.readMode();
        this.readState("carplay".equals(string) ? CARPLAY_STATE_FILE : AA_STATE_FILE);
        return this.snapshot;
    }

    public static void setProjection(String string) {
        projection = "carplay".equals(string) ? "carplay" : ("aa".equals(string) ? "aa" : "stock");
    }

    public static boolean isFresh(String string, boolean bl, long l, long l2) {
        long l3 = l2 - l;
        return ("aa".equals(string) || "carplay".equals(string)) && bl && l > 0L && l3 >= 0L && l3 <= 3000L;
    }

    public void resetForTest(String string) {
        this.snapshot.mode = string;
        this.snapshot.sequence = -1;
        this.snapshot.updated = 0L;
        this.snapshot.active = false;
        this.snapshot.destinationDistanceMeters = -1;
        this.snapshot.timeToDestinationSeconds = -1;
        this.nextPoll = 0L;
        this.testOverrideActive = false;
    }

    private void clearSnapshot(String string) {
        this.snapshot.mode = string;
        this.snapshot.sequence = -1;
        this.snapshot.updated = 0L;
        this.snapshot.active = false;
        this.snapshot.maneuver = 0;
        this.snapshot.roundaboutExit = -1;
        this.snapshot.roundaboutAngle = -1;
        this.snapshot.distanceMeters = -1;
        this.snapshot.timeToStepSeconds = -1;
        this.snapshot.destinationDistanceMeters = -1;
        this.snapshot.timeToDestinationSeconds = -1;
        this.snapshot.currentRoad = "";
        this.snapshot.nextRoad = "";
        this.snapshot.instruction = "";
        this.snapshot.laneGuidance = new byte[0];
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean readTestState(long l) {
        boolean bl;
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(TEST_STATE_FILE));
            String string = bufferedReader.readLine();
            bl = NavigationStateReader.parseTestState(string, this.snapshot, l);
            if (bufferedReader == null) return bl;
        }
        catch (Throwable throwable) {
            try {
                boolean bl2 = false;
                return bl2;
            }
            catch (Throwable throwable2) {
                throw throwable2;
            }
            finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    }
                    catch (Throwable throwable3) {}
                }
            }
        }
        try {
            bufferedReader.close();
            return bl;
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return bl;
    }

    private static boolean parseTestState(String string, NavigationSnapshot navigationSnapshot, long l) {
        if (string == null || navigationSnapshot == null) {
            return false;
        }
        try {
            byte[] byArray;
            StringTokenizer stringTokenizer = new StringTokenizer(string, "\t");
            int n = stringTokenizer.countTokens();
            String string2 = stringTokenizer.nextToken();
            boolean bl = TEST_STATE_VERSION_V1.equals(string2);
            boolean bl2 = TEST_STATE_VERSION_V2.equals(string2);
            if (!(bl && n == 9 || bl2 && n == 10)) {
                return false;
            }
            int n2 = Integer.parseInt(stringTokenizer.nextToken());
            int n3 = Integer.parseInt(stringTokenizer.nextToken());
            int n4 = Integer.parseInt(stringTokenizer.nextToken());
            int n5 = Integer.parseInt(stringTokenizer.nextToken());
            int n6 = Integer.parseInt(stringTokenizer.nextToken());
            int n7 = Integer.parseInt(stringTokenizer.nextToken());
            int n8 = Integer.parseInt(stringTokenizer.nextToken());
            int n9 = Integer.parseInt(stringTokenizer.nextToken());
            byte[] byArray2 = byArray = bl2 ? NavigationStateReader.decodeLaneGuidance(stringTokenizer.nextToken()) : new byte[]{};
            if (n2 <= 0 || n3 < 0 || n3 > 50) {
                return false;
            }
            navigationSnapshot.mode = "aa";
            navigationSnapshot.sequence = n2;
            navigationSnapshot.updated = l;
            navigationSnapshot.active = true;
            navigationSnapshot.maneuver = n3;
            navigationSnapshot.roundaboutExit = n4;
            navigationSnapshot.roundaboutAngle = n5;
            navigationSnapshot.distanceMeters = n6;
            navigationSnapshot.timeToStepSeconds = n7;
            navigationSnapshot.destinationDistanceMeters = n8;
            navigationSnapshot.timeToDestinationSeconds = n9;
            navigationSnapshot.currentRoad = "TEST";
            navigationSnapshot.nextRoad = "";
            navigationSnapshot.instruction = "";
            navigationSnapshot.laneGuidance = byArray;
            return true;
        }
        catch (Throwable throwable) {
            return false;
        }
    }

    static NavigationSnapshot parseTestStateForTest(String string, long l) {
        NavigationSnapshot navigationSnapshot = new NavigationSnapshot();
        return NavigationStateReader.parseTestState(string, navigationSnapshot, l) ? navigationSnapshot : null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private String readMode() {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(MODE_FILE));
            String string = bufferedReader.readLine();
            if (string == null) {
                String string2 = "aa";
                return string2;
            }
            if ("aa".equals(string = string.trim()) || "stock".equals(string) || "shadow".equals(string)) {
                String string3 = string;
                return string3;
            }
        }
        catch (Throwable throwable) {
        }
        finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                }
                catch (Throwable throwable) {}
            }
        }
        return "aa";
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void readState(String string) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(string));
            String string2 = bufferedReader.readLine();
            if (string2 != null) {
                NavigationStateReader.parseStateLine(string2, this.snapshot);
            }
        }
        catch (Throwable throwable) {
        }
        finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                }
                catch (Throwable throwable) {}
            }
        }
    }

    private static boolean parseStateLine(String string, NavigationSnapshot navigationSnapshot) {
        if (string == null || navigationSnapshot == null) {
            return false;
        }
        try {
            StringTokenizer stringTokenizer = new StringTokenizer(string, "\t");
            int n = stringTokenizer.countTokens();
            String string2 = stringTokenizer.nextToken();
            boolean bl = "MHI2Q_AA_NAV_V1".equals(string2);
            boolean bl2 = "MHI2Q_AA_NAV_V2".equals(string2);
            boolean bl3 = "MHI2Q_AA_NAV_V3".equals(string2);
            if (!(bl && n == 13 || bl2 && n == 15 || bl3 && n == 16)) {
                return false;
            }
            int n2 = Integer.parseInt(stringTokenizer.nextToken());
            long l = Long.parseLong(stringTokenizer.nextToken());
            boolean bl4 = Integer.parseInt(stringTokenizer.nextToken()) != 0;
            stringTokenizer.nextToken();
            int n3 = Integer.parseInt(stringTokenizer.nextToken());
            int n4 = Integer.parseInt(stringTokenizer.nextToken());
            int n5 = Integer.parseInt(stringTokenizer.nextToken());
            int n6 = Integer.parseInt(stringTokenizer.nextToken());
            int n7 = Integer.parseInt(stringTokenizer.nextToken());
            int n8 = bl2 || bl3 ? Integer.parseInt(stringTokenizer.nextToken()) : -1;
            int n9 = bl2 || bl3 ? Integer.parseInt(stringTokenizer.nextToken()) : -1;
            String string3 = NavigationStateReader.decodeHex(stringTokenizer.nextToken());
            String string4 = NavigationStateReader.decodeHex(stringTokenizer.nextToken());
            String string5 = NavigationStateReader.decodeHex(stringTokenizer.nextToken());
            byte[] byArray = bl3 ? NavigationStateReader.decodeLaneGuidance(stringTokenizer.nextToken()) : new byte[]{};
            navigationSnapshot.sequence = n2;
            navigationSnapshot.updated = l;
            navigationSnapshot.active = bl4;
            navigationSnapshot.maneuver = n3;
            navigationSnapshot.roundaboutExit = n4;
            navigationSnapshot.roundaboutAngle = n5;
            navigationSnapshot.distanceMeters = n6;
            navigationSnapshot.timeToStepSeconds = n7;
            navigationSnapshot.destinationDistanceMeters = n8;
            navigationSnapshot.timeToDestinationSeconds = n9;
            navigationSnapshot.currentRoad = string3;
            navigationSnapshot.nextRoad = string4;
            navigationSnapshot.instruction = string5;
            navigationSnapshot.laneGuidance = byArray;
            return true;
        }
        catch (Throwable throwable) {
            return false;
        }
    }

    static NavigationSnapshot parseStateForTest(String string) {
        NavigationSnapshot navigationSnapshot = new NavigationSnapshot();
        return NavigationStateReader.parseStateLine(string, navigationSnapshot) ? navigationSnapshot : null;
    }

    private static byte[] decodeLaneGuidance(String string) throws Exception {
        if ("-".equals(string)) {
            return new byte[0];
        }
        if ((string.length() & 1) != 0 || string.length() > 66) {
            throw new IllegalArgumentException("invalid lane hex");
        }
        byte[] byArray = NavigationStateReader.decodeHexBytes(string);
        if (byArray.length == 0) {
            return byArray;
        }
        int n = byArray[0] & 0xFF;
        if (n == 0 || n > 8) {
            throw new IllegalArgumentException("invalid lane count");
        }
        int n2 = 1;
        for (int i = 0; i < n; ++i) {
            int n3;
            if (n2 >= byArray.length) {
                throw new IllegalArgumentException("missing lane");
            }
            if ((n3 = byArray[n2++] & 0xFF) == 0 || n3 > 3 || n2 + n3 > byArray.length) {
                throw new IllegalArgumentException("invalid lane directions");
            }
            int n4 = 0;
            for (int j = 0; j < n3; ++j) {
                int n5;
                if (((n5 = byArray[n2++] & 0xFF) & 0x70) != 0 || (n5 & 0xF) > 9) {
                    throw new IllegalArgumentException("invalid lane shape");
                }
                if ((n5 & 0x80) == 0 || ++n4 <= 1) continue;
                throw new IllegalArgumentException("multiple selected directions");
            }
        }
        if (n2 != byArray.length) {
            throw new IllegalArgumentException("trailing lane bytes");
        }
        return byArray;
    }

    private static String decodeHex(String string) throws Exception {
        if ("-".equals(string)) {
            return "";
        }
        if ((string.length() & 1) != 0 || string.length() > 768) {
            throw new IllegalArgumentException("invalid hex text");
        }
        return new String(NavigationStateReader.decodeHexBytes(string), "UTF-8");
    }

    private static byte[] decodeHexBytes(String string) {
        byte[] byArray = new byte[string.length() / 2];
        for (int i = 0; i < byArray.length; ++i) {
            int n = Character.digit(string.charAt(i * 2), 16);
            int n2 = Character.digit(string.charAt(i * 2 + 1), 16);
            if (n < 0 || n2 < 0) {
                throw new IllegalArgumentException("invalid hex digit");
            }
            byArray[i] = (byte)(n << 4 | n2);
        }
        return byArray;
    }
}

