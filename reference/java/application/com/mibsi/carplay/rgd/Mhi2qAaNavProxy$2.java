package com.mibsi.carplay.rgd;

import com.mibsi.carplay.core.Mhi2qAaClusterComposition;

final class Mhi2qAaNavProxy$2 implements Mhi2qAaClusterComposition.PresentationListener {
   public boolean onTurnCardPresented(int var1, int var2, int var3, int var4, int var5, int var6) {
      return Mhi2qAaNavProxy.access$300().sendPresented(var2, var3, var4, var5, var6);
   }

   public boolean onTurnCardHidden(int var1) {
      return !Mhi2qAaNavProxy.access$400() && !Mhi2qAaNavProxy.access$500() ? Mhi2qAaNavProxy.access$300().sendClear() : true;
   }

   public boolean onVcMenuPresented(int var1, int var2, int var3, int var4, int var5, int var6) {
      return Mhi2qAaNavProxy.access$300().sendPresented(var2, var3, var4, var5, var6);
   }

   public boolean onVcMenuHidden(int var1) {
      return Mhi2qAaNavProxy.access$300().sendVcMenu(false, Mhi2qAaNavProxy.access$600(), Mhi2qAaNavProxy.access$700(), 0);
   }
}
