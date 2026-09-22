package com.mibsi.carplay.aa;

public final class NavigationSnapshot {
   public String mode = "aa";
   public int sequence = -1;
   public long updated;
   public boolean active;
   public int maneuver;
   public int roundaboutExit;
   public int roundaboutAngle;
   public int distanceMeters;
   public int timeToStepSeconds;
   public int destinationDistanceMeters = -1;
   public int timeToDestinationSeconds = -1;
   public String currentRoad = "";
   public String nextRoad = "";
   public String instruction = "";
   public byte[] laneGuidance = new byte[0];
}
