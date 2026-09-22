/*
 * Decompiled with CFR 0.152.
 */
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
    private static int level = Log.resolveInitialLevel();
    private static final String FILE = "/tmp/carplay_java.log";
    private static final String FILE_OLD = "/tmp/carplay_java.log.1";
    private static final long MAX_FILE_BYTES = 524288L;
    private static PrintStream file;
    private static boolean fileTried;
    private static long fileBytes;
    private static final Object LOCK;
    private static final int QUEUE_CAPACITY = 256;
    private static final String[] queue;
    private static int queueHead;
    private static int queueTail;
    private static int queueCount;
    private static Thread writerThread;

    private static int resolveInitialLevel() {
        try {
            int n = 0;
            while (n < VERBOSE_MARKERS.length()) {
                String string;
                int n2 = VERBOSE_MARKERS.indexOf(58, n);
                String string2 = string = n2 < 0 ? VERBOSE_MARKERS.substring(n) : VERBOSE_MARKERS.substring(n, n2);
                if (new File(string).exists()) {
                    return 2;
                }
                if (n2 >= 0) {
                    n = n2 + 1;
                    continue;
                }
                break;
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return 1;
    }

    private Log() {
    }

    public static void setLevel(int n) {
        level = n;
    }

    public static void e(String string, String string2) {
        Log.out(0, "E", string, string2);
    }

    public static void w(String string, String string2) {
        Log.out(1, "W", string, string2);
    }

    public static void i(String string, String string2) {
        Log.out(2, "I", string, string2);
    }

    public static void d(String string, String string2) {
        Log.out(3, "D", string, string2);
    }

    public static void e(String string, String string2, Throwable throwable) {
        Log.out(0, "E", string, string2 + " :: " + throwable);
    }

    public static void w(String string, String string2, Throwable throwable) {
        Log.out(1, "W", string, string2 + " :: " + throwable);
    }

    private static void out(int n, String string, String string2, String string3) {
    }

    private static void ensureWriterLocked() {
        if (writerThread != null) {
            return;
        }
        Thread thread = new Thread(new Runnable(){

            public void run() {
                Log.writerLoop();
            }
        }, "carplay-log-writer");
        thread.setDaemon(true);
        try {
            thread.start();
            writerThread = thread;
        }
        catch (Throwable throwable) {
            writerThread = null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void writerLoop() {
        while (true) {
            String string;
            Object object = LOCK;
            synchronized (object) {
                while (queueCount == 0) {
                    try {
                        LOCK.wait();
                    }
                    catch (InterruptedException interruptedException) {}
                }
                string = queue[queueHead];
                Log.queue[Log.queueHead] = null;
                queueHead = (queueHead + 1) % 256;
                --queueCount;
            }
            try {
                Log.writeLine(string);
                continue;
            }
            catch (Throwable throwable) {
                Log.resetFileAfterFailure();
                continue;
            }
            break;
        }
    }

    private static void writeLine(String string) {
        if (string == null) {
            return;
        }
        PrintStream printStream = Log.logFile();
        if (printStream != null) {
            int n = string.getBytes().length + 1;
            if (fileBytes + (long)n > 524288L) {
                Log.rotateFile();
                printStream = Log.logFile();
            }
            if (printStream != null) {
                printStream.println(string);
                fileBytes += (long)n;
            }
        }
    }

    private static PrintStream logFile() {
        if (!fileTried) {
            fileTried = true;
            try {
                File file = new File(FILE);
                if (file.length() >= 524288L) {
                    Log.rotateFiles(file);
                }
                fileBytes = file.exists() ? file.length() : 0L;
                Log.file = new PrintStream(new FileOutputStream(FILE, true), true);
            }
            catch (Throwable throwable) {
                file = null;
            }
        }
        return file;
    }

    private static void rotateFile() {
        if (file != null) {
            try {
                file.close();
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            file = null;
        }
        try {
            Log.rotateFiles(new File(FILE));
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        fileTried = false;
        fileBytes = 0L;
    }

    private static void resetFileAfterFailure() {
        if (file != null) {
            try {
                file.close();
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            file = null;
        }
        fileTried = false;
        fileBytes = 0L;
    }

    private static void rotateFiles(File file) {
        File file2 = new File(FILE_OLD);
        if (file2.exists()) {
            file2.delete();
        }
        if (file.exists()) {
            file.renameTo(file2);
        }
    }

    static {
        LOCK = new Object();
        queue = new String[256];
    }
}

