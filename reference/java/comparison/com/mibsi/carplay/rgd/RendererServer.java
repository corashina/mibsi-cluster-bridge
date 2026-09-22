/*
 * Decompiled with CFR 0.152.
 */
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
    private volatile StateListener stateListener;
    private long lastBindFailureLogMs;
    private int suppressedBindFailures;
    private int connectionGeneration;
    private final PendingWrite[] writeQueue = new PendingWrite[32];
    private int writeHead;
    private int writeTail;
    private int writeCount;

    public void setStateListener(StateListener stateListener) {
        this.stateListener = stateListener;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean connect() {
        Object object = this.lock;
        synchronized (object) {
            if (this.server != null && !this.server.isClosed()) {
                return true;
            }
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket();
                serverSocket.setReuseAddress(true);
                serverSocket.bind(new InetSocketAddress(HOST, 19800));
                this.server = serverSocket;
                this.running = true;
                Log.i(TAG, "listening on 127.0.0.1:19800");
            }
            catch (IOException iOException) {
                if (serverSocket != null) {
                    try {
                        serverSocket.close();
                    }
                    catch (IOException iOException2) {
                        // empty catch block
                    }
                }
                this.logBindFailure(iOException);
                return false;
            }
            this.acceptThread = new Thread(new Runnable(){

                public void run() {
                    RendererServer.this.acceptLoop();
                }
            }, "RendererServer-Accept");
            this.acceptThread.setDaemon(true);
            this.acceptThread.start();
            this.writerThread = new Thread(new Runnable(){

                public void run() {
                    RendererServer.this.writerLoop();
                }
            }, "RendererServer-Write");
            this.writerThread.setDaemon(true);
            this.writerThread.start();
        }
        return true;
    }

    private void logBindFailure(IOException iOException) {
        long l = System.currentTimeMillis();
        if (this.lastBindFailureLogMs == 0L || l - this.lastBindFailureLogMs >= 10000L) {
            String string = this.suppressedBindFailures > 0 ? " (" + this.suppressedBindFailures + " repeats suppressed)" : "";
            Log.w(TAG, "bind failed: " + iOException.getMessage() + string);
            this.lastBindFailureLogMs = l;
            this.suppressedBindFailures = 0;
        } else {
            ++this.suppressedBindFailures;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void acceptLoop() {
        ServerSocket serverSocket;
        Object object = this.lock;
        synchronized (object) {
            serverSocket = this.server;
        }
        while (this.running && serverSocket != null && !serverSocket.isClosed()) {
            Object object2;
            try {
                object = serverSocket.accept();
                try {
                    ((Socket)object).setTcpNoDelay(true);
                    ((Socket)object).setSoTimeout(5000);
                    object2 = ((Socket)object).getOutputStream();
                    InputStream inputStream = ((Socket)object).getInputStream();
                    Object object3 = this.lock;
                    synchronized (object3) {
                        if (this.sock != null) {
                            try {
                                this.sock.close();
                            }
                            catch (Exception exception) {
                                // empty catch block
                            }
                        }
                        this.sock = object;
                        this.out = object2;
                        ++this.connectionGeneration;
                        this.clearWriteQueue();
                        this.everConnected = true;
                        this.rendererReady = false;
                        this.frameReady = false;
                        this.clearPending = false;
                        this.menuPresentationToken = 0;
                        this.lock.notifyAll();
                    }
                    Log.i(TAG, "renderer connected from " + ((Socket)object).getInetAddress() + ":" + ((Socket)object).getPort());
                    this.notifyStateChanged("connected");
                    object3 = new Thread(new Runnable((Socket)object, inputStream){
                        private final /* synthetic */ Socket val$s;
                        private final /* synthetic */ InputStream val$i;
                        {
                            this.val$s = socket;
                            this.val$i = inputStream;
                        }

                        public void run() {
                            RendererServer.this.readerLoop(this.val$s, this.val$i);
                        }
                    }, "RendererServer-Read");
                    ((Thread)object3).setDaemon(true);
                    ((Thread)object3).start();
                }
                catch (IOException iOException) {
                    try {
                        ((Socket)object).close();
                    }
                    catch (Exception exception) {}
                }
            }
            catch (IOException iOException) {
                if (this.running) {
                    // empty if block
                }
                object2 = this.lock;
                synchronized (object2) {
                    if (this.server == null || this.server.isClosed()) {
                        break;
                    }
                }
            }
        }
        Log.i(TAG, "accept thread exiting");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void writerLoop() {
        while (true) {
            PendingWrite pendingWrite;
            Object object = this.writeLock;
            synchronized (object) {
                while (this.running && this.writeCount == 0) {
                    try {
                        this.writeLock.wait();
                    }
                    catch (InterruptedException interruptedException) {}
                }
                if (!this.running) {
                    break;
                }
                pendingWrite = this.writeQueue[this.writeHead];
                this.writeQueue[this.writeHead] = null;
                this.writeHead = (this.writeHead + 1) % 32;
                --this.writeCount;
            }
            Object object2 = this.lock;
            synchronized (object2) {
                if (this.out == null || pendingWrite == null || pendingWrite.generation != this.connectionGeneration) {
                    continue;
                }
                object = this.out;
            }
            try {
                ((OutputStream)object).write(pendingWrite.packet);
                ((OutputStream)object).flush();
            }
            catch (Exception exception) {
                Log.w(TAG, "Writer failed: " + exception.getMessage());
                boolean bl = false;
                Object object3 = this.lock;
                synchronized (object3) {
                    if (this.out == object) {
                        this.closeClientLocked();
                        bl = true;
                    }
                }
                if (!bl) continue;
                this.notifyStateChanged("send-failed");
            }
        }
        Log.i(TAG, "writer thread exiting");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void clearWriteQueue() {
        Object object = this.writeLock;
        synchronized (object) {
            for (int i = 0; i < 32; ++i) {
                this.writeQueue[i] = null;
            }
            this.writeHead = 0;
            this.writeTail = 0;
            this.writeCount = 0;
            this.writeLock.notifyAll();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void readerLoop(Socket socket, InputStream inputStream) {
        byte[] byArray = new byte[48];
        try {
            while (this.running) {
                int n;
                for (int i = 0; i < 48; i += n) {
                    n = inputStream.read(byArray, i, 48 - i);
                    if (n >= 0) continue;
                    throw new IOException("EOF");
                }
                this.handleRendererEvent(socket, byArray[0]);
            }
        }
        catch (IOException iOException) {
            if (this.running) {
                Log.i(TAG, "reader exit: " + iOException.getClass().getName() + (iOException.getMessage() == null ? "" : " " + iOException.getMessage()));
            }
        }
        finally {
            boolean bl = false;
            Object object = this.lock;
            synchronized (object) {
                if (this.sock == socket) {
                    this.closeClientLocked();
                    bl = true;
                }
            }
            if (bl) {
                this.notifyStateChanged("disconnected");
            }
        }
    }

    private void notifyStateChanged(String string) {
        StateListener stateListener = this.stateListener;
        if (stateListener == null) {
            return;
        }
        try {
            stateListener.onRendererStateChanged(string);
        }
        catch (Throwable throwable) {
            Log.w(TAG, "state listener failed: " + throwable.getMessage());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void handleRendererEvent(Socket socket, byte by) {
        boolean bl = false;
        String string = null;
        Object object = this.lock;
        synchronized (object) {
            if (this.sock != socket) {
                return;
            }
            if (by == -127) {
                bl = !this.rendererReady;
                this.rendererReady = true;
                this.lock.notifyAll();
                string = "ready";
            } else if (by == -126) {
                this.rendererReady = true;
                if (!this.clearPending) {
                    bl = !this.frameReady;
                    this.frameReady = true;
                    this.lock.notifyAll();
                    string = "frame-ready";
                }
            } else if (by == -125) {
                bl = this.clearPending || this.frameReady;
                this.clearPending = false;
                this.frameReady = false;
                this.lock.notifyAll();
                string = "frame-cleared";
            }
        }
        if (bl) {
            Log.i(TAG, "renderer " + string);
            this.notifyStateChanged(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void disconnectClient() {
        boolean bl;
        Object object = this.lock;
        synchronized (object) {
            bl = this.sock != null || this.rendererReady || this.frameReady || this.clearPending;
            this.closeClientLocked();
        }
        if (bl) {
            this.notifyStateChanged("disconnect-request");
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void dispose() {
        Object object = this.lock;
        synchronized (object) {
            this.running = false;
            this.everConnected = false;
            this.closeClientLocked();
            if (this.server != null) {
                try {
                    this.server.close();
                }
                catch (Exception exception) {
                    // empty catch block
                }
                this.server = null;
            }
        }
        this.clearWriteQueue();
        if (this.acceptThread != null) {
            try {
                this.acceptThread.join(500L);
            }
            catch (InterruptedException interruptedException) {
                // empty catch block
            }
            this.acceptThread = null;
        }
        object = this.writeLock;
        synchronized (object) {
            this.writeLock.notifyAll();
        }
        if (this.writerThread != null) {
            try {
                this.writerThread.join(500L);
            }
            catch (InterruptedException interruptedException) {
                // empty catch block
            }
            this.writerThread = null;
        }
        this.notifyStateChanged("disposed");
    }

    private void closeClientLocked() {
        if (this.out != null) {
            try {
                this.out.close();
            }
            catch (Exception exception) {
                // empty catch block
            }
            this.out = null;
        }
        if (this.sock != null) {
            try {
                this.sock.close();
            }
            catch (Exception exception) {
                // empty catch block
            }
            this.sock = null;
        }
        ++this.connectionGeneration;
        this.clearWriteQueue();
        this.rendererReady = false;
        this.frameReady = false;
        this.clearPending = false;
        this.menuPresentationToken = 0;
        this.lock.notifyAll();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean isConnected() {
        Object object = this.lock;
        synchronized (object) {
            return this.sock != null && !this.sock.isClosed() && this.out != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean isReady() {
        Object object = this.lock;
        synchronized (object) {
            return this.sock != null && !this.sock.isClosed() && this.out != null && this.rendererReady;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean isFrameReady() {
        Object object = this.lock;
        synchronized (object) {
            return this.sock != null && !this.sock.isClosed() && this.out != null && this.frameReady && !this.clearPending;
        }
    }

    public boolean everConnected() {
        return this.everConnected;
    }

    public boolean sendManeuver(int n, int n2, int n3, int n4, int[] nArray, int n5, int n6, int n7, int n8) {
        return this.sendManeuver(n, n2, n3, n4, nArray, null, 0, false, n5, n6, n7, n8);
    }

    public boolean sendManeuver(int n, int n2, int n3, int n4, int[] nArray, byte[] byArray, int n5, boolean bl, int n6, int n7, int n8, int n9) {
        int n10;
        byte[] byArray2 = new byte[48];
        byArray2[0] = 1;
        int n11 = 0;
        if (n7 > 0) {
            n11 = (byte)(n11 | 2);
        }
        if (n8 >= 0) {
            n11 = (byte)(n11 | 1);
        }
        if (bl) {
            n11 = (byte)(n11 | 4);
        }
        byArray2[1] = n11;
        byArray2[2] = (byte)(n & 0xFF);
        byArray2[3] = (byte)n2;
        byArray2[4] = (byte)(n3 >> 8 & 0xFF);
        byArray2[5] = (byte)(n3 & 0xFF);
        byArray2[6] = (byte)(n4 & 0xFF);
        int n12 = 0;
        if (nArray != null && (n12 = nArray.length) > 16) {
            n12 = 16;
        }
        byArray2[7] = (byte)(n12 & 0xFF);
        for (int i = 0; i < n12 && (n10 = 8 + i * 2) + 1 < 48; ++i) {
            int n13 = nArray[i];
            byArray2[n10] = (byte)(n13 >> 8 & 0xFF);
            byArray2[n10 + 1] = (byte)(n13 & 0xFF);
        }
        byArray2[40] = (byte)(n9 >> 24 & 0xFF);
        byArray2[41] = (byte)(n9 >> 16 & 0xFF);
        byArray2[42] = (byte)(n9 >> 8 & 0xFF);
        byArray2[43] = (byte)(n9 & 0xFF);
        if (n5 < 0) {
            n5 = 0;
        }
        if (n5 > 99) {
            n5 = 99;
        }
        byArray2[44] = (byte)(n5 & 0xFF);
        if (n8 >= 0) {
            byArray2[45] = (byte)(n8 & 0xFF);
        }
        if (n7 > 0) {
            byArray2[46] = (byte)(n6 & 0xFF);
            byArray2[47] = (byte)(n7 & 0xFF);
        }
        byte[] byArray3 = RendererServer.buildLanePacket(byArray, n9);
        return this.sendPackets(new byte[][]{byArray3, byArray2}, false);
    }

    private static byte[] buildLanePacket(byte[] byArray, int n) {
        byte[] byArray2 = new byte[48];
        byArray2[0] = 10;
        byArray2[2] = (byte)(n >> 24 & 0xFF);
        byArray2[3] = (byte)(n >> 16 & 0xFF);
        byArray2[4] = (byte)(n >> 8 & 0xFF);
        byArray2[5] = (byte)(n & 0xFF);
        byArray2[7] = 105;
        if (byArray == null || byArray.length == 0) {
            return byArray2;
        }
        int n2 = byArray[0] & 0xFF;
        int n3 = RendererServer.validateLaneGuidance(byArray, n2);
        if (n3 == 0) {
            return byArray2;
        }
        byArray2[6] = (byte)n2;
        System.arraycopy(byArray, 1, byArray2, 8, n3 - 1);
        byArray2[40] = (byte)(n3 - 1);
        return byArray2;
    }

    static byte[] lanePacketForTest(byte[] byArray, int n) {
        return RendererServer.buildLanePacket(byArray, n);
    }

    private static int validateLaneGuidance(byte[] byArray, int n) {
        if (n < 1 || n > 8 || byArray.length > 33) {
            return 0;
        }
        int n2 = 1;
        for (int i = 0; i < n; ++i) {
            int n3;
            if (n2 >= byArray.length) {
                return 0;
            }
            if ((n3 = byArray[n2++] & 0xFF) < 1 || n3 > 3 || n2 + n3 > byArray.length) {
                return 0;
            }
            int n4 = 0;
            for (int j = 0; j < n3; ++j) {
                int n5;
                if (((n5 = byArray[n2++] & 0xFF) & 0x70) != 0 || (n5 & 0xF) > 9) {
                    return 0;
                }
                if ((n5 & 0x80) == 0 || ++n4 <= 1) continue;
                return 0;
            }
        }
        return n2 == byArray.length ? n2 : 0;
    }

    public boolean sendBargraph(int n, int n2) {
        byte[] byArray = new byte[48];
        byArray[0] = 6;
        byArray[2] = (byte)(n & 0xFF);
        byArray[3] = (byte)(n2 & 0xFF);
        return this.sendPacket(byArray);
    }

    public boolean sendClear() {
        byte[] byArray = new byte[48];
        byArray[0] = 7;
        return this.sendPacket(byArray, true);
    }

    public boolean sendPresented(int n, int n2, int n3, int n4, int n5) {
        byte[] byArray = new byte[48];
        byArray[0] = 8;
        byArray[2] = (byte)(n >> 24 & 0xFF);
        byArray[3] = (byte)(n >> 16 & 0xFF);
        byArray[4] = (byte)(n >> 8 & 0xFF);
        byArray[5] = (byte)(n & 0xFF);
        RendererServer.putU16(byArray, 6, n2);
        RendererServer.putU16(byArray, 8, n3);
        RendererServer.putU16(byArray, 10, n4);
        RendererServer.putU16(byArray, 12, n5);
        return this.sendPacket(byArray);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean sendVcMenu(boolean bl, int n, int n2, int n3) {
        boolean bl2;
        byte[] byArray = new byte[48];
        byArray[0] = 9;
        byArray[2] = (byte)(bl ? 1 : 0);
        byArray[3] = (byte)(n & 0xFF);
        byArray[4] = (byte)(n2 & 0xFF);
        byArray[5] = 0;
        byArray[6] = (byte)(n3 >> 24 & 0xFF);
        byArray[7] = (byte)(n3 >> 16 & 0xFF);
        byArray[8] = (byte)(n3 >> 8 & 0xFF);
        byArray[9] = (byte)(n3 & 0xFF);
        Object object = this.lock;
        synchronized (object) {
            bl2 = !bl || n3 != this.menuPresentationToken;
            this.menuPresentationToken = bl ? n3 : 0;
        }
        return this.sendPacket(byArray, bl2);
    }

    private static void putU16(byte[] byArray, int n, int n2) {
        if (n2 < 0) {
            n2 = 0;
        }
        if (n2 > 65535) {
            n2 = 65535;
        }
        byArray[n] = (byte)(n2 >> 8 & 0xFF);
        byArray[n + 1] = (byte)(n2 & 0xFF);
    }

    private boolean sendPacket(byte[] byArray) {
        return this.sendPacket(byArray, false);
    }

    private boolean sendPacket(byte[] byArray, boolean bl) {
        return this.sendPackets(new byte[][]{byArray}, bl);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean sendPackets(byte[][] byArray, boolean bl) {
        int n;
        Object object = this.lock;
        synchronized (object) {
            if (this.out == null) {
                return false;
            }
            n = this.connectionGeneration;
            if (bl) {
                this.frameReady = false;
                this.clearPending = true;
                this.lock.notifyAll();
            }
        }
        object = this.writeLock;
        synchronized (object) {
            for (int i = 0; i < byArray.length; ++i) {
                PendingWrite pendingWrite = new PendingWrite();
                pendingWrite.packet = byArray[i];
                pendingWrite.generation = n;
                if (this.writeCount == 32) {
                    this.writeQueue[this.writeHead] = null;
                    this.writeHead = (this.writeHead + 1) % 32;
                    --this.writeCount;
                }
                this.writeQueue[this.writeTail] = pendingWrite;
                this.writeTail = (this.writeTail + 1) % 32;
                ++this.writeCount;
            }
            this.writeLock.notifyAll();
        }
        return true;
    }

    public static interface StateListener {
        public void onRendererStateChanged(String var1);
    }

    private static final class PendingWrite {
        byte[] packet;
        int generation;

        private PendingWrite() {
        }
    }
}

