package com.mibsi.carplay.rgd;

import com.mibsi.carplay.aa.ManeuverMapper;
import com.mibsi.carplay.aa.NavigationSnapshot;
import com.mibsi.carplay.aa.NavigationStateReader;
import com.mibsi.carplay.core.Mhi2qAaClusterComposition;
import com.mibsi.carplay.framework.Log;
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
import de.audi.tghu.navi.app.cluster.BAPDistanceFormatter.BAPDistance;
import de.esolutions.fw.util.commons.job.DispatcherBase;
import java.io.File;
import org.osgi.framework.ServiceReference;

public final class Mhi2qAaNavProxy implements Runnable {
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
   private static int appliedMapScaleSuppression = -1;
   private static boolean previousRgActive;
   private static boolean oemResyncPending;
   private static int exitViewSendCount;
   private static int publishedRgStatus = -1;
   private static int publishedCardOpen = -1;
   private static int publishedRouteDataAvailable = -1;
   private static boolean navigationStateClaimed;
   private static int lastPublishedSequence = -1;
   private static int lastRenderedSequence = -1;
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
   private static volatile int vcActiveMode = 0;
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
   private static final File VC_MENU_COMMAND = new File("/tmp/mhi2q_vc_menu_test.command");
   private static long vcMenuTestDeadline;
   private static final RendererServer renderer = new RendererServer();
   private static final NavigationStateReader stateReader = new NavigationStateReader();
   private static String mode = "aa";
   private static int sequence = -1;
   private static long updated;
   private static boolean active;
   private static int maneuver;
   private static int roundaboutExit;
   private static int roundaboutAngle;
   private static int distanceMeters;
   private static int timeToStepSeconds;
   private static int destinationDistanceMeters = -1;
   private static int timeToDestinationSeconds = -1;
   private static String currentRoad = "";
   private static String nextRoad = "";
   private static String instruction = "";
   private static byte[] laneGuidance = new byte[0];

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
      presentationTokenCounter = nextPresentationTokenForTest(presentationTokenCounter);
      vcMenuToken = presentationTokenCounter;
      lastRenderedSequence = -1;
      return true;
   }

   public static synchronized void closeVcSettingsMenu() {
      if (vcMenuRequested) {
         vcMenuRequested = false;
         vcMenuClosing = true;
         vcMenuTestDeadline = 0L;
      }
   }

   public static synchronized void toggleVcSettingsMenu() {
      if (vcMenuRequested) {
         closeVcSettingsMenu();
      } else {
         openVcSettingsMenu();
      }
   }

   public static boolean isVcSettingsMenuRequested() {
      return vcMenuRequested;
   }

   public static synchronized boolean moveVcSettingsSelection(int var0) {
      enforceVcMenuView();
      if (vcMenuRequested && var0 != 0) {
         vcMenuSelection = wrapMenuSelectionForTest(vcMenuSelection, var0);
         return true;
      } else {
         return false;
      }
   }

   public static synchronized boolean selectVcSettingsItem() {
      enforceVcMenuView();
      if (!vcMenuRequested) {
         return false;
      }

      if (!vcPreferenceLoaded || vcActiveMode != vcMenuSelection) {
         vcPreferenceLoaded = true;
         vcPreferenceRevision++;
         vcStorageRetryAfter = 0L;
      }

      vcActiveMode = vcMenuSelection;
      Mhi2qAaClusterComposition.setClusterProjectionEnabled(vcActiveMode != 1);
      if (vcActiveMode == 1) {
         Mhi2qAaClusterComposition.setTurnCardVisible(false, 0);
      }

      closeVcSettingsMenu();
      appliedMapScaleSuppression = -1;
      wakeWorker();
      return true;
   }

   static synchronized void pollVcMenuTestCommand(long var0) {
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
      wakeWorker();
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
      wakeWorker();
   }

   public static void startTransport(Object var0) {
      try {
         if (!(var0 instanceof IContext)) {
            return;
         }

         synchronized (Mhi2qAaNavProxy.class) {
            serviceManager = ((IContext)var0).getServiceManager();

            try {
               vcStorageFramework = ((IContext)var0).getFramework();
            } catch (Throwable var4) {
            }

            startWorker();
         }
      } catch (Throwable var6) {
      }
   }

   public static synchronized CombiBAPServiceNavi prepareCombiBAPService(ClusterService var0, CombiBAPServiceNavi var1) {
      try {
         CombiBAPServiceNavi var2 = var1;
         if (var1 instanceof Mhi2qAaGatedCombiService) {
            var2 = ((Mhi2qAaGatedCombiService)var1).getRealService();
         }

         if (rawService != null && rawService != var2 && sessionStarted) {
            stopSession();
         }

         if (cluster != var0) {
            appliedMapScaleSuppression = -1;
         }

         cluster = var0;
         rawService = var2;
         distanceFormatter = var0 == null ? null : new BAPDistanceFormatter(var0.getMhi2qAaLogChannel());
         lastPublishedSequence = -1;
         if (var2 == null) {
            gate = null;
            log("MHI2Q AA BAP service detached");
            return null;
         } else {
            gate = new Mhi2qAaGatedCombiService(var2);
            appliedMapScaleSuppression = -1;
            gate.setBlocked(wantsProjectedBap() || sessionStarted);
            startWorker();
            log("MHI2Q AA BAP gate installed");
            return gate;
         }
      } catch (Throwable var3) {
         gate = null;
         rawService = var1;
         return var1;
      }
   }

   private static void startWorker() {
      if (!workerStarted) {
         Thread var0 = new Thread(new Mhi2qAaNavProxy(), "MHI2Q-AA-BAP");
         var0.setDaemon(true);

         try {
            workerThread = var0;
            var0.start();
            workerStarted = true;
         } catch (Throwable var2) {
            workerThread = null;
            workerStarted = false;
         }
      }
   }

   public void run() {
      while (true) {
         try {
            Thread.sleep(250L);
         } catch (InterruptedException var3) {
         }

         try {
            syncVcPreference();
            tick();
         } catch (Throwable var2) {
            failOpen();
         }
      }
   }

   private static void wakeWorker() {
      Thread var0 = workerThread;
      if (var0 != null) {
         var0.interrupt();
      }
   }

   private static void syncVcPreference() {
      long var5 = System.currentTimeMillis();
      IFrameworkAccess var0;
      IStorageAccess var1;
      boolean var2;
      int var3;
      int var4;
      synchronized (Mhi2qAaNavProxy.class) {
         if (var5 < vcStorageRetryAfter) {
            return;
         }

         var0 = vcStorageFramework;
         var1 = vcStorageForTest;
         var2 = vcPreferenceLoaded;
         var3 = vcPreferenceRevision;
         var4 = vcActiveMode;
         if (var2 && var3 == vcPersistedRevision) {
            return;
         }
      }

      try {
         IStorageAccess var7 = var1 != null ? var1 : (var0 == null ? null : var0.getStorageMgr());
         if (var7 == null) {
            return;
         }

         if (!var2) {
            int var8 = var7.getInt(9000, 1, 0);
            int var9 = var8 == 1 ? 1 : 0;
            boolean var10 = false;
            synchronized (Mhi2qAaNavProxy.class) {
               if (!vcPreferenceLoaded && vcPreferenceRevision == var3) {
                  vcPreferenceLoaded = true;
                  vcPersistedRevision = var3;
                  vcActiveMode = var9;
                  if (vcMenuRequested) {
                     vcMenuSelection = var9;
                  }

                  appliedMapScaleSuppression = -1;
                  var10 = carPlayActive;
               }
            }

            if (var10) {
               Mhi2qAaClusterComposition.setClusterProjectionEnabled(var9 != 1);
               if (var9 == 1) {
                  Mhi2qAaClusterComposition.setTurnCardVisible(false, 0);
               }
            }

            return;
         }

         var7.setInt(9000, 1, var4);
         if (var7.getInt(9000, 1, -1) != var4) {
            throw new IllegalStateException("VC preference write not visible");
         }

         synchronized (Mhi2qAaNavProxy.class) {
            if (vcPreferenceRevision == var3) {
               vcPersistedRevision = var3;
            }

            vcStorageRetryAfter = 0L;
         }
      } catch (Throwable var18) {
         synchronized (Mhi2qAaNavProxy.class) {
            vcStorageRetryAfter = var5 + 5000L;
         }

         Log.w("VCMenu", "preference storage unavailable: " + var18);
      }
   }

   private static synchronized void tick() {
      pollVcMenuTestCommand(System.currentTimeMillis());
      renderer.connect();
      int var0 = rendererConnectionRevision;
      if (var0 != lastRendererConnectionRevision) {
         presentationToken = 0;
         lastRenderedSequence = -1;
         lastRendererConnectionRevision = var0;
      }

      enforceVcMenuView();
      resolveInfrastructure();
      if (!carPlayActive) {
         if (sessionStarted) {
            stopSession();
         } else if (oemResyncPending || gate != null && gate.isBlocked()) {
            releaseBapToOem();
         }

         if (deactivationRequested) {
            deactivationRequested = false;
            prepareOemMapImage();

            try {
               renderer.sendClear();
            } catch (Throwable var15) {
            }

            Mhi2qAaClusterComposition.setTurnCardVisible(false, 0);
            log("MHI2Q AA OEM state restored after disconnect");
         }
      } else {
         setOemMapPresentation(oemMapShouldBeVisible(carPlayActive, vcActiveMode));
         if (!wantsProjectedBap() && sessionStarted) {
            Mhi2qAaClusterComposition.setTurnCardVisible(false, 0);
            Mhi2qAaClusterComposition.setVcMenuVisible(false, 0);
            if (!Mhi2qAaClusterComposition.isProjectionReleased()) {
               return;
            }

            stopSession();
         } else if (!wantsProjectedBap() && (oemResyncPending || gate != null && gate.isBlocked())) {
            releaseBapToOem();
         }

         refresh();
         boolean var1 = routeDataIsAvailable();
         boolean var2 = vcMenuRequested || vcMenuClosing;
         if (vcMenuRequested && renderer.isReady() && (wantsProjectedBap() || Mhi2qAaClusterComposition.isProjectionReleased())) {
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

         if (wantsProjectedBap()) {
            if (rawService != null) {
               if (!sessionStarted && !startSession(var1)) {
                  return;
               }

               boolean var3 = var1 && projectedCardsEnabled() && shouldOpenTurnCard();
               if (var3 != turnCardWindowOpen) {
                  turnCardWindowOpen = var3;
                  lastPublishedSequence = -1;
                  lastRenderedSequence = -1;
                  presentationToken = 0;
                  log("MHI2Q AA turn card window " + (var3 ? "opened" : "closed") + " eta=" + timeToStepSeconds + "s distance=" + distanceMeters + "m");
               }

               if (turnCardWindowOpen && !var2 && sequence != lastRenderedSequence && publishRendererSnapshot()) {
                  lastRenderedSequence = sequence;
               }
            } else if (sessionStarted) {
               stopSession();
            }

            boolean var19 = renderer.isFrameReady();
            int var4 = rendererStateRevision;
            boolean var5 = false;
            var0 = rendererConnectionRevision;
            if (var0 != lastRendererConnectionRevision) {
               presentationToken = 0;
               lastRenderedSequence = -1;
               lastRendererConnectionRevision = var0;
               var5 = true;
            }

            boolean var6 = var4 != lastRendererStateRevision;
            if (var6 || var19 != rendererHadFrame) {
               lastPublishedSequence = -1;
               if (!var19) {
                  lastRenderedSequence = -1;
               }
            }

            boolean var7 = var1 && !var5 && isCardPresented(sessionStarted, turnCardWindowOpen, var19, maneuver) && !var2;
            boolean var8 = clusterCardAvailable && !var7;
            boolean var9 = rgStatusPublicationRequired(publishedRgStatus, var7, true);
            boolean var10 = cardPublicationRequired(publishedCardOpen, var7);
            boolean var11 = routePublicationRequired(publishedRouteDataAvailable, var1);
            if (sessionStarted && var8 && (var9 || var10 || var11)) {
               try {
                  publishSnapshot(false, var1, true);
                  lastPublishedSequence = sequence;
               } catch (Throwable var17) {
                  failOpen();
                  var7 = false;
               }
            }

            if (var8) {
               Mhi2qAaClusterComposition.setTurnCardVisible(false, 0);
            }

            boolean var12 = closeCanCommit(var8, Mhi2qAaClusterComposition.isTurnCardVisibilityApplied(false));
            boolean var13 = shouldForcePresentationEdge(var7, rendererHadFrame, var6);
            if (sessionStarted && !var8 && (sequence != lastPublishedSequence || var9 || var10 || var11)) {
               try {
                  publishSnapshot(var7, var1, true);
                  lastPublishedSequence = sequence;
               } catch (Throwable var16) {
                  failOpen();
                  var7 = false;
               }
            }

            rendererHadFrame = var19;
            lastRendererStateRevision = var4;
            if (sessionStarted && var12) {
               setClusterCardAvailable(var7);
            }

            if (var13) {
               Mhi2qAaClusterComposition.setTurnCardVisible(false, 0);
            }

            if (var2) {
               Mhi2qAaClusterComposition.setTurnCardVisible(false, 0);
            } else if (!var8) {
               Mhi2qAaClusterComposition.setTurnCardVisible(var7, var7 ? presentationToken : 0);
            }
         }
      }
   }

   private static boolean isCardPresented(boolean var0, boolean var1, boolean var2, int var3) {
      return var0 && var1 && var2 && var3 != 0;
   }

   private static boolean shouldForcePresentationEdge(boolean var0, boolean var1, boolean var2) {
      return var0 && var1 && var2;
   }

   private static boolean closeCanCommit(boolean var0, boolean var1) {
      return !var0 || var1;
   }

   private static boolean isRendererConnectionBoundary(String var0) {
      return "connected".equals(var0)
         || "disconnected".equals(var0)
         || "send-failed".equals(var0)
         || "disconnect-request".equals(var0)
         || "disposed".equals(var0);
   }

   private static boolean shouldOpenTurnCard() {
      return TurnCardPolicy.shouldOpen(timeToStepSeconds, distanceMeters, maneuver, turnCardWindowOpen);
   }

   private static void resolveInfrastructure() {
      if (rawService == null && serviceManager != null) {
         try {
            ServiceReference[] var0 = serviceManager.getServiceReferences(CombiBAPServiceNavi.class);
            if (var0 != null) {
               for (int var1 = 0; var1 < var0.length; var1++) {
                  Object var2 = serviceManager.getService(var0[var1]);
                  if (var2 instanceof CombiBAPServiceNavi) {
                     serviceReference = var0[var1];
                     rawService = (CombiBAPServiceNavi)var2;
                     gate = new Mhi2qAaGatedCombiService(rawService);
                     appliedMapScaleSuppression = -1;
                     gate.setBlocked(wantsProjectedBap() || sessionStarted);
                     lastPublishedSequence = -1;
                     log("MHI2Q AA BAP OSGi service acquired");
                     break;
                  }
               }
            }
         } catch (Throwable var4) {
         }
      }

      if (rawService != null && gate != null && !installScheduled) {
         try {
            Navigation var5 = Navigation.getInstance();
            if (var5 == null) {
               return;
            }

            DispatcherBase var6 = var5.getDispatcher();
            if (var6 == null) {
               return;
            }

            installScheduled = true;
            var6.execute(new Mhi2qAaNavProxy$3());
         } catch (Throwable var3) {
            installScheduled = false;
         }
      }
   }

   private static synchronized void installGateOnNavigationJobs() {
      installScheduled = false;

      try {
         Navigation var0 = Navigation.getInstance();
         if (var0 == null) {
            return;
         }

         ClusterService var1 = var0.getClusterService();
         if (var1 == null || rawService == null) {
            return;
         }

         CombiBAPServiceNavi var2 = var1.getCombiBAPListenerCombiService();
         if (var2 == gate) {
            if (cluster != var1) {
               appliedMapScaleSuppression = -1;
            }

            cluster = var1;
            return;
         }

         if (var2 instanceof Mhi2qAaGatedCombiService) {
            gate = (Mhi2qAaGatedCombiService)var2;
            rawService = gate.getRealService();
            appliedMapScaleSuppression = -1;
         } else if (var2 != null) {
            rawService = var2;
            gate = new Mhi2qAaGatedCombiService(rawService);
            appliedMapScaleSuppression = -1;
         }

         if (gate == null) {
            return;
         }

         if (cluster != var1) {
            appliedMapScaleSuppression = -1;
         }

         cluster = var1;
         distanceFormatter = new BAPDistanceFormatter(var1.getMhi2qAaLogChannel());
         gate.setBlocked(wantsProjectedBap() || sessionStarted);
         var1.setCombiBAPListenerCombiService(gate);
         log("MHI2Q AA BAP gate installed on NavigationJobs");
      } catch (Throwable var3) {
      }
   }

   public static synchronized void onOemRgActive(boolean var0) {
      if (sessionStarted) {
         previousRgActive = var0;
      }
   }

   private static boolean wantsProjectedBap() {
      return carPlayActive && projectedCardsEnabled();
   }

   static synchronized void enforceVcMenuView() {
      if (!Mhi2qAaClusterComposition.isLargeView()) {
         closeVcSettingsMenu();
      }
   }

   private static boolean startSession(boolean var0) {
      if (cluster != null && rawService != null && gate != null) {
         try {
            previousRgActive = cluster.getDSIResponseContainer().isRgActive();
            publishedRgStatus = -1;
            publishedCardOpen = -1;
            publishedRouteDataAvailable = -1;
            gate.setBlocked(true);
            cluster.getDSIResponseContainer().setRgActive(true);
            forceClusterCardUnavailable(true);
            publishSnapshot(false, var0, true);
            sessionStarted = true;
            lastPublishedSequence = sequence;
            lastRenderedSequence = -1;
            turnCardWindowOpen = false;
            log("MHI2Q AA BAP ownership started route=" + var0);
            return true;
         } catch (Throwable var2) {
            rollbackStart();
            return false;
         }
      } else {
         return false;
      }
   }

   private static void publishSnapshot(boolean var0, boolean var1, boolean var2) throws Exception {
      int var3 = rgStatusForPresentation(var0, var2);
      boolean var4 = rgStatusPublicationRequired(publishedRgStatus, var0, var2);
      boolean var5 = cardPublicationRequired(publishedCardOpen, var0);
      boolean var6 = routePublicationRequired(publishedRouteDataAvailable, var1);
      boolean var7 = var4 || var5 || var6;
      if (var7) {
         rawService.updateRGStatus(var3);
         rawService.updateActiveRGType(0);
      }

      if (var0) {
         int[] var8 = ManeuverMapper.bapDescriptor(maneuver, roundaboutAngle);
         sendDescriptor(var8[0], var8[1]);
      } else {
         sendDescriptor(0, 0);
      }

      int var12 = TurnCardPolicy.actionThreshold(maneuver);
      boolean var9 = distanceMeters >= 0 && distanceMeters <= var12;
      int var10 = 0;
      if (var9 && var12 > 0) {
         var10 = distanceMeters * 100 / var12;
         if (var10 < 0) {
            var10 = 0;
         }

         if (var10 > 100) {
            var10 = 100;
         }
      }

      if (var0) {
         int[] var11 = formatDistance(distanceMeters);
         rawService.updateDistanceToNextManeuver(var11[0], var11[1], var9, var10);
      } else {
         rawService.updateDistanceToNextManeuver(-1, 0, false, 0);
      }

      int[] var13 = var1 ? formatDestinationDistance(destinationDistanceMeters) : new int[]{-1, 0};
      rawService.updateDistanceToDestination(var13[0], var13[1], false);
      rawService.updateTimeToDestination(0, 0, var1 ? timeToDestinationSeconds : -1L);
      rawService.updateCurrentPositionInfo(var1 ? choosePositionText() : "");
      rawService.updateTurnToInfo(turnToTextForCard(var0), "");
      rawService.updateManeuverState(maneuverStateForCard(var0, var9));
      sendExitView();
      if (var7) {
         publishedRgStatus = var3;
         publishedCardOpen = var0 ? 1 : 0;
         publishedRouteDataAvailable = var1 ? 1 : 0;
      }
   }

   private static int rgStatusForPresentation(boolean var0, boolean var1) {
      return var1 && var0 ? 1 : 0;
   }

   private static boolean rgStatusPublicationRequired(int var0, boolean var1, boolean var2) {
      return var0 != rgStatusForPresentation(var1, var2);
   }

   private static boolean cardPublicationRequired(int var0, boolean var1) {
      return var0 != (var1 ? 1 : 0);
   }

   private static boolean routePublicationRequired(int var0, boolean var1) {
      return var0 != (var1 ? 1 : 0);
   }

   private static int maneuverStateForCard(boolean var0, boolean var1) {
      return TurnCardPolicy.bapManeuverState(var0, var1);
   }

   private static boolean publishRendererSnapshot() {
      if (!renderer.isReady()) {
         return false;
      }

      int[] var0 = ManeuverMapper.rendererManeuver(maneuver, roundaboutAngle, roundaboutExit);
      if (!rendererManeuverHasContent(var0)) {
         presentationToken = 0;
         return renderer.sendClear();
      }

      if (presentationToken == 0) {
         presentationToken = allocatePresentationToken();
      }

      int var1 = TurnCardPolicy.actionThreshold(maneuver);
      boolean var2 = distanceMeters >= 0 && distanceMeters <= var1 && var1 > 0;
      int var3 = 0;
      if (var2) {
         var3 = distanceMeters * 16 / var1;
         if (var3 < 0) {
            var3 = 0;
         }

         if (var3 > 16) {
            var3 = 16;
         }
      }

      boolean var4 = renderer.sendManeuver(
         var0[0], var0[1], var0[2], var0[3], null, laneGuidance, roundaboutExit, var0[4] != 0, var3, var2 ? 1 : 0, 1, presentationToken
      );
      if (var4) {
         log("MHI2Q AA turn card type=" + maneuver + " icon=" + var0[0] + " direction=" + var0[1] + " angle=" + var0[2]);
      }

      return var4;
   }

   private static boolean rendererManeuverHasContent(int[] var0) {
      return var0 != null && var0.length > 0 && var0[0] != 0;
   }

   private static int allocatePresentationToken() {
      presentationTokenCounter = nextPresentationTokenForTest(presentationTokenCounter);
      return presentationTokenCounter;
   }

   private static void stopSession() {
      try {
         if (gate != null) {
            gate.setBlocked(true);
         }
      } catch (Throwable var4) {
      }

      publishedRgStatus = -1;
      publishedCardOpen = -1;
      publishedRouteDataAvailable = -1;

      try {
         if (rawService != null) {
            publishSnapshot(false, false, false);
         }
      } catch (Throwable var3) {
      }

      try {
         if (cluster != null) {
            forceClusterCardUnavailable(false);
         }
      } catch (Throwable var2) {
      }

      restoreClusterState();
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
      } catch (Throwable var1) {
      }

      Mhi2qAaClusterComposition.setTurnCardVisible(false, 0);
      releaseBapToOem();
      log("MHI2Q AA BAP session stopped");
   }

   private static void rollbackStart() {
      publishedRgStatus = -1;
      publishedCardOpen = -1;
      publishedRouteDataAvailable = -1;

      try {
         if (rawService != null) {
            publishSnapshot(false, false, false);
         }
      } catch (Throwable var3) {
      }

      try {
         if (cluster != null) {
            forceClusterCardUnavailable(false);
         }
      } catch (Throwable var2) {
      }

      restoreClusterState();
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
      } catch (Throwable var1) {
      }

      Mhi2qAaClusterComposition.setTurnCardVisible(false, 0);
      releaseBapToOem();
      log("MHI2Q AA BAP start rolled back");
   }

   private static synchronized void failOpen() {
      if (sessionStarted) {
         stopSession();
      } else {
         try {
            if (gate != null) {
               gate.setBlocked(false);
            }
         } catch (Throwable var1) {
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
      } catch (Throwable var1) {
      }
   }

   private static void releaseBapToOem() {
      boolean var0 = oemResyncPending;
      oemResyncPending = true;

      try {
         if (gate != null) {
            gate.setBlocked(false);
         }
      } catch (Throwable var2) {
      }

      try {
         if (cluster != null) {
            cluster.resyncMhi2qAaCombiBAP();
            oemResyncPending = false;
         }
      } catch (Throwable var3) {
         if (!var0) {
            log("MHI2Q OEM BAP replay pending: " + var3);
         }
      }
   }

   private static void prepareOemMapImage() {
      setOemMapPresentation(true);
   }

   private static void setOemMapPresentation(boolean var0) {
      setMapScaleSuppressed(!var0 || vcMenuRequested || vcMenuClosing);
   }

   private static void setMapScaleSuppressed(boolean var0) {
      int var1 = var0 ? 1 : 0;
      if (gate != null && appliedMapScaleSuppression != var1) {
         try {
            gate.setMapScaleBlocked(var0);
            if (!var0 && cluster != null) {
               cluster.resyncMhi2qAaCombiBAP();
            }

            appliedMapScaleSuppression = var1;
            log("MHI2Q AA OEM map scale " + (var0 ? "neutralized" : "restored"));
         } catch (Throwable var3) {
         }
      }
   }

   public static void acknowledgeProjectedMapScaleInput(CombiBAPServiceNavi var0) {
      try {
         if (var0 instanceof Mhi2qAaGatedCombiService) {
            var0 = ((Mhi2qAaGatedCombiService)var0).getRealService();
         }

         if (var0 != null) {
            var0.updateMapScale(0, false, 0, 0, false);
         }
      } catch (Throwable var2) {
      }
   }

   private static boolean oemMapShouldBeVisible(boolean var0, int var1) {
      return !var0 || var1 == 1;
   }

   private static boolean projectedCardsEnabled() {
      return vcActiveMode == 0;
   }

   private static void setClusterCardAvailable(boolean var0) {
      if (cluster != null && clusterCardTransitionRequired(clusterCardAvailable, var0)) {
         try {
            if (var0) {
               cluster.updateRGIString(new short[]{1});
            } else {
               cluster.setKDKVisibility(false);
            }

            clusterCardAvailable = var0;
         } catch (Throwable var2) {
         }
      }
   }

   private static void forceClusterCardUnavailable(boolean var0) {
      cluster.setKDKVisibility(false);
      cluster.updateRGIString(var0 ? new short[]{1} : null);
      clusterCardAvailable = false;
   }

   private static boolean clusterCardTransitionRequired(boolean var0, boolean var1) {
      return var0 != var1;
   }

   private static void sendDescriptor(int var0, int var1) {
      CombiBAPNaviManeuverDescriptor[] var2 = new CombiBAPNaviManeuverDescriptor[]{new CombiBAPNaviManeuverDescriptor(var0, var1, 0, new byte[0])};
      rawService.updateManeuverDescriptor(var2);
   }

   private static void sendExitView() {
      exitViewSendCount++;
      rawService.updateExitView((exitViewSendCount & 1) == 0 ? 0 : 1, 0);
   }

   private static int[] formatDistance(int var0) {
      return formatDistance(var0, false);
   }

   private static int[] formatDestinationDistance(int var0) {
      return formatDistance(var0, true);
   }

   private static int[] formatDistance(int var0, boolean var1) {
      int[] var2 = new int[]{-1, 0};
      if (var0 >= 0 && distanceFormatter != null) {
         try {
            int var3 = Distance.getSystemUnit();
            boolean var4 = var3 == 1 || var3 == 5 || var3 == 6;
            BAPDistance var5 = var1 ? distanceFormatter.formatDistanceToDestination(var0, var4) : distanceFormatter.formatDistanceToTurn(var0, var4);
            Class var6 = var5.getClass();
            var2[0] = (Integer)var6.getMethod("getValue").invoke(var5);
            var2[1] = (Integer)var6.getMethod("getUnit").invoke(var5);
         } catch (Throwable var7) {
            var2[0] = -1;
            var2[1] = 0;
         }

         return var2;
      } else {
         return var2;
      }
   }

   private static String choosePositionText() {
      if (currentRoad != null && currentRoad.length() > 0) {
         return currentRoad;
      } else if (nextRoad != null && nextRoad.length() > 0) {
         return nextRoad;
      } else {
         return instruction != null && instruction.length() > 0 ? instruction : "↑";
      }
   }

   private static String chooseTurnToText() {
      if (nextRoad != null && nextRoad.length() > 0) {
         return nextRoad;
      } else {
         return instruction != null && instruction.length() > 0 ? instruction : "";
      }
   }

   private static String turnToTextForCard(boolean var0) {
      return var0 ? chooseTurnToText() : "";
   }

   private static boolean routeDataIsAvailable() {
      boolean var0 = "aa".equals(mode) || "carplay".equals(mode);
      if (var0 && updated > 0L) {
         if (!navigationStateClaimed) {
            if (!NavigationStateReader.isFresh(mode, active, updated, System.currentTimeMillis())) {
               return false;
            }

            navigationStateClaimed = true;
         }

         return active;
      } else {
         return false;
      }
   }

   private static void refresh() {
      NavigationSnapshot var0 = stateReader.refresh(false);
      mode = var0.mode;
      sequence = var0.sequence;
      updated = var0.updated;
      active = var0.active;
      maneuver = var0.maneuver;
      roundaboutExit = var0.roundaboutExit;
      roundaboutAngle = var0.roundaboutAngle;
      distanceMeters = var0.distanceMeters;
      timeToStepSeconds = var0.timeToStepSeconds;
      destinationDistanceMeters = var0.destinationDistanceMeters;
      timeToDestinationSeconds = var0.timeToDestinationSeconds;
      currentRoad = var0.currentRoad;
      nextRoad = var0.nextRoad;
      instruction = var0.instruction;
      laneGuidance = var0.laneGuidance;
   }

   private static void log(String var0) {
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

   static synchronized void setVcStorageForTest(IStorageAccess var0) {
      vcStorageForTest = var0;
   }

   static void syncVcPreferenceForTest() {
      syncVcPreference();
   }

   public static synchronized boolean overrideIsFreshForTest() {
      refresh();
      return routeDataIsAvailable();
   }

   public static synchronized int[] destinationStateForTest() {
      return new int[]{destinationDistanceMeters, timeToDestinationSeconds};
   }

   public static int[] maneuverDescriptorForTest(int var0, int var1) {
      return ManeuverMapper.bapDescriptor(var0, var1);
   }

   public static int[] rendererManeuverForTest(int var0, int var1, int var2) {
      return ManeuverMapper.rendererManeuver(var0, var1, var2);
   }

   public static synchronized boolean turnCardWindowForTest(int var0, int var1, int var2, boolean var3) {
      int var4 = timeToStepSeconds;
      int var5 = distanceMeters;
      int var6 = maneuver;
      boolean var7 = turnCardWindowOpen;
      timeToStepSeconds = var0;
      distanceMeters = var1;
      maneuver = var2;
      turnCardWindowOpen = var3;
      boolean var8 = shouldOpenTurnCard();
      timeToStepSeconds = var4;
      distanceMeters = var5;
      maneuver = var6;
      turnCardWindowOpen = var7;
      return var8;
   }

   public static int maneuverStateForCardForTest(boolean var0, boolean var1) {
      return maneuverStateForCard(var0, var1);
   }

   public static boolean rendererManeuverHasContentForTest(int var0, int var1, int var2) {
      return rendererManeuverHasContent(ManeuverMapper.rendererManeuver(var0, var1, var2));
   }

   public static boolean cardPresentedForTest(boolean var0, boolean var1, boolean var2, int var3) {
      return isCardPresented(var0, var1, var2, var3);
   }

   public static boolean forcePresentationEdgeForTest(boolean var0, boolean var1, boolean var2) {
      return shouldForcePresentationEdge(var0, var1, var2);
   }

   public static boolean closeCanCommitForTest(boolean var0, boolean var1) {
      return closeCanCommit(var0, var1);
   }

   public static int nextPresentationTokenForTest(int var0) {
      int var1 = var0 + 1;
      return var1 == 0 ? 1 : var1;
   }

   public static int wrapMenuSelectionForTest(int var0, int var1) {
      int var2 = (var0 + var1) % 2;
      return var2 < 0 ? var2 + 2 : var2;
   }

   public static boolean oemMapShouldBeVisibleForTest(boolean var0, int var1) {
      return oemMapShouldBeVisible(var0, var1);
   }

   public static boolean projectedCardsEnabledForTest() {
      return projectedCardsEnabled();
   }

   public static int retainPresentationTokenForTest(int var0, int var1) {
      return var0 == 0 ? var1 : var0;
   }

   public static boolean presentationTokenMatchesForTest(int var0, int var1) {
      return var0 != 0 && var1 != 0 && var0 == var1;
   }

   public static boolean clusterCardTransitionRequiredForTest(boolean var0, boolean var1) {
      return clusterCardTransitionRequired(var0, var1);
   }

   public static synchronized String turnToTextForCardForTest(boolean var0) {
      return turnToTextForCard(var0);
   }

   public static int rgStatusForPresentationForTest(boolean var0, boolean var1) {
      return rgStatusForPresentation(var0, var1);
   }

   public static boolean rgStatusPublicationRequiredForTest(int var0, boolean var1, boolean var2) {
      return rgStatusPublicationRequired(var0, var1, var2);
   }

   public static boolean cardPublicationRequiredForTest(int var0, boolean var1) {
      return cardPublicationRequired(var0, var1);
   }

   public static boolean routePublicationRequiredForTest(int var0, boolean var1) {
      return routePublicationRequired(var0, var1);
   }

   static {
      renderer.setStateListener(new Mhi2qAaNavProxy$1());
      Mhi2qAaClusterComposition.setPresentationListener(new Mhi2qAaNavProxy$2());
   }
}
