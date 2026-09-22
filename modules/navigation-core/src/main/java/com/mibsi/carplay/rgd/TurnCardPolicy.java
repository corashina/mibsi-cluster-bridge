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

   public static int actionThreshold(int var0) {
      int var1 = ManeuverMapper.isHighway(var0) ? 3000 : 1500;
      return var1 * 15 / 100;
   }

   public static boolean shouldOpen(int var0, int var1, int var2, boolean var3) {
      if (var2 == 0) {
         return false;
      }

      if (var2 >= 39 && var2 <= 42) {
         return true;
      }

      boolean var4 = ManeuverMapper.isHighway(var2);
      int var5 = var4 ? (var3 ? 55 : 45) : (var3 ? 45 : 35);
      int var6 = var4 ? (var3 ? 1500 : 1200) : (var3 ? 560 : 450);
      return var0 >= 0 && var0 <= var5 || var1 >= 0 && var1 <= var6;
   }

   public static int bapManeuverState(boolean var0, boolean var1) {
      if (!var0) {
         return 0;
      } else {
         return var1 ? 4 : 2;
      }
   }
}
