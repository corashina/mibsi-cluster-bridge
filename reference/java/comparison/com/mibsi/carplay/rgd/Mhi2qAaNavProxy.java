/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.audi.app.terminalmode.IContext
 *  de.audi.app.terminalmode.osgi.IServiceManager
 *  de.audi.atip.base.IFrameworkAccess
 *  de.audi.atip.interapp.combi.bap.navi.CombiBAPServiceNavi
 *  de.audi.atip.interapp.combi.bap.navi.data.CombiBAPNaviManeuverDescriptor
 *  de.audi.atip.metrics.Distance
 *  de.audi.atip.storage.IStorageAccess
 *  de.audi.tghu.navi.app.Navigation
 *  de.audi.tghu.navi.app.cluster.BAPDistanceFormatter
 *  de.audi.tghu.navi.app.cluster.BAPDistanceFormatter$BAPDistance
 *  de.esolutions.fw.util.commons.job.DispatcherBase
 *  org.osgi.framework.ServiceReference
 */
package com.mibsi.carplay.rgd;

import com.mibsi.carplay.aa.ManeuverMapper;
import com.mibsi.carplay.aa.NavigationSnapshot;
import com.mibsi.carplay.aa.NavigationStateReader;
import com.mibsi.carplay.core.Mhi2qAaClusterComposition;
import com.mibsi.carplay.framework.Log;
import com.mibsi.carplay.rgd.Mhi2qAaGatedCombiService;
import com.mibsi.carplay.rgd.RendererServer;
import com.mibsi.carplay.rgd.TurnCardPolicy;
import de.audi.app.terminalmode.IContext;
import de.audi.app.terminalmode.osgi.IServiceManager;
import de.audi.atip.base.IFrameworkAccess;
import de.audi.atip.interapp.combi.bap.navi.CombiBAPServiceNavi;
import de.audi.atip.interapp.combi.bap.navi.data.CombiBAPNaviManeuverDescriptor;
import de.audi.atip.metrics.Distance;
import de.audi.atip.storage.IStorageAccess;
import de.audi.tghu.navi.app.Navigation;
import de.audi.tghu.navi.app.cluster.BAPDistanceFormatter;
import de.audi.tghu.navi.app.cluster.ClusterService;
import de.esolutions.fw.util.commons.job.DispatcherBase;
import java.io.File;
import org.osgi.framework.ServiceReference;

public final class Mhi2qAaNavProxy
implements Runnable {
    private static final long POLL_MS = 250L;
    public static final int VC_MODE_CLUSTER = 0;
    public static final int VC_MODE_OEM_MAP = 1;
    private static ClusterService cluster;
    private static IServiceManager serviceManager;
    private static ServiceReference serviceReference;
    private static CombiBAPServiceNavi rawService;
    private static Mhi2qAaGatedCombiService gate;
    private static BAPDistanceFormatter distanceFormatter;
    private static boolean workerStarted;
    private static Thread workerThread;
    private static boolean installScheduled;
    private static boolean sessionStarted;
    private static volatile boolean carPlayActive;
    private static volatile boolean deactivationRequested;
    private static int appliedMapScaleSuppression;
    private static boolean previousRgActive;
    private static boolean oemResyncPending;
    private static int exitViewSendCount;
    private static int publishedRgStatus;
    private static int publishedCardOpen;
    private static int publishedRouteDataAvailable;
    private static boolean navigationStateClaimed;
    private static int lastPublishedSequence;
    private static int lastRenderedSequence;
    private static boolean rendererHadFrame;
    private static volatile int rendererStateRevision;
    private static int lastRendererStateRevision;
    private static volatile int rendererConnectionRevision;
    private static int lastRendererConnectionRevision;
    private static boolean turnCardWindowOpen;
    private static boolean clusterCardAvailable;
    private static int presentationTokenCounter;
    private static int presentationToken;
    private static volatile boolean vcMenuRequested;
    private static volatile boolean vcMenuClosing;
    private static volatile int vcMenuSelection;
    private static volatile int vcActiveMode;
    private static volatile int vcMenuToken;
    private static final int VC_STORAGE_SCOPE = 9000;
    private static final int VC_STORAGE_KEY = 1;
    private static final long VC_STORAGE_RETRY_MS = 5000L;
    private static volatile IFrameworkAccess vcStorageFramework;
    private static IStorageAccess vcStorageForTest;
    private static boolean vcPreferenceLoaded;
    private static int vcPreferenceRevision;
    private static int vcPersistedRevision;
    private static long vcStorageRetryAfter;
    private static final File VC_MENU_COMMAND;
    private static long vcMenuTestDeadline;
    private static final RendererServer renderer;
    private static final NavigationStateReader stateReader;
    private static String mode;
    private static int sequence;
    private static long updated;
    private static boolean active;
    private static int maneuver;
    private static int roundaboutExit;
    private static int roundaboutAngle;
    private static int distanceMeters;
    private static int timeToStepSeconds;
    private static int destinationDistanceMeters;
    private static int timeToDestinationSeconds;
    private static String currentRoad;
    private static String nextRoad;
    private static String instruction;
    private static byte[] laneGuidance;

    private Mhi2qAaNavProxy() {
    }

    public static synchronized boolean openVcSettingsMenu() {
        if (!carPlayActive || !Mhi2qAaClusterComposition.isLargeView()) {
            return false;
        }
        if (vcMenuRequested) {
            return true;
        }
        vcMenuRequested = true;
        vcMenuClosing = false;
        vcMenuSelection = vcActiveMode;
        vcMenuToken = presentationTokenCounter = Mhi2qAaNavProxy.nextPresentationTokenForTest(presentationTokenCounter);
        lastRenderedSequence = -1;
        return true;
    }

    public static synchronized void closeVcSettingsMenu() {
        if (!vcMenuRequested) {
            return;
        }
        vcMenuRequested = false;
        vcMenuClosing = true;
        vcMenuTestDeadline = 0L;
    }

    public static synchronized void toggleVcSettingsMenu() {
        if (vcMenuRequested) {
            Mhi2qAaNavProxy.closeVcSettingsMenu();
        } else {
            Mhi2qAaNavProxy.openVcSettingsMenu();
        }
    }

    public static boolean isVcSettingsMenuRequested() {
        return vcMenuRequested;
    }

    public static synchronized boolean moveVcSettingsSelection(int n) {
        Mhi2qAaNavProxy.enforceVcMenuView();
        if (!vcMenuRequested || n == 0) {
            return false;
        }
        vcMenuSelection = Mhi2qAaNavProxy.wrapMenuSelectionForTest(vcMenuSelection, n);
        return true;
    }

    public static synchronized boolean selectVcSettingsItem() {
        Mhi2qAaNavProxy.enforceVcMenuView();
        if (!vcMenuRequested) {
            return false;
        }
        if (!vcPreferenceLoaded || vcActiveMode != vcMenuSelection) {
            vcPreferenceLoaded = true;
            ++vcPreferenceRevision;
            vcStorageRetryAfter = 0L;
        }
        Mhi2qAaClusterComposition.setClusterProjectionEnabled((vcActiveMode = vcMenuSelection) != 1);
        if (vcActiveMode == 1) {
            Mhi2qAaClusterComposition.setTurnCardVisible(false, 0);
        }
        Mhi2qAaNavProxy.closeVcSettingsMenu();
        appliedMapScaleSuppression = -1;
        Mhi2qAaNavProxy.wakeWorker();
        return true;
    }

    static synchronized void pollVcMenuTestCommand(long l) {
    }

    static synchronized int[] vcMenuStateForTest() {
        return new int[]{vcMenuRequested ? 1 : 0, vcMenuClosing ? 1 : 0, vcMenuSelection, vcActiveMode};
    }

    public static synchronized void onCarPlayActivate() {
        carPlayActive = true;
        Mhi2qAaClusterComposition.setClusterProjectionEnabled(vcActiveMode != 1);
        deactivationRequested = false;
        navigationStateClaimed = false;
        appliedMapScaleSuppression = -1;
        Mhi2qAaNavProxy.wakeWorker();
    }

    public static synchronized void onCarPlayDeactivate() {
        carPlayActive = false;
        deactivationRequested = true;
        vcMenuTestDeadline = 0L;
        navigationStateClaimed = false;
        vcMenuRequested = false;
        vcMenuClosing = false;
        vcMenuToken = 0;
        appliedMapScaleSuppression = -1;
        Mhi2qAaClusterComposition.setVcMenuVisible(false, 0);
        Mhi2qAaNavProxy.wakeWorker();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void startTransport(Object object) {
        try {
            if (!(object instanceof IContext)) {
                return;
            }
            Class clazz = Mhi2qAaNavProxy.class;
            synchronized (clazz) {
                serviceManager = ((IContext)object).getServiceManager();
                try {
                    vcStorageFramework = ((IContext)object).getFramework();
                }
                catch (Throwable throwable) {
                    // empty catch block
                }
                Mhi2qAaNavProxy.startWorker();
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    public static synchronized CombiBAPServiceNavi prepareCombiBAPService(ClusterService clusterService, CombiBAPServiceNavi combiBAPServiceNavi) {
        try {
            CombiBAPServiceNavi combiBAPServiceNavi2 = combiBAPServiceNavi;
            if (combiBAPServiceNavi instanceof Mhi2qAaGatedCombiService) {
                combiBAPServiceNavi2 = ((Mhi2qAaGatedCombiService)combiBAPServiceNavi).getRealService();
            }
            if (rawService != null && rawService != combiBAPServiceNavi2 && sessionStarted) {
                Mhi2qAaNavProxy.stopSession();
            }
            if (cluster != clusterService) {
                appliedMapScaleSuppression = -1;
            }
            cluster = clusterService;
            rawService = combiBAPServiceNavi2;
            distanceFormatter = clusterService == null ? null : new BAPDistanceFormatter(clusterService.getMhi2qAaLogChannel());
            lastPublishedSequence = -1;
            if (combiBAPServiceNavi2 == null) {
                gate = null;
                Mhi2qAaNavProxy.log("MHI2Q AA BAP service detached");
                return null;
            }
            gate = new Mhi2qAaGatedCombiService(combiBAPServiceNavi2);
            appliedMapScaleSuppression = -1;
            gate.setBlocked(Mhi2qAaNavProxy.wantsProjectedBap() || sessionStarted);
            Mhi2qAaNavProxy.startWorker();
            Mhi2qAaNavProxy.log("MHI2Q AA BAP gate installed");
            return gate;
        }
        catch (Throwable throwable) {
            gate = null;
            rawService = combiBAPServiceNavi;
            return combiBAPServiceNavi;
        }
    }

    private static void startWorker() {
        if (workerStarted) {
            return;
        }
        Thread thread = new Thread(new Mhi2qAaNavProxy(), "MHI2Q-AA-BAP");
        thread.setDaemon(true);
        try {
            workerThread = thread;
            thread.start();
            workerStarted = true;
        }
        catch (Throwable throwable) {
            workerThread = null;
            workerStarted = false;
        }
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(250L);
            }
            catch (InterruptedException interruptedException) {
                // empty catch block
            }
            try {
                Mhi2qAaNavProxy.syncVcPreference();
                Mhi2qAaNavProxy.tick();
                continue;
            }
            catch (Throwable throwable) {
                Mhi2qAaNavProxy.failOpen();
                continue;
            }
            break;
        }
    }

    private static void wakeWorker() {
        Thread thread = workerThread;
        if (thread != null) {
            thread.interrupt();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void syncVcPreference() {
        int n;
        int n2;
        boolean bl;
        IStorageAccess iStorageAccess;
        IFrameworkAccess iFrameworkAccess;
        long l = System.currentTimeMillis();
        Class clazz = Mhi2qAaNavProxy.class;
        synchronized (clazz) {
            if (l < vcStorageRetryAfter) {
                return;
            }
            iFrameworkAccess = vcStorageFramework;
            iStorageAccess = vcStorageForTest;
            bl = vcPreferenceLoaded;
            n2 = vcPreferenceRevision;
            n = vcActiveMode;
            if (bl && n2 == vcPersistedRevision) {
                return;
            }
        }
        try {
            Object object = iStorageAccess != null ? iStorageAccess : (clazz = iFrameworkAccess == null ? null : iFrameworkAccess.getStorageMgr());
            if (clazz == null) {
                return;
            }
            if (!bl) {
                int n3 = clazz.getInt(9000, 1, 0);
                int n4 = n3 == 1 ? 1 : 0;
                boolean bl2 = false;
                Class clazz2 = Mhi2qAaNavProxy.class;
                synchronized (clazz2) {
                    if (!vcPreferenceLoaded && vcPreferenceRevision == n2) {
                        vcPreferenceLoaded = true;
                        vcPersistedRevision = n2;
                        vcActiveMode = n4;
                        if (vcMenuRequested) {
                            vcMenuSelection = n4;
                        }
                        appliedMapScaleSuppression = -1;
                        bl2 = carPlayActive;
                    }
                }
                if (bl2) {
                    Mhi2qAaClusterComposition.setClusterProjectionEnabled(n4 != 1);
                    if (n4 == 1) {
                        Mhi2qAaClusterComposition.setTurnCardVisible(false, 0);
                    }
                }
                return;
            }
            clazz.setInt(9000, 1, n);
            if (clazz.getInt(9000, 1, -1) != n) {
                throw new IllegalStateException("VC preference write not visible");
            }
            Class clazz3 = Mhi2qAaNavProxy.class;
            synchronized (clazz3) {
                if (vcPreferenceRevision == n2) {
                    vcPersistedRevision = n2;
                }
                vcStorageRetryAfter = 0L;
            }
        }
        catch (Throwable throwable) {
            Class clazz4 = Mhi2qAaNavProxy.class;
            synchronized (clazz4) {
                vcStorageRetryAfter = l + 5000L;
            }
            Log.w("VCMenu", "preference storage unavailable: " + throwable);
        }
    }

    private static synchronized void tick() {
        boolean bl;
        boolean bl2;
        boolean bl3;
        Mhi2qAaNavProxy.pollVcMenuTestCommand(System.currentTimeMillis());
        renderer.connect();
        int n = rendererConnectionRevision;
        if (n != lastRendererConnectionRevision) {
            presentationToken = 0;
            lastRenderedSequence = -1;
            lastRendererConnectionRevision = n;
        }
        Mhi2qAaNavProxy.enforceVcMenuView();
        Mhi2qAaNavProxy.resolveInfrastructure();
        if (!carPlayActive) {
            if (sessionStarted) {
                Mhi2qAaNavProxy.stopSession();
            } else if (oemResyncPending || gate != null && gate.isBlocked()) {
                Mhi2qAaNavProxy.releaseBapToOem();
            }
            if (deactivationRequested) {
                deactivationRequested = false;
                Mhi2qAaNavProxy.prepareOemMapImage();
                try {
                    renderer.sendClear();
                }
                catch (Throwable throwable) {
                    // empty catch block
                }
                Mhi2qAaClusterComposition.setTurnCardVisible(false, 0);
                Mhi2qAaNavProxy.log("MHI2Q AA OEM state restored after disconnect");
            }
            return;
        }
        Mhi2qAaNavProxy.setOemMapPresentation(Mhi2qAaNavProxy.oemMapShouldBeVisible(carPlayActive, vcActiveMode));
        if (!Mhi2qAaNavProxy.wantsProjectedBap() && sessionStarted) {
            Mhi2qAaClusterComposition.setTurnCardVisible(false, 0);
            Mhi2qAaClusterComposition.setVcMenuVisible(false, 0);
            if (!Mhi2qAaClusterComposition.isProjectionReleased()) {
                return;
            }
            Mhi2qAaNavProxy.stopSession();
        } else if (!Mhi2qAaNavProxy.wantsProjectedBap() && (oemResyncPending || gate != null && gate.isBlocked())) {
            Mhi2qAaNavProxy.releaseBapToOem();
        }
        Mhi2qAaNavProxy.refresh();
        boolean bl4 = Mhi2qAaNavProxy.routeDataIsAvailable();
        boolean bl5 = bl3 = vcMenuRequested || vcMenuClosing;
        if (vcMenuRequested && renderer.isReady() && (Mhi2qAaNavProxy.wantsProjectedBap() || Mhi2qAaClusterComposition.isProjectionReleased())) {
            renderer.sendVcMenu(true, vcMenuSelection, vcActiveMode, vcMenuToken);
            Mhi2qAaClusterComposition.setTurnCardVisible(false, 0);
            Mhi2qAaClusterComposition.setVcMenuVisible(renderer.isFrameReady(), renderer.isFrameReady() ? vcMenuToken : 0);
        } else if (vcMenuClosing) {
            Mhi2qAaClusterComposition.setTurnCardVisible(false, 0);
            Mhi2qAaClusterComposition.setVcMenuVisible(false, 0);
            if (Mhi2qAaClusterComposition.isVcMenuVisibilityApplied(false)) {
                renderer.sendVcMenu(false, vcMenuSelection, vcActiveMode, 0);
                vcMenuClosing = false;
                vcMenuToken = 0;
                lastRenderedSequence = -1;
            }
        }
        if (!Mhi2qAaNavProxy.wantsProjectedBap()) {
            return;
        }
        if (rawService != null) {
            if (!sessionStarted && !Mhi2qAaNavProxy.startSession(bl4)) {
                return;
            }
            boolean bl6 = bl2 = bl4 && Mhi2qAaNavProxy.projectedCardsEnabled() && Mhi2qAaNavProxy.shouldOpenTurnCard();
            if (bl2 != turnCardWindowOpen) {
                turnCardWindowOpen = bl2;
                lastPublishedSequence = -1;
                lastRenderedSequence = -1;
                presentationToken = 0;
                Mhi2qAaNavProxy.log("MHI2Q AA turn card window " + (bl2 ? "opened" : "closed") + " eta=" + timeToStepSeconds + "s distance=" + distanceMeters + "m");
            }
            if (turnCardWindowOpen && !bl3 && sequence != lastRenderedSequence && Mhi2qAaNavProxy.publishRendererSnapshot()) {
                lastRenderedSequence = sequence;
            }
        } else if (sessionStarted) {
            Mhi2qAaNavProxy.stopSession();
        }
        bl2 = renderer.isFrameReady();
        int n2 = rendererStateRevision;
        boolean bl7 = false;
        n = rendererConnectionRevision;
        if (n != lastRendererConnectionRevision) {
            presentationToken = 0;
            lastRenderedSequence = -1;
            lastRendererConnectionRevision = n;
            bl7 = true;
        }
        boolean bl8 = bl = n2 != lastRendererStateRevision;
        if (bl || bl2 != rendererHadFrame) {
            lastPublishedSequence = -1;
            if (!bl2) {
                lastRenderedSequence = -1;
            }
        }
        boolean bl9 = bl4 && !bl7 && Mhi2qAaNavProxy.isCardPresented(sessionStarted, turnCardWindowOpen, bl2, maneuver) && !bl3;
        boolean bl10 = clusterCardAvailable && !bl9;
        boolean bl11 = Mhi2qAaNavProxy.rgStatusPublicationRequired(publishedRgStatus, bl9, true);
        boolean bl12 = Mhi2qAaNavProxy.cardPublicationRequired(publishedCardOpen, bl9);
        boolean bl13 = Mhi2qAaNavProxy.routePublicationRequired(publishedRouteDataAvailable, bl4);
        if (sessionStarted && bl10 && (bl11 || bl12 || bl13)) {
            try {
                Mhi2qAaNavProxy.publishSnapshot(false, bl4, true);
                lastPublishedSequence = sequence;
            }
            catch (Throwable throwable) {
                Mhi2qAaNavProxy.failOpen();
                bl9 = false;
            }
        }
        if (bl10) {
            Mhi2qAaClusterComposition.setTurnCardVisible(false, 0);
        }
        boolean bl14 = Mhi2qAaNavProxy.closeCanCommit(bl10, Mhi2qAaClusterComposition.isTurnCardVisibilityApplied(false));
        boolean bl15 = Mhi2qAaNavProxy.shouldForcePresentationEdge(bl9, rendererHadFrame, bl);
        if (sessionStarted && !bl10 && (sequence != lastPublishedSequence || bl11 || bl12 || bl13)) {
            try {
                Mhi2qAaNavProxy.publishSnapshot(bl9, bl4, true);
                lastPublishedSequence = sequence;
            }
            catch (Throwable throwable) {
                Mhi2qAaNavProxy.failOpen();
                bl9 = false;
            }
        }
        rendererHadFrame = bl2;
        lastRendererStateRevision = n2;
        if (sessionStarted && bl14) {
            Mhi2qAaNavProxy.setClusterCardAvailable(bl9);
        }
        if (bl15) {
            Mhi2qAaClusterComposition.setTurnCardVisible(false, 0);
        }
        if (bl3) {
            Mhi2qAaClusterComposition.setTurnCardVisible(false, 0);
        } else if (!bl10) {
            Mhi2qAaClusterComposition.setTurnCardVisible(bl9, bl9 ? presentationToken : 0);
        }
    }

    private static boolean isCardPresented(boolean bl, boolean bl2, boolean bl3, int n) {
        return bl && bl2 && bl3 && n != 0;
    }

    private static boolean shouldForcePresentationEdge(boolean bl, boolean bl2, boolean bl3) {
        return bl && bl2 && bl3;
    }

    private static boolean closeCanCommit(boolean bl, boolean bl2) {
        return !bl || bl2;
    }

    private static boolean isRendererConnectionBoundary(String string) {
        return "connected".equals(string) || "disconnected".equals(string) || "send-failed".equals(string) || "disconnect-request".equals(string) || "disposed".equals(string);
    }

    private static boolean shouldOpenTurnCard() {
        return TurnCardPolicy.shouldOpen(timeToStepSeconds, distanceMeters, maneuver, turnCardWindowOpen);
    }

    private static void resolveInfrastructure() {
        Navigation navigation;
        block9: {
            if (rawService == null && serviceManager != null) {
                try {
                    navigation = serviceManager.getServiceReferences(CombiBAPServiceNavi.class);
                    if (navigation == null) break block9;
                    for (int i = 0; i < ((Navigation)navigation).length; ++i) {
                        Object object = serviceManager.getService((ServiceReference)navigation[i]);
                        if (!(object instanceof CombiBAPServiceNavi)) continue;
                        serviceReference = navigation[i];
                        rawService = (CombiBAPServiceNavi)object;
                        gate = new Mhi2qAaGatedCombiService(rawService);
                        appliedMapScaleSuppression = -1;
                        gate.setBlocked(Mhi2qAaNavProxy.wantsProjectedBap() || sessionStarted);
                        lastPublishedSequence = -1;
                        Mhi2qAaNavProxy.log("MHI2Q AA BAP OSGi service acquired");
                        break;
                    }
                }
                catch (Throwable throwable) {
                    // empty catch block
                }
            }
        }
        if (rawService == null || gate == null || installScheduled) {
            return;
        }
        try {
            navigation = Navigation.getInstance();
            if (navigation == null) {
                return;
            }
            DispatcherBase dispatcherBase = navigation.getDispatcher();
            if (dispatcherBase == null) {
                return;
            }
            installScheduled = true;
            dispatcherBase.execute((Object)new Runnable(){

                public void run() {
                    Mhi2qAaNavProxy.installGateOnNavigationJobs();
                }
            });
        }
        catch (Throwable throwable) {
            installScheduled = false;
        }
    }

    private static synchronized void installGateOnNavigationJobs() {
        installScheduled = false;
        try {
            Navigation navigation = Navigation.getInstance();
            if (navigation == null) {
                return;
            }
            ClusterService clusterService = navigation.getClusterService();
            if (clusterService == null || rawService == null) {
                return;
            }
            CombiBAPServiceNavi combiBAPServiceNavi = clusterService.getCombiBAPListenerCombiService();
            if (combiBAPServiceNavi == gate) {
                if (cluster != clusterService) {
                    appliedMapScaleSuppression = -1;
                }
                cluster = clusterService;
                return;
            }
            if (combiBAPServiceNavi instanceof Mhi2qAaGatedCombiService) {
                gate = (Mhi2qAaGatedCombiService)combiBAPServiceNavi;
                rawService = gate.getRealService();
                appliedMapScaleSuppression = -1;
            } else if (combiBAPServiceNavi != null) {
                rawService = combiBAPServiceNavi;
                gate = new Mhi2qAaGatedCombiService(rawService);
                appliedMapScaleSuppression = -1;
            }
            if (gate == null) {
                return;
            }
            if (cluster != clusterService) {
                appliedMapScaleSuppression = -1;
            }
            cluster = clusterService;
            distanceFormatter = new BAPDistanceFormatter(clusterService.getMhi2qAaLogChannel());
            gate.setBlocked(Mhi2qAaNavProxy.wantsProjectedBap() || sessionStarted);
            clusterService.setCombiBAPListenerCombiService(gate);
            Mhi2qAaNavProxy.log("MHI2Q AA BAP gate installed on NavigationJobs");
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    public static synchronized void onOemRgActive(boolean bl) {
        if (sessionStarted) {
            previousRgActive = bl;
        }
    }

    private static boolean wantsProjectedBap() {
        return carPlayActive && Mhi2qAaNavProxy.projectedCardsEnabled();
    }

    static synchronized void enforceVcMenuView() {
        if (!Mhi2qAaClusterComposition.isLargeView()) {
            Mhi2qAaNavProxy.closeVcSettingsMenu();
        }
    }

    private static boolean startSession(boolean bl) {
        if (cluster == null || rawService == null || gate == null) {
            return false;
        }
        try {
            previousRgActive = cluster.getDSIResponseContainer().isRgActive();
            publishedRgStatus = -1;
            publishedCardOpen = -1;
            publishedRouteDataAvailable = -1;
            gate.setBlocked(true);
            cluster.getDSIResponseContainer().setRgActive(true);
            Mhi2qAaNavProxy.forceClusterCardUnavailable(true);
            Mhi2qAaNavProxy.publishSnapshot(false, bl, true);
            sessionStarted = true;
            lastPublishedSequence = sequence;
            lastRenderedSequence = -1;
            turnCardWindowOpen = false;
            Mhi2qAaNavProxy.log("MHI2Q AA BAP ownership started route=" + bl);
            return true;
        }
        catch (Throwable throwable) {
            Mhi2qAaNavProxy.rollbackStart();
            return false;
        }
    }

    private static void publishSnapshot(boolean bl, boolean bl2, boolean bl3) throws Exception {
        int[] nArray;
        int[] nArray2;
        boolean bl4;
        int n = Mhi2qAaNavProxy.rgStatusForPresentation(bl, bl3);
        boolean bl5 = Mhi2qAaNavProxy.rgStatusPublicationRequired(publishedRgStatus, bl, bl3);
        boolean bl6 = Mhi2qAaNavProxy.cardPublicationRequired(publishedCardOpen, bl);
        boolean bl7 = Mhi2qAaNavProxy.routePublicationRequired(publishedRouteDataAvailable, bl2);
        boolean bl8 = bl4 = bl5 || bl6 || bl7;
        if (bl4) {
            rawService.updateRGStatus(n);
            rawService.updateActiveRGType(0);
        }
        if (bl) {
            int[] nArray3 = ManeuverMapper.bapDescriptor(maneuver, roundaboutAngle);
            Mhi2qAaNavProxy.sendDescriptor(nArray3[0], nArray3[1]);
        } else {
            Mhi2qAaNavProxy.sendDescriptor(0, 0);
        }
        int n2 = TurnCardPolicy.actionThreshold(maneuver);
        boolean bl9 = distanceMeters >= 0 && distanceMeters <= n2;
        int n3 = 0;
        if (bl9 && n2 > 0) {
            n3 = distanceMeters * 100 / n2;
            if (n3 < 0) {
                n3 = 0;
            }
            if (n3 > 100) {
                n3 = 100;
            }
        }
        if (bl) {
            nArray2 = Mhi2qAaNavProxy.formatDistance(distanceMeters);
            rawService.updateDistanceToNextManeuver(nArray2[0], nArray2[1], bl9, n3);
        } else {
            rawService.updateDistanceToNextManeuver(-1, 0, false, 0);
        }
        if (bl2) {
            nArray = Mhi2qAaNavProxy.formatDestinationDistance(destinationDistanceMeters);
        } else {
            int[] nArray4 = new int[2];
            nArray4[0] = -1;
            nArray = nArray4;
            nArray4[1] = 0;
        }
        nArray2 = nArray;
        rawService.updateDistanceToDestination(nArray2[0], nArray2[1], false);
        rawService.updateTimeToDestination(0, 0, bl2 ? (long)timeToDestinationSeconds : -1L);
        rawService.updateCurrentPositionInfo(bl2 ? Mhi2qAaNavProxy.choosePositionText() : "");
        rawService.updateTurnToInfo(Mhi2qAaNavProxy.turnToTextForCard(bl), "");
        rawService.updateManeuverState(Mhi2qAaNavProxy.maneuverStateForCard(bl, bl9));
        Mhi2qAaNavProxy.sendExitView();
        if (bl4) {
            publishedRgStatus = n;
            publishedCardOpen = bl ? 1 : 0;
            publishedRouteDataAvailable = bl2 ? 1 : 0;
        }
    }

    private static int rgStatusForPresentation(boolean bl, boolean bl2) {
        return bl2 && bl ? 1 : 0;
    }

    private static boolean rgStatusPublicationRequired(int n, boolean bl, boolean bl2) {
        return n != Mhi2qAaNavProxy.rgStatusForPresentation(bl, bl2);
    }

    private static boolean cardPublicationRequired(int n, boolean bl) {
        return n != (bl ? 1 : 0);
    }

    private static boolean routePublicationRequired(int n, boolean bl) {
        return n != (bl ? 1 : 0);
    }

    private static int maneuverStateForCard(boolean bl, boolean bl2) {
        return TurnCardPolicy.bapManeuverState(bl, bl2);
    }

    private static boolean publishRendererSnapshot() {
        boolean bl;
        if (!renderer.isReady()) {
            return false;
        }
        int[] nArray = ManeuverMapper.rendererManeuver(maneuver, roundaboutAngle, roundaboutExit);
        if (!Mhi2qAaNavProxy.rendererManeuverHasContent(nArray)) {
            presentationToken = 0;
            return renderer.sendClear();
        }
        if (presentationToken == 0) {
            presentationToken = Mhi2qAaNavProxy.allocatePresentationToken();
        }
        int n = TurnCardPolicy.actionThreshold(maneuver);
        boolean bl2 = distanceMeters >= 0 && distanceMeters <= n && n > 0;
        int n2 = 0;
        if (bl2) {
            n2 = distanceMeters * 16 / n;
            if (n2 < 0) {
                n2 = 0;
            }
            if (n2 > 16) {
                n2 = 16;
            }
        }
        if (bl = renderer.sendManeuver(nArray[0], nArray[1], nArray[2], nArray[3], null, laneGuidance, roundaboutExit, nArray[4] != 0, n2, bl2 ? 1 : 0, 1, presentationToken)) {
            Mhi2qAaNavProxy.log("MHI2Q AA turn card type=" + maneuver + " icon=" + nArray[0] + " direction=" + nArray[1] + " angle=" + nArray[2]);
        }
        return bl;
    }

    private static boolean rendererManeuverHasContent(int[] nArray) {
        return nArray != null && nArray.length > 0 && nArray[0] != 0;
    }

    private static int allocatePresentationToken() {
        presentationTokenCounter = Mhi2qAaNavProxy.nextPresentationTokenForTest(presentationTokenCounter);
        return presentationTokenCounter;
    }

    private static void stopSession() {
        try {
            if (gate != null) {
                gate.setBlocked(true);
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        publishedRgStatus = -1;
        publishedCardOpen = -1;
        publishedRouteDataAvailable = -1;
        try {
            if (rawService != null) {
                Mhi2qAaNavProxy.publishSnapshot(false, false, false);
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        try {
            if (cluster != null) {
                Mhi2qAaNavProxy.forceClusterCardUnavailable(false);
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        Mhi2qAaNavProxy.restoreClusterState();
        sessionStarted = false;
        publishedRgStatus = -1;
        publishedCardOpen = -1;
        publishedRouteDataAvailable = -1;
        lastPublishedSequence = -1;
        lastRenderedSequence = -1;
        turnCardWindowOpen = false;
        clusterCardAvailable = false;
        presentationToken = 0;
        try {
            renderer.sendClear();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        Mhi2qAaClusterComposition.setTurnCardVisible(false, 0);
        Mhi2qAaNavProxy.releaseBapToOem();
        Mhi2qAaNavProxy.log("MHI2Q AA BAP session stopped");
    }

    private static void rollbackStart() {
        publishedRgStatus = -1;
        publishedCardOpen = -1;
        publishedRouteDataAvailable = -1;
        try {
            if (rawService != null) {
                Mhi2qAaNavProxy.publishSnapshot(false, false, false);
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        try {
            if (cluster != null) {
                Mhi2qAaNavProxy.forceClusterCardUnavailable(false);
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        Mhi2qAaNavProxy.restoreClusterState();
        sessionStarted = false;
        publishedRgStatus = -1;
        publishedCardOpen = -1;
        publishedRouteDataAvailable = -1;
        lastPublishedSequence = -1;
        lastRenderedSequence = -1;
        turnCardWindowOpen = false;
        clusterCardAvailable = false;
        presentationToken = 0;
        try {
            renderer.sendClear();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        Mhi2qAaClusterComposition.setTurnCardVisible(false, 0);
        Mhi2qAaNavProxy.releaseBapToOem();
        Mhi2qAaNavProxy.log("MHI2Q AA BAP start rolled back");
    }

    private static synchronized void failOpen() {
        if (sessionStarted) {
            Mhi2qAaNavProxy.stopSession();
        } else {
            try {
                if (gate != null) {
                    gate.setBlocked(false);
                }
            }
            catch (Throwable throwable) {
                // empty catch block
            }
        }
    }

    private static void restoreClusterState() {
        try {
            if (cluster != null) {
                cluster.getDSIResponseContainer().setRgActive(previousRgActive);
                cluster.updateRgActive(previousRgActive);
                cluster.updateRGIString(cluster.getDSIResponseContainer().getRGIString());
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    private static void releaseBapToOem() {
        block6: {
            boolean bl = oemResyncPending;
            oemResyncPending = true;
            try {
                if (gate != null) {
                    gate.setBlocked(false);
                }
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            try {
                if (cluster != null) {
                    cluster.resyncMhi2qAaCombiBAP();
                    oemResyncPending = false;
                }
            }
            catch (Throwable throwable) {
                if (bl) break block6;
                Mhi2qAaNavProxy.log("MHI2Q OEM BAP replay pending: " + throwable);
            }
        }
    }

    private static void prepareOemMapImage() {
        Mhi2qAaNavProxy.setOemMapPresentation(true);
    }

    private static void setOemMapPresentation(boolean bl) {
        Mhi2qAaNavProxy.setMapScaleSuppressed(!bl || vcMenuRequested || vcMenuClosing);
    }

    private static void setMapScaleSuppressed(boolean bl) {
        int n;
        int n2 = n = bl ? 1 : 0;
        if (gate == null || appliedMapScaleSuppression == n) {
            return;
        }
        try {
            gate.setMapScaleBlocked(bl);
            if (!bl && cluster != null) {
                cluster.resyncMhi2qAaCombiBAP();
            }
            appliedMapScaleSuppression = n;
            Mhi2qAaNavProxy.log("MHI2Q AA OEM map scale " + (bl ? "neutralized" : "restored"));
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    public static void acknowledgeProjectedMapScaleInput(CombiBAPServiceNavi combiBAPServiceNavi) {
        try {
            if (combiBAPServiceNavi instanceof Mhi2qAaGatedCombiService) {
                combiBAPServiceNavi = ((Mhi2qAaGatedCombiService)combiBAPServiceNavi).getRealService();
            }
            if (combiBAPServiceNavi != null) {
                combiBAPServiceNavi.updateMapScale(0, false, 0, 0, false);
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    private static boolean oemMapShouldBeVisible(boolean bl, int n) {
        return !bl || n == 1;
    }

    private static boolean projectedCardsEnabled() {
        return vcActiveMode == 0;
    }

    private static void setClusterCardAvailable(boolean bl) {
        if (cluster == null || !Mhi2qAaNavProxy.clusterCardTransitionRequired(clusterCardAvailable, bl)) {
            return;
        }
        try {
            if (bl) {
                cluster.updateRGIString(new short[]{1});
            } else {
                cluster.setKDKVisibility(false);
            }
            clusterCardAvailable = bl;
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    private static void forceClusterCardUnavailable(boolean bl) {
        short[] sArray;
        cluster.setKDKVisibility(false);
        if (bl) {
            short[] sArray2 = new short[1];
            sArray = sArray2;
            sArray2[0] = 1;
        } else {
            sArray = null;
        }
        cluster.updateRGIString(sArray);
        clusterCardAvailable = false;
    }

    private static boolean clusterCardTransitionRequired(boolean bl, boolean bl2) {
        return bl != bl2;
    }

    private static void sendDescriptor(int n, int n2) {
        CombiBAPNaviManeuverDescriptor[] combiBAPNaviManeuverDescriptorArray = new CombiBAPNaviManeuverDescriptor[]{new CombiBAPNaviManeuverDescriptor(n, n2, 0, new byte[0])};
        rawService.updateManeuverDescriptor(combiBAPNaviManeuverDescriptorArray);
    }

    private static void sendExitView() {
        rawService.updateExitView((++exitViewSendCount & 1) == 0 ? 0 : 1, 0);
    }

    private static int[] formatDistance(int n) {
        return Mhi2qAaNavProxy.formatDistance(n, false);
    }

    private static int[] formatDestinationDistance(int n) {
        return Mhi2qAaNavProxy.formatDistance(n, true);
    }

    private static int[] formatDistance(int n, boolean bl) {
        int[] nArray = new int[]{-1, 0};
        if (n < 0 || distanceFormatter == null) {
            return nArray;
        }
        try {
            int n2 = Distance.getSystemUnit();
            boolean bl2 = n2 == 1 || n2 == 5 || n2 == 6;
            BAPDistanceFormatter.BAPDistance bAPDistance = bl ? distanceFormatter.formatDistanceToDestination(n, bl2) : distanceFormatter.formatDistanceToTurn(n, bl2);
            Class<?> clazz = bAPDistance.getClass();
            nArray[0] = (Integer)clazz.getMethod("getValue", new Class[0]).invoke((Object)bAPDistance, new Object[0]);
            nArray[1] = (Integer)clazz.getMethod("getUnit", new Class[0]).invoke((Object)bAPDistance, new Object[0]);
        }
        catch (Throwable throwable) {
            nArray[0] = -1;
            nArray[1] = 0;
        }
        return nArray;
    }

    private static String choosePositionText() {
        if (currentRoad != null && currentRoad.length() > 0) {
            return currentRoad;
        }
        if (nextRoad != null && nextRoad.length() > 0) {
            return nextRoad;
        }
        if (instruction != null && instruction.length() > 0) {
            return instruction;
        }
        return "\u2191";
    }

    private static String chooseTurnToText() {
        if (nextRoad != null && nextRoad.length() > 0) {
            return nextRoad;
        }
        if (instruction != null && instruction.length() > 0) {
            return instruction;
        }
        return "";
    }

    private static String turnToTextForCard(boolean bl) {
        return bl ? Mhi2qAaNavProxy.chooseTurnToText() : "";
    }

    private static boolean routeDataIsAvailable() {
        boolean bl;
        boolean bl2 = bl = "aa".equals(mode) || "carplay".equals(mode);
        if (!bl || updated <= 0L) {
            return false;
        }
        if (!navigationStateClaimed) {
            if (!NavigationStateReader.isFresh(mode, active, updated, System.currentTimeMillis())) {
                return false;
            }
            navigationStateClaimed = true;
        }
        return active;
    }

    private static void refresh() {
        NavigationSnapshot navigationSnapshot = stateReader.refresh(false);
        mode = navigationSnapshot.mode;
        sequence = navigationSnapshot.sequence;
        updated = navigationSnapshot.updated;
        active = navigationSnapshot.active;
        maneuver = navigationSnapshot.maneuver;
        roundaboutExit = navigationSnapshot.roundaboutExit;
        roundaboutAngle = navigationSnapshot.roundaboutAngle;
        distanceMeters = navigationSnapshot.distanceMeters;
        timeToStepSeconds = navigationSnapshot.timeToStepSeconds;
        destinationDistanceMeters = navigationSnapshot.destinationDistanceMeters;
        timeToDestinationSeconds = navigationSnapshot.timeToDestinationSeconds;
        currentRoad = navigationSnapshot.currentRoad;
        nextRoad = navigationSnapshot.nextRoad;
        instruction = navigationSnapshot.instruction;
        laneGuidance = navigationSnapshot.laneGuidance;
    }

    private static void log(String string) {
    }

    public static synchronized void resetForTest() {
        mode = "shadow";
        sequence = -1;
        updated = 0L;
        active = false;
        destinationDistanceMeters = -1;
        laneGuidance = new byte[0];
        timeToDestinationSeconds = -1;
        sessionStarted = false;
        oemResyncPending = false;
        carPlayActive = true;
        deactivationRequested = false;
        appliedMapScaleSuppression = -1;
        publishedRgStatus = -1;
        publishedCardOpen = -1;
        publishedRouteDataAvailable = -1;
        navigationStateClaimed = false;
        lastPublishedSequence = -1;
        lastRenderedSequence = -1;
        rendererHadFrame = false;
        rendererStateRevision = 0;
        lastRendererStateRevision = 0;
        rendererConnectionRevision = 0;
        lastRendererConnectionRevision = 0;
        turnCardWindowOpen = false;
        clusterCardAvailable = false;
        presentationTokenCounter = 0;
        presentationToken = 0;
        vcMenuRequested = false;
        vcMenuClosing = false;
        vcMenuSelection = 0;
        vcActiveMode = 0;
        vcMenuToken = 0;
        vcMenuTestDeadline = 0L;
        vcStorageFramework = null;
        vcStorageForTest = null;
        vcPreferenceLoaded = false;
        vcPreferenceRevision = 0;
        vcPersistedRevision = 0;
        vcStorageRetryAfter = 0L;
        stateReader.resetForTest("shadow");
    }

    static synchronized void setVcStorageForTest(IStorageAccess iStorageAccess) {
        vcStorageForTest = iStorageAccess;
    }

    static void syncVcPreferenceForTest() {
        Mhi2qAaNavProxy.syncVcPreference();
    }

    public static synchronized boolean overrideIsFreshForTest() {
        Mhi2qAaNavProxy.refresh();
        return Mhi2qAaNavProxy.routeDataIsAvailable();
    }

    public static synchronized int[] destinationStateForTest() {
        return new int[]{destinationDistanceMeters, timeToDestinationSeconds};
    }

    public static int[] maneuverDescriptorForTest(int n, int n2) {
        return ManeuverMapper.bapDescriptor(n, n2);
    }

    public static int[] rendererManeuverForTest(int n, int n2, int n3) {
        return ManeuverMapper.rendererManeuver(n, n2, n3);
    }

    public static synchronized boolean turnCardWindowForTest(int n, int n2, int n3, boolean bl) {
        int n4 = timeToStepSeconds;
        int n5 = distanceMeters;
        int n6 = maneuver;
        boolean bl2 = turnCardWindowOpen;
        timeToStepSeconds = n;
        distanceMeters = n2;
        maneuver = n3;
        turnCardWindowOpen = bl;
        boolean bl3 = Mhi2qAaNavProxy.shouldOpenTurnCard();
        timeToStepSeconds = n4;
        distanceMeters = n5;
        maneuver = n6;
        turnCardWindowOpen = bl2;
        return bl3;
    }

    public static int maneuverStateForCardForTest(boolean bl, boolean bl2) {
        return Mhi2qAaNavProxy.maneuverStateForCard(bl, bl2);
    }

    public static boolean rendererManeuverHasContentForTest(int n, int n2, int n3) {
        return Mhi2qAaNavProxy.rendererManeuverHasContent(ManeuverMapper.rendererManeuver(n, n2, n3));
    }

    public static boolean cardPresentedForTest(boolean bl, boolean bl2, boolean bl3, int n) {
        return Mhi2qAaNavProxy.isCardPresented(bl, bl2, bl3, n);
    }

    public static boolean forcePresentationEdgeForTest(boolean bl, boolean bl2, boolean bl3) {
        return Mhi2qAaNavProxy.shouldForcePresentationEdge(bl, bl2, bl3);
    }

    public static boolean closeCanCommitForTest(boolean bl, boolean bl2) {
        return Mhi2qAaNavProxy.closeCanCommit(bl, bl2);
    }

    public static int nextPresentationTokenForTest(int n) {
        int n2 = n + 1;
        return n2 == 0 ? 1 : n2;
    }

    public static int wrapMenuSelectionForTest(int n, int n2) {
        int n3 = (n + n2) % 2;
        return n3 < 0 ? n3 + 2 : n3;
    }

    public static boolean oemMapShouldBeVisibleForTest(boolean bl, int n) {
        return Mhi2qAaNavProxy.oemMapShouldBeVisible(bl, n);
    }

    public static boolean projectedCardsEnabledForTest() {
        return Mhi2qAaNavProxy.projectedCardsEnabled();
    }

    public static int retainPresentationTokenForTest(int n, int n2) {
        return n == 0 ? n2 : n;
    }

    public static boolean presentationTokenMatchesForTest(int n, int n2) {
        return n != 0 && n2 != 0 && n == n2;
    }

    public static boolean clusterCardTransitionRequiredForTest(boolean bl, boolean bl2) {
        return Mhi2qAaNavProxy.clusterCardTransitionRequired(bl, bl2);
    }

    public static synchronized String turnToTextForCardForTest(boolean bl) {
        return Mhi2qAaNavProxy.turnToTextForCard(bl);
    }

    public static int rgStatusForPresentationForTest(boolean bl, boolean bl2) {
        return Mhi2qAaNavProxy.rgStatusForPresentation(bl, bl2);
    }

    public static boolean rgStatusPublicationRequiredForTest(int n, boolean bl, boolean bl2) {
        return Mhi2qAaNavProxy.rgStatusPublicationRequired(n, bl, bl2);
    }

    public static boolean cardPublicationRequiredForTest(int n, boolean bl) {
        return Mhi2qAaNavProxy.cardPublicationRequired(n, bl);
    }

    public static boolean routePublicationRequiredForTest(int n, boolean bl) {
        return Mhi2qAaNavProxy.routePublicationRequired(n, bl);
    }

    static {
        appliedMapScaleSuppression = -1;
        publishedRgStatus = -1;
        publishedCardOpen = -1;
        publishedRouteDataAvailable = -1;
        lastPublishedSequence = -1;
        lastRenderedSequence = -1;
        vcActiveMode = 0;
        VC_MENU_COMMAND = new File("/tmp/mhi2q_vc_menu_test.command");
        renderer = new RendererServer();
        stateReader = new NavigationStateReader();
        renderer.setStateListener(new RendererServer.StateListener(){

            public void onRendererStateChanged(String string) {
                rendererStateRevision++;
                if (Mhi2qAaNavProxy.isRendererConnectionBoundary(string)) {
                    rendererConnectionRevision++;
                }
            }
        });
        Mhi2qAaClusterComposition.setPresentationListener(new Mhi2qAaClusterComposition.PresentationListener(){

            public boolean onTurnCardPresented(int n, int n2, int n3, int n4, int n5, int n6) {
                return renderer.sendPresented(n2, n3, n4, n5, n6);
            }

            public boolean onTurnCardHidden(int n) {
                if (vcMenuRequested || vcMenuClosing) {
                    return true;
                }
                return renderer.sendClear();
            }

            public boolean onVcMenuPresented(int n, int n2, int n3, int n4, int n5, int n6) {
                return renderer.sendPresented(n2, n3, n4, n5, n6);
            }

            public boolean onVcMenuHidden(int n) {
                return renderer.sendVcMenu(false, vcMenuSelection, vcActiveMode, 0);
            }
        });
        mode = "aa";
        sequence = -1;
        destinationDistanceMeters = -1;
        timeToDestinationSeconds = -1;
        currentRoad = "";
        nextRoad = "";
        instruction = "";
        laneGuidance = new byte[0];
    }
}

