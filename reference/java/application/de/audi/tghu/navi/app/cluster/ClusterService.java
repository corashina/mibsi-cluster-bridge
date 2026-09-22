package de.audi.tghu.navi.app.cluster;

import com.mibsi.carplay.core.ScreenNavStatusGate;
import de.audi.atip.hmi.intercommunication.NaviMoKoKDKConstants;
import de.audi.atip.hmi.model.ModelGroup;
import de.audi.atip.hmi.modelaccess.ChoiceModelApp;
import de.audi.atip.hmi.modelaccess.MetricsModelApp;
import de.audi.atip.interapp.combi.bap.navi.CombiBAPServiceNavi;
import de.audi.atip.interapp.combi.bap.navi.CombiBAPServiceNaviListener;
import de.audi.atip.interapp.combi.bap.navi.data.CombiBAPDestinationInfo;
import de.audi.atip.interapp.combi.bap.navi.data.CombiBAPNaviDestination;
import de.audi.atip.interapp.combi.bap.navi.data.CombiBAPSemiDynamicRouteInfo;
import de.audi.atip.interapp.combi.ddp2.CombiService;
import de.audi.atip.interapp.def.NullViewSizeManager;
import de.audi.atip.interapp.locationaccessor.IMyLocationAccessor;
import de.audi.atip.log.LogChannel;
import de.audi.atip.metrics.DateMetric;
import de.audi.atip.metrics.Distance;
import de.audi.atip.mmicombi.IViewSizeManager;
import de.audi.atip.power.PowerEventListener;
import de.audi.tghu.command.ICommandListFactory;
import de.audi.tghu.navi.app.NavigationEnv;
import de.audi.tghu.navi.app.OperationManager;
import de.audi.tghu.navi.app.SpeechManager;
import de.audi.tghu.navi.app.audio.AudioStateMachine;
import de.audi.tghu.navi.app.cluster.BAPDistanceFormatter.BAPDistance;
import de.audi.tghu.navi.app.command.DSIResponseContainer;
import de.audi.tghu.navi.app.interapp.IViewSizeChangeHandler;
import de.audi.tghu.navi.app.interapp.NullViewSizeChangeHandler;
import de.audi.tghu.navi.app.map.AbstractMap;
import de.audi.tghu.navi.app.map.MapInterface;
import de.audi.tghu.navi.app.map.MapManager;
import de.audi.tghu.navi.app.map.handler.MapScaleHandler;
import de.audi.tghu.navi.app.map.handler.MapScaleInfo;
import de.audi.tghu.navi.app.map.handler.MapScaleTimer;
import de.audi.tghu.navi.app.map.routecalc.RcciEvent;
import de.audi.tghu.navi.app.rp.TripHandler.TripData;
import de.audi.tghu.navi.app.util.LocationFormatter;
import de.audi.tghu.navi.app.util.Util;
import de.audi.tghu.navi.app.util.addressformatting.AddressFormatter;
import de.audi.tghu.navi.app.util.addressformatting.LocationFormattingResponse;
import de.esolutions.fw.util.commons.Buffer;
import java.util.Date;
import org.dsi.ifc.global.NavLocation;
import org.dsi.ifc.komoview.RouteInfoElement;
import org.dsi.ifc.komoview.TrafficInfo;
import org.dsi.ifc.navigation.BapManeuverDescriptor;
import org.dsi.ifc.navigation.BapTurnToInfo;
import org.dsi.ifc.navigation.DistanceToNextManeuver;
import org.dsi.ifc.navigation.NavLaneGuidanceData;
import org.dsi.ifc.navigation.PosPosition;
import org.dsi.ifc.navigation.RgInfoForNextDestination;
import org.dsi.ifc.tmc.TmcMessage;

public class ClusterService implements NaviMoKoKDKConstants, PowerEventListener {
   public static final String EMPTY_STREET_LABEL = "---";
   protected LogChannel logChannel;
   protected final NavigationEnv env;
   private boolean rgiDataValid = false;
   private final DateMetric etaDateMetric;
   private final DateMetric rttDateMetric;
   private Distance distanceToManeuver = new Distance(0.0F, 1);
   private Distance distanceToDestination = new Distance(0.0F, 1);
   private ClusterViewMode clusterViewMode;
   protected ModelGroup travelParametersGroup;
   private ModelGroup nextManeuverGroup;
   private boolean showBargraph = false;
   private String turnToStreet = "isNativeLittleEndian";
   private boolean turnToStreetValid = false;
   protected KOMOService komoService = null;
   protected final CombiBAPListener combiBAPListener;
   private ClusterInputListener clusterInputListener = null;
   private ClusterKDKHandler clusterKDKHandler;
   private CombiBAPServiceNavi combiBAPServiceNavi;
   private CombiService combiDDP2ServiceNavi;
   protected final MapInterface mapInterface;
   protected RouteInfoElement followInfoRIE = null;
   private RgInfoForNextDestination rgInfoForNextDestination = null;
   private final Object komoDataRateMutex = new Object();
   private RouteInfoElement nextManeuverElement = null;
   private final BAPDistanceFormatter bapDistanceFormatter;
   private boolean operationStateIsKnownToTheKombi = false;
   private boolean dataConnectivityAvailable = false;
   private final MapScaleHandler mapScaleHandler;
   private final MapScaleTimer mapScaleTimer;
   private boolean satMapProviderChanged = true;

   public ClusterService(NavigationEnv var1, SpeechManager var2, OperationManager var3, AudioStateMachine var4, MapManager var5, ICommandListFactory var6) {
      this(var1, var2, var3, var4, var5, var6, new NullViewSizeManager(var1.getClusterLogChannel()), new NullViewSizeChangeHandler());
   }

   protected ClusterKDKHandler initClusterKDKHandler(IViewSizeChangeHandler var1) {
      return new ClusterKDKHandlerImpl(this.env, var1, this.combiBAPListener);
   }

   public ClusterService(
      NavigationEnv var1,
      SpeechManager var2,
      OperationManager var3,
      AudioStateMachine var4,
      MapManager var5,
      ICommandListFactory var6,
      IViewSizeManager var7,
      IViewSizeChangeHandler var8
   ) {
      this.env = var1;
      this.mapInterface = var5.getMapInterface();
      this.logChannel = var1.getClusterLogChannel();
      this.bapDistanceFormatter = new BAPDistanceFormatter(this.logChannel);
      this.travelParametersGroup = new ModelGroup();
      this.nextManeuverGroup = new ModelGroup();
      this.etaDateMetric = new DateMetric(new Date(), 1);
      this.rttDateMetric = new DateMetric(new Date(), 3);
      this.followInfoRIE = new RouteInfoElement(null, null, 0, null, null, null, 0, null, null, null, null, new TrafficInfo(), 0, null, null, null, null, 0);
      this.combiBAPListener = this.initBAPListener(var2, var3, var4, var5, var6, var7);
      this.clusterViewMode = new ClusterViewMode(var1, this);
      this.clusterKDKHandler = this.initClusterKDKHandler(var8);
      this.komoService = new KOMOService(var1, this, this.clusterKDKHandler);
      this.clusterInputListener = this.createClusterInputListener(var1);
      this.mapScaleHandler = new MapScaleHandler();
      this.mapScaleTimer = new MapScaleTimer(var1, this, this.mapScaleHandler);
      var1.getLabelModel(62).setText("isNativeLittleEndian");
      this.initializeModels();
   }

   private void initializeModels() {
      this.logChannel.log(10000000, "ClusterService#initializeModels() ");
      this.env.getLabelModel(71).setText("isNativeLittleEndian");
      this.env.getChoiceModel(69).setValue(0);
      this.turnToStreetValid = false;
      this.turnToStreet = "isNativeLittleEndian";
      this.env.getMetricsModel(66).setStatus(3);
      this.env.getMetricsModel(66).setMetric(this.etaDateMetric);
      this.env.getMetricsModel(63).setStatus(3);
      this.env.getMetricsModel(63).setMetric(this.distanceToDestination);
      this.travelParametersGroup.add(this.env.getMetricsModel(66));
      this.travelParametersGroup.add(this.env.getMetricsModel(63));
      this.env.getMetricsModel(64).setStatus(3);
      this.env.getMetricsModel(64).setMetric(this.distanceToManeuver);
      this.env.getChoiceModel(65).setValue(-1);
      this.showBargraph = false;
      this.nextManeuverGroup.add(this.env.getMetricsModel(64));
      this.nextManeuverGroup.add(this.env.getChoiceModel(65));
      if (Util.isClusterKDKOnly(this.env.getFramework())) {
         this.switchDisplayContextKombi(9);
      } else if (Util.isClusterMapAvailable(this.env.getFramework())) {
         this.switchDisplayContextKombi(8);
      }
   }

   protected CombiBAPListener initBAPListener(
      SpeechManager var1, OperationManager var2, AudioStateMachine var3, MapManager var4, ICommandListFactory var5, IViewSizeManager var6
   ) {
      return new CombiBAPListener(this, this.logChannel, this.env, var1, var2, var3, var4, var5, var6);
   }

   public synchronized void unitsChanged(TripData var1) {
      this.logChannel.log(10000000, "ClusterService#unitsChanged()");
      this.refreshTravelParameters(var1);
      this.refreshDistanceToNextManeuver();
      this.combiBAPListener.updateSemidynamicRouteGuidance();
      this.combiBAPListener.updateAltitude();
      this.combiBAPListener.onUnitsChanged();
   }

   public KOMOService getKomoService() {
      return this.komoService;
   }

   public ClusterViewMode getClusterViewMode() {
      return this.clusterViewMode;
   }

   public ClusterInputListener getClusterInputListener() {
      return this.clusterInputListener;
   }

   protected ClusterInputListener createClusterInputListener(NavigationEnv var1) {
      return new ClusterInputListener(var1, this);
   }

   public CombiBAPServiceNaviListener getCombiBAPListener() {
      return this.combiBAPListener;
   }

   public void setCombiBAPService(CombiBAPServiceNavi var1) {
      var1 = ScreenNavStatusGate.prepareCombiBAPService(this, var1);
      this.logChannel.log(10000000, "ClusterService#setCombiBAPService()");
      this.combiBAPListener.setCombiService(var1);
      this.combiBAPServiceNavi = var1;
   }

   public void setCombiService(CombiService var1) {
      this.logChannel.log(10000000, "ClusterService#setCombiService()");
      this.clusterViewMode.setCombiService(var1);
      this.combiDDP2ServiceNavi = var1;
   }

   public void setMOSTFrameVisible(boolean var1) {
      this.logChannel.log(10000000, "ClusterService#setMOSTFrameVisible( %1 )", var1);
      this.komoService.notifyVisibility(var1);
   }

   public boolean isLvdsMapVisible() {
      return this.combiBAPListener.lvdsMapVisible;
   }

   public void updateCurrentStreet(String var1) {
      this.logChannel.log(100000000, "ClusterService#updateCurrentStreet( %1 )", var1);
      String var2 = var1;
      String var3 = var1;
      String var4 = var1;
      if (Util.isEmpty(var2)) {
         this.logChannel.log(10000000, "ClusterService#updateCurrentStreet() - invalid currentStreet: %1", var2);
         var3 = "isNativeLittleEndian";
         var2 = "isNativeLittleEndian";
         var4 = "---";
      }

      this.env.getLabelModel(62).setText(var4);
      this.komoService.setCurrentStreet(var2);
      this.combiBAPListener.setCurrentStreet(var3);
   }

   public void updateTurnToStreet(String var1, boolean var2) {
      this.logChannel.log(100000000, "ClusterService#updateTurnToStreet( %1 )", var1);
      this.turnToStreet = var1;
      this.turnToStreetValid = !Util.isEmpty(var1);
      this.env.getLabelModel(71).setText(var1);
      this.refreshStreetMode();
   }

   private void refreshStreetMode() {
      this.logChannel
         .log(100000000, "ClusterService#refreshStreetMode() - turnToStreetValid: %1, showBargraph: %2 ", this.turnToStreetValid, this.showBargraph);
      ChoiceModelApp var1 = this.env.getChoiceModel(69);
      if (this.turnToStreetValid && this.showBargraph) {
         var1.setValue(1);
         this.komoService.setTurnToStreet(this.turnToStreet, "isNativeLittleEndian");
      } else {
         var1.setValue(0);
         this.komoService.setTurnToStreet("isNativeLittleEndian", "isNativeLittleEndian");
      }
   }

   public void refreshDistanceToNextManeuver() {
      DistanceToNextManeuver var1 = this.env.getContainer().getDistanceToNextManeuver();
      this.refreshDistanceToNextManeuver(var1);
   }

   private int convertBAP2KOMODistanceUnit(int var1) {
      switch (var1) {
         case 0:
            return 1;
         case 1:
         case 6:
            return 2;
         case 2:
            return 3;
         case 3:
            return 4;
         case 4:
         case 7:
            return 0;
         case 5:
            return 5;
         case 255:
         default:
            this.logChannel.log(100000, "ClusterService#convertBAP2KOMOUnit() - unknown or not convertable BAP unit: %1", var1);
            return 255;
      }
   }

   protected void refreshDistanceToNextManeuver(DistanceToNextManeuver var1) {
      if (var1 == null) {
         this.logChannel.log(1000000, "ClusterService#refreshDistanceToNextManeuver() - distanceToNextManeuver is null! ");
      } else {
         this.logChannel.log(100000000, "ClusterService#refreshDistanceToNextManeuver() - distanceToNextManeuver: %1", var1);
         boolean var2 = var1.showDistance;
         int var3 = var1.distance;
         this.showBargraph = var1.showBargraph;
         int var4 = var1.bargraph;
         long var5;
         int var7;
         boolean var8;
         if (var3 > 0) {
            BAPDistance var9 = this.bapDistanceFormatter.formatDistanceToTurn(var3, Distance.getSystemUnit() == 1);
            var5 = var9.getValue();
            var7 = this.convertBAP2KOMODistanceUnit(var9.getUnit());
            var8 = !this.showBargraph;
         } else {
            var5 = -1L;
            var7 = 255;
            var8 = false;
         }

         MetricsModelApp var11 = this.env.getMetricsModel(64);
         if (var2 && var3 > 0) {
            this.distanceToManeuver.setValue(var3 / 1000.0F);
            var11.setMetric(this.distanceToManeuver);
         }

         if (var2 && var3 > 0 && !this.showBargraph) {
            Util.setModelStatus(var11, 1);
         } else {
            Util.setModelStatus(var11, 3);
         }

         int var10 = this.showBargraph ? var4 : -1;
         this.env.getChoiceModel(65).setValue(var10);
         this.refreshStreetMode();
         this.nextManeuverGroup.flush();
         this.refreshDistanceToNextManeuverMOST(var5, var7, var8, var2);
         this.combiBAPListener.setDistanceToNextManeuver(var3, this.showBargraph, var4);
      }
   }

   public void refreshTravelParameters(TripData var1) {
      this.logChannel.log(100000000, "ClusterService#refreshTravelParameters()");
      if (var1.etaModeActive) {
         this.updateArrivalTime(var1.etaValid, var1.etaToNextDestination, var1.isTimeZoneOffset);
      } else {
         this.updateRemainingTravelTime(var1.etaValid, var1.timeToNextDestination * 1000L);
      }

      this.followInfoRIE.destinationIndex = this.getDestIndex();
      this.updateDistanceToDestination(var1.distanceToNextDestination, this.followInfoRIE.destinationIndex == 0);
      this.updateKOMOFollowInfo();
      this.travelParametersGroup.flush();
   }

   private void clearRouteInfoElement(RouteInfoElement var1) {
      if (var1 != null) {
         var1.distanceToElement = "isNativeLittleEndian";
         var1.estimatedTimeToElement = "--:--";
         var1.routeInfoElementType = 0;
         var1.elementIconIDs = null;
         var1.prio1EventText = null;
         var1.streetIconText = null;
         var1.streetIconID = 0;
         var1.exitNumber = null;
         var1.turnToStreet = null;
         var1.pOIElementNames = null;
         var1.maneuverDescriptor = null;
         if (var1.trafficInfo != null) {
            var1.trafficInfo.trafficOffset = null;
            var1.trafficInfo.trafficOffsetAffix = null;
            var1.trafficInfo.affixPlacementBefore = false;
         }

         var1.destinationIndex = 0;
         var1.signPostInfo = null;
         var1.distanceToManeuver = null;
         var1.estimatedTimeToManeuver = null;
         var1.streetCardinalDirection = null;
         var1.exitIconId = 0;
      }
   }

   protected void refreshDistanceToNextManeuverMOST(long var1, int var3, boolean var4, boolean var5) {
      this.komoService.setDistanceToNextManeuver(var1, var3, var4);
   }

   protected int getDestIndex() {
      int var1 = this.mapInterface.getMap().getNaviInterface().getRouteListLength();
      int var2 = this.mapInterface.getMap().getNaviInterface().getIndexOfCurrentDestination();
      return var2 + 1 < var1 ? 0 : -1;
   }

   protected void updateArrivalTime(boolean var1, long var2, boolean var4) {
      this.logChannel.log(100000000, "ClusterService#updateArrivalTime( %1, %2 )", var1, var2);
      MetricsModelApp var6 = this.env.getMetricsModel(66);
      String var5;
      if (var1) {
         this.etaDateMetric.setDate(var2);
         var6.setMetric(this.etaDateMetric);
         Util.setModelStatus(var6, 1);
         var5 = Util.formatTime(var2, 2, this.env);
      } else {
         this.logChannel.log(10000000, "ClusterService#updateArrivalTime() - invalid flag for ETA set! ");
         Util.setModelStatus(var6, 3);
         var5 = "--:--";
      }

      int var7 = KOMOService.convertTimeFormatToKOMO(DateMetric.timeFormat);
      KOMOTime var8 = KOMOService.convertTimeToKOMO(var2);
      this.followInfoRIE.estimatedTimeToElement = var5;
      this.komoService.setETA(var7, var8.day, var8.hour, var8.min, var1, var4);
      this.combiBAPListener.setRgTimeToNextDestination(var2, true, var1);
   }

   protected void updateRemainingTravelTime(boolean var1, long var2) {
      this.logChannel.log(100000000, "ClusterService#RemainingTravelTime( %1, %2 )", var1, var2);
      MetricsModelApp var4 = this.env.getMetricsModel(66);
      if (var1) {
         this.rttDateMetric.setDate(var2);
         var4.setMetric(this.rttDateMetric);
         Util.setModelStatus(var4, 1);
      } else {
         this.logChannel.log(10000000, "ClusterService#updateArrivalTime() - invalid flag for ETA set! ");
         Util.setModelStatus(var4, 3);
      }

      KOMOTime var5 = KOMOService.convertDurationToKOMO(var2);
      this.komoService.setRTT(var5.hour, var5.min, var1);
      this.combiBAPListener.setRgTimeToNextDestination(var2, false, var1);
   }

   protected void updateDistanceToDestination(int var1, boolean var2) {
      this.logChannel.log(100000000, "ClusterService#updateDistanceToDestination( %1 )", var1);
      long var4 = -1L;
      int var6 = -1;
      MetricsModelApp var8 = this.env.getMetricsModel(63);
      String var3;
      boolean var7;
      if (var1 > 0) {
         this.distanceToDestination.setValue(var1 / 1000.0F);
         var8.setMetric(this.distanceToDestination);
         Util.setModelStatus(var8, 1);
         var3 = Util.formatDistance(var1, 1, 2, "---");
         BAPDistance var9 = this.bapDistanceFormatter.formatDistanceToDestination(var1, Distance.getSystemUnit() == 1);
         var4 = var9.getValue();
         var6 = this.convertBAP2KOMODistanceUnit(var9.getUnit());
         var7 = true;
      } else {
         this.logChannel.log(10000000, "ClusterService#updateDistanceToDestination() - invalid distanceToDestination! ");
         Util.setModelStatus(var8, 3);
         var3 = "isNativeLittleEndian";
         var7 = false;
      }

      this.followInfoRIE.distanceToElement = var3;
      this.komoService.setDistanceToDestination(var4, var6, var7);
      this.combiBAPListener.setRgDistanceToNextDestination(var1, var2);
   }

   public void updateSoPosPosition(PosPosition var1) {
      this.logChannel.log(100000000, "ClusterService#updateSoPosPosition( %1 )", var1);
      short var2 = 0;
      short var3 = 255;
      int var4 = -1;
      if (var1 != null) {
         var2 = (short)var1.getDirectionAngle();
         var3 = (short)var1.getDirectionSymbolic();
         var4 = var1.getState();
      } else {
         this.logChannel.log(100000, "ClusterService#updateSoPosPosition() - invalid soPosPosition!");
      }

      this.combiBAPListener.setVehicleHeading(var2, var3);
      this.combiBAPListener.setInfoStateGPS(var4);
   }

   public void updateSoPosPositionDescription(NavLocation var1) {
      this.logChannel.log(100000000, "ClusterService#updateSoPosPositionDescription( %1 )", var1);
      String var2 = LocationFormatter.formatCity(var1);
      this.komoService.setCityName(var2);
   }

   public void updateRgDirectionToNextDestination(short var1) {
      this.logChannel.log(100000000, "ClusterService#updateRgDirectionToNextDestination( %1 )", var1);
   }

   public void updateRGIString(short[] var1) {
      if (var1 != null && var1.length > 0) {
         this.rgiDataValid = true;
         this.env.getDataModel(68).set(var1);
      } else {
         this.rgiDataValid = false;
         this.logChannel.log(10000000, "ClusterService#updateRGIData() - invalid RGI data ");
      }

      this.refreshRGIValid();
   }

   public void updateRgActive(boolean var1) {
      ScreenNavStatusGate.onOemRgActive(var1);
      this.logChannel.log(100000000, "ClusterService#updateRgActive( %1 )", var1);
      this.refreshRGIValid();
      this.clusterViewMode.refreshRGState();
      if (!var1) {
         this.initializeModels();
         this.updateTurnToStreet("isNativeLittleEndian", false);
         this.clearRouteInfoElement(this.followInfoRIE);
         this.clearRouteInfoElement(this.nextManeuverElement);
         this.updateDestinationInfo(null, 0, 0);
      }

      this.clusterKDKHandler.updateRgActive(var1);
      this.combiBAPListener.setRgActive(var1);
   }

   public void updateNavState(int var1, int var2) {
      this.logChannel.log(10000000, "ClusterService#updateNavState( %1, %2 )", var1, var2);
      if (this.combiDDP2ServiceNavi != null) {
         this.combiDDP2ServiceNavi.updateNavInitialized(var1, var2);
      }
   }

   private void refreshRGIValid() {
      boolean var1 = this.env.getContainer().isRgActive();
      boolean var2 = var1 && this.rgiDataValid;
      this.logChannel.log(100000000, "ClusterService#refreshRGIValid() - rgActive: %1, rgiDataValid: %2", var1, this.rgiDataValid);
      this.clusterViewMode.setRGIValid(var2);
   }

   public void refreshViewMode(int var1) {
      this.combiBAPListener.setViewMode(var1);
   }

   public void updateLaneGuidance(NavLaneGuidanceData[] var1, boolean var2) {
      this.combiBAPListener.setLaneGuidance(var1, var2);
   }

   public void updateManeuverDescriptor(BapManeuverDescriptor[] var1) {
      this.rgiDataValid = var1 != null && var1.length > 0;
      this.refreshRGIValid();
      this.combiBAPListener.setManeuverDescriptor(var1);
   }

   public void updateManeuverDescriptor(BapManeuverDescriptor[] var1, int var2) {
      this.rgiDataValid = var1 != null && var1.length > 0;
      this.refreshRGIValid();
      this.combiBAPListener.setManeuverDescriptor(var1, var2);
   }

   public String toString() {
      Buffer var1 = new Buffer();
      var1.append(this.komoService);
      var1.append(this.clusterViewMode);
      return var1.toString();
   }

   public void updateBapTurnToInfo(BapTurnToInfo[] var1) {
      this.combiBAPListener.setTurnToInfo(var1);
   }

   public void updateInfoStatesGPS(int var1) {
      this.combiBAPListener.setInfoStateGPS(var1);
   }

   private boolean initScreenNeededOnKombi() {
      boolean var1 = this.env.getContainer().getNavstateOfOperation() == 5;
      boolean var2 = Util.isClusterMapAvailable(this.env.getFramework());
      boolean var3 = false;
      AbstractMap var4 = this.mapInterface.getKombiMap();
      if (var4 != null && var4.isInitialized()) {
         var3 = true;
      }

      return var1 && !var3 && var2 ? true : !this.operationStateIsKnownToTheKombi;
   }

   public synchronized void updateOperationState(int var1) {
      this.logChannel.log(1000000, "ClusterService#updateOperationState( %1 )", var1);
      this.operationStateIsKnownToTheKombi = this.combiBAPListener.setInfoStateNavi(var1);
      this.combiBAPListener.forceShowInitScreen(this.initScreenNeededOnKombi());
      if (this.combiBAPListener.lvdsMapVisible && this.env.getContainer().getNavstateOfOperation() == 5) {
         this.showKombiMap(true);
      }
   }

   public void updateXUrgentMessages(TmcMessage[] var1) {
      this.combiBAPListener.setXUrgentMessages(var1);
   }

   public void updateMessagesOnRoute(TmcMessage[] var1) {
      this.combiBAPListener.setMessagesOnRoute(var1);
   }

   public void setRouteGuidanceAborted() {
      this.combiBAPListener.setRouteGuidanceAborted();
   }

   public void updateRgInfoForNextDestination(RgInfoForNextDestination var1) {
      this.logChannel.log(100000000, "ClusterService#updateRgInfoForNextDestination(%1)", var1);
      this.rgInfoForNextDestination = var1;
      this.updateRgDirectionToNextDestination(var1.getDirectionToNextDest());
   }

   public void updateDistanceToNextManeuver(DistanceToNextManeuver var1) {
      this.refreshDistanceToNextManeuver(var1);
   }

   public synchronized void updateKombiMapReady(boolean var1) {
      this.logChannel.log(1000000, "ClusterService#updateKombiMapReady( %1 )", var1);
      if (var1) {
         this.switchDisplayContextKombi(8);
         if (Util.isClusterMapAlwaysOn()) {
            this.combiBAPListener.setMainMapVisibility(true);
         }

         if (this.combiBAPListener.lvdsMapVisible) {
            this.showKombiMap(true);
         }
      }

      this.combiBAPListener.forceShowInitScreen(this.initScreenNeededOnKombi());
      this.getClusterViewMode().setKombiMapReady(var1);
   }

   public void showKombiMap(boolean var1) {
      this.logChannel.log(1000000, "ClusterService#showKombiMap( %1 )", var1);
      this.mapInterface.showKombiMap(var1);
   }

   public void setSupplementaryMap(int var1, boolean var2) {
      this.logChannel.log(10000000, "ClusterService#setSupplementaryMap() - supplementaryMapView: %2, visible: %1", var2, var1);
      if (var1 != 1 && var2) {
         this.logChannel.log(100000, "ClusterService#setSupplementaryMap() - got request to show supplementary map although not available or unimplemented");
      }

      this.setKDKVisibility(var2);
   }

   public void switchDisplayContextKombi(int var1) {
      this.logChannel.log(1000000, "ClusterService#switchDisplayContextKombi( %1 )", var1);
      this.mapInterface.switchDisplayContextKombi(var1);
   }

   public void setKOMODataRate(int var1) {
      if (Util.isClusterMapMOST(this.env.getFramework())) {
         this.setKOMODataRate(var1, true);
      }
   }

   public int getKOMODataRate() {
      this.logChannel.log(100000000, "ClusterService#getKOMODataRate()");
      ChoiceModelApp var1 = this.env.getChoiceModel(1, 168);
      int var2 = var1.getHints();
      boolean var3 = (var2 & 2) == 2;
      boolean var4 = (var2 & 1) == 1;
      if (var3) {
         if (var4) {
            this.logChannel.log(100000, "ClusterService#getKOMODataRate() - undefined state: full and reduced framerate -> assuming full framerate");
         }

         return 2;
      } else {
         return var4 ? 1 : 0;
      }
   }

   public void reSyncKOMO() {
      this.logChannel.log(1000000, "ClusterService#reSyncKOMO()");
      synchronized (this.komoDataRateMutex) {
         int var2 = this.getKOMODataRate();
         this.setKOMODataRate(0);
         this.setKOMODataRate(var2);
         this.clusterViewMode.refreshRgMode();
      }
   }

   private void setKOMODataRate(int var1, boolean var2) {
      this.logChannel.log(1000000, "ClusterService#setKOMODataRate( %1 )", var1);
      synchronized (this.komoDataRateMutex) {
         ChoiceModelApp var4 = this.env.getChoiceModel(1, 168);
         if (var1 == 2) {
            var4.removeHint(1);
            var4.addHint(2);
         } else if (var1 == 1) {
            var4.removeHint(2);
            var4.addHint(1);
         } else {
            var4.removeHint(2);
            var4.removeHint(1);
         }

         if (var2) {
            var4.publishHints();
         }
      }
   }

   public void onAutoZoomStateChanged(boolean var1) {
      this.combiBAPListener.setAutoZoomActive(var1);
   }

   public void updateKOMOFollowInfo() {
      this.logChannel.log(100000000, "ClusterService#updateKOMOFollowInfo())");
      if (this.komoService != null && Util.isKOMOFollowMode(this.env.getFramework())) {
         try {
            this.logChannel
               .log(
                  100000000, "ClusterService#updateKOMOFollowInfo() - followInfoRIE: %1, nextManeuverElement: %2", this.followInfoRIE, this.nextManeuverElement
               );
            if (Util.isSetRouteInfoDSIAvailable(this.env.getFramework())) {
               this.komoService.setRouteInfo(new RouteInfoElement[]{this.followInfoRIE, this.nextManeuverElement});
            } else {
               this.komoService.setRouteInfoElement(this.followInfoRIE);
            }
         } catch (Exception var2) {
            this.logChannel.log(10000, "ClusterService#updateFollowInfo() - ERROR=%1", var2);
         }
      }
   }

   public void updateAltitude(int var1) {
      this.logChannel.log(100000000, "ClusterService#updateAltitude( %1 )", var1);
      this.combiBAPListener.setAltitude(var1);
   }

   private String getDestinationDescription4BAP(NavLocation var1) {
      if (var1 != null && var1.isPositionValid()) {
         try {
            LocationFormattingResponse var2 = AddressFormatter.formatTwoLines(var1, this.env);
            String var3 = var2.getFirstLineAsText();
            return var3 != null ? var3 : "isNativeLittleEndian";
         } catch (Exception var4) {
            this.env.getLogChannel().log(100000, "Util#getString4BAPFromNavLocation - got an exception from AddressFormatter#formatTwoLines: %1", var4);
            return "isNativeLittleEndian";
         }
      } else {
         return "isNativeLittleEndian";
      }
   }

   private CombiBAPNaviDestination getBAPNaviDestFromLocation(NavLocation var1) {
      if (var1 == null) {
         return new CombiBAPNaviDestination();
      }

      IMyLocationAccessor var2 = Util.getLocationAccessor(var1);
      String var3 = this.getDestinationDescription4BAP(var1);
      if (Util.isEmpty(var3)) {
         var3 = Util.isEmpty(var2.getPoiName()) ? null : var2.getPoiName();
      }

      return new CombiBAPNaviDestination(
         null,
         null,
         Util.isEmpty(var1.getStreet()) ? null : var1.getStreet(),
         Util.isEmpty(var1.getHousenumber()) ? null : var1.getHousenumber(),
         Util.isEmpty(var1.getTown()) ? null : var1.getTown(),
         Util.isEmpty(var1.getTownRefinement()) ? null : var1.getTownRefinement(),
         Util.isEmpty(var2.getState()) ? null : var2.getState(),
         Util.isEmpty(var1.getZipCode()) ? null : var1.getZipCode(),
         Util.isEmpty(var1.getCountry()) ? null : var1.getCountry(),
         wgs84ToDeg(var1.getLatitude()),
         wgs84ToDeg(var1.getLongitude()),
         var2.getType() == 1 ? 255 : 0,
         var3,
         Util.isEmpty(var2.getPoiCategory()) ? null : var2.getPoiCategory(),
         255
      );
   }

   public void updateDestinationInfo(NavLocation var1, int var2, int var3) {
      if (this.logChannel.isDebug2()) {
         this.logChannel
            .log(
               100000000,
               "ClusterService#updateDestinationInfo() - noOfStopovers: %2, noOfNextStopover: %3, nextDestination: %1",
               LocationFormatter.formatLocationShort(var1),
               var2,
               var3
            );
      }

      CombiBAPNaviDestination var4 = this.getBAPNaviDestFromLocation(var1);
      CombiBAPDestinationInfo var5;
      if (var1 != null) {
         var5 = new CombiBAPDestinationInfo(var4);
         var5.setStopoverInformation(var2, var3);
      } else {
         var5 = new CombiBAPDestinationInfo(var4);
         var5.setStopoverInformation(0, 0);
      }

      this.combiBAPListener.setDestinationInfo(var5);
   }

   public void updateSemidynamicRouteGuidance(RcciEvent var1) {
      this.logChannel
         .log(
            10000000,
            "ClusterService#updateSemidynamicRouteGuidance() - TrafficImpactOnCurrentRoute: %1, delay: %2",
            var1.hasTrafficImpactOnCurrentRoute(),
            var1.delay
         );
      KOMOTime var2 = KOMOService.convertDurationToKOMO(var1.delay);
      short var3 = var2.min;
      short var4 = var2.hour;
      short var5 = var2.day;
      byte var6 = 0;
      if (this.rgInfoForNextDestination != null) {
         var6 = 0;
      }

      CombiBAPSemiDynamicRouteInfo var7;
      if (var1.hasBetterRoute && var1.origin != null) {
         Util.formatDistance((int)var1.origin.newRoute.distance, 1);
         long var8 = (long)Util.getFormattedDistance();
         int var10 = Util.getFormattedUnit();
         KOMOTime var11 = KOMOService.convertTimeToKOMO(this.env.getFramework().getKombiTime() + var1.origin.newRoute.getEtaWithSpeedAndFlow() + var6);
         var7 = new CombiBAPSemiDynamicRouteInfo(
            var1.hasTrafficImpactOnCurrentRoute(),
            var1.hasBetterRoute,
            var4 + var5 * 24,
            var3,
            var8,
            var10,
            0,
            1,
            DateMetric.timeFormat == 10 ? 0 : 1,
            var11.min,
            var11.hour,
            var11.day,
            var11.month,
            var11.year
         );
      } else {
         var7 = new CombiBAPSemiDynamicRouteInfo(var1.hasTrafficImpactOnCurrentRoute(), var1.hasBetterRoute, var4 + var5 * 24, var3);
      }

      this.combiBAPListener.setSemidynamicRouteGuidance(var7);
      this.komoService.setSemiDynRoute(var1.hasBetterRoute);
      if (var1.hasTrafficImpactOnCurrentRoute()) {
         this.komoService.setTrafficOffset(KOMOService.convertTimeFormatToKOMO(DateMetric.timeFormat), var5, var4, var3, true);
         if (var1.reliable) {
            this.followInfoRIE.trafficInfo.trafficOffset = Util.formatTrafficOffsetDuration(var1.delay, 2, this.env);
         } else {
            this.followInfoRIE.trafficInfo.trafficOffset = "isNativeLittleEndian";
            this.followInfoRIE.estimatedTimeToElement = "--:--";
         }

         this.followInfoRIE.trafficInfo.trafficOffsetAffix = this.env.getTranslatedText(49, "incl.");
      } else {
         this.komoService.setTrafficOffset(KOMOService.convertTimeFormatToKOMO(DateMetric.timeFormat), var5, var4, var3, false);
         this.followInfoRIE.trafficInfo.trafficOffset = "isNativeLittleEndian";
         this.followInfoRIE.trafficInfo.trafficOffsetAffix = "isNativeLittleEndian";
      }

      this.updateKOMOFollowInfo();
   }

   public void updateExitView(int var1) {
      this.logChannel.log(100000000, "ClusterService#updateExitView( %1 )", var1);
      this.combiBAPListener.setExitView(var1);
   }

   public void notifyPowerListenerOnEnterState(int var1, int var2) {
      this.clusterKDKHandler.notifyPowerListenerOnEnterState(var1, var2);
   }

   public void notifyPowerListenerOnExitState(int var1, int var2) {
      this.clusterKDKHandler.notifyPowerListenerOnExitState(var1, var2);
   }

   public void notifyPowerTriggerAction(int var1, int var2) {
      this.clusterKDKHandler.notifyPowerTriggerAction(var1, var2);
   }

   public void updateClampState(boolean var1, boolean var2, boolean var3, boolean var4) {
      this.clusterKDKHandler.updateClampState(var1, var2, var3, var4);
   }

   public void showKombiSplashScreen(boolean var1) {
      this.combiBAPListener.showSplashScreen(var1);
   }

   public void updateBapManeuverState(int var1) {
      this.combiBAPListener.updateBapManeuverState(var1);
   }

   public void applySetupHandlerSettings() {
      this.logChannel.log(10000000, "ClusterService#applySetupHandlerSettings( )");
      this.combiBAPListener.initFromSetup();
   }

   public void setKDKVisibility(boolean var1) {
      this.clusterKDKHandler.setKDKVisibility(var1);
   }

   public void updateManoeuvreViewActive(int var1) {
   }

   public void onMagnificationChanged(int var1) {
      this.combiBAPListener.onMapScaleChanged(var1);
      AbstractMap var2 = this.mapInterface.getKombiMap();
      if (var2 != null) {
         float[] var3 = var2.getZoomHandler().getZoomList();
         MapScaleInfo var4 = this.mapScaleHandler.createMapScaleInfo(var1, var3);
         if (!var4.equals(this.mapScaleTimer.mapScaleInfo)) {
            this.mapScaleTimer.restart(var4);
         }
      }
   }

   public void updateNextManeuver(RouteInfoElement var1) {
      this.nextManeuverElement = var1;
      this.updateKOMOFollowInfo();
   }

   public static float wgs84ToDeg(int var0) {
      return var0 / 1.1930464E7F;
   }

   public void setSupportedSupplementaryMapView(int var1) {
      this.logChannel.log(10000000, "ClusterService#setSupportedSupplementaryMapView()");
   }

   public void updateOnlineNavigationState() {
      int var1;
      if (this.mapInterface.getKombiMap() != null && this.mapInterface.getKombiMap().getSetup() != null) {
         var1 = this.mapInterface.getKombiMap().getSetup().getMapRepresentation();
      } else {
         this.logChannel.log(10000000, "ClusterService#updateOnlineNavigationState() - Kombi map does not exist or not initialized yet!");
         var1 = 0;
      }

      int var2 = this.env.getChoiceModel(162).getValue();
      this.logChannel
         .log(
            10000000,
            "ClusterService#updateOnlineNavigationState() - mapRepresentation: %1, bufferProgress: %2, dataConnectivityAvailable: %3",
            var1,
            var2,
            true
         );
      this.combiBAPListener.setOnlineNavigationState(var1 == 1 && !this.hasSatMapProviderChanged(), var2, true);
   }

   public void cleanup() {
      this.clusterKDKHandler.cleanup();
      this.clusterInputListener.cleanup();
      this.mapScaleTimer.cancel();
   }

   public void updateGALState(boolean var1) {
      this.combiBAPListener.setGALState(var1);
   }

   public void updateOnlineConnectionState(boolean var1) {
      this.dataConnectivityAvailable = var1;
      this.updateOnlineNavigationState();
   }

   public void updateMapScale(int var1, int var2, boolean var3) {
      this.logChannel.log(100000000, "ClusterService#updateMapScale( %1, %2, %3 )", var1, var2, var3);
      boolean[] var6 = new boolean[]{false};
      boolean[] var7 = new boolean[]{false};
      this.komoService.setMapScale(0, 0, var6, var1, var2, var7, var3);
   }

   public void setHomeAddress(NavLocation var1) {
      this.logChannel.log(10000000, "ClusterService#updateHomeAddress() - homeAddress: %1", LocationFormatter.formatLocationShort(var1));
      if (var1 == null) {
         this.combiBAPListener.setHomeAddress(null);
      } else {
         this.combiBAPListener.setHomeAddress(this.getBAPNaviDestFromLocation(var1));
      }
   }

   public void setProviderChangeFlag(boolean var1) {
      this.satMapProviderChanged = var1;
   }

   private boolean hasSatMapProviderChanged() {
      return this.satMapProviderChanged;
   }

   public DSIResponseContainer getDSIResponseContainer() {
      return this.env.getContainer();
   }

   public LogChannel getMhi2qAaLogChannel() {
      return this.logChannel;
   }

   public CombiBAPServiceNavi getCombiBAPListenerCombiService() {
      return this.combiBAPListener.combiservice;
   }

   public void setCombiBAPListenerCombiService(CombiBAPServiceNavi var1) {
      this.combiBAPListener.combiservice = var1;
   }

   public void resyncMhi2qAaCombiBAP() {
      this.combiBAPListener.setCombiService(this.combiBAPListener.combiservice);
   }
}
