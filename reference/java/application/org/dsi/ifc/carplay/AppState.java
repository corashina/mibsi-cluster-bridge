package org.dsi.ifc.carplay;

public class AppState {
   public int appStateID;
   public int owner;
   public int speechMode;

   public AppState() {
      this.appStateID = 0;
      this.owner = 0;
      this.speechMode = 0;
   }

   public AppState(int var1, int var2, int var3) {
      this.appStateID = var1;
      this.owner = var2;
      this.speechMode = var3;
   }

   public int getAppStateID() {
      return 0;
   }

   public int getOwner() {
      return 0;
   }

   public int getSpeechMode() {
      return this.speechMode;
   }

   public String toString() {
      StringBuffer var1 = new StringBuffer(150);
      var1.append("AppState");
      var1.append('(');
      var1.append("appStateID");
      var1.append('=');
      var1.append(this.appStateID);
      var1.append(',');
      var1.append("owner");
      var1.append('=');
      var1.append(this.owner);
      var1.append(',');
      var1.append("speechMode");
      var1.append('=');
      var1.append(this.speechMode);
      var1.append(')');
      return var1.toString();
   }
}
