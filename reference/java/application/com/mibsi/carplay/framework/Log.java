package com.mibsi.carplay.framework;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public final class Log {
   public static final int E = 0;
   public static final int W = 1;
   public static final int I = 2;
   public static final int D = 3;
   private static final String VERBOSE_MARKERS = "/mnt/app/carplay_verbose:/tmp/carplay_verbose";
   private static int level = resolveInitialLevel();
   private static final String FILE = "/tmp/carplay_java.log";
   private static final String FILE_OLD = "/tmp/carplay_java.log.1";
   private static final long MAX_FILE_BYTES = 524288L;
   private static PrintStream file;
   private static boolean fileTried;
   private static long fileBytes;
   private static final Object LOCK = new Object();
   private static final int QUEUE_CAPACITY = 256;
   private static final String[] queue = new String[256];
   private static int queueHead;
   private static int queueTail;
   private static int queueCount;
   private static Thread writerThread;

   private static int resolveInitialLevel() {
      try {
         int var0 = 0;

         while (var0 < "/mnt/app/carplay_verbose:/tmp/carplay_verbose".length()) {
            int var1 = "/mnt/app/carplay_verbose:/tmp/carplay_verbose".indexOf(58, var0);
            String var2 = var1 < 0
               ? "/mnt/app/carplay_verbose:/tmp/carplay_verbose".substring(var0)
               : "/mnt/app/carplay_verbose:/tmp/carplay_verbose".substring(var0, var1);
            if (new File(var2).exists()) {
               return 2;
            }

            if (var1 < 0) {
               break;
            }

            var0 = var1 + 1;
         }
      } catch (Throwable var3) {
      }

      return 1;
   }

   private Log() {
   }

   public static void setLevel(int var0) {
      level = var0;
   }

   public static void e(String var0, String var1) {
      out(0, "E", var0, var1);
   }

   public static void w(String var0, String var1) {
      out(1, "W", var0, var1);
   }

   public static void i(String var0, String var1) {
      out(2, "I", var0, var1);
   }

   public static void d(String var0, String var1) {
      out(3, "D", var0, var1);
   }

   public static void e(String var0, String var1, Throwable var2) {
      out(0, "E", var0, var1 + " :: " + var2);
   }

   public static void w(String var0, String var1, Throwable var2) {
      out(1, "W", var0, var1 + " :: " + var2);
   }

   private static void out(int var0, String var1, String var2, String var3) {
   }

   private static void ensureWriterLocked() {
      if (writerThread == null) {
         Thread var0 = new Thread(new Log$1(), "carplay-log-writer");
         var0.setDaemon(true);

         try {
            var0.start();
            writerThread = var0;
         } catch (Throwable var2) {
            writerThread = null;
         }
      }
   }

   private static void writerLoop() {
      while (true) {
         String var0;
         synchronized (LOCK) {
            while (queueCount == 0) {
               try {
                  LOCK.wait();
               } catch (InterruptedException var5) {
               }
            }

            var0 = queue[queueHead];
            queue[queueHead] = null;
            queueHead = (queueHead + 1) % 256;
            queueCount--;
         }

         try {
            writeLine(var0);
         } catch (Throwable var4) {
            resetFileAfterFailure();
         }
      }
   }

   private static void writeLine(String var0) {
      if (var0 != null) {
         PrintStream var1 = logFile();
         if (var1 != null) {
            int var2 = var0.getBytes().length + 1;
            if (fileBytes + var2 > 524288L) {
               rotateFile();
               var1 = logFile();
            }

            if (var1 != null) {
               var1.println(var0);
               fileBytes += var2;
            }
         }
      }
   }

   private static PrintStream logFile() {
      if (!fileTried) {
         fileTried = true;

         try {
            File var0 = new File("/tmp/carplay_java.log");
            if (var0.length() >= 524288L) {
               rotateFiles(var0);
            }

            fileBytes = var0.exists() ? var0.length() : 0L;
            file = new PrintStream(new FileOutputStream("/tmp/carplay_java.log", true), true);
         } catch (Throwable var1) {
            file = null;
         }
      }

      return file;
   }

   private static void rotateFile() {
      if (file != null) {
         try {
            file.close();
         } catch (Throwable var2) {
         }

         file = null;
      }

      try {
         rotateFiles(new File("/tmp/carplay_java.log"));
      } catch (Throwable var1) {
      }

      fileTried = false;
      fileBytes = 0L;
   }

   private static void resetFileAfterFailure() {
      if (file != null) {
         try {
            file.close();
         } catch (Throwable var1) {
         }

         file = null;
      }

      fileTried = false;
      fileBytes = 0L;
   }

   private static void rotateFiles(File var0) {
      File var1 = new File("/tmp/carplay_java.log.1");
      if (var1.exists()) {
         var1.delete();
      }

      if (var0.exists()) {
         var0.renameTo(var1);
      }
   }
}
