package de.audi.app.terminalmode.smartphone.androidauto2.nav;

import de.audi.app.terminalmode.IContext;
import de.audi.app.terminalmode.smartphone.androidauto2.AbstractAndroidAuto2Handler;
import de.audi.app.terminalmode.statemachine.Application;
import de.audi.app.terminalmode.statemachine.ApplicationOwner;
import de.audi.app.terminalmode.statemachine.IStateHandler;
import de.audi.atip.log.LogChannel;
import org.dsi.ifc.androidauto2.DSIAndroidAuto2;

public class AndroidAuto2NavHandler extends AbstractAndroidAuto2Handler implements IAndroidAuto2NavHandler {
   private static final String LOGCLASS = "AndroidAuto2NavHandler";
   private volatile ApplicationOwner appNaviOwnerCurrent = ApplicationOwner.NOBODY;
   private volatile boolean hasNavFocusRequested;
   private volatile ApplicationOwner expectedAppNavOwner = ApplicationOwner.NOBODY;

   public AndroidAuto2NavHandler(LogChannel var1, DSIAndroidAuto2 var2, IStateHandler var3, IContext var4) {
      super(var1, var2, var3, var4);
   }

   public void updateNavFocus(ApplicationOwner var1) {
      if (this.hasNavFocusRequested) {
         if (this.expectedAppNavOwner.equals(var1)) {
            this.logger
               .log(
                  1000000,
                  "[%1.updateNavFocus] responding to requested navFocusNotification: %2",
                  "AndroidAuto2NavHandler",
                  ApplicationOwner.DEVICE.equals(var1) ? "PROJECTED" : "NATIVE"
               );
            this.dsi.navFocusNotification(ApplicationOwner.DEVICE.equals(var1) ? 2 : 1, false);
            this.hasNavFocusRequested = false;
            this.expectedAppNavOwner = ApplicationOwner.NOBODY;
         }
      } else {
         if (this.appNaviOwnerCurrent.equals(var1)) {
            this.logger.log(1000000, "[%1.updateNavFocus] %2 -> %3, do nothing", "AndroidAuto2NavHandler", this.appNaviOwnerCurrent, var1);
            return;
         }

         if (ApplicationOwner.DEVICE.equals(var1)) {
            this.logger.log(1000000, "[%1.updateNavFocus] %2 -> %3, navFocusNotification: PROJECTED", "AndroidAuto2NavHandler", this.appNaviOwnerCurrent, var1);
            this.dsi.navFocusNotification(2, true);
         } else {
            this.logger.log(1000000, "[%1.updateNavFocus] %2 -> %3, navFocusNotification: NATIVE", "AndroidAuto2NavHandler", this.appNaviOwnerCurrent, var1);
            boolean var10001 = true;
            boolean var10002 = true;
         }
      }

      this.appNaviOwnerCurrent = var1;
   }

   public void navFocusRequestNotification(int var1, int var2) {
      if (this.isValid(var2)) {
         this.logger.log(1000000, "<- [%1.navFocusRequestNotification] %2", "AndroidAuto2NavHandler", this.getNavState(var1));
         if (1 == var1) {
            this.requestDSIUpdate(Application.NAVI, ApplicationOwner.NOBODY);
            this.hasNavFocusRequested = true;
            this.expectedAppNavOwner = ApplicationOwner.NOBODY;
         } else if (2 == var1) {
            this.hasNavFocusRequested = true;
            this.expectedAppNavOwner = ApplicationOwner.DEVICE;
            this.updateNavFocus(ApplicationOwner.DEVICE);
         }
      }
   }

   private String getNavState(int var1) {
      switch (var1) {
         case 1:
            return "NAV_NATIVE";
         case 2:
            return "NAV_PROJECTED";
         default:
            return "NAV_UNKNOWN " + var1;
      }
   }

   protected String getLogClass() {
      return "AndroidAuto2NavHandler";
   }
}
