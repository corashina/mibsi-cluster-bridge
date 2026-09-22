/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.audi.atip.log.LogChannel
 *  de.audi.atip.timer.Timer
 *  de.audi.atip.timer.TimerListener
 *  de.audi.tghu.navi.app.map.dsi.AbstractRequester
 *  de.audi.tghu.navi.app.map.dsi.AbstractResponser
 *  de.audi.tghu.navi.app.map.dsi.MVResponseControl
 *  de.audi.tghu.navi.app.map.utils.MapUtils
 *  de.audi.tghu.navi.app.util.LocationFormatter
 *  org.dsi.ifc.base.DSIBase
 *  org.dsi.ifc.global.NavLocation
 *  org.dsi.ifc.global.NavLocationWgs84
 *  org.dsi.ifc.global.NavRectangle
 *  org.dsi.ifc.global.NavSegmentID
 *  org.dsi.ifc.map.DSIMapViewerControl
 *  org.dsi.ifc.map.MapFlag
 *  org.dsi.ifc.map.MapOverlay
 *  org.dsi.ifc.map.PoiListElement
 *  org.dsi.ifc.map.Point
 *  org.dsi.ifc.map.Rect
 */
package de.audi.tghu.navi.app.map.dsi;

import com.mibsi.carplay.core.OemClusterMapRenderGate;
import de.audi.atip.log.LogChannel;
import de.audi.atip.timer.Timer;
import de.audi.atip.timer.TimerListener;
import de.audi.tghu.navi.app.map.dsi.AbstractRequester;
import de.audi.tghu.navi.app.map.dsi.AbstractResponser;
import de.audi.tghu.navi.app.map.dsi.MVResponseControl;
import de.audi.tghu.navi.app.map.utils.MapUtils;
import de.audi.tghu.navi.app.util.LocationFormatter;
import java.util.Arrays;
import org.dsi.ifc.base.DSIBase;
import org.dsi.ifc.global.NavLocation;
import org.dsi.ifc.global.NavLocationWgs84;
import org.dsi.ifc.global.NavRectangle;
import org.dsi.ifc.global.NavSegmentID;
import org.dsi.ifc.map.DSIMapViewerControl;
import org.dsi.ifc.map.MapFlag;
import org.dsi.ifc.map.MapOverlay;
import org.dsi.ifc.map.PoiListElement;
import org.dsi.ifc.map.Point;
import org.dsi.ifc.map.Rect;

public class MVRequestControl
extends AbstractRequester
implements DSIMapViewerControl {
    private DSIMapViewerControl mDSICtrl;
    private int mActiveScrollDirection = -1;
    private int mViewType = -1;
    private int mOrientation = -1;
    private boolean mQueueGetInfoForPosition = false;
    private Point mQueueGetInfoForScreenPositionPoint = new Point(-1, -1);
    private boolean mGetInfoForPositionActive = false;
    private boolean mConfigureFlagsActive = false;
    private Rect mPendingScreenViewport = null;
    private Rect mQueuedScreenViewport = null;
    private Point mHotPointPosition = new Point(-1, -1);
    private Point mCarPosition = new Point(-1, -1);
    private int mShowTMC = -1;
    private int mEnableSoftZoom = -1;
    private float mZoomLevel = -1.0f;
    private int mZoomIndex = -1;
    private int roadClass = -1;
    private int mMapMode = 255;
    private Timer getInfoForPositionTimer;

    public MVRequestControl(int n, LogChannel logChannel, LogChannel logChannel2) {
        super(n, logChannel, "MVRequestControl");
        MVResponseControl mVResponseControl = new MVResponseControl(n, logChannel2);
        this.setResponser((AbstractResponser)mVResponseControl);
        this.getInfoForPositionTimer = new Timer("GetInfoForPosition", 500L, true, (TimerListener)new /* Unavailable Anonymous Inner Class!! */);
    }

    private void restartGetInfoForPositionTimer() {
        this.getLogger().log(100000, "MVRequestControl#restartGetInfoForPositionTimer()");
        this.getInfoForPositionTimer.restart();
    }

    public void cancelGetInfoForPositionTimer() {
        this.getLogger().log(100000, "MVRequestControl#cancelGetInfoForPositionTimer()");
        this.getInfoForPositionTimer.cancel();
    }

    protected void cleanup() {
        super.cleanup();
    }

    public MVResponseControl getMVResponseControl() {
        return (MVResponseControl)this.getResponser();
    }

    public void bind(DSIMapViewerControl dSIMapViewerControl) {
        if (dSIMapViewerControl == null) {
            this.cleanupResponserAttributeNotifications();
        }
        this.mDSICtrl = dSIMapViewerControl;
        super.bind((DSIBase)dSIMapViewerControl);
    }

    protected DSIMapViewerControl getDSICtrl() {
        return this.mDSICtrl;
    }

    protected final boolean isDSICtrlAvailable() {
        return this.mDSICtrl != null;
    }

    protected final DSIBase getDSIBase() {
        return this.mDSICtrl;
    }

    protected int getLogLevelForException() {
        return this.isReady() && this.isOperable() ? 10000 : 100000;
    }

    protected void setZoomLevelMember(float f) {
        if (Math.abs(f - this.mZoomLevel) > 0.001f) {
            this.mZoomLevel = -1.0f;
        }
    }

    protected void setZoomIndexMember(int n) {
        if (n != this.mZoomIndex) {
            this.mZoomIndex = -1;
        }
    }

    public void viewSetScreenViewport(Rect rect) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#viewSetScreenViewport() - DSICtrl not available!");
            return;
        }
        if (!this.isViewSetScreenViewportNeeded(rect)) {
            this.getLogger().log(10000000, "MVRequestControl#viewSetScreenViewport(%1) - Ignore request! (Requested viewport already active, pending or queued)", (Object)rect);
            return;
        }
        if (this.isViewSetScreenViewportActive()) {
            this.getLogger().log(10000000, "MVRequestControl#viewSetScreenViewport(%1) - Queue request! (Pending request active)", (Object)rect);
            this.setQueuedScreenViewport(rect);
            return;
        }
        this.setPendingScreenViewport(rect);
        try {
            this.getLogger().log(10000000, "MVRequestControl#viewSetScreenViewport(%1)", (Object)rect);
            this.getDSICtrl().viewSetScreenViewport(rect);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#viewSetScreenViewport() - ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void viewSetScreenViewportMaximum(Rect rect) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#viewSetScreenViewportMaximum() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#viewSetScreenViewportMaximum(%1)", (Object)rect);
            this.getDSICtrl().viewSetScreenViewportMaximum(rect);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#viewSetScreenViewportMaximum(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean isViewSetScreenViewportNeeded(Rect rect) {
        MVRequestControl mVRequestControl = this;
        synchronized (mVRequestControl) {
            Rect rect2 = this.getMap().getMVResponseControl().mViewScreenViewPort;
            Rect rect3 = this.getPendingScreenViewport();
            Rect rect4 = this.getQueuedScreenViewport();
            this.getLogger().log(1000000, "MVRequestControl#isViewSetScreenViewportNeeded() - screenViewPort: %1, currentViewPort: %2, pendingViewPort: %3, queuedViewPort: %4", (Object)rect, (Object)rect2, (Object)rect3, (Object)rect4);
            if (MapUtils.isRectEqual((Rect)rect, (Rect)rect2) && rect3 == null && rect4 == null) {
                return false;
            }
            if (MapUtils.isRectEqual((Rect)rect, (Rect)rect3) && rect4 == null) {
                return false;
            }
            if (MapUtils.isRectEqual((Rect)rect, (Rect)rect4)) {
                return false;
            }
            if (MapUtils.isRectEqual((Rect)rect, (Rect)rect3)) {
                this.setQueuedScreenViewport(null);
                return false;
            }
            return true;
        }
    }

    public boolean isViewSetScreenViewportActive() {
        return this.getPendingScreenViewport() != null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Rect getPendingScreenViewport() {
        MVRequestControl mVRequestControl = this;
        synchronized (mVRequestControl) {
            return this.mPendingScreenViewport;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setPendingScreenViewport(Rect rect) {
        MVRequestControl mVRequestControl = this;
        synchronized (mVRequestControl) {
            this.mPendingScreenViewport = rect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Rect getQueuedScreenViewport() {
        MVRequestControl mVRequestControl = this;
        synchronized (mVRequestControl) {
            return this.mQueuedScreenViewport;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setQueuedScreenViewport(Rect rect) {
        MVRequestControl mVRequestControl = this;
        synchronized (mVRequestControl) {
            this.mQueuedScreenViewport = rect;
        }
    }

    public void viewSetVisible(boolean bl) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#viewSetVisible() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#viewSetVisible(%1)", bl);
            if (!bl && Boolean.getBoolean("AlwaysRenderMap")) {
                this.getLogger().log(1000000, "MVRequestControl#viewSetVisible() - setting map invisible is disabled by environment parameter!");
            } else {
                this.getActiveContext().setRequestedVisibility(bl);
                this.getDSICtrl().viewSetVisible(OemClusterMapRenderGate.filter((Object)this, bl));
            }
        }
        catch (Exception exception) {
            this.getLogger().log(this.getLogLevelForException(), "MVRequestControl#viewSetVisible(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void viewFreeze(boolean bl) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#viewFreeze() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#viewFreeze(%1)", bl);
            this.getActiveContext().setRequestedFreeze(bl);
            this.getDSICtrl().viewFreeze(bl);
        }
        catch (Exception exception) {
            this.getLogger().log(this.getLogLevelForException(), "MVRequestControl#viewFreeze(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setDayView() {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setDayView() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setDayView()");
            this.getDSICtrl().setDayView();
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setDayView(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setNightView() {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setNightView() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setNightView()");
            this.getDSICtrl().setNightView();
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setNightView(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setViewType(int n) {
        if (this.mViewType != n) {
            if (!this.isDSICtrlAvailable()) {
                this.getLogger().log(10000, "MVRequestControl#setViewType() - DSICtrl not available!");
                return;
            }
            try {
                this.getLogger().log(10000000, "MVRequestControl#setViewType(%1)", (long)n);
                this.getDSICtrl().setViewType(n);
                this.mViewType = n;
            }
            catch (Exception exception) {
                this.getLogger().log(10000, "MVRequestControl#setViewType(): ERROR = %1", (Object)exception.getMessage());
            }
        }
    }

    public void setMode(int n) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setMode() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setMode(%1)", (long)n);
            this.mMapMode = n;
            this.getDSICtrl().setMode(n);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setMode(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public int getMode() {
        return this.mMapMode;
    }

    public void setMapPosition(NavLocationWgs84 navLocationWgs84) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setMapPosition() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setMapPosition(%1)", (Object)navLocationWgs84);
            this.getDSICtrl().setMapPosition(navLocationWgs84);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setMapPosition(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setMapViewPort(NavLocationWgs84 navLocationWgs84, short s, int n) {
        try {
            this.getLogger().log(10000000, "MVRequestControl#setMapViewPort(%1, %2, %3)", (Object)navLocationWgs84, (long)s, (long)n);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setMapViewPort(): ERROR = %1", (Throwable)exception);
        }
    }

    public void setZoomListIndex(int n) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setZoomListIndex() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setZoomListIndex( %1 ) - last requested: %2 ", (long)n, (long)this.mZoomIndex);
            if (n > -1 && n != this.mZoomIndex) {
                this.getDSICtrl().setZoomLevel(-1.0f, n);
                this.mZoomIndex = n;
                this.mZoomLevel = -1.0f;
            }
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setZoomListIndex(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setZoomLevel(float f) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setZoomLevel() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setZoomLevel( %1 ) - last requested: %2 ", (Object)Float.toString(f), (Object)Float.toString(this.mZoomLevel));
            if (f > -1.0f && Math.abs(f - this.mZoomLevel) > 0.001f) {
                this.getDSICtrl().setZoomLevel(f, -1);
                this.mZoomLevel = f;
                this.mZoomIndex = -1;
            }
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setZoomLevel(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setRotation(short s) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setRotation() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setRotation(%1)", (long)s);
            this.getDSICtrl().setRotation(s);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setRotation(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setOrientation(int n) {
        if (this.mOrientation != n) {
            if (!this.isDSICtrlAvailable()) {
                this.getLogger().log(10000, "MVRequestControl#setOrientation() - DSICtrl not available!");
                return;
            }
            try {
                this.getLogger().log(10000000, "MVRequestControl#setOrientation(%1, new Point())", (long)n);
                this.getDSICtrl().setOrientation(n, new Point());
                this.mOrientation = n;
            }
            catch (Exception exception) {
                this.getLogger().log(10000, "MVRequestControl#setOrientation(): ERROR = %1", (Object)exception.getMessage());
            }
        }
    }

    public void setMapViewPortByLD(NavLocation navLocation, NavLocation navLocation2, int n) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setMapViewPortByLD() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setMapViewPortByLD( %1, %2, %3 )", (Object)LocationFormatter.formatLocationShort((NavLocation)navLocation), (Object)LocationFormatter.formatLocationShort((NavLocation)navLocation2), (long)n);
            this.getDSICtrl().setMapViewPortByLD(navLocation, navLocation2, n);
            this.mZoomLevel = -1.0f;
            this.mZoomIndex = -1;
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setMapViewPortByLD(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setLocationByLocation(NavLocation navLocation) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setLocationByLocation() - DSICtrl not available!");
            return;
        }
        try {
            if (this.getLogger().isDebug()) {
                int n = 0;
                int n2 = 0;
                if (navLocation != null) {
                    n = navLocation.longitude;
                    n2 = navLocation.latitude;
                }
                this.getLogger().log(10000000, "MVRequestControl#setLocationByLocation( longitude=%1, latitude=%2, formatted=%3)", (Object)new Integer(n), (Object)new Integer(n2), (Object)LocationFormatter.formatLocationShort((NavLocation)navLocation));
            }
            this.getDSICtrl().setLocationByLocation(navLocation);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setLocationByLocation(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setLocationByLocationAndView(NavLocation navLocation, short s, int n) {
        try {
            this.getLogger().log(10000000, "MVRequestControl#setLocationByLocationAndView(%1)", (Object)LocationFormatter.formatLocationShort((NavLocation)navLocation));
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setLocationByLocationAndView(): ERROR = %1", (Throwable)exception);
        }
    }

    public void setCarPosition(Point point) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setCarPosition() - DSICtrl not available!");
            return;
        }
        try {
            if (this.mCarPosition.getXPos() == point.getXPos() && this.mCarPosition.getYPos() == point.getYPos()) {
                return;
            }
            this.getLogger().log(10000000, "MVRequestControl#setCarPosition(%1)", (Object)point);
            this.getDSICtrl().setCarPosition(point);
            this.mCarPosition = point;
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setCarPosition(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setHotPoint(Point point) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setHotPoint() - DSICtrl not available!");
            return;
        }
        if (this.mHotPointPosition.getXPos() == point.getXPos() && this.mHotPointPosition.getYPos() == point.getYPos()) {
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setHotPoint(%1)", (Object)point);
            this.getDSICtrl().setHotPoint(point);
            this.mHotPointPosition = point;
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setHotPoint(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public Point getHotPointPosition() {
        return this.mHotPointPosition;
    }

    public void setZoomArea(Rect rect) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setZoomArea() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setZoomArea(%1)", (Object)rect);
            this.getDSICtrl().setZoomArea(rect);
            this.mZoomLevel = -1.0f;
            this.mZoomIndex = -1;
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setZoomArea(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setTrafficMapStyle(boolean bl) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setTrafficMapStyle() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setTrafficMapStyle( %1 )", bl);
            this.getDSICtrl().setTrafficMapStyle(bl);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setTrafficMapStyle(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void showSpeedAndFlowCongestions(boolean bl) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#showSpeedAndFlowCongestions() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#showSpeedAndFlowCongestions(%1)", bl);
            this.getDSICtrl().showSpeedAndFlowCongestions(bl);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#showSpeedAndFlowCongestions(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setCityModelMode(int n) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setCityModelMode() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setCityModelMode(%1)", (long)n);
            this.getDSICtrl().setCityModelMode(n);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setCityModelMode(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setLandmarksVisible(boolean bl) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setLandmarksVisible() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setLandmarksVisible(%1)", bl);
            this.getDSICtrl().set3DLandmarksVisible(bl);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setLandmarksVisible(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setBrandIconStyle(int[] nArray, int n) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setBrandIconStyle() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setBrandIconStyle( %1 , %2 )", (Object)nArray, (long)n);
            this.getDSICtrl().setBrandIconStyle(nArray, n);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setBrandIconStyle(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void displayRemainingRangeOfVehicle(boolean bl) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#displayRemainingRangeOfVehicle() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#displayRemainingRangeOfVehicle(%1)", bl);
            this.getDSICtrl().displayRemainingRangeOfVehicle(bl);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#displayRemainingRangeOfVehicle(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setMobilityHorizonZoomMode(int n) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setMobilityHorizonZoomMode() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setMobilityHorizonZoomMode(%1)", (long)n);
            this.getDSICtrl().setMobilityHorizonZoomMode(n);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setMobilityHorizonZoomMode(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setMobilityHorizonVisibility(boolean bl) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setMobilityHorizonVisibility() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setMobilityHorizonVisibility(%1)", bl);
            this.getDSICtrl().setMobilityHorizonVisibility(bl);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setMobilityHorizonVisibility(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setCrossHairsColor(boolean bl) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setCrossHairsColor() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setCrossHairsColor(%1)", bl);
            this.getDSICtrl().setCrossHairsColor(bl ? 2 : 1);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setCrossHairsColor(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void suspendMapViewer() {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#suspendMapViewer() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#suspendMapViewer()");
            this.getDSICtrl().suspendMapViewer();
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#suspendMapViewer(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void wakeupMapViewer() {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#wakeupMapViewer() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#wakeupMapViewer()");
            this.getDSICtrl().wakeupMapViewer();
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#wakeupMapViewer(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void isDetailedMapMaterialAvailable(NavLocationWgs84 navLocationWgs84) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#isDetailedMapMaterialAvailable() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#isDetailedMapMaterialAvailable()");
            this.getDSICtrl().isDetailedMapMaterialAvailable(navLocationWgs84);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#isDetailedMapMaterialAvailable(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setViewPortBorder(int n) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setViewPortBorder() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setViewPortBorder( %1 )", (long)n);
            this.getDSICtrl().setViewPortBorder(n);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setViewPortBorder(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void showTMCMessages(boolean bl) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#showTMCMessages() - DSICtrl not available!");
            return;
        }
        try {
            int n;
            int n2 = n = bl ? 1 : 0;
            if (n == this.mShowTMC) {
                return;
            }
            this.getLogger().log(10000000, "MVRequestControl#showTMCMessages(%1)", bl);
            this.getDSICtrl().showTMCMessages(bl);
            this.mShowTMC = n;
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#showTMCMessages(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void goToTMCMessage(long l) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#goToTMCMessage() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#goToTMCMessage(%1)", l);
            this.getDSICtrl().goToTMCMessage(l);
            this.mZoomLevel = -1.0f;
            this.mZoomIndex = -1;
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#goToTMCMessage(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void ensureTMCVisibility(long l) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#ensureTMCVisibility() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#ensureTMCVisibility() - messageID %1", l);
            this.getDSICtrl().ensureTMCVisibility(l);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#ensureTMCVisibility(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void ensureTrafficEventIconsVisibility(long[] lArray) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#ensureTrafficEventIconsVisibility() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#ensureTrafficEventIconsVisibility() - number of messageIDs: %1", lArray == null ? 0L : (long)lArray.length);
            this.getDSICtrl().ensureTrafficEventIconsVisibility(lArray);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#ensureTrafficEventIconsVisibility(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setEnableSoftZoom(boolean bl) {
        int n;
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setEnableSoftZoom() - DSICtrl not available!");
            return;
        }
        boolean bl2 = Boolean.getBoolean("disableSoftZoom");
        boolean bl3 = Boolean.getBoolean("enableSoftZoomConditional");
        if (bl3) {
            return;
        }
        if (bl2) {
            bl = false;
        }
        int n2 = n = bl ? 1 : 0;
        if (this.mEnableSoftZoom != n) {
            try {
                this.getLogger().log(10000000, "MVRequestControl#setEnableSoftZoom( %1, disableSoftZoom-flag: %3 )", bl, bl2);
                this.getDSICtrl().setEnableSoftZoom(bl);
                this.mEnableSoftZoom = n;
            }
            catch (Exception exception) {
                this.getLogger().log(this.getLogLevelForException(), "MVRequestControl#setEnableSoftZoom(): ERROR = %1", (Object)exception.getMessage());
            }
        }
    }

    public void setEnableSoftZoomConditional(boolean bl) {
        int n;
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setEnableSoftZoomConditional() - DSICtrl not available!");
            return;
        }
        boolean bl2 = Boolean.getBoolean("disableSoftZoom");
        boolean bl3 = Boolean.getBoolean("enableSoftZoomConditional");
        if (!bl3) {
            return;
        }
        if (bl2) {
            bl = false;
        }
        int n2 = n = bl ? 1 : 0;
        if (this.mEnableSoftZoom != n) {
            try {
                this.getLogger().log(10000000, "MVRequestControl#setEnableSoftZoomConditional( %1, disableSoftZoom-flag: %3 )", bl, bl2);
                this.getDSICtrl().setEnableSoftZoom(bl);
                this.mEnableSoftZoom = n;
            }
            catch (Exception exception) {
                this.getLogger().log(this.getLogLevelForException(), "MVRequestControl#setEnableSoftZoomConditional(): ERROR = %1", (Object)exception.getMessage());
            }
        }
    }

    public void setEnableSoftRotation(boolean bl) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setEnableSoftRotation() - DSICtrl not available!");
            return;
        }
        try {
            if (Boolean.getBoolean("enableSoftRotation")) {
                this.getLogger().log(10000000, "MVRequestControl#setEnableSoftRotation(%1)", bl);
                this.getDSICtrl().setEnableSoftRotation(bl);
            }
        }
        catch (Exception exception) {
            this.getLogger().log(this.getLogLevelForException(), "MVRequestControl#setEnableSoftRotation(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setEnableSoftJump(boolean bl) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setEnableSoftJump() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setEnableSoftJump(%1)", bl);
            this.getDSICtrl().setEnableSoftJump(bl);
        }
        catch (Exception exception) {
            this.getLogger().log(this.getLogLevelForException(), "MVRequestControl#setEnableSoftJump(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setEnableSoftTilt(boolean bl) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setEnableSoftTilt() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setEnableSoftTilt(%1)", bl);
            this.getDSICtrl().setEnableSoftTilt(bl);
        }
        catch (Exception exception) {
            this.getLogger().log(this.getLogLevelForException(), "MVRequestControl#setEnableSoftTilt(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setScrollByCrossHairs(boolean bl) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setScrollByCrossHairs() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setScrollByCrossHairs(%1)", bl);
            this.getDSICtrl().setScrollByCrossHairs(bl);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setScrollByCrossHairs(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setScrollByCrossHairsBoundingBox(Rect rect) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setScrollByCrossHairsBoundingBox() - DSICtrl not available!");
            return;
        }
        try {
            if (rect == null) {
                this.getLogger().log(10000000, "MVRequestControl#setScrollByCrossHairsBoundingBox(null)");
            } else {
                this.getLogger().log(10000000, "MVRequestControl#setScrollByCrossHairsBoundingBox(x/y: %1/%2)", (long)rect.getKordX(), (long)rect.getKordY());
                this.getLogger().log(10000000, "MVRequestControl#setScrollByCrossHairsBoundingBox(w/h: %1/%2)", (long)rect.getDiffX(), (long)rect.getDiffY());
            }
            this.getDSICtrl().setScrollByCrossHairsBoundingBox(rect);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setScrollByCrossHairsBoundingBox(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void rbSelectNextSegment() {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#rbSelectNextSegment() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#rbSelectNextSegment()");
            this.getDSICtrl().rbSelectNextSegment();
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#rbSelectNextSegment(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void rbSelectPreviousSegment() {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#rbSelectPreviousSegment() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#rbSelectPreviousSegment()");
            this.getDSICtrl().rbSelectPreviousSegment();
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#rbSelectPreviousSegment(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void rbGetIDOfSelectedSegment() {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#rbGetIDOfSelectedSegment() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#rbGetIDOfSelectedSegment()");
            this.getDSICtrl().rbGetIDOfSelectedSegment();
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#rbGetIDOfSelectedSegment(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void rbGetRRDToSelectedSegment(long l) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#rbGetRRDToSelectedSegment() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#rbGetRRDToSelectedSegment(%1)", l);
            this.getDSICtrl().rbGetRRDToSelectedSegment(l);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#rbGetRRDToSelectedSegment(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void rbSelectAlternativeRoute(int n) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#rbSelectAlternativeRoute() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#rbSelectAlternativeRoute(%1)", (long)n);
            this.getDSICtrl().rbSelectAlternativeRoute(n);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#rbSelectAlternativeRoute(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void rbSetPosition(int n) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#rbSetPosition() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#rbSetPosition(%1)", (long)n);
            this.getDSICtrl().rbSetPosition(n);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#rbSetPosition(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void highlightRouteBasedOnLength(long l, long l2, int n) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#highlightRouteBasedOnLength() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#highlightRouteBasedOnLength(%1, %2, %3)", l, l2, (long)n);
            this.getDSICtrl().highlightRouteBasedOnLength(l, l2, n);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#highlightRouteBasedOnLength(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void dragMap(short s, short s2) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#dragMap() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#dragMap(%1, %2)", (long)s, (long)s2);
            this.getDSICtrl().dragMap(s, s2);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#dragMap(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void dragRoute(short s, short s2) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#dragRoute() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#dragRoute(%1, %2)", (long)s, (long)s2);
            this.getDSICtrl().dragRoute(s, s2);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#dragRoute(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void scrollToDirection(short s, int n, short s2) {
        try {
            this.getLogger().log(100000000, "MVRequestControl#scrollToDirection(%1, %2, %3)", (long)s, (long)n, (long)s2);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#scrollToDirection(): ERROR = %1", (Throwable)exception);
        }
    }

    public void startRouteDragging(NavLocationWgs84 navLocationWgs84) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#startRouteDragging() - DSICtrl not available!");
            return;
        }
        this.getLogger().log(10000000, "MVRequestControl#startRouteDragging( long=%1, lat=%2 )", (long)navLocationWgs84.getLongitude(), (long)navLocationWgs84.getLatitude());
        try {
            this.getDSICtrl().startRouteDragging(navLocationWgs84);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#startRouteDragging(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void startScrollToDirection(int n) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#startScrollToDirection() - DSICtrl not available!");
            return;
        }
        this.getLogger().log(10000000, "MVRequestControl#startScrollToDirection(%1) - mActiveScrollDirection: %2", (long)n, (long)this.mActiveScrollDirection);
        if (this.mActiveScrollDirection == n) {
            return;
        }
        try {
            this.getDSICtrl().startScrollToDirection(n);
            this.mActiveScrollDirection = n;
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#startScrollToDirection(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void stopScrollToDirection() {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#stopScrollToDirection() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#stopScrollToDirection()");
            this.getDSICtrl().stopScrollToDirection();
            this.mActiveScrollDirection = -1;
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#stopScrollToDirection(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void getInfoForPosition() {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#getInfoForPosition() - DSICtrl not available!");
            return;
        }
        if (this.getInfoForPositionIsActive() || this.configureFlagsIsActive()) {
            this.getLogger().log(100000, "MVRequestControl#getInfoForPosition() - getInfoForPositionIsActive() = %1,  configureFlagsIsActive() = %2", this.getInfoForPositionIsActive(), this.configureFlagsIsActive());
            this.mQueueGetInfoForPosition = true;
            return;
        }
        this.setInfoForPositionIsActive(true);
        try {
            this.getLogger().log(10000000, "MVRequestControl#getInfoForPosition()");
            this.getDSICtrl().getInfoForPosition();
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#getInfoForPosition(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void getInfoForScreenPosition(Point point) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#getInfoForScreenPosition() - DSICtrl not available!");
            return;
        }
        if (this.getInfoForPositionIsActive() || this.configureFlagsIsActive()) {
            this.getLogger().log(100000, "MVRequestControl#getInfoForScreenPosition() - getInfoForPosition() = %1,  configureFlags() = %2", this.getInfoForPositionIsActive(), this.configureFlagsIsActive());
            this.mQueueGetInfoForPosition = true;
            this.mQueueGetInfoForScreenPositionPoint = point;
            this.restartGetInfoForPositionTimer();
            return;
        }
        this.setInfoForPositionIsActive(true);
        try {
            this.getLogger().log(10000000, "MVRequestControl#getInfoForScreenPosition(%1)", (Object)point);
            this.getDSICtrl().getInfoForScreenPosition(point);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#getInfoForScreenPosition(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean getInfoForPositionIsActive() {
        MVRequestControl mVRequestControl = this;
        synchronized (mVRequestControl) {
            return this.mGetInfoForPositionActive;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setInfoForPositionIsActive(boolean bl) {
        MVRequestControl mVRequestControl = this;
        synchronized (mVRequestControl) {
            this.mGetInfoForPositionActive = bl;
            if (this.mGetInfoForPositionActive) {
                this.restartGetInfoForPositionTimer();
            } else {
                this.cancelGetInfoForPositionTimer();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean getQueueGetInfoForPosition() {
        MVRequestControl mVRequestControl = this;
        synchronized (mVRequestControl) {
            boolean bl = this.mQueueGetInfoForPosition;
            this.mQueueGetInfoForPosition = false;
            return bl;
        }
    }

    public void ensurePoiVisibility(NavLocation[] navLocationArray) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#ensurePoiVisibility() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#ensurePoiVisibility() - locations: %1", navLocationArray == null ? 0L : (long)navLocationArray.length);
            this.getDSICtrl().ensurePoiVisibility(navLocationArray);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#ensurePoiVisibility(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setGeneralPoiVisibility(boolean bl) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setGeneralPoiVisibility() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setGeneralPoiVisibility( %1 )", bl);
            this.getDSICtrl().setGeneralPoiVisibility(bl);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setGeneralPoiVisibility(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void selectNextPOI() {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#selectNextPOI() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#selectNextPOI()");
            this.getDSICtrl().selectNextPOI();
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#selectNextPOI(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void selectPrevPOI() {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#selectPrevPOI() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#selectPrevPOI()");
            this.getDSICtrl().selectPrevPOI();
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#selectPrevPOI(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void configureFlags(int n, MapFlag[] mapFlagArray) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#configureFlags() - DSICtrl not available!");
            return;
        }
        this.configureFlagsIsActive(true);
        try {
            if (n == 1) {
                this.getLogger().log(10000000, "MVRequestControl#configureFlags() - clear all flags (command: %1)", (long)n);
            } else {
                this.getLogger().log(10000000, "MVRequestControl#configureFlags( %1, length = %2 )", (long)n, (long)mapFlagArray.length);
                for (int i = 0; i < mapFlagArray.length; ++i) {
                    this.getLogger().log(10000000, "MVRequestControl#configureFlags() -  mapFlag[%2]=%1", (Object)mapFlagArray[i], (long)i);
                }
            }
            this.getDSICtrl().configureFlags(n, mapFlagArray);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#configureFlags(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean configureFlagsIsActive(boolean bl) {
        MVRequestControl mVRequestControl = this;
        synchronized (mVRequestControl) {
            boolean bl2 = this.mConfigureFlagsActive;
            this.mConfigureFlagsActive = bl;
            return bl2;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean configureFlagsIsActive() {
        MVRequestControl mVRequestControl = this;
        synchronized (mVRequestControl) {
            return this.mConfigureFlagsActive;
        }
    }

    public void setPictureNavigationIconVisibility(boolean bl, int n) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setPictureNavigationIconVisibility() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setPictureNavigationIconVisibility( %1, %2 )", bl, (long)n);
            this.getDSICtrl().setPictureNavigationIconVisibility(bl, n);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setPictureNavigationIconVisibility(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setWeatherVisualization(boolean bl) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setWeatherVisualization() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setWeatherVisualization( %1 )", bl);
            this.getDSICtrl().setWeatherVisualization(bl);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setWeatherVisualization(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setEnableRouteCalcMode(boolean bl) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setEnableRouteCalcMode() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setEnableRouteCalcMode(%1)", bl);
            this.getDSICtrl().setEnableRouteCalcMode(bl);
        }
        catch (Exception exception) {
            this.getLogger().log(100000, "MVRequestControl#setEnableRouteCalcMode(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setMetricSystem(int n) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setMetricSystem() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setMetricSystem(%1)", (long)n);
            this.getDSICtrl().setMetricSystem(n);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setMetricSystem(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void resetMemberVariables() {
        this.getLogger().log(10000000, "MVRequestControl#resetMemberVariables()");
        this.mActiveScrollDirection = -1;
        this.mViewType = -1;
        this.mOrientation = -1;
        this.mQueueGetInfoForPosition = false;
        this.mPendingScreenViewport = null;
        this.mQueuedScreenViewport = null;
        this.mHotPointPosition = new Point(-1, -1);
        this.mCarPosition = new Point(-1, -1);
        this.mShowTMC = -1;
        this.mEnableSoftZoom = -1;
        this.mZoomIndex = -1;
        this.mZoomLevel = -1.0f;
        MVRequestControl mVRequestControl = this;
        synchronized (mVRequestControl) {
            this.mGetInfoForPositionActive = false;
            this.mConfigureFlagsActive = false;
        }
        this.mMapMode = 255;
    }

    public void setDragRouteMarker(int n) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setDragRouteMarker() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setDragRouteMarker( %1 )", (long)n);
            this.getDSICtrl().setDragRouteMarker(n);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setDragRouteMarker(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setMapViewPortByWGS84Rectangle(NavRectangle navRectangle, int n) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setMapViewPortByWGS84Rectangle() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setMapViewPortByWGS84Rectangle( %1, %2 )", (Object)navRectangle, (long)n);
            this.getDSICtrl().setMapViewPortByWGS84Rectangle(navRectangle, n);
            this.mZoomLevel = -1.0f;
            this.mZoomIndex = -1;
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setMapViewPortByWGS84Rectangle(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void ehSetCategoryVisibility(int n, int[] nArray, boolean[] blArray) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#ehSetCategoryVisibility() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#ehSetCategoryVisibility( mapStyleType = %1 )", (long)n);
            if (nArray != null && nArray.length > 0 && blArray != null && blArray.length > 0) {
                this.getLogger().log(10000000, "MVRequestControl#ehSetCategoryVisibility( categoryUid = %1 and visibility = %2 )", (Object)MapUtils.toString((int[])nArray), (Object)MapUtils.toString((boolean[])blArray));
            }
            this.getDSICtrl().ehSetCategoryVisibility(n, nArray, blArray);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#ehSetCategoryVisibility(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void ehSetCategoryVisibilityToDefault(int n) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#ehSetCategoryVisibilityToDefault() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#ehSetCategoryVisibilityToDefault( mapStyleType = %1 )", (long)n);
            this.getDSICtrl().ehSetCategoryVisibilityToDefault(n);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#ehSetCategoryVisibilityToDefault(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setHorizonMarkerVisibility(boolean bl) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setHorizonMarkerVisibility() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setHorizonMarkerVisibility( mapStyleType = %1 )", bl);
            this.getDSICtrl().setHorizonMarkerVisibility(bl);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setHorizonMarkerVisibility(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void getNumberOfPOIs() {
        try {
            this.getLogger().log(10000000, "MVRequestControl#getNumberOfPOIs( )");
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#getNumberOfPOIs(): ERROR = %1", (Throwable)exception);
        }
    }

    public void packPOIContainer() {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#packPOIContainer() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#packPOIContainer()");
            this.getDSICtrl().packPOIContainer();
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#packPOIContainer(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void set3DLandmarksVisible(boolean bl) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#set3DLandmarksVisible() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#set3DLandmarksVisible( %1 )", bl);
            this.getDSICtrl().set3DLandmarksVisible(bl);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#set3DLandmarksVisible(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setLocation(int n, short s) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setLocation() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setLocation( %1, %2 )", (long)n, (long)s);
            this.getDSICtrl().setLocation(n, s);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setLocation(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setOrientation(int n, Point point) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setOrientation() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setOrientation( %2, %1 )", (Object)point, (long)n);
            this.getDSICtrl().setOrientation(n, point);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setOrientation(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setZoomLevel(float f, int n) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setZoomLevel() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setZoomLevel( %1, %2 )", (Object)Float.toString(f), (long)n);
            this.getLogger().log(10000000, "MVRequestControl#setZoomLevel() - last requested zoomLevel: %1, last requested zoomIndex: %2 ", (Object)Float.toString(this.mZoomLevel), (long)this.mZoomIndex);
            if (n != this.mZoomIndex || Math.abs(f - this.mZoomLevel) > 0.001f) {
                this.getDSICtrl().setZoomLevel(f, n);
                this.mZoomIndex = n;
                this.mZoomLevel = f;
            }
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setZoomLevel(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setCountryOverviewCountry(String string) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setCountryOverviewCountry() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setCountryOverviewCountry( %1 )", (Object)string);
            this.getDSICtrl().setCountryOverviewCountry(string);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setCountryOverviewCountry(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void unpackPOIContainer(long l) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#unpackPOIContainer() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#unpackPOIContainer( %1 )", l);
            this.getDSICtrl().unpackPOIContainer(l);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#unpackPOIContainer(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setViewFocusOnBlock(long[] lArray) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setViewFocusOnBlock() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setViewFocusOnBlock( %1 )", (Object)lArray);
            this.getDSICtrl().setViewFocusOnBlock(lArray);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setViewFocusOnBlock(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setViewFocusOnPoi(PoiListElement[] poiListElementArray) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setViewFocusOnPoi() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setViewFocusOnPoi( %1 )", (Object)poiListElementArray);
            this.getDSICtrl().setViewFocusOnPoi(poiListElementArray);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setViewFocusOnPoi(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void startToDrawNewRectangleInMap() {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#startToDrawNewRectangleInMap() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#startToDrawNewRectangleInMap()");
            this.getDSICtrl().startToDrawNewRectangleInMap();
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#startToDrawNewRectangleInMap(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void editRectangleInMap(long l) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#editRectangleInMap() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#editRectangleInMap( %1 )", l);
            this.getDSICtrl().editRectangleInMap(l);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#editRectangleInMap(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setSouthWestCornerOfRectangleInMap(Point point) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setSouthWestCornerOfRectangleInMap() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setSouthWestCornerOfRectangleInMap( %1 )", (Object)point);
            this.getDSICtrl().setSouthWestCornerOfRectangleInMap(point);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setSouthWestCornerOfRectangleInMap(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setNorthEastCornerOfRectangleInMap(Point point) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setNorthEastCornerOfRectangleInMap() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setNorthEastCornerOfRectangleInMap( %1 )", (Object)point);
            this.getDSICtrl().setNorthEastCornerOfRectangleInMap(point);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setNorthEastCornerOfRectangleInMap(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void finishDrawRectangleInMap() {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#finishDrawRectangleInMap() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#finishDrawRectangleInMap()");
            this.getDSICtrl().finishDrawRectangleInMap();
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#finishDrawRectangleInMap(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void touchApproach(boolean bl) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#touchApproach() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#touchApproach( %1 )", bl);
            this.getDSICtrl().touchApproach(bl);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void startScrollByVector(int n, int n2) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#startScrollByVector() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#startScrollByVector( %1, %2 )", (long)n, (long)n2);
            this.getDSICtrl().startScrollByVector(n, n2);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#startScrollByVector(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setGuidanceSymbol(int n) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setGuidanceSymbol() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setGuidanceSymbol( %1 )", (long)n);
            this.getDSICtrl().setGuidanceSymbol(n);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setGuidanceSymbol(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setHOVLaneVisibility(boolean bl) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setHOVLaneVisibility() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setHOVLaneVisibility( %1 )", bl);
            this.getDSICtrl().setHOVLaneVisibility(bl);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setHOVLaneVisibility(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setTollRoadHighLighting(boolean bl) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setTollRoadHighLighting() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setTollRoadHighLighting( %1 )", bl);
            this.getDSICtrl().setTollRoadHighLighting(bl);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setTollRoadHighLighting(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setMountainPeakMarker(boolean bl) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setMountainPeakMarker() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setMountainPeakMarker( %1 )", bl);
            this.getDSICtrl().setMountainPeakMarker(bl);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setMountainPeakMarker(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setViewFocusOnCombinedRouteListElements(long[] lArray) {
        try {
            this.getLogger().log(10000000, "MVRequestControl#setViewFocusOnCombinedRouteListElements( %1 )", (Object)lArray);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setViewFocusOnCombinedRouteListElements(): ERROR = %1", (Throwable)exception);
        }
    }

    public void setFrameRateMode(int n) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setFrameRateMode() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setFrameRateMode( %1 )", (long)n);
            this.getDSICtrl().setFrameRateMode(n);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setFrameRateMode(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setRouteColoringPolicy(int n) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setRouteColoringPolicy() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setRouteColoringPolicy( %1 )", (long)n);
            this.getDSICtrl().setRouteColoringPolicy(n);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setRouteColoringPolicy(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void showSpeedAndFlowFreeFlow(boolean bl) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#showSpeedAndFlowFreeFlow() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#showSpeedAndFlowFreeFlow( %1 )", bl);
            this.getDSICtrl().showSpeedAndFlowFreeFlow(bl);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#showSpeedAndFlowFreeFlow(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void showRichContent(boolean bl) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#showRichContent() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#showRichContent( %1 )", bl);
            this.getDSICtrl().showRichContent(bl);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#showRichContent(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setCrossHairsColor(int n) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setCrossHairsColor() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setCrossHairsColor( %1 )", (long)n);
            this.getDSICtrl().setCrossHairsColor(n);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setCrossHairsColor(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setTerrainElevation(boolean bl) {
        try {
            this.getLogger().log(10000000, "MVRequestControl#setTerrainElevation( %1 )", bl);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setTerrainElevation(): ERROR = %1", (Throwable)exception);
        }
    }

    public void setMapOverlays(int n, MapOverlay[] mapOverlayArray, int n2, int n3) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setMapOverlays() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setMapOverlays( %1 )", (long)n);
            this.getDSICtrl().setMapOverlays(n, mapOverlayArray, n2, n3);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setMapOverlays(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setMapLayerVisible(int[] nArray) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setMapLayerVisible() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setMapLayerVisible( %1 )", (Object)nArray);
            this.getDSICtrl().setMapLayerVisible(nArray);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setMapLayerVisible(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setTemperatureScale(int n) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setTemperatureScale() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setTemperatureScale( %1 )", (long)n);
            this.getDSICtrl().setTemperatureScale(n);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setTemperatureScale(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setSoftAnimationSpeed(int n) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setSoftAnimationSpeed() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setSoftAnimationSpeed( %1 )", (long)n);
            this.getDSICtrl().setSoftAnimationSpeed(n);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setSoftAnimationSpeed(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setSpeedAndFlowRoadClass(int n) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setSpeedAndFlowRoadClass() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setSpeedAndFlowRoadClass, new roadClass is:( %1 ), old roadClass is:( %2 )", (long)n, (long)this.roadClass);
            if (this.roadClass != n) {
                this.roadClass = n;
                this.getDSICtrl().setSpeedAndFlowRoadClass(n);
            }
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setSpeedAndFlowRoadClass(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setRouteVisibility(boolean bl) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setRouteVisibility() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setRouteVisibility( %1 )", bl);
            this.getDSICtrl().setRouteVisibility(bl);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setRouteVisibility(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setVisibleRoutes(NavSegmentID[] navSegmentIDArray) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setVisibleRoutes() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setVisibleRoutes( %1 )", Arrays.asList(navSegmentIDArray));
            this.getDSICtrl().setVisibleRoutes(navSegmentIDArray);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setVisibleRoutes(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void showTrafficEventListView(long[] lArray, boolean bl, boolean bl2) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#showTrafficEventListView() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#showTrafficEventListView( adjustViewPort: %1, showPins: %2)", bl, bl2);
            this.getDSICtrl().showTrafficEventListView(lArray, bl, bl2);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#showTrafficEventListView(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public int getRequestedOrientation() {
        return this.mOrientation;
    }

    public float getRequestedZoomLevel() {
        return this.mZoomLevel;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Point getQueueGetInfoForScreenPositionPoint() {
        MVRequestControl mVRequestControl = this;
        synchronized (mVRequestControl) {
            Point point = this.mQueueGetInfoForScreenPositionPoint;
            this.mQueueGetInfoForScreenPositionPoint = new Point(-1, -1);
            return point;
        }
    }

    public void setCopyrightPosition(NavRectangle navRectangle, int n, int n2) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setCopyrightPosition() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setCopyrightPosition(Rectangle: %1, textAlignment %2, verticalAlignment %3)", (Object)navRectangle, (long)n, (long)n2);
            this.getDSICtrl().setCopyrightPosition(navRectangle, n, n2);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#setCopyrightPosition(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    public void setMapStyle(int n) {
        if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setMapStyle() - DSICtrl not available!");
            return;
        }
        try {
            this.getLogger().log(10000000, "MVRequestControl#setMapStyle(mapStlye: %1)", (long)n);
            this.getDSICtrl().setMapStyle(n);
        }
        catch (Exception exception) {
            this.getLogger().log(10000, "MVRequestControl#showTrafficEventListView(): ERROR = %1", (Object)exception.getMessage());
        }
    }

    static /* synthetic */ boolean access$002(MVRequestControl mVRequestControl, boolean bl) {
        mVRequestControl.mGetInfoForPositionActive = bl;
        return mVRequestControl.mGetInfoForPositionActive;
    }

    public boolean mibsiSetRenderVisible(boolean bl) {
        if (!this.isDSICtrlAvailable()) {
            return false;
        }
        this.getDSICtrl().viewSetVisible(bl);
        return true;
    }
}

