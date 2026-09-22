/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.audi.app.terminalmode.IContext
 *  de.audi.app.terminalmode.smartphone.androidauto2.AbstractAndroidAuto2Handler
 *  de.audi.app.terminalmode.smartphone.androidauto2.nav.IAndroidAuto2NavHandler
 *  de.audi.app.terminalmode.statemachine.Application
 *  de.audi.app.terminalmode.statemachine.ApplicationOwner
 *  de.audi.app.terminalmode.statemachine.IStateHandler
 *  de.audi.atip.log.LogChannel
 *  org.dsi.ifc.androidauto2.DSIAndroidAuto2
 */
package de.audi.app.terminalmode.smartphone.androidauto2.nav;

import de.audi.app.terminalmode.IContext;
import de.audi.app.terminalmode.smartphone.androidauto2.AbstractAndroidAuto2Handler;
import de.audi.app.terminalmode.smartphone.androidauto2.nav.IAndroidAuto2NavHandler;
import de.audi.app.terminalmode.statemachine.Application;
import de.audi.app.terminalmode.statemachine.ApplicationOwner;
import de.audi.app.terminalmode.statemachine.IStateHandler;
import de.audi.atip.log.LogChannel;
import org.dsi.ifc.androidauto2.DSIAndroidAuto2;

public class AndroidAuto2NavHandler
extends AbstractAndroidAuto2Handler
implements IAndroidAuto2NavHandler {
    private static final String LOGCLASS = "AndroidAuto2NavHandler";
    private volatile ApplicationOwner appNaviOwnerCurrent = ApplicationOwner.NOBODY;
    private volatile boolean hasNavFocusRequested;
    private volatile ApplicationOwner expectedAppNavOwner = ApplicationOwner.NOBODY;

    public AndroidAuto2NavHandler(LogChannel logChannel, DSIAndroidAuto2 dSIAndroidAuto2, IStateHandler iStateHandler, IContext iContext) {
        super(logChannel, dSIAndroidAuto2, iStateHandler, iContext);
    }

    public void updateNavFocus(ApplicationOwner applicationOwner) {
        if (this.hasNavFocusRequested) {
            if (this.expectedAppNavOwner.equals((Object)applicationOwner)) {
                this.logger.log(1000000, "[%1.updateNavFocus] responding to requested navFocusNotification: %2", (Object)LOGCLASS, (Object)(ApplicationOwner.DEVICE.equals((Object)applicationOwner) ? "PROJECTED" : "NATIVE"));
                this.dsi.navFocusNotification(ApplicationOwner.DEVICE.equals((Object)applicationOwner) ? 2 : 1, false);
                this.hasNavFocusRequested = false;
                this.expectedAppNavOwner = ApplicationOwner.NOBODY;
            }
        } else {
            if (this.appNaviOwnerCurrent.equals((Object)applicationOwner)) {
                this.logger.log(1000000, "[%1.updateNavFocus] %2 -> %3, do nothing", (Object)LOGCLASS, (Object)this.appNaviOwnerCurrent, (Object)applicationOwner);
                return;
            }
            if (ApplicationOwner.DEVICE.equals((Object)applicationOwner)) {
                this.logger.log(1000000, "[%1.updateNavFocus] %2 -> %3, navFocusNotification: PROJECTED", (Object)LOGCLASS, (Object)this.appNaviOwnerCurrent, (Object)applicationOwner);
                this.dsi.navFocusNotification(2, true);
            } else {
                this.logger.log(1000000, "[%1.updateNavFocus] %2 -> %3, navFocusNotification: NATIVE", (Object)LOGCLASS, (Object)this.appNaviOwnerCurrent, (Object)applicationOwner);
            }
        }
        this.appNaviOwnerCurrent = applicationOwner;
    }

    public void navFocusRequestNotification(int n, int n2) {
        if (this.isValid(n2)) {
            this.logger.log(1000000, "<- [%1.navFocusRequestNotification] %2", (Object)LOGCLASS, (Object)this.getNavState(n));
            if (1 == n) {
                this.requestDSIUpdate(Application.NAVI, ApplicationOwner.NOBODY);
                this.hasNavFocusRequested = true;
                this.expectedAppNavOwner = ApplicationOwner.NOBODY;
            } else if (2 == n) {
                this.hasNavFocusRequested = true;
                this.expectedAppNavOwner = ApplicationOwner.DEVICE;
                this.updateNavFocus(ApplicationOwner.DEVICE);
            }
        }
    }

    private String getNavState(int n) {
        switch (n) {
            case 1: {
                return "NAV_NATIVE";
            }
            case 2: {
                return "NAV_PROJECTED";
            }
        }
        return "NAV_UNKNOWN " + n;
    }

    protected String getLogClass() {
        return LOGCLASS;
    }
}

