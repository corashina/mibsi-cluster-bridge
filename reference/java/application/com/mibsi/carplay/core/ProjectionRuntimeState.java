package com.mibsi.carplay.core;

import com.mibsi.carplay.framework.Log;

final class ProjectionRuntimeState {
   private static String checker = "/eso/bin/apps/mhi2q-aa-cluster-display";
   private static volatile boolean ready = checker.length() == 0;
   private static Thread worker;

   private ProjectionRuntimeState() {
   }

   static synchronized void start() {
      if (!ready && (worker == null || !worker.isAlive())) {
         worker = new Thread(new ProjectionRuntimeState$1(), "MIBSI-VIN");
         worker.setDaemon(true);

         try {
            worker.start();
         } catch (Throwable var1) {
            worker = null;
            Log.w("VIN", "authorization worker unavailable");
         }
      }
   }

   private static int check() {
      Process var0 = null;

      try {
         var0 = Runtime.getRuntime().exec(new String[]{checker, "--check-vin"});
         long var1 = System.currentTimeMillis() + 5000L;

         while (System.currentTimeMillis() < var1) {
            try {
               return var0.exitValue();
            } catch (IllegalThreadStateException var25) {
               Thread.sleep(50L);
            }
         }
      } catch (Throwable var26) {
      } finally {
         if (var0 != null) {
            var0.destroy();

            try {
               var0.getInputStream().close();
            } catch (Throwable var24) {
            }

            try {
               var0.getErrorStream().close();
            } catch (Throwable var23) {
            }

            try {
               var0.getOutputStream().close();
            } catch (Throwable var22) {
            }
         }
      }

      return 55;
   }

   static boolean isReady() {
      return ready;
   }
}
