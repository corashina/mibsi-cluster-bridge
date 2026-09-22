/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.audi.atip.hmi.event.ModelUpdateEvent
 *  de.audi.atip.hmi.intercommunication.NaviMoKoKDKConstants
 *  de.audi.atip.hmi.model.HMIModel
 *  de.audi.atip.hmi.modelaccess.ChoiceModelGUI
 *  de.audi.atip.hmi.view.IDisplayListener
 *  de.audi.atip.hmi.view.IDisplayManager
 *  de.audi.tghu.fwhmi.IDisplayManagerKombiControl
 *  de.audi.tghu.hmi.evo.IHMIServiceEvo
 *  de.esolutions.hmi.widgets.audi.base.HMITerminalImpl
 *  de.esolutions.hmi.widgets.audi.base.IWidgetLogChannel
 *  de.esolutions.hmi.widgets.audi.base.InitializationContext
 *  de.esolutions.hmi.widgets.audi.base.Layout
 *  de.esolutions.hmi.widgets.audi.base.widgets.IRenderer
 *  de.esolutions.hmi.widgets.audi.evo.DisplayControllerEvo
 *  de.esolutions.hmi.widgets.audi.evo.high.AnimationControllerMIB2High
 */
package de.esolutions.hmi.widgets.audi.evo.high.widgets;

import com.mibsi.carplay.core.Mhi2qAaClusterComposition;
import de.audi.atip.hmi.event.ModelUpdateEvent;
import de.audi.atip.hmi.intercommunication.NaviMoKoKDKConstants;
import de.audi.atip.hmi.model.HMIModel;
import de.audi.atip.hmi.modelaccess.ChoiceModelGUI;
import de.audi.atip.hmi.view.IDisplayListener;
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

public class CombiMapController
extends DisplayControllerEvo
implements NaviMoKoKDKConstants {
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

    public void connected(InitializationContext initializationContext) {
        super.connected(initializationContext);
        if (this.kombiTerminal == 1) {
            IDisplayManager iDisplayManager = this.getDisplayManager(hmiService);
            Layout layout = this.getLayout(this.terminal);
            if (iDisplayManager != null && layout != null) {
                iDisplayManager.setDisplayType(1, this.displayType);
                this.positionKDKBackgrounds(iDisplayManager, layout);
                this.positionMap(iDisplayManager, layout, false);
            }
        }
    }

    private IDisplayManager getDisplayManager(IHMIServiceEvo iHMIServiceEvo) {
        IDisplayManager iDisplayManager = null;
        if (iHMIServiceEvo != null) {
            iDisplayManager = iHMIServiceEvo.getDisplayManager();
        }
        return iDisplayManager;
    }

    private Layout getLayout(HMITerminalImpl hMITerminalImpl) {
        Layout layout = null;
        if (hMITerminalImpl != null) {
            layout = hMITerminalImpl.getLayout();
        }
        return layout;
    }

    private void positionKDKBackgrounds(IDisplayManager iDisplayManager, Layout layout) {
        logKDK.log(10000000, "CombiMapController#connected layout: %1", (Object)layout.getClass().getName());
        int n = layout.getIntegerConstant(58);
        int n2 = layout.getIntegerConstant(59);
        int n3 = layout.getIntegerConstant(60);
        int n4 = layout.getIntegerConstant(61);
        logKDK.log(10000000, "CombiMapController#connected KDK background small stage: (%1, %2)", (long)n, (long)n2);
        iDisplayManager.setPosition(101, this.kombiTerminal, n, n2);
        logKDK.log(10000000, "CombiMapController#connected KDK background large stage: (%1, %2)", (long)n3, (long)n4);
        iDisplayManager.setPosition(102, this.kombiTerminal, n3, n4);
    }

    private void positionMap(IDisplayManager iDisplayManager, Layout layout, boolean bl) {
        logKDK.log(10000000, "CombiMapController#positionMap layout: %1", (Object)layout.getClass().getName());
        int n = layout.getIntegerConstant(108);
        int n2 = layout.getIntegerConstant(109);
        if (bl) {
            logKDK.log(10000000, "CombiMapController#positionMap adding a small stage offset from the layout");
            n += layout.getIntegerConstant(80);
            n2 += layout.getIntegerConstant(81);
        }
        logKDK.log(10000000, "CombiMapController#positionMap mapX: %1, mapY: %2", (long)n, (long)n2);
        iDisplayManager.setPosition(33, this.kombiTerminal, n, n2);
        iDisplayManager.setPosition(58, this.kombiTerminal, n, n2);
    }

    public void processModelUpdateEvent(ModelUpdateEvent modelUpdateEvent) {
        logKDK.log(1000000, "CombiMapController#processModelUpdateEvent %1", (Object)modelUpdateEvent);
        if (modelUpdateEvent.getModelId() == 402521) {
            if (this.kombiTerminal != 0) {
                IDisplayManager iDisplayManager = this.getDisplayManager(hmiService);
                Layout layout = this.getLayout(this.terminal);
                if (iDisplayManager != null && layout != null && hmiService != null) {
                    HMIModel hMIModel = hmiService.getModel(402521);
                    boolean bl = false;
                    if (hMIModel instanceof ChoiceModelGUI) {
                        int n = ((ChoiceModelGUI)hMIModel).getValue();
                        Mhi2qAaClusterComposition.onViewSize(n);
                        bl = n == 1;
                    }
                    this.positionMap(iDisplayManager, layout, bl);
                } else {
                    logKDK.log(100000, "CombiMapController#processModelUpdateEvent environment not fully initialized");
                }
            }
        } else if (this.kombiTerminal != 0) {
            if (framework.getSysConst(541) == 2) {
                this.handleKDK(modelUpdateEvent);
                this.switchToTargetContext();
            } else {
                this.switchToTargetContext();
                this.updateFrameRate(modelUpdateEvent);
            }
        } else {
            this.handleKDK(modelUpdateEvent);
        }
    }

    private void handleKDK(ModelUpdateEvent modelUpdateEvent) {
        IDisplayManagerKombiControl iDisplayManagerKombiControl = this.getDisplayManager();
        if (iDisplayManagerKombiControl == null) {
            IWidgetLogChannel.logKDK.log(100000, "CombiMapController#handleKDK failed to retrieve the display manager; ignoring the invocation");
            return;
        }
        int n = this.updateKdkDisplayable(modelUpdateEvent);
        Layout layout = this.terminal.getLayout();
        if (this.kombiTerminal != 0) {
            this.handleKdkDualTerminal(iDisplayManagerKombiControl, this.kdkDisplayable, modelUpdateEvent, layout);
        } else {
            this.handleKdkSingleTerminal(this.kdkDisplayable, n, layout);
        }
    }

    private IDisplayManagerKombiControl getDisplayManager() {
        IDisplayManager iDisplayManager;
        IDisplayManagerKombiControl iDisplayManagerKombiControl = null;
        if (hmiService != null && (iDisplayManager = hmiService.getDisplayManager()) instanceof IDisplayManagerKombiControl) {
            iDisplayManagerKombiControl = (IDisplayManagerKombiControl)iDisplayManager;
        }
        return iDisplayManagerKombiControl;
    }

    private int updateKdkDisplayable(ModelUpdateEvent modelUpdateEvent) {
        int n = this.kdkDisplayable;
        this.kdkDisplayable = this.getKdkDisplayable(modelUpdateEvent);
        logKDK.log(10000000, "CombiMapController#updateKdkDisplayable received model update, previous visible kdk: %1 new visible kdk: %2", (long)n, (long)this.kdkDisplayable);
        return n;
    }

    private int getKdkDisplayable(ModelUpdateEvent modelUpdateEvent) {
        int n = -1;
        if ((modelUpdateEvent.getHints() & 4) > 0) {
            n = 20;
        }
        return n;
    }

    private void handleKdkDualTerminal(IDisplayManagerKombiControl iDisplayManagerKombiControl, int n, ModelUpdateEvent modelUpdateEvent, Layout layout) {
        int n2 = 102;
        int n3 = 0;
        int n4 = 101;
        int n5 = 0;
        if (n != -1) {
            this.kdkOpacity = 0;
            if ((modelUpdateEvent.getHints() & 0x10) > 0) {
                this.kdkOpacity = 100;
            }
            n3 = this.kdkOpacity;
            logKDK.log(10000000, "CombiMapController#handleKdkDualTerminal layout: %1", (Object)layout.getClass().getName());
            int n6 = layout.getIntegerConstant(118);
            int n7 = layout.getIntegerConstant(119);
            int n8 = layout.getIntegerConstant(120);
            int n9 = layout.getIntegerConstant(121);
            this.kdkPosX = layout.getIntegerConstant(60);
            this.kdkPosY = layout.getIntegerConstant(61);
            if ((modelUpdateEvent.getHints() & 8) > 0) {
                n2 = 101;
                n4 = 102;
                n6 = layout.getIntegerConstant(122);
                n7 = layout.getIntegerConstant(123);
                n8 = layout.getIntegerConstant(124);
                n9 = layout.getIntegerConstant(125);
                this.kdkPosX = layout.getIntegerConstant(58);
                this.kdkPosY = layout.getIntegerConstant(59);
            }
            logKDK.log(10000000, "CombiMapController#handleKdkDualTerminal KDK cropping source: (%1, %2), size: %3 x %4", (Object)String.valueOf(n6), (Object)String.valueOf(n7), (Object)String.valueOf(n8), (Object)String.valueOf(n9));
            logKDK.log(10000000, "CombiMapController#handleKdkDualTerminal KDK cropping target: (%1, %2)", (long)this.kdkPosX, (long)this.kdkPosY);
            iDisplayManagerKombiControl.setCropping(n, this.kombiTerminal, n6, n7, n8, n9, this.kdkPosX, this.kdkPosY, n8, n9);
            logKDK.log(10000000, "CombiMapController#handleKdkDualTerminal setting the KDK's opacity to %1", (long)this.kdkOpacity);
            iDisplayManagerKombiControl.setOpacity(n, this.kombiTerminal, this.kdkOpacity);
        }
        this.positionKDKBackgrounds((IDisplayManager)iDisplayManagerKombiControl, layout);
        logKDK.log(10000000, "CombiMapController#handleKdkDualTerminal setting the primary KDK backround's opacity to %1, the secondary background's opacity to %2", (long)n3, (long)n5);
        iDisplayManagerKombiControl.setOpacity(n2, this.kombiTerminal, n3);
        iDisplayManagerKombiControl.setOpacity(n4, this.kombiTerminal, n5);
        iDisplayManagerKombiControl.setKDKVisible(n, this.kombiTerminal);
    }

    private void handleKdkSingleTerminal(int n, int n2, Layout layout) {
        logKDK.log(10000000, "CombiMapController#handleKDK kombiTerminal == HMITerminal.MAIN (G24)");
        int n3 = layout.getIntegerConstant(58);
        int n4 = layout.getIntegerConstant(60);
        int n5 = layout.getIntegerConstant(59);
        int n6 = layout.getIntegerConstant(61);
        if (n != -1) {
            boolean bl = ((HMITerminalImpl)hmiService.getHMITerminal(0)).getViewSizeManager().getCurrentViewSize() == 2;
            this.kdkOpacity = 0;
            this.kdkPosX = bl ? n4 : n3;
            this.kdkPosY = bl ? n6 : n5;
            hmiService.getDisplayManager().setOpacity(n, this.kombiTerminal, this.kdkOpacity);
            hmiService.getDisplayManager().setPosition(n, this.kombiTerminal, this.kdkPosX, this.kdkPosY);
        }
        if (n != n2) {
            if (n != -1) {
                ((IDisplayManagerKombiControl)hmiService.getDisplayManager()).setKDKVisible(n, this.kombiTerminal);
            }
            logKDK.log(10000000, "CombiMapController#handleKDK before starting kdk animation fade in: %1", n != -1);
            ((AnimationControllerMIB2High)hmiService.getHMITerminal(0).getIAnimationController()).startKDKAnimation(n != -1);
        }
    }

    private void updateFrameRate(ModelUpdateEvent modelUpdateEvent) {
        int n = modelUpdateEvent.getHints();
        this.updateRate = (n & 2) > 0 ? 10 : ((n & 1) > 0 ? 1 : 0);
        int n2 = hmiService.getDisplayManager().getCurrentContextID(this.kombiTerminal);
        if (n2 == -1 || n2 == 75) {
            this.updateRate = 0;
        }
        hmiService.getDisplayManager().setUpdateRate(this.kombiTerminal, this.updateRate);
    }

    private boolean isMapContext(int n) {
        boolean bl = false;
        switch (n) {
            case 72: 
            case 73: 
            case 74: 
            case 75: 
            case 76: 
            case 77: {
                bl = true;
                break;
            }
            default: {
                bl = false;
            }
        }
        return bl;
    }

    public void switchToTargetContext() {
        if (Mhi2qAaClusterComposition.shouldPinStockClusterContext()) {
            return;
        }
        if (this.kombiTerminal != 0 && !this.isMapContext(this.getTargetContextID())) {
            logDisplay.log(10000, "CombiMapController#switchToTargetContext not switching to context: %1 because it is no valid map context", (long)this.getTargetContextID());
            return;
        }
        logDisplay.log(10000000, "CombiMapController#switchToTargetContext context: %1", (long)this.getTargetContextID());
        hmiService.getDisplayManager().switchContext(this.getTargetContextID(), this.kombiTerminal, (IDisplayListener)this);
    }

    public void setOpacityOnBackgroundLayers(float f, boolean bl) {
    }

    protected void positionDisplayables() {
    }

    public void setKombiTerminal(int n) {
        this.kombiTerminal = n;
    }

    public void setDisplayType(int n) {
        this.displayType = n;
    }
}

