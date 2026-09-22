package com.mibsi.carplay.core;

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

public final class Mhi2qAaClusterComposition implements Runnable {
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
   private static volatile boolean clusterProjectionEnabled = true;
   private static volatile boolean turnCardVisible;
   private static volatile int turnCardToken;
   private static volatile Mhi2qAaClusterComposition.PresentationListener presentationListener;
   private static volatile boolean menuVisible;
   private static volatile int menuToken;
   private static volatile int menuRevision;
   private static volatile int viewSize = 2;
   private static volatile boolean viewSizeKnown;
   private static int oemMenuContext = -1;
   private static int[] oemMenuLayers;
   private static volatile int skin;
   private static int appliedViewSize = -1;
   private static int appliedSkin = -1;
   private static int appliedGeometryRevision = -1;
   private static volatile int appliedTurnCardRevision = -1;
   private static volatile int appliedTurnCardToken;
   private static int notifiedTurnCardRevision = -1;
   private static int notifiedTurnCardToken;
   private static int notifiedSourceX = -1;
   private static int notifiedSourceY = -1;
   private static int notifiedSourceWidth = -1;
   private static int notifiedSourceHeight = -1;
   private static volatile boolean appliedTurnCardVisible;
   private static volatile boolean appliedMenuVisible;
   private static volatile int appliedMenuRevision = -1;
   private static volatile int appliedMenuToken;
   private static int notifiedMenuRevision = -1;
   private static int notifiedMenuToken;
   private static volatile int loggedContext = Integer.MIN_VALUE;
   private static boolean reassertPending;
   private static long lastReassertRequestMs;
   private static int publishedUiViewState = -1;
   private static int geometryRevision;
   private static long turnDisplayShowDeadlineMs;
   private static long turnDisplayHideDeadlineMs;
   private static volatile int turnCardRevision;
   private static int inTubeX = 1055;
   private static int inTubeY = 207;
   private static int inTubeCropX = 59;
   private static int inTubeCropY = 27;
   private static int inTubeCropW = 210;
   private static int inTubeCropH = 153;
   private static int popupX = 1091;
   private static int popupY = 110;
   private static int popupCropX = 59;
   private static int popupCropY = 27;
   private static int popupCropW = 210;
   private static int popupCropH = 153;
   private static String layoutName = "fallback-classic";

   private Mhi2qAaClusterComposition() {
   }

   public static void setPresentationListener(Mhi2qAaClusterComposition.PresentationListener var0) {
      synchronized (LOCK) {
         presentationListener = var0;
         notifiedTurnCardRevision = -1;
         notifiedMenuRevision = -1;
         notifiedSourceX = -1;
         LOCK.notifyAll();
      }
   }

   public static void prepare(Object var0) {
      if (var0 instanceof IContext) {
         synchronized (LOCK) {
            context = (IContext)var0;
            ensureWorkerLocked();
            LOCK.notifyAll();
         }
      }
   }

   public static void onActivate(Object var0) {
      prepare(var0);
      synchronized (LOCK) {
         androidAutoActive = true;
         clusterProjectionEnabled = true;
         nativeContextReady = false;
         reassertPending = false;
         ensureWorkerLocked();
         LOCK.notifyAll();
      }
   }

   public static void onDeactivate() {
      synchronized (LOCK) {
         androidAutoActive = false;
         nativeContextReady = false;
         reassertPending = false;
         ownershipRequested = false;
         clusterProjectionEnabled = true;
         if (turnCardVisible || turnCardToken != 0) {
            turnCardVisible = false;
            turnCardToken = 0;
            turnCardRevision++;
         }

         turnDisplayShowDeadlineMs = 0L;
         if (menuVisible || menuToken != 0) {
            menuVisible = false;
            menuToken = 0;
            menuRevision++;
         }

         try {
            new File("/tmp/mhi2q_aa_context70.request").delete();
         } catch (Throwable var3) {
         }

         LOCK.notifyAll();
      }
   }

   public static void setTurnCardVisible(boolean var0, int var1) {
      synchronized (LOCK) {
         int var3 = var0 ? var1 : 0;
         if (turnCardVisible != var0 || turnCardToken != var3) {
            boolean var4 = var0 && !turnCardVisible && !appliedTurnCardVisible;
            turnDisplayShowDeadlineMs = nextTurnCardShowDeadline(var4, var0, turnDisplayShowDeadlineMs, System.currentTimeMillis());
            turnCardVisible = var0;
            turnCardToken = var3;
            turnCardRevision++;
            if (var4) {
               Log.w("AAComposition", "turn overlay open delayed 600ms after BAP");
            }

            LOCK.notifyAll();
         }
      }
   }

   public static boolean isTurnCardVisibilityApplied(boolean var0) {
      synchronized (LOCK) {
         return turnCardVisible == var0
            && appliedTurnCardVisible == var0
            && appliedTurnCardRevision == turnCardRevision
            && appliedTurnCardToken == (var0 ? turnCardToken : 0);
      }
   }

   public static void setVcMenuVisible(boolean var0, int var1) {
      synchronized (LOCK) {
         var0 = var0 && isLargeView();
         int var3 = var0 ? var1 : 0;
         if (menuVisible != var0 || menuToken != var3) {
            menuVisible = var0;
            menuToken = var3;
            menuRevision++;
            LOCK.notifyAll();
         }
      }
   }

   public static boolean isVcMenuVisibilityApplied(boolean var0) {
      synchronized (LOCK) {
         return menuVisible == var0 && appliedMenuVisible == var0 && appliedMenuRevision == menuRevision && appliedMenuToken == (var0 ? menuToken : 0);
      }
   }

   public static void setClusterProjectionEnabled(boolean var0) {
      synchronized (LOCK) {
         if (clusterProjectionEnabled != var0) {
            clusterProjectionEnabled = var0;
            reassertPending = false;
            LOCK.notifyAll();
         }
      }
   }

   public static boolean isClusterProjectionEnabled() {
      return clusterProjectionEnabled;
   }

   public static void onViewSize(int var0) {
      int var1 = var0 == 1 ? 1 : 2;
      synchronized (LOCK) {
         viewSizeKnown = var0 == 1 || var0 == 2;
         if (viewSize != var1) {
            viewSize = var1;
            LOCK.notifyAll();
         }
      }
   }

   public static boolean isLargeView() {
      return viewSizeKnown && viewSize == 2;
   }

   public static boolean isProjectionReleased() {
      return !owningCluster && !ownershipRequested && loggedContext != Integer.MIN_VALUE && loggedContext != 70;
   }

   public static void onSkin(int var0) {
      synchronized (LOCK) {
         if (skin != var0) {
            skin = var0;
            LOCK.notifyAll();
         }
      }
   }

   public static boolean shouldBlockStockContextWrite() {
      return wantsCustomContext();
   }

   public static boolean shouldPinStockClusterContext() {
      return wantsCustomContext();
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
      if (worker == null || !worker.isAlive()) {
         Thread var0 = new Thread(new Mhi2qAaClusterComposition(), "MHI2Q-AA-VC-Composition");
         var0.setDaemon(true);

         try {
            var0.start();
            worker = var0;
         } catch (Throwable var2) {
            worker = null;
            Log.w("AAComposition", "worker start failed: " + var2);
         }
      }
   }

   public void run() {
      while (true) {
         try {
            reconcile();
         } catch (Throwable var6) {
            Log.w("AAComposition", "reconcile failed: " + var6);
         }

         synchronized (LOCK) {
            try {
               LOCK.wait(100L);
            } catch (InterruptedException var4) {
            }
         }
      }
   }

   private static void reconcile() {
      boolean var0 = androidAutoActive;
      boolean var1 = wantsCustomContext();
      IDisplayManager var2 = resolveDisplayManager();
      if (var2 == null) {
         OemClusterMapRenderGate.request(false);
      } else {
         refreshOemViewState();
         if (!isLargeView()) {
            setVcMenuVisible(false, 0);
         }

         int var3 = -1;

         try {
            var3 = var2.getCurrentContextID(1);
         } catch (Throwable var7) {
         }

         boolean var4 = OemClusterMapRenderGate.request(var1 && var3 == 70);
         publishUiViewState(var3);
         if (var3 != loggedContext) {
            loggedContext = var3;
            Log.w("AAComposition", "active=" + var0 + " native-context=" + var3);
         }

         if (var0 && var3 == 70) {
            nativeContextReady = true;
            if (reassertPending) {
               reassertPending = false;
               appliedViewSize = -1;
               appliedSkin = -1;
               appliedGeometryRevision = -1;
               Log.w("AAComposition", "native context 70 restored");
            }
         }

         if (!var1) {
            ownershipRequested = false;
            if (owningCluster && var3 >= 0 && var3 != 70) {
               owningCluster = false;
               reassertPending = false;
               Log.w("AAComposition", "OEM cluster map restored");
            }

            if (var3 == 70) {
               setVcMenuVisible(false, 0);
               applyLayers(var2);
               if (!isTurnCardVisibilityApplied(false) || !isVcMenuVisibilityApplied(false)) {
                  return;
               }

               if (!var4) {
                  return;
               }

               long var9 = System.currentTimeMillis();
               if (!reassertPending || var9 - lastReassertRequestMs >= 1000L) {
                  reassertPending = requestNativeContext(79);
                  lastReassertRequestMs = var9;
               }
            } else {
               applyOemMenu(var2, var3);
            }
         } else if (restoreOemMenuContext()) {
            if (var3 != 70) {
               ownershipRequested = nativeContextReady;
               if (nativeContextReady) {
                  owningCluster = true;
               }

               long var8 = System.currentTimeMillis();
               if (!reassertPending || var8 - lastReassertRequestMs >= 1000L) {
                  reassertPending = requestNativeContext(67);
                  lastReassertRequestMs = var8;
                  Log.w("AAComposition", "context drift " + var3 + " -> native reassert " + 70 + " requested=" + reassertPending);
               }

               if (nativeContextReady) {
                  applyLayers(var2);
               }
            } else {
               boolean var5 = var3 == 70;
               if (var5) {
                  ownershipRequested = true;
                  if (!owningCluster) {
                     owningCluster = true;
                     appliedViewSize = -1;
                     appliedSkin = -1;
                     appliedGeometryRevision = -1;
                     Log.w("AAComposition", "native context 70 acquired: {98,101,102,3}");
                  }

                  applyLayers(var2);
               } else if (!var1 && owningCluster) {
                  ownershipRequested = false;
                  hideLayers(var2);
                  owningCluster = false;
                  appliedViewSize = -1;
                  appliedSkin = -1;
                  appliedGeometryRevision = -1;
                  Log.w("AAComposition", "native context 70 released");
               } else {
                  ownershipRequested = false;
               }
            }
         }
      }
   }

   private static void applyOemMenu(IDisplayManager var0, int var1) {
      if (var1 >= 0 && var1 != 70) {
         boolean var2 = androidAutoActive && menuVisible && isLargeView();
         int var3 = menuRevision;
         int var4 = menuToken;
         if (!var2 && !appliedMenuVisible && oemMenuContext < 0) {
            appliedMenuRevision = var3;
            appliedMenuToken = 0;
            appliedTurnCardVisible = false;
            appliedTurnCardRevision = turnCardRevision;
            appliedTurnCardToken = 0;
            notifyVcMenuHidden(var3);
         } else if (oemMenuContext == var1 || restoreOemMenuContext()) {
            if (var2 && oemMenuContext < 0) {
               int[] var5 = var0.getDisplayables(var1);
               if (var5 == null || var5.length == 0) {
                  return;
               }

               int[] var6 = prependMenuLayer(var5);
               if (!declareOemContext(var1, var6)) {
                  return;
               }

               oemMenuLayers = (int[])var5.clone();
               oemMenuContext = var1;
            }

            var0.lockDisplayAndWait(1);

            try {
               if (var2) {
                  var0.setCropping(98, 1, 0, 0, 328, 180, 449, 87, 541, 297);
               }

               var0.setOpacity(98, 1, var2 ? 100 : 0);
            } finally {
               var0.unlockDisplayAndWait(1);
            }

            if (var0.getOpacity(98, 1) == (var2 ? 100 : 0)) {
               if (var2 || restoreOemMenuContext()) {
                  appliedMenuVisible = var2;
                  appliedMenuRevision = var3;
                  appliedMenuToken = var2 ? var4 : 0;
                  appliedTurnCardVisible = false;
                  appliedTurnCardRevision = turnCardRevision;
                  appliedTurnCardToken = 0;
                  if (var2) {
                     notifyVcMenuPresented(var3, var4, 0, 0, 328, 180);
                  } else {
                     notifyVcMenuHidden(var3);
                  }
               }
            }
         }
      }
   }

   static int[] prependMenuLayer(int[] var0) {
      int var1 = 0;

      for (int var2 = 0; var2 < var0.length; var2++) {
         if (var0[var2] != 98) {
            var1++;
         }
      }

      int[] var5 = new int[var1 + 1];
      var5[0] = 98;
      int var3 = 1;

      for (int var4 = 0; var4 < var0.length; var4++) {
         if (var0[var4] != 98) {
            var5[var3++] = var0[var4];
         }
      }

      return var5;
   }

   private static boolean restoreOemMenuContext() {
      if (oemMenuContext < 0) {
         return true;
      }

      if (!declareOemContext(oemMenuContext, oemMenuLayers)) {
         return false;
      }

      oemMenuContext = -1;
      oemMenuLayers = null;
      return true;
   }

   private static boolean declareOemContext(int var0, int[] var1) {
      IContext var2 = context;
      if (var2 != null && var2.getFramework() != null) {
         BundleContext var3 = var2.getFramework().getBundleCxt();
         if (var3 == null) {
            return false;
         }

         ServiceReference var4 = var3.getServiceReference(DSIDisplayManagement.class.getName());
         if (var4 == null) {
            return false;
         }

         try {
            Object var5 = var3.getService(var4);
            if (!(var5 instanceof DSIDisplayManagement)) {
               return false;
            }

            ((DSIDisplayManagement)var5).declareContexts(new DisplayContext[]{new DisplayContext(var0, var1)});
            return true;
         } finally {
            var3.ungetService(var4);
         }
      } else {
         return false;
      }
   }

   private static boolean requestNativeContext(int var0) {
      FileOutputStream var1 = null;

      try {
         var1 = new FileOutputStream("/tmp/mhi2q_aa_context70.request", false);
         var1.write(var0);
         var1.flush();
         return true;
      } catch (Throwable var13) {
         Log.w("AAComposition", "native context request failed: " + var13);
         return false;
      } finally {
         if (var1 != null) {
            try {
               var1.close();
            } catch (Throwable var12) {
            }
         }
      }
   }

   private static void publishUiViewState(int var0) {
      int var1 = uiSnapshotForTest(viewSize, skin, clusterProjectionEnabled, var0, publishedUiViewState);
      if (publishedUiViewState != var1) {
         try {
            writeUiViewState(new File("/tmp/mhi2q_aa_ui_view.state"), var1);
            publishedUiViewState = var1;
            Log.w(
               "AAComposition",
               "AA UI view="
                  + ((var1 & 0xFF) == 83 ? "sport-left" : ((var1 & 0xFF) == 67 ? "classic-large-gauges" : "large-center"))
                  + " projection="
                  + (var1 >>> 8 == 80)
            );
         } catch (Throwable var3) {
            Log.w("AAComposition", "AA UI view publish failed: " + var3);
         }
      }
   }

   static int uiSnapshotForTest(int var0, int var1, boolean var2, int var3, int var4) {
      boolean var5 = var2 || var3 == 70 || var3 < 0 && (var4 < 0 || var4 >>> 8 != 79);
      return uiViewStateForTest(var0, var1) | (var5 ? 80 : 79) << 8;
   }

   static void writeUiViewState(File var0, int var1) throws IOException {
      FileOutputStream var2 = new FileOutputStream(var0, false);

      try {
         var2.write(new byte[]{(byte)var1, (byte)(var1 >>> 8)});
      } finally {
         var2.close();
      }
   }

   private static IDisplayManager resolveDisplayManager() {
      IDisplayManager var0 = displayManager;
      if (var0 != null) {
         return var0;
      }

      IContext var1 = context;
      if (var1 == null) {
         return null;
      }

      try {
         if (var1.getFramework() == null || var1.getFramework().getHMIService() == null) {
            return null;
         }

         var0 = var1.getFramework().getHMIService().getDisplayManager();
         if (var0 != null) {
            displayManager = var0;
         }
      } catch (Throwable var3) {
      }

      return var0;
   }

   private static void refreshOemViewState() {
      IContext var0 = context;
      if (var0 != null && var0.getFramework() != null) {
         try {
            HMIService var1 = var0.getFramework().getHMIService();
            if (var1 == null) {
               return;
            }

            HMIModel var2 = var1.getModel(402521);
            if (!(var2 instanceof ChoiceModelGUI)) {
               return;
            }

            int var3 = ((ChoiceModelGUI)var2).getValue();
            int var4 = var3 == 1 ? 1 : 2;
            int var5 = skin;
            Layout var6 = null;
            HMITerminal var7 = var1.getHMITerminal(0);
            if (var7 instanceof HMITerminalEvo) {
               var5 = ((HMITerminalEvo)var7).getSkin();
            }

            if (var7 instanceof HMITerminalImpl) {
               var6 = ((HMITerminalImpl)var7).getLayout();
            }

            int var8 = inTubeX;
            int var9 = inTubeY;
            int var10 = inTubeCropX;
            int var11 = inTubeCropY;
            int var12 = inTubeCropW;
            int var13 = inTubeCropH;
            int var14 = popupX;
            int var15 = popupY;
            int var16 = popupCropX;
            int var17 = popupCropY;
            int var18 = popupCropW;
            int var19 = popupCropH;
            String var20 = layoutName;
            if (var6 != null) {
               var8 = var6.getIntegerConstant(58);
               var9 = var6.getIntegerConstant(59);
               var10 = var6.getIntegerConstant(122);
               var11 = var6.getIntegerConstant(123);
               var12 = var6.getIntegerConstant(124);
               var13 = var6.getIntegerConstant(125);
               var14 = var6.getIntegerConstant(60);
               var15 = var6.getIntegerConstant(61);
               var16 = var6.getIntegerConstant(118);
               var17 = var6.getIntegerConstant(119);
               var18 = var6.getIntegerConstant(120);
               var19 = var6.getIntegerConstant(121);
               var20 = var6.getClass().getName();
            }

            synchronized (LOCK) {
               viewSizeKnown = var3 == 1 || var3 == 2;
               boolean var22 = inTubeX != var8
                  || inTubeY != var9
                  || inTubeCropX != var10
                  || inTubeCropY != var11
                  || inTubeCropW != var12
                  || inTubeCropH != var13
                  || popupX != var14
                  || popupY != var15
                  || popupCropX != var16
                  || popupCropY != var17
                  || popupCropW != var18
                  || popupCropH != var19
                  || !layoutName.equals(var20);
               boolean var23 = viewSize != var4 || skin != var5;
               if (!var22 && !var23) {
                  return;
               }

               viewSize = var4;
               skin = var5;
               if (var22) {
                  inTubeX = var8;
                  inTubeY = var9;
                  inTubeCropX = var10;
                  inTubeCropY = var11;
                  inTubeCropW = var12;
                  inTubeCropH = var13;
                  popupX = var14;
                  popupY = var15;
                  popupCropX = var16;
                  popupCropY = var17;
                  popupCropW = var18;
                  popupCropH = var19;
                  layoutName = var20;
                  geometryRevision++;
               }

               Log.w("AAComposition", "VC view model=" + var3 + " skin=" + skin + " layout=" + layoutName);
            }
         } catch (Throwable var26) {
            Log.w("AAComposition", "VC view/Layout read failed: " + var26);
         }
      }
   }

   private static void applyLayers(IDisplayManager var0) {
      int var1 = viewSize;
      int var2 = skin;
      int var3 = geometryRevision;
      boolean var4;
      int var5;
      int var6;
      boolean var7;
      int var8;
      int var9;
      synchronized (LOCK) {
         var4 = turnCardVisible;
         var5 = turnCardRevision;
         var6 = turnCardToken;
         var7 = menuVisible;
         var8 = menuRevision;
         var9 = menuToken;
      }

      if (var7) {
         byte var18 = 0;
         byte var19 = 0;
         short var20 = 328;
         short var21 = 180;
         if (!appliedMenuVisible || appliedMenuRevision != var8 || appliedMenuToken != var9 || !layerOpacitiesMatch(var0, 100, 0, 0)) {
            if (!setGeometry(var0, var18, var19, var20, var21, 449, 87, 541, 297, -1)) {
               return;
            }

            appliedMenuVisible = true;
            appliedMenuRevision = var8;
            appliedMenuToken = var9;
            appliedTurnCardVisible = false;
         }

         notifyVcMenuPresented(var8, var9, var18, var19, var20, var21);
      } else {
         boolean var17 = appliedMenuVisible;
         int var11 = var1 != 1 ? popupCropX : inTubeCropX;
         int var12 = var1 != 1 ? popupCropY : inTubeCropY;
         int var13 = var1 != 1 ? popupCropW : inTubeCropW;
         int var14 = var1 != 1 ? popupCropH : inTubeCropH;
         if (appliedViewSize == var1
            && appliedSkin == var2
            && appliedGeometryRevision == var3
            && appliedTurnCardVisible == var4
            && appliedTurnCardRevision == var5
            && appliedTurnCardToken == var6
            && appliedMenuRevision == var8
            && !appliedMenuVisible
            && layerOpacitiesMatch(var0, var4 ? 100 : 0, var4 && var1 == 1 ? 100 : 0, var4 && var1 != 1 ? 100 : 0)) {
            notifyTurnCardVisibilityApplied(var4, var5, var6, var11, var12, var13, var14);
         } else {
            boolean var15;
            if (!var4) {
               var15 = hideLayers(var0);
            } else {
               if (!turnCardShowCanCommit(var5, var6)) {
                  return;
               }

               if (var1 != 1) {
                  var15 = setGeometry(var0, var11, var12, var13, var14, popupX, popupY, var13, var14, 102);
               } else {
                  var15 = setGeometry(var0, var11, var12, var13, var14, inTubeX, inTubeY, var13, var14, 101);
               }
            }

            if (var15) {
               appliedViewSize = var1;
               appliedSkin = var2;
               appliedGeometryRevision = var3;
               appliedTurnCardVisible = var4;
               appliedTurnCardRevision = var5;
               appliedTurnCardToken = var6;
               appliedMenuVisible = false;
               appliedMenuRevision = var8;
               appliedMenuToken = 0;
               if (var17) {
                  notifyVcMenuHidden(var8);
               }

               notifyTurnCardVisibilityApplied(var4, var5, var6, var11, var12, var13, var14);
            }
         }
      }
   }

   private static boolean turnCardShowCanCommit(int var0, int var1) {
      synchronized (LOCK) {
         if (turnCardVisible && turnCardRevision == var0 && turnCardToken == var1) {
            if (!turnCardShowDelayElapsed(turnDisplayShowDeadlineMs, System.currentTimeMillis())) {
               return false;
            }

            turnDisplayShowDeadlineMs = 0L;
            return true;
         } else {
            return false;
         }
      }
   }

   private static void notifyTurnCardVisibilityApplied(boolean var0, int var1, int var2, int var3, int var4, int var5, int var6) {
      if (var0) {
         notifyTurnCardPresented(var1, var2, var3, var4, var5, var6);
      } else {
         notifyTurnCardHidden(var1);
      }
   }

   private static void notifyTurnCardPresented(int var0, int var1, int var2, int var3, int var4, int var5) {
      Mhi2qAaClusterComposition.PresentationListener var6;
      synchronized (LOCK) {
         if (!presentationIsCurrentLocked(var0, var1)
            || notifiedTurnCardRevision == var0
               && notifiedTurnCardToken == var1
               && notifiedSourceX == var2
               && notifiedSourceY == var3
               && notifiedSourceWidth == var4
               && notifiedSourceHeight == var5) {
            return;
         }

         var6 = presentationListener;
         if (var6 == null) {
            return;
         }
      }

      boolean var13 = false;

      try {
         var13 = var6.onTurnCardPresented(var0, var1, var2, var3, var4, var5);
      } catch (Throwable var11) {
         Log.w("AAComposition", "presentation listener failed: " + var11);
      }

      if (var13) {
         synchronized (LOCK) {
            if (presentationIsCurrentLocked(var0, var1)) {
               notifiedTurnCardRevision = var0;
               notifiedTurnCardToken = var1;
               notifiedSourceX = var2;
               notifiedSourceY = var3;
               notifiedSourceWidth = var4;
               notifiedSourceHeight = var5;
            }
         }
      }
   }

   private static void notifyTurnCardHidden(int var0) {
      Mhi2qAaClusterComposition.PresentationListener var1;
      synchronized (LOCK) {
         if (!hiddenPresentationIsCurrentLocked(var0) || notifiedTurnCardRevision == var0 && notifiedTurnCardToken == 0) {
            return;
         }

         var1 = presentationListener;
         if (var1 == null) {
            return;
         }
      }

      boolean var8 = false;

      try {
         var8 = var1.onTurnCardHidden(var0);
      } catch (Throwable var6) {
         Log.w("AAComposition", "hidden listener failed: " + var6);
      }

      if (var8) {
         synchronized (LOCK) {
            if (hiddenPresentationIsCurrentLocked(var0)) {
               notifiedTurnCardRevision = var0;
               notifiedTurnCardToken = 0;
            }
         }
      }
   }

   private static void notifyVcMenuPresented(int var0, int var1, int var2, int var3, int var4, int var5) {
      Mhi2qAaClusterComposition.PresentationListener var6;
      synchronized (LOCK) {
         if (!menuPresentationIsCurrentLocked(var0, var1) || notifiedMenuRevision == var0 && notifiedMenuToken == var1) {
            return;
         }

         var6 = presentationListener;
         if (var6 == null) {
            return;
         }
      }

      boolean var13 = false;

      try {
         var13 = var6.onVcMenuPresented(var0, var1, var2, var3, var4, var5);
      } catch (Throwable var11) {
         Log.w("AAComposition", "menu presentation listener failed: " + var11);
      }

      if (var13) {
         synchronized (LOCK) {
            if (menuPresentationIsCurrentLocked(var0, var1)) {
               notifiedMenuRevision = var0;
               notifiedMenuToken = var1;
            }
         }
      }
   }

   private static void notifyVcMenuHidden(int var0) {
      Mhi2qAaClusterComposition.PresentationListener var1;
      synchronized (LOCK) {
         if (!hiddenMenuPresentationIsCurrentLocked(var0) || notifiedMenuRevision == var0 && notifiedMenuToken == 0) {
            return;
         }

         var1 = presentationListener;
         if (var1 == null) {
            return;
         }
      }

      boolean var8 = false;

      try {
         var8 = var1.onVcMenuHidden(var0);
      } catch (Throwable var6) {
         Log.w("AAComposition", "menu hidden listener failed: " + var6);
      }

      if (var8) {
         synchronized (LOCK) {
            if (hiddenMenuPresentationIsCurrentLocked(var0)) {
               notifiedMenuRevision = var0;
               notifiedMenuToken = 0;
            }
         }
      }
   }

   private static boolean presentationIsCurrentLocked(int var0, int var1) {
      return var1 != 0
         && turnCardVisible
         && turnCardRevision == var0
         && turnCardToken == var1
         && appliedTurnCardVisible
         && appliedTurnCardRevision == var0
         && appliedTurnCardToken == var1;
   }

   private static boolean hiddenPresentationIsCurrentLocked(int var0) {
      return !turnCardVisible
         && turnCardToken == 0
         && turnCardRevision == var0
         && !appliedTurnCardVisible
         && appliedTurnCardRevision == var0
         && appliedTurnCardToken == 0;
   }

   private static boolean menuPresentationIsCurrentLocked(int var0, int var1) {
      return var1 != 0
         && menuVisible
         && menuRevision == var0
         && menuToken == var1
         && appliedMenuVisible
         && appliedMenuRevision == var0
         && appliedMenuToken == var1;
   }

   private static boolean hiddenMenuPresentationIsCurrentLocked(int var0) {
      return !menuVisible && menuToken == 0 && menuRevision == var0 && !appliedMenuVisible && appliedMenuRevision == var0 && appliedMenuToken == 0;
   }

   private static boolean setGeometry(IDisplayManager var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9) {
      turnDisplayHideDeadlineMs = 0L;
      var0.lockDisplayAndWait(1);

      try {
         var0.setCropping(98, 1, var1, var2, var3, var4, var5, var6, var7, var8);
         var0.setOpacity(98, 1, 100);
         if (var9 >= 0) {
            var0.setPosition(var9, 1, var5, var6);
            var0.setOpacity(var9, 1, 100);
            var0.setOpacity(var9 == 102 ? 101 : 102, 1, 0);
         } else {
            var0.setOpacity(101, 1, 0);
            var0.setOpacity(102, 1, 0);
         }
      } finally {
         var0.unlockDisplayAndWait(1);
      }

      if (!layerOpacitiesMatch(var0, 100, var9 == 101 ? 100 : 0, var9 == 102 ? 100 : 0)) {
         return false;
      }

      Log.w(
         "AAComposition",
         "mode="
            + modeName()
            + " layout="
            + layoutName
            + " backing="
            + var9
            + " src="
            + var1
            + ","
            + var2
            + " "
            + var3
            + "x"
            + var4
            + " dst="
            + var5
            + ","
            + var6
            + " "
            + var7
            + "x"
            + var8
      );
      return true;
   }

   private static boolean hideLayers(IDisplayManager var0) {
      long var1 = System.currentTimeMillis();
      if (turnDisplayHideDeadlineMs == 0L) {
         turnDisplayHideDeadlineMs = var1 + 500L;
         Log.w("AAComposition", "complete turn overlay close delayed 500ms");
         return false;
      }

      if (var1 < turnDisplayHideDeadlineMs) {
         return false;
      }

      var0.lockDisplayAndWait(1);

      try {
         var0.setOpacity(98, 1, 0);
         var0.setOpacity(101, 1, 0);
         var0.setOpacity(102, 1, 0);
      } finally {
         var0.unlockDisplayAndWait(1);
      }

      if (!layerOpacitiesMatch(var0, 0, 0, 0)) {
         return false;
      }

      turnDisplayHideDeadlineMs = 0L;
      appliedTurnCardVisible = false;
      appliedMenuVisible = false;
      Log.w("AAComposition", "complete turn overlay close committed");
      return true;
   }

   private static boolean layerOpacitiesMatch(IDisplayManager var0, int var1, int var2, int var3) {
      try {
         return var0.getOpacity(98, 1) == var1 && var0.getOpacity(101, 1) == var2 && var0.getOpacity(102, 1) == var3;
      } catch (Throwable var5) {
         return false;
      }
   }

   private static String modeName() {
      if (viewSize != 1) {
         return "large";
      } else {
         return skin == 1 ? "sport-small" : "classic-small";
      }
   }

   static int[] geometryForTest(int var0, int var1) {
      if (var0 != 1) {
         return new int[]{59, 27, 210, 153, 1091, 110, 102};
      } else {
         return var1 == 1 ? new int[]{0, 0, 328, 180, 984, 139, 101} : new int[]{59, 27, 210, 153, 1055, 207, 101};
      }
   }

   static int uiViewStateForTest(int var0, int var1) {
      if (var0 != 1) {
         return 76;
      } else {
         return var1 == 1 ? 83 : 67;
      }
   }

   static int[] menuGeometryForTest() {
      return new int[]{0, 0, 328, 180, 449, 87, 541, 297};
   }

   static boolean wantsCustomContextForTest(boolean var0, boolean var1, boolean var2) {
      return var0 && var1;
   }

   static int nextTurnCardRevisionForTest(boolean var0, int var1, int var2, boolean var3, int var4) {
      int var5 = var3 ? var4 : 0;
      return var0 == var3 && var1 == var5 ? var2 : var2 + 1;
   }

   static boolean turnCardRequestNeedsApplyForTest(boolean var0, int var1, int var2, boolean var3, int var4, int var5) {
      return var0 != var3 || var1 != var4 || var2 != var5;
   }

   static long nextTurnCardShowDeadline(boolean var0, boolean var1, long var2, long var4) {
      if (!var1) {
         return 0L;
      } else {
         return var0 ? var4 + 600L : var2;
      }
   }

   static boolean turnCardShowDelayElapsed(long var0, long var2) {
      return var0 == 0L || var2 >= var0;
   }

   public interface PresentationListener {
      boolean onTurnCardPresented(int var1, int var2, int var3, int var4, int var5, int var6);

      boolean onTurnCardHidden(int var1);

      boolean onVcMenuPresented(int var1, int var2, int var3, int var4, int var5, int var6);

      boolean onVcMenuHidden(int var1);
   }
}
