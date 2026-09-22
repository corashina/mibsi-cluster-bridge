package com.mibsi.carplay.core;

import com.mibsi.carplay.framework.Log;

final class ProjectionRuntimeState$1 implements Runnable {
   public void run() {
      int var1 = -1;

      while (!ProjectionRuntimeState.access$000()) {
         int var2 = ProjectionRuntimeState.access$100();
         if (var2 != var1) {
            Log.w("VIN", "projection authorization status=" + var2);
            var1 = var2;
         }

         if (var2 == 0) {
            ProjectionRuntimeState.access$002(true);
            CarPlayApp.onRuntimeReady();
            return;
         }

         try {
            Thread.sleep(var2 == 56 ? 30000L : 2000L);
         } catch (InterruptedException var4) {
            return;
         }
      }
   }
}
