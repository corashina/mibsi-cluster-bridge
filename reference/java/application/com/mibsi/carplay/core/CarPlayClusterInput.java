package com.mibsi.carplay.core;

import com.mibsi.carplay.framework.Log;
import com.mibsi.carplay.rgd.Mhi2qAaNavProxy;
import de.audi.atip.interapp.combi.bap.navi.CombiBAPServiceNavi;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public final class CarPlayClusterInput implements Runnable {
   private static final Object LOCK = new Object();
   private static final int PORT = 15558;
   private static final int MAX_PENDING_STEPS = 1024;
   private static final int MAX_REPORT_STEPS = 127;
   static final int MAP_SCALE_OEM = 0;
   static final int MAP_SCALE_CONSUME = 1;
   static final int MAP_SCALE_FORWARD = 2;
   private static Thread worker;
   private static int pendingSteps;

   private CarPlayClusterInput() {
   }

   public static int onMapScale(int var0, CombiBAPServiceNavi var1) {
      boolean var2 = CarPlayApp.isActive();
      boolean var3 = var2 && Mhi2qAaNavProxy.isVcSettingsMenuRequested();
      int var4 = mapScaleActionForTest(var2, CarPlayApp.isCarPlayActive(), Mhi2qAaClusterComposition.isClusterProjectionEnabled(), var0);
      if (var4 != 0 || var3 && var0 != 0) {
         Mhi2qAaNavProxy.acknowledgeProjectedMapScaleInput(var1);
      }

      if (var3 && CarPlayApp.moveVcSettingsSelection(var0)) {
         return 0;
      }

      if (var4 == 0) {
         return var0;
      }

      if (var4 == 1) {
         return 0;
      }

      synchronized (LOCK) {
         pendingSteps = addBoundedForTest(pendingSteps, var0);
         ensureWorkerLocked();
         if (worker == null) {
            pendingSteps = 0;
            return 0;
         } else {
            LOCK.notifyAll();
            return 0;
         }
      }
   }

   public static void onDeactivate() {
      synchronized (LOCK) {
         pendingSteps = 0;
      }
   }

   private static void ensureWorkerLocked() {
      if (worker == null || !worker.isAlive()) {
         Thread var0 = new Thread(new CarPlayClusterInput(), "MHI2Q-CarPlay-Cluster-Input");
         var0.setDaemon(true);

         try {
            var0.start();
            worker = var0;
         } catch (Throwable var2) {
            worker = null;
            Log.w("CarPlayClusterInput", "worker start failed: " + var2);
         }
      }
   }

   public void run() {
      DatagramSocket var1 = null;

      try {
         var1 = new DatagramSocket();
         InetAddress var2 = InetAddress.getByName("127.0.0.1");

         while (true) {
            int var3;
            synchronized (LOCK) {
               while (pendingSteps == 0) {
                  try {
                     LOCK.wait();
                  } catch (InterruptedException var19) {
                  }
               }

               var3 = takeChunkForTest(pendingSteps);
               pendingSteps -= var3;
            }

            if (CarPlayApp.isCarPlayActive()) {
               byte[] var24 = encodePacketForTest(var3);

               try {
                  var1.send(new DatagramPacket(var24, var24.length, var2, 15558));
               } catch (Throwable var20) {
                  Log.w("CarPlayClusterInput", "zoom send failed: " + var20);
               }
            }
         }
      } catch (Throwable var22) {
         Log.w("CarPlayClusterInput", "transport failed: " + var22);
      } finally {
         if (var1 != null) {
            var1.close();
         }

         synchronized (LOCK) {
            if (Thread.currentThread() == worker) {
               worker = null;
            }

            pendingSteps = 0;
         }
      }
   }

   static int mapScaleActionForTest(boolean var0, boolean var1, boolean var2, int var3) {
      if (var0 && var2 && var3 != 0) {
         return var1 ? 2 : 1;
      } else {
         return 0;
      }
   }

   static int addBoundedForTest(int var0, int var1) {
      long var2 = (long)var0 + var1;
      if (var2 > 1024L) {
         return 1024;
      } else {
         return var2 < -1024L ? -1024 : (int)var2;
      }
   }

   static int takeChunkForTest(int var0) {
      if (var0 > 127) {
         return 127;
      } else {
         return var0 < -127 ? -127 : var0;
      }
   }

   static byte[] encodePacketForTest(int var0) {
      return new byte[]{67, 80, 90, 49, (byte)(var0 >>> 24), (byte)(var0 >>> 16), (byte)(var0 >>> 8), (byte)var0};
   }
}
