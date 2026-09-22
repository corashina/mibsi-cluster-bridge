package de.audi.tghu.navi.app.cluster;

import com.mibsi.carplay.core.CarPlayClusterInput;
import de.audi.atip.interapp.combi.bap.audio.data.CombiBAPTMCInfoMessage;
import de.audi.atip.interapp.combi.bap.navi.CombiBAPServiceNavi;
import de.audi.atip.interapp.combi.bap.navi.CombiBAPServiceNaviListener;
import de.audi.atip.interapp.combi.bap.navi.data.CombiBAPDestinationInfo;
import de.audi.atip.interapp.combi.bap.navi.data.CombiBAPDestinationListEntry;
import de.audi.atip.interapp.combi.bap.navi.data.CombiBAPNaviDestination;
import de.audi.atip.interapp.combi.bap.navi.data.CombiBAPNaviLaneGuidanceData;
import de.audi.atip.interapp.combi.bap.navi.data.CombiBAPNaviManeuverDescriptor;
import de.audi.atip.interapp.combi.bap.navi.data.CombiBAPSemiDynamicRouteInfo;
import de.audi.atip.interapp.combi.bap.navi.data.FormattedDestination;
import de.audi.atip.log.LogChannel;
import de.audi.atip.metrics.DateMetric;
import de.audi.atip.metrics.Distance;
import de.audi.atip.mmicombi.IViewSizeManager;
import de.audi.tghu.command.CommandList;
import de.audi.tghu.command.ICommandListFactory;
import de.audi.tghu.navi.app.NavigationEnv;
import de.audi.tghu.navi.app.OperationManager;
import de.audi.tghu.navi.app.SpeechManager;
import de.audi.tghu.navi.app.SpeechManager.GuidanceModeSettingsListener;
import de.audi.tghu.navi.app.audio.AudioStateMachine;
import de.audi.tghu.navi.app.audio.LastAnnouncementHandler;
import de.audi.tghu.navi.app.cluster.BAPDistanceFormatter.BAPDistance;
import de.audi.tghu.navi.app.cluster.CombiBAPListener.1;
import de.audi.tghu.navi.app.cluster.CombiBAPListener.2;
import de.audi.tghu.navi.app.cluster.CombiBAPListener.3;
import de.audi.tghu.navi.app.cluster.CombiBAPListener.4;
import de.audi.tghu.navi.app.cluster.CombiBAPListener.5;
import de.audi.tghu.navi.app.cluster.CombiBAPListener.6;
import de.audi.tghu.navi.app.cluster.CombiBAPListener.7;
import de.audi.tghu.navi.app.command.LIGetStateCommand;
import de.audi.tghu.navi.app.command.RGStopGuidanceCommand;
import de.audi.tghu.navi.app.command.via.RemoveViaPointsCommand;
import de.audi.tghu.navi.app.li.SpellerStack;
import de.audi.tghu.navi.app.li.sc.SpellerContextManager;
import de.audi.tghu.navi.app.map.AbstractMap;
import de.audi.tghu.navi.app.map.MapManager;
import de.audi.tghu.navi.app.map.context.CtxPositionMap;
import de.audi.tghu.navi.app.map.handler.ISetupHandler;
import de.audi.tghu.navi.app.map.handler.IZoomHandler;
import de.audi.tghu.navi.app.util.Util;
import de.esolutions.fw.util.commons.Buffer;
import java.util.Arrays;
import org.dsi.ifc.navigation.BapManeuverDescriptor;
import org.dsi.ifc.navigation.BapTurnToInfo;
import org.dsi.ifc.navigation.NavLaneGuidanceData;
import org.dsi.ifc.tmc.TmcMessage;

public class CombiBAPListener implements CombiBAPServiceNaviListener, GuidanceModeSettingsListener, BAPTMCSymbolMapping {
   private static final char LINE_FEED = '\n';
   protected NavigationEnv env;
   protected LogChannel logChannel;
   protected CombiBAPServiceNavi combiservice = null;
   protected ClusterService clusterservice = null;
   private final SpeechManager speechManager;
   private final OperationManager operationManager;
   private final AudioStateMachine audioStateMachine;
   protected final MapManager mapManager;
   protected final ICommandListFactory commandListFactory;
   private final IViewSizeManager viewSizeManagerFPK;
   private String currentPositionInfo = "";
   private int directionAngle = 65535;
   private int directionSymbolic = 255;
   private int rgStatus = 0;
   private int maneuverDistance = -1;
   private boolean maneuverShowBargraph = false;
   private int maneuverBargraph = 0;
   private int destinationDistance = -1;
   private boolean destinationDistanceIsToStopOver = false;
   private long destinationTime = -1L;
   private int destinationTimeType = 15;
   private int destinationTimeUnit = 0;
   private static final int RG_TYPE_INVALID = -1;
   private int rgType = -1;
   private String turnToInfo = "";
   private String signPost = "";
   private boolean laneGuidanceEnable = false;
   private CombiBAPNaviLaneGuidanceData[] laneGuidanceData = null;
   private CombiBAPNaviManeuverDescriptor[] maneuverDescriptor = null;
   private boolean maneuverDescriptorReceivedForCurrentRG = false;
   private int voiceGuidanceState = 1;
   private int infoStatesGPS = 255;
   private int infoStatesGPSBackup = 255;
   private int infoStatesNavi = 255;
   private int infoStatesNaviBackup = 255;
   private int maneuverState = 0;
   private CombiBAPTMCInfoMessage[] xUrgentMessages = null;
   private int tmcSymbolOnRoute = 0;
   private int altitude = -1;
   private boolean showSplashScreen = false;
   private CombiBAPDestinationInfo destInfo;
   private CombiBAPNaviDestination homeAddress = null;
   private int exitViewVariant;
   private int exitviewID = 0;
   private CombiBAPDestinationListEntry[] favoriteDestinationsList = new CombiBAPDestinationListEntry[0];
   private int voiceGuidanceSetup = 7;
   private boolean poiSearchSupported = false;
   private int mapColor = 2;
   protected int mapOrientation = 2;
   private boolean largeMapView = true;
   private boolean leftSideMenuOpen;
   private boolean rightSideMenuOpen;
   private boolean autoZoomActive = false;
   protected int autoZoomSetting = 0;
   protected int activeMapType = 2;
   protected int mainMapSetup = 255;
   protected int mapView = 0;
   protected boolean lvdsMapVisible = false;
   private CombiBAPSemiDynamicRouteInfo semiDynRouteInfo;
   private boolean mainMapSetupSupported = false;
   private final int supportedMapTypes;
   private int supportedMapViews;
   private int supportedSupplementaryMapViews;
   protected boolean supplementaryMapViewVisible = false;
   protected int supplementaryMapView = 0;
   protected final boolean intersectionAutoZoomSupported;
   private boolean validRGTypeReceived = false;
   private final BAPDistanceFormatter bapDistanceFormatter;
   private boolean forceShowInitScreen = true;
   private BAPDistance mapScale = null;
   private int onlineNavigationState = 0;
   private int bufferProgress = 255;
   private final int onlineNavigationSystem;
   private boolean naviIsRunningOnSmartphone = false;
   protected boolean waitingForStartGuidanceResponse = false;
   private boolean popupButtonPressed = false;
   private CombiBAPDestinationListEntry[] lastDestinations = new CombiBAPDestinationListEntry[0];

   public CombiBAPListener(
      ClusterService var1,
      LogChannel var2,
      NavigationEnv var3,
      SpeechManager var4,
      OperationManager var5,
      AudioStateMachine var6,
      MapManager var7,
      ICommandListFactory var8,
      IViewSizeManager var9
   ) {
      this.env = var3;
      this.clusterservice = var1;
      this.logChannel = var2;
      this.speechManager = var4;
      this.operationManager = var5;
      this.audioStateMachine = var6;
      this.mapManager = var7;
      this.commandListFactory = var8;
      this.viewSizeManagerFPK = var9;
      this.bapDistanceFormatter = new BAPDistanceFormatter(var2);
      this.mapManager.getMapEventDispatcher().addListener(new CombiRouteGuidanceMapEventListener(var2, this));
      this.supportedMapTypes = this.getSupportedMapTypes();
      this.supportedMapViews = this.getSupportedMapViews();
      this.supportedSupplementaryMapViews = this.getSupportedSupplementaryMapViews();
      this.intersectionAutoZoomSupported = this.setupIntersectionZoomSupported();
      if (Util.isClusterMapFPK(var3.getFramework()) || Util.isClusterMMI(var3.getFramework())) {
         this.rgType = 4;
      }

      var2.log(
         1000000,
         "CombiBAPListener - supportedMapTypes: %1, supportedMapViews: %2, supportedSupplementaryMapViews: %3",
         this.supportedMapTypes,
         this.supportedMapViews,
         this.supportedSupplementaryMapViews
      );
      if (!Util.isClusterMapAvailable(var3.getFramework())) {
         this.onlineNavigationSystem = 0;
      } else if (Util.isGoogleEarthPresent(var3.getFramework())) {
         this.onlineNavigationSystem = 1;
      } else {
         this.onlineNavigationSystem = 0;
      }

      this.initExitViewVariant();
      var4.registerSettingsListener(this);
   }

   protected boolean setupIntersectionZoomSupported() {
      return !Util.isHURegionAsia();
   }

   protected int getSupportedMapTypes() {
      if (Util.isClusterMapAvailable(this.env.getFramework())) {
         byte var1 = 6;
         if (Util.isClusterMapFPK(this.env.getFramework())) {
            var1 |= 8;
            if (!Util.isHURegionAsia()) {
               var1 |= 1;
            }
         }

         return var1;
      } else {
         return 0;
      }
   }

   protected int getSupportedMapViews() {
      if (!Util.isClusterMapAvailable(this.env.getFramework())) {
         return 0;
      }

      byte var1 = 1;
      boolean var2 = !Util.isLockFeatureNavMapAdvancedMapEnabled(this.env) || !this.mapManager.getNaviInterface().isFeatureToBeLocked();
      if (Util.isGoogleEarthPresent(this.env.getFramework()) && var2) {
         var1 |= 2;
      }

      if (Util.isTrafficMapAvailable(this.env.getFramework())) {
         var1 |= 4;
      }

      return Util.isOnlyOneOrNoBitSet(var1) ? 0 : var1;
   }

   protected int getSupportedSupplementaryMapViews() {
      return Util.isClusterKDKAvailable(this.env.getFramework()) && !Util.isClusterMMI(this.env.getFramework()) ? 1 : 0;
   }

   public void setLastDestinationsList(CombiBAPNaviDestination[] var1, String[] var2, FormattedDestination[] var3) {
      this.logChannel.log(10000000, "CombiBAPListener#setLastDestinationsList lastDest.length=%1", var1.length);
      if (this.logChannel.isDebug2()) {
         this.logChannel.log(100000000, "CombiBAPListener#setLastDestinationsList lastDest=%1", Arrays.asList(var1));
         this.logChannel.log(100000000, "CombiBAPListener#setLastDestinationsList descriptions=%1", Arrays.asList(var2));
      }

      this.lastDestinations = new CombiBAPDestinationListEntry[var1.length];

      for (int var4 = 0; var4 < var1.length; var4++) {
         CombiBAPDestinationListEntry var5;
         if (var3 == null) {
            var5 = new CombiBAPDestinationListEntry(var4 + 1, var1[var4].getPOIType(), var2[var4], new CombiBAPNaviDestination[]{var1[var4]});
         } else {
            var5 = new CombiBAPDestinationListEntry(var4 + 1, var1[var4].getPOIType(), var2[var4], new CombiBAPNaviDestination[]{var1[var4]}, var3[var4]);
         }

         this.lastDestinations[var4] = var5;
      }

      this.updateLastDestinationsList();
   }

   public void setFavoritesDestinationsList(CombiBAPNaviDestination[] var1, String[] var2) {
      this.setFavoritesDestinationsList(var1, var2, null);
   }

   public void setFavoritesDestinationsList(CombiBAPNaviDestination[] var1, String[] var2, FormattedDestination[] var3) {
      this.logChannel.log(10000000, "CombiBAPListener#updateFavoritesDestinationsList favorites.length=%1", var1.length);
      if (this.logChannel.isDebug2()) {
         this.logChannel.log(100000000, "CombiBAPListener#updateFavoritesDestinationsList favorites=%1", Arrays.asList(var1));
         this.logChannel.log(100000000, "CombiBAPListener#updateFavoritesDestinationsList descriptions=%1", Arrays.asList(var2));
      }

      this.favoriteDestinationsList = new CombiBAPDestinationListEntry[var1.length];

      for (int var4 = 0; var4 < var1.length; var4++) {
         CombiBAPDestinationListEntry var5;
         if (var3 == null) {
            var5 = new CombiBAPDestinationListEntry(var4 + 1, var1[var4].getPOIType(), var2[var4], new CombiBAPNaviDestination[]{var1[var4]});
         } else {
            var5 = new CombiBAPDestinationListEntry(var4 + 1, var1[var4].getPOIType(), var2[var4], new CombiBAPNaviDestination[]{var1[var4]}, var3[var4]);
         }

         this.favoriteDestinationsList[var4] = var5;
      }

      this.updateFavoriteDestinationsList();
   }

   private ISetupHandler checkedGetSetup() {
      AbstractMap var1 = this.mapManager.getMapKombi();
      if (var1 != null) {
         ISetupHandler var2 = var1.getSetup();
         if (var2 == null) {
            this.logChannel.log(100000, "CombiBAPListener#checkedGetSetup() - setup unavailable");
         }

         return var2;
      } else {
         this.logChannel.log(100000, "CombiBAPListener#checkedGetSetup() - no kombi map available");
         return null;
      }
   }

   public void initFromSetup() {
      this.logChannel.log(10000000, "CombiBAPListener#initFromSetup()");
      if (this.mainMapandKombiMapSetupInitialized()) {
         ISetupHandler var1 = this.checkedGetSetup();
         if (var1 != null) {
            this.mapView = this.setupMapPresentation2BAP(var1.getMapRepresentation());
            this.autoZoomSetting = this.setupAutoZoom2BAP(var1.getAutoZoom());
            this.mapColor = this.setupMapColor2BAP(var1.getDayNightView());
            this.activeMapType = this.setupMapType2BAP(var1.getMapType());
            this.mapOrientation = this.setupMapOrientation2BAP(var1.getOrientation());
         }

         this.updateMapView();
         this.updateMapScale();
         this.updateMapColor();
         this.updateMapType();
         this.updateMapOrientation();
      } else {
         this.logChannel.log(100000, "CombiBAPListener#initFromSetup() - one of the map instance is not initialized, cannot initFromSetup!");
      }
   }

   public int setupAutoZoom2BAP(int var1) {
      switch (var1) {
         case 0:
            return 1;
         case 1:
            if (Util.isHURegionAsia()) {
               return 1;
            }

            return 2;
         case 2:
            return 0;
         default:
            this.logChannel.log(100000, "CombiBAPListener#setupAutoZoom2BAP() - unknown auto-zoom type: %1", var1);
            return this.autoZoomSetting;
      }
   }

   public int setupMapColor2BAP(int var1) {
      switch (var1) {
         case 0:
            return 0;
         case 1:
            return 1;
         case 2:
            return 2;
         default:
            this.logChannel.log(10000, "CombiBAPListener#setupMapColor2BAP() - unknown map color: %1", var1);
            return this.mapColor;
      }
   }

   public int setupMapType2BAP(int var1) {
      switch (var1) {
         case 0:
            return 0;
         case 1:
            return 1;
         case 2:
            return 2;
         case 3:
            return 3;
         default:
            this.logChannel.log(100000, "CombiBAPListener#setupMapType2BAP() - unknown map type: %1", var1);
            return this.activeMapType;
      }
   }

   public int setupMapOrientation2BAP(int var1) {
      switch (var1) {
         case 0:
            return 1;
         case 1:
            return 2;
         default:
            this.logChannel.log(100000, "CombiBAPListener#setupMapOrientation2BAP() - unknown map orientation: %1", var1);
            return this.mapOrientation;
      }
   }

   public int setupMapPresentation2BAP(int var1) {
      switch (var1) {
         case 0:
            return 0;
         case 1:
            return 1;
         case 2:
            return 2;
         default:
            this.logChannel.log(100000, "CombiBAPListener#setupMapPresentation2BAP() - unknown map representation: %1", var1);
            return this.mapView;
      }
   }

   private void updateAll() {
      this.logChannel.log(10000000, "CombiBAPListener#updateAll()");
      if (this.combiservice != null) {
         this.updateInitScreen();
         this.updateAltitude();
         this.updateCompassInfo();
         this.updateCurrentPositionInfo();
         this.updateDestinationInfo();
         this.updateDistanceToDestination();
         this.updateExitView();
         this.updateFavoriteDestinationsList();
         this.updateFSGSetup();
         this.updateInfoStates();
         this.updateLaneGuidance();
         this.updateRGStatusAndType();
         this.updateManeuverDescriptor();
         this.updateDistanceToNextManeuver();
         this.updateExitView();
         this.updateMapColor();
         this.updateMapOrientation();
         this.updateMapScale();
         this.updateMapType();
         this.updateMapView();
         this.updateMapVisibility();
         this.updateMessagesOnRoute();
         this.updateSemidynamicRouteGuidance();
         this.updateSupportedMapTypes();
         this.updateSupportedMapViews();
         this.updateTimeToDestination();
         this.updateTurnToInfo();
         this.updateVoiceGuidanceState();
         this.updateXUrgentMessages();
         this.updateMapPresentation();
         this.updateOnlineNavigationState();
         this.updateLastDestinationsList();
         this.updateManeuverState();
         this.updateHomeAddress();
      }
   }

   public void setCombiService(CombiBAPServiceNavi var1) {
      this.logChannel.log(1000000, "CombiBAPListener#setCombiService()");
      this.combiservice = var1;
      this.updateAll();
   }

   protected boolean isNaviOperable() {
      return this.operationManager != null ? this.operationManager.isFullyOperable() : false;
   }

   private static int getTMCSymbolMapping(int var0) {
      for (int var1 = 0; var1 < tmcEvent2CombiSymbols1.length; var1++) {
         for (int var2 = 0; var2 < tmcEvent2CombiSymbols1[var1].length; var2++) {
            if (tmcEvent2CombiSymbols1[var1][var2] == var0 && tmcEvent2CombiSymbols1[var1][var2] >= 0) {
               return tmcEvent2CombiSymbols2[var1];
            }
         }
      }

      return 0;
   }

   void setCurrentStreet(String var1) {
      this.logChannel.log(10000000, "CombiBAPListener#currentStreet( %1 )", var1);
      this.currentPositionInfo = var1;
      this.updateCurrentPositionInfo();
   }

   void setDistanceToNextManeuver(int var1, boolean var2, int var3) {
      this.logChannel
         .log(
            10000000,
            "CombiBAPListener#setDistanceToNextManeuver() - distance: %1, showBargraph: %2, bargraphValue: %3",
            new Integer(var1),
            var2,
            new Integer(var3)
         );
      int var4 = Math.min(100, (int)(var3 / 255.0F * 100.0F));
      this.maneuverDistance = var1;
      this.maneuverBargraph = var4;
      this.maneuverShowBargraph = var2;
      if (var1 < 0 || var2 && !this.maneuverShowBargraph) {
         Buffer var5 = new Buffer(500);
         var5.append("[distanceInMeters: ").append(var1);
         var5.append(", showBargraph: ").append(var2);
         var5.append(", bargraphValue: ").append(var3);
         var5.append("] - [maneuverDistance: ").append(this.maneuverDistance);
         var5.append(", maneuverShowBargraph: ").append(this.maneuverShowBargraph);
         var5.append(", maneuverBargraph: ").append(this.maneuverBargraph);
         var5.append(']');
         this.logChannel.log(100000, "CombiBAPListener#setDistanceToNextManeuver() - %1", var5);
      }

      this.updateDistanceToNextManeuver();
   }

   void setRgTimeToNextDestination(long var1, boolean var3, boolean var4) {
      long var5 = var4 ? var1 / 1000L : -1L;
      this.destinationTime = var5;
      this.destinationTimeType = var4 ? (var3 ? 1 : 0) : 15;
      this.destinationTimeUnit = DateMetric.timeFormat == 10 ? 0 : 1;
      this.updateTimeToDestination();
   }

   void setRgDistanceToNextDestination(int var1, boolean var2) {
      this.destinationDistance = var1;
      this.destinationDistanceIsToStopOver = var2;
      this.updateDistanceToDestination();
   }

   void setVehicleHeading(short var1, short var2) {
      if (var1 != this.directionAngle || var2 != this.directionSymbolic) {
         this.directionAngle = var1;
         this.directionSymbolic = var2;
         this.updateCompassInfo();
      }
   }

   private boolean isRgStatusValidForRgType() {
      if (Util.isClusterMapFPK(this.env.getFramework()) || Util.isClusterMMI(this.env.getFramework())) {
         return true;
      } else {
         return Util.isBentley(this.env.getFramework()) || Util.isPorsche(this.env.getFramework()) || Util.isPorscheGen2(this.env.getFramework())
            ? true
            : this.rgStatus != 1 || this.rgType != 2;
      }
   }

   synchronized void setRgActive(boolean var1) {
      this.logChannel.log(10000000, "CombiBAPListener#setRgActive( %1 )", var1);
      this.rgStatus = var1 ? 1 : 0;
      if (!this.isRgStatusValidForRgType()) {
         this.logChannel.log(1000000, "CombiBAPListener#setRgActive() - rgStatus is not valid for rgType %1, deferring update...", this.rgType);
      } else {
         this.updateRGStatusAndType();
      }
   }

   synchronized void setViewMode(int var1) {
      this.logChannel.log(10000000, "CombiBAPListener#setViewMode() - view mode %1, oldType: %2", var1, this.rgType);
      byte var2;
      if (!Util.isClusterMapFPK(this.env.getFramework()) && !Util.isClusterMMI(this.env.getFramework())) {
         switch (var1) {
            case 0:
               var2 = 2;
               break;
            case 1:
               var2 = 0;
               break;
            case 2:
               var2 = 1;
               break;
            case 3:
               var2 = 3;
               break;
            default:
               this.logChannel.log(10000, "CombiBAPListener#setViewMode() - invalid view mode %1 !", var1);
               return;
         }
      } else {
         var2 = 4;
      }

      if (this.rgType != var2) {
         this.rgType = var2;
         if (!this.isRgStatusValidForRgType()) {
            this.logChannel.log(1000000, "CombiBAPListener#setViewMode() - rgType is not valid for rgStatus %1, deferring update...", this.rgStatus);
         } else {
            this.updateRGStatusAndType();
         }
      }
   }

   void setLaneGuidance(NavLaneGuidanceData[] var1, boolean var2) {
      this.laneGuidanceEnable = var2;
      if (var1 != null) {
         this.laneGuidanceData = new CombiBAPNaviLaneGuidanceData[var1.length];

         for (int var3 = 0; var3 < var1.length; var3++) {
            NavLaneGuidanceData var4 = var1[var3];
            if (var4 != null) {
               this.logChannel.log(10000000, "CombiBAPListener#setLaneGuidance() - lane %2: %1", var4, var3);
               this.laneGuidanceData[var3] = new CombiBAPNaviLaneGuidanceData(
                  var4.pos,
                  var4.laneDirection,
                  var4.getLaneSideStreets(),
                  var4.laneType,
                  var4.laneMarkingLeft,
                  var4.laneMarkingRight,
                  var4.laneDescription,
                  var4.guidanceInfo
               );
            } else {
               this.laneGuidanceData[var3] = null;
            }
         }
      } else {
         this.laneGuidanceData = new CombiBAPNaviLaneGuidanceData[0];
      }

      this.updateLaneGuidance();
   }

   private void applyManeuverDescriptor(BapManeuverDescriptor[] var1) {
      if (var1 != null) {
         this.maneuverDescriptor = new CombiBAPNaviManeuverDescriptor[var1.length];

         for (int var2 = 0; var2 < var1.length; var2++) {
            BapManeuverDescriptor var3 = var1[var2];
            if (var3 != null) {
               this.logChannel.log(10000000, "CombiBAPListener#applyManeuverDescriptor() - maneuver %2: %1", var3, var2);
               this.maneuverDescriptor[var2] = new CombiBAPNaviManeuverDescriptor(var3.mainElement, var3.direction, var3.zLevelGuidance, var3.getSideStreets());
            } else {
               this.maneuverDescriptor[var2] = null;
            }
         }
      } else {
         this.logChannel.log(100000, "CombiBAPListener#applyManeuverDescriptor() - no data available!");
      }
   }

   void setManeuverDescriptor(BapManeuverDescriptor[] var1) {
      this.applyManeuverDescriptor(var1);
      this.maneuverDescriptorReceivedForCurrentRG = true;
      this.updateManeuverDescriptor();
      this.updateDistanceToNextManeuver();
      this.updateExitView();
   }

   void setTurnToInfo(BapTurnToInfo[] var1) {
      boolean var2 = var1 != null && var1.length > 0;
      String var3 = var2 ? var1[0].getTurnToInfo() : "";
      String var4 = var2 ? var1[0].getSignPost() : "";
      if (!var3.equals(this.turnToInfo) || !var4.equals(this.signPost)) {
         this.signPost = var4;
         this.turnToInfo = var3;
         this.updateTurnToInfo();
      }

      this.logChannel.log(100000000, "CombiBAPListener#setTurnToInfo() - %1", this.turnToInfo);
   }

   void setInfoStateGPS(int var1) {
      this.logChannel.log(10000000, "CombiBAPListener#setInfoStateGPS( %1 )", var1);
      short var2 = 255;
      switch (var1) {
         case 0:
         case 3:
         case 4:
            var2 = 3;
            break;
         case 1:
         case 2:
            var2 = 0;
            break;
         default:
            this.logChannel.log(10000, "CombiBAPListener#setInfoStateGPS() - state %1 is unknown!", var1);
      }

      if (this.infoStatesGPS != var2) {
         this.infoStatesGPS = var2;
         this.updateInfoStates();
      }
   }

   public void setSupportedSupplementaryMapViews(int var1) {
      this.logChannel.log(10000000, "CombiBAPListener#setSupportedSupplementaryMapView( %1 )", var1);
      this.supportedSupplementaryMapViews = var1;
      this.updateSupportedMapViews();
   }

   boolean setInfoStateNavi(int var1) {
      this.logChannel.log(10000000, "CombiBAPListener#setInfoStateNavi( %1 )", var1);
      short var2 = 255;
      switch (var1) {
         case 0:
         case 1:
         case 12:
            break;
         case 2:
         case 4:
         case 6:
         case 7:
         case 9:
            var2 = 2;
            break;
         case 3:
         case 8:
            var2 = 1;
            break;
         case 5:
         case 10:
         case 11:
         case 14:
            var2 = 0;
            break;
         case 13:
         default:
            this.logChannel.log(10000, "CombiBAPListener#setInfoStateNavi() - state %1 is unknown!", var1);
      }

      if (this.infoStatesNavi != var2) {
         this.infoStatesNavi = var2;
         this.updateInfoStates();
      }

      return var2 != 255;
   }

   void setRouteGuidanceAborted() {
      this.logChannel.log(1000000, "CombiBAPListener#setRouteGuidanceAborted() - notify combi that route guidance has aborted");
      this.routeGuidanceActDeactResult(1);
   }

   public void setXUrgentMessages(TmcMessage[] var1) {
      if (var1 != null) {
         this.xUrgentMessages = new CombiBAPTMCInfoMessage[var1.length];

         for (int var2 = 0; var2 < var1.length; var2++) {
            TmcMessage var3 = var1[var2];
            this.xUrgentMessages[var2] = new CombiBAPTMCInfoMessage(
               (byte)0,
               new Buffer().append(var3.getDirectionOfRoad1()).append('\n').append(var3.getDirectionOfRoad2()).toString(),
               new Buffer().append(var3.getStartLocation()).append('\n').append(var3.getEndLocation()).toString(),
               var3.getEventText()[0],
               var3.getAffectedRoadLength(),
               0
            );
         }

         this.updateXUrgentMessages();
      } else {
         this.logChannel.log(100000, "CombiBAPListener#setXUrgentMessages() - no data available!");
      }
   }

   public void setMessagesOnRoute(TmcMessage[] var1) {
      if (var1 != null && var1.length > 0) {
         int[] var2 = var1[0].getEventCode();
         if (var2 != null && var2.length > 0) {
            this.tmcSymbolOnRoute = getTMCSymbolMapping(var2[0]);
            this.updateMessagesOnRoute();
         } else {
            this.logChannel.log(100000, "CombiBAPListener#setMessagesOnRoute() - eventCode[] is either null or empty!");
         }
      } else {
         this.logChannel.log(100000, "CombiBAPListener#setMessagesOnRoute() - tmcMessagesAhead[] is either null or empty!");
      }
   }

   public void setAltitude(int var1) {
      this.logChannel.log(10000000, "CombiBAPListener#setAltitude( %1 )", var1);
      this.altitude = var1;
      this.updateAltitude();
   }

   public void setDestinationInfo(CombiBAPDestinationInfo var1) {
      this.logChannel.log(10000000, "CombiBAPListener#setDestinationInfo( %1 )", var1);
      this.destInfo = var1;
      this.updateDestinationInfo();
   }

   public void setSemidynamicRouteGuidance(CombiBAPSemiDynamicRouteInfo var1) {
      this.semiDynRouteInfo = var1;
      BAPDistance var2 = this.bapDistanceFormatter.formatDistanceToDestination((int)var1.getNewDTDDistance(), isMetricSystemInUse());
      this.semiDynRouteInfo.setDistanceToDestination(var2.getValue(), var2.getUnit(), var1.getNewDTDTypeOfDistance());
      this.updateSemidynamicRouteGuidance();
   }

   public void setExitView(int var1) {
      this.logChannel.log(10000000, "CombiBAPListener#setExitView( %1 )", var1);
      this.exitviewID = var1;
      this.updateExitView();
   }

   public void updateGuidanceMode(int var1) {
      this.logChannel.log(10000000, "CombiBAPListener#updateGuidanceMode( %1 )", var1);
      switch (var1) {
         case 0:
            this.voiceGuidanceState = 2;
            break;
         case 1:
            this.voiceGuidanceState = 0;
            break;
         case 2:
            this.voiceGuidanceState = 3;
            break;
         case 3:
            this.voiceGuidanceState = 1;
            break;
         default:
            this.logChannel.log(10000, "CombiBAPListener#setVoiceGuidanceMode() - value %1 is unknown, guidance is disabled!", var1);
      }

      this.updateVoiceGuidanceState();
   }

   private void updateCurrentPositionInfo() {
      this.logChannel.log(10000000, "CombiBAPListener#updateCurrentPositionInfo()");

      try {
         this.combiservice.updateCurrentPositionInfo(this.currentPositionInfo);
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateCurrentPositionInfo() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateCurrentPositionInfo() - ERROR=%1 ", var3);
      }
   }

   private void updateCompassInfo() {
      this.logChannel.log(10000000, "CombiBAPListener#updateCompassInfo()");

      try {
         this.combiservice.updateCompassInfo(this.directionAngle, this.directionSymbolic);
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateCompassInfo() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateCompassInfo() - ERROR=%1 ", var3);
      }
   }

   private void updateRGStatusAndType() {
      this.logChannel.log(10000000, "CombiBAPListener#updateRGStatusAndType()");
      if (this.rgType != -1) {
         try {
            this.combiservice.updateRGStatus(this.rgStatus);
            this.updateActiveRGType();
            if (this.rgStatus == 0) {
               this.setTurnToInfo(new BapTurnToInfo[]{new BapTurnToInfo("", "")});
               this.setRgDistanceToNextDestination(-1, false);
               this.setRgTimeToNextDestination(-1L, false, false);
               this.setLaneGuidance(new NavLaneGuidanceData[0], false);
               this.setDistanceToNextManeuver(-1, false, 0);
               this.setManeuverDescriptor(new BapManeuverDescriptor[0]);
               this.maneuverDescriptorReceivedForCurrentRG = false;
            } else if (this.maneuverDescriptorReceivedForCurrentRG) {
               this.updateManeuverDescriptor();
               this.updateDistanceToNextManeuver();
               this.updateExitView();
            }
         } catch (NullPointerException var2) {
            this.logChannel.log(100000, "CombiBAPListener#updateRGStatusAndType() - no combi service registered!");
         } catch (Exception var3) {
            this.logChannel.log(10000, "CombiBAPListener#updateRGStatusAndType() - ERROR=%1 ", var3);
         }

         if (!this.validRGTypeReceived) {
            this.validRGTypeReceived(true);
         }
      } else {
         this.logChannel.log(100000, "CombiBAPListener#updateRGStatusAndType() - invalid value: %1", this.rgType);
         this.validRGTypeReceived(false);
      }
   }

   void updateDistanceToNextManeuver() {
      try {
         BAPDistance var1 = this.bapDistanceFormatter.formatDistanceToTurn(this.maneuverDistance, isMetricSystemInUse());
         this.combiservice.updateDistanceToNextManeuver(var1.getValue(), var1.getUnit(), this.maneuverShowBargraph, this.maneuverBargraph);
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateDistanceToNextManeuver() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateDistanceToNextManeuver() - ERROR=%1 ", var3);
      }
   }

   void updateDistanceToDestination() {
      try {
         BAPDistance var1 = this.bapDistanceFormatter.formatDistanceToDestination(this.destinationDistance, isMetricSystemInUse());
         this.combiservice.updateDistanceToDestination(var1.getValue(), var1.getUnit(), this.destinationDistanceIsToStopOver);
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateDistanceToDestination() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateDistanceToDestination() - ERROR=%1 ", var3);
      }
   }

   private void updateTimeToDestination() {
      try {
         this.combiservice.updateTimeToDestination(this.destinationTimeType, this.destinationTimeUnit, this.destinationTime);
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateTimeToDestination() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateTimeToDestination() - ERROR=%1 ", var3);
      }
   }

   private void updateTurnToInfo() {
      try {
         this.combiservice.updateTurnToInfo(this.turnToInfo, this.signPost);
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateTurnToInfo() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateTurnToInfo() - ERROR=%1 ", var3);
      }
   }

   private void updateActiveRGType() {
      if (this.rgType != -1) {
         try {
            if (this.naviIsRunningOnSmartphone && !Util.isClusterMMI(this.env.getFramework())) {
               this.combiservice.updateActiveRGType(2);
            } else if (!Util.isClusterMapFPK(this.env.getFramework()) && !Util.isClusterMMI(this.env.getFramework())) {
               this.combiservice.updateActiveRGType(this.rgType);
            } else {
               this.combiservice.updateActiveRGType(4);
            }
         } catch (NullPointerException var2) {
            this.logChannel.log(100000, "CombiBAPListener#updateActiveRGType() - no combi service registered!");
         } catch (Exception var3) {
            this.logChannel.log(10000, "CombiBAPListener#updateActiveRGType() - ERROR=%1 ", var3);
         }

         if (!this.validRGTypeReceived) {
            this.validRGTypeReceived(true);
         }
      } else {
         this.logChannel.log(100000, "CombiBAPListener#updateActiveRGType() - invalid value: %1", this.rgType);
         this.validRGTypeReceived(false);
      }
   }

   private void repeatLastNavAnnouncementResult(int var1) {
      try {
         this.combiservice.repeatLastNavAnnouncementResult(var1);
      } catch (NullPointerException var3) {
         this.logChannel.log(100000, "CombiBAPListener#repeatLastNavAnnouncementResult() - no combi service registered!");
      } catch (Exception var4) {
         this.logChannel.log(10000, "CombiBAPListener#repeatLastNavAnnouncementResult() - ERROR=%1 ", var4);
      }
   }

   private void updateManeuverDescriptor() {
      try {
         if (this.maneuverDescriptor != null) {
            this.combiservice.updateManeuverDescriptor(this.maneuverDescriptor);
         } else {
            this.logChannel.log(100000, "CombiBAPListener#updateManeuverDescriptor() - no data available!");
         }
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateManeuverDescriptor() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateManeuverDescriptor() - ERROR=%1 ", var3);
      }
   }

   private void updateLaneGuidance() {
      try {
         if (this.laneGuidanceData != null) {
            this.combiservice.updateLaneGuidance(this.laneGuidanceEnable, this.laneGuidanceData);
         } else {
            this.logChannel.log(100000, "CombiBAPListener#updateLaneGuidance() - no data available!");
         }
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateLaneGuidance() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateLaneGuidance() - ERROR=%1 ", var3);
      }
   }

   protected void routeGuidanceActDeactResult(int var1) {
      this.logChannel.log(10000000, "CombiBAPListener#routeGuidanceActDeactResult( %1 )", var1);
      this.waitingForStartGuidanceResponse = false;
      this.popupButtonPressed = false;

      try {
         this.combiservice.routeGuidanceActDeactResult(var1);
         if (var1 == 0) {
            this.updateCurrentPositionInfo();
         }
      } catch (NullPointerException var3) {
         this.logChannel.log(100000, "CombiBAPListener#routeGuidanceActDeactResult() - no combi service registered!");
      } catch (Exception var4) {
         this.logChannel.log(10000, "CombiBAPListener#routeGuidanceActDeactResult() - ERROR=%1 ", var4);
      }
   }

   private void updateVoiceGuidanceState() {
      try {
         this.combiservice.updateVoiceGuidanceState(this.voiceGuidanceState);
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateVoiceGuidanceState() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateVoiceGuidanceState() - ERROR=%1 ", var3);
      }
   }

   private void updateInfoStates() {
      try {
         int var1 = 0;
         if (this.naviIsRunningOnSmartphone) {
            var1 = 6;
         } else if (var1 != this.infoStatesNavi) {
            var1 = this.infoStatesNavi;
         } else if (var1 != this.infoStatesGPS) {
            var1 = this.infoStatesGPS;
         }

         if (var1 != 0 && var1 != 3 && var1 != 6) {
            this.logChannel.log(10000000, "CombiBAPListener#updateInfoStates() - %1", var1);
            this.adaptActiveRGType(var1);
            this.combiservice.updateInfoStates(var1);
         } else if (this.validRGTypeReceived) {
            this.logChannel.log(10000000, "CombiBAPListener#updateInfoStates() - %1", var1);
            this.adaptActiveRGType(var1);
            this.combiservice.updateInfoStates(var1);
         }
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateInfoStates() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateInfoStates() - ERROR=%1 ", var3);
      }
   }

   private void updateXUrgentMessages() {
      try {
         if (this.xUrgentMessages != null) {
            this.combiservice.updateTMCInfoMessages(this.xUrgentMessages);
         } else {
            this.logChannel.log(100000, "CombiBAPListener#updateXUrgentMessages() - no data available!");
         }
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateXUrgentMessages() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateXUrgentMessages() - ERROR=%1 ", var3);
      }
   }

   private void updateMessagesOnRoute() {
      try {
         this.combiservice.updateTrafficBlockIndication(this.tmcSymbolOnRoute);
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateMessagesOnRoute() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateMessagesOnRoute() - ERROR=%1 ", var3);
      }
   }

   private void updateLastDestinationsList() {
      try {
         this.combiservice.updateLastDestinationsList(this.lastDestinations);
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateLastDestinationsList() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateLastDestinationsList() - ERROR=%1 ", var3);
      }
   }

   void updateAltitude() {
      try {
         BAPDistance var1 = this.bapDistanceFormatter.formatAltitude(this.altitude, isMetricSystemInUse());
         this.combiservice.updateAltitude(var1.getValue(), var1.getUnit());
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateAltitude() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateAltitude() - ERROR=%1 ", var3);
      }
   }

   private void updateDestinationInfo() {
      try {
         if (this.destInfo != null) {
            this.combiservice.updateDestinationInfo(this.destInfo);
         } else {
            this.logChannel.log(100000, "CombiBAPListener#updateDestinationInfo() - no data available!");
         }
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateDestinationInfo() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateDestinationInfo() - ERROR=%1 ", var3);
      }
   }

   private void updateHomeAddress() {
      try {
         this.combiservice.updateHomeAddress(this.homeAddress);
      } catch (Exception var2) {
         this.logChannel.log(10000, "CombiBAPListener#updateHomeAddress() - ERROR=%1 ", var2);
      }
   }

   private void updateExitView() {
      try {
         this.combiservice.updateExitView(this.exitViewVariant, this.exitviewID);
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateExitView() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateExitView() - ERROR=%1 ", var3);
      }
   }

   private void initExitViewVariant() {
      if (Util.isHURegionAsia()) {
         this.exitViewVariant = 3;
      } else if (Util.isHURegionNAR()) {
         this.exitViewVariant = 1;
      } else if (Util.isHURegionRdW()) {
         this.exitViewVariant = 2;
      } else {
         this.exitViewVariant = 0;
      }
   }

   private void updateFavoriteDestinationsList() {
      try {
         if (this.favoriteDestinationsList != null) {
            this.combiservice.updateFavoriteDestinationsList(this.favoriteDestinationsList);
         } else {
            this.logChannel.log(100000, "CombiBAPListener#updateFavoriteDestinationsList() - no data available!");
         }
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateFavoriteDestinationsList() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateFavoriteDestinationsList() - ERROR=%1 ", var3);
      }
   }

   private void updateFSGSetup() {
      try {
         this.combiservice.updateFSGSetup(this.voiceGuidanceSetup, this.poiSearchSupported);
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateFSGSetup() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateFSGSetup() - ERROR=%1 ", var3);
      }
   }

   private void updateMapColor() {
      try {
         this.combiservice.updateMapColor(this.mapColor);
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateMapColor() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateMapColor() - ERROR=%1 ", var3);
      }
   }

   protected void updateMapOrientation() {
      try {
         this.combiservice.updateMapOrientation(this.mapOrientation);
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateMapOrientation() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateMapOrientation() - ERROR=%1 ", var3);
      }
   }

   private void updateMapPresentation() {
      try {
         this.combiservice.updateMapPresentation(this.largeMapView, this.leftSideMenuOpen, this.rightSideMenuOpen);
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateMapPresentation() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateMapPresentation() - ERROR=%1 ", var3);
      }
   }

   void onMapScaleChanged(int var1) {
      this.logChannel.log(100000000, "CombiBAPListener#onMapScaleChanged() - zoomIndex: %1", var1);
      AbstractMap var2 = this.mapManager.getMapKombi();
      if (var2 != null) {
         IZoomHandler var3 = var2.getZoomHandler();
         int var4 = Math.round(var3.getZoomLevel(var1) / 100.0F);
         var2.getSetup().setSavedZoomListIndex(var1, true);
         this.mapScale = this.bapDistanceFormatter.formatMapScale(var4, isMetricSystemInUse());
         this.updateMapScale();
      }
   }

   void onUnitsChanged() {
   }

   protected void updateMapScale() {
      if (this.mapScale != null) {
         try {
            this.combiservice
               .updateMapScale(this.autoZoomSetting, this.autoZoomActive, this.mapScale.getValue(), this.mapScale.getUnit(), this.intersectionAutoZoomSupported);
         } catch (NullPointerException var2) {
            this.logChannel.log(100000, "CombiBAPListener#updateMapScale() - no combi service registered!");
         } catch (Exception var3) {
            this.logChannel.log(10000, "CombiBAPListener#updateMapScale() - ERROR=%1 ", var3);
         }
      }
   }

   public void setAutoZoomActive(boolean var1) {
      this.logChannel.log(100000, "CombiBAPListener#setAutoZoomActive( %1 )", var1);
      this.autoZoomActive = var1;
      this.updateMapScale();
   }

   protected void updateMapType() {
      try {
         this.combiservice.updateMapType(this.activeMapType, this.mainMapSetup);
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateMapType() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateMapType() - ERROR=%1 ", var3);
      }
   }

   protected void updateMapView() {
      try {
         this.combiservice.updateMapView(this.mapView, this.supplementaryMapView);
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateMapView() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateMapView() - ERROR=%1 ", var3);
      }
   }

   protected void updateMapVisibility() {
      try {
         this.combiservice.updateMapVisibility(this.lvdsMapVisible, this.supplementaryMapViewVisible);
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateMapVisibility() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateMapVisibility() - ERROR=%1 ", var3);
      }
   }

   private void updateManeuverState() {
      try {
         this.combiservice.updateManeuverState(this.maneuverState);
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateManeuverState() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateManeuverState() - ERROR=%1 ", var3);
      }
   }

   void updateSemidynamicRouteGuidance() {
      try {
         if (this.semiDynRouteInfo != null) {
            this.combiservice.updateSemidynamicRouteGuidance(this.semiDynRouteInfo);
         } else {
            this.logChannel.log(100000, "CombiBAPListener#updateSemidynamicRouteGuidance() - no data available!");
         }
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateSemidynamicRouteGuidance() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateSemidynamicRouteGuidance() - ERROR=%1 ", var3);
      }
   }

   private void updateSupportedMapTypes() {
      try {
         this.combiservice.updateSupportedMapTypes(this.mainMapSetupSupported, this.supportedMapTypes);
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateSupportedMapTypes() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateSupportedMapTypes() - ERROR=%1 ", var3);
      }
   }

   private void updateSupportedMapViews() {
      try {
         this.combiservice.updateSupportedMapViews(this.supportedMapViews, this.supportedSupplementaryMapViews);
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateSupportedMapViews() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateSupportedMapViews() - ERROR=%1 ", var3);
      }
   }

   private void updateOnlineNavigationState() {
      try {
         this.combiservice.updateOnlineNavigationState(this.onlineNavigationState, this.bufferProgress, this.onlineNavigationSystem);
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateOnlineNavigationState() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateOnlineNavigationState() - ERROR=%1 ", var3);
      }
   }

   public void setActiveRGType(int var1) {
      this.logChannel.log(10000000, "CombiBAPListener#setActiveRGType( %1 )", var1);
      byte var2 = -1;
      if ((Util.isClusterMapFPK(this.env.getFramework()) || Util.isClusterMMI(this.env.getFramework())) && var1 != 4) {
         this.logChannel.log(10000, "CombiBAPListener#setActiveRGType() - invalid activeRgType for FPK/G24: %1", var1);
      } else {
         switch (var1) {
            case 0:
               var2 = 1;
               break;
            case 1:
               var2 = 2;
               break;
            case 2:
               var2 = 0;
               break;
            case 3:
               var2 = 3;
               break;
            default:
               this.logChannel.log(10000, "CombiBAPListener#setActiveRGType() - invalid view mode %1 !", var2);
         }

         if (var2 != -1) {
            this.logChannel.log(10000000, "CombiBAPListener#setActiveRGType() - set new mode %1 !", ClusterViewMode.viewModeToString(var2));
            this.clusterservice.getClusterInputListener().setFavoredViewMode(var2, true);
         } else {
            this.updateActiveRGType();
         }
      }
   }

   public void repeatLastNavAnnouncement() {
      this.logChannel.log(10000000, "CombiBAPListener#repeatLastNavAnnouncement()");
      if (this.isNaviOperable()) {
         LastAnnouncementHandler var1 = this.audioStateMachine.getLastAnnouncementHandler();
         var1.repeatLastAnnouncement(1);
         this.repeatLastNavAnnouncementResult(var1.isAnnouncementRepeatActive() ? 0 : 1);
      } else {
         this.logChannel.log(10000, "CombiBAPListener#repeatLastNavAnnouncement() - navigation not operable!");
         this.repeatLastNavAnnouncementResult(1);
      }
   }

   public void startRouteGuidanceToHomeAddress() {
      this.logChannel.log(1000000, "CombiBAPListener#startRouteGuidanceToHomeAddress()");
      if (this.isNaviOperable()) {
         CommandList var1 = this.commandListFactory.createCommandList();
         var1.add(new LIGetStateCommand(SpellerStack.getInstance(), SpellerContextManager.getSpellerContext(121)));
         var1.add(new 1(this, "CombiBAPListener#StartRouteGuidanceToHomeAddressCommand"));
         var1.execute("StartRouteGuidanceToHomeAddress");
      } else {
         this.logChannel.log(10000, "CombiBAPListener#startRouteGuidanceToHomeAddress() - navigation not operable!");
         this.routeGuidanceActDeactResult(1);
      }
   }

   public void startRouteGuidance(CombiBAPNaviDestination var1) {
      this.logChannel.log(1000000, "CombiBAPListener#startRouteGuidance() %1", var1);
      if (this.isNaviOperable()) {
         CommandList var2 = this.commandListFactory.createCommandList();
         var2.add(new LIGetStateCommand(SpellerStack.getInstance(), SpellerContextManager.getSpellerContext(121)));
         var2.add(new 2(this, "GetByteStream", var1));
         var2.add(new 3(this, "TriggerDeserializationCommand"));
         var2.add(new 4(this, "StartRouteGuidance"));
         var2.setErrorCommand(new 5(this, "CombiBAPListener#startRouteGuidanceErrorCommand"));
         var2.execute("CombiBAPListener#StartRouteGuidance");
      } else {
         this.logChannel.log(10000, "CombiBAPListener#startRouteGuidance() - navigation not operable!");
         this.routeGuidanceActDeactResult(1);
      }
   }

   public void stopRouteGuidance() {
      this.logChannel.log(1000000, "CombiBAPListener#stopRouteGuidance()");
      if (this.isNaviOperable()) {
         if (this.rgStatus == 1) {
            CommandList var1 = this.commandListFactory.createCommandList();
            var1.add(new RGStopGuidanceCommand());
            var1.add(new RemoveViaPointsCommand());
            var1.add(new 6(this, "notifyCombi"));
            var1.setErrorCommand(new 7(this, "CombiBAPListener#StopRouteGuidanceErrorCommand"));
            var1.execute("CombiBAPListener#stopRouteGuidance");
         } else {
            this.routeGuidanceActDeactResult(4);
         }
      } else {
         this.logChannel.log(10000, "CombiBAPListener#stopRouteGuidance() - navigation not operable!");
         this.routeGuidanceActDeactResult(1);
      }
   }

   public void partialPopupRemoved(int var1) {
      this.logChannel
         .log(
            1000000,
            "CombiBAPListener#partialPopupRemoved() - waitingForStartGuidanceResponse: %1, popupButtonPressed: %2",
            this.waitingForStartGuidanceResponse,
            this.popupButtonPressed
         );
      if (this.waitingForStartGuidanceResponse && !this.popupButtonPressed) {
         this.routeGuidanceActDeactResult(2);
      }

      this.popupButtonPressed = false;
   }

   public void popupButtonPressed() {
      this.popupButtonPressed = true;
   }

   public void setVoiceGuidanceState(int var1) {
      this.logChannel.log(10000000, "CombiBAPListener#setVoiceGuidanceState( %1 )", var1);
      if (this.isNaviOperable()) {
         byte var2 = 3;
         switch (var1) {
            case 0:
               var2 = 1;
               break;
            case 1:
               var2 = 3;
               break;
            case 2:
               var2 = 0;
               break;
            case 3:
               var2 = 2;
               break;
            default:
               this.logChannel.log(10000, "CombiBAPListener#setVoiceGuidanceState() - value %1 is unknown, guidance is disabled!", var1);
         }

         this.speechManager.setGuidanceMode(var2, true);
         CommandList var3 = this.speechManager.buildGuidanceModeCommandList(var2);
         var3.execute("CombiBAPListener#setVoiceGuidanceState");
      } else {
         this.logChannel.log(10000, "CombiBAPListener#setVoiceGuidanceState() - navigation not operable!");
         this.updateVoiceGuidanceState();
      }
   }

   public void setMapColor(int var1) {
      this.logChannel.log(10000000, "CombiBAPListener#setMapColor() - color: %1", var1);
      if (this.mainMapandKombiMapSetupInitialized()) {
         this.mapColor = var1;
         switch (var1) {
            case 0:
               this.mapManager.setMapColor(0);
               break;
            case 1:
               this.mapManager.setMapColor(1);
               break;
            case 2:
               this.mapManager.setMapColor(2);
               break;
            default:
               this.logChannel.log(10000, "CombiBAPListener#setMapColor() - invalid map color: %1 ", var1);
         }

         this.updateMapColor();
      } else {
         this.logChannel.log(100000, "CombiBAPListener#setMapColor() - one of the map instance is not initialized, cannot setMapColor!");
      }
   }

   public void setMapType(int var1, int var2) {
      this.logChannel.log(10000000, "CombiBAPListener#setMapType() - activeMapType: %1, mainMapSetup: %2 not handled", var1, var2);
      this.activeMapType = var1;
      this.mainMapSetup = var2;
      byte var3 = -1;
      switch (var1) {
         case 0:
            var3 = 0;
            break;
         case 1:
            var3 = 1;
            break;
         case 2:
            var3 = 2;
            break;
         case 3:
            var3 = 3;
            break;
         default:
            this.logChannel.log(100000, "CombiBAPListener#setMapType() - activeMapType:%1 not handled", var1);
      }

      if (var3 >= 0) {
         AbstractMap var4 = this.mapManager.getMapKombi();
         if (var4 != null) {
            var4.getSetup().setMapType(var3, true);
         }
      }

      this.updateMapType();
   }

   public void setMapView(int var1, int var2) {
      this.logChannel.log(10000000, "CombiBAPListener#setMapView() - mapView: %1, supplementaryMapView: %2", var1, var2);
      byte var3 = -1;
      switch (var1) {
         case 0:
            var3 = 0;
            break;
         case 1:
            var3 = 1;
            break;
         case 2:
            var3 = 2;
            break;
         default:
            this.logChannel.log(100000, "CombiBAPListener#setMapView() - MapView:%1 not handled", var1);
      }

      AbstractMap var4 = this.mapManager.getMapKombi();
      if (var3 >= 0 && var4 != null && var4.isInitialized()) {
         this.mapView = var1;
         this.mapManager.setMapRepresentation(var3, true);
      }

      if (this.supplementaryMapView != var2) {
         this.supplementaryMapView = var2;
         this.clusterservice.setSupplementaryMap(this.getSupplementaryMapView(), this.supplementaryMapViewVisible);
      }

      this.updateMapView();
   }

   public void mapViewChanged(boolean var1) {
      if (var1) {
         byte var2 = -1;
         switch (this.mapView) {
            case 0:
               var2 = 0;
               break;
            case 1:
               var2 = 1;
               break;
            case 2:
               var2 = 2;
               break;
            default:
               this.logChannel.log(100000, "CombiBAPListener#setMapView() - MapView:%1 not handled", this.mapView);
         }

         AbstractMap var3 = this.mapManager.getMapKombi();
         if (var2 >= 0 && var3 != null && var3.isInitialized() && this.mapManager.getMapInterface().isGoogleTempDisabled() && var2 != 1) {
            this.mapManager.getMapInterface().setGoogleTempDisabled(false);
         }
      }
   }

   public int getSupplementaryMapView() {
      switch (this.supplementaryMapView) {
         case 0:
            return 0;
         case 1:
            return 1;
         case 2:
            return 2;
         case 3:
            return 3;
         default:
            this.logChannel
               .log(100000, "CombiBAPListener#getSupplementaryMapView() - unknown supplementary map view mode (BAP): %1", this.supplementaryMapView);
            return 0;
      }
   }

   public void setSupplementaryMapView(int var1) {
      this.setMapView(this.mapView, var1);
   }

   public void setMapVisibility(boolean var1, boolean var2) {
      this.setMapVisibility(var1, var2, false);
   }

   public void setMainMapVisibility(boolean var1) {
      this.logChannel.log(10000000, "CombiBAPListener#setMainMapVisibility() - %1", var1);
      this.setMapVisibility(var1, this.supplementaryMapViewVisible);
   }

   public void setMapVisibility(boolean var1, boolean var2, boolean var3) {
      this.logChannel
         .log(
            10000000,
            "CombiBAPListener#setMapVisibility() - lvdsMapVisible: %1, supplementaryMapViewVisible: %2, supplementaryMapVisibilityAlreadyChanged: %3",
            var1,
            var2,
            var3
         );
      if (this.lvdsMapVisible != var1) {
         this.lvdsMapVisible = var1;
         this.clusterservice.showKombiMap(var1);
      }

      if (this.supplementaryMapViewVisible != var2) {
         this.supplementaryMapViewVisible = var2;
         if (!var3) {
            this.clusterservice.setSupplementaryMap(this.getSupplementaryMapView(), var2);
         }
      }

      this.updateMapVisibility();
   }

   public void setSupplementaryMapVisibility(boolean var1, boolean var2) {
      this.logChannel.log(10000000, "CombiBAPListener#setSupplementaryMapVisibility() - %1", var1);
      this.setMapVisibility(this.lvdsMapVisible, var1, var2);
   }

   public void setMapOrientation(int var1) {
      this.logChannel.log(10000000, "CombiBAPListener#setMapOrientation() - orientation: %1", var1);
      if (this.mapManager.getMapKombi() != null) {
         this.mapOrientation = var1;
         if (var1 == 0) {
            this.mapManager.getMapKombi().getActiveContext().automaticOrientateMap();
         } else {
            this.mapManager.getMapKombi().getSetup().setOrientation(this.mapOrientationFromBap(this.mapOrientation), true);
            if (this.mapOrientation == 1) {
               this.mapManager.getMapKombi().getSetup().setMapType(1, true);
               this.activeMapType = this.setupMapType2BAP(1);
            }
         }
      }

      this.updateMapOrientation();
      this.updateMapType();
   }

   protected int mapOrientationFromBap(int var1) {
      switch (var1) {
         case 1:
            return 0;
         case 2:
            return 1;
         default:
            this.logChannel.log(100000, "CombiBAPListener#mapOrientationFromBap() - unknown map orientation: %1", var1);
            return -1;
      }
   }

   protected int mapOrientationToBap(int var1) {
      switch (var1) {
         case 0:
            return 1;
         case 1:
            return 2;
         default:
            this.logChannel.log(100000, "CombiBAPListener#setMapOrientation() - unknown map orientation: %1", var1);
            return -1;
      }
   }

   protected int mapMapTypeToBap(int var1) {
      switch (var1) {
         case 0:
            return 0;
         case 1:
            return 1;
         case 2:
            return 2;
         case 3:
            return 3;
         default:
            this.logChannel.log(100000, "CombiBAPListener#setMapType() - activeMapType:%1 not handled", this.activeMapType);
            return 2;
      }
   }

   protected int mapMapTypeFromBap(int var1) {
      switch (var1) {
         case 0:
            return 0;
         case 1:
            return 1;
         case 2:
            return 2;
         case 3:
            return 3;
         default:
            this.logChannel.log(100000, "CombiBAPListener#setMapType() - activeMapType:%1 not handled", var1);
            return 2;
      }
   }

   public void setMapScale(int var1) {
      var1 = CarPlayClusterInput.onMapScale(var1, this.combiservice);
      this.logChannel.log(10000000, "CombiBAPListener#setMapScale() - steps: %1", var1);
      AbstractMap var2 = this.mapManager.getMapKombi();
      if (var2 != null && var1 != 0) {
         var2.getActiveContext().increment(400476, var1);
         if (this.env.getFramework().getSysConstManager().getSysConst(4445) == 1) {
            var2.getSetup().setAutoZoom(2, true);
         }
      }

      this.updateMapScale();
   }

   public void setMapScaleSetting(int var1) {
      this.logChannel.log(10000000, "CombiBAPListener#setMapScaleSetting() - autoZoomSetting: %1", var1);
      this.autoZoomSetting = var1;
      AbstractMap var2 = this.mapManager.getMapKombi();
      if (var2 != null) {
         switch (var1) {
            case 0:
               var2.getSetup().setAutoZoom(2, true);
               break;
            case 1:
               if (Util.isHURegionAsia()) {
                  var2.getSetup().setAutoZoom(1, true);
               } else {
                  var2.getSetup().setAutoZoom(0, true);
               }
               break;
            case 2:
               if (!this.intersectionAutoZoomSupported) {
                  this.logChannel
                     .log(100000, "CombiBAPListener#setMapScaleSetting() - intersection zoom requested although we notified the Kombi that it is unsupported");
               }

               var2.getSetup().setAutoZoom(1, true);
               break;
            case 15:
               var2.getSetup().setAutoZoom(2, true);
               break;
            default:
               this.logChannel.log(100000, "CombiBAPListener#setMapScaleSetting() - autoZoomSetting: %1 not handled", var1);
         }

         if (var2.getActiveContext() instanceof CtxPositionMap) {
            var2.switchToAShownContext();
         }
      } else {
         this.logChannel.log(100000, "CombiBAPListener#setMapScaleSetting() - no kombi map available");
      }

      this.updateMapScale();
   }

   public void startPOISearch(int var1, int var2) {
      byte var3 = 1;
      byte var4 = 0;

      try {
         this.combiservice.poiSearchResult(var3, var4);
      } catch (NullPointerException var6) {
         this.logChannel.log(100000, "CombiBAPListener#startPOISearch() - no combi service registered!");
      } catch (Exception var7) {
         this.logChannel.log(10000, "CombiBAPListener#startPOISearch() - ERROR=%1 ", var7);
      }
   }

   public void setMapPresentation(boolean var1, boolean var2, boolean var3) {
      this.logChannel.log(10000000, "CombiBAPListener#setMapPresentation( %1, %2, %3 )", var1, var2, var3);

      try {
         if (var1) {
            this.viewSizeManagerFPK.setViewSize(2, false);
         } else {
            this.viewSizeManagerFPK.setViewSize(1, false);
         }
      } catch (Exception var5) {
         this.logChannel.log(10000, "CombiBAPListener#setMapPresentation() - ERROR=%1 ", var5);
      }

      if (var2) {
      }

      if (var3) {
      }

      this.largeMapView = var1;
      this.leftSideMenuOpen = var2;
      this.rightSideMenuOpen = var3;
      this.updateMapPresentation();
   }

   public void validRGTypeReceived(boolean var1) {
      this.logChannel.log(10000000, "CombiBAPListener#validRGTypeReceived( %1 )", var1);
      this.validRGTypeReceived = var1;
      this.updateInfoStates();
   }

   private void updateInitScreen() {
      this.logChannel.log(10000000, "CombiBAPListener#updateStartupFinished() - forceShowInitScreen: %1 ", this.forceShowInitScreen);

      try {
         if (this.forceShowInitScreen) {
            this.combiservice.showInitializingScreen();
         } else {
            this.combiservice.hideInitializingScreen();
         }
      } catch (NullPointerException var2) {
         this.logChannel.log(100000, "CombiBAPListener#updateMapScale() - no combi service registered!");
      } catch (Exception var3) {
         this.logChannel.log(10000, "CombiBAPListener#updateMapScale() - ERROR=%1 ", var3);
      }
   }

   public void forceShowInitScreen(boolean var1) {
      this.logChannel.log(10000000, "CombiBAPListener#forceShowInitScreen( %1 )", var1);
      this.forceShowInitScreen = var1;
      this.updateInitScreen();
   }

   public void showSplashScreen(boolean var1) {
      this.logChannel.log(10000000, "CombiBAPListener#showSplashScreen() - show=%1 ", var1);
      if (var1) {
         if (this.infoStatesNavi != 5 && this.infoStatesGPS != 5) {
            this.infoStatesNaviBackup = this.infoStatesNavi;
            this.infoStatesGPSBackup = this.infoStatesGPS;
         }

         this.infoStatesNavi = 5;
         this.infoStatesGPS = 5;
      } else if (this.showSplashScreen != var1) {
         this.infoStatesNavi = this.infoStatesNaviBackup;
         this.infoStatesGPS = this.infoStatesGPSBackup;
      }

      this.logChannel.log(100000000, "CombiBAPListener#showSplashScreen() - infoStatesNavi:%1, infosStatesGPS:%2", this.infoStatesNavi, this.infoStatesGPS);
      this.showSplashScreen = var1;
      this.updateInfoStates();
   }

   void updateBapManeuverState(int var1) {
      this.logChannel.log(100000000, "CombiBAPListener#updateBapManeuverState(%1)", var1);
      switch (var1) {
         case 0:
            this.maneuverState = 0;
            break;
         case 1:
            this.maneuverState = 1;
            break;
         case 2:
            this.maneuverState = 2;
            break;
         case 3:
            this.maneuverState = 3;
            break;
         case 4:
            this.maneuverState = 4;
            break;
         default:
            this.maneuverState = 0;
      }

      this.updateManeuverState();
   }

   public void setOnlineNavigationState(boolean var1, int var2, boolean var3) {
      this.logChannel
         .log(10000000, "CombiBAPListener#setOnlineNavigationState() - geSelected: %1, progress: %3, dataConnectivityAvailable: %2", var1, var3, var2);
      var2 = Math.max(var2, 0);
      var2 = Math.min(var2, 100);
      this.bufferProgress = var2;
      if (var1) {
         if (var2 == 100) {
            if (var3) {
               this.onlineNavigationState = 2;
            } else {
               this.onlineNavigationState = 3;
            }
         } else {
            this.onlineNavigationState = 1;
         }
      } else {
         this.onlineNavigationState = 0;
      }

      this.updateOnlineNavigationState();
   }

   private static boolean isMetricSystemInUse() {
      return Distance.getSystemUnit() == 1;
   }

   public void setManeuverDescriptor(BapManeuverDescriptor[] var1, int var2) {
      this.logChannel.log(10000000, "CombiBAPListener#setManeuverDescriptor() - exitViewId: %1", var2);
      this.exitviewID = var2;
      this.applyManeuverDescriptor(var1);
      this.maneuverDescriptorReceivedForCurrentRG = true;
      this.updateManeuverDescriptor();
      this.updateDistanceToNextManeuver();
      this.updateExitView();
   }

   public void setGALState(boolean var1) {
      this.logChannel.log(1000000, "CombiBAPListener#setGALState() - naviIsRunningOnSmartphone: %1", var1);
      this.naviIsRunningOnSmartphone = var1;
      this.updateInfoStates();
   }

   private void adaptActiveRGType(int var1) {
      if (!Util.isClusterMMI(this.env.getFramework())) {
         this.logChannel.log(10000000, "CombiBAPListener#adaptActiveRGType() - infoStates: %1, rgType: %2", var1, this.rgType);
         if (var1 == 6) {
            this.combiservice.updateActiveRGType(2);
         } else if (this.rgType != -1) {
            this.updateActiveRGType();
         }
      }
   }

   protected int getNavigationPresetSGCoreModel() {
      return 4146;
   }

   protected boolean mainMapandKombiMapSetupInitialized() {
      if (Util.isClusterMapAvailable(this.env.getFramework())) {
         return this.mapManager.getMapKombi().getSetup() == null
            ? false
            : this.mapManager.getMapKombi().getSetup().isInitializationPhasefinished()
               && this.mapManager.getMapMain().getSetup().isInitializationPhasefinished();
      } else {
         return this.mapManager.getMapMain().getSetup().isInitializationPhasefinished();
      }
   }

   public void resetSupportedMapViews() {
      this.supportedMapViews = this.getSupportedMapViews();
      this.updateSupportedMapViews();
   }

   public void setHomeAddress(CombiBAPNaviDestination var1) {
      this.homeAddress = var1;
      this.updateHomeAddress();
   }

   public void updateLockingState(boolean var1) {
      if (Util.isLockFeatureNavMapAdvancedMapEnabled(this.env)) {
         this.resetSupportedMapViews();
      }
   }
}
