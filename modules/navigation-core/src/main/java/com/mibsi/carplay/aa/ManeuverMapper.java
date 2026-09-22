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

   public static boolean isHighway(int var0) {
      return var0 >= 13 && var0 <= 29;
   }

   public static int[] bapDescriptor(int var0, int var1) {
      byte var2 = 11;
      int var3 = direction(var0);
      if (var0 == 0) {
         var2 = 0;
      } else if (var0 == 1) {
         var2 = 11;
      } else if (var0 != 2 && var0 != 36) {
         if (var0 != 3 && var0 != 4 && (var0 < 27 || var0 > 29)) {
            if (var0 >= 5 && var0 <= 10) {
               var2 = 13;
            } else if (var0 == 11 || var0 == 12) {
               var2 = 25;
            } else if (var0 >= 13 && var0 <= 20) {
               var2 = (byte)(isLeft(var0) ? 18 : 17);
            } else if (var0 >= 21 && var0 <= 24) {
               var2 = (byte)(isLeft(var0) ? 16 : 15);
            } else if (var0 == 25 || var0 == 26) {
               var2 = 19;
            } else if (var0 >= 32 && var0 <= 35) {
               var2 = 21;
            } else if (var0 >= 39 && var0 <= 42) {
               var2 = 3;
            }
         } else {
            var2 = 12;
         }
      } else {
         var2 = 11;
      }

      if ((var0 == 33 || var0 == 35) && var1 >= 1 && var1 <= 360) {
         int var4 = var0 == 33 ? var1 - 180 : 180 - var1;
         var3 = -var4 * 256 / 360 & 0xFF;
      }

      return new int[]{var2, var3};
   }

   public static int[] rendererManeuver(int var0, int var1, int var2) {
      byte var3 = 1;
      int var4 = isLeft(var0) ? -1 : 1;
      int var5 = 0;
      byte var6 = 0;
      byte var7 = 0;
      if (var0 == 0) {
         var3 = 0;
         var4 = 0;
      } else if (var0 == 1
         || var0 == 2
         || var0 == 30
         || var0 == 31
         || var0 == 36
         || var0 == 37
         || var0 == 38
         || var0 == 43
         || var0 == 45
         || var0 == 47
         || var0 == 48
         || var0 == 49
         || var0 == 50) {
         var3 = 1;
         var4 = 0;
      } else if (var0 == 3 || var0 == 4) {
         var3 = 8;
      } else if (var0 >= 5 && var0 <= 10) {
         var3 = 2;
         if (var0 <= 6) {
            var5 = var4 * 45;
         } else if (var0 <= 8) {
            var5 = var4 * 90;
         } else {
            var5 = var4 * 135;
         }

         var4 = 0;
      } else if (var0 != 11 && var0 != 12) {
         if ((var0 < 13 || var0 > 18) && var0 != 27 && var0 != 28) {
            if (var0 == 19 || var0 == 20) {
               var3 = 3;
               var5 = var4 * 180;
               var6 = (byte)(var4 > 0 ? 1 : 0);
            } else if (var0 >= 21 && var0 <= 24) {
               var3 = 5;
            } else if (var0 == 25 || var0 == 26) {
               var3 = 9;
            } else if (var0 == 29) {
               var3 = 1;
               var4 = 0;
            } else if (var0 >= 32 && var0 <= 35) {
               var3 = 6;
               var4 = 0;
               boolean var8 = var0 == 32 || var0 == 33;
               var6 = (byte)(var8 ? 1 : 0);
               if ((var0 == 33 || var0 == 35) && var1 >= 1 && var1 <= 360) {
                  var5 = var8 ? var1 - 180 : 180 - var1;
                  var7 = 1;
               }
            } else if (var0 >= 39 && var0 <= 42) {
               var3 = 7;
               if (var0 == 39 || var0 == 40) {
                  var4 = 0;
               }
            } else if (var0 == 44) {
               var3 = 5;
               var4 = -1;
            } else if (var0 == 46) {
               var3 = 5;
               var4 = 1;
            }
         } else {
            var3 = 4;
         }
      } else {
         var3 = 3;
         var5 = var4 * 180;
         var6 = (byte)(var4 > 0 ? 1 : 0);
      }

      return new int[]{var3, var4, var5, var6, var7};
   }

   private static boolean isLeft(int var0) {
      return var0 == 3
         || var0 == 5
         || var0 == 7
         || var0 == 9
         || var0 == 11
         || var0 == 13
         || var0 == 15
         || var0 == 17
         || var0 == 19
         || var0 == 21
         || var0 == 23
         || var0 == 25
         || var0 == 27
         || var0 == 34
         || var0 == 41
         || var0 == 45
         || var0 == 47;
   }

   private static int direction(int var0) {
      switch (var0) {
         case 3:
         case 5:
            return 32;
         case 4:
         case 6:
            return 224;
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
         case 47:
            return 64;
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
         case 50:
            return 192;
         case 9:
            return 96;
         case 10:
            return 160;
         case 11:
         case 12:
            return 128;
         case 29:
         case 30:
         case 31:
         case 32:
         case 33:
         case 34:
         case 35:
         case 36:
         case 37:
         case 38:
         case 39:
         case 40:
         case 43:
         case 49:
         default:
            return 0;
      }
   }
}
