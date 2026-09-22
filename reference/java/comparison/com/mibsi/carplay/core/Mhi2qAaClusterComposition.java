/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.audi.app.terminalmode.IContext
 *  de.audi.atip.hmi.HMIService
 *  de.audi.atip.hmi.HMITerminal
 *  de.audi.atip.hmi.model.HMIModel
 *  de.audi.atip.hmi.modelaccess.ChoiceModelGUI
 *  de.audi.atip.hmi.view.IDisplayManager
 *  de.audi.tghu.hmi.evo.HMITerminalEvo
 *  de.esolutions.hmi.widgets.audi.base.HMITerminalImpl
 *  de.esolutions.hmi.widgets.audi.base.Layout
 *  org.dsi.ifc.displaymanagement.DSIDisplayManagement
 *  org.dsi.ifc.displaymanagement.DisplayContext
 *  org.osgi.framework.BundleContext
 *  org.osgi.framework.ServiceReference
 */
package com.mibsi.carplay.core;

import com.mibsi.carplay.core.OemClusterMapRenderGate;
import com.mibsi.carplay.framework.Log;
import de.audi.app.terminalmode.IContext;
import de.audi.atip.hmi.HMIService;
import de.audi.atip.hmi.HMITerminal;
import de.audi.atip.hmi.model.HMIModel;
import de.audi.atip.hmi.modelaccess.ChoiceModelGUI;
import de.audi.atip.hmi.view.IDisplayManager;
import de.audi.tghu.hmi.evo.HMITerminalEvo;
import de.esolutions.hmi.widgets.audi.base.HMITerminalImpl;
import de.esolutions.hmi.widgets.audi.base.Layout;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.dsi.ifc.displaymanagement.DSIDisplayManagement;
import org.dsi.ifc.displaymanagement.DisplayContext;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public final class Mhi2qAaClusterComposition
implements Runnable {
    public static final int TERMINAL_CLUSTER = 1;
    public static final int CONTEXT_AA = 70;
    private static final int DISPLAYABLE_TURN = 98;
    private static final int BACKING_IN_TUBE = 101;
    private static final int BACKING_POPUP = 102;
    private static final int VIEW_SMALL = 1;
    private static final long RECONCILE_MS = 100L;
    private static final long REASSERT_RETRY_MS = 1000L;
    private static final long TURN_DISPLAY_SHOW_DELAY_MS = 600L;
    private static final long TURN_DISPLAY_HIDE_DELAY_MS = 500L;
    private static final String NATIVE_REASSERT_REQUEST = "/tmp/mhi2q_aa_context70.request";
    private static final String UI_VIEW_STATE = "/tmp/mhi2q_aa_ui_view.state";
    private static final int UI_VIEW_LARGE = 76;
    private static final int UI_VIEW_CLASSIC_SMALL = 67;
    private static final int UI_VIEW_SPORT_SMALL = 83;
    private static final int LC_IN_TUBE_X = 58;
    private static final int LC_IN_TUBE_Y = 59;
    private static final int LC_POPUP_X = 60;
    private static final int LC_POPUP_Y = 61;
    private static final int LC_POPUP_CROP_X = 118;
    private static final int LC_POPUP_CROP_Y = 119;
    private static final int LC_POPUP_CROP_W = 120;
    private static final int LC_POPUP_CROP_H = 121;
    private static final int LC_IN_TUBE_CROP_X = 122;
    private static final int LC_IN_TUBE_CROP_Y = 123;
    private static final int LC_IN_TUBE_CROP_W = 124;
    private static final int LC_IN_TUBE_CROP_H = 125;
    private static final Object LOCK = new Object();
    private static IContext context;
    private static IDisplayManager displayManager;
    private static Thread worker;
    private static volatile boolean androidAutoActive;
    private static volatile boolean nativeContextReady;
    private static volatile boolean ownershipRequested;
    private static volatile boolean owningCluster;
    private static volatile boolean clusterProjectionEnabled;
    private static volatile boolean turnCardVisible;
    private static volatile int turnCardToken;
    private static volatile PresentationListener presentationListener;
    private static volatile boolean menuVisible;
    private static volatile int menuToken;
    private static volatile int menuRevision;
    private static volatile int viewSize;
    private static volatile boolean viewSizeKnown;
    private static int oemMenuContext;
    private static int[] oemMenuLayers;
    private static volatile int skin;
    private static int appliedViewSize;
    private static int appliedSkin;
    private static int appliedGeometryRevision;
    private static volatile int appliedTurnCardRevision;
    private static volatile int appliedTurnCardToken;
    private static int notifiedTurnCardRevision;
    private static int notifiedTurnCardToken;
    private static int notifiedSourceX;
    private static int notifiedSourceY;
    private static int notifiedSourceWidth;
    private static int notifiedSourceHeight;
    private static volatile boolean appliedTurnCardVisible;
    private static volatile boolean appliedMenuVisible;
    private static volatile int appliedMenuRevision;
    private static volatile int appliedMenuToken;
    private static int notifiedMenuRevision;
    private static int notifiedMenuToken;
    private static volatile int loggedContext;
    private static boolean reassertPending;
    private static long lastReassertRequestMs;
    private static int publishedUiViewState;
    private static int geometryRevision;
    private static long turnDisplayShowDeadlineMs;
    private static long turnDisplayHideDeadlineMs;
    private static volatile int turnCardRevision;
    private static int inTubeX;
    private static int inTubeY;
    private static int inTubeCropX;
    private static int inTubeCropY;
    private static int inTubeCropW;
    private static int inTubeCropH;
    private static int popupX;
    private static int popupY;
    private static int popupCropX;
    private static int popupCropY;
    private static int popupCropW;
    private static int popupCropH;
    private static String layoutName;

    private Mhi2qAaClusterComposition() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void setPresentationListener(PresentationListener presentationListener) {
        Object object = LOCK;
        synchronized (object) {
            Mhi2qAaClusterComposition.presentationListener = presentationListener;
            notifiedTurnCardRevision = -1;
            notifiedMenuRevision = -1;
            notifiedSourceX = -1;
            LOCK.notifyAll();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void prepare(Object object) {
        if (!(object instanceof IContext)) {
            return;
        }
        Object object2 = LOCK;
        synchronized (object2) {
            context = (IContext)object;
            Mhi2qAaClusterComposition.ensureWorkerLocked();
            LOCK.notifyAll();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void onActivate(Object object) {
        Mhi2qAaClusterComposition.prepare(object);
        Object object2 = LOCK;
        synchronized (object2) {
            androidAutoActive = true;
            clusterProjectionEnabled = true;
            nativeContextReady = false;
            reassertPending = false;
            Mhi2qAaClusterComposition.ensureWorkerLocked();
            LOCK.notifyAll();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void onDeactivate() {
        Object object = LOCK;
        synchronized (object) {
            androidAutoActive = false;
            nativeContextReady = false;
            reassertPending = false;
            ownershipRequested = false;
            clusterProjectionEnabled = true;
            if (turnCardVisible || turnCardToken != 0) {
                turnCardVisible = false;
                turnCardToken = 0;
                ++turnCardRevision;
            }
            turnDisplayShowDeadlineMs = 0L;
            if (menuVisible || menuToken != 0) {
                menuVisible = false;
                menuToken = 0;
                ++menuRevision;
            }
            try {
                new File(NATIVE_REASSERT_REQUEST).delete();
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            LOCK.notifyAll();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void setTurnCardVisible(boolean bl, int n) {
        Object object = LOCK;
        synchronized (object) {
            int n2;
            int n3 = n2 = bl ? n : 0;
            if (turnCardVisible == bl && turnCardToken == n2) {
                return;
            }
            boolean bl2 = bl && !turnCardVisible && !appliedTurnCardVisible;
            turnDisplayShowDeadlineMs = Mhi2qAaClusterComposition.nextTurnCardShowDeadline(bl2, bl, turnDisplayShowDeadlineMs, System.currentTimeMillis());
            turnCardVisible = bl;
            turnCardToken = n2;
            ++turnCardRevision;
            if (bl2) {
                Log.w("AAComposition", "turn overlay open delayed 600ms after BAP");
            }
            LOCK.notifyAll();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean isTurnCardVisibilityApplied(boolean bl) {
        Object object = LOCK;
        synchronized (object) {
            return turnCardVisible == bl && appliedTurnCardVisible == bl && appliedTurnCardRevision == turnCardRevision && appliedTurnCardToken == (bl ? turnCardToken : 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void setVcMenuVisible(boolean bl, int n) {
        Object object = LOCK;
        synchronized (object) {
            int n2;
            bl = bl && Mhi2qAaClusterComposition.isLargeView();
            int n3 = n2 = bl ? n : 0;
            if (menuVisible == bl && menuToken == n2) {
                return;
            }
            menuVisible = bl;
            menuToken = n2;
            ++menuRevision;
            LOCK.notifyAll();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean isVcMenuVisibilityApplied(boolean bl) {
        Object object = LOCK;
        synchronized (object) {
            return menuVisible == bl && appliedMenuVisible == bl && appliedMenuRevision == menuRevision && appliedMenuToken == (bl ? menuToken : 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void setClusterProjectionEnabled(boolean bl) {
        Object object = LOCK;
        synchronized (object) {
            if (clusterProjectionEnabled == bl) {
                return;
            }
            clusterProjectionEnabled = bl;
            reassertPending = false;
            LOCK.notifyAll();
        }
    }

    public static boolean isClusterProjectionEnabled() {
        return clusterProjectionEnabled;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void onViewSize(int n) {
        int n2 = n == 1 ? 1 : 2;
        Object object = LOCK;
        synchronized (object) {
            boolean bl = viewSizeKnown = n == 1 || n == 2;
            if (viewSize == n2) {
                return;
            }
            viewSize = n2;
            LOCK.notifyAll();
        }
    }

    public static boolean isLargeView() {
        return viewSizeKnown && viewSize == 2;
    }

    public static boolean isProjectionReleased() {
        return !owningCluster && !ownershipRequested && loggedContext != Integer.MIN_VALUE && loggedContext != 70;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void onSkin(int n) {
        Object object = LOCK;
        synchronized (object) {
            if (skin == n) {
                return;
            }
            skin = n;
            LOCK.notifyAll();
        }
    }

    public static boolean shouldBlockStockContextWrite() {
        return Mhi2qAaClusterComposition.wantsCustomContext();
    }

    public static boolean shouldPinStockClusterContext() {
        return Mhi2qAaClusterComposition.wantsCustomContext();
    }

    private static boolean wantsCustomContext() {
        return androidAutoActive && clusterProjectionEnabled;
    }

    public static boolean isContextWriterThread() {
        return Thread.currentThread() == worker;
    }

    public static boolean isOwningCluster() {
        return owningCluster;
    }

    private static void ensureWorkerLocked() {
        if (worker != null && worker.isAlive()) {
            return;
        }
        Thread thread = new Thread(new Mhi2qAaClusterComposition(), "MHI2Q-AA-VC-Composition");
        thread.setDaemon(true);
        try {
            thread.start();
            worker = thread;
        }
        catch (Throwable throwable) {
            worker = null;
            Log.w("AAComposition", "worker start failed: " + throwable);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void run() {
        while (true) {
            try {
                Mhi2qAaClusterComposition.reconcile();
            }
            catch (Throwable throwable) {
                Log.w("AAComposition", "reconcile failed: " + throwable);
            }
            Object object = LOCK;
            synchronized (object) {
                try {
                    LOCK.wait(100L);
                }
                catch (InterruptedException interruptedException) {
                    // empty catch block
                }
            }
        }
    }

    private static void reconcile() {
        boolean bl;
        boolean bl2 = androidAutoActive;
        boolean bl3 = Mhi2qAaClusterComposition.wantsCustomContext();
        IDisplayManager iDisplayManager = Mhi2qAaClusterComposition.resolveDisplayManager();
        if (iDisplayManager == null) {
            OemClusterMapRenderGate.request(false);
            return;
        }
        Mhi2qAaClusterComposition.refreshOemViewState();
        if (!Mhi2qAaClusterComposition.isLargeView()) {
            Mhi2qAaClusterComposition.setVcMenuVisible(false, 0);
        }
        int n = -1;
        try {
            n = iDisplayManager.getCurrentContextID(1);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        boolean bl4 = OemClusterMapRenderGate.request(bl3 && n == 70);
        Mhi2qAaClusterComposition.publishUiViewState(n);
        if (n != loggedContext) {
            loggedContext = n;
            Log.w("AAComposition", "active=" + bl2 + " native-context=" + n);
        }
        if (bl2 && n == 70) {
            nativeContextReady = true;
            if (reassertPending) {
                reassertPending = false;
                appliedViewSize = -1;
                appliedSkin = -1;
                appliedGeometryRevision = -1;
                Log.w("AAComposition", "native context 70 restored");
            }
        }
        if (!bl3) {
            ownershipRequested = false;
            if (owningCluster && n >= 0 && n != 70) {
                owningCluster = false;
                reassertPending = false;
                Log.w("AAComposition", "OEM cluster map restored");
            }
            if (n == 70) {
                Mhi2qAaClusterComposition.setVcMenuVisible(false, 0);
                Mhi2qAaClusterComposition.applyLayers(iDisplayManager);
                if (!Mhi2qAaClusterComposition.isTurnCardVisibilityApplied(false) || !Mhi2qAaClusterComposition.isVcMenuVisibilityApplied(false)) {
                    return;
                }
                if (!bl4) {
                    return;
                }
                long l = System.currentTimeMillis();
                if (!reassertPending || l - lastReassertRequestMs >= 1000L) {
                    reassertPending = Mhi2qAaClusterComposition.requestNativeContext(79);
                    lastReassertRequestMs = l;
                }
            } else {
                Mhi2qAaClusterComposition.applyOemMenu(iDisplayManager, n);
            }
            return;
        }
        if (!Mhi2qAaClusterComposition.restoreOemMenuContext()) {
            return;
        }
        if (n != 70) {
            ownershipRequested = nativeContextReady;
            if (nativeContextReady) {
                owningCluster = true;
            }
            long l = System.currentTimeMillis();
            if (!reassertPending || l - lastReassertRequestMs >= 1000L) {
                reassertPending = Mhi2qAaClusterComposition.requestNativeContext(67);
                lastReassertRequestMs = l;
                Log.w("AAComposition", "context drift " + n + " -> native reassert " + 70 + " requested=" + reassertPending);
            }
            if (nativeContextReady) {
                Mhi2qAaClusterComposition.applyLayers(iDisplayManager);
            }
            return;
        }
        boolean bl5 = bl = n == 70;
        if (bl) {
            ownershipRequested = true;
            if (!owningCluster) {
                owningCluster = true;
                appliedViewSize = -1;
                appliedSkin = -1;
                appliedGeometryRevision = -1;
                Log.w("AAComposition", "native context 70 acquired: {98,101,102,3}");
            }
            Mhi2qAaClusterComposition.applyLayers(iDisplayManager);
        } else if (!bl3 && owningCluster) {
            ownershipRequested = false;
            Mhi2qAaClusterComposition.hideLayers(iDisplayManager);
            owningCluster = false;
            appliedViewSize = -1;
            appliedSkin = -1;
            appliedGeometryRevision = -1;
            Log.w("AAComposition", "native context 70 released");
        } else {
            ownershipRequested = false;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void applyOemMenu(IDisplayManager iDisplayManager, int n) {
        if (n < 0 || n == 70) {
            return;
        }
        boolean bl = androidAutoActive && menuVisible && Mhi2qAaClusterComposition.isLargeView();
        int n2 = menuRevision;
        int n3 = menuToken;
        if (!bl && !appliedMenuVisible && oemMenuContext < 0) {
            appliedMenuRevision = n2;
            appliedMenuToken = 0;
            appliedTurnCardVisible = false;
            appliedTurnCardRevision = turnCardRevision;
            appliedTurnCardToken = 0;
            Mhi2qAaClusterComposition.notifyVcMenuHidden(n2);
            return;
        }
        if (oemMenuContext != n && !Mhi2qAaClusterComposition.restoreOemMenuContext()) {
            return;
        }
        if (bl && oemMenuContext < 0) {
            int[] nArray = iDisplayManager.getDisplayables(n);
            if (nArray == null || nArray.length == 0) {
                return;
            }
            int[] nArray2 = Mhi2qAaClusterComposition.prependMenuLayer(nArray);
            if (!Mhi2qAaClusterComposition.declareOemContext(n, nArray2)) {
                return;
            }
            oemMenuLayers = (int[])nArray.clone();
            oemMenuContext = n;
        }
        iDisplayManager.lockDisplayAndWait(1);
        try {
            if (bl) {
                iDisplayManager.setCropping(98, 1, 0, 0, 328, 180, 449, 87, 541, 297);
            }
            iDisplayManager.setOpacity(98, 1, bl ? 100 : 0);
        }
        finally {
            iDisplayManager.unlockDisplayAndWait(1);
        }
        if (iDisplayManager.getOpacity(98, 1) != (bl ? 100 : 0)) {
            return;
        }
        if (!bl && !Mhi2qAaClusterComposition.restoreOemMenuContext()) {
            return;
        }
        appliedMenuVisible = bl;
        appliedMenuRevision = n2;
        appliedMenuToken = bl ? n3 : 0;
        appliedTurnCardVisible = false;
        appliedTurnCardRevision = turnCardRevision;
        appliedTurnCardToken = 0;
        if (bl) {
            Mhi2qAaClusterComposition.notifyVcMenuPresented(n2, n3, 0, 0, 328, 180);
        } else {
            Mhi2qAaClusterComposition.notifyVcMenuHidden(n2);
        }
    }

    static int[] prependMenuLayer(int[] nArray) {
        int n = 0;
        for (int i = 0; i < nArray.length; ++i) {
            if (nArray[i] == 98) continue;
            ++n;
        }
        int[] nArray2 = new int[n + 1];
        nArray2[0] = 98;
        int n2 = 1;
        for (int i = 0; i < nArray.length; ++i) {
            if (nArray[i] == 98) continue;
            nArray2[n2++] = nArray[i];
        }
        return nArray2;
    }

    private static boolean restoreOemMenuContext() {
        if (oemMenuContext < 0) {
            return true;
        }
        if (!Mhi2qAaClusterComposition.declareOemContext(oemMenuContext, oemMenuLayers)) {
            return false;
        }
        oemMenuContext = -1;
        oemMenuLayers = null;
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static boolean declareOemContext(int n, int[] nArray) {
        IContext iContext = context;
        if (iContext == null || iContext.getFramework() == null) {
            return false;
        }
        BundleContext bundleContext = iContext.getFramework().getBundleCxt();
        if (bundleContext == null) {
            return false;
        }
        ServiceReference serviceReference = bundleContext.getServiceReference(DSIDisplayManagement.class.getName());
        if (serviceReference == null) {
            return false;
        }
        try {
            Object object = bundleContext.getService(serviceReference);
            if (!(object instanceof DSIDisplayManagement)) {
                boolean bl = false;
                return bl;
            }
            ((DSIDisplayManagement)object).declareContexts(new DisplayContext[]{new DisplayContext(n, nArray)});
            boolean bl = true;
            return bl;
        }
        finally {
            bundleContext.ungetService(serviceReference);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static boolean requestNativeContext(int n) {
        boolean bl;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(NATIVE_REASSERT_REQUEST, false);
            fileOutputStream.write(n);
            fileOutputStream.flush();
            bl = true;
            if (fileOutputStream == null) return bl;
        }
        catch (Throwable throwable) {
            try {
                Log.w("AAComposition", "native context request failed: " + throwable);
                boolean bl2 = false;
                return bl2;
            }
            catch (Throwable throwable2) {
                throw throwable2;
            }
            finally {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    }
                    catch (Throwable throwable3) {}
                }
            }
        }
        try {
            fileOutputStream.close();
            return bl;
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return bl;
    }

    private static void publishUiViewState(int n) {
        int n2 = Mhi2qAaClusterComposition.uiSnapshotForTest(viewSize, skin, clusterProjectionEnabled, n, publishedUiViewState);
        if (publishedUiViewState == n2) {
            return;
        }
        try {
            Mhi2qAaClusterComposition.writeUiViewState(new File(UI_VIEW_STATE), n2);
            publishedUiViewState = n2;
            Log.w("AAComposition", "AA UI view=" + ((n2 & 0xFF) == 83 ? "sport-left" : ((n2 & 0xFF) == 67 ? "classic-large-gauges" : "large-center")) + " projection=" + (n2 >>> 8 == 80));
        }
        catch (Throwable throwable) {
            Log.w("AAComposition", "AA UI view publish failed: " + throwable);
        }
    }

    static int uiSnapshotForTest(int n, int n2, boolean bl, int n3, int n4) {
        boolean bl2 = bl || n3 == 70 || n3 < 0 && (n4 < 0 || n4 >>> 8 != 79);
        return Mhi2qAaClusterComposition.uiViewStateForTest(n, n2) | (bl2 ? 80 : 79) << 8;
    }

    static void writeUiViewState(File file, int n) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file, false);
        try {
            fileOutputStream.write(new byte[]{(byte)n, (byte)(n >>> 8)});
        }
        finally {
            fileOutputStream.close();
        }
    }

    private static IDisplayManager resolveDisplayManager() {
        IDisplayManager iDisplayManager = displayManager;
        if (iDisplayManager != null) {
            return iDisplayManager;
        }
        IContext iContext = context;
        if (iContext == null) {
            return null;
        }
        try {
            if (iContext.getFramework() == null || iContext.getFramework().getHMIService() == null) {
                return null;
            }
            iDisplayManager = iContext.getFramework().getHMIService().getDisplayManager();
            if (iDisplayManager != null) {
                displayManager = iDisplayManager;
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return iDisplayManager;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void refreshOemViewState() {
        IContext iContext = context;
        if (iContext == null || iContext.getFramework() == null) {
            return;
        }
        try {
            HMIService hMIService = iContext.getFramework().getHMIService();
            if (hMIService == null) {
                return;
            }
            HMIModel hMIModel = hMIService.getModel(402521);
            if (!(hMIModel instanceof ChoiceModelGUI)) {
                return;
            }
            int n = ((ChoiceModelGUI)hMIModel).getValue();
            int n2 = n == 1 ? 1 : 2;
            int n3 = skin;
            Layout layout = null;
            HMITerminal hMITerminal = hMIService.getHMITerminal(0);
            if (hMITerminal instanceof HMITerminalEvo) {
                n3 = ((HMITerminalEvo)hMITerminal).getSkin();
            }
            if (hMITerminal instanceof HMITerminalImpl) {
                layout = ((HMITerminalImpl)hMITerminal).getLayout();
            }
            int n4 = inTubeX;
            int n5 = inTubeY;
            int n6 = inTubeCropX;
            int n7 = inTubeCropY;
            int n8 = inTubeCropW;
            int n9 = inTubeCropH;
            int n10 = popupX;
            int n11 = popupY;
            int n12 = popupCropX;
            int n13 = popupCropY;
            int n14 = popupCropW;
            int n15 = popupCropH;
            String string = layoutName;
            if (layout != null) {
                n4 = layout.getIntegerConstant(58);
                n5 = layout.getIntegerConstant(59);
                n6 = layout.getIntegerConstant(122);
                n7 = layout.getIntegerConstant(123);
                n8 = layout.getIntegerConstant(124);
                n9 = layout.getIntegerConstant(125);
                n10 = layout.getIntegerConstant(60);
                n11 = layout.getIntegerConstant(61);
                n12 = layout.getIntegerConstant(118);
                n13 = layout.getIntegerConstant(119);
                n14 = layout.getIntegerConstant(120);
                n15 = layout.getIntegerConstant(121);
                string = layout.getClass().getName();
            }
            Object object = LOCK;
            synchronized (object) {
                boolean bl;
                viewSizeKnown = n == 1 || n == 2;
                boolean bl2 = inTubeX != n4 || inTubeY != n5 || inTubeCropX != n6 || inTubeCropY != n7 || inTubeCropW != n8 || inTubeCropH != n9 || popupX != n10 || popupY != n11 || popupCropX != n12 || popupCropY != n13 || popupCropW != n14 || popupCropH != n15 || !layoutName.equals(string);
                boolean bl3 = bl = viewSize != n2 || skin != n3;
                if (!bl2 && !bl) {
                    return;
                }
                viewSize = n2;
                skin = n3;
                if (bl2) {
                    inTubeX = n4;
                    inTubeY = n5;
                    inTubeCropX = n6;
                    inTubeCropY = n7;
                    inTubeCropW = n8;
                    inTubeCropH = n9;
                    popupX = n10;
                    popupY = n11;
                    popupCropX = n12;
                    popupCropY = n13;
                    popupCropW = n14;
                    popupCropH = n15;
                    layoutName = string;
                    ++geometryRevision;
                }
                Log.w("AAComposition", "VC view model=" + n + " skin=" + skin + " layout=" + layoutName);
            }
        }
        catch (Throwable throwable) {
            Log.w("AAComposition", "VC view/Layout read failed: " + throwable);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void applyLayers(IDisplayManager iDisplayManager) {
        boolean bl;
        int n;
        int n2;
        int n3;
        boolean bl2;
        int n4;
        int n5;
        boolean bl3;
        int n6 = viewSize;
        int n7 = skin;
        int n8 = geometryRevision;
        Object object = LOCK;
        synchronized (object) {
            bl3 = turnCardVisible;
            n5 = turnCardRevision;
            n4 = turnCardToken;
            bl2 = menuVisible;
            n3 = menuRevision;
            n2 = menuToken;
        }
        if (bl2) {
            int n9 = 0;
            int n10 = 0;
            int n11 = 328;
            int n12 = 180;
            if (!appliedMenuVisible || appliedMenuRevision != n3 || appliedMenuToken != n2 || !Mhi2qAaClusterComposition.layerOpacitiesMatch(iDisplayManager, 100, 0, 0)) {
                if (!Mhi2qAaClusterComposition.setGeometry(iDisplayManager, n9, n10, n11, n12, 449, 87, 541, 297, -1)) {
                    return;
                }
                appliedMenuVisible = true;
                appliedMenuRevision = n3;
                appliedMenuToken = n2;
                appliedTurnCardVisible = false;
            }
            Mhi2qAaClusterComposition.notifyVcMenuPresented(n3, n2, n9, n10, n11, n12);
            return;
        }
        boolean bl4 = appliedMenuVisible;
        int n13 = n6 != 1 ? popupCropX : inTubeCropX;
        int n14 = n6 != 1 ? popupCropY : inTubeCropY;
        int n15 = n6 != 1 ? popupCropW : inTubeCropW;
        int n16 = n = n6 != 1 ? popupCropH : inTubeCropH;
        if (appliedViewSize == n6 && appliedSkin == n7 && appliedGeometryRevision == n8 && appliedTurnCardVisible == bl3 && appliedTurnCardRevision == n5 && appliedTurnCardToken == n4 && appliedMenuRevision == n3 && !appliedMenuVisible && Mhi2qAaClusterComposition.layerOpacitiesMatch(iDisplayManager, bl3 ? 100 : 0, bl3 && n6 == 1 ? 100 : 0, bl3 && n6 != 1 ? 100 : 0)) {
            Mhi2qAaClusterComposition.notifyTurnCardVisibilityApplied(bl3, n5, n4, n13, n14, n15, n);
            return;
        }
        if (!bl3) {
            bl = Mhi2qAaClusterComposition.hideLayers(iDisplayManager);
        } else {
            if (!Mhi2qAaClusterComposition.turnCardShowCanCommit(n5, n4)) {
                return;
            }
            bl = n6 != 1 ? Mhi2qAaClusterComposition.setGeometry(iDisplayManager, n13, n14, n15, n, popupX, popupY, n15, n, 102) : Mhi2qAaClusterComposition.setGeometry(iDisplayManager, n13, n14, n15, n, inTubeX, inTubeY, n15, n, 101);
        }
        if (!bl) {
            return;
        }
        appliedViewSize = n6;
        appliedSkin = n7;
        appliedGeometryRevision = n8;
        appliedTurnCardVisible = bl3;
        appliedTurnCardRevision = n5;
        appliedTurnCardToken = n4;
        appliedMenuVisible = false;
        appliedMenuRevision = n3;
        appliedMenuToken = 0;
        if (bl4) {
            Mhi2qAaClusterComposition.notifyVcMenuHidden(n3);
        }
        Mhi2qAaClusterComposition.notifyTurnCardVisibilityApplied(bl3, n5, n4, n13, n14, n15, n);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static boolean turnCardShowCanCommit(int n, int n2) {
        Object object = LOCK;
        synchronized (object) {
            if (!turnCardVisible || turnCardRevision != n || turnCardToken != n2) {
                return false;
            }
            if (!Mhi2qAaClusterComposition.turnCardShowDelayElapsed(turnDisplayShowDeadlineMs, System.currentTimeMillis())) {
                return false;
            }
            turnDisplayShowDeadlineMs = 0L;
            return true;
        }
    }

    private static void notifyTurnCardVisibilityApplied(boolean bl, int n, int n2, int n3, int n4, int n5, int n6) {
        if (bl) {
            Mhi2qAaClusterComposition.notifyTurnCardPresented(n, n2, n3, n4, n5, n6);
        } else {
            Mhi2qAaClusterComposition.notifyTurnCardHidden(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void notifyTurnCardPresented(int n, int n2, int n3, int n4, int n5, int n6) {
        PresentationListener presentationListener;
        Object object = LOCK;
        synchronized (object) {
            if (!Mhi2qAaClusterComposition.presentationIsCurrentLocked(n, n2) || notifiedTurnCardRevision == n && notifiedTurnCardToken == n2 && notifiedSourceX == n3 && notifiedSourceY == n4 && notifiedSourceWidth == n5 && notifiedSourceHeight == n6) {
                return;
            }
            presentationListener = Mhi2qAaClusterComposition.presentationListener;
            if (presentationListener == null) {
                return;
            }
        }
        boolean bl = false;
        try {
            bl = presentationListener.onTurnCardPresented(n, n2, n3, n4, n5, n6);
        }
        catch (Throwable throwable) {
            Log.w("AAComposition", "presentation listener failed: " + throwable);
        }
        if (!bl) {
            return;
        }
        Object object2 = LOCK;
        synchronized (object2) {
            if (Mhi2qAaClusterComposition.presentationIsCurrentLocked(n, n2)) {
                notifiedTurnCardRevision = n;
                notifiedTurnCardToken = n2;
                notifiedSourceX = n3;
                notifiedSourceY = n4;
                notifiedSourceWidth = n5;
                notifiedSourceHeight = n6;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void notifyTurnCardHidden(int n) {
        PresentationListener presentationListener;
        Object object = LOCK;
        synchronized (object) {
            if (!Mhi2qAaClusterComposition.hiddenPresentationIsCurrentLocked(n) || notifiedTurnCardRevision == n && notifiedTurnCardToken == 0) {
                return;
            }
            presentationListener = Mhi2qAaClusterComposition.presentationListener;
            if (presentationListener == null) {
                return;
            }
        }
        boolean bl = false;
        try {
            bl = presentationListener.onTurnCardHidden(n);
        }
        catch (Throwable throwable) {
            Log.w("AAComposition", "hidden listener failed: " + throwable);
        }
        if (!bl) {
            return;
        }
        Object object2 = LOCK;
        synchronized (object2) {
            if (Mhi2qAaClusterComposition.hiddenPresentationIsCurrentLocked(n)) {
                notifiedTurnCardRevision = n;
                notifiedTurnCardToken = 0;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void notifyVcMenuPresented(int n, int n2, int n3, int n4, int n5, int n6) {
        PresentationListener presentationListener;
        Object object = LOCK;
        synchronized (object) {
            if (!Mhi2qAaClusterComposition.menuPresentationIsCurrentLocked(n, n2) || notifiedMenuRevision == n && notifiedMenuToken == n2) {
                return;
            }
            presentationListener = Mhi2qAaClusterComposition.presentationListener;
            if (presentationListener == null) {
                return;
            }
        }
        boolean bl = false;
        try {
            bl = presentationListener.onVcMenuPresented(n, n2, n3, n4, n5, n6);
        }
        catch (Throwable throwable) {
            Log.w("AAComposition", "menu presentation listener failed: " + throwable);
        }
        if (bl) {
            Object object2 = LOCK;
            synchronized (object2) {
                if (Mhi2qAaClusterComposition.menuPresentationIsCurrentLocked(n, n2)) {
                    notifiedMenuRevision = n;
                    notifiedMenuToken = n2;
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void notifyVcMenuHidden(int n) {
        PresentationListener presentationListener;
        Object object = LOCK;
        synchronized (object) {
            if (!Mhi2qAaClusterComposition.hiddenMenuPresentationIsCurrentLocked(n) || notifiedMenuRevision == n && notifiedMenuToken == 0) {
                return;
            }
            presentationListener = Mhi2qAaClusterComposition.presentationListener;
            if (presentationListener == null) {
                return;
            }
        }
        boolean bl = false;
        try {
            bl = presentationListener.onVcMenuHidden(n);
        }
        catch (Throwable throwable) {
            Log.w("AAComposition", "menu hidden listener failed: " + throwable);
        }
        if (bl) {
            Object object2 = LOCK;
            synchronized (object2) {
                if (Mhi2qAaClusterComposition.hiddenMenuPresentationIsCurrentLocked(n)) {
                    notifiedMenuRevision = n;
                    notifiedMenuToken = 0;
                }
            }
        }
    }

    private static boolean presentationIsCurrentLocked(int n, int n2) {
        return n2 != 0 && turnCardVisible && turnCardRevision == n && turnCardToken == n2 && appliedTurnCardVisible && appliedTurnCardRevision == n && appliedTurnCardToken == n2;
    }

    private static boolean hiddenPresentationIsCurrentLocked(int n) {
        return !turnCardVisible && turnCardToken == 0 && turnCardRevision == n && !appliedTurnCardVisible && appliedTurnCardRevision == n && appliedTurnCardToken == 0;
    }

    private static boolean menuPresentationIsCurrentLocked(int n, int n2) {
        return n2 != 0 && menuVisible && menuRevision == n && menuToken == n2 && appliedMenuVisible && appliedMenuRevision == n && appliedMenuToken == n2;
    }

    private static boolean hiddenMenuPresentationIsCurrentLocked(int n) {
        return !menuVisible && menuToken == 0 && menuRevision == n && !appliedMenuVisible && appliedMenuRevision == n && appliedMenuToken == 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static boolean setGeometry(IDisplayManager iDisplayManager, int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9) {
        turnDisplayHideDeadlineMs = 0L;
        iDisplayManager.lockDisplayAndWait(1);
        try {
            iDisplayManager.setCropping(98, 1, n, n2, n3, n4, n5, n6, n7, n8);
            iDisplayManager.setOpacity(98, 1, 100);
            if (n9 >= 0) {
                iDisplayManager.setPosition(n9, 1, n5, n6);
                iDisplayManager.setOpacity(n9, 1, 100);
                iDisplayManager.setOpacity(n9 == 102 ? 101 : 102, 1, 0);
            } else {
                iDisplayManager.setOpacity(101, 1, 0);
                iDisplayManager.setOpacity(102, 1, 0);
            }
        }
        finally {
            iDisplayManager.unlockDisplayAndWait(1);
        }
        if (!Mhi2qAaClusterComposition.layerOpacitiesMatch(iDisplayManager, 100, n9 == 101 ? 100 : 0, n9 == 102 ? 100 : 0)) {
            return false;
        }
        Log.w("AAComposition", "mode=" + Mhi2qAaClusterComposition.modeName() + " layout=" + layoutName + " backing=" + n9 + " src=" + n + "," + n2 + " " + n3 + "x" + n4 + " dst=" + n5 + "," + n6 + " " + n7 + "x" + n8);
        return true;
    }

    private static boolean hideLayers(IDisplayManager iDisplayManager) {
        long l = System.currentTimeMillis();
        if (turnDisplayHideDeadlineMs == 0L) {
            turnDisplayHideDeadlineMs = l + 500L;
            Log.w("AAComposition", "complete turn overlay close delayed 500ms");
            return false;
        }
        if (l < turnDisplayHideDeadlineMs) {
            return false;
        }
        iDisplayManager.lockDisplayAndWait(1);
        try {
            iDisplayManager.setOpacity(98, 1, 0);
            iDisplayManager.setOpacity(101, 1, 0);
            iDisplayManager.setOpacity(102, 1, 0);
        }
        finally {
            iDisplayManager.unlockDisplayAndWait(1);
        }
        if (!Mhi2qAaClusterComposition.layerOpacitiesMatch(iDisplayManager, 0, 0, 0)) {
            return false;
        }
        turnDisplayHideDeadlineMs = 0L;
        appliedTurnCardVisible = false;
        appliedMenuVisible = false;
        Log.w("AAComposition", "complete turn overlay close committed");
        return true;
    }

    private static boolean layerOpacitiesMatch(IDisplayManager iDisplayManager, int n, int n2, int n3) {
        try {
            return iDisplayManager.getOpacity(98, 1) == n && iDisplayManager.getOpacity(101, 1) == n2 && iDisplayManager.getOpacity(102, 1) == n3;
        }
        catch (Throwable throwable) {
            return false;
        }
    }

    private static String modeName() {
        if (viewSize != 1) {
            return "large";
        }
        return skin == 1 ? "sport-small" : "classic-small";
    }

    static int[] geometryForTest(int n, int n2) {
        if (n != 1) {
            return new int[]{59, 27, 210, 153, 1091, 110, 102};
        }
        if (n2 == 1) {
            return new int[]{0, 0, 328, 180, 984, 139, 101};
        }
        return new int[]{59, 27, 210, 153, 1055, 207, 101};
    }

    static int uiViewStateForTest(int n, int n2) {
        if (n != 1) {
            return 76;
        }
        return n2 == 1 ? 83 : 67;
    }

    static int[] menuGeometryForTest() {
        return new int[]{0, 0, 328, 180, 449, 87, 541, 297};
    }

    static boolean wantsCustomContextForTest(boolean bl, boolean bl2, boolean bl3) {
        return bl && bl2;
    }

    static int nextTurnCardRevisionForTest(boolean bl, int n, int n2, boolean bl2, int n3) {
        int n4 = bl2 ? n3 : 0;
        return bl == bl2 && n == n4 ? n2 : n2 + 1;
    }

    static boolean turnCardRequestNeedsApplyForTest(boolean bl, int n, int n2, boolean bl2, int n3, int n4) {
        return bl != bl2 || n != n3 || n2 != n4;
    }

    static long nextTurnCardShowDeadline(boolean bl, boolean bl2, long l, long l2) {
        if (!bl2) {
            return 0L;
        }
        if (bl) {
            return l2 + 600L;
        }
        return l;
    }

    static boolean turnCardShowDelayElapsed(long l, long l2) {
        return l == 0L || l2 >= l;
    }

    static {
        clusterProjectionEnabled = true;
        viewSize = 2;
        oemMenuContext = -1;
        appliedViewSize = -1;
        appliedSkin = -1;
        appliedGeometryRevision = -1;
        appliedTurnCardRevision = -1;
        notifiedTurnCardRevision = -1;
        notifiedSourceX = -1;
        notifiedSourceY = -1;
        notifiedSourceWidth = -1;
        notifiedSourceHeight = -1;
        appliedMenuRevision = -1;
        notifiedMenuRevision = -1;
        loggedContext = Integer.MIN_VALUE;
        publishedUiViewState = -1;
        inTubeX = 1055;
        inTubeY = 207;
        inTubeCropX = 59;
        inTubeCropY = 27;
        inTubeCropW = 210;
        inTubeCropH = 153;
        popupX = 1091;
        popupY = 110;
        popupCropX = 59;
        popupCropY = 27;
        popupCropW = 210;
        popupCropH = 153;
        layoutName = "fallback-classic";
    }

    public static interface PresentationListener {
        public boolean onTurnCardPresented(int var1, int var2, int var3, int var4, int var5, int var6);

        public boolean onTurnCardHidden(int var1);

        public boolean onVcMenuPresented(int var1, int var2, int var3, int var4, int var5, int var6);

        public boolean onVcMenuHidden(int var1);
    }
}

