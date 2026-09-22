/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.audi.app.terminalmode.IContext
 *  de.audi.app.terminalmode.ITerminalModeComponent
 *  de.audi.app.terminalmode.audio.AudioConnectionState
 *  de.audi.app.terminalmode.audio.IAudioStateListener
 *  de.audi.app.terminalmode.combi.NullCombiBAPService
 *  de.audi.app.terminalmode.device.IActiveDeviceStateListener
 *  de.audi.app.terminalmode.device.TMDevice
 *  de.audi.app.terminalmode.device.TMDevice$ConnectionState
 *  de.audi.app.terminalmode.events.DefaultEventListener
 *  de.audi.app.terminalmode.events.IEventListener
 *  de.audi.app.terminalmode.events.TrackDataChangedEvent
 *  de.audi.app.terminalmode.events.TrackDataChangedEvent$Builder
 *  de.audi.app.terminalmode.events.TrackPlayPositionEvent
 *  de.audi.app.terminalmode.osgi.IServiceManager
 *  de.audi.app.terminalmode.osgi.IServiceTracker
 *  de.audi.atip.interapp.bap.data.TimeStamp
 *  de.audi.atip.interapp.combi.bap.audio.CombiBAPServiceTerminalMode
 *  de.audi.atip.interapp.combi.bap.audio.data.CombiBAPCurrentStationInfo
 *  de.audi.atip.interapp.combi.bap.audio.data.PlayPosition
 *  de.audi.atip.interapp.def.DefaultServiceTrackerCustomizer
 *  de.audi.atip.log.LogChannel
 *  org.osgi.framework.BundleContext
 *  org.osgi.framework.ServiceReference
 *  org.osgi.util.tracker.ServiceTrackerCustomizer
 */
package de.audi.app.terminalmode.combi;

import com.mibsi.carplay.core.CarPlayApp;
import de.audi.app.terminalmode.IContext;
import de.audi.app.terminalmode.ITerminalModeComponent;
import de.audi.app.terminalmode.audio.AudioConnectionState;
import de.audi.app.terminalmode.audio.IAudioStateListener;
import de.audi.app.terminalmode.combi.NullCombiBAPService;
import de.audi.app.terminalmode.device.IActiveDeviceStateListener;
import de.audi.app.terminalmode.device.TMDevice;
import de.audi.app.terminalmode.events.DefaultEventListener;
import de.audi.app.terminalmode.events.IEventListener;
import de.audi.app.terminalmode.events.TrackDataChangedEvent;
import de.audi.app.terminalmode.events.TrackPlayPositionEvent;
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
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class TerminalModeBapCombi
implements ITerminalModeComponent {
    private static final String LOGCLASS = "TerminalModeBapCombi";
    private final IContext context;
    private final BundleContext bundleContext;
    private final LogChannel lc;
    private final IServiceTracker serviceTracker;
    private final IServiceManager serviceManager;
    private final EventListener eventListener;
    private final CachingEventListener cachingEventListener;
    private volatile CombiBAPServiceTerminalMode bapService;
    private ActiveDeviceStateListener activeDeviceListener;
    private final IAudioStateListener audioStateListener;

    public TerminalModeBapCombi(IContext iContext) {
        this.context = iContext;
        this.bundleContext = this.context.getFramework().getBundleCxt();
        this.lc = this.context.getLogger().main();
        this.bapService = new NullCombiBAPService(this.lc);
        this.serviceManager = this.context.getServiceManager();
        this.serviceTracker = this.serviceManager.createServiceTracker(CombiBAPServiceTerminalMode.class, (ServiceTrackerCustomizer)new CombiBapServiceTrackerCustomizer(this));
        this.eventListener = new EventListener(this);
        this.cachingEventListener = new CachingEventListener(this);
        this.activeDeviceListener = new ActiveDeviceStateListener(this);
        this.audioStateListener = new AudioStateListener(this);
    }

    public void init() {
        this.serviceTracker.open();
        this.context.getEventBus().registerListener((IEventListener)this.cachingEventListener);
        this.context.getDeviceManager().addActiveDeviceListener((IActiveDeviceStateListener)this.activeDeviceListener);
        this.context.getAudioManager().addAudioContextListener(this.audioStateListener);
        try {
            CarPlayApp.startTransport(this.context);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    public void deinit() {
        try {
            CarPlayApp.onDeactivateAndWait();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        this.serviceTracker.close();
        this.context.getEventBus().unregisterListener((IEventListener)this.eventListener);
        this.context.getEventBus().unregisterListener((IEventListener)this.cachingEventListener);
        this.context.getDeviceManager().removeActiveDeviceListener((IActiveDeviceStateListener)this.activeDeviceListener);
        this.context.getAudioManager().removeAudioContextListener(this.audioStateListener);
    }

    private class EventListener
    extends DefaultEventListener {
        private final TerminalModeBapCombi this$0;

        private EventListener(TerminalModeBapCombi terminalModeBapCombi2) {
            this.this$0 = terminalModeBapCombi2;
        }

        public void updateNowPlayingData(TrackDataChangedEvent trackDataChangedEvent) {
            String string = trackDataChangedEvent.getTitle();
            String string2 = trackDataChangedEvent.getArtist();
            String string3 = trackDataChangedEvent.getAlbum();
            if (string == null) {
                string = "";
            }
            if (string2 == null) {
                string2 = "";
            }
            if (string3 == null) {
                string3 = "";
            }
            int n = string.length() > 0 ? 6 : 0;
            int n2 = string2.length() > 0 ? 73 : 0;
            int n3 = string3.length() > 0 ? 74 : 0;
            CombiBAPCurrentStationInfo combiBAPCurrentStationInfo = new CombiBAPCurrentStationInfo();
            combiBAPCurrentStationInfo.setPrimaryInformation(string, n, 0);
            combiBAPCurrentStationInfo.setSecondaryInformation(string2, n2);
            combiBAPCurrentStationInfo.setTertiaryInformation(string3, n3);
            this.this$0.bapService.updateCurrentStation(combiBAPCurrentStationInfo);
            this.this$0.bapService.updateActiveInfoState(0);
        }

        public void updatePlayPosition(TrackPlayPositionEvent trackPlayPositionEvent) {
            if (trackPlayPositionEvent.getTotalTimeOfTrack() == 0) {
                this.this$0.bapService.updatePlayPosition(PlayPosition.builder().setTimePosition(TimeStamp.getInstanceFromSeconds((int)65535)).setTotalPlayTime(TimeStamp.getInstanceFromSeconds((int)65535)).build());
            } else {
                this.this$0.bapService.updatePlayPosition(PlayPosition.builder().setTimePosition(TimeStamp.getInstanceFromSeconds((int)trackPlayPositionEvent.getPlayTime())).setTotalPlayTime(TimeStamp.getInstanceFromSeconds((int)trackPlayPositionEvent.getTotalTimeOfTrack())).build());
            }
        }
    }

    private final class CombiBapServiceTrackerCustomizer
    extends DefaultServiceTrackerCustomizer {
        private final TerminalModeBapCombi this$0;

        private CombiBapServiceTrackerCustomizer(TerminalModeBapCombi terminalModeBapCombi2) {
            this.this$0 = terminalModeBapCombi2;
        }

        public Object addingService(ServiceReference serviceReference) {
            CombiBAPServiceTerminalMode combiBAPServiceTerminalMode = (CombiBAPServiceTerminalMode)this.this$0.context.getServiceManager().getService(serviceReference);
            if (combiBAPServiceTerminalMode != null) {
                this.this$0.bapService = combiBAPServiceTerminalMode;
            }
            return combiBAPServiceTerminalMode;
        }

        public void removedService(ServiceReference serviceReference, Object object) {
            this.this$0.bapService = (CombiBAPServiceTerminalMode)new NullCombiBAPService(this.this$0.lc);
            this.this$0.bundleContext.ungetService(serviceReference);
        }
    }

    private class CachingEventListener
    extends DefaultEventListener {
        private final TrackPlayPositionEvent INVALID_TIME;
        private final TrackDataChangedEvent INVALID_TRACK;
        private TrackPlayPositionEvent lastPlayPositionEvent;
        private TrackDataChangedEvent lastTrackData;
        private final TerminalModeBapCombi this$0;

        private CachingEventListener(TerminalModeBapCombi terminalModeBapCombi2) {
            this.this$0 = terminalModeBapCombi2;
            this.INVALID_TIME = new TrackPlayPositionEvent(-1, 0);
            this.INVALID_TRACK = new TrackDataChangedEvent.Builder().build();
            this.lastPlayPositionEvent = this.INVALID_TIME;
            this.lastTrackData = this.INVALID_TRACK;
        }

        public void updateNowPlayingData(TrackDataChangedEvent trackDataChangedEvent) {
            this.lastTrackData = trackDataChangedEvent;
        }

        public void updatePlayPosition(TrackPlayPositionEvent trackPlayPositionEvent) {
            this.lastPlayPositionEvent = trackPlayPositionEvent;
        }

        public void sendCachedEvents() {
            if (!this.INVALID_TIME.equals((Object)this.lastPlayPositionEvent)) {
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

    private final class AudioStateListener
    implements IAudioStateListener {
        private final TerminalModeBapCombi this$0;

        private AudioStateListener(TerminalModeBapCombi terminalModeBapCombi2) {
            this.this$0 = terminalModeBapCombi2;
        }

        public void audioStateChanged(AudioConnectionState audioConnectionState) {
        }

        public void audioFocusChanged(boolean bl) {
            if (bl) {
                this.this$0.context.getEventBus().registerListener((IEventListener)this.this$0.eventListener);
                this.this$0.cachingEventListener.sendCachedEvents();
            } else {
                this.this$0.context.getEventBus().unregisterListener((IEventListener)this.this$0.eventListener);
            }
        }
    }

    private final class ActiveDeviceStateListener
    implements IActiveDeviceStateListener {
        private final TerminalModeBapCombi this$0;

        private ActiveDeviceStateListener(TerminalModeBapCombi terminalModeBapCombi2) {
            this.this$0 = terminalModeBapCombi2;
        }

        public void updateActiveDeviceState(TMDevice tMDevice) {
            if (tMDevice.connectionState().is((Object)TMDevice.ConnectionState.ACTIVATING)) {
                this.this$0.bapService.updateActiveInfoState(9);
                int n = this.getBapSourceType(tMDevice);
                this.this$0.bapService.updateActiveSource(n, 0, 0, false, false, 0);
                this.this$0.bapService.updateCurrentStation(new CombiBAPCurrentStationInfo());
                this.this$0.bapService.updatePlayPosition(PlayPosition.builder().setTimePosition(TimeStamp.getInstanceFromSeconds((int)65535)).setTotalPlayTime(TimeStamp.getInstanceFromSeconds((int)65535)).build());
                this.this$0.bapService.updateActiveInfoState(9);
                if (tMDevice.isAndroidAutoDevice() || tMDevice.isCarplayDevice()) {
                    try {
                        CarPlayApp.onActivate(this.this$0.context, tMDevice.isCarplayDevice() ? "carplay" : "aa");
                    }
                    catch (Throwable throwable) {
                        // empty catch block
                    }
                }
            }
            if (tMDevice.connectionState().is((Object)TMDevice.ConnectionState.ACTIVE) && (tMDevice.isAndroidAutoDevice() || tMDevice.isCarplayDevice()) && !CarPlayApp.isActive()) {
                try {
                    CarPlayApp.onActivate(this.this$0.context, tMDevice.isCarplayDevice() ? "carplay" : "aa");
                }
                catch (Throwable throwable) {
                    // empty catch block
                }
            }
            if (!tMDevice.isAndroidAutoDevice() && !tMDevice.isCarplayDevice() && CarPlayApp.isActive()) {
                try {
                    CarPlayApp.onDeactivate();
                }
                catch (Throwable throwable) {
                    // empty catch block
                }
            }
        }

        private int getBapSourceType(TMDevice tMDevice) {
            if (tMDevice.isCarplayDevice()) {
                return 37;
            }
            return tMDevice.isAndroidAutoDevice() ? 39 : 40;
        }
    }
}

