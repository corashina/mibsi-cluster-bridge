package com.mibsi.carplay.rgd;

import com.mibsi.carplay.framework.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class RendererServer {
   private static final String TAG = "RendererServer";
   private static final String HOST = "127.0.0.1";
   private static final int PORT = 19800;
   private static final int PKT_SIZE = 48;
   private static final int WRITE_QUEUE_CAPACITY = 32;
   private static final int SOCKET_READ_TIMEOUT_MS = 5000;
   private static final byte EVT_HEARTBEAT = -128;
   private static final byte EVT_READY = -127;
   private static final byte EVT_FRAME_READY = -126;
   private static final byte EVT_FRAME_CLEARED = -125;
   private static final byte CMD_MANEUVER = 1;
   private static final byte CMD_BARGRAPH = 6;
   private static final byte CMD_CLEAR = 7;
   private static final byte CMD_PRESENTED = 8;
   private static final byte CMD_VC_MENU = 9;
   private static final byte CMD_LANE_GUIDANCE = 10;
   private static final byte MAN_FLAG_SET_PERSP = 1;
   private static final byte MAN_FLAG_BARGRAPH = 2;
   private static final byte MAN_FLAG_ROUNDABOUT_ANGLE_VALID = 4;
   private final Object lock = new Object();
   private final Object writeLock = new Object();
   private ServerSocket server;
   private Socket sock;
   private OutputStream out;
   private Thread acceptThread;
   private Thread writerThread;
   private volatile boolean running;
   private volatile boolean rendererReady;
   private volatile boolean frameReady;
   private volatile boolean clearPending;
   private volatile int menuPresentationToken;
   private volatile boolean everConnected;
   private volatile RendererServer.StateListener stateListener;
   private long lastBindFailureLogMs;
   private int suppressedBindFailures;
   private int connectionGeneration;
   private final RendererServer.PendingWrite[] writeQueue = new RendererServer.PendingWrite[32];
   private int writeHead;
   private int writeTail;
   private int writeCount;

   public void setStateListener(RendererServer.StateListener var1) {
      this.stateListener = var1;
   }

   public boolean connect() {
      synchronized (this.lock) {
         if (this.server != null && !this.server.isClosed()) {
            return true;
         }

         ServerSocket var2 = null;

         try {
            var2 = new ServerSocket();
            var2.setReuseAddress(true);
            var2.bind(new InetSocketAddress("127.0.0.1", 19800));
            this.server = var2;
            this.running = true;
            Log.i("RendererServer", "listening on 127.0.0.1:19800");
         } catch (IOException var7) {
            if (var2 != null) {
               try {
                  var2.close();
               } catch (IOException var6) {
               }
            }

            this.logBindFailure(var7);
            return false;
         }

         this.acceptThread = new Thread(new RendererServer$1(this), "RendererServer-Accept");
         this.acceptThread.setDaemon(true);
         this.acceptThread.start();
         this.writerThread = new Thread(new RendererServer$2(this), "RendererServer-Write");
         this.writerThread.setDaemon(true);
         this.writerThread.start();
         return true;
      }
   }

   private void logBindFailure(IOException var1) {
      long var2 = System.currentTimeMillis();
      if (this.lastBindFailureLogMs != 0L && var2 - this.lastBindFailureLogMs < 10000L) {
         this.suppressedBindFailures++;
      } else {
         String var4 = this.suppressedBindFailures > 0 ? " (" + this.suppressedBindFailures + " repeats suppressed)" : "";
         Log.w("RendererServer", "bind failed: " + var1.getMessage() + var4);
         this.lastBindFailureLogMs = var2;
         this.suppressedBindFailures = 0;
      }
   }

   private void acceptLoop() {
      ServerSocket var1;
      synchronized (this.lock) {
         var1 = this.server;
      }

      while (this.running && var1 != null && !var1.isClosed()) {
         try {
            Socket var16 = var1.accept();

            try {
               var16.setTcpNoDelay(true);
               var16.setSoTimeout(5000);
               OutputStream var17 = var16.getOutputStream();
               InputStream var4 = var16.getInputStream();
               synchronized (this.lock) {
                  if (this.sock != null) {
                     try {
                        this.sock.close();
                     } catch (Exception var10) {
                     }
                  }

                  this.sock = var16;
                  this.out = var17;
                  this.connectionGeneration++;
                  this.clearWriteQueue();
                  this.everConnected = true;
                  this.rendererReady = false;
                  this.frameReady = false;
                  this.clearPending = false;
                  this.menuPresentationToken = 0;
                  this.lock.notifyAll();
               }

               Log.i("RendererServer", "renderer connected from " + var16.getInetAddress() + ":" + var16.getPort());
               this.notifyStateChanged("connected");
               Thread var18 = new Thread(new RendererServer$3(this, var16, var4), "RendererServer-Read");
               var18.setDaemon(true);
               var18.start();
            } catch (IOException var14) {
               try {
                  var16.close();
               } catch (Exception var9) {
               }
            }
         } catch (IOException var15) {
            if (this.running) {
            }

            synchronized (this.lock) {
               if (this.server == null || this.server.isClosed()) {
                  break;
               }
            }
         }
      }

      Log.i("RendererServer", "accept thread exiting");
   }

   private void writerLoop() {
      while (true) {
         label88: {
            RendererServer.PendingWrite var1;
            synchronized (this.writeLock) {
               while (this.running && this.writeCount == 0) {
                  try {
                     this.writeLock.wait();
                  } catch (InterruptedException var7) {
                  }
               }

               if (!this.running) {
                  break label88;
               }

               var1 = this.writeQueue[this.writeHead];
               this.writeQueue[this.writeHead] = null;
               this.writeHead = (this.writeHead + 1) % 32;
               this.writeCount--;
            }

            OutputStream var12;
            synchronized (this.lock) {
               if (this.out == null || var1 == null || var1.generation != this.connectionGeneration) {
                  continue;
               }

               var12 = this.out;
            }

            try {
               var12.write(var1.packet);
               var12.flush();
            } catch (Exception var9) {
               Log.w("RendererServer", "Writer failed: " + var9.getMessage());
               boolean var4 = false;
               synchronized (this.lock) {
                  if (this.out == var12) {
                     this.closeClientLocked();
                     var4 = true;
                  }
               }

               if (var4) {
                  this.notifyStateChanged("send-failed");
               }
            }
            continue;
         }

         Log.i("RendererServer", "writer thread exiting");
         return;
      }
   }

   private void clearWriteQueue() {
      synchronized (this.writeLock) {
         for (int var2 = 0; var2 < 32; var2++) {
            this.writeQueue[var2] = null;
         }

         this.writeHead = 0;
         this.writeTail = 0;
         this.writeCount = 0;
         this.writeLock.notifyAll();
      }
   }

   private void readerLoop(Socket var1, InputStream var2) {
      byte[] var3 = new byte[48];

      try {
         while (this.running) {
            int var4 = 0;

            while (var4 < 48) {
               int var5 = var2.read(var3, var4, 48 - var4);
               if (var5 < 0) {
                  throw new IOException("EOF");
               }

               var4 += var5;
            }

            this.handleRendererEvent(var1, var3[0]);
         }
      } catch (IOException var18) {
         if (this.running) {
            Log.i("RendererServer", "reader exit: " + var18.getClass().getName() + (var18.getMessage() == null ? "" : " " + var18.getMessage()));
         }
      } finally {
         boolean var9 = false;
         synchronized (this.lock) {
            if (this.sock == var1) {
               this.closeClientLocked();
               var9 = true;
            }
         }

         if (var9) {
            this.notifyStateChanged("disconnected");
         }
      }
   }

   private void notifyStateChanged(String var1) {
      RendererServer.StateListener var2 = this.stateListener;
      if (var2 != null) {
         try {
            var2.onRendererStateChanged(var1);
         } catch (Throwable var4) {
            Log.w("RendererServer", "state listener failed: " + var4.getMessage());
         }
      }
   }

   private void handleRendererEvent(Socket var1, byte var2) {
      boolean var3 = false;
      String var4 = null;
      synchronized (this.lock) {
         if (this.sock != var1) {
            return;
         }

         if (var2 == -127) {
            var3 = !this.rendererReady;
            this.rendererReady = true;
            this.lock.notifyAll();
            var4 = "ready";
         } else if (var2 == -126) {
            this.rendererReady = true;
            if (!this.clearPending) {
               var3 = !this.frameReady;
               this.frameReady = true;
               this.lock.notifyAll();
               var4 = "frame-ready";
            }
         } else if (var2 == -125) {
            var3 = this.clearPending || this.frameReady;
            this.clearPending = false;
            this.frameReady = false;
            this.lock.notifyAll();
            var4 = "frame-cleared";
         }
      }

      if (var3) {
         Log.i("RendererServer", "renderer " + var4);
         this.notifyStateChanged(var4);
      }
   }

   public void disconnectClient() {
      boolean var1;
      synchronized (this.lock) {
         var1 = this.sock != null || this.rendererReady || this.frameReady || this.clearPending;
         this.closeClientLocked();
      }

      if (var1) {
         this.notifyStateChanged("disconnect-request");
      }
   }

   public void dispose() {
      synchronized (this.lock) {
         this.running = false;
         this.everConnected = false;
         this.closeClientLocked();
         if (this.server != null) {
            try {
               this.server.close();
            } catch (Exception var8) {
            }

            this.server = null;
         }
      }

      this.clearWriteQueue();
      if (this.acceptThread != null) {
         try {
            this.acceptThread.join(500L);
         } catch (InterruptedException var7) {
         }

         this.acceptThread = null;
      }

      synchronized (this.writeLock) {
         this.writeLock.notifyAll();
      }

      if (this.writerThread != null) {
         try {
            this.writerThread.join(500L);
         } catch (InterruptedException var5) {
         }

         this.writerThread = null;
      }

      this.notifyStateChanged("disposed");
   }

   private void closeClientLocked() {
      if (this.out != null) {
         try {
            this.out.close();
         } catch (Exception var3) {
         }

         this.out = null;
      }

      if (this.sock != null) {
         try {
            this.sock.close();
         } catch (Exception var2) {
         }

         this.sock = null;
      }

      this.connectionGeneration++;
      this.clearWriteQueue();
      this.rendererReady = false;
      this.frameReady = false;
      this.clearPending = false;
      this.menuPresentationToken = 0;
      this.lock.notifyAll();
   }

   public boolean isConnected() {
      synchronized (this.lock) {
         return this.sock != null && !this.sock.isClosed() && this.out != null;
      }
   }

   public boolean isReady() {
      synchronized (this.lock) {
         return this.sock != null && !this.sock.isClosed() && this.out != null && this.rendererReady;
      }
   }

   public boolean isFrameReady() {
      synchronized (this.lock) {
         return this.sock != null && !this.sock.isClosed() && this.out != null && this.frameReady && !this.clearPending;
      }
   }

   public boolean everConnected() {
      return this.everConnected;
   }

   public boolean sendManeuver(int var1, int var2, int var3, int var4, int[] var5, int var6, int var7, int var8, int var9) {
      return this.sendManeuver(var1, var2, var3, var4, var5, null, 0, false, var6, var7, var8, var9);
   }

   public boolean sendManeuver(
      int var1, int var2, int var3, int var4, int[] var5, byte[] var6, int var7, boolean var8, int var9, int var10, int var11, int var12
   ) {
      byte[] var13 = new byte[48];
      var13[0] = 1;
      byte var14 = 0;
      if (var10 > 0) {
         var14 = (byte)(var14 | 2);
      }

      if (var11 >= 0) {
         var14 = (byte)(var14 | 1);
      }

      if (var8) {
         var14 = (byte)(var14 | 4);
      }

      var13[1] = var14;
      var13[2] = (byte)(var1 & 0xFF);
      var13[3] = (byte)var2;
      var13[4] = (byte)(var3 >> 8 & 0xFF);
      var13[5] = (byte)(var3 & 0xFF);
      var13[6] = (byte)(var4 & 0xFF);
      int var15 = 0;
      if (var5 != null) {
         var15 = var5.length;
         if (var15 > 16) {
            var15 = 16;
         }
      }

      var13[7] = (byte)(var15 & 0xFF);

      for (int var16 = 0; var16 < var15; var16++) {
         int var17 = 8 + var16 * 2;
         if (var17 + 1 >= 48) {
            break;
         }

         int var18 = var5[var16];
         var13[var17] = (byte)(var18 >> 8 & 0xFF);
         var13[var17 + 1] = (byte)(var18 & 0xFF);
      }

      var13[40] = (byte)(var12 >> 24 & 0xFF);
      var13[41] = (byte)(var12 >> 16 & 0xFF);
      var13[42] = (byte)(var12 >> 8 & 0xFF);
      var13[43] = (byte)(var12 & 0xFF);
      if (var7 < 0) {
         var7 = 0;
      }

      if (var7 > 99) {
         var7 = 99;
      }

      var13[44] = (byte)(var7 & 0xFF);
      if (var11 >= 0) {
         var13[45] = (byte)(var11 & 0xFF);
      }

      if (var10 > 0) {
         var13[46] = (byte)(var9 & 0xFF);
         var13[47] = (byte)(var10 & 0xFF);
      }

      byte[] var19 = buildLanePacket(var6, var12);
      return this.sendPackets(new byte[][]{var19, var13}, false);
   }

   private static byte[] buildLanePacket(byte[] var0, int var1) {
      byte[] var2 = new byte[48];
      var2[0] = 10;
      var2[2] = (byte)(var1 >> 24 & 0xFF);
      var2[3] = (byte)(var1 >> 16 & 0xFF);
      var2[4] = (byte)(var1 >> 8 & 0xFF);
      var2[5] = (byte)(var1 & 0xFF);
      var2[7] = 105;
      if (var0 != null && var0.length != 0) {
         int var3 = var0[0] & 255;
         int var4 = validateLaneGuidance(var0, var3);
         if (var4 == 0) {
            return var2;
         }

         var2[6] = (byte)var3;
         System.arraycopy(var0, 1, var2, 8, var4 - 1);
         var2[40] = (byte)(var4 - 1);
         return var2;
      } else {
         return var2;
      }
   }

   static byte[] lanePacketForTest(byte[] var0, int var1) {
      return buildLanePacket(var0, var1);
   }

   private static int validateLaneGuidance(byte[] var0, int var1) {
      if (var1 >= 1 && var1 <= 8 && var0.length <= 33) {
         int var2 = 1;

         for (int var3 = 0; var3 < var1; var3++) {
            if (var2 >= var0.length) {
               return 0;
            }

            int var4 = var0[var2++] & 255;
            if (var4 < 1 || var4 > 3 || var2 + var4 > var0.length) {
               return 0;
            }

            int var5 = 0;

            for (int var6 = 0; var6 < var4; var6++) {
               int var7 = var0[var2++] & 255;
               if ((var7 & 112) != 0 || (var7 & 15) > 9) {
                  return 0;
               }

               if ((var7 & 128) != 0) {
                  if (++var5 > 1) {
                     return 0;
                  }
               }
            }
         }

         return var2 == var0.length ? var2 : 0;
      } else {
         return 0;
      }
   }

   public boolean sendBargraph(int var1, int var2) {
      byte[] var3 = new byte[48];
      var3[0] = 6;
      var3[2] = (byte)(var1 & 0xFF);
      var3[3] = (byte)(var2 & 0xFF);
      return this.sendPacket(var3);
   }

   public boolean sendClear() {
      byte[] var1 = new byte[48];
      var1[0] = 7;
      return this.sendPacket(var1, true);
   }

   public boolean sendPresented(int var1, int var2, int var3, int var4, int var5) {
      byte[] var6 = new byte[48];
      var6[0] = 8;
      var6[2] = (byte)(var1 >> 24 & 0xFF);
      var6[3] = (byte)(var1 >> 16 & 0xFF);
      var6[4] = (byte)(var1 >> 8 & 0xFF);
      var6[5] = (byte)(var1 & 0xFF);
      putU16(var6, 6, var2);
      putU16(var6, 8, var3);
      putU16(var6, 10, var4);
      putU16(var6, 12, var5);
      return this.sendPacket(var6);
   }

   public boolean sendVcMenu(boolean var1, int var2, int var3, int var4) {
      byte[] var5 = new byte[48];
      var5[0] = 9;
      var5[2] = (byte)(var1 ? 1 : 0);
      var5[3] = (byte)(var2 & 0xFF);
      var5[4] = (byte)(var3 & 0xFF);
      var5[5] = 0;
      var5[6] = (byte)(var4 >> 24 & 0xFF);
      var5[7] = (byte)(var4 >> 16 & 0xFF);
      var5[8] = (byte)(var4 >> 8 & 0xFF);
      var5[9] = (byte)(var4 & 0xFF);
      boolean var6;
      synchronized (this.lock) {
         var6 = !var1 || var4 != this.menuPresentationToken;
         this.menuPresentationToken = var1 ? var4 : 0;
      }

      return this.sendPacket(var5, var6);
   }

   private static void putU16(byte[] var0, int var1, int var2) {
      if (var2 < 0) {
         var2 = 0;
      }

      if (var2 > 65535) {
         var2 = 65535;
      }

      var0[var1] = (byte)(var2 >> 8 & 0xFF);
      var0[var1 + 1] = (byte)(var2 & 0xFF);
   }

   private boolean sendPacket(byte[] var1) {
      return this.sendPacket(var1, false);
   }

   private boolean sendPacket(byte[] var1, boolean var2) {
      return this.sendPackets(new byte[][]{var1}, var2);
   }

   private boolean sendPackets(byte[][] var1, boolean var2) {
      int var3;
      synchronized (this.lock) {
         if (this.out == null) {
            return false;
         }

         var3 = this.connectionGeneration;
         if (var2) {
            this.frameReady = false;
            this.clearPending = true;
            this.lock.notifyAll();
         }
      }

      synchronized (this.writeLock) {
         for (int var5 = 0; var5 < var1.length; var5++) {
            RendererServer.PendingWrite var6 = new RendererServer.PendingWrite(null);
            var6.packet = var1[var5];
            var6.generation = var3;
            if (this.writeCount == 32) {
               this.writeQueue[this.writeHead] = null;
               this.writeHead = (this.writeHead + 1) % 32;
               this.writeCount--;
            }

            this.writeQueue[this.writeTail] = var6;
            this.writeTail = (this.writeTail + 1) % 32;
            this.writeCount++;
         }

         this.writeLock.notifyAll();
         return true;
      }
   }

   private static final class PendingWrite {
      byte[] packet;
      int generation;

      private PendingWrite() {
      }
   }

   public interface StateListener {
      void onRendererStateChanged(String var1);
   }
}
