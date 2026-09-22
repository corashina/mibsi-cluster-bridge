package com.mibsi.carplay.core;

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

   public static synchronized void onKeyState(int var0, int var1, int var2) {
      if (var0 == 4 && var1 == 40) {
         try {
            if (var2 == 1) {
               if (!rollerPressed) {
                  rollerLongPressed = false;
                  rollerLongHandled = false;
               }

               rollerPressed = true;
            } else if (var2 == 3) {
               boolean var3 = !rollerLongHandled;
               rollerPressed = true;
               rollerLongPressed = true;
               rollerLongHandled = true;
               if (var3) {
                  CarPlayApp.toggleVcSettingsMenu();
               }
            } else if (var2 == 4) {
               rollerLongPressed = true;
            } else if (var2 == 0 || var2 == 5) {
               boolean var5 = rollerPressed && !rollerLongPressed && var2 == 0;
               rollerPressed = false;
               rollerLongPressed = false;
               rollerLongHandled = false;
               if (var5) {
                  CarPlayApp.selectVcSettingsItem();
               }
            }
         } catch (Throwable var4) {
         }
      }
   }
}
