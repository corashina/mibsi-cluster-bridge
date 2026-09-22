/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.audi.atip.interapp.combi.bap.audio.data.CombiBAPTMCInfoMessage
 *  de.audi.atip.interapp.combi.bap.navi.CombiBAPServiceNavi
 *  de.audi.atip.interapp.combi.bap.navi.data.CombiBAPDestinationInfo
 *  de.audi.atip.interapp.combi.bap.navi.data.CombiBAPDestinationListEntry
 *  de.audi.atip.interapp.combi.bap.navi.data.CombiBAPNaviDestination
 *  de.audi.atip.interapp.combi.bap.navi.data.CombiBAPNaviLaneGuidanceData
 *  de.audi.atip.interapp.combi.bap.navi.data.CombiBAPNaviManeuverDescriptor
 *  de.audi.atip.interapp.combi.bap.navi.data.CombiBAPSemiDynamicRouteInfo
 *  de.audi.atip.interapp.combi.bap.navi.data.EtcStatus
 */
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

public final class Mhi2qAaGatedCombiService
implements CombiBAPServiceNavi {
    private final CombiBAPServiceNavi real;
    private final CombinedManeuverPublisher combinedManeuverPublisher;
    private volatile boolean blockRouteGuidance;
    private volatile boolean blockCurrentPosition;
    private volatile boolean blockMapScale;

    public Mhi2qAaGatedCombiService(CombiBAPServiceNavi combiBAPServiceNavi) {
        this(combiBAPServiceNavi, null);
    }

    public Mhi2qAaGatedCombiService(CombiBAPServiceNavi combiBAPServiceNavi, CombinedManeuverPublisher combinedManeuverPublisher) {
        this.real = combiBAPServiceNavi;
        this.combinedManeuverPublisher = combinedManeuverPublisher;
    }

    public CombiBAPServiceNavi getRealService() {
        return this.real;
    }

    public boolean isBlocked() {
        return this.blockRouteGuidance;
    }

    public void setBlocked(boolean bl) {
        boolean bl2 = this.blockRouteGuidance;
        this.blockRouteGuidance = bl;
        this.blockCurrentPosition = bl;
        if (bl && !bl2) {
            this.real.hideInitializingScreen();
        }
    }

    public void setMapScaleBlocked(boolean bl) {
        if (this.blockMapScale == bl) {
            return;
        }
        this.blockMapScale = bl;
        if (bl) {
            this.real.updateMapScale(0, false, 0, 0, false);
        }
    }

    public void acknowledgeBlockedMapScale() {
        if (this.blockMapScale) {
            this.real.updateMapScale(0, false, 0, 0, false);
        }
    }

    public void updateRGStatus(int n) {
        if (!this.blockRouteGuidance) {
            this.real.updateRGStatus(n);
        }
    }

    public void updateActiveRGType(int n) {
        if (!this.blockRouteGuidance) {
            this.real.updateActiveRGType(n);
        }
    }

    public void updateDistanceToNextManeuver(int n, int n2, boolean bl, int n3) {
        if (!this.blockRouteGuidance) {
            this.real.updateDistanceToNextManeuver(n, n2, bl, n3);
        }
    }

    public void updateCurrentPositionInfo(String string) {
        if (!this.blockCurrentPosition) {
            this.real.updateCurrentPositionInfo(string);
        }
    }

    public void updateTurnToInfo(String string, String string2) {
        if (!this.blockCurrentPosition) {
            this.real.updateTurnToInfo(string, string2);
        }
    }

    public void updateDistanceToDestination(int n, int n2, boolean bl) {
        if (!this.blockCurrentPosition) {
            this.real.updateDistanceToDestination(n, n2, bl);
        }
    }

    public void updateTimeToDestination(int n, int n2, long l) {
        if (!this.blockCurrentPosition) {
            this.real.updateTimeToDestination(n, n2, l);
        }
    }

    public void updateManeuverDescriptor(CombiBAPNaviManeuverDescriptor[] combiBAPNaviManeuverDescriptorArray) {
        if (!this.blockRouteGuidance) {
            this.real.updateManeuverDescriptor(combiBAPNaviManeuverDescriptorArray);
        }
    }

    public void updateManeuverDescriptorAndExitView(CombiBAPNaviManeuverDescriptor[] combiBAPNaviManeuverDescriptorArray, int n, int n2) {
        if (this.blockRouteGuidance) {
            return;
        }
        if (this.combinedManeuverPublisher == null) {
            throw new IllegalStateException("combined maneuver call requires the OEM adapter");
        }
        this.combinedManeuverPublisher.publish(this.real, combiBAPNaviManeuverDescriptorArray, n, n2);
    }

    public void updateLaneGuidance(boolean bl, CombiBAPNaviLaneGuidanceData[] combiBAPNaviLaneGuidanceDataArray) {
        if (!this.blockRouteGuidance) {
            this.real.updateLaneGuidance(bl, combiBAPNaviLaneGuidanceDataArray);
        }
    }

    public void updateDestinationInfo(CombiBAPDestinationInfo combiBAPDestinationInfo) {
        if (!this.blockCurrentPosition) {
            this.real.updateDestinationInfo(combiBAPDestinationInfo);
        }
    }

    public void updateExitView(int n, int n2) {
        if (!this.blockRouteGuidance) {
            this.real.updateExitView(n, n2);
        }
    }

    public void updateManeuverState(int n) {
        if (!this.blockRouteGuidance) {
            this.real.updateManeuverState(n);
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

    public void updateCompassInfo(int n, int n2) {
        this.real.updateCompassInfo(n, n2);
    }

    public void updateTMCInfoMessages(CombiBAPTMCInfoMessage[] combiBAPTMCInfoMessageArray) {
        this.real.updateTMCInfoMessages(combiBAPTMCInfoMessageArray);
    }

    public void updateLastDestinationsList(CombiBAPDestinationListEntry[] combiBAPDestinationListEntryArray) {
        this.real.updateLastDestinationsList(combiBAPDestinationListEntryArray);
    }

    public void updateFavoriteDestinationsList(CombiBAPDestinationListEntry[] combiBAPDestinationListEntryArray) {
        this.real.updateFavoriteDestinationsList(combiBAPDestinationListEntryArray);
    }

    public void updateHomeAddress(CombiBAPNaviDestination combiBAPNaviDestination) {
        this.real.updateHomeAddress(combiBAPNaviDestination);
    }

    public void routeGuidanceActDeactResult(int n) {
        this.real.routeGuidanceActDeactResult(n);
    }

    public void repeatLastNavAnnouncementResult(int n) {
        this.real.repeatLastNavAnnouncementResult(n);
    }

    public void updateVoiceGuidanceState(int n) {
        this.real.updateVoiceGuidanceState(n);
    }

    public void updateInfoStates(int n) {
        this.real.updateInfoStates(n);
    }

    public void updateTrafficBlockIndication(int n) {
        this.real.updateTrafficBlockIndication(n);
    }

    public void updateMapColor(int n) {
        this.real.updateMapColor(n);
    }

    public void updateMapType(int n, int n2) {
        this.real.updateMapType(n, n2);
    }

    public void updateSupportedMapTypes(boolean bl, int n) {
        this.real.updateSupportedMapTypes(bl, n);
    }

    public void updateMapView(int n, int n2) {
        this.real.updateMapView(n, n2);
    }

    public void updateSupportedMapViews(int n, int n2) {
        this.real.updateSupportedMapViews(n, n2);
    }

    public void updateMapVisibility(boolean bl, boolean bl2) {
        this.real.updateMapVisibility(bl, bl2);
    }

    public void updateMapOrientation(int n) {
        this.real.updateMapOrientation(n);
    }

    public void updateMapScale(int n, boolean bl, int n2, int n3, boolean bl2) {
        if (!this.blockMapScale) {
            this.real.updateMapScale(n, bl, n2, n3, bl2);
        }
    }

    public void updateAltitude(int n, int n2) {
        this.real.updateAltitude(n, n2);
    }

    public void updateOnlineNavigationState(int n, int n2, int n3) {
        this.real.updateOnlineNavigationState(n, n2, n3);
    }

    public void updateSemidynamicRouteGuidance(CombiBAPSemiDynamicRouteInfo combiBAPSemiDynamicRouteInfo) {
        this.real.updateSemidynamicRouteGuidance(combiBAPSemiDynamicRouteInfo);
    }

    public void poiSearchResult(int n, int n2) {
        this.real.poiSearchResult(n, n2);
    }

    public void updatePOIListSize(int n) {
        this.real.updatePOIListSize(n);
    }

    public void updateFSGSetup(int n, boolean bl) {
        this.real.updateFSGSetup(n, bl);
    }

    public void updateMapPresentation(boolean bl, boolean bl2, boolean bl3) {
        this.real.updateMapPresentation(bl, bl2, bl3);
    }

    public void updateEtcStatus(EtcStatus etcStatus) {
        this.real.updateEtcStatus(etcStatus);
    }

    public static interface CombinedManeuverPublisher {
        public void publish(CombiBAPServiceNavi var1, CombiBAPNaviManeuverDescriptor[] var2, int var3, int var4);
    }
}

