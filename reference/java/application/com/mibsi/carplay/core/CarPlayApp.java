package com.mibsi.carplay.core;

import com.mibsi.carplay.aa.NavigationStateReader;
import com.mibsi.carplay.rgd.Mhi2qAaNavProxy;

public final class CarPlayApp {
   private static volatile boolean active;
   private static volatile String activeProjection = "stock";
   private static Object requestedContext;
   private static String requestedProjection;

   private CarPlayApp() {
   }

   public static void startTransport(Object var0) {
      ProjectionRuntimeState.start();
      Mhi2qAaClusterComposition.prepare(var0);
      Mhi2qAaNavProxy.startTransport(var0);
   }

   public static synchronized void onActivate(Object var0) {
      onActivate(var0, "aa");
   }

   public static synchronized void onActivate(Object var0, String var1) {
      requestedContext = var0;
      requestedProjection = var1;
      ProjectionRuntimeState.start();
      if (!ProjectionRuntimeState.isReady()) {
         active = false;
         activeProjection = "stock";
      } else {
         activeProjection = "carplay".equals(var1) ? "carplay" : "aa";
         active = true;
         NavigationStateReader.setProjection(var1);
         Mhi2qAaClusterComposition.onActivate(var0);
         Mhi2qAaNavProxy.onCarPlayActivate();
         Mhi2qAaNavProxy.startTransport(var0);
      }
   }

   public static synchronized void onDeactivate() {
      requestedContext = null;
      requestedProjection = null;
      active = false;
      activeProjection = "stock";
      CarPlayClusterInput.onDeactivate();
      NavigationStateReader.setProjection("stock");
      Mhi2qAaClusterComposition.onDeactivate();
      Mhi2qAaNavProxy.onCarPlayDeactivate();
   }

   static synchronized void onRuntimeReady() {
      if (requestedProjection != null && !active) {
         onActivate(requestedContext, requestedProjection);
      }
   }

   public static void onDeactivateAndWait() {
      onDeactivate();
   }

   public static boolean isActive() {
      return active && ProjectionRuntimeState.isReady();
   }

   public static boolean isCarPlayActive() {
      return active && ProjectionRuntimeState.isReady() && "carplay".equals(activeProjection);
   }

   public static synchronized boolean openVcSettingsMenu() {
      return active && Mhi2qAaNavProxy.openVcSettingsMenu();
   }

   public static synchronized void closeVcSettingsMenu() {
      if (active) {
         Mhi2qAaNavProxy.closeVcSettingsMenu();
      }
   }

   public static synchronized void toggleVcSettingsMenu() {
      if (active) {
         Mhi2qAaNavProxy.toggleVcSettingsMenu();
      }
   }

   public static synchronized boolean moveVcSettingsSelection(int var0) {
      return active && Mhi2qAaNavProxy.moveVcSettingsSelection(var0);
   }

   public static synchronized boolean selectVcSettingsItem() {
      return active && Mhi2qAaNavProxy.selectVcSettingsItem();
   }
}
