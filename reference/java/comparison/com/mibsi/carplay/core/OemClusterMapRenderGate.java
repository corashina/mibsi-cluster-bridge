/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.audi.tghu.navi.app.Navigation
 *  de.audi.tghu.navi.app.map.AbstractMap
 *  de.esolutions.fw.util.commons.job.DispatcherBase
 */
package com.mibsi.carplay.core;

import com.mibsi.carplay.framework.Log;
import de.audi.tghu.navi.app.Navigation;
import de.audi.tghu.navi.app.map.AbstractMap;
import de.audi.tghu.navi.app.map.dsi.MVRequestControl;
import de.esolutions.fw.util.commons.job.DispatcherBase;

public final class OemClusterMapRenderGate {
    private static Backend backend = new NavigationBackend();
    private static boolean wanted;
    private static boolean queued;
    private static Object held;
    private static boolean heldDesired;
    private static Object observed;
    private static boolean observedDesired;
    private static long nextCheck;
    private static boolean failureLogged;
    private static boolean restorePending;

    private OemClusterMapRenderGate() {
    }

    public static synchronized boolean filter(Object object, boolean bl) {
        try {
            if (object != null && object == held) {
                heldDesired = bl;
            }
            if (object == null || object != backend.current()) {
                return bl;
            }
            observed = object;
            observedDesired = bl;
            return wanted && object == held ? false : bl;
        }
        catch (Throwable throwable) {
            return bl;
        }
    }

    public static synchronized boolean request(boolean bl) {
        if (wanted != bl) {
            nextCheck = 0L;
        }
        if (!(wanted = bl) && held == null && !queued) {
            return true;
        }
        long l = System.currentTimeMillis();
        if (!queued && l >= nextCheck) {
            nextCheck = l + 1000L;
            queued = true;
            try {
                if (!backend.execute(new Runnable(){

                    public void run() {
                        OemClusterMapRenderGate.reconcile();
                    }
                })) {
                    queued = false;
                }
            }
            catch (Throwable throwable) {
                queued = false;
                OemClusterMapRenderGate.reportFailure(throwable);
            }
        }
        return !wanted && held == null && !queued;
    }

    private static synchronized void reconcile() {
        try {
            Object object;
            Object object2 = object = wanted ? backend.current() : null;
            if (held != null && (!wanted || object != held || restorePending)) {
                restorePending = true;
                if (!backend.write(held, heldDesired)) {
                    return;
                }
                held = null;
                restorePending = false;
                Log.w("OEMMapRender", "cockpit OEM visibility restored");
            }
            if (wanted && object != null && held == null) {
                boolean bl = object == observed ? observedDesired : backend.visible(object);
                held = object;
                heldDesired = bl;
                if (!backend.write(object, false)) {
                    held = null;
                    return;
                }
                restorePending = false;
                Log.w("OEMMapRender", "cockpit OEM rendering suspended");
            }
            failureLogged = false;
        }
        catch (Throwable throwable) {
            restorePending = true;
            OemClusterMapRenderGate.reportFailure(throwable);
        }
        finally {
            queued = false;
        }
    }

    private static void reportFailure(Throwable throwable) {
        if (!failureLogged) {
            Log.w("OEMMapRender", "visibility retry: " + throwable);
        }
        failureLogged = true;
    }

    private static final class NavigationBackend
    implements Backend {
        private NavigationBackend() {
        }

        public Object current() {
            Navigation navigation = Navigation.getInstance();
            if (navigation == null || navigation.getMapManager() == null) {
                return null;
            }
            AbstractMap abstractMap = navigation.getMapManager().getMapKombi();
            if (abstractMap == null || !abstractMap.isInitialized() || abstractMap.getMVRequest() == null) {
                return null;
            }
            return abstractMap.getMVRequest().getMVRequestControlActive();
        }

        public boolean visible(Object object) {
            return ((MVRequestControl)((Object)object)).getMVResponseControl().getViewVisible();
        }

        public boolean write(Object object, boolean bl) {
            MVRequestControl mVRequestControl = (MVRequestControl)((Object)object);
            AbstractMap abstractMap = mVRequestControl.getMap();
            if (abstractMap == null || !abstractMap.isInitialized()) {
                return false;
            }
            return mVRequestControl.mibsiSetRenderVisible(bl);
        }

        public boolean execute(Runnable runnable) {
            Navigation navigation = Navigation.getInstance();
            if (navigation == null) {
                return false;
            }
            DispatcherBase dispatcherBase = navigation.getDispatcher();
            if (dispatcherBase == null) {
                return false;
            }
            dispatcherBase.execute((Object)runnable);
            return true;
        }
    }

    static interface Backend {
        public Object current();

        public boolean visible(Object var1);

        public boolean write(Object var1, boolean var2);

        public boolean execute(Runnable var1);
    }
}

