/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.audi.atip.base.IFrameworkAccess
 *  de.audi.atip.interapp.combi.bap.audio.data.CombiBAPTMCInfoMessage
 *  de.audi.atip.interapp.combi.bap.navi.CombiBAPServiceNavi
 *  de.audi.atip.interapp.combi.bap.navi.CombiBAPServiceNaviListener
 *  de.audi.atip.interapp.combi.bap.navi.data.CombiBAPDestinationInfo
 *  de.audi.atip.interapp.combi.bap.navi.data.CombiBAPDestinationListEntry
 *  de.audi.atip.interapp.combi.bap.navi.data.CombiBAPNaviDestination
 *  de.audi.atip.interapp.combi.bap.navi.data.CombiBAPNaviLaneGuidanceData
 *  de.audi.atip.interapp.combi.bap.navi.data.CombiBAPNaviManeuverDescriptor
 *  de.audi.atip.interapp.combi.bap.navi.data.CombiBAPSemiDynamicRouteInfo
 *  de.audi.atip.interapp.combi.bap.navi.data.FormattedDestination
 *  de.audi.atip.log.LogChannel
 *  de.audi.atip.metrics.DateMetric
 *  de.audi.atip.metrics.Distance
 *  de.audi.atip.mmicombi.IViewSizeManager
 *  de.audi.tghu.command.Command
 *  de.audi.tghu.command.CommandList
 *  de.audi.tghu.command.ICommandListFactory
 *  de.audi.tghu.navi.app.NavigationEnv
 *  de.audi.tghu.navi.app.OperationManager
 *  de.audi.tghu.navi.app.SpeechManager
 *  de.audi.tghu.navi.app.SpeechManager$GuidanceModeSettingsListener
 *  de.audi.tghu.navi.app.audio.AudioStateMachine
 *  de.audi.tghu.navi.app.audio.LastAnnouncementHandler
 *  de.audi.tghu.navi.app.cluster.BAPDistanceFormatter
 *  de.audi.tghu.navi.app.cluster.BAPDistanceFormatter$BAPDistance
 *  de.audi.tghu.navi.app.cluster.BAPTMCSymbolMapping
 *  de.audi.tghu.navi.app.cluster.ClusterViewMode
 *  de.audi.tghu.navi.app.cluster.CombiRouteGuidanceMapEventListener
 *  de.audi.tghu.navi.app.command.LIGetStateCommand
 *  de.audi.tghu.navi.app.command.RGStopGuidanceCommand
 *  de.audi.tghu.navi.app.command.via.RemoveViaPointsCommand
 *  de.audi.tghu.navi.app.li.SpellerStack
 *  de.audi.tghu.navi.app.li.sc.SpellerContextManager
 *  de.audi.tghu.navi.app.map.AbstractMap
 *  de.audi.tghu.navi.app.map.MapManager
 *  de.audi.tghu.navi.app.map.context.CtxPositionMap
 *  de.audi.tghu.navi.app.map.event.MapEventListener
 *  de.audi.tghu.navi.app.map.handler.ISetupHandler
 *  de.audi.tghu.navi.app.map.handler.IZoomHandler
 *  de.audi.tghu.navi.app.util.Util
 *  de.esolutions.fw.util.commons.Buffer
 *  org.dsi.ifc.navigation.BapManeuverDescriptor
 *  org.dsi.ifc.navigation.BapTurnToInfo
 *  org.dsi.ifc.navigation.NavLaneGuidanceData
 *  org.dsi.ifc.tmc.TmcMessage
 */
package de.audi.tghu.navi.app.cluster;

import com.mibsi.carplay.core.CarPlayClusterInput;
import de.audi.atip.base.IFrameworkAccess;
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
import de.audi.tghu.command.Command;
import de.audi.tghu.command.CommandList;
import de.audi.tghu.command.ICommandListFactory;
import de.audi.tghu.navi.app.NavigationEnv;
import de.audi.tghu.navi.app.OperationManager;
import de.audi.tghu.navi.app.SpeechManager;
import de.audi.tghu.navi.app.audio.AudioStateMachine;
import de.audi.tghu.navi.app.audio.LastAnnouncementHandler;
import de.audi.tghu.navi.app.cluster.BAPDistanceFormatter;
import de.audi.tghu.navi.app.cluster.BAPTMCSymbolMapping;
import de.audi.tghu.navi.app.cluster.ClusterService;
import de.audi.tghu.navi.app.cluster.ClusterViewMode;
import de.audi.tghu.navi.app.cluster.CombiRouteGuidanceMapEventListener;
import de.audi.tghu.navi.app.command.LIGetStateCommand;
import de.audi.tghu.navi.app.command.RGStopGuidanceCommand;
import de.audi.tghu.navi.app.command.via.RemoveViaPointsCommand;
import de.audi.tghu.navi.app.li.SpellerStack;
import de.audi.tghu.navi.app.li.sc.SpellerContextManager;
import de.audi.tghu.navi.app.map.AbstractMap;
import de.audi.tghu.navi.app.map.MapManager;
import de.audi.tghu.navi.app.map.context.CtxPositionMap;
import de.audi.tghu.navi.app.map.event.MapEventListener;
import de.audi.tghu.navi.app.map.handler.ISetupHandler;
import de.audi.tghu.navi.app.map.handler.IZoomHandler;
import de.audi.tghu.navi.app.util.Util;
import de.esolutions.fw.util.commons.Buffer;
import java.util.Arrays;
import org.dsi.ifc.navigation.BapManeuverDescriptor;
import org.dsi.ifc.navigation.BapTurnToInfo;
import org.dsi.ifc.navigation.NavLaneGuidanceData;
import org.dsi.ifc.tmc.TmcMessage;

public class CombiBAPListener
implements CombiBAPServiceNaviListener,
SpeechManager.GuidanceModeSettingsListener,
BAPTMCSymbolMapping {
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
    private BAPDistanceFormatter.BAPDistance mapScale = null;
    private int onlineNavigationState = 0;
    private int bufferProgress = 255;
    private final int onlineNavigationSystem;
    private boolean naviIsRunningOnSmartphone = false;
    protected boolean waitingForStartGuidanceResponse = false;
    private boolean popupButtonPressed = false;
    private CombiBAPDestinationListEntry[] lastDestinations = new CombiBAPDestinationListEntry[0];

    public CombiBAPListener(ClusterService clusterService, LogChannel logChannel, NavigationEnv navigationEnv, SpeechManager speechManager, OperationManager operationManager, AudioStateMachine audioStateMachine, MapManager mapManager, ICommandListFactory iCommandListFactory, IViewSizeManager iViewSizeManager) {
        this.env = navigationEnv;
        this.clusterservice = clusterService;
        this.logChannel = logChannel;
        this.speechManager = speechManager;
        this.operationManager = operationManager;
        this.audioStateMachine = audioStateMachine;
        this.mapManager = mapManager;
        this.commandListFactory = iCommandListFactory;
        this.viewSizeManagerFPK = iViewSizeManager;
        this.bapDistanceFormatter = new BAPDistanceFormatter(logChannel);
        this.mapManager.getMapEventDispatcher().addListener((MapEventListener)new CombiRouteGuidanceMapEventListener(logChannel, this));
        this.supportedMapTypes = this.getSupportedMapTypes();
        this.supportedMapViews = this.getSupportedMapViews();
        this.supportedSupplementaryMapViews = this.getSupportedSupplementaryMapViews();
        this.intersectionAutoZoomSupported = this.setupIntersectionZoomSupported();
        if (Util.isClusterMapFPK((IFrameworkAccess)navigationEnv.getFramework()) || Util.isClusterMMI((IFrameworkAccess)navigationEnv.getFramework())) {
            this.rgType = 4;
        }
        logChannel.log(1000000, "CombiBAPListener - supportedMapTypes: %1, supportedMapViews: %2, supportedSupplementaryMapViews: %3", (long)this.supportedMapTypes, (long)this.supportedMapViews, (long)this.supportedSupplementaryMapViews);
        this.onlineNavigationSystem = !Util.isClusterMapAvailable((IFrameworkAccess)navigationEnv.getFramework()) ? 0 : (Util.isGoogleEarthPresent((IFrameworkAccess)navigationEnv.getFramework()) ? 1 : 0);
        this.initExitViewVariant();
        speechManager.registerSettingsListener((SpeechManager.GuidanceModeSettingsListener)this);
    }

    protected boolean setupIntersectionZoomSupported() {
        return !Util.isHURegionAsia();
    }

    protected int getSupportedMapTypes() {
        if (Util.isClusterMapAvailable((IFrameworkAccess)this.env.getFramework())) {
            int n = 6;
            if (Util.isClusterMapFPK((IFrameworkAccess)this.env.getFramework())) {
                n |= 8;
                if (!Util.isHURegionAsia()) {
                    n |= 1;
                }
            }
            return n;
        }
        return 0;
    }

    protected int getSupportedMapViews() {
        boolean bl;
        if (!Util.isClusterMapAvailable((IFrameworkAccess)this.env.getFramework())) {
            return 0;
        }
        int n = 1;
        boolean bl2 = bl = !Util.isLockFeatureNavMapAdvancedMapEnabled((NavigationEnv)this.env) || !this.mapManager.getNaviInterface().isFeatureToBeLocked();
        if (Util.isGoogleEarthPresent((IFrameworkAccess)this.env.getFramework()) && bl) {
            n |= 2;
        }
        if (Util.isTrafficMapAvailable((IFrameworkAccess)this.env.getFramework())) {
            n |= 4;
        }
        if (Util.isOnlyOneOrNoBitSet((int)n)) {
            return 0;
        }
        return n;
    }

    protected int getSupportedSupplementaryMapViews() {
        if (Util.isClusterKDKAvailable((IFrameworkAccess)this.env.getFramework()) && !Util.isClusterMMI((IFrameworkAccess)this.env.getFramework())) {
            return 1;
        }
        return 0;
    }

    public void setLastDestinationsList(CombiBAPNaviDestination[] combiBAPNaviDestinationArray, String[] stringArray, FormattedDestination[] formattedDestinationArray) {
        this.logChannel.log(10000000, "CombiBAPListener#setLastDestinationsList lastDest.length=%1", (long)combiBAPNaviDestinationArray.length);
        if (this.logChannel.isDebug2()) {
            this.logChannel.log(100000000, "CombiBAPListener#setLastDestinationsList lastDest=%1", Arrays.asList(combiBAPNaviDestinationArray));
            this.logChannel.log(100000000, "CombiBAPListener#setLastDestinationsList descriptions=%1", Arrays.asList(stringArray));
        }
        this.lastDestinations = new CombiBAPDestinationListEntry[combiBAPNaviDestinationArray.length];
        for (int i = 0; i < combiBAPNaviDestinationArray.length; ++i) {
            CombiBAPDestinationListEntry combiBAPDestinationListEntry = formattedDestinationArray == null ? new CombiBAPDestinationListEntry(i + 1, combiBAPNaviDestinationArray[i].getPOIType(), stringArray[i], new CombiBAPNaviDestination[]{combiBAPNaviDestinationArray[i]}) : new CombiBAPDestinationListEntry(i + 1, combiBAPNaviDestinationArray[i].getPOIType(), stringArray[i], new CombiBAPNaviDestination[]{combiBAPNaviDestinationArray[i]}, formattedDestinationArray[i]);
            this.lastDestinations[i] = combiBAPDestinationListEntry;
        }
        this.updateLastDestinationsList();
    }

    public void setFavoritesDestinationsList(CombiBAPNaviDestination[] combiBAPNaviDestinationArray, String[] stringArray) {
        this.setFavoritesDestinationsList(combiBAPNaviDestinationArray, stringArray, null);
    }

    public void setFavoritesDestinationsList(CombiBAPNaviDestination[] combiBAPNaviDestinationArray, String[] stringArray, FormattedDestination[] formattedDestinationArray) {
        this.logChannel.log(10000000, "CombiBAPListener#updateFavoritesDestinationsList favorites.length=%1", (long)combiBAPNaviDestinationArray.length);
        if (this.logChannel.isDebug2()) {
            this.logChannel.log(100000000, "CombiBAPListener#updateFavoritesDestinationsList favorites=%1", Arrays.asList(combiBAPNaviDestinationArray));
            this.logChannel.log(100000000, "CombiBAPListener#updateFavoritesDestinationsList descriptions=%1", Arrays.asList(stringArray));
        }
        this.favoriteDestinationsList = new CombiBAPDestinationListEntry[combiBAPNaviDestinationArray.length];
        for (int i = 0; i < combiBAPNaviDestinationArray.length; ++i) {
            CombiBAPDestinationListEntry combiBAPDestinationListEntry = formattedDestinationArray == null ? new CombiBAPDestinationListEntry(i + 1, combiBAPNaviDestinationArray[i].getPOIType(), stringArray[i], new CombiBAPNaviDestination[]{combiBAPNaviDestinationArray[i]}) : new CombiBAPDestinationListEntry(i + 1, combiBAPNaviDestinationArray[i].getPOIType(), stringArray[i], new CombiBAPNaviDestination[]{combiBAPNaviDestinationArray[i]}, formattedDestinationArray[i]);
            this.favoriteDestinationsList[i] = combiBAPDestinationListEntry;
        }
        this.updateFavoriteDestinationsList();
    }

    private ISetupHandler checkedGetSetup() {
        AbstractMap abstractMap = this.mapManager.getMapKombi();
        if (abstractMap != null) {
            ISetupHandler iSetupHandler = abstractMap.getSetup();
            if (iSetupHandler == null) {
                this.logChannel.log(100000, "CombiBAPListener#checkedGetSetup() - setup unavailable");
            }
            return iSetupHandler;
        }
        this.logChannel.log(100000, "CombiBAPListener#checkedGetSetup() - no kombi map available");
        return null;
    }

    public void initFromSetup() {
        this.logChannel.log(10000000, "CombiBAPListener#initFromSetup()");
        if (this.mainMapandKombiMapSetupInitialized()) {
            ISetupHandler iSetupHandler = this.checkedGetSetup();
            if (iSetupHandler != null) {
                this.mapView = this.setupMapPresentation2BAP(iSetupHandler.getMapRepresentation());
                this.autoZoomSetting = this.setupAutoZoom2BAP(iSetupHandler.getAutoZoom());
                this.mapColor = this.setupMapColor2BAP(iSetupHandler.getDayNightView());
                this.activeMapType = this.setupMapType2BAP(iSetupHandler.getMapType());
                this.mapOrientation = this.setupMapOrientation2BAP(iSetupHandler.getOrientation());
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

    public int setupAutoZoom2BAP(int n) {
        switch (n) {
            case 2: {
                return 0;
            }
            case 0: {
                return 1;
            }
            case 1: {
                if (Util.isHURegionAsia()) {
                    return 1;
                }
                return 2;
            }
        }
        this.logChannel.log(100000, "CombiBAPListener#setupAutoZoom2BAP() - unknown auto-zoom type: %1", (long)n);
        return this.autoZoomSetting;
    }

    public int setupMapColor2BAP(int n) {
        switch (n) {
            case 0: {
                return 0;
            }
            case 1: {
                return 1;
            }
            case 2: {
                return 2;
            }
        }
        this.logChannel.log(10000, "CombiBAPListener#setupMapColor2BAP() - unknown map color: %1", (long)n);
        return this.mapColor;
    }

    public int setupMapType2BAP(int n) {
        switch (n) {
            case 0: {
                return 0;
            }
            case 1: {
                return 1;
            }
            case 2: {
                return 2;
            }
            case 3: {
                return 3;
            }
        }
        this.logChannel.log(100000, "CombiBAPListener#setupMapType2BAP() - unknown map type: %1", (long)n);
        return this.activeMapType;
    }

    public int setupMapOrientation2BAP(int n) {
        switch (n) {
            case 1: {
                return 2;
            }
            case 0: {
                return 1;
            }
        }
        this.logChannel.log(100000, "CombiBAPListener#setupMapOrientation2BAP() - unknown map orientation: %1", (long)n);
        return this.mapOrientation;
    }

    public int setupMapPresentation2BAP(int n) {
        switch (n) {
            case 0: {
                return 0;
            }
            case 1: {
                return 1;
            }
            case 2: {
                return 2;
            }
        }
        this.logChannel.log(100000, "CombiBAPListener#setupMapPresentation2BAP() - unknown map representation: %1", (long)n);
        return this.mapView;
    }

    private void updateAll() {
        this.logChannel.log(10000000, "CombiBAPListener#updateAll()");
        if (this.combiservice == null) {
            return;
        }
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

    public void setCombiService(CombiBAPServiceNavi combiBAPServiceNavi) {
        this.logChannel.log(1000000, "CombiBAPListener#setCombiService()");
        this.combiservice = combiBAPServiceNavi;
        this.updateAll();
    }

    protected boolean isNaviOperable() {
        return this.operationManager != null ? this.operationManager.isFullyOperable() : false;
    }

    private static int getTMCSymbolMapping(int n) {
        for (int i = 0; i < tmcEvent2CombiSymbols1.length; ++i) {
            for (int j = 0; j < tmcEvent2CombiSymbols1[i].length; ++j) {
                if (tmcEvent2CombiSymbols1[i][j] != n || tmcEvent2CombiSymbols1[i][j] < 0) continue;
                return tmcEvent2CombiSymbols2[i];
            }
        }
        return 0;
    }

    void setCurrentStreet(String string) {
        this.logChannel.log(10000000, "CombiBAPListener#currentStreet( %1 )", (Object)string);
        this.currentPositionInfo = string;
        this.updateCurrentPositionInfo();
    }

    void setDistanceToNextManeuver(int n, boolean bl, int n2) {
        this.logChannel.log(10000000, "CombiBAPListener#setDistanceToNextManeuver() - distance: %1, showBargraph: %2, bargraphValue: %3", (Object)new Integer(n), (Object)bl, (Object)new Integer(n2));
        int n3 = Math.min(100, (int)((float)n2 / 255.0f * 100.0f));
        this.maneuverDistance = n;
        this.maneuverBargraph = n3;
        this.maneuverShowBargraph = bl;
        if (n < 0 || bl && !this.maneuverShowBargraph) {
            Buffer buffer = new Buffer(500);
            buffer.append("[distanceInMeters: ").append(n);
            buffer.append(", showBargraph: ").append(bl);
            buffer.append(", bargraphValue: ").append(n2);
            buffer.append("] - [maneuverDistance: ").append(this.maneuverDistance);
            buffer.append(", maneuverShowBargraph: ").append(this.maneuverShowBargraph);
            buffer.append(", maneuverBargraph: ").append(this.maneuverBargraph);
            buffer.append(']');
            this.logChannel.log(100000, "CombiBAPListener#setDistanceToNextManeuver() - %1", (Object)buffer);
        }
        this.updateDistanceToNextManeuver();
    }

    void setRgTimeToNextDestination(long l, boolean bl, boolean bl2) {
        long l2;
        this.destinationTime = l2 = bl2 ? l / 1000L : -1L;
        this.destinationTimeType = bl2 ? (bl ? 1 : 0) : 15;
        this.destinationTimeUnit = DateMetric.timeFormat == 10 ? 0 : 1;
        this.updateTimeToDestination();
    }

    void setRgDistanceToNextDestination(int n, boolean bl) {
        this.destinationDistance = n;
        this.destinationDistanceIsToStopOver = bl;
        this.updateDistanceToDestination();
    }

    void setVehicleHeading(short s, short s2) {
        if (s != this.directionAngle || s2 != this.directionSymbolic) {
            this.directionAngle = s;
            this.directionSymbolic = s2;
            this.updateCompassInfo();
        }
    }

    private boolean isRgStatusValidForRgType() {
        if (Util.isClusterMapFPK((IFrameworkAccess)this.env.getFramework()) || Util.isClusterMMI((IFrameworkAccess)this.env.getFramework())) {
            return true;
        }
        if (Util.isBentley((IFrameworkAccess)this.env.getFramework()) || Util.isPorsche((IFrameworkAccess)this.env.getFramework()) || Util.isPorscheGen2((IFrameworkAccess)this.env.getFramework())) {
            return true;
        }
        return this.rgStatus != 1 || this.rgType != 2;
    }

    synchronized void setRgActive(boolean bl) {
        this.logChannel.log(10000000, "CombiBAPListener#setRgActive( %1 )", bl);
        int n = this.rgStatus = bl ? 1 : 0;
        if (!this.isRgStatusValidForRgType()) {
            this.logChannel.log(1000000, "CombiBAPListener#setRgActive() - rgStatus is not valid for rgType %1, deferring update...", (long)this.rgType);
        } else {
            this.updateRGStatusAndType();
        }
    }

    synchronized void setViewMode(int n) {
        int n2;
        this.logChannel.log(10000000, "CombiBAPListener#setViewMode() - view mode %1, oldType: %2", (long)n, (long)this.rgType);
        if (Util.isClusterMapFPK((IFrameworkAccess)this.env.getFramework()) || Util.isClusterMMI((IFrameworkAccess)this.env.getFramework())) {
            n2 = 4;
        } else {
            switch (n) {
                case 0: {
                    n2 = 2;
                    break;
                }
                case 1: {
                    n2 = 0;
                    break;
                }
                case 2: {
                    n2 = 1;
                    break;
                }
                case 3: {
                    n2 = 3;
                    break;
                }
                default: {
                    this.logChannel.log(10000, "CombiBAPListener#setViewMode() - invalid view mode %1 !", (long)n);
                    return;
                }
            }
        }
        if (this.rgType == n2) {
            return;
        }
        this.rgType = n2;
        if (!this.isRgStatusValidForRgType()) {
            this.logChannel.log(1000000, "CombiBAPListener#setViewMode() - rgType is not valid for rgStatus %1, deferring update...", (long)this.rgStatus);
        } else {
            this.updateRGStatusAndType();
        }
    }

    void setLaneGuidance(NavLaneGuidanceData[] navLaneGuidanceDataArray, boolean bl) {
        this.laneGuidanceEnable = bl;
        if (navLaneGuidanceDataArray != null) {
            this.laneGuidanceData = new CombiBAPNaviLaneGuidanceData[navLaneGuidanceDataArray.length];
            for (int i = 0; i < navLaneGuidanceDataArray.length; ++i) {
                NavLaneGuidanceData navLaneGuidanceData = navLaneGuidanceDataArray[i];
                if (navLaneGuidanceData != null) {
                    this.logChannel.log(10000000, "CombiBAPListener#setLaneGuidance() - lane %2: %1", (Object)navLaneGuidanceData, (long)i);
                    this.laneGuidanceData[i] = new CombiBAPNaviLaneGuidanceData(navLaneGuidanceData.pos, navLaneGuidanceData.laneDirection, navLaneGuidanceData.getLaneSideStreets(), navLaneGuidanceData.laneType, navLaneGuidanceData.laneMarkingLeft, navLaneGuidanceData.laneMarkingRight, navLaneGuidanceData.laneDescription, navLaneGuidanceData.guidanceInfo);
                    continue;
                }
                this.laneGuidanceData[i] = null;
            }
        } else {
            this.laneGuidanceData = new CombiBAPNaviLaneGuidanceData[0];
        }
        this.updateLaneGuidance();
    }

    private void applyManeuverDescriptor(BapManeuverDescriptor[] bapManeuverDescriptorArray) {
        if (bapManeuverDescriptorArray != null) {
            this.maneuverDescriptor = new CombiBAPNaviManeuverDescriptor[bapManeuverDescriptorArray.length];
            for (int i = 0; i < bapManeuverDescriptorArray.length; ++i) {
                BapManeuverDescriptor bapManeuverDescriptor = bapManeuverDescriptorArray[i];
                if (bapManeuverDescriptor != null) {
                    this.logChannel.log(10000000, "CombiBAPListener#applyManeuverDescriptor() - maneuver %2: %1", (Object)bapManeuverDescriptor, (long)i);
                    this.maneuverDescriptor[i] = new CombiBAPNaviManeuverDescriptor(bapManeuverDescriptor.mainElement, bapManeuverDescriptor.direction, bapManeuverDescriptor.zLevelGuidance, bapManeuverDescriptor.getSideStreets());
                    continue;
                }
                this.maneuverDescriptor[i] = null;
            }
        } else {
            this.logChannel.log(100000, "CombiBAPListener#applyManeuverDescriptor() - no data available!");
        }
    }

    void setManeuverDescriptor(BapManeuverDescriptor[] bapManeuverDescriptorArray) {
        this.applyManeuverDescriptor(bapManeuverDescriptorArray);
        this.maneuverDescriptorReceivedForCurrentRG = true;
        this.updateManeuverDescriptor();
        this.updateDistanceToNextManeuver();
        this.updateExitView();
    }

    void setTurnToInfo(BapTurnToInfo[] bapTurnToInfoArray) {
        String string;
        boolean bl = bapTurnToInfoArray != null && bapTurnToInfoArray.length > 0;
        String string2 = bl ? bapTurnToInfoArray[0].getTurnToInfo() : "";
        String string3 = string = bl ? bapTurnToInfoArray[0].getSignPost() : "";
        if (!string2.equals(this.turnToInfo) || !string.equals(this.signPost)) {
            this.signPost = string;
            this.turnToInfo = string2;
            this.updateTurnToInfo();
        }
        this.logChannel.log(100000000, "CombiBAPListener#setTurnToInfo() - %1", (Object)this.turnToInfo);
    }

    void setInfoStateGPS(int n) {
        this.logChannel.log(10000000, "CombiBAPListener#setInfoStateGPS( %1 )", (long)n);
        int n2 = 255;
        switch (n) {
            case 1: 
            case 2: {
                n2 = 0;
                break;
            }
            case 0: 
            case 3: 
            case 4: {
                n2 = 3;
                break;
            }
            default: {
                this.logChannel.log(10000, "CombiBAPListener#setInfoStateGPS() - state %1 is unknown!", (long)n);
            }
        }
        if (this.infoStatesGPS != n2) {
            this.infoStatesGPS = n2;
            this.updateInfoStates();
        }
    }

    public void setSupportedSupplementaryMapViews(int n) {
        this.logChannel.log(10000000, "CombiBAPListener#setSupportedSupplementaryMapView( %1 )", (long)n);
        this.supportedSupplementaryMapViews = n;
        this.updateSupportedMapViews();
    }

    boolean setInfoStateNavi(int n) {
        this.logChannel.log(10000000, "CombiBAPListener#setInfoStateNavi( %1 )", (long)n);
        int n2 = 255;
        switch (n) {
            case 0: 
            case 1: 
            case 12: {
                break;
            }
            case 5: 
            case 10: 
            case 11: 
            case 14: {
                n2 = 0;
                break;
            }
            case 2: 
            case 4: 
            case 6: 
            case 7: 
            case 9: {
                n2 = 2;
                break;
            }
            case 3: 
            case 8: {
                n2 = 1;
                break;
            }
            default: {
                this.logChannel.log(10000, "CombiBAPListener#setInfoStateNavi() - state %1 is unknown!", (long)n);
            }
        }
        if (this.infoStatesNavi != n2) {
            this.infoStatesNavi = n2;
            this.updateInfoStates();
        }
        return n2 != 255;
    }

    void setRouteGuidanceAborted() {
        this.logChannel.log(1000000, "CombiBAPListener#setRouteGuidanceAborted() - notify combi that route guidance has aborted");
        this.routeGuidanceActDeactResult(1);
    }

    public void setXUrgentMessages(TmcMessage[] tmcMessageArray) {
        if (tmcMessageArray != null) {
            this.xUrgentMessages = new CombiBAPTMCInfoMessage[tmcMessageArray.length];
            for (int i = 0; i < tmcMessageArray.length; ++i) {
                TmcMessage tmcMessage = tmcMessageArray[i];
                this.xUrgentMessages[i] = new CombiBAPTMCInfoMessage(0, new Buffer().append(tmcMessage.getDirectionOfRoad1()).append('\n').append(tmcMessage.getDirectionOfRoad2()).toString(), new Buffer().append(tmcMessage.getStartLocation()).append('\n').append(tmcMessage.getEndLocation()).toString(), tmcMessage.getEventText()[0], tmcMessage.getAffectedRoadLength(), 0);
            }
            this.updateXUrgentMessages();
        } else {
            this.logChannel.log(100000, "CombiBAPListener#setXUrgentMessages() - no data available!");
        }
    }

    public void setMessagesOnRoute(TmcMessage[] tmcMessageArray) {
        if (tmcMessageArray != null && tmcMessageArray.length > 0) {
            int[] nArray = tmcMessageArray[0].getEventCode();
            if (nArray != null && nArray.length > 0) {
                this.tmcSymbolOnRoute = CombiBAPListener.getTMCSymbolMapping(nArray[0]);
                this.updateMessagesOnRoute();
            } else {
                this.logChannel.log(100000, "CombiBAPListener#setMessagesOnRoute() - eventCode[] is either null or empty!");
            }
        } else {
            this.logChannel.log(100000, "CombiBAPListener#setMessagesOnRoute() - tmcMessagesAhead[] is either null or empty!");
        }
    }

    public void setAltitude(int n) {
        this.logChannel.log(10000000, "CombiBAPListener#setAltitude( %1 )", (long)n);
        this.altitude = n;
        this.updateAltitude();
    }

    public void setDestinationInfo(CombiBAPDestinationInfo combiBAPDestinationInfo) {
        this.logChannel.log(10000000, "CombiBAPListener#setDestinationInfo( %1 )", (Object)combiBAPDestinationInfo);
        this.destInfo = combiBAPDestinationInfo;
        this.updateDestinationInfo();
    }

    public void setSemidynamicRouteGuidance(CombiBAPSemiDynamicRouteInfo combiBAPSemiDynamicRouteInfo) {
        this.semiDynRouteInfo = combiBAPSemiDynamicRouteInfo;
        BAPDistanceFormatter.BAPDistance bAPDistance = this.bapDistanceFormatter.formatDistanceToDestination((int)combiBAPSemiDynamicRouteInfo.getNewDTDDistance(), CombiBAPListener.isMetricSystemInUse());
        this.semiDynRouteInfo.setDistanceToDestination((long)bAPDistance.getValue(), bAPDistance.getUnit(), combiBAPSemiDynamicRouteInfo.getNewDTDTypeOfDistance());
        this.updateSemidynamicRouteGuidance();
    }

    public void setExitView(int n) {
        this.logChannel.log(10000000, "CombiBAPListener#setExitView( %1 )", (long)n);
        this.exitviewID = n;
        this.updateExitView();
    }

    public void updateGuidanceMode(int n) {
        this.logChannel.log(10000000, "CombiBAPListener#updateGuidanceMode( %1 )", (long)n);
        switch (n) {
            case 1: {
                this.voiceGuidanceState = 0;
                break;
            }
            case 0: {
                this.voiceGuidanceState = 2;
                break;
            }
            case 3: {
                this.voiceGuidanceState = 1;
                break;
            }
            case 2: {
                this.voiceGuidanceState = 3;
                break;
            }
            default: {
                this.logChannel.log(10000, "CombiBAPListener#setVoiceGuidanceMode() - value %1 is unknown, guidance is disabled!", (long)n);
            }
        }
        this.updateVoiceGuidanceState();
    }

    private void updateCurrentPositionInfo() {
        this.logChannel.log(10000000, "CombiBAPListener#updateCurrentPositionInfo()");
        try {
            this.combiservice.updateCurrentPositionInfo(this.currentPositionInfo);
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateCurrentPositionInfo() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateCurrentPositionInfo() - ERROR=%1 ", (Throwable)exception);
        }
    }

    private void updateCompassInfo() {
        this.logChannel.log(10000000, "CombiBAPListener#updateCompassInfo()");
        try {
            this.combiservice.updateCompassInfo(this.directionAngle, this.directionSymbolic);
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateCompassInfo() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateCompassInfo() - ERROR=%1 ", (Throwable)exception);
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
            }
            catch (NullPointerException nullPointerException) {
                this.logChannel.log(100000, "CombiBAPListener#updateRGStatusAndType() - no combi service registered!");
            }
            catch (Exception exception) {
                this.logChannel.log(10000, "CombiBAPListener#updateRGStatusAndType() - ERROR=%1 ", (Throwable)exception);
            }
            if (!this.validRGTypeReceived) {
                this.validRGTypeReceived(true);
            }
        } else {
            this.logChannel.log(100000, "CombiBAPListener#updateRGStatusAndType() - invalid value: %1", (long)this.rgType);
            this.validRGTypeReceived(false);
        }
    }

    void updateDistanceToNextManeuver() {
        try {
            BAPDistanceFormatter.BAPDistance bAPDistance = this.bapDistanceFormatter.formatDistanceToTurn(this.maneuverDistance, CombiBAPListener.isMetricSystemInUse());
            this.combiservice.updateDistanceToNextManeuver(bAPDistance.getValue(), bAPDistance.getUnit(), this.maneuverShowBargraph, this.maneuverBargraph);
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateDistanceToNextManeuver() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateDistanceToNextManeuver() - ERROR=%1 ", (Throwable)exception);
        }
    }

    void updateDistanceToDestination() {
        try {
            BAPDistanceFormatter.BAPDistance bAPDistance = this.bapDistanceFormatter.formatDistanceToDestination(this.destinationDistance, CombiBAPListener.isMetricSystemInUse());
            this.combiservice.updateDistanceToDestination(bAPDistance.getValue(), bAPDistance.getUnit(), this.destinationDistanceIsToStopOver);
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateDistanceToDestination() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateDistanceToDestination() - ERROR=%1 ", (Throwable)exception);
        }
    }

    private void updateTimeToDestination() {
        try {
            this.combiservice.updateTimeToDestination(this.destinationTimeType, this.destinationTimeUnit, this.destinationTime);
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateTimeToDestination() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateTimeToDestination() - ERROR=%1 ", (Throwable)exception);
        }
    }

    private void updateTurnToInfo() {
        try {
            this.combiservice.updateTurnToInfo(this.turnToInfo, this.signPost);
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateTurnToInfo() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateTurnToInfo() - ERROR=%1 ", (Throwable)exception);
        }
    }

    private void updateActiveRGType() {
        if (this.rgType != -1) {
            try {
                if (this.naviIsRunningOnSmartphone && !Util.isClusterMMI((IFrameworkAccess)this.env.getFramework())) {
                    this.combiservice.updateActiveRGType(2);
                } else if (Util.isClusterMapFPK((IFrameworkAccess)this.env.getFramework()) || Util.isClusterMMI((IFrameworkAccess)this.env.getFramework())) {
                    this.combiservice.updateActiveRGType(4);
                } else {
                    this.combiservice.updateActiveRGType(this.rgType);
                }
            }
            catch (NullPointerException nullPointerException) {
                this.logChannel.log(100000, "CombiBAPListener#updateActiveRGType() - no combi service registered!");
            }
            catch (Exception exception) {
                this.logChannel.log(10000, "CombiBAPListener#updateActiveRGType() - ERROR=%1 ", (Throwable)exception);
            }
            if (!this.validRGTypeReceived) {
                this.validRGTypeReceived(true);
            }
        } else {
            this.logChannel.log(100000, "CombiBAPListener#updateActiveRGType() - invalid value: %1", (long)this.rgType);
            this.validRGTypeReceived(false);
        }
    }

    private void repeatLastNavAnnouncementResult(int n) {
        try {
            this.combiservice.repeatLastNavAnnouncementResult(n);
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#repeatLastNavAnnouncementResult() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#repeatLastNavAnnouncementResult() - ERROR=%1 ", (Throwable)exception);
        }
    }

    private void updateManeuverDescriptor() {
        try {
            if (this.maneuverDescriptor != null) {
                this.combiservice.updateManeuverDescriptor(this.maneuverDescriptor);
            } else {
                this.logChannel.log(100000, "CombiBAPListener#updateManeuverDescriptor() - no data available!");
            }
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateManeuverDescriptor() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateManeuverDescriptor() - ERROR=%1 ", (Throwable)exception);
        }
    }

    private void updateLaneGuidance() {
        try {
            if (this.laneGuidanceData != null) {
                this.combiservice.updateLaneGuidance(this.laneGuidanceEnable, this.laneGuidanceData);
            } else {
                this.logChannel.log(100000, "CombiBAPListener#updateLaneGuidance() - no data available!");
            }
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateLaneGuidance() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateLaneGuidance() - ERROR=%1 ", (Throwable)exception);
        }
    }

    protected void routeGuidanceActDeactResult(int n) {
        this.logChannel.log(10000000, "CombiBAPListener#routeGuidanceActDeactResult( %1 )", (long)n);
        this.waitingForStartGuidanceResponse = false;
        this.popupButtonPressed = false;
        try {
            this.combiservice.routeGuidanceActDeactResult(n);
            if (n == 0) {
                this.updateCurrentPositionInfo();
            }
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#routeGuidanceActDeactResult() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#routeGuidanceActDeactResult() - ERROR=%1 ", (Throwable)exception);
        }
    }

    private void updateVoiceGuidanceState() {
        try {
            this.combiservice.updateVoiceGuidanceState(this.voiceGuidanceState);
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateVoiceGuidanceState() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateVoiceGuidanceState() - ERROR=%1 ", (Throwable)exception);
        }
    }

    private void updateInfoStates() {
        try {
            int n = 0;
            if (this.naviIsRunningOnSmartphone) {
                n = 6;
            } else if (n != this.infoStatesNavi) {
                n = this.infoStatesNavi;
            } else if (n != this.infoStatesGPS) {
                n = this.infoStatesGPS;
            }
            if (n == 0 || n == 3 || n == 6) {
                if (this.validRGTypeReceived) {
                    this.logChannel.log(10000000, "CombiBAPListener#updateInfoStates() - %1", (long)n);
                    this.adaptActiveRGType(n);
                    this.combiservice.updateInfoStates(n);
                }
            } else {
                this.logChannel.log(10000000, "CombiBAPListener#updateInfoStates() - %1", (long)n);
                this.adaptActiveRGType(n);
                this.combiservice.updateInfoStates(n);
            }
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateInfoStates() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateInfoStates() - ERROR=%1 ", (Throwable)exception);
        }
    }

    private void updateXUrgentMessages() {
        try {
            if (this.xUrgentMessages != null) {
                this.combiservice.updateTMCInfoMessages(this.xUrgentMessages);
            } else {
                this.logChannel.log(100000, "CombiBAPListener#updateXUrgentMessages() - no data available!");
            }
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateXUrgentMessages() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateXUrgentMessages() - ERROR=%1 ", (Throwable)exception);
        }
    }

    private void updateMessagesOnRoute() {
        try {
            this.combiservice.updateTrafficBlockIndication(this.tmcSymbolOnRoute);
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateMessagesOnRoute() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateMessagesOnRoute() - ERROR=%1 ", (Throwable)exception);
        }
    }

    private void updateLastDestinationsList() {
        try {
            this.combiservice.updateLastDestinationsList(this.lastDestinations);
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateLastDestinationsList() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateLastDestinationsList() - ERROR=%1 ", (Throwable)exception);
        }
    }

    void updateAltitude() {
        try {
            BAPDistanceFormatter.BAPDistance bAPDistance = this.bapDistanceFormatter.formatAltitude(this.altitude, CombiBAPListener.isMetricSystemInUse());
            this.combiservice.updateAltitude(bAPDistance.getValue(), bAPDistance.getUnit());
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateAltitude() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateAltitude() - ERROR=%1 ", (Throwable)exception);
        }
    }

    private void updateDestinationInfo() {
        try {
            if (this.destInfo != null) {
                this.combiservice.updateDestinationInfo(this.destInfo);
            } else {
                this.logChannel.log(100000, "CombiBAPListener#updateDestinationInfo() - no data available!");
            }
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateDestinationInfo() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateDestinationInfo() - ERROR=%1 ", (Throwable)exception);
        }
    }

    private void updateHomeAddress() {
        try {
            this.combiservice.updateHomeAddress(this.homeAddress);
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateHomeAddress() - ERROR=%1 ", (Throwable)exception);
        }
    }

    private void updateExitView() {
        try {
            this.combiservice.updateExitView(this.exitViewVariant, this.exitviewID);
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateExitView() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateExitView() - ERROR=%1 ", (Throwable)exception);
        }
    }

    private void initExitViewVariant() {
        this.exitViewVariant = Util.isHURegionAsia() ? 3 : (Util.isHURegionNAR() ? 1 : (Util.isHURegionRdW() ? 2 : 0));
    }

    private void updateFavoriteDestinationsList() {
        try {
            if (this.favoriteDestinationsList != null) {
                this.combiservice.updateFavoriteDestinationsList(this.favoriteDestinationsList);
            } else {
                this.logChannel.log(100000, "CombiBAPListener#updateFavoriteDestinationsList() - no data available!");
            }
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateFavoriteDestinationsList() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateFavoriteDestinationsList() - ERROR=%1 ", (Throwable)exception);
        }
    }

    private void updateFSGSetup() {
        try {
            this.combiservice.updateFSGSetup(this.voiceGuidanceSetup, this.poiSearchSupported);
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateFSGSetup() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateFSGSetup() - ERROR=%1 ", (Throwable)exception);
        }
    }

    private void updateMapColor() {
        try {
            this.combiservice.updateMapColor(this.mapColor);
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateMapColor() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateMapColor() - ERROR=%1 ", (Throwable)exception);
        }
    }

    protected void updateMapOrientation() {
        try {
            this.combiservice.updateMapOrientation(this.mapOrientation);
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateMapOrientation() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateMapOrientation() - ERROR=%1 ", (Throwable)exception);
        }
    }

    private void updateMapPresentation() {
        try {
            this.combiservice.updateMapPresentation(this.largeMapView, this.leftSideMenuOpen, this.rightSideMenuOpen);
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateMapPresentation() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateMapPresentation() - ERROR=%1 ", (Throwable)exception);
        }
    }

    void onMapScaleChanged(int n) {
        this.logChannel.log(100000000, "CombiBAPListener#onMapScaleChanged() - zoomIndex: %1", (long)n);
        AbstractMap abstractMap = this.mapManager.getMapKombi();
        if (abstractMap != null) {
            IZoomHandler iZoomHandler = abstractMap.getZoomHandler();
            int n2 = Math.round(iZoomHandler.getZoomLevel(n) / 100.0f);
            abstractMap.getSetup().setSavedZoomListIndex(n, true);
            this.mapScale = this.bapDistanceFormatter.formatMapScale(n2, CombiBAPListener.isMetricSystemInUse());
            this.updateMapScale();
        }
    }

    void onUnitsChanged() {
    }

    protected void updateMapScale() {
        if (this.mapScale != null) {
            try {
                this.combiservice.updateMapScale(this.autoZoomSetting, this.autoZoomActive, this.mapScale.getValue(), this.mapScale.getUnit(), this.intersectionAutoZoomSupported);
            }
            catch (NullPointerException nullPointerException) {
                this.logChannel.log(100000, "CombiBAPListener#updateMapScale() - no combi service registered!");
            }
            catch (Exception exception) {
                this.logChannel.log(10000, "CombiBAPListener#updateMapScale() - ERROR=%1 ", (Throwable)exception);
            }
        }
    }

    public void setAutoZoomActive(boolean bl) {
        this.logChannel.log(100000, "CombiBAPListener#setAutoZoomActive( %1 )", bl);
        this.autoZoomActive = bl;
        this.updateMapScale();
    }

    protected void updateMapType() {
        try {
            this.combiservice.updateMapType(this.activeMapType, this.mainMapSetup);
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateMapType() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateMapType() - ERROR=%1 ", (Throwable)exception);
        }
    }

    protected void updateMapView() {
        try {
            this.combiservice.updateMapView(this.mapView, this.supplementaryMapView);
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateMapView() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateMapView() - ERROR=%1 ", (Throwable)exception);
        }
    }

    protected void updateMapVisibility() {
        try {
            this.combiservice.updateMapVisibility(this.lvdsMapVisible, this.supplementaryMapViewVisible);
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateMapVisibility() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateMapVisibility() - ERROR=%1 ", (Throwable)exception);
        }
    }

    private void updateManeuverState() {
        try {
            this.combiservice.updateManeuverState(this.maneuverState);
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateManeuverState() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateManeuverState() - ERROR=%1 ", (Throwable)exception);
        }
    }

    void updateSemidynamicRouteGuidance() {
        try {
            if (this.semiDynRouteInfo != null) {
                this.combiservice.updateSemidynamicRouteGuidance(this.semiDynRouteInfo);
            } else {
                this.logChannel.log(100000, "CombiBAPListener#updateSemidynamicRouteGuidance() - no data available!");
            }
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateSemidynamicRouteGuidance() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateSemidynamicRouteGuidance() - ERROR=%1 ", (Throwable)exception);
        }
    }

    private void updateSupportedMapTypes() {
        try {
            this.combiservice.updateSupportedMapTypes(this.mainMapSetupSupported, this.supportedMapTypes);
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateSupportedMapTypes() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateSupportedMapTypes() - ERROR=%1 ", (Throwable)exception);
        }
    }

    private void updateSupportedMapViews() {
        try {
            this.combiservice.updateSupportedMapViews(this.supportedMapViews, this.supportedSupplementaryMapViews);
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateSupportedMapViews() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateSupportedMapViews() - ERROR=%1 ", (Throwable)exception);
        }
    }

    private void updateOnlineNavigationState() {
        try {
            this.combiservice.updateOnlineNavigationState(this.onlineNavigationState, this.bufferProgress, this.onlineNavigationSystem);
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateOnlineNavigationState() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateOnlineNavigationState() - ERROR=%1 ", (Throwable)exception);
        }
    }

    public void setActiveRGType(int n) {
        this.logChannel.log(10000000, "CombiBAPListener#setActiveRGType( %1 )", (long)n);
        int n2 = -1;
        if ((Util.isClusterMapFPK((IFrameworkAccess)this.env.getFramework()) || Util.isClusterMMI((IFrameworkAccess)this.env.getFramework())) && n != 4) {
            this.logChannel.log(10000, "CombiBAPListener#setActiveRGType() - invalid activeRgType for FPK/G24: %1", (long)n);
            return;
        }
        switch (n) {
            case 2: {
                n2 = 0;
                break;
            }
            case 0: {
                n2 = 1;
                break;
            }
            case 1: {
                n2 = 2;
                break;
            }
            case 3: {
                n2 = 3;
                break;
            }
            default: {
                this.logChannel.log(10000, "CombiBAPListener#setActiveRGType() - invalid view mode %1 !", (long)n2);
            }
        }
        if (n2 != -1) {
            this.logChannel.log(10000000, "CombiBAPListener#setActiveRGType() - set new mode %1 !", (Object)ClusterViewMode.viewModeToString((int)n2));
            this.clusterservice.getClusterInputListener().setFavoredViewMode(n2, true);
        } else {
            this.updateActiveRGType();
        }
    }

    public void repeatLastNavAnnouncement() {
        this.logChannel.log(10000000, "CombiBAPListener#repeatLastNavAnnouncement()");
        if (this.isNaviOperable()) {
            LastAnnouncementHandler lastAnnouncementHandler = this.audioStateMachine.getLastAnnouncementHandler();
            lastAnnouncementHandler.repeatLastAnnouncement(1);
            this.repeatLastNavAnnouncementResult(lastAnnouncementHandler.isAnnouncementRepeatActive() ? 0 : 1);
        } else {
            this.logChannel.log(10000, "CombiBAPListener#repeatLastNavAnnouncement() - navigation not operable!");
            this.repeatLastNavAnnouncementResult(1);
        }
    }

    public void startRouteGuidanceToHomeAddress() {
        this.logChannel.log(1000000, "CombiBAPListener#startRouteGuidanceToHomeAddress()");
        if (this.isNaviOperable()) {
            CommandList commandList = this.commandListFactory.createCommandList();
            commandList.add((Command)new LIGetStateCommand(SpellerStack.getInstance(), SpellerContextManager.getSpellerContext((int)121)));
            commandList.add((Command)new /* Unavailable Anonymous Inner Class!! */);
            commandList.execute("StartRouteGuidanceToHomeAddress");
        } else {
            this.logChannel.log(10000, "CombiBAPListener#startRouteGuidanceToHomeAddress() - navigation not operable!");
            this.routeGuidanceActDeactResult(1);
        }
    }

    public void startRouteGuidance(CombiBAPNaviDestination combiBAPNaviDestination) {
        this.logChannel.log(1000000, "CombiBAPListener#startRouteGuidance() %1", (Object)combiBAPNaviDestination);
        if (this.isNaviOperable()) {
            CommandList commandList = this.commandListFactory.createCommandList();
            commandList.add((Command)new LIGetStateCommand(SpellerStack.getInstance(), SpellerContextManager.getSpellerContext((int)121)));
            commandList.add((Command)new /* Unavailable Anonymous Inner Class!! */);
            commandList.add((Command)new /* Unavailable Anonymous Inner Class!! */);
            commandList.add((Command)new /* Unavailable Anonymous Inner Class!! */);
            commandList.setErrorCommand((Command)new /* Unavailable Anonymous Inner Class!! */);
            commandList.execute("CombiBAPListener#StartRouteGuidance");
        } else {
            this.logChannel.log(10000, "CombiBAPListener#startRouteGuidance() - navigation not operable!");
            this.routeGuidanceActDeactResult(1);
        }
    }

    public void stopRouteGuidance() {
        this.logChannel.log(1000000, "CombiBAPListener#stopRouteGuidance()");
        if (this.isNaviOperable()) {
            if (this.rgStatus == 1) {
                CommandList commandList = this.commandListFactory.createCommandList();
                commandList.add((Command)new RGStopGuidanceCommand());
                commandList.add((Command)new RemoveViaPointsCommand());
                commandList.add((Command)new /* Unavailable Anonymous Inner Class!! */);
                commandList.setErrorCommand((Command)new /* Unavailable Anonymous Inner Class!! */);
                commandList.execute("CombiBAPListener#stopRouteGuidance");
            } else {
                this.routeGuidanceActDeactResult(4);
            }
        } else {
            this.logChannel.log(10000, "CombiBAPListener#stopRouteGuidance() - navigation not operable!");
            this.routeGuidanceActDeactResult(1);
        }
    }

    public void partialPopupRemoved(int n) {
        this.logChannel.log(1000000, "CombiBAPListener#partialPopupRemoved() - waitingForStartGuidanceResponse: %1, popupButtonPressed: %2", this.waitingForStartGuidanceResponse, this.popupButtonPressed);
        if (this.waitingForStartGuidanceResponse && !this.popupButtonPressed) {
            this.routeGuidanceActDeactResult(2);
        }
        this.popupButtonPressed = false;
    }

    public void popupButtonPressed() {
        this.popupButtonPressed = true;
    }

    public void setVoiceGuidanceState(int n) {
        this.logChannel.log(10000000, "CombiBAPListener#setVoiceGuidanceState( %1 )", (long)n);
        if (this.isNaviOperable()) {
            int n2 = 3;
            switch (n) {
                case 0: {
                    n2 = 1;
                    break;
                }
                case 2: {
                    n2 = 0;
                    break;
                }
                case 3: {
                    n2 = 2;
                    break;
                }
                case 1: {
                    n2 = 3;
                    break;
                }
                default: {
                    this.logChannel.log(10000, "CombiBAPListener#setVoiceGuidanceState() - value %1 is unknown, guidance is disabled!", (long)n);
                }
            }
            this.speechManager.setGuidanceMode(n2, true);
            CommandList commandList = this.speechManager.buildGuidanceModeCommandList(n2);
            commandList.execute("CombiBAPListener#setVoiceGuidanceState");
        } else {
            this.logChannel.log(10000, "CombiBAPListener#setVoiceGuidanceState() - navigation not operable!");
            this.updateVoiceGuidanceState();
        }
    }

    public void setMapColor(int n) {
        this.logChannel.log(10000000, "CombiBAPListener#setMapColor() - color: %1", (long)n);
        if (this.mainMapandKombiMapSetupInitialized()) {
            this.mapColor = n;
            switch (n) {
                case 0: {
                    this.mapManager.setMapColor(0);
                    break;
                }
                case 1: {
                    this.mapManager.setMapColor(1);
                    break;
                }
                case 2: {
                    this.mapManager.setMapColor(2);
                    break;
                }
                default: {
                    this.logChannel.log(10000, "CombiBAPListener#setMapColor() - invalid map color: %1 ", (long)n);
                }
            }
            this.updateMapColor();
        } else {
            this.logChannel.log(100000, "CombiBAPListener#setMapColor() - one of the map instance is not initialized, cannot setMapColor!");
        }
    }

    public void setMapType(int n, int n2) {
        AbstractMap abstractMap;
        this.logChannel.log(10000000, "CombiBAPListener#setMapType() - activeMapType: %1, mainMapSetup: %2 not handled", (long)n, (long)n2);
        this.activeMapType = n;
        this.mainMapSetup = n2;
        int n3 = -1;
        switch (n) {
            case 0: {
                n3 = 0;
                break;
            }
            case 3: {
                n3 = 3;
                break;
            }
            case 1: {
                n3 = 1;
                break;
            }
            case 2: {
                n3 = 2;
                break;
            }
            default: {
                this.logChannel.log(100000, "CombiBAPListener#setMapType() - activeMapType:%1 not handled", (long)n);
            }
        }
        if (n3 >= 0 && (abstractMap = this.mapManager.getMapKombi()) != null) {
            abstractMap.getSetup().setMapType(n3, true);
        }
        this.updateMapType();
    }

    public void setMapView(int n, int n2) {
        this.logChannel.log(10000000, "CombiBAPListener#setMapView() - mapView: %1, supplementaryMapView: %2", (long)n, (long)n2);
        int n3 = -1;
        switch (n) {
            case 0: {
                n3 = 0;
                break;
            }
            case 1: {
                n3 = 1;
                break;
            }
            case 2: {
                n3 = 2;
                break;
            }
            default: {
                this.logChannel.log(100000, "CombiBAPListener#setMapView() - MapView:%1 not handled", (long)n);
            }
        }
        AbstractMap abstractMap = this.mapManager.getMapKombi();
        if (n3 >= 0 && abstractMap != null && abstractMap.isInitialized()) {
            this.mapView = n;
            this.mapManager.setMapRepresentation(n3, true);
        }
        if (this.supplementaryMapView != n2) {
            this.supplementaryMapView = n2;
            this.clusterservice.setSupplementaryMap(this.getSupplementaryMapView(), this.supplementaryMapViewVisible);
        }
        this.updateMapView();
    }

    public void mapViewChanged(boolean bl) {
        if (bl) {
            int n = -1;
            switch (this.mapView) {
                case 0: {
                    n = 0;
                    break;
                }
                case 1: {
                    n = 1;
                    break;
                }
                case 2: {
                    n = 2;
                    break;
                }
                default: {
                    this.logChannel.log(100000, "CombiBAPListener#setMapView() - MapView:%1 not handled", (long)this.mapView);
                }
            }
            AbstractMap abstractMap = this.mapManager.getMapKombi();
            if (n >= 0 && abstractMap != null && abstractMap.isInitialized() && this.mapManager.getMapInterface().isGoogleTempDisabled() && n != 1) {
                this.mapManager.getMapInterface().setGoogleTempDisabled(false);
            }
        }
    }

    public int getSupplementaryMapView() {
        switch (this.supplementaryMapView) {
            case 3: {
                return 3;
            }
            case 2: {
                return 2;
            }
            case 1: {
                return 1;
            }
            case 0: {
                return 0;
            }
        }
        this.logChannel.log(100000, "CombiBAPListener#getSupplementaryMapView() - unknown supplementary map view mode (BAP): %1", (long)this.supplementaryMapView);
        return 0;
    }

    public void setSupplementaryMapView(int n) {
        this.setMapView(this.mapView, n);
    }

    public void setMapVisibility(boolean bl, boolean bl2) {
        this.setMapVisibility(bl, bl2, false);
    }

    public void setMainMapVisibility(boolean bl) {
        this.logChannel.log(10000000, "CombiBAPListener#setMainMapVisibility() - %1", bl);
        this.setMapVisibility(bl, this.supplementaryMapViewVisible);
    }

    public void setMapVisibility(boolean bl, boolean bl2, boolean bl3) {
        this.logChannel.log(10000000, "CombiBAPListener#setMapVisibility() - lvdsMapVisible: %1, supplementaryMapViewVisible: %2, supplementaryMapVisibilityAlreadyChanged: %3", bl, bl2, bl3);
        if (this.lvdsMapVisible != bl) {
            this.lvdsMapVisible = bl;
            this.clusterservice.showKombiMap(bl);
        }
        if (this.supplementaryMapViewVisible != bl2) {
            this.supplementaryMapViewVisible = bl2;
            if (!bl3) {
                this.clusterservice.setSupplementaryMap(this.getSupplementaryMapView(), bl2);
            }
        }
        this.updateMapVisibility();
    }

    public void setSupplementaryMapVisibility(boolean bl, boolean bl2) {
        this.logChannel.log(10000000, "CombiBAPListener#setSupplementaryMapVisibility() - %1", bl);
        this.setMapVisibility(this.lvdsMapVisible, bl, bl2);
    }

    public void setMapOrientation(int n) {
        this.logChannel.log(10000000, "CombiBAPListener#setMapOrientation() - orientation: %1", (long)n);
        if (this.mapManager.getMapKombi() != null) {
            this.mapOrientation = n;
            if (n == 0) {
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

    protected int mapOrientationFromBap(int n) {
        switch (n) {
            case 2: {
                return 1;
            }
            case 1: {
                return 0;
            }
        }
        this.logChannel.log(100000, "CombiBAPListener#mapOrientationFromBap() - unknown map orientation: %1", (long)n);
        return -1;
    }

    protected int mapOrientationToBap(int n) {
        switch (n) {
            case 1: {
                return 2;
            }
            case 0: {
                return 1;
            }
        }
        this.logChannel.log(100000, "CombiBAPListener#setMapOrientation() - unknown map orientation: %1", (long)n);
        return -1;
    }

    protected int mapMapTypeToBap(int n) {
        switch (n) {
            case 0: {
                return 0;
            }
            case 3: {
                return 3;
            }
            case 1: {
                return 1;
            }
            case 2: {
                return 2;
            }
        }
        this.logChannel.log(100000, "CombiBAPListener#setMapType() - activeMapType:%1 not handled", (long)this.activeMapType);
        return 2;
    }

    protected int mapMapTypeFromBap(int n) {
        switch (n) {
            case 0: {
                return 0;
            }
            case 3: {
                return 3;
            }
            case 1: {
                return 1;
            }
            case 2: {
                return 2;
            }
        }
        this.logChannel.log(100000, "CombiBAPListener#setMapType() - activeMapType:%1 not handled", (long)n);
        return 2;
    }

    public void setMapScale(int n) {
        n = CarPlayClusterInput.onMapScale(n, this.combiservice);
        this.logChannel.log(10000000, "CombiBAPListener#setMapScale() - steps: %1", (long)n);
        AbstractMap abstractMap = this.mapManager.getMapKombi();
        if (abstractMap != null && n != 0) {
            abstractMap.getActiveContext().increment(400476, n);
            if (this.env.getFramework().getSysConstManager().getSysConst(4445) == 1) {
                abstractMap.getSetup().setAutoZoom(2, true);
            }
        }
        this.updateMapScale();
    }

    public void setMapScaleSetting(int n) {
        this.logChannel.log(10000000, "CombiBAPListener#setMapScaleSetting() - autoZoomSetting: %1", (long)n);
        this.autoZoomSetting = n;
        AbstractMap abstractMap = this.mapManager.getMapKombi();
        if (abstractMap != null) {
            switch (n) {
                case 0: {
                    abstractMap.getSetup().setAutoZoom(2, true);
                    break;
                }
                case 1: {
                    if (Util.isHURegionAsia()) {
                        abstractMap.getSetup().setAutoZoom(1, true);
                        break;
                    }
                    abstractMap.getSetup().setAutoZoom(0, true);
                    break;
                }
                case 2: {
                    if (!this.intersectionAutoZoomSupported) {
                        this.logChannel.log(100000, "CombiBAPListener#setMapScaleSetting() - intersection zoom requested although we notified the Kombi that it is unsupported");
                    }
                    abstractMap.getSetup().setAutoZoom(1, true);
                    break;
                }
                case 15: {
                    abstractMap.getSetup().setAutoZoom(2, true);
                    break;
                }
                default: {
                    this.logChannel.log(100000, "CombiBAPListener#setMapScaleSetting() - autoZoomSetting: %1 not handled", (long)n);
                }
            }
            if (abstractMap.getActiveContext() instanceof CtxPositionMap) {
                abstractMap.switchToAShownContext();
            }
        } else {
            this.logChannel.log(100000, "CombiBAPListener#setMapScaleSetting() - no kombi map available");
        }
        this.updateMapScale();
    }

    public void startPOISearch(int n, int n2) {
        int n3 = 1;
        int n4 = 0;
        try {
            this.combiservice.poiSearchResult(n3, n4);
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#startPOISearch() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#startPOISearch() - ERROR=%1 ", (Throwable)exception);
        }
    }

    public void setMapPresentation(boolean bl, boolean bl2, boolean bl3) {
        this.logChannel.log(10000000, "CombiBAPListener#setMapPresentation( %1, %2, %3 )", bl, bl2, bl3);
        try {
            if (bl) {
                this.viewSizeManagerFPK.setViewSize(2, false);
            } else {
                this.viewSizeManagerFPK.setViewSize(1, false);
            }
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#setMapPresentation() - ERROR=%1 ", (Throwable)exception);
        }
        if (bl2) {
            // empty if block
        }
        if (bl3) {
            // empty if block
        }
        this.largeMapView = bl;
        this.leftSideMenuOpen = bl2;
        this.rightSideMenuOpen = bl3;
        this.updateMapPresentation();
    }

    public void validRGTypeReceived(boolean bl) {
        this.logChannel.log(10000000, "CombiBAPListener#validRGTypeReceived( %1 )", bl);
        this.validRGTypeReceived = bl;
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
        }
        catch (NullPointerException nullPointerException) {
            this.logChannel.log(100000, "CombiBAPListener#updateMapScale() - no combi service registered!");
        }
        catch (Exception exception) {
            this.logChannel.log(10000, "CombiBAPListener#updateMapScale() - ERROR=%1 ", (Throwable)exception);
        }
    }

    public void forceShowInitScreen(boolean bl) {
        this.logChannel.log(10000000, "CombiBAPListener#forceShowInitScreen( %1 )", bl);
        this.forceShowInitScreen = bl;
        this.updateInitScreen();
    }

    public void showSplashScreen(boolean bl) {
        this.logChannel.log(10000000, "CombiBAPListener#showSplashScreen() - show=%1 ", bl);
        if (bl) {
            if (this.infoStatesNavi != 5 && this.infoStatesGPS != 5) {
                this.infoStatesNaviBackup = this.infoStatesNavi;
                this.infoStatesGPSBackup = this.infoStatesGPS;
            }
            this.infoStatesNavi = 5;
            this.infoStatesGPS = 5;
        } else if (this.showSplashScreen != bl) {
            this.infoStatesNavi = this.infoStatesNaviBackup;
            this.infoStatesGPS = this.infoStatesGPSBackup;
        }
        this.logChannel.log(100000000, "CombiBAPListener#showSplashScreen() - infoStatesNavi:%1, infosStatesGPS:%2", (long)this.infoStatesNavi, (long)this.infoStatesGPS);
        this.showSplashScreen = bl;
        this.updateInfoStates();
    }

    void updateBapManeuverState(int n) {
        this.logChannel.log(100000000, "CombiBAPListener#updateBapManeuverState(%1)", (long)n);
        switch (n) {
            case 0: {
                this.maneuverState = 0;
                break;
            }
            case 1: {
                this.maneuverState = 1;
                break;
            }
            case 2: {
                this.maneuverState = 2;
                break;
            }
            case 3: {
                this.maneuverState = 3;
                break;
            }
            case 4: {
                this.maneuverState = 4;
                break;
            }
            default: {
                this.maneuverState = 0;
            }
        }
        this.updateManeuverState();
    }

    public void setOnlineNavigationState(boolean bl, int n, boolean bl2) {
        this.logChannel.log(10000000, "CombiBAPListener#setOnlineNavigationState() - geSelected: %1, progress: %3, dataConnectivityAvailable: %2", (Object)bl, (Object)bl2, (long)n);
        n = Math.max(n, 0);
        this.bufferProgress = n = Math.min(n, 100);
        this.onlineNavigationState = bl ? (n == 100 ? (bl2 ? 2 : 3) : 1) : 0;
        this.updateOnlineNavigationState();
    }

    private static boolean isMetricSystemInUse() {
        return Distance.getSystemUnit() == 1;
    }

    public void setManeuverDescriptor(BapManeuverDescriptor[] bapManeuverDescriptorArray, int n) {
        this.logChannel.log(10000000, "CombiBAPListener#setManeuverDescriptor() - exitViewId: %1", (long)n);
        this.exitviewID = n;
        this.applyManeuverDescriptor(bapManeuverDescriptorArray);
        this.maneuverDescriptorReceivedForCurrentRG = true;
        this.updateManeuverDescriptor();
        this.updateDistanceToNextManeuver();
        this.updateExitView();
    }

    public void setGALState(boolean bl) {
        this.logChannel.log(1000000, "CombiBAPListener#setGALState() - naviIsRunningOnSmartphone: %1", bl);
        this.naviIsRunningOnSmartphone = bl;
        this.updateInfoStates();
    }

    private void adaptActiveRGType(int n) {
        if (Util.isClusterMMI((IFrameworkAccess)this.env.getFramework())) {
            return;
        }
        this.logChannel.log(10000000, "CombiBAPListener#adaptActiveRGType() - infoStates: %1, rgType: %2", (long)n, (long)this.rgType);
        if (n == 6) {
            this.combiservice.updateActiveRGType(2);
        } else if (this.rgType != -1) {
            this.updateActiveRGType();
        }
    }

    protected int getNavigationPresetSGCoreModel() {
        return 4146;
    }

    protected boolean mainMapandKombiMapSetupInitialized() {
        if (Util.isClusterMapAvailable((IFrameworkAccess)this.env.getFramework())) {
            if (this.mapManager.getMapKombi().getSetup() != null) {
                return this.mapManager.getMapKombi().getSetup().isInitializationPhasefinished() && this.mapManager.getMapMain().getSetup().isInitializationPhasefinished();
            }
        } else {
            return this.mapManager.getMapMain().getSetup().isInitializationPhasefinished();
        }
        return false;
    }

    public void resetSupportedMapViews() {
        this.supportedMapViews = this.getSupportedMapViews();
        this.updateSupportedMapViews();
    }

    public void setHomeAddress(CombiBAPNaviDestination combiBAPNaviDestination) {
        this.homeAddress = combiBAPNaviDestination;
        this.updateHomeAddress();
    }

    public void updateLockingState(boolean bl) {
        if (Util.isLockFeatureNavMapAdvancedMapEnabled((NavigationEnv)this.env)) {
            this.resetSupportedMapViews();
        }
    }
}

