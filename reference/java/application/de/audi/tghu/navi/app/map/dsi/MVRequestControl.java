package de.audi.tghu.navi.app.map.dsi;

import com.mibsi.carplay.core.OemClusterMapRenderGate;
import de.audi.atip.log.LogChannel;
import de.audi.atip.timer.Timer;
import de.audi.tghu.navi.app.map.dsi.MVRequestControl.1;
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

public class MVRequestControl extends AbstractRequester implements DSIMapViewerControl {
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
   private float mZoomLevel = -1.0F;
   private int mZoomIndex = -1;
   private int roadClass = -1;
   private int mMapMode = 255;
   private Timer getInfoForPositionTimer;

   public MVRequestControl(int var1, LogChannel var2, LogChannel var3) {
      super(var1, var2, "MVRequestControl");
      MVResponseControl var4 = new MVResponseControl(var1, var3);
      this.setResponser(var4);
      this.getInfoForPositionTimer = new Timer("GetInfoForPosition", 500L, true, new 1(this));
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

   public void bind(DSIMapViewerControl var1) {
      if (var1 == null) {
         this.cleanupResponserAttributeNotifications();
      }

      this.mDSICtrl = var1;
      super.bind(var1);
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

   protected void setZoomLevelMember(float var1) {
      if (Math.abs(var1 - this.mZoomLevel) > 0.001F) {
         this.mZoomLevel = -1.0F;
      }
   }

   protected void setZoomIndexMember(int var1) {
      if (var1 != this.mZoomIndex) {
         this.mZoomIndex = -1;
      }
   }

   public void viewSetScreenViewport(Rect var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#viewSetScreenViewport() - DSICtrl not available!");
      } else if (!this.isViewSetScreenViewportNeeded(var1)) {
         this.getLogger()
            .log(10000000, "MVRequestControl#viewSetScreenViewport(%1) - Ignore request! (Requested viewport already active, pending or queued)", var1);
      } else if (this.isViewSetScreenViewportActive()) {
         this.getLogger().log(10000000, "MVRequestControl#viewSetScreenViewport(%1) - Queue request! (Pending request active)", var1);
         this.setQueuedScreenViewport(var1);
      } else {
         this.setPendingScreenViewport(var1);

         try {
            this.getLogger().log(10000000, "MVRequestControl#viewSetScreenViewport(%1)", var1);
            this.getDSICtrl().viewSetScreenViewport(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#viewSetScreenViewport() - ERROR = %1", var3.getMessage());
         }
      }
   }

   public void viewSetScreenViewportMaximum(Rect var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#viewSetScreenViewportMaximum() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#viewSetScreenViewportMaximum(%1)", var1);
            this.getDSICtrl().viewSetScreenViewportMaximum(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#viewSetScreenViewportMaximum(): ERROR = %1", var3.getMessage());
         }
      }
   }

   private boolean isViewSetScreenViewportNeeded(Rect var1) {
      synchronized (this) {
         Rect var3 = this.getMap().getMVResponseControl().mViewScreenViewPort;
         Rect var4 = this.getPendingScreenViewport();
         Rect var5 = this.getQueuedScreenViewport();
         this.getLogger()
            .log(
               1000000,
               "MVRequestControl#isViewSetScreenViewportNeeded() - screenViewPort: %1, currentViewPort: %2, pendingViewPort: %3, queuedViewPort: %4",
               var1,
               var3,
               var4,
               var5
            );
         if (MapUtils.isRectEqual(var1, var3) && var4 == null && var5 == null) {
            return false;
         } else if (MapUtils.isRectEqual(var1, var4) && var5 == null) {
            return false;
         } else if (MapUtils.isRectEqual(var1, var5)) {
            return false;
         } else if (MapUtils.isRectEqual(var1, var4)) {
            this.setQueuedScreenViewport(null);
            return false;
         } else {
            return true;
         }
      }
   }

   public boolean isViewSetScreenViewportActive() {
      return this.getPendingScreenViewport() != null;
   }

   public Rect getPendingScreenViewport() {
      synchronized (this) {
         return this.mPendingScreenViewport;
      }
   }

   public void setPendingScreenViewport(Rect var1) {
      synchronized (this) {
         this.mPendingScreenViewport = var1;
      }
   }

   public Rect getQueuedScreenViewport() {
      synchronized (this) {
         return this.mQueuedScreenViewport;
      }
   }

   public void setQueuedScreenViewport(Rect var1) {
      synchronized (this) {
         this.mQueuedScreenViewport = var1;
      }
   }

   public void viewSetVisible(boolean var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#viewSetVisible() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#viewSetVisible(%1)", var1);
            if (!var1 && Boolean.getBoolean("AlwaysRenderMap")) {
               this.getLogger().log(1000000, "MVRequestControl#viewSetVisible() - setting map invisible is disabled by environment parameter!");
            } else {
               this.getActiveContext().setRequestedVisibility(var1);
               this.getDSICtrl().viewSetVisible(OemClusterMapRenderGate.filter(this, var1));
            }
         } catch (Exception var3) {
            this.getLogger().log(this.getLogLevelForException(), "MVRequestControl#viewSetVisible(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void viewFreeze(boolean var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#viewFreeze() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#viewFreeze(%1)", var1);
            this.getActiveContext().setRequestedFreeze(var1);
            this.getDSICtrl().viewFreeze(var1);
         } catch (Exception var3) {
            this.getLogger().log(this.getLogLevelForException(), "MVRequestControl#viewFreeze(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setDayView() {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setDayView() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setDayView()");
            this.getDSICtrl().setDayView();
         } catch (Exception var2) {
            this.getLogger().log(10000, "MVRequestControl#setDayView(): ERROR = %1", var2.getMessage());
         }
      }
   }

   public void setNightView() {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setNightView() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setNightView()");
            this.getDSICtrl().setNightView();
         } catch (Exception var2) {
            this.getLogger().log(10000, "MVRequestControl#setNightView(): ERROR = %1", var2.getMessage());
         }
      }
   }

   public void setViewType(int var1) {
      if (this.mViewType != var1) {
         if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setViewType() - DSICtrl not available!");
            return;
         }

         try {
            this.getLogger().log(10000000, "MVRequestControl#setViewType(%1)", var1);
            this.getDSICtrl().setViewType(var1);
            this.mViewType = var1;
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setViewType(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setMode(int var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setMode() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setMode(%1)", var1);
            this.mMapMode = var1;
            this.getDSICtrl().setMode(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setMode(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public int getMode() {
      return this.mMapMode;
   }

   public void setMapPosition(NavLocationWgs84 var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setMapPosition() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setMapPosition(%1)", var1);
            this.getDSICtrl().setMapPosition(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setMapPosition(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setMapViewPort(NavLocationWgs84 var1, short var2, int var3) {
      try {
         this.getLogger().log(10000000, "MVRequestControl#setMapViewPort(%1, %2, %3)", var1, var2, var3);
      } catch (Exception var5) {
         this.getLogger().log(10000, "MVRequestControl#setMapViewPort(): ERROR = %1", var5);
      }
   }

   public void setZoomListIndex(int var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setZoomListIndex() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setZoomListIndex( %1 ) - last requested: %2 ", var1, this.mZoomIndex);
            if (var1 > -1 && var1 != this.mZoomIndex) {
               this.getDSICtrl().setZoomLevel(-1.0F, var1);
               this.mZoomIndex = var1;
               this.mZoomLevel = -1.0F;
            }
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setZoomListIndex(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setZoomLevel(float var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setZoomLevel() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setZoomLevel( %1 ) - last requested: %2 ", Float.toString(var1), Float.toString(this.mZoomLevel));
            if (var1 > -1.0F && Math.abs(var1 - this.mZoomLevel) > 0.001F) {
               this.getDSICtrl().setZoomLevel(var1, -1);
               this.mZoomLevel = var1;
               this.mZoomIndex = -1;
            }
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setZoomLevel(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setRotation(short var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setRotation() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setRotation(%1)", var1);
            this.getDSICtrl().setRotation(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setRotation(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setOrientation(int var1) {
      if (this.mOrientation != var1) {
         if (!this.isDSICtrlAvailable()) {
            this.getLogger().log(10000, "MVRequestControl#setOrientation() - DSICtrl not available!");
            return;
         }

         try {
            this.getLogger().log(10000000, "MVRequestControl#setOrientation(%1, new Point())", var1);
            this.getDSICtrl().setOrientation(var1, new Point());
            this.mOrientation = var1;
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setOrientation(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setMapViewPortByLD(NavLocation var1, NavLocation var2, int var3) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setMapViewPortByLD() - DSICtrl not available!");
      } else {
         try {
            this.getLogger()
               .log(
                  10000000,
                  "MVRequestControl#setMapViewPortByLD( %1, %2, %3 )",
                  LocationFormatter.formatLocationShort(var1),
                  LocationFormatter.formatLocationShort(var2),
                  var3
               );
            this.getDSICtrl().setMapViewPortByLD(var1, var2, var3);
            this.mZoomLevel = -1.0F;
            this.mZoomIndex = -1;
         } catch (Exception var5) {
            this.getLogger().log(10000, "MVRequestControl#setMapViewPortByLD(): ERROR = %1", var5.getMessage());
         }
      }
   }

   public void setLocationByLocation(NavLocation var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setLocationByLocation() - DSICtrl not available!");
      } else {
         try {
            if (this.getLogger().isDebug()) {
               int var2 = 0;
               int var3 = 0;
               if (var1 != null) {
                  var2 = var1.longitude;
                  var3 = var1.latitude;
               }

               this.getLogger()
                  .log(
                     10000000,
                     "MVRequestControl#setLocationByLocation( longitude=%1, latitude=%2, formatted=%3)",
                     new Integer(var2),
                     new Integer(var3),
                     LocationFormatter.formatLocationShort(var1)
                  );
            }

            this.getDSICtrl().setLocationByLocation(var1);
         } catch (Exception var4) {
            this.getLogger().log(10000, "MVRequestControl#setLocationByLocation(): ERROR = %1", var4.getMessage());
         }
      }
   }

   public void setLocationByLocationAndView(NavLocation var1, short var2, int var3) {
      try {
         this.getLogger().log(10000000, "MVRequestControl#setLocationByLocationAndView(%1)", LocationFormatter.formatLocationShort(var1));
      } catch (Exception var5) {
         this.getLogger().log(10000, "MVRequestControl#setLocationByLocationAndView(): ERROR = %1", var5);
      }
   }

   public void setCarPosition(Point var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setCarPosition() - DSICtrl not available!");
      } else {
         try {
            if (this.mCarPosition.getXPos() == var1.getXPos() && this.mCarPosition.getYPos() == var1.getYPos()) {
               return;
            }

            this.getLogger().log(10000000, "MVRequestControl#setCarPosition(%1)", var1);
            this.getDSICtrl().setCarPosition(var1);
            this.mCarPosition = var1;
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setCarPosition(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setHotPoint(Point var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setHotPoint() - DSICtrl not available!");
      } else if (this.mHotPointPosition.getXPos() != var1.getXPos() || this.mHotPointPosition.getYPos() != var1.getYPos()) {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setHotPoint(%1)", var1);
            this.getDSICtrl().setHotPoint(var1);
            this.mHotPointPosition = var1;
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setHotPoint(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public Point getHotPointPosition() {
      return this.mHotPointPosition;
   }

   public void setZoomArea(Rect var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setZoomArea() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setZoomArea(%1)", var1);
            this.getDSICtrl().setZoomArea(var1);
            this.mZoomLevel = -1.0F;
            this.mZoomIndex = -1;
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setZoomArea(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setTrafficMapStyle(boolean var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setTrafficMapStyle() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setTrafficMapStyle( %1 )", var1);
            this.getDSICtrl().setTrafficMapStyle(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setTrafficMapStyle(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void showSpeedAndFlowCongestions(boolean var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#showSpeedAndFlowCongestions() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#showSpeedAndFlowCongestions(%1)", var1);
            this.getDSICtrl().showSpeedAndFlowCongestions(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#showSpeedAndFlowCongestions(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setCityModelMode(int var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setCityModelMode() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setCityModelMode(%1)", var1);
            this.getDSICtrl().setCityModelMode(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setCityModelMode(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setLandmarksVisible(boolean var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setLandmarksVisible() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setLandmarksVisible(%1)", var1);
            this.getDSICtrl().set3DLandmarksVisible(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setLandmarksVisible(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setBrandIconStyle(int[] var1, int var2) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setBrandIconStyle() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setBrandIconStyle( %1 , %2 )", var1, var2);
            this.getDSICtrl().setBrandIconStyle(var1, var2);
         } catch (Exception var4) {
            this.getLogger().log(10000, "MVRequestControl#setBrandIconStyle(): ERROR = %1", var4.getMessage());
         }
      }
   }

   public void displayRemainingRangeOfVehicle(boolean var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#displayRemainingRangeOfVehicle() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#displayRemainingRangeOfVehicle(%1)", var1);
            this.getDSICtrl().displayRemainingRangeOfVehicle(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#displayRemainingRangeOfVehicle(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setMobilityHorizonZoomMode(int var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setMobilityHorizonZoomMode() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setMobilityHorizonZoomMode(%1)", var1);
            this.getDSICtrl().setMobilityHorizonZoomMode(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setMobilityHorizonZoomMode(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setMobilityHorizonVisibility(boolean var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setMobilityHorizonVisibility() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setMobilityHorizonVisibility(%1)", var1);
            this.getDSICtrl().setMobilityHorizonVisibility(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setMobilityHorizonVisibility(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setCrossHairsColor(boolean var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setCrossHairsColor() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setCrossHairsColor(%1)", var1);
            this.getDSICtrl().setCrossHairsColor(var1 ? 2 : 1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setCrossHairsColor(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void suspendMapViewer() {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#suspendMapViewer() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#suspendMapViewer()");
            this.getDSICtrl().suspendMapViewer();
         } catch (Exception var2) {
            this.getLogger().log(10000, "MVRequestControl#suspendMapViewer(): ERROR = %1", var2.getMessage());
         }
      }
   }

   public void wakeupMapViewer() {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#wakeupMapViewer() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#wakeupMapViewer()");
            this.getDSICtrl().wakeupMapViewer();
         } catch (Exception var2) {
            this.getLogger().log(10000, "MVRequestControl#wakeupMapViewer(): ERROR = %1", var2.getMessage());
         }
      }
   }

   public void isDetailedMapMaterialAvailable(NavLocationWgs84 var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#isDetailedMapMaterialAvailable() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#isDetailedMapMaterialAvailable()");
            this.getDSICtrl().isDetailedMapMaterialAvailable(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#isDetailedMapMaterialAvailable(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setViewPortBorder(int var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setViewPortBorder() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setViewPortBorder( %1 )", var1);
            this.getDSICtrl().setViewPortBorder(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setViewPortBorder(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void showTMCMessages(boolean var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#showTMCMessages() - DSICtrl not available!");
      } else {
         try {
            int var2 = var1 ? 1 : 0;
            if (var2 == this.mShowTMC) {
               return;
            }

            this.getLogger().log(10000000, "MVRequestControl#showTMCMessages(%1)", var1);
            this.getDSICtrl().showTMCMessages(var1);
            this.mShowTMC = var2;
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#showTMCMessages(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void goToTMCMessage(long var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#goToTMCMessage() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#goToTMCMessage(%1)", var1);
            this.getDSICtrl().goToTMCMessage(var1);
            this.mZoomLevel = -1.0F;
            this.mZoomIndex = -1;
         } catch (Exception var4) {
            this.getLogger().log(10000, "MVRequestControl#goToTMCMessage(): ERROR = %1", var4.getMessage());
         }
      }
   }

   public void ensureTMCVisibility(long var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#ensureTMCVisibility() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#ensureTMCVisibility() - messageID %1", var1);
            this.getDSICtrl().ensureTMCVisibility(var1);
         } catch (Exception var4) {
            this.getLogger().log(10000, "MVRequestControl#ensureTMCVisibility(): ERROR = %1", var4.getMessage());
         }
      }
   }

   public void ensureTrafficEventIconsVisibility(long[] var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#ensureTrafficEventIconsVisibility() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#ensureTrafficEventIconsVisibility() - number of messageIDs: %1", var1 == null ? 0L : var1.length);
            this.getDSICtrl().ensureTrafficEventIconsVisibility(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#ensureTrafficEventIconsVisibility(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setEnableSoftZoom(boolean var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setEnableSoftZoom() - DSICtrl not available!");
      } else {
         boolean var2 = Boolean.getBoolean("disableSoftZoom");
         boolean var3 = Boolean.getBoolean("enableSoftZoomConditional");
         if (!var3) {
            if (var2) {
               var1 = false;
            }

            int var4 = var1 ? 1 : 0;
            if (this.mEnableSoftZoom != var4) {
               try {
                  this.getLogger().log(10000000, "MVRequestControl#setEnableSoftZoom( %1, disableSoftZoom-flag: %3 )", var1, var2);
                  this.getDSICtrl().setEnableSoftZoom(var1);
                  this.mEnableSoftZoom = var4;
               } catch (Exception var6) {
                  this.getLogger().log(this.getLogLevelForException(), "MVRequestControl#setEnableSoftZoom(): ERROR = %1", var6.getMessage());
               }
            }
         }
      }
   }

   public void setEnableSoftZoomConditional(boolean var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setEnableSoftZoomConditional() - DSICtrl not available!");
      } else {
         boolean var2 = Boolean.getBoolean("disableSoftZoom");
         boolean var3 = Boolean.getBoolean("enableSoftZoomConditional");
         if (var3) {
            if (var2) {
               var1 = false;
            }

            int var4 = var1 ? 1 : 0;
            if (this.mEnableSoftZoom != var4) {
               try {
                  this.getLogger().log(10000000, "MVRequestControl#setEnableSoftZoomConditional( %1, disableSoftZoom-flag: %3 )", var1, var2);
                  this.getDSICtrl().setEnableSoftZoom(var1);
                  this.mEnableSoftZoom = var4;
               } catch (Exception var6) {
                  this.getLogger().log(this.getLogLevelForException(), "MVRequestControl#setEnableSoftZoomConditional(): ERROR = %1", var6.getMessage());
               }
            }
         }
      }
   }

   public void setEnableSoftRotation(boolean var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setEnableSoftRotation() - DSICtrl not available!");
      } else {
         try {
            if (Boolean.getBoolean("enableSoftRotation")) {
               this.getLogger().log(10000000, "MVRequestControl#setEnableSoftRotation(%1)", var1);
               this.getDSICtrl().setEnableSoftRotation(var1);
            }
         } catch (Exception var3) {
            this.getLogger().log(this.getLogLevelForException(), "MVRequestControl#setEnableSoftRotation(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setEnableSoftJump(boolean var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setEnableSoftJump() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setEnableSoftJump(%1)", var1);
            this.getDSICtrl().setEnableSoftJump(var1);
         } catch (Exception var3) {
            this.getLogger().log(this.getLogLevelForException(), "MVRequestControl#setEnableSoftJump(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setEnableSoftTilt(boolean var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setEnableSoftTilt() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setEnableSoftTilt(%1)", var1);
            this.getDSICtrl().setEnableSoftTilt(var1);
         } catch (Exception var3) {
            this.getLogger().log(this.getLogLevelForException(), "MVRequestControl#setEnableSoftTilt(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setScrollByCrossHairs(boolean var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setScrollByCrossHairs() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setScrollByCrossHairs(%1)", var1);
            this.getDSICtrl().setScrollByCrossHairs(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setScrollByCrossHairs(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setScrollByCrossHairsBoundingBox(Rect var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setScrollByCrossHairsBoundingBox() - DSICtrl not available!");
      } else {
         try {
            if (var1 == null) {
               this.getLogger().log(10000000, "MVRequestControl#setScrollByCrossHairsBoundingBox(null)");
            } else {
               this.getLogger().log(10000000, "MVRequestControl#setScrollByCrossHairsBoundingBox(x/y: %1/%2)", var1.getKordX(), var1.getKordY());
               this.getLogger().log(10000000, "MVRequestControl#setScrollByCrossHairsBoundingBox(w/h: %1/%2)", var1.getDiffX(), var1.getDiffY());
            }

            this.getDSICtrl().setScrollByCrossHairsBoundingBox(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setScrollByCrossHairsBoundingBox(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void rbSelectNextSegment() {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#rbSelectNextSegment() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#rbSelectNextSegment()");
            this.getDSICtrl().rbSelectNextSegment();
         } catch (Exception var2) {
            this.getLogger().log(10000, "MVRequestControl#rbSelectNextSegment(): ERROR = %1", var2.getMessage());
         }
      }
   }

   public void rbSelectPreviousSegment() {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#rbSelectPreviousSegment() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#rbSelectPreviousSegment()");
            this.getDSICtrl().rbSelectPreviousSegment();
         } catch (Exception var2) {
            this.getLogger().log(10000, "MVRequestControl#rbSelectPreviousSegment(): ERROR = %1", var2.getMessage());
         }
      }
   }

   public void rbGetIDOfSelectedSegment() {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#rbGetIDOfSelectedSegment() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#rbGetIDOfSelectedSegment()");
            this.getDSICtrl().rbGetIDOfSelectedSegment();
         } catch (Exception var2) {
            this.getLogger().log(10000, "MVRequestControl#rbGetIDOfSelectedSegment(): ERROR = %1", var2.getMessage());
         }
      }
   }

   public void rbGetRRDToSelectedSegment(long var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#rbGetRRDToSelectedSegment() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#rbGetRRDToSelectedSegment(%1)", var1);
            this.getDSICtrl().rbGetRRDToSelectedSegment(var1);
         } catch (Exception var4) {
            this.getLogger().log(10000, "MVRequestControl#rbGetRRDToSelectedSegment(): ERROR = %1", var4.getMessage());
         }
      }
   }

   public void rbSelectAlternativeRoute(int var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#rbSelectAlternativeRoute() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#rbSelectAlternativeRoute(%1)", var1);
            this.getDSICtrl().rbSelectAlternativeRoute(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#rbSelectAlternativeRoute(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void rbSetPosition(int var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#rbSetPosition() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#rbSetPosition(%1)", var1);
            this.getDSICtrl().rbSetPosition(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#rbSetPosition(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void highlightRouteBasedOnLength(long var1, long var3, int var5) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#highlightRouteBasedOnLength() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#highlightRouteBasedOnLength(%1, %2, %3)", var1, var3, var5);
            this.getDSICtrl().highlightRouteBasedOnLength(var1, var3, var5);
         } catch (Exception var7) {
            this.getLogger().log(10000, "MVRequestControl#highlightRouteBasedOnLength(): ERROR = %1", var7.getMessage());
         }
      }
   }

   public void dragMap(short var1, short var2) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#dragMap() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#dragMap(%1, %2)", var1, var2);
            this.getDSICtrl().dragMap(var1, var2);
         } catch (Exception var4) {
            this.getLogger().log(10000, "MVRequestControl#dragMap(): ERROR = %1", var4.getMessage());
         }
      }
   }

   public void dragRoute(short var1, short var2) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#dragRoute() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#dragRoute(%1, %2)", var1, var2);
            this.getDSICtrl().dragRoute(var1, var2);
         } catch (Exception var4) {
            this.getLogger().log(10000, "MVRequestControl#dragRoute(): ERROR = %1", var4.getMessage());
         }
      }
   }

   public void scrollToDirection(short var1, int var2, short var3) {
      try {
         this.getLogger().log(100000000, "MVRequestControl#scrollToDirection(%1, %2, %3)", var1, var2, var3);
      } catch (Exception var5) {
         this.getLogger().log(10000, "MVRequestControl#scrollToDirection(): ERROR = %1", var5);
      }
   }

   public void startRouteDragging(NavLocationWgs84 var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#startRouteDragging() - DSICtrl not available!");
      } else {
         this.getLogger().log(10000000, "MVRequestControl#startRouteDragging( long=%1, lat=%2 )", var1.getLongitude(), var1.getLatitude());

         try {
            this.getDSICtrl().startRouteDragging(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#startRouteDragging(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void startScrollToDirection(int var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#startScrollToDirection() - DSICtrl not available!");
      } else {
         this.getLogger().log(10000000, "MVRequestControl#startScrollToDirection(%1) - mActiveScrollDirection: %2", var1, this.mActiveScrollDirection);
         if (this.mActiveScrollDirection != var1) {
            try {
               this.getDSICtrl().startScrollToDirection(var1);
               this.mActiveScrollDirection = var1;
            } catch (Exception var3) {
               this.getLogger().log(10000, "MVRequestControl#startScrollToDirection(): ERROR = %1", var3.getMessage());
            }
         }
      }
   }

   public void stopScrollToDirection() {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#stopScrollToDirection() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#stopScrollToDirection()");
            this.getDSICtrl().stopScrollToDirection();
            this.mActiveScrollDirection = -1;
         } catch (Exception var2) {
            this.getLogger().log(10000, "MVRequestControl#stopScrollToDirection(): ERROR = %1", var2.getMessage());
         }
      }
   }

   public void getInfoForPosition() {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#getInfoForPosition() - DSICtrl not available!");
      } else if (!this.getInfoForPositionIsActive() && !this.configureFlagsIsActive()) {
         this.setInfoForPositionIsActive(true);

         try {
            this.getLogger().log(10000000, "MVRequestControl#getInfoForPosition()");
            this.getDSICtrl().getInfoForPosition();
         } catch (Exception var2) {
            this.getLogger().log(10000, "MVRequestControl#getInfoForPosition(): ERROR = %1", var2.getMessage());
         }
      } else {
         this.getLogger()
            .log(
               100000,
               "MVRequestControl#getInfoForPosition() - getInfoForPositionIsActive() = %1,  configureFlagsIsActive() = %2",
               this.getInfoForPositionIsActive(),
               this.configureFlagsIsActive()
            );
         this.mQueueGetInfoForPosition = true;
      }
   }

   public void getInfoForScreenPosition(Point var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#getInfoForScreenPosition() - DSICtrl not available!");
      } else if (!this.getInfoForPositionIsActive() && !this.configureFlagsIsActive()) {
         this.setInfoForPositionIsActive(true);

         try {
            this.getLogger().log(10000000, "MVRequestControl#getInfoForScreenPosition(%1)", var1);
            this.getDSICtrl().getInfoForScreenPosition(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#getInfoForScreenPosition(): ERROR = %1", var3.getMessage());
         }
      } else {
         this.getLogger()
            .log(
               100000,
               "MVRequestControl#getInfoForScreenPosition() - getInfoForPosition() = %1,  configureFlags() = %2",
               this.getInfoForPositionIsActive(),
               this.configureFlagsIsActive()
            );
         this.mQueueGetInfoForPosition = true;
         this.mQueueGetInfoForScreenPositionPoint = var1;
         this.restartGetInfoForPositionTimer();
      }
   }

   public boolean getInfoForPositionIsActive() {
      synchronized (this) {
         return this.mGetInfoForPositionActive;
      }
   }

   public void setInfoForPositionIsActive(boolean var1) {
      synchronized (this) {
         this.mGetInfoForPositionActive = var1;
         if (this.mGetInfoForPositionActive) {
            this.restartGetInfoForPositionTimer();
         } else {
            this.cancelGetInfoForPositionTimer();
         }
      }
   }

   public boolean getQueueGetInfoForPosition() {
      synchronized (this) {
         boolean var2 = this.mQueueGetInfoForPosition;
         this.mQueueGetInfoForPosition = false;
         return var2;
      }
   }

   public void ensurePoiVisibility(NavLocation[] var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#ensurePoiVisibility() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#ensurePoiVisibility() - locations: %1", var1 == null ? 0L : var1.length);
            this.getDSICtrl().ensurePoiVisibility(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#ensurePoiVisibility(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setGeneralPoiVisibility(boolean var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setGeneralPoiVisibility() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setGeneralPoiVisibility( %1 )", var1);
            this.getDSICtrl().setGeneralPoiVisibility(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setGeneralPoiVisibility(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void selectNextPOI() {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#selectNextPOI() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#selectNextPOI()");
            this.getDSICtrl().selectNextPOI();
         } catch (Exception var2) {
            this.getLogger().log(10000, "MVRequestControl#selectNextPOI(): ERROR = %1", var2.getMessage());
         }
      }
   }

   public void selectPrevPOI() {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#selectPrevPOI() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#selectPrevPOI()");
            this.getDSICtrl().selectPrevPOI();
         } catch (Exception var2) {
            this.getLogger().log(10000, "MVRequestControl#selectPrevPOI(): ERROR = %1", var2.getMessage());
         }
      }
   }

   public void configureFlags(int var1, MapFlag[] var2) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#configureFlags() - DSICtrl not available!");
      } else {
         this.configureFlagsIsActive(true);

         try {
            if (var1 == 1) {
               this.getLogger().log(10000000, "MVRequestControl#configureFlags() - clear all flags (command: %1)", var1);
            } else {
               this.getLogger().log(10000000, "MVRequestControl#configureFlags( %1, length = %2 )", var1, var2.length);

               for (int var3 = 0; var3 < var2.length; var3++) {
                  this.getLogger().log(10000000, "MVRequestControl#configureFlags() -  mapFlag[%2]=%1", var2[var3], var3);
               }
            }

            this.getDSICtrl().configureFlags(var1, var2);
         } catch (Exception var4) {
            this.getLogger().log(10000, "MVRequestControl#configureFlags(): ERROR = %1", var4.getMessage());
         }
      }
   }

   public boolean configureFlagsIsActive(boolean var1) {
      synchronized (this) {
         boolean var3 = this.mConfigureFlagsActive;
         this.mConfigureFlagsActive = var1;
         return var3;
      }
   }

   private boolean configureFlagsIsActive() {
      synchronized (this) {
         return this.mConfigureFlagsActive;
      }
   }

   public void setPictureNavigationIconVisibility(boolean var1, int var2) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setPictureNavigationIconVisibility() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setPictureNavigationIconVisibility( %1, %2 )", var1, var2);
            this.getDSICtrl().setPictureNavigationIconVisibility(var1, var2);
         } catch (Exception var4) {
            this.getLogger().log(10000, "MVRequestControl#setPictureNavigationIconVisibility(): ERROR = %1", var4.getMessage());
         }
      }
   }

   public void setWeatherVisualization(boolean var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setWeatherVisualization() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setWeatherVisualization( %1 )", var1);
            this.getDSICtrl().setWeatherVisualization(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setWeatherVisualization(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setEnableRouteCalcMode(boolean var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setEnableRouteCalcMode() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setEnableRouteCalcMode(%1)", var1);
            this.getDSICtrl().setEnableRouteCalcMode(var1);
         } catch (Exception var3) {
            this.getLogger().log(100000, "MVRequestControl#setEnableRouteCalcMode(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setMetricSystem(int var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setMetricSystem() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setMetricSystem(%1)", var1);
            this.getDSICtrl().setMetricSystem(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setMetricSystem(): ERROR = %1", var3.getMessage());
         }
      }
   }

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
      this.mZoomLevel = -1.0F;
      synchronized (this) {
         this.mGetInfoForPositionActive = false;
         this.mConfigureFlagsActive = false;
      }

      this.mMapMode = 255;
   }

   public void setDragRouteMarker(int var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setDragRouteMarker() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setDragRouteMarker( %1 )", var1);
            this.getDSICtrl().setDragRouteMarker(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setDragRouteMarker(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setMapViewPortByWGS84Rectangle(NavRectangle var1, int var2) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setMapViewPortByWGS84Rectangle() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setMapViewPortByWGS84Rectangle( %1, %2 )", var1, var2);
            this.getDSICtrl().setMapViewPortByWGS84Rectangle(var1, var2);
            this.mZoomLevel = -1.0F;
            this.mZoomIndex = -1;
         } catch (Exception var4) {
            this.getLogger().log(10000, "MVRequestControl#setMapViewPortByWGS84Rectangle(): ERROR = %1", var4.getMessage());
         }
      }
   }

   public void ehSetCategoryVisibility(int var1, int[] var2, boolean[] var3) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#ehSetCategoryVisibility() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#ehSetCategoryVisibility( mapStyleType = %1 )", var1);
            if (var2 != null && var2.length > 0 && var3 != null && var3.length > 0) {
               this.getLogger()
                  .log(
                     10000000,
                     "MVRequestControl#ehSetCategoryVisibility( categoryUid = %1 and visibility = %2 )",
                     MapUtils.toString(var2),
                     MapUtils.toString(var3)
                  );
            }

            this.getDSICtrl().ehSetCategoryVisibility(var1, var2, var3);
         } catch (Exception var5) {
            this.getLogger().log(10000, "MVRequestControl#ehSetCategoryVisibility(): ERROR = %1", var5.getMessage());
         }
      }
   }

   public void ehSetCategoryVisibilityToDefault(int var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#ehSetCategoryVisibilityToDefault() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#ehSetCategoryVisibilityToDefault( mapStyleType = %1 )", var1);
            this.getDSICtrl().ehSetCategoryVisibilityToDefault(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#ehSetCategoryVisibilityToDefault(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setHorizonMarkerVisibility(boolean var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setHorizonMarkerVisibility() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setHorizonMarkerVisibility( mapStyleType = %1 )", var1);
            this.getDSICtrl().setHorizonMarkerVisibility(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setHorizonMarkerVisibility(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void getNumberOfPOIs() {
      try {
         this.getLogger().log(10000000, "MVRequestControl#getNumberOfPOIs( )");
      } catch (Exception var2) {
         this.getLogger().log(10000, "MVRequestControl#getNumberOfPOIs(): ERROR = %1", var2);
      }
   }

   public void packPOIContainer() {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#packPOIContainer() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#packPOIContainer()");
            this.getDSICtrl().packPOIContainer();
         } catch (Exception var2) {
            this.getLogger().log(10000, "MVRequestControl#packPOIContainer(): ERROR = %1", var2.getMessage());
         }
      }
   }

   public void set3DLandmarksVisible(boolean var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#set3DLandmarksVisible() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#set3DLandmarksVisible( %1 )", var1);
            this.getDSICtrl().set3DLandmarksVisible(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#set3DLandmarksVisible(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setLocation(int var1, short var2) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setLocation() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setLocation( %1, %2 )", var1, var2);
            this.getDSICtrl().setLocation(var1, var2);
         } catch (Exception var4) {
            this.getLogger().log(10000, "MVRequestControl#setLocation(): ERROR = %1", var4.getMessage());
         }
      }
   }

   public void setOrientation(int var1, Point var2) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setOrientation() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setOrientation( %2, %1 )", var2, var1);
            this.getDSICtrl().setOrientation(var1, var2);
         } catch (Exception var4) {
            this.getLogger().log(10000, "MVRequestControl#setOrientation(): ERROR = %1", var4.getMessage());
         }
      }
   }

   public void setZoomLevel(float var1, int var2) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setZoomLevel() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setZoomLevel( %1, %2 )", Float.toString(var1), var2);
            this.getLogger()
               .log(
                  10000000,
                  "MVRequestControl#setZoomLevel() - last requested zoomLevel: %1, last requested zoomIndex: %2 ",
                  Float.toString(this.mZoomLevel),
                  this.mZoomIndex
               );
            if (var2 != this.mZoomIndex || Math.abs(var1 - this.mZoomLevel) > 0.001F) {
               this.getDSICtrl().setZoomLevel(var1, var2);
               this.mZoomIndex = var2;
               this.mZoomLevel = var1;
            }
         } catch (Exception var4) {
            this.getLogger().log(10000, "MVRequestControl#setZoomLevel(): ERROR = %1", var4.getMessage());
         }
      }
   }

   public void setCountryOverviewCountry(String var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setCountryOverviewCountry() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setCountryOverviewCountry( %1 )", var1);
            this.getDSICtrl().setCountryOverviewCountry(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setCountryOverviewCountry(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void unpackPOIContainer(long var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#unpackPOIContainer() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#unpackPOIContainer( %1 )", var1);
            this.getDSICtrl().unpackPOIContainer(var1);
         } catch (Exception var4) {
            this.getLogger().log(10000, "MVRequestControl#unpackPOIContainer(): ERROR = %1", var4.getMessage());
         }
      }
   }

   public void setViewFocusOnBlock(long[] var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setViewFocusOnBlock() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setViewFocusOnBlock( %1 )", var1);
            this.getDSICtrl().setViewFocusOnBlock(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setViewFocusOnBlock(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setViewFocusOnPoi(PoiListElement[] var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setViewFocusOnPoi() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setViewFocusOnPoi( %1 )", var1);
            this.getDSICtrl().setViewFocusOnPoi(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setViewFocusOnPoi(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void startToDrawNewRectangleInMap() {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#startToDrawNewRectangleInMap() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#startToDrawNewRectangleInMap()");
            this.getDSICtrl().startToDrawNewRectangleInMap();
         } catch (Exception var2) {
            this.getLogger().log(10000, "MVRequestControl#startToDrawNewRectangleInMap(): ERROR = %1", var2.getMessage());
         }
      }
   }

   public void editRectangleInMap(long var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#editRectangleInMap() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#editRectangleInMap( %1 )", var1);
            this.getDSICtrl().editRectangleInMap(var1);
         } catch (Exception var4) {
            this.getLogger().log(10000, "MVRequestControl#editRectangleInMap(): ERROR = %1", var4.getMessage());
         }
      }
   }

   public void setSouthWestCornerOfRectangleInMap(Point var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setSouthWestCornerOfRectangleInMap() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setSouthWestCornerOfRectangleInMap( %1 )", var1);
            this.getDSICtrl().setSouthWestCornerOfRectangleInMap(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setSouthWestCornerOfRectangleInMap(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setNorthEastCornerOfRectangleInMap(Point var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setNorthEastCornerOfRectangleInMap() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setNorthEastCornerOfRectangleInMap( %1 )", var1);
            this.getDSICtrl().setNorthEastCornerOfRectangleInMap(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setNorthEastCornerOfRectangleInMap(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void finishDrawRectangleInMap() {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#finishDrawRectangleInMap() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#finishDrawRectangleInMap()");
            this.getDSICtrl().finishDrawRectangleInMap();
         } catch (Exception var2) {
            this.getLogger().log(10000, "MVRequestControl#finishDrawRectangleInMap(): ERROR = %1", var2.getMessage());
         }
      }
   }

   public void touchApproach(boolean var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#touchApproach() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#touchApproach( %1 )", var1);
            this.getDSICtrl().touchApproach(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void startScrollByVector(int var1, int var2) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#startScrollByVector() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#startScrollByVector( %1, %2 )", var1, var2);
            this.getDSICtrl().startScrollByVector(var1, var2);
         } catch (Exception var4) {
            this.getLogger().log(10000, "MVRequestControl#startScrollByVector(): ERROR = %1", var4.getMessage());
         }
      }
   }

   public void setGuidanceSymbol(int var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setGuidanceSymbol() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setGuidanceSymbol( %1 )", var1);
            this.getDSICtrl().setGuidanceSymbol(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setGuidanceSymbol(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setHOVLaneVisibility(boolean var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setHOVLaneVisibility() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setHOVLaneVisibility( %1 )", var1);
            this.getDSICtrl().setHOVLaneVisibility(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setHOVLaneVisibility(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setTollRoadHighLighting(boolean var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setTollRoadHighLighting() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setTollRoadHighLighting( %1 )", var1);
            this.getDSICtrl().setTollRoadHighLighting(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setTollRoadHighLighting(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setMountainPeakMarker(boolean var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setMountainPeakMarker() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setMountainPeakMarker( %1 )", var1);
            this.getDSICtrl().setMountainPeakMarker(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setMountainPeakMarker(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setViewFocusOnCombinedRouteListElements(long[] var1) {
      try {
         this.getLogger().log(10000000, "MVRequestControl#setViewFocusOnCombinedRouteListElements( %1 )", var1);
      } catch (Exception var3) {
         this.getLogger().log(10000, "MVRequestControl#setViewFocusOnCombinedRouteListElements(): ERROR = %1", var3);
      }
   }

   public void setFrameRateMode(int var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setFrameRateMode() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setFrameRateMode( %1 )", var1);
            this.getDSICtrl().setFrameRateMode(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setFrameRateMode(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setRouteColoringPolicy(int var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setRouteColoringPolicy() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setRouteColoringPolicy( %1 )", var1);
            this.getDSICtrl().setRouteColoringPolicy(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setRouteColoringPolicy(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void showSpeedAndFlowFreeFlow(boolean var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#showSpeedAndFlowFreeFlow() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#showSpeedAndFlowFreeFlow( %1 )", var1);
            this.getDSICtrl().showSpeedAndFlowFreeFlow(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#showSpeedAndFlowFreeFlow(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void showRichContent(boolean var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#showRichContent() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#showRichContent( %1 )", var1);
            this.getDSICtrl().showRichContent(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#showRichContent(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setCrossHairsColor(int var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setCrossHairsColor() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setCrossHairsColor( %1 )", var1);
            this.getDSICtrl().setCrossHairsColor(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setCrossHairsColor(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setTerrainElevation(boolean var1) {
      try {
         this.getLogger().log(10000000, "MVRequestControl#setTerrainElevation( %1 )", var1);
      } catch (Exception var3) {
         this.getLogger().log(10000, "MVRequestControl#setTerrainElevation(): ERROR = %1", var3);
      }
   }

   public void setMapOverlays(int var1, MapOverlay[] var2, int var3, int var4) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setMapOverlays() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setMapOverlays( %1 )", var1);
            this.getDSICtrl().setMapOverlays(var1, var2, var3, var4);
         } catch (Exception var6) {
            this.getLogger().log(10000, "MVRequestControl#setMapOverlays(): ERROR = %1", var6.getMessage());
         }
      }
   }

   public void setMapLayerVisible(int[] var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setMapLayerVisible() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setMapLayerVisible( %1 )", var1);
            this.getDSICtrl().setMapLayerVisible(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setMapLayerVisible(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setTemperatureScale(int var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setTemperatureScale() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setTemperatureScale( %1 )", var1);
            this.getDSICtrl().setTemperatureScale(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setTemperatureScale(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setSoftAnimationSpeed(int var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setSoftAnimationSpeed() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setSoftAnimationSpeed( %1 )", var1);
            this.getDSICtrl().setSoftAnimationSpeed(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setSoftAnimationSpeed(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setSpeedAndFlowRoadClass(int var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setSpeedAndFlowRoadClass() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setSpeedAndFlowRoadClass, new roadClass is:( %1 ), old roadClass is:( %2 )", var1, this.roadClass);
            if (this.roadClass != var1) {
               this.roadClass = var1;
               this.getDSICtrl().setSpeedAndFlowRoadClass(var1);
            }
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setSpeedAndFlowRoadClass(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setRouteVisibility(boolean var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setRouteVisibility() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setRouteVisibility( %1 )", var1);
            this.getDSICtrl().setRouteVisibility(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setRouteVisibility(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void setVisibleRoutes(NavSegmentID[] var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setVisibleRoutes() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setVisibleRoutes( %1 )", Arrays.asList(var1));
            this.getDSICtrl().setVisibleRoutes(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#setVisibleRoutes(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public void showTrafficEventListView(long[] var1, boolean var2, boolean var3) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#showTrafficEventListView() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#showTrafficEventListView( adjustViewPort: %1, showPins: %2)", var2, var3);
            this.getDSICtrl().showTrafficEventListView(var1, var2, var3);
         } catch (Exception var5) {
            this.getLogger().log(10000, "MVRequestControl#showTrafficEventListView(): ERROR = %1", var5.getMessage());
         }
      }
   }

   public int getRequestedOrientation() {
      return this.mOrientation;
   }

   public float getRequestedZoomLevel() {
      return this.mZoomLevel;
   }

   public Point getQueueGetInfoForScreenPositionPoint() {
      synchronized (this) {
         Point var2 = this.mQueueGetInfoForScreenPositionPoint;
         this.mQueueGetInfoForScreenPositionPoint = new Point(-1, -1);
         return var2;
      }
   }

   public void setCopyrightPosition(NavRectangle var1, int var2, int var3) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setCopyrightPosition() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setCopyrightPosition(Rectangle: %1, textAlignment %2, verticalAlignment %3)", var1, var2, var3);
            this.getDSICtrl().setCopyrightPosition(var1, var2, var3);
         } catch (Exception var5) {
            this.getLogger().log(10000, "MVRequestControl#setCopyrightPosition(): ERROR = %1", var5.getMessage());
         }
      }
   }

   public void setMapStyle(int var1) {
      if (!this.isDSICtrlAvailable()) {
         this.getLogger().log(10000, "MVRequestControl#setMapStyle() - DSICtrl not available!");
      } else {
         try {
            this.getLogger().log(10000000, "MVRequestControl#setMapStyle(mapStlye: %1)", var1);
            this.getDSICtrl().setMapStyle(var1);
         } catch (Exception var3) {
            this.getLogger().log(10000, "MVRequestControl#showTrafficEventListView(): ERROR = %1", var3.getMessage());
         }
      }
   }

   public boolean mibsiSetRenderVisible(boolean var1) {
      if (!this.isDSICtrlAvailable()) {
         return false;
      }

      this.getDSICtrl().viewSetVisible(var1);
      return true;
   }
}
