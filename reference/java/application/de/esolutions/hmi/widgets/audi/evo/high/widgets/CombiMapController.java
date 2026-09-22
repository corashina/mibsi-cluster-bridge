package de.esolutions.hmi.widgets.audi.evo.high.widgets;

import com.mibsi.carplay.core.Mhi2qAaClusterComposition;
import de.audi.atip.hmi.event.ModelUpdateEvent;
import de.audi.atip.hmi.intercommunication.NaviMoKoKDKConstants;
import de.audi.atip.hmi.model.HMIModel;
import de.audi.atip.hmi.modelaccess.ChoiceModelGUI;
import de.audi.atip.hmi.view.IDisplayManager;
import de.audi.tghu.fwhmi.IDisplayManagerKombiControl;
import de.audi.tghu.hmi.evo.IHMIServiceEvo;
import de.esolutions.hmi.widgets.audi.base.HMITerminalImpl;
import de.esolutions.hmi.widgets.audi.base.IWidgetLogChannel;
import de.esolutions.hmi.widgets.audi.base.InitializationContext;
import de.esolutions.hmi.widgets.audi.base.Layout;
import de.esolutions.hmi.widgets.audi.base.widgets.IRenderer;
import de.esolutions.hmi.widgets.audi.evo.DisplayControllerEvo;
import de.esolutions.hmi.widgets.audi.evo.high.AnimationControllerMIB2High;

public class CombiMapController extends DisplayControllerEvo implements NaviMoKoKDKConstants {
   private static final boolean SMALL_STAGE_FLAG_DEFAULT = false;
   private int kombiTerminal = 1;
   private int displayType;
   private int updateRate = 10;
   private int kdkDisplayable = -1;
   private int kdkPosX = 0;
   private int kdkPosY = 0;
   private int kdkOpacity = 0;

   public IRenderer getRenderer() {
      return null;
   }

   public void connected(InitializationContext var1) {
      super.connected(var1);
      if (this.kombiTerminal == 1) {
         IDisplayManager var2 = this.getDisplayManager(hmiService);
         Layout var3 = this.getLayout(this.terminal);
         if (var2 != null && var3 != null) {
            var2.setDisplayType(1, this.displayType);
            this.positionKDKBackgrounds(var2, var3);
            this.positionMap(var2, var3, false);
         }
      }
   }

   private IDisplayManager getDisplayManager(IHMIServiceEvo var1) {
      IDisplayManager var2 = null;
      if (var1 != null) {
         var2 = var1.getDisplayManager();
      }

      return var2;
   }

   private Layout getLayout(HMITerminalImpl var1) {
      Layout var2 = null;
      if (var1 != null) {
         var2 = var1.getLayout();
      }

      return var2;
   }

   private void positionKDKBackgrounds(IDisplayManager var1, Layout var2) {
      logKDK.log(10000000, "CombiMapController#connected layout: %1", var2.getClass().getName());
      int var3 = var2.getIntegerConstant(58);
      int var4 = var2.getIntegerConstant(59);
      int var5 = var2.getIntegerConstant(60);
      int var6 = var2.getIntegerConstant(61);
      logKDK.log(10000000, "CombiMapController#connected KDK background small stage: (%1, %2)", var3, var4);
      var1.setPosition(101, this.kombiTerminal, var3, var4);
      logKDK.log(10000000, "CombiMapController#connected KDK background large stage: (%1, %2)", var5, var6);
      var1.setPosition(102, this.kombiTerminal, var5, var6);
   }

   private void positionMap(IDisplayManager var1, Layout var2, boolean var3) {
      logKDK.log(10000000, "CombiMapController#positionMap layout: %1", var2.getClass().getName());
      int var4 = var2.getIntegerConstant(108);
      int var5 = var2.getIntegerConstant(109);
      if (var3) {
         logKDK.log(10000000, "CombiMapController#positionMap adding a small stage offset from the layout");
         var4 += var2.getIntegerConstant(80);
         var5 += var2.getIntegerConstant(81);
      }

      logKDK.log(10000000, "CombiMapController#positionMap mapX: %1, mapY: %2", var4, var5);
      var1.setPosition(33, this.kombiTerminal, var4, var5);
      var1.setPosition(58, this.kombiTerminal, var4, var5);
   }

   public void processModelUpdateEvent(ModelUpdateEvent var1) {
      logKDK.log(1000000, "CombiMapController#processModelUpdateEvent %1", var1);
      if (var1.getModelId() == 402521) {
         if (this.kombiTerminal != 0) {
            IDisplayManager var2 = this.getDisplayManager(hmiService);
            Layout var3 = this.getLayout(this.terminal);
            if (var2 != null && var3 != null && hmiService != null) {
               HMIModel var4 = hmiService.getModel(402521);
               boolean var5 = false;
               if (var4 instanceof ChoiceModelGUI) {
                  int var10000 = ((ChoiceModelGUI)var4).getValue();
                  Mhi2qAaClusterComposition.onViewSize(var10000);
                  var5 = var10000 == 1;
               }

               this.positionMap(var2, var3, var5);
            } else {
               logKDK.log(100000, "CombiMapController#processModelUpdateEvent environment not fully initialized");
            }
         }
      } else if (this.kombiTerminal != 0) {
         if (framework.getSysConst(541) == 2) {
            this.handleKDK(var1);
            this.switchToTargetContext();
         } else {
            this.switchToTargetContext();
            this.updateFrameRate(var1);
         }
      } else {
         this.handleKDK(var1);
      }
   }

   private void handleKDK(ModelUpdateEvent var1) {
      IDisplayManagerKombiControl var2 = this.getDisplayManager();
      if (var2 == null) {
         IWidgetLogChannel.logKDK.log(100000, "CombiMapController#handleKDK failed to retrieve the display manager; ignoring the invocation");
      } else {
         int var3 = this.updateKdkDisplayable(var1);
         Layout var4 = this.terminal.getLayout();
         if (this.kombiTerminal != 0) {
            this.handleKdkDualTerminal(var2, this.kdkDisplayable, var1, var4);
         } else {
            this.handleKdkSingleTerminal(this.kdkDisplayable, var3, var4);
         }
      }
   }

   private IDisplayManagerKombiControl getDisplayManager() {
      IDisplayManagerKombiControl var1 = null;
      if (hmiService != null) {
         IDisplayManager var2 = hmiService.getDisplayManager();
         if (var2 instanceof IDisplayManagerKombiControl) {
            var1 = (IDisplayManagerKombiControl)var2;
         }
      }

      return var1;
   }

   private int updateKdkDisplayable(ModelUpdateEvent var1) {
      int var2 = this.kdkDisplayable;
      this.kdkDisplayable = this.getKdkDisplayable(var1);
      logKDK.log(
         10000000, "CombiMapController#updateKdkDisplayable received model update, previous visible kdk: %1 new visible kdk: %2", var2, this.kdkDisplayable
      );
      return var2;
   }

   private int getKdkDisplayable(ModelUpdateEvent var1) {
      byte var2 = -1;
      if ((var1.getHints() & 4) > 0) {
         var2 = 20;
      }

      return var2;
   }

   private void handleKdkDualTerminal(IDisplayManagerKombiControl var1, int var2, ModelUpdateEvent var3, Layout var4) {
      byte var5 = 102;
      int var6 = 0;
      byte var7 = 101;
      byte var8 = 0;
      if (var2 != -1) {
         this.kdkOpacity = 0;
         if ((var3.getHints() & 16) > 0) {
            this.kdkOpacity = 100;
         }

         var6 = this.kdkOpacity;
         logKDK.log(10000000, "CombiMapController#handleKdkDualTerminal layout: %1", var4.getClass().getName());
         int var9 = var4.getIntegerConstant(118);
         int var10 = var4.getIntegerConstant(119);
         int var11 = var4.getIntegerConstant(120);
         int var12 = var4.getIntegerConstant(121);
         this.kdkPosX = var4.getIntegerConstant(60);
         this.kdkPosY = var4.getIntegerConstant(61);
         if ((var3.getHints() & 8) > 0) {
            var5 = 101;
            var7 = 102;
            var9 = var4.getIntegerConstant(122);
            var10 = var4.getIntegerConstant(123);
            var11 = var4.getIntegerConstant(124);
            var12 = var4.getIntegerConstant(125);
            this.kdkPosX = var4.getIntegerConstant(58);
            this.kdkPosY = var4.getIntegerConstant(59);
         }

         logKDK.log(
            10000000,
            "CombiMapController#handleKdkDualTerminal KDK cropping source: (%1, %2), size: %3 x %4",
            String.valueOf(var9),
            String.valueOf(var10),
            String.valueOf(var11),
            String.valueOf(var12)
         );
         logKDK.log(10000000, "CombiMapController#handleKdkDualTerminal KDK cropping target: (%1, %2)", this.kdkPosX, this.kdkPosY);
         var1.setCropping(var2, this.kombiTerminal, var9, var10, var11, var12, this.kdkPosX, this.kdkPosY, var11, var12);
         logKDK.log(10000000, "CombiMapController#handleKdkDualTerminal setting the KDK's opacity to %1", this.kdkOpacity);
         var1.setOpacity(var2, this.kombiTerminal, this.kdkOpacity);
      }

      this.positionKDKBackgrounds(var1, var4);
      logKDK.log(
         10000000,
         "CombiMapController#handleKdkDualTerminal setting the primary KDK backround's opacity to %1, the secondary background's opacity to %2",
         var6,
         var8
      );
      var1.setOpacity(var5, this.kombiTerminal, var6);
      var1.setOpacity(var7, this.kombiTerminal, var8);
      var1.setKDKVisible(var2, this.kombiTerminal);
   }

   private void handleKdkSingleTerminal(int var1, int var2, Layout var3) {
      logKDK.log(10000000, "CombiMapController#handleKDK kombiTerminal == HMITerminal.MAIN (G24)");
      int var4 = var3.getIntegerConstant(58);
      int var5 = var3.getIntegerConstant(60);
      int var6 = var3.getIntegerConstant(59);
      int var7 = var3.getIntegerConstant(61);
      if (var1 != -1) {
         boolean var8 = ((HMITerminalImpl)hmiService.getHMITerminal(0)).getViewSizeManager().getCurrentViewSize() == 2;
         this.kdkOpacity = 0;
         this.kdkPosX = var8 ? var5 : var4;
         this.kdkPosY = var8 ? var7 : var6;
         hmiService.getDisplayManager().setOpacity(var1, this.kombiTerminal, this.kdkOpacity);
         hmiService.getDisplayManager().setPosition(var1, this.kombiTerminal, this.kdkPosX, this.kdkPosY);
      }

      if (var1 != var2) {
         if (var1 != -1) {
            ((IDisplayManagerKombiControl)hmiService.getDisplayManager()).setKDKVisible(var1, this.kombiTerminal);
         }

         logKDK.log(10000000, "CombiMapController#handleKDK before starting kdk animation fade in: %1", var1 != -1);
         ((AnimationControllerMIB2High)hmiService.getHMITerminal(0).getIAnimationController()).startKDKAnimation(var1 != -1);
      }
   }

   private void updateFrameRate(ModelUpdateEvent var1) {
      int var2 = var1.getHints();
      if ((var2 & 2) > 0) {
         this.updateRate = 10;
      } else if ((var2 & 1) > 0) {
         this.updateRate = 1;
      } else {
         this.updateRate = 0;
      }

      int var3 = hmiService.getDisplayManager().getCurrentContextID(this.kombiTerminal);
      if (var3 == -1 || var3 == 75) {
         this.updateRate = 0;
      }

      hmiService.getDisplayManager().setUpdateRate(this.kombiTerminal, this.updateRate);
   }

   private boolean isMapContext(int var1) {
      boolean var2 = false;
      switch (var1) {
         case 72:
         case 73:
         case 74:
         case 75:
         case 76:
         case 77:
            var2 = true;
            break;
         default:
            var2 = false;
      }

      return var2;
   }

   public void switchToTargetContext() {
      if (!Mhi2qAaClusterComposition.shouldPinStockClusterContext()) {
         if (this.kombiTerminal != 0 && !this.isMapContext(this.getTargetContextID())) {
            logDisplay.log(
               10000, "CombiMapController#switchToTargetContext not switching to context: %1 because it is no valid map context", this.getTargetContextID()
            );
         } else {
            logDisplay.log(10000000, "CombiMapController#switchToTargetContext context: %1", this.getTargetContextID());
            hmiService.getDisplayManager().switchContext(this.getTargetContextID(), this.kombiTerminal, this);
         }
      }
   }

   public void setOpacityOnBackgroundLayers(float var1, boolean var2) {
   }

   protected void positionDisplayables() {
   }

   public void setKombiTerminal(int var1) {
      this.kombiTerminal = var1;
   }

   public void setDisplayType(int var1) {
      this.displayType = var1;
   }
}
