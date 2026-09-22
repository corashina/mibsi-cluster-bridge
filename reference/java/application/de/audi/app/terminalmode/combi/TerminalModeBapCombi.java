package de.audi.app.terminalmode.combi;

import com.mibsi.carplay.core.CarPlayApp;
import de.audi.app.terminalmode.IContext;
import de.audi.app.terminalmode.ITerminalModeComponent;
import de.audi.app.terminalmode.audio.AudioConnectionState;
import de.audi.app.terminalmode.audio.IAudioStateListener;
import de.audi.app.terminalmode.device.IActiveDeviceStateListener;
import de.audi.app.terminalmode.device.TMDevice;
import de.audi.app.terminalmode.device.TMDevice.ConnectionState;
import de.audi.app.terminalmode.events.DefaultEventListener;
import de.audi.app.terminalmode.events.TrackDataChangedEvent;
import de.audi.app.terminalmode.events.TrackPlayPositionEvent;
import de.audi.app.terminalmode.events.TrackDataChangedEvent.Builder;
import de.audi.app.terminalmode.osgi.IServiceManager;
import de.audi.app.terminalmode.osgi.IServiceTracker;
import de.audi.atip.interapp.bap.data.TimeStamp;
import de.audi.atip.interapp.combi.bap.audio.CombiBAPServiceTerminalMode;
import de.audi.atip.interapp.combi.bap.audio.data.CombiBAPCurrentStationInfo;
import de.audi.atip.interapp.combi.bap.audio.data.PlayPosition;
import de.audi.atip.interapp.def.DefaultServiceTrackerCustomizer;
import de.audi.atip.log.LogChannel;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class TerminalModeBapCombi implements ITerminalModeComponent {
   private static final String LOGCLASS = "TerminalModeBapCombi";
   private final IContext context;
   private final BundleContext bundleContext;
   private final LogChannel lc;
   private final IServiceTracker serviceTracker;
   private final IServiceManager serviceManager;
   private final TerminalModeBapCombi.EventListener eventListener;
   private final TerminalModeBapCombi.CachingEventListener cachingEventListener;
   private volatile CombiBAPServiceTerminalMode bapService;
   private TerminalModeBapCombi.ActiveDeviceStateListener activeDeviceListener;
   private final IAudioStateListener audioStateListener;

   public TerminalModeBapCombi(IContext var1) {
      this.context = var1;
      this.bundleContext = this.context.getFramework().getBundleCxt();
      this.lc = this.context.getLogger().main();
      this.bapService = new NullCombiBAPService(this.lc);
      this.serviceManager = this.context.getServiceManager();
      this.serviceTracker = this.serviceManager
         .createServiceTracker(CombiBAPServiceTerminalMode.class, new TerminalModeBapCombi.CombiBapServiceTrackerCustomizer(this, null));
      this.eventListener = new TerminalModeBapCombi.EventListener(this, null);
      this.cachingEventListener = new TerminalModeBapCombi.CachingEventListener(this, null);
      this.activeDeviceListener = new TerminalModeBapCombi.ActiveDeviceStateListener(this, null);
      this.audioStateListener = new TerminalModeBapCombi.AudioStateListener(this, null);
   }

   public void init() {
      this.serviceTracker.open();
      this.context.getEventBus().registerListener(this.cachingEventListener);
      this.context.getDeviceManager().addActiveDeviceListener(this.activeDeviceListener);
      this.context.getAudioManager().addAudioContextListener(this.audioStateListener);

      try {
         CarPlayApp.startTransport(this.context);
      } catch (Throwable var2) {
      }
   }

   public void deinit() {
      try {
         CarPlayApp.onDeactivateAndWait();
      } catch (Throwable var2) {
      }

      this.serviceTracker.close();
      this.context.getEventBus().unregisterListener(this.eventListener);
      this.context.getEventBus().unregisterListener(this.cachingEventListener);
      this.context.getDeviceManager().removeActiveDeviceListener(this.activeDeviceListener);
      this.context.getAudioManager().removeAudioContextListener(this.audioStateListener);
   }

   private final class ActiveDeviceStateListener implements IActiveDeviceStateListener {
      private final TerminalModeBapCombi this$0;

      private ActiveDeviceStateListener(TerminalModeBapCombi var2) {
         this.this$0 = var2;
      }

      public void updateActiveDeviceState(TMDevice var1) {
         if (var1.connectionState().is(ConnectionState.ACTIVATING)) {
            this.this$0.bapService.updateActiveInfoState(9);
            int var2 = this.getBapSourceType(var1);
            this.this$0.bapService.updateActiveSource(var2, 0, 0, false, false, 0);
            this.this$0.bapService.updateCurrentStation(new CombiBAPCurrentStationInfo());
            this.this$0
               .bapService
               .updatePlayPosition(
                  PlayPosition.builder()
                     .setTimePosition(TimeStamp.getInstanceFromSeconds(65535))
                     .setTotalPlayTime(TimeStamp.getInstanceFromSeconds(65535))
                     .build()
               );
            this.this$0.bapService.updateActiveInfoState(9);
            if (var1.isAndroidAutoDevice() || var1.isCarplayDevice()) {
               try {
                  CarPlayApp.onActivate(this.this$0.context, var1.isCarplayDevice() ? "carplay" : "aa");
               } catch (Throwable var6) {
               }
            }
         }

         if (var1.connectionState().is(ConnectionState.ACTIVE) && (var1.isAndroidAutoDevice() || var1.isCarplayDevice()) && !CarPlayApp.isActive()) {
            try {
               CarPlayApp.onActivate(this.this$0.context, var1.isCarplayDevice() ? "carplay" : "aa");
            } catch (Throwable var5) {
            }
         }

         if (!var1.isAndroidAutoDevice() && !var1.isCarplayDevice() && CarPlayApp.isActive()) {
            try {
               CarPlayApp.onDeactivate();
            } catch (Throwable var4) {
            }
         }
      }

      private int getBapSourceType(TMDevice var1) {
         if (var1.isCarplayDevice()) {
            return 37;
         } else {
            return var1.isAndroidAutoDevice() ? 39 : 40;
         }
      }
   }

   private final class AudioStateListener implements IAudioStateListener {
      private final TerminalModeBapCombi this$0;

      private AudioStateListener(TerminalModeBapCombi var2) {
         this.this$0 = var2;
      }

      public void audioStateChanged(AudioConnectionState var1) {
      }

      public void audioFocusChanged(boolean var1) {
         if (var1) {
            this.this$0.context.getEventBus().registerListener(this.this$0.eventListener);
            this.this$0.cachingEventListener.sendCachedEvents();
         } else {
            this.this$0.context.getEventBus().unregisterListener(this.this$0.eventListener);
         }
      }
   }

   private class CachingEventListener extends DefaultEventListener {
      private final TrackPlayPositionEvent INVALID_TIME;
      private final TrackDataChangedEvent INVALID_TRACK;
      private TrackPlayPositionEvent lastPlayPositionEvent;
      private TrackDataChangedEvent lastTrackData;
      private final TerminalModeBapCombi this$0;

      private CachingEventListener(TerminalModeBapCombi var2) {
         this.this$0 = var2;
         this.INVALID_TIME = new TrackPlayPositionEvent(-1, 0);
         this.INVALID_TRACK = new Builder().build();
         this.lastPlayPositionEvent = this.INVALID_TIME;
         this.lastTrackData = this.INVALID_TRACK;
      }

      public void updateNowPlayingData(TrackDataChangedEvent var1) {
         this.lastTrackData = var1;
      }

      public void updatePlayPosition(TrackPlayPositionEvent var1) {
         this.lastPlayPositionEvent = var1;
      }

      public void sendCachedEvents() {
         if (!this.INVALID_TIME.equals(this.lastPlayPositionEvent)) {
            this.this$0.eventListener.updatePlayPosition(this.lastPlayPositionEvent);
         }

         if (!this.INVALID_TRACK.equals(this.lastTrackData)) {
            this.this$0.eventListener.updateNowPlayingData(this.lastTrackData);
         }
      }

      public void clearCachedEvents() {
         this.lastPlayPositionEvent = this.INVALID_TIME;
         this.lastTrackData = this.INVALID_TRACK;
      }
   }

   private final class CombiBapServiceTrackerCustomizer extends DefaultServiceTrackerCustomizer {
      private final TerminalModeBapCombi this$0;

      private CombiBapServiceTrackerCustomizer(TerminalModeBapCombi var2) {
         this.this$0 = var2;
      }

      public Object addingService(ServiceReference var1) {
         CombiBAPServiceTerminalMode var2 = (CombiBAPServiceTerminalMode)this.this$0.context.getServiceManager().getService(var1);
         if (var2 != null) {
            this.this$0.bapService = var2;
         }

         return var2;
      }

      public void removedService(ServiceReference var1, Object var2) {
         this.this$0.bapService = new NullCombiBAPService(this.this$0.lc);
         this.this$0.bundleContext.ungetService(var1);
      }
   }

   private class EventListener extends DefaultEventListener {
      private final TerminalModeBapCombi this$0;

      private EventListener(TerminalModeBapCombi var2) {
         this.this$0 = var2;
      }

      public void updateNowPlayingData(TrackDataChangedEvent var1) {
         String var2 = var1.getTitle();
         String var3 = var1.getArtist();
         String var4 = var1.getAlbum();
         if (var2 == null) {
            var2 = "";
         }

         if (var3 == null) {
            var3 = "";
         }

         if (var4 == null) {
            var4 = "";
         }

         int var5 = var2.length() > 0 ? 6 : 0;
         int var6 = var3.length() > 0 ? 73 : 0;
         int var7 = var4.length() > 0 ? 74 : 0;
         CombiBAPCurrentStationInfo var8 = new CombiBAPCurrentStationInfo();
         var8.setPrimaryInformation(var2, var5, 0);
         var8.setSecondaryInformation(var3, var6);
         var8.setTertiaryInformation(var4, var7);
         this.this$0.bapService.updateCurrentStation(var8);
         this.this$0.bapService.updateActiveInfoState(0);
      }

      public void updatePlayPosition(TrackPlayPositionEvent var1) {
         if (var1.getTotalTimeOfTrack() == 0) {
            this.this$0
               .bapService
               .updatePlayPosition(
                  PlayPosition.builder()
                     .setTimePosition(TimeStamp.getInstanceFromSeconds(65535))
                     .setTotalPlayTime(TimeStamp.getInstanceFromSeconds(65535))
                     .build()
               );
         } else {
            this.this$0
               .bapService
               .updatePlayPosition(
                  PlayPosition.builder()
                     .setTimePosition(TimeStamp.getInstanceFromSeconds(var1.getPlayTime()))
                     .setTotalPlayTime(TimeStamp.getInstanceFromSeconds(var1.getTotalTimeOfTrack()))
                     .build()
               );
         }
      }
   }
}
