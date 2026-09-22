package com.mibsi.carplay.rgd;

import de.audi.atip.interapp.combi.bap.audio.data.CombiBAPTMCInfoMessage;
import de.audi.atip.interapp.combi.bap.navi.CombiBAPServiceNavi;
import de.audi.atip.interapp.combi.bap.navi.data.CombiBAPDestinationInfo;
import de.audi.atip.interapp.combi.bap.navi.data.CombiBAPDestinationListEntry;
import de.audi.atip.interapp.combi.bap.navi.data.CombiBAPNaviDestination;
import de.audi.atip.interapp.combi.bap.navi.data.CombiBAPNaviLaneGuidanceData;
import de.audi.atip.interapp.combi.bap.navi.data.CombiBAPNaviManeuverDescriptor;
import de.audi.atip.interapp.combi.bap.navi.data.CombiBAPSemiDynamicRouteInfo;
import de.audi.atip.interapp.combi.bap.navi.data.EtcStatus;

public final class Mhi2qAaGatedCombiService implements CombiBAPServiceNavi {
   private final CombiBAPServiceNavi real;
   private final Mhi2qAaGatedCombiService.CombinedManeuverPublisher combinedManeuverPublisher;
   private volatile boolean blockRouteGuidance;
   private volatile boolean blockCurrentPosition;
   private volatile boolean blockMapScale;

   public Mhi2qAaGatedCombiService(CombiBAPServiceNavi var1) {
      this(var1, null);
   }

   public Mhi2qAaGatedCombiService(CombiBAPServiceNavi var1, Mhi2qAaGatedCombiService.CombinedManeuverPublisher var2) {
      this.real = var1;
      this.combinedManeuverPublisher = var2;
   }

   public CombiBAPServiceNavi getRealService() {
      return this.real;
   }

   public boolean isBlocked() {
      return this.blockRouteGuidance;
   }

   public void setBlocked(boolean var1) {
      boolean var2 = this.blockRouteGuidance;
      this.blockRouteGuidance = var1;
      this.blockCurrentPosition = var1;
      if (var1 && !var2) {
         this.real.hideInitializingScreen();
      }
   }

   public void setMapScaleBlocked(boolean var1) {
      if (this.blockMapScale != var1) {
         this.blockMapScale = var1;
         if (var1) {
            this.real.updateMapScale(0, false, 0, 0, false);
         }
      }
   }

   public void acknowledgeBlockedMapScale() {
      if (this.blockMapScale) {
         this.real.updateMapScale(0, false, 0, 0, false);
      }
   }

   public void updateRGStatus(int var1) {
      if (!this.blockRouteGuidance) {
         this.real.updateRGStatus(var1);
      }
   }

   public void updateActiveRGType(int var1) {
      if (!this.blockRouteGuidance) {
         this.real.updateActiveRGType(var1);
      }
   }

   public void updateDistanceToNextManeuver(int var1, int var2, boolean var3, int var4) {
      if (!this.blockRouteGuidance) {
         this.real.updateDistanceToNextManeuver(var1, var2, var3, var4);
      }
   }

   public void updateCurrentPositionInfo(String var1) {
      if (!this.blockCurrentPosition) {
         this.real.updateCurrentPositionInfo(var1);
      }
   }

   public void updateTurnToInfo(String var1, String var2) {
      if (!this.blockCurrentPosition) {
         this.real.updateTurnToInfo(var1, var2);
      }
   }

   public void updateDistanceToDestination(int var1, int var2, boolean var3) {
      if (!this.blockCurrentPosition) {
         this.real.updateDistanceToDestination(var1, var2, var3);
      }
   }

   public void updateTimeToDestination(int var1, int var2, long var3) {
      if (!this.blockCurrentPosition) {
         this.real.updateTimeToDestination(var1, var2, var3);
      }
   }

   public void updateManeuverDescriptor(CombiBAPNaviManeuverDescriptor[] var1) {
      if (!this.blockRouteGuidance) {
         this.real.updateManeuverDescriptor(var1);
      }
   }

   public void updateManeuverDescriptorAndExitView(CombiBAPNaviManeuverDescriptor[] var1, int var2, int var3) {
      if (!this.blockRouteGuidance) {
         if (this.combinedManeuverPublisher == null) {
            throw new IllegalStateException("combined maneuver call requires the OEM adapter");
         }

         this.combinedManeuverPublisher.publish(this.real, var1, var2, var3);
      }
   }

   public void updateLaneGuidance(boolean var1, CombiBAPNaviLaneGuidanceData[] var2) {
      if (!this.blockRouteGuidance) {
         this.real.updateLaneGuidance(var1, var2);
      }
   }

   public void updateDestinationInfo(CombiBAPDestinationInfo var1) {
      if (!this.blockCurrentPosition) {
         this.real.updateDestinationInfo(var1);
      }
   }

   public void updateExitView(int var1, int var2) {
      if (!this.blockRouteGuidance) {
         this.real.updateExitView(var1, var2);
      }
   }

   public void updateManeuverState(int var1) {
      if (!this.blockRouteGuidance) {
         this.real.updateManeuverState(var1);
      }
   }

   public void showInitializingScreen() {
      if (!this.blockRouteGuidance) {
         this.real.showInitializingScreen();
      }
   }

   public void hideInitializingScreen() {
      this.real.hideInitializingScreen();
   }

   public void updateCompassInfo(int var1, int var2) {
      this.real.updateCompassInfo(var1, var2);
   }

   public void updateTMCInfoMessages(CombiBAPTMCInfoMessage[] var1) {
      this.real.updateTMCInfoMessages(var1);
   }

   public void updateLastDestinationsList(CombiBAPDestinationListEntry[] var1) {
      this.real.updateLastDestinationsList(var1);
   }

   public void updateFavoriteDestinationsList(CombiBAPDestinationListEntry[] var1) {
      this.real.updateFavoriteDestinationsList(var1);
   }

   public void updateHomeAddress(CombiBAPNaviDestination var1) {
      this.real.updateHomeAddress(var1);
   }

   public void routeGuidanceActDeactResult(int var1) {
      this.real.routeGuidanceActDeactResult(var1);
   }

   public void repeatLastNavAnnouncementResult(int var1) {
      this.real.repeatLastNavAnnouncementResult(var1);
   }

   public void updateVoiceGuidanceState(int var1) {
      this.real.updateVoiceGuidanceState(var1);
   }

   public void updateInfoStates(int var1) {
      this.real.updateInfoStates(var1);
   }

   public void updateTrafficBlockIndication(int var1) {
      this.real.updateTrafficBlockIndication(var1);
   }

   public void updateMapColor(int var1) {
      this.real.updateMapColor(var1);
   }

   public void updateMapType(int var1, int var2) {
      this.real.updateMapType(var1, var2);
   }

   public void updateSupportedMapTypes(boolean var1, int var2) {
      this.real.updateSupportedMapTypes(var1, var2);
   }

   public void updateMapView(int var1, int var2) {
      this.real.updateMapView(var1, var2);
   }

   public void updateSupportedMapViews(int var1, int var2) {
      this.real.updateSupportedMapViews(var1, var2);
   }

   public void updateMapVisibility(boolean var1, boolean var2) {
      this.real.updateMapVisibility(var1, var2);
   }

   public void updateMapOrientation(int var1) {
      this.real.updateMapOrientation(var1);
   }

   public void updateMapScale(int var1, boolean var2, int var3, int var4, boolean var5) {
      if (!this.blockMapScale) {
         this.real.updateMapScale(var1, var2, var3, var4, var5);
      }
   }

   public void updateAltitude(int var1, int var2) {
      this.real.updateAltitude(var1, var2);
   }

   public void updateOnlineNavigationState(int var1, int var2, int var3) {
      this.real.updateOnlineNavigationState(var1, var2, var3);
   }

   public void updateSemidynamicRouteGuidance(CombiBAPSemiDynamicRouteInfo var1) {
      this.real.updateSemidynamicRouteGuidance(var1);
   }

   public void poiSearchResult(int var1, int var2) {
      this.real.poiSearchResult(var1, var2);
   }

   public void updatePOIListSize(int var1) {
      this.real.updatePOIListSize(var1);
   }

   public void updateFSGSetup(int var1, boolean var2) {
      this.real.updateFSGSetup(var1, var2);
   }

   public void updateMapPresentation(boolean var1, boolean var2, boolean var3) {
      this.real.updateMapPresentation(var1, var2, var3);
   }

   public void updateEtcStatus(EtcStatus var1) {
      this.real.updateEtcStatus(var1);
   }

   public interface CombinedManeuverPublisher {
      void publish(CombiBAPServiceNavi var1, CombiBAPNaviManeuverDescriptor[] var2, int var3, int var4);
   }
}
