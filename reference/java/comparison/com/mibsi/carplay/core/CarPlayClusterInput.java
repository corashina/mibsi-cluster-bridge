/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.audi.atip.interapp.combi.bap.navi.CombiBAPServiceNavi
 */
package com.mibsi.carplay.core;

import com.mibsi.carplay.core.CarPlayApp;
import com.mibsi.carplay.core.Mhi2qAaClusterComposition;
import com.mibsi.carplay.framework.Log;
import com.mibsi.carplay.rgd.Mhi2qAaNavProxy;
import de.audi.atip.interapp.combi.bap.navi.CombiBAPServiceNavi;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public final class CarPlayClusterInput
implements Runnable {
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

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static int onMapScale(int n, CombiBAPServiceNavi combiBAPServiceNavi) {
        boolean bl = CarPlayApp.isActive();
        boolean bl2 = bl && Mhi2qAaNavProxy.isVcSettingsMenuRequested();
        int n2 = CarPlayClusterInput.mapScaleActionForTest(bl, CarPlayApp.isCarPlayActive(), Mhi2qAaClusterComposition.isClusterProjectionEnabled(), n);
        if (n2 != 0 || bl2 && n != 0) {
            Mhi2qAaNavProxy.acknowledgeProjectedMapScaleInput(combiBAPServiceNavi);
        }
        if (bl2 && CarPlayApp.moveVcSettingsSelection(n)) {
            return 0;
        }
        if (n2 == 0) {
            return n;
        }
        if (n2 == 1) {
            return 0;
        }
        Object object = LOCK;
        synchronized (object) {
            pendingSteps = CarPlayClusterInput.addBoundedForTest(pendingSteps, n);
            CarPlayClusterInput.ensureWorkerLocked();
            if (worker == null) {
                pendingSteps = 0;
                return 0;
            }
            LOCK.notifyAll();
        }
        return 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void onDeactivate() {
        Object object = LOCK;
        synchronized (object) {
            pendingSteps = 0;
        }
    }

    private static void ensureWorkerLocked() {
        if (worker != null && worker.isAlive()) {
            return;
        }
        Thread thread = new Thread(new CarPlayClusterInput(), "MHI2Q-CarPlay-Cluster-Input");
        thread.setDaemon(true);
        try {
            thread.start();
            worker = thread;
        }
        catch (Throwable throwable) {
            worker = null;
            Log.w("CarPlayClusterInput", "worker start failed: " + throwable);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void run() {
        DatagramSocket datagramSocket = null;
        try {
            try {
                datagramSocket = new DatagramSocket();
                InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
                while (true) {
                    int n;
                    Object object = LOCK;
                    synchronized (object) {
                        while (pendingSteps == 0) {
                            try {
                                LOCK.wait();
                            }
                            catch (InterruptedException interruptedException) {}
                        }
                        n = CarPlayClusterInput.takeChunkForTest(pendingSteps);
                        pendingSteps -= n;
                    }
                    if (!CarPlayApp.isCarPlayActive()) continue;
                    object = CarPlayClusterInput.encodePacketForTest(n);
                    try {
                        datagramSocket.send(new DatagramPacket((byte[])object, ((Object)object).length, inetAddress, 15558));
                    }
                    catch (Throwable throwable) {
                        Log.w("CarPlayClusterInput", "zoom send failed: " + throwable);
                    }
                }
            }
            catch (Throwable throwable) {
                Log.w("CarPlayClusterInput", "transport failed: " + throwable);
                if (datagramSocket != null) {
                    datagramSocket.close();
                }
                Object object = LOCK;
                synchronized (object) {
                    if (Thread.currentThread() == worker) {
                        worker = null;
                    }
                    pendingSteps = 0;
                }
            }
        }
        catch (Throwable throwable) {
            if (datagramSocket != null) {
                datagramSocket.close();
            }
            Object object = LOCK;
            synchronized (object) {
                if (Thread.currentThread() == worker) {
                    worker = null;
                }
                pendingSteps = 0;
            }
            throw throwable;
        }
    }

    static int mapScaleActionForTest(boolean bl, boolean bl2, boolean bl3, int n) {
        if (!bl || !bl3 || n == 0) {
            return 0;
        }
        return bl2 ? 2 : 1;
    }

    static int addBoundedForTest(int n, int n2) {
        long l = (long)n + (long)n2;
        if (l > 1024L) {
            return 1024;
        }
        if (l < -1024L) {
            return -1024;
        }
        return (int)l;
    }

    static int takeChunkForTest(int n) {
        if (n > 127) {
            return 127;
        }
        if (n < -127) {
            return -127;
        }
        return n;
    }

    static byte[] encodePacketForTest(int n) {
        byte[] byArray = new byte[]{67, 80, 90, 49, (byte)(n >>> 24), (byte)(n >>> 16), (byte)(n >>> 8), (byte)n};
        return byArray;
    }
}

