/*
 * Decompiled with CFR 0.152.
 */
package com.mibsi.carplay.core;

import com.mibsi.carplay.core.CarPlayApp;
import com.mibsi.carplay.framework.Log;

final class ProjectionRuntimeState {
    private static String checker = "/eso/bin/apps/mhi2q-aa-cluster-display";
    private static volatile boolean ready = checker.length() == 0;
    private static Thread worker;

    private ProjectionRuntimeState() {
    }

    static synchronized void start() {
        if (ready || worker != null && worker.isAlive()) {
            return;
        }
        worker = new Thread(new Runnable(){

            public void run() {
                int n = -1;
                while (!ready) {
                    int n2 = ProjectionRuntimeState.check();
                    if (n2 != n) {
                        Log.w("VIN", "projection authorization status=" + n2);
                        n = n2;
                    }
                    if (n2 == 0) {
                        ready = true;
                        CarPlayApp.onRuntimeReady();
                        return;
                    }
                    try {
                        Thread.sleep(n2 == 56 ? 30000L : 2000L);
                    }
                    catch (InterruptedException interruptedException) {
                        return;
                    }
                }
            }
        }, "MIBSI-VIN");
        worker.setDaemon(true);
        try {
            worker.start();
        }
        catch (Throwable throwable) {
            worker = null;
            Log.w("VIN", "authorization worker unavailable");
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static int check() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[]{checker, "--check-vin"});
            long l = System.currentTimeMillis() + 5000L;
            while (true) {
                if (System.currentTimeMillis() >= l) break;
                try {
                    int n = process.exitValue();
                    return n;
                }
                catch (IllegalThreadStateException illegalThreadStateException) {
                    try {
                        Thread.sleep(50L);
                        continue;
                    }
                    catch (Throwable throwable) {
                    }
                    catch (Throwable throwable) {
                        throw throwable;
                    }
                }
                break;
            }
        }
        finally {
            if (process != null) {
                process.destroy();
                try {
                    process.getInputStream().close();
                }
                catch (Throwable throwable) {}
                try {
                    process.getErrorStream().close();
                }
                catch (Throwable throwable) {}
                try {
                    process.getOutputStream().close();
                }
                catch (Throwable throwable) {}
            }
        }
        return 55;
    }

    static boolean isReady() {
        return ready;
    }
}

