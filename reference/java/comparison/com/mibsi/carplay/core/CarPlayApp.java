/*
 * Decompiled with CFR 0.152.
 */
package com.mibsi.carplay.core;

import com.mibsi.carplay.aa.NavigationStateReader;
import com.mibsi.carplay.core.CarPlayClusterInput;
import com.mibsi.carplay.core.Mhi2qAaClusterComposition;
import com.mibsi.carplay.core.ProjectionRuntimeState;
import com.mibsi.carplay.rgd.Mhi2qAaNavProxy;

public final class CarPlayApp {
    private static volatile boolean active;
    private static volatile String activeProjection;
    private static Object requestedContext;
    private static String requestedProjection;

    private CarPlayApp() {
    }

    public static void startTransport(Object object) {
        ProjectionRuntimeState.start();
        Mhi2qAaClusterComposition.prepare(object);
        Mhi2qAaNavProxy.startTransport(object);
    }

    public static synchronized void onActivate(Object object) {
        CarPlayApp.onActivate(object, "aa");
    }

    public static synchronized void onActivate(Object object, String string) {
        requestedContext = object;
        requestedProjection = string;
        ProjectionRuntimeState.start();
        if (!ProjectionRuntimeState.isReady()) {
            active = false;
            activeProjection = "stock";
            return;
        }
        activeProjection = "carplay".equals(string) ? "carplay" : "aa";
        active = true;
        NavigationStateReader.setProjection(string);
        Mhi2qAaClusterComposition.onActivate(object);
        Mhi2qAaNavProxy.onCarPlayActivate();
        Mhi2qAaNavProxy.startTransport(object);
    }

    public static synchronized void onDeactivate() {
        requestedContext = null;
        requestedProjection = null;
        active = false;
        activeProjection = "stock";
        CarPlayClusterInput.onDeactivate();
        NavigationStateReader.setProjection("stock");
        Mhi2qAaClusterComposition.onDeactivate();
        Mhi2qAaNavProxy.onCarPlayDeactivate();
    }

    static synchronized void onRuntimeReady() {
        if (requestedProjection != null && !active) {
            CarPlayApp.onActivate(requestedContext, requestedProjection);
        }
    }

    public static void onDeactivateAndWait() {
        CarPlayApp.onDeactivate();
    }

    public static boolean isActive() {
        return active && ProjectionRuntimeState.isReady();
    }

    public static boolean isCarPlayActive() {
        return active && ProjectionRuntimeState.isReady() && "carplay".equals(activeProjection);
    }

    public static synchronized boolean openVcSettingsMenu() {
        return active && Mhi2qAaNavProxy.openVcSettingsMenu();
    }

    public static synchronized void closeVcSettingsMenu() {
        if (active) {
            Mhi2qAaNavProxy.closeVcSettingsMenu();
        }
    }

    public static synchronized void toggleVcSettingsMenu() {
        if (active) {
            Mhi2qAaNavProxy.toggleVcSettingsMenu();
        }
    }

    public static synchronized boolean moveVcSettingsSelection(int n) {
        return active && Mhi2qAaNavProxy.moveVcSettingsSelection(n);
    }

    public static synchronized boolean selectVcSettingsItem() {
        return active && Mhi2qAaNavProxy.selectVcSettingsItem();
    }

    static {
        activeProjection = "stock";
    }
}

