package com.mibsi.carplay.aa;

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

   public NavigationSnapshot refresh(boolean var1) {
      long var2 = System.currentTimeMillis();
      if (var2 < this.nextPoll) {
         return this.snapshot;
      }

      this.nextPoll = var2 + 250L;
      if (var1 && this.readTestState(var2)) {
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

      String var4 = projection;
      if (!var4.equals(this.appliedProjection)) {
         this.clearSnapshot(var4);
         this.appliedProjection = var4;
      }

      this.snapshot.mode = "carplay".equals(var4) ? "carplay" : this.readMode();
      this.readState("carplay".equals(var4) ? "/tmp/mhi2q_carplay_nav_proxy.state" : "/tmp/mhi2q_aa_nav_proxy.state");
      return this.snapshot;
   }

   public static void setProjection(String var0) {
      if ("carplay".equals(var0)) {
         projection = "carplay";
      } else if ("aa".equals(var0)) {
         projection = "aa";
      } else {
         projection = "stock";
      }
   }

   public static boolean isFresh(String var0, boolean var1, long var2, long var4) {
      long var6 = var4 - var2;
      return ("aa".equals(var0) || "carplay".equals(var0)) && var1 && var2 > 0L && var6 >= 0L && var6 <= 3000L;
   }

   public void resetForTest(String var1) {
      this.snapshot.mode = var1;
      this.snapshot.sequence = -1;
      this.snapshot.updated = 0L;
      this.snapshot.active = false;
      this.snapshot.destinationDistanceMeters = -1;
      this.snapshot.timeToDestinationSeconds = -1;
      this.nextPoll = 0L;
      this.testOverrideActive = false;
   }

   private void clearSnapshot(String var1) {
      this.snapshot.mode = var1;
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

   private boolean readTestState(long var1) {
      BufferedReader var3 = null;

      try {
         var3 = new BufferedReader(new FileReader("/tmp/mhi2q_aa_turn_card_test.state"));
         String var4 = var3.readLine();
         return parseTestState(var4, this.snapshot, var1);
      } catch (Throwable var15) {
         return false;
      } finally {
         if (var3 != null) {
            try {
               var3.close();
            } catch (Throwable var14) {
            }
         }
      }
   }

   private static boolean parseTestState(String var0, NavigationSnapshot var1, long var2) {
      if (var0 != null && var1 != null) {
         try {
            StringTokenizer var4 = new StringTokenizer(var0, "\t");
            int var5 = var4.countTokens();
            String var6 = var4.nextToken();
            boolean var7 = "MHI2Q_AA_TURN_TEST_V1".equals(var6);
            boolean var8 = "MHI2Q_AA_TURN_TEST_V2".equals(var6);
            if (var7 && var5 == 9 || var8 && var5 == 10) {
               int var9 = Integer.parseInt(var4.nextToken());
               int var10 = Integer.parseInt(var4.nextToken());
               int var11 = Integer.parseInt(var4.nextToken());
               int var12 = Integer.parseInt(var4.nextToken());
               int var13 = Integer.parseInt(var4.nextToken());
               int var14 = Integer.parseInt(var4.nextToken());
               int var15 = Integer.parseInt(var4.nextToken());
               int var16 = Integer.parseInt(var4.nextToken());
               byte[] var17 = var8 ? decodeLaneGuidance(var4.nextToken()) : new byte[0];
               if (var9 > 0 && var10 >= 0 && var10 <= 50) {
                  var1.mode = "aa";
                  var1.sequence = var9;
                  var1.updated = var2;
                  var1.active = true;
                  var1.maneuver = var10;
                  var1.roundaboutExit = var11;
                  var1.roundaboutAngle = var12;
                  var1.distanceMeters = var13;
                  var1.timeToStepSeconds = var14;
                  var1.destinationDistanceMeters = var15;
                  var1.timeToDestinationSeconds = var16;
                  var1.currentRoad = "TEST";
                  var1.nextRoad = "";
                  var1.instruction = "";
                  var1.laneGuidance = var17;
                  return true;
               } else {
                  return false;
               }
            } else {
               return false;
            }
         } catch (Throwable var18) {
            return false;
         }
      } else {
         return false;
      }
   }

   static NavigationSnapshot parseTestStateForTest(String var0, long var1) {
      NavigationSnapshot var3 = new NavigationSnapshot();
      return parseTestState(var0, var3, var1) ? var3 : null;
   }

   private String readMode() {
      BufferedReader var1 = null;

      try {
         var1 = new BufferedReader(new FileReader("/tmp/mhi2q_aa_nav_proxy.mode"));
         String var2 = var1.readLine();
         if (var2 == null) {
            return "aa";
         }

         var2 = var2.trim();
         if ("aa".equals(var2) || "stock".equals(var2) || "shadow".equals(var2)) {
            return var2;
         }
      } catch (Throwable var15) {
      } finally {
         if (var1 != null) {
            try {
               var1.close();
            } catch (Throwable var14) {
            }
         }
      }

      return "aa";
   }

   private void readState(String var1) {
      BufferedReader var2 = null;

      try {
         var2 = new BufferedReader(new FileReader(var1));
         String var3 = var2.readLine();
         if (var3 != null) {
            parseStateLine(var3, this.snapshot);
         }
      } catch (Throwable var12) {
      } finally {
         if (var2 != null) {
            try {
               var2.close();
            } catch (Throwable var11) {
            }
         }
      }
   }

   private static boolean parseStateLine(String var0, NavigationSnapshot var1) {
      if (var0 != null && var1 != null) {
         try {
            StringTokenizer var2 = new StringTokenizer(var0, "\t");
            int var3 = var2.countTokens();
            String var4 = var2.nextToken();
            boolean var5 = "MHI2Q_AA_NAV_V1".equals(var4);
            boolean var6 = "MHI2Q_AA_NAV_V2".equals(var4);
            boolean var7 = "MHI2Q_AA_NAV_V3".equals(var4);
            if (var5 && var3 == 13 || var6 && var3 == 15 || var7 && var3 == 16) {
               int var8 = Integer.parseInt(var2.nextToken());
               long var9 = Long.parseLong(var2.nextToken());
               boolean var11 = Integer.parseInt(var2.nextToken()) != 0;
               var2.nextToken();
               int var12 = Integer.parseInt(var2.nextToken());
               int var13 = Integer.parseInt(var2.nextToken());
               int var14 = Integer.parseInt(var2.nextToken());
               int var15 = Integer.parseInt(var2.nextToken());
               int var16 = Integer.parseInt(var2.nextToken());
               int var17 = !var6 && !var7 ? -1 : Integer.parseInt(var2.nextToken());
               int var18 = !var6 && !var7 ? -1 : Integer.parseInt(var2.nextToken());
               String var19 = decodeHex(var2.nextToken());
               String var20 = decodeHex(var2.nextToken());
               String var21 = decodeHex(var2.nextToken());
               byte[] var22 = var7 ? decodeLaneGuidance(var2.nextToken()) : new byte[0];
               var1.sequence = var8;
               var1.updated = var9;
               var1.active = var11;
               var1.maneuver = var12;
               var1.roundaboutExit = var13;
               var1.roundaboutAngle = var14;
               var1.distanceMeters = var15;
               var1.timeToStepSeconds = var16;
               var1.destinationDistanceMeters = var17;
               var1.timeToDestinationSeconds = var18;
               var1.currentRoad = var19;
               var1.nextRoad = var20;
               var1.instruction = var21;
               var1.laneGuidance = var22;
               return true;
            } else {
               return false;
            }
         } catch (Throwable var23) {
            return false;
         }
      } else {
         return false;
      }
   }

   static NavigationSnapshot parseStateForTest(String var0) {
      NavigationSnapshot var1 = new NavigationSnapshot();
      return parseStateLine(var0, var1) ? var1 : null;
   }

   private static byte[] decodeLaneGuidance(String var0) throws Exception {
      if ("-".equals(var0)) {
         return new byte[0];
      }

      if ((var0.length() & 1) == 0 && var0.length() <= 66) {
         byte[] var1 = decodeHexBytes(var0);
         if (var1.length == 0) {
            return var1;
         }

         int var2 = var1[0] & 255;
         if (var2 != 0 && var2 <= 8) {
            int var3 = 1;

            for (int var4 = 0; var4 < var2; var4++) {
               if (var3 >= var1.length) {
                  throw new IllegalArgumentException("missing lane");
               }

               int var5 = var1[var3++] & 255;
               if (var5 == 0 || var5 > 3 || var3 + var5 > var1.length) {
                  throw new IllegalArgumentException("invalid lane directions");
               }

               int var6 = 0;

               for (int var7 = 0; var7 < var5; var7++) {
                  int var8 = var1[var3++] & 255;
                  if ((var8 & 112) != 0 || (var8 & 15) > 9) {
                     throw new IllegalArgumentException("invalid lane shape");
                  }

                  if ((var8 & 128) != 0) {
                     if (++var6 > 1) {
                        throw new IllegalArgumentException("multiple selected directions");
                     }
                  }
               }
            }

            if (var3 != var1.length) {
               throw new IllegalArgumentException("trailing lane bytes");
            } else {
               return var1;
            }
         } else {
            throw new IllegalArgumentException("invalid lane count");
         }
      } else {
         throw new IllegalArgumentException("invalid lane hex");
      }
   }

   private static String decodeHex(String var0) throws Exception {
      if ("-".equals(var0)) {
         return "";
      } else if ((var0.length() & 1) == 0 && var0.length() <= 768) {
         return new String(decodeHexBytes(var0), "UTF-8");
      } else {
         throw new IllegalArgumentException("invalid hex text");
      }
   }

   private static byte[] decodeHexBytes(String var0) {
      byte[] var1 = new byte[var0.length() / 2];

      for (int var2 = 0; var2 < var1.length; var2++) {
         int var3 = Character.digit(var0.charAt(var2 * 2), 16);
         int var4 = Character.digit(var0.charAt(var2 * 2 + 1), 16);
         if (var3 < 0 || var4 < 0) {
            throw new IllegalArgumentException("invalid hex digit");
         }

         var1[var2] = (byte)(var3 << 4 | var4);
      }

      return var1;
   }
}
