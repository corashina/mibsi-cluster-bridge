package com.mibsi.carplay.core;

import com.mibsi.carplay.rgd.Mhi2qAaNavProxy;
import de.audi.atip.interapp.combi.bap.navi.CombiBAPServiceNavi;
import de.audi.tghu.navi.app.cluster.ClusterService;

public final class ScreenNavStatusGate {
   private ScreenNavStatusGate() {
   }

   public static void onOemRgActive(boolean var0) {
      Mhi2qAaNavProxy.onOemRgActive(var0);
   }

   public static CombiBAPServiceNavi prepareCombiBAPService(ClusterService var0, CombiBAPServiceNavi var1) {
      return Mhi2qAaNavProxy.prepareCombiBAPService(var0, var1);
   }
}
