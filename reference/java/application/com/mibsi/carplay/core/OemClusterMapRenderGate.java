package com.mibsi.carplay.core;

import com.mibsi.carplay.framework.Log;
import de.audi.tghu.navi.app.Navigation;
import de.audi.tghu.navi.app.map.AbstractMap;
import de.audi.tghu.navi.app.map.dsi.MVRequestControl;
import de.esolutions.fw.util.commons.job.DispatcherBase;

public final class OemClusterMapRenderGate {
   private static OemClusterMapRenderGate.Backend backend = new OemClusterMapRenderGate.NavigationBackend(null);
   private static boolean wanted;
   private static boolean queued;
   private static Object held;
   private static boolean heldDesired;
   private static Object observed;
   private static boolean observedDesired;
   private static long nextCheck;
   private static boolean failureLogged;
   private static boolean restorePending;

   private OemClusterMapRenderGate() {
   }

   public static synchronized boolean filter(Object var0, boolean var1) {
      try {
         if (var0 != null && var0 == held) {
            heldDesired = var1;
         }

         if (var0 != null && var0 == backend.current()) {
            observed = var0;
            observedDesired = var1;
            return wanted && var0 == held ? false : var1;
         } else {
            return var1;
         }
      } catch (Throwable var3) {
         return var1;
      }
   }

   public static synchronized boolean request(boolean var0) {
      if (wanted != var0) {
         nextCheck = 0L;
      }

      wanted = var0;
      if (!wanted && held == null && !queued) {
         return true;
      }

      long var1 = System.currentTimeMillis();
      if (!queued && var1 >= nextCheck) {
         nextCheck = var1 + 1000L;
         queued = true;

         try {
            if (!backend.execute(new OemClusterMapRenderGate$1())) {
               queued = false;
            }
         } catch (Throwable var4) {
            queued = false;
            reportFailure(var4);
         }
      }

      return !wanted && held == null && !queued;
   }

   private static synchronized void reconcile() {
      try {
         try {
            Object var0 = wanted ? backend.current() : null;
            if (held != null && (!wanted || var0 != held || restorePending)) {
               restorePending = true;
               if (!backend.write(held, heldDesired)) {
                  return;
               }

               held = null;
               restorePending = false;
               Log.w("OEMMapRender", "cockpit OEM visibility restored");
            }

            if (wanted && var0 != null && held == null) {
               boolean var1 = var0 == observed ? observedDesired : backend.visible(var0);
               held = var0;
               heldDesired = var1;
               if (!backend.write(var0, false)) {
                  held = null;
                  return;
               }

               restorePending = false;
               Log.w("OEMMapRender", "cockpit OEM rendering suspended");
            }

            failureLogged = false;
         } catch (Throwable var5) {
            restorePending = true;
            reportFailure(var5);
         }
      } finally {
         queued = false;
      }
   }

   private static void reportFailure(Throwable var0) {
      if (!failureLogged) {
         Log.w("OEMMapRender", "visibility retry: " + var0);
      }

      failureLogged = true;
   }

   interface Backend {
      Object current();

      boolean visible(Object var1);

      boolean write(Object var1, boolean var2);

      boolean execute(Runnable var1);
   }

   private static final class NavigationBackend implements OemClusterMapRenderGate.Backend {
      private NavigationBackend() {
      }

      public Object current() {
         Navigation var1 = Navigation.getInstance();
         if (var1 != null && var1.getMapManager() != null) {
            AbstractMap var2 = var1.getMapManager().getMapKombi();
            return var2 != null && var2.isInitialized() && var2.getMVRequest() != null ? var2.getMVRequest().getMVRequestControlActive() : null;
         } else {
            return null;
         }
      }

      public boolean visible(Object var1) {
         return ((MVRequestControl)var1).getMVResponseControl().getViewVisible();
      }

      public boolean write(Object var1, boolean var2) {
         MVRequestControl var3 = (MVRequestControl)var1;
         AbstractMap var4 = var3.getMap();
         return var4 != null && var4.isInitialized() ? var3.mibsiSetRenderVisible(var2) : false;
      }

      public boolean execute(Runnable var1) {
         Navigation var2 = Navigation.getInstance();
         if (var2 == null) {
            return false;
         }

         DispatcherBase var3 = var2.getDispatcher();
         if (var3 == null) {
            return false;
         }

         var3.execute(var1);
         return true;
      }
   }
}
