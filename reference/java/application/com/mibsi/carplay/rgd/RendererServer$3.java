package com.mibsi.carplay.rgd;

import java.io.InputStream;
import java.net.Socket;

class RendererServer$3 implements Runnable {
   RendererServer$3(RendererServer var1, Socket var2, InputStream var3) {
      this.this$0 = var1;
      this.val$s = var2;
      this.val$i = var3;
   }

   public void run() {
      RendererServer.access$200(this.this$0, this.val$s, this.val$i);
   }
}
