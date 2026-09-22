/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.audi.atip.interapp.combi.bap.navi.CombiBAPServiceNavi
 */
package com.mibsi.carplay.core;

import com.mibsi.carplay.rgd.Mhi2qAaNavProxy;
import de.audi.atip.interapp.combi.bap.navi.CombiBAPServiceNavi;
import de.audi.tghu.navi.app.cluster.ClusterService;

public final class ScreenNavStatusGate {
    private ScreenNavStatusGate() {
    }

    public static void onOemRgActive(boolean bl) {
        Mhi2qAaNavProxy.onOemRgActive(bl);
    }

    public static CombiBAPServiceNavi prepareCombiBAPService(ClusterService clusterService, CombiBAPServiceNavi combiBAPServiceNavi) {
        return Mhi2qAaNavProxy.prepareCombiBAPService(clusterService, combiBAPServiceNavi);
    }
}

