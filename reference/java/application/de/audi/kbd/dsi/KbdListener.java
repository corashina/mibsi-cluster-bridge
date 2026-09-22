package de.audi.kbd.dsi;

import com.mibsi.carplay.core.CarPlaySteeringMenuInput;
import de.audi.atip.base.IFrameworkAccess;
import de.audi.atip.hmi.event.KeyEvent;
import de.audi.atip.hmi.event.RunnableEvent;
import de.audi.atip.hmi.model.ChoiceModel;
import de.audi.atip.hmi.view.IKeyBoardManager;
import de.audi.atip.interapp.ExternalKeyListener;
import de.audi.atip.interapp.IEjectHandler;
import de.audi.atip.interapp.RSESettingsEventListener;
import de.audi.atip.interapp.SDSService;
import de.audi.atip.interapp.audio.SDISRangeListener;
import de.audi.atip.log.LogChannel;
import de.audi.atip.mmicombi.IMMICombiViewSizeSync;
import de.audi.atip.mmicombi.IViewSizeManager;
import de.audi.atip.msg.MsgListener;
import de.audi.atip.power.PowerEventListener;
import de.audi.kbd.KeyMap;
import de.audi.kbd.dsi.KbdListener.1;
import de.audi.kbd.dsi.KbdListener.VolumeLongPressTimer;
import de.esolutions.fw.util.commons.Buffer;
import de.esolutions.fw.util.commons.job.Job;
import org.dsi.ifc.keypanel.DSIKeyPanelListener;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public final class KbdListener
   implements ServiceTrackerCustomizer,
   DSIKeyPanelListener,
   ExternalKeyListener,
   PowerEventListener,
   RSESettingsEventListener,
   MsgListener {
   private static final int A3_WORKAROUND_INACTIVE = 0;
   private static final int ONE_FINGER = 1;
   private static final boolean IS_OLD_KEYPANEL = Boolean.valueOf(System.getProperty("de.audi.kbd.dsi.oldKeyPanel", "false"));
   private final IFrameworkAccess fwServices;
   private final LogChannel logChannelInput;
   private final LogChannel logChannelKeyEvent;
   private final IKeyBoardManager keyBoardManager;
   private final ComboKeyDetector comboKeyDetector;
   private volatile IMMICombiViewSizeSync iMMICombiViewSizeSync = null;
   private volatile SDSService sdsService = null;
   private volatile IEjectHandler ejectHandler = null;
   private volatile IViewSizeManager viewSizeManager = null;
   private final Object powerEventLock = new Object();
   private volatile boolean ejectPending = false;
   private boolean restrictFunctionUsage;
   private boolean childProtectionActivated;
   private boolean touchPad2Pressed = false;
   private volatile int currentKeyboardType = 0;
   private int[] powerState = new int[8];
   private volatile int previousSubClickCount;
   private boolean isHandsOnRing = false;
   private final boolean isRSE;
   private final boolean a3WorkaroundActive;
   private int currentViewSize = 2;
   private volatile boolean ignoreNextMutePressButtonFlag;
   private KbdHandler kbdHandler;
   private Job[] keyEventJobs = new Job[150];
   private static final int LONG_PRESS_TIME = 750;
   private static final int DOUBLE_PRESS_TIME = 200;
   private GestureStateMachine gestureIDcalculator;
   private final ServiceTracker tracker;
   private SDISRangeListener volumeChangerService = null;
   private final VolumeLongPressTimer volumeLongPressTimer = new VolumeLongPressTimer(this, null);
   static Class class$de$audi$atip$interapp$audio$SDISRangeListener;

   public KbdListener(IFrameworkAccess var1, IKeyBoardManager var2) {
      this.fwServices = var1;
      this.keyBoardManager = var2;
      this.logChannelInput = this.fwServices.getLogChannel("Fw.Kbd.Input");
      this.logChannelKeyEvent = this.fwServices.getLogChannel("Fw.Kbd.KeyEvent");
      this.comboKeyDetector = new ComboKeyDetector(this.fwServices, this);
      this.gestureIDcalculator = new GestureStateMachine();
      this.isRSE = !this.fwServices.isFrontMU();
      this.a3WorkaroundActive = this.fwServices.getStorageMgr().getInt(262, 20, 0) != 0;
      this.tracker = new ServiceTracker(
         this.fwServices.getBundleCxt(),
         new String[]{
            (class$de$audi$atip$interapp$audio$SDISRangeListener == null
                  ? (class$de$audi$atip$interapp$audio$SDISRangeListener = class$("de.audi.atip.interapp.audio.SDISRangeListener"))
                  : class$de$audi$atip$interapp$audio$SDISRangeListener)
               .getName()
         },
         this
      );
      this.tracker.open();
   }

   public void setKbdHandler(KbdHandler var1) {
      this.kbdHandler = var1;
   }

   private IFrameworkAccess getFramework() {
      return this.fwServices;
   }

   public int getCurrentKeyboardType() {
      return this.currentKeyboardType;
   }

   public void setIMMICombiViewSizeSync(IMMICombiViewSizeSync var1) {
      this.iMMICombiViewSizeSync = var1;
   }

   public void setSDSService(SDSService var1) {
      this.logChannelInput.log(1000000, "KbdListener.setSDSService(%1)", var1);
      this.sdsService = var1;
   }

   public void setEjectHandler(IEjectHandler var1) {
      this.logChannelInput.log(1000000, "KbdListener.setEjectHandler(%1)", var1);
      boolean var2 = false;
      synchronized (this) {
         this.ejectHandler = var1;
         if (var1 != null && this.ejectPending) {
            var2 = true;
            this.ejectPending = false;
         }
      }

      if (var2) {
         this.logChannelInput.log(1000000, "KbdListener.setEjectHandler: Forward pending eject command to eject handler (service=%1)", var1);
         var1.ejectCD();
      }
   }

   public void triggerHmiKeyPressedEvent(int var1, int var2) {
      this.logChannelKeyEvent.log(10000000, "KbdListener.triggerHmiKeyPressedEvent(%1, %2)", var1, var2);
      this.keyBoardManager.fireKeyEvent(10401, this.fwServices.getMonotonicTime(), var1, var2);
   }

   public void triggerKeyPressedEvent(int var1, int var2) {
      this.logChannelKeyEvent.log(10000000, "KbdListener.triggerKeyPressedEvent(%1, %2)", var1, var2);
      this.fireKeyPressedEvent(var1, var2);
   }

   public void triggerKeyReleasedEvent(int var1, int var2) {
      this.logChannelKeyEvent.log(10000000, "KbdListener.triggerKeyReleasedEvent(%1, %2)", var1, var2);
      this.fireKeyReleasedEvent(var1, var2);
   }

   public void notifyPowerListenerOnEnterState(int var1, int var2) {
      this.logChannelInput.log(1000000, "KbdListener.notifyPowerListenerOnEnterState(%1, %2)", var1, var2);
      synchronized (this.powerEventLock) {
         this.powerState[var2] = var1;
      }
   }

   public void notifyPowerListenerOnExitState(int var1, int var2) {
   }

   public void notifyPowerTriggerAction(int var1, int var2) {
   }

   public void updateClampState(boolean var1, boolean var2, boolean var3, boolean var4) {
      this.logChannelInput.log(1000000, "KbdListener.updateClampState(clamp15=%1)", var2);
   }

   public void processAudioUsageRestriction(boolean var1) {
   }

   public void processTunerUsageRestriction(boolean var1) {
   }

   public void processTVUsageRestriction(boolean var1) {
   }

   public void processCDCUsageRestriction(boolean var1) {
   }

   public void processFunctionUsageRestriction(boolean var1) {
      this.logChannelInput.log(1000000, "KbdListener.processFunctionUsageRestriction(%1)", var1);
      this.restrictFunctionUsage = var1;
   }

   public void processChildProtection(boolean var1) {
      this.logChannelInput.log(1000000, "KbdListener.processChildProtection(%1)", var1);
      this.childProtectionActivated = var1;
   }

   public void asyncException(int var1, String var2, int var3) {
      this.logChannelInput.log(10000, "KbdListener.asyncException(errorCode=%1, requestType=%2)", var1, var3);
      this.logChannelInput.log(10000, "KbdListener.asyncException(errorMsg=%1)", var2);
   }

   public void updateKey2(int var1, int var2, int var3, int var4, int var5) {
      this.logChannelInput.log(100000, "KbdListener.updateKey2(keyboardID=%1, keyID=%2, keyState=%3)", var1, var2, var3);
      this.logChannelInput.log(100000, "KbdListener.updateKey2(time=%1, validFlag=%2)", var4, var5);
      if (var5 == 1) {
         if (var2 == 105) {
            if (var3 == 6) {
               this.isHandsOnRing = true;
            } else if (var3 == 7) {
               this.isHandsOnRing = false;
            }

            this.logChannelInput.log(10000000, "KbdListener.updateKey2(isHandsOnRing=%1)", this.isHandsOnRing);
         }

         this.updateKeyInternal(var1, var2, var3);
      } else {
         if (var1 == 13 && var5 == 2 && (var2 == 61 || var2 == 62 || var2 == 63 || var2 == 64 || var2 == 65 || var2 == 66 || var2 == 101 || var2 == 102)) {
            this.updateKeyInternal(var1, var2, 7);
         }
      }
   }

   public void supplyExternalKey(int var1, int var2, int var3, int var4) {
      this.logChannelInput.log(100000, "KbdListener.supplyExternalKey(keyboardID=%1, keyID=%2, keyState=%3)", var1, var2, var3);
      this.logChannelInput.log(100000, "KbdListener.supplyExternalKey(validFlag=%1)", var4);
      if (var4 == 1) {
         this.updateKeyInternal(var1, var2, var3);
      }
   }

   private void fireGestureEvent(int var1, int var2, int var3, int var4, int var5, int var6, long var7, int var9) {
      int var10 = KeyMap.getHmiGestureKeyCode(var1);
      if (var10 == 10901) {
         this.logChannelKeyEvent.log(100000, "KbdListener.fireGestureEvent: Ignore input key! (keyCode=%1)", var1);
      } else {
         this.keyBoardManager.fireGestureEvent(var10, var2, var3, var4, var5, var6, var7, var9);
      }
   }

   private void updateGesture(int var1, int var2, int var3, int var4, int var5) {
      this.logChannelInput.log(100000, "KbdListener.updateGesture(keyboardID=%1, gestureId=%2, validFlag=%3)", var1, var2, var5);
      this.logChannelInput.log(100000, "KbdListener.updateGesture(x=%1, y=%2)", var3, var4);
      if (var5 == 1 && !this.getFramework().isPorsche()) {
         switch (var2) {
            case 0:
            case 12:
               this.fireTouchEvent(10914, var3, var4, 1, KeyMap.kbdID2termID(var1));
               break;
            case 1:
               this.fireTouchEvent(10906, 0, 0, 1, KeyMap.kbdID2termID(var1));
               break;
            case 2:
               this.fireTouchEvent(10907, 0, 0, 1, KeyMap.kbdID2termID(var1));
               break;
            case 3:
               this.fireTouchEvent(10905, var3, var4, 1, KeyMap.kbdID2termID(var1));
               break;
            case 4:
               this.fireTouchEvent(10904, var3, var4, 1, KeyMap.kbdID2termID(var1));
               break;
            case 5:
               this.fireTouchEvent(10912, var3, var4, 1, KeyMap.kbdID2termID(var1));
            case 6:
            case 7:
            case 10:
            case 11:
            default:
               break;
            case 8:
            case 13:
               this.fireTouchEvent(10911, var3, var4, 1, KeyMap.kbdID2termID(var1));
               break;
            case 9:
               this.fireTouchEvent(10913, var3, var4, 1, KeyMap.kbdID2termID(var1));
         }
      }
   }

   public void updateDisplayTurnMechStatus(int var1, int var2) {
      this.logChannelInput.log(100000, "KbdListener.updateDisplayTurnMechStatus(displayTurnMechStatus=%1, validFlag=%2)", var1, var2);
   }

   public void updateRecognizerMode(int var1, int var2, int var3) {
      this.logChannelInput.log(10000000, "KbdListener.updateRecognizerMode(keyboardID=%1, recognizerMode=%2, validFlag=%3)", var1, var2, var3);
   }

   public void updateProximity(int var1, int var2, int var3) {
      this.logChannelInput.log(100000, "KbdListener.updateProximity(keyboardID=%1, distance=%2, validFlag=%3)", var1, var2, var3);
      if (var3 == 1) {
         this.fireProximityEvent(var2, KeyMap.kbdID2termID(var1));
      }
   }

   public void genericSettingResponse(int var1, int var2, int var3) {
      this.logChannelInput.log(10000000, "KbdListener.genericSettingResponse(respKeyboardID=%1, respKey=%2, respValue=%3)", var1, var2, var3);
      this.kbdHandler.setGenericSettingResponse(var2, var3);
   }

   public void lastKey(int var1, int var2, int var3) {
   }

   public void updateEncoder2(int var1, int var2, int var3, int var4, int var5) {
      this.logChannelInput.log(100000, "KbdListener.updateEncoder2(keyboardID=%1, keyID=%2, incCount=%3)", var1, var2, var3);
      this.logChannelInput.log(100000, "KbdListener.updateEncoder2(time=%1, validFlag=%2)", var4, var5);
      if (var2 == 5) {
         byte var6 = (byte)var3;
         byte var7 = (byte)(var3 >> 8);
         this.logChannelInput.log(100000, "KbdListener.updateEncoder2: hDDS event: mainClicks=%1 and subClicks=%2", var6, var7);
         if (this.isHandsOnRing) {
            this.updateEncoderInternal(var1, 16, var6, var7, var5);
         } else if (var6 != 0) {
            this.logChannelInput.log(100000, "KbdListener.updateEncoder2: Hands are not on ring - do not propagate sublicks");
            this.updateEncoderInternal(var1, 16, var6, 0, var5);
         } else {
            this.logChannelInput.log(100000, "KbdListener.updateEncoder2: Ignore updateEncoder because it only has subClicks an hand is not on Ring");
         }
      } else {
         this.updateEncoderInternal(var1, var2, var3, var5);
      }
   }

   public void updateRecognizerLanguage2(int var1, String var2, int var3, int var4) {
      this.logChannelInput.log(10000000, "KbdListener.updateRecognizerLanguage2(keyboardID=%1, validFlag=%2)", var1, var4);
      this.logChannelInput.log(10000000, "KbdListener.updateRecognizerLanguage2(language=%1, langCode=%2)", var2, var3);
   }

   public void updateCharacterEvent2(int var1, String[] var2, int[] var3, int var4) {
      this.logChannelInput.log(100000, "KbdListener.updateCharacterEvent2(keyboardID=%1, validFlag=%2)", var1, var4);
      this.logChannelInput.log(100000, "KbdListener.updateCharacterEvent2(recognizedCharacters=%1)", var2);
      this.logChannelInput.log(100000, "KbdListener.updateCharacterEvent2(confidence=%1)", var3);
      if (var4 == 1) {
         if (this.getFramework().isPorsche()) {
            if (var2 != null && var3 != null) {
               this.fireRecognizedGestureEvent(var2, var3, KeyMap.kbdID2termID(var1));
            } else {
               this.logChannelInput.log(100000, "KbdListener.updateCharacterEvent2: recognizedCharacters or confidence are null - discard");
            }
         } else if (var2 != null && var3 != null) {
            Buffer var5 = new Buffer(var2.length);
            int var6 = Math.min(var2.length, var3.length);

            for (int var7 = 0; var7 < var6; var7++) {
               String var8 = var2[var7];
               if (var8 != null) {
                  if (var8.length() == 1) {
                     var5.append(var8);
                  } else if (var8.length() > 1) {
                     this.logChannelInput
                        .log(
                           100000,
                           "KbdListener.updateCharacterEvent2: The keypanel recognized multiple character result. They will be mapped. recognizedString=%1",
                           var8
                        );
                     char var9 = this.mapCharacter(var8);
                     var5.append(var9);
                  }
               }
            }

            if (var5.length() == 0 && var2.length > 0 && var2[0] != null) {
               var5.append(var2[0]);
            }

            int[] var10 = new int[var5.length()];
            System.arraycopy(var3, 0, var10, 0, var5.length());
            this.fireRecognizedTouchEvent(var5.toString(), var10, KeyMap.kbdID2termID(var1));
         } else {
            this.logChannelInput.log(10000, "KbdListener.updateCharacterEvent2: recognizedCharacters or confidence are null - sending empty event");
            this.fireRecognizedTouchEvent("isNativeLittleEndian", null, KeyMap.kbdID2termID(var1));
         }
      }
   }

   private char mapCharacter(String var1) {
      if (var1.equals("لا")) {
         return 'ﻻ';
      } else if (var1.equals("لآ")) {
         return 'ﻵ';
      } else if (var1.equals("لأ")) {
         return 'ﻷ';
      } else {
         return var1.equals("لإ") ? 'ﻹ' : var1.charAt(0);
      }
   }

   public void updateGesture2(int var1, int var2, int var3, boolean var4, int var5, int var6, int var7, int var8, int var9, int var10) {
      if (this.logChannelInput.isDebug()) {
         Buffer var11 = new Buffer(144);
         var11.append("keyboardID=").append(var1).append(' ');
         var11.append("gestureID=").append(var2).append(' ');
         var11.append("fingerCount=").append(var3).append(' ');
         var11.append("palm=").append(var4).append(' ');
         var11.append("x=").append(var5).append(' ');
         var11.append("y=").append(var6).append(' ');
         var11.append("param1(angle)=").append(var7).append(' ');
         var11.append("param2(distance)=").append(var8).append(' ');
         var11.append("time=").append(var9).append(' ');
         var11.append("validFlag=").append(var10).append(' ');
         this.logChannelInput.log(10000000, "KbdListener.updateGesture2( %1)", var11);
      }

      if (var10 == 1) {
         int var14 = var7;
         int var12 = var8;
         if (this.getFramework().isPorsche()) {
            int var13 = KeyMap.kbdID2termID(var1);
            if (this.powerstateNoDisplay(var13)) {
               this.reactivateDisplay(var1, 33, var13);
            } else {
               this.fireGestureEvent(var2, var3, var5, var6, var7, var8, var9, KeyMap.kbdID2termID(var1));
            }
         } else if (this.kbdHandler.getCurrentKeyboardType() == 15) {
            this.updateGesture(var1, var2, var5, var6, var10);
         } else if (var1 == 9) {
            if (this.touchPad2Pressed) {
               if (var3 == 0) {
                  this.fireTouchEvent(10905, var5, var6, var3, var4, var14, var12, var9, KeyMap.kbdID2termID(var1));
                  this.touchPad2Pressed = false;
               } else {
                  this.fireTouchEvent(10911, var5, var6, var3, var4, var14, var12, var9, KeyMap.kbdID2termID(var1));
               }
            } else if (var3 > 0) {
               this.fireTouchEvent(10904, var5, var6, var3, var4, var14, var12, var9, KeyMap.kbdID2termID(var1));
               this.touchPad2Pressed = true;
            }
         } else if (var1 == 13) {
            int var15 = this.gestureIDcalculator.calculateGestureID(var3, var5, var6);
            this.fireGestureEvent(var15, var3, var5, var6, var7, var8, var9, KeyMap.kbdID2termID(var1));
         }
      }
   }

   public void updateKeyboardType(int var1, int var2) {
      System.out.println("KbdListener.updateKeyboardType(keyboardType=" + var1 + ", validFlag=" + var2 + ")");
      this.logChannelInput.log(10000000, "KbdListener.updateKeyboardType(keyboardType=%1, validFlag=%2)", var1, var2);
      if (var2 == 1) {
         this.currentKeyboardType = var1;
         this.kbdHandler.updateRecognizerConfig();

         try {
            ChoiceModel var3 = (ChoiceModel)this.getFramework().getHMIService().getModel(4076);
            if (var3 != null) {
               var3.setValue(this.currentKeyboardType);
            }
         } catch (Exception var4) {
            this.logChannelInput.log(10000, "KbdListener#updateKeyboardType exception=%1", var4.toString());
         }
      }
   }

   public void updateTouchSensitiveArea(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.logChannelInput.log(1000000, "KbdListener.updateTouchSensitiveArea( keybordID=%1, x=%2, y=%3 )", var1, var2, var3);
      this.logChannelInput.log(1000000, "KbdListener.updateTouchSensitiveArea( width=%1, height=%2, validFlag=%3 )", var4, var5, var6);
      this.keyBoardManager.fireGestureEvent(10914, 0, var2, var3, var4, var5, -1L, KeyMap.kbdID2termID(var1));
   }

   public void updateInputPanelReady(int var1, int var2, int var3) {
      this.logChannelInput.log(1000000, "KbdListener.updateInputPanelReady(keyboardID=%1, startupState=%2, validFlag=%3)", var1, var2, var3);
      if (var3 == 1) {
         this.kbdHandler.updateRecognizerConfig();
      }
   }

   public void getVersionInfo(int var1, int var2, String var3) {
      this.logChannelInput.log(1000000, "KbdListener.getVersionInfo(keyboardID=%2, versionInfo=%3, version=%1)", var3, var1, var2);
   }

   private void handleSimulateMIB2JUpJDownViaSESW(int var1, int var2, int var3, int var4) {
      if (var1 == 1 && var3 == 1) {
         if (var2 == 9) {
            this.fireJoystickEvent(28, var4);
         } else if (var2 == 12) {
            this.fireJoystickEvent(24, var4);
         }
      }
   }

   private synchronized void updateKeyInternal(int var1, int var2, int var3) {
      CarPlaySteeringMenuInput.onKeyState(var1, var2, var3);
      if (KeyMap.isVolumeKey(var2) && this.ignoreNextMutePressButtonFlag) {
         this.ignoreNextMutePressButtonFlag = false;
      } else {
         int var4 = KeyMap.kbdID2termID(var1);
         boolean var5 = this.standBy(var4);
         if (IS_OLD_KEYPANEL && !var5) {
            this.handleSimulateMIB2JUpJDownViaSESW(var1, var2, var3, var4);
         }

         if (this.checkMFWFilters(var1, var2, var5)) {
            if (this.isMIB2HighMMICombi() && (var2 == 37 || var2 == 36)) {
               if (this.sdsService != null) {
                  this.logChannelInput.log(100000, "KbdListener.updateKeyInternal: abort sds session upon arrow left/right");
                  this.sdsService.abortSDSSession(false, (byte)2);
               }

               if (this.viewSizeManager != null) {
                  long var12 = this.fwServices.getMonotonicTime();
                  RunnableEvent var13 = new RunnableEvent(false, new 1(this, var3, var12));
                  this.fwServices.getHMIService().getEventDispatcher().postEvent(var13);
               }
            }
         } else {
            if (this.a3WorkaroundActive && this.isMIB2HighMMICombi()) {
               if (var2 == 14) {
                  if (var3 == 1) {
                     this.toggleViewSize();
                  }

                  return;
               }

               if (var2 == 10) {
                  var2 = 78;
               }
            }

            switch (var3) {
               case 0:
                  if (var2 == 42 || var2 == 43) {
                     VolumeLongPressTimer.access$200(this.volumeLongPressTimer);
                  } else if (var1 == 5) {
                     this.fireJoystickEvent(var2, var4);
                  } else if (!this.comboKeyDetector.keyReleasedConsumed(var2, var4)) {
                     if (var2 == 50 && this.fwServices.isPorsche()) {
                        Job var11 = this.keyEventJobs[var2];
                        if (var11 != null) {
                           var11.cancel();
                        }
                     }

                     this.fireKeyReleasedEvent(var2, var4);
                  }
                  break;
               case 1:
                  if (var2 == 33) {
                     this.fireKeyPressedEvent(var2, var4);
                  } else if (var2 == 32) {
                     this.handleEject();
                  } else if (var1 == 5 || var1 == 6 || var1 == 7) {
                     this.fireJoystickEvent(var2, var4);
                  } else if (var1 == 9 && var2 >= 23 && var2 <= 31) {
                     this.fireJoystickEvent(var2, var4);
                  } else if (var2 == 42) {
                     if (this.volumeChangerService != null) {
                        this.logChannelInput.log(10000000, "KbdListener#updateKeyInternal() - Changing (inc) volume via SDISRangeListener.");
                        this.volumeChangerService.increment(-1, 1, var4);
                     } else {
                        this.logChannelInput
                           .log(100000, "KbdListener#updateKeyInternal() - No SDISRangeListener available, changing (inc) volume via Fallback Implementation.");
                        this.fireWheelButtonEvent(16, 0, 1, KeyMap.kbdID2termID(var1));
                     }
                  } else if (var2 == 43) {
                     if (this.volumeChangerService != null) {
                        this.logChannelInput.log(10000000, "KbdListener#updateEncoderInternal() - Changing (inc) volume via SDISRangeListener.");
                        this.volumeChangerService.decrement(-1, 1, var4);
                     } else {
                        this.logChannelInput
                           .log(
                              100000,
                              "KbdListener#updateEncoderInternal() - No SDISRangeListener available, changing (inc) volume via Fallback Implementation."
                           );
                        this.fireWheelButtonEvent(16, 1, 1, KeyMap.kbdID2termID(var1));
                     }
                  } else {
                     if (var5) {
                        if (this.fwServices.isFrontMU() && KeyMap.isVolumeKey(var2)) {
                           this.logChannelInput.log(100000, "KbdListener.updateKeyInternal: Ignore volume key on main unit during standby!");
                           break;
                        }
                     } else if (this.powerstateNoDisplay(var4) && (var2 == 13 || var2 == 16)) {
                        this.logChannelInput.log(100000, "KbdListener.updateKeyInternal: Ignore back key if power state is HMI_ON_NO_DISPLAY!");
                        break;
                     }

                     if (!var5 && this.comboKeyDetector.keyPressedConsumed(var2, var4)) {
                        break;
                     }

                     this.fireKeyPressedEvent(var2, var4);
                     if (var2 == 50 && this.fwServices.isPorsche()) {
                        Job var6 = this.keyEventJobs[var2];
                        if (var6 != null) {
                           KeyEvent var7 = (KeyEvent)var6.getPayload();
                           long var8 = var7.getWhen() - 750L;
                           if (this.fwServices.getMonotonicTime() - var8 < 200L) {
                              this.fireKeyDoublePressedEvent(var2, var4);
                           }
                        }

                        var6 = this.fireKeyLongPressedEvent(var2, 750L, var4);
                        this.keyEventJobs[var2] = var6;
                     }
                  }
                  break;
               case 2:
                  if (var2 == 50) {
                     this.fireKeyDoublePressedEvent(var2, var4);
                  } else {
                     this.fireKeyPressedEvent(var2, var4);
                  }
                  break;
               case 3:
                  if (var2 == 42) {
                     VolumeLongPressTimer.access$300(this.volumeLongPressTimer, 0, var1);
                  } else if (var2 == 43) {
                     VolumeLongPressTimer.access$300(this.volumeLongPressTimer, 1, var1);
                  }

                  this.fireKeyLongPressedEvent(var2, 0L, var4);
               case 4:
               case 5:
               default:
                  break;
               case 6:
                  if (var1 == 9 || var1 == 1 || var1 == 13) {
                     this.fireTouchEvent(10909, var2, 0, 0, 0, var4);
                  }
                  break;
               case 7:
                  if (var1 == 9 || var1 == 1 || var1 == 13) {
                     this.fireTouchEvent(10910, var2, 0, 0, 0, var4);
                  }
            }

            if (this.checkPowerManagementTriggerDisplayButton(var2, var3)) {
               this.reactivateDisplay(var1, var2, var4);
            }

            if (this.checkPowerManagementTriggerHardKey(var2, var3)) {
               this.triggerHK(var1, var2, var4);
            }

            if (this.powerstateNoDisplay(var4) && this.checkPowerManagementTriggerSoftKey(var2, var3)) {
               this.triggerHK(var1, var2, var4);
            }
         }
      }
   }

   private void updateEncoderInternal(int var1, int var2, int var3, int var4) {
      this.updateEncoderInternal(var1, var2, var3, 0, var4);
   }

   private void updateEncoderInternal(int var1, int var2, int var3, int var4, int var5) {
      if (var5 == 1) {
         if (this.restrictFunctionUsage && this.isRSE && !KeyMap.isVolumeKey(var2)) {
            this.logChannelInput.log(100000, "KbdListener.updateEncoderInternal:  Function restriction is active! Ignore key event! (keyID=%1)", var2);
         } else {
            int var6 = KeyMap.kbdID2termID(var1);
            if (!this.checkMFWFilters(var1, var2, this.standBy(var6))) {
               switch (var2) {
                  case 16:
                     if (this.powerstateNoDisplay(var6)) {
                        this.logChannelInput.log(10000000, "KbdListener.updateEncoderInternal: Ignore DDS key if power state is HMI_ON_NO_DISPLAY!");
                        this.reactivateDisplay(var1, var2, var6);
                     } else if (var3 > 0) {
                        this.previousSubClickCount = var4;
                        this.fireWheelButtonEvent(17, 0, var3, var4, var6);
                     } else if (var3 < 0) {
                        this.previousSubClickCount = var4;
                        this.fireWheelButtonEvent(17, 1, -1 * var3, var4, var6);
                     } else {
                        boolean var7 = this.previousSubClickCount < var4;
                        this.previousSubClickCount = var4;
                        int var8 = var7 ? 0 : 1;
                        this.fireWheelButtonEvent(17, var8, var3, var4, var6);
                     }
                     break;
                  case 17:
                  case 44:
                  case 88:
                     if (var3 > 0) {
                        if (this.volumeChangerService != null) {
                           this.logChannelInput.log(10000000, "KbdListener#updateEncoderInternal() - Changing (inc) volume via SDISRangeListener.");
                           this.volumeChangerService.increment(-1, var3, var6);
                        } else {
                           this.logChannelInput
                              .log(
                                 100000,
                                 "KbdListener#updateEncoderInternal() - No SDISRangeListener available, changing (inc) volume via Fallback Implementation."
                              );
                           this.fireWheelButtonEvent(16, 0, var3, var6);
                        }
                     } else if (this.volumeChangerService != null) {
                        this.logChannelInput.log(10000000, "KbdListener#updateEncoderInternal() - Changing (dec) volume via SDISRangeListener.");
                        this.volumeChangerService.decrement(-1, -var3, var6);
                     } else {
                        this.logChannelInput
                           .log(
                              100000,
                              "KbdListener#updateEncoderInternal() - No SDISRangeListener available, changing (dec) volume via Fallback Implementation."
                           );
                        this.fireWheelButtonEvent(16, 1, -1 * var3, var6);
                     }
                     break;
                  case 40:
                     if (var3 > 0) {
                        this.fireWheelButtonEvent(20, 1, var3, var6);
                     } else {
                        this.fireWheelButtonEvent(20, 0, -1 * var3, var6);
                     }
                     break;
                  case 76:
                     if (var3 > 127) {
                        this.fireWheelButtonEvent(64, 1, 256 - var3, KeyMap.kbdID2termID(var1));
                     } else {
                        this.fireWheelButtonEvent(64, 0, var3, KeyMap.kbdID2termID(var1));
                     }
                     break;
                  case 77:
                     if (var3 > 127) {
                        this.fireWheelButtonEvent(65, 1, 256 - var3, KeyMap.kbdID2termID(var1));
                     } else {
                        this.fireWheelButtonEvent(65, 0, var3, KeyMap.kbdID2termID(var1));
                     }
               }
            }
         }
      }
   }

   private void toggleViewSize() {
      if (this.iMMICombiViewSizeSync != null) {
         if (this.currentViewSize == 1) {
            this.currentViewSize = 2;
         } else {
            this.currentViewSize = 1;
         }

         this.logChannelInput.log(10000000, "KbdListener.toggleViewSize:  Trigger change of HMI view size! (size=%1)", this.currentViewSize);
         this.iMMICombiViewSizeSync.requestViewSize(this.currentViewSize);
      } else {
         this.logChannelInput
            .log(10000000, "KbdListener.toggleViewSize:  No change of HMI view size possible! Service IMMICombiViewSizeSync is not available!");
      }
   }

   private void handleEject() {
      boolean var2 = false;
      IEjectHandler var1;
      synchronized (this) {
         var1 = this.ejectHandler;
         this.ejectPending = var1 == null;
         var2 = !this.ejectPending;
      }

      if (var2) {
         this.logChannelInput.log(10000000, "KbdListener.handleEject: Forward eject command to eject handler! (service=%1)", var1);
         var1.ejectCD();
      }
   }

   private void reactivateDisplay(int var1, int var2, int var3) {
      if (var1 == 1 || var1 == 10 || var1 == 2 || var1 == 3 || var1 == 13) {
         this.logChannelInput.log(10000000, "KbdListener.reactivateDisplay: Trigger power management! (terminal=%1, keyID=%2)", var3, var2);
         this.fwServices.getPowerMgr().displayButtonTyped(var3, var2);
      }
   }

   private void triggerHK(int var1, int var2, int var3) {
      if (var1 == 1 || var1 == 10 || var1 == 2 || var1 == 3 || var1 == 13) {
         this.logChannelInput.log(10000000, "KbdListener.triggerHK: Trigger power management! (terminal=%1, keyID=%2)", var3, var2);
         this.fwServices.getPowerMgr().hardKeyTyped(var3, var2);
      }
   }

   private boolean standBy(int var1) {
      if (var1 >= 0 && var1 < 8) {
         switch (this.getPowerState(var1)) {
            case 2:
               return true;
            default:
               return false;
         }
      } else {
         return false;
      }
   }

   private boolean checkPowerManagementTriggerDisplayButton(int var1, int var2) {
      boolean var3 = false;
      if (var1 == 33 && var2 == 1) {
         var3 = true;
      }

      return var3;
   }

   private boolean checkPowerManagementTriggerHardKey(int var1, int var2) {
      boolean var3 = false;
      if (var2 == 0) {
         switch (var1) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 15:
            case 68:
            case 78:
            case 86:
            case 103:
            case 104:
               var3 = true;
               break;
            case 7:
               var3 = !this.fwServices.isPorsche();
         }
      }

      return var3;
   }

   private boolean checkPowerManagementTriggerSoftKey(int var1, int var2) {
      boolean var3 = false;
      if (var2 == 0) {
         switch (var1) {
            case 8:
            case 9:
            case 11:
            case 12:
            case 13:
            case 16:
            case 97:
            case 98:
               var3 = true;
         }
      }

      return var3;
   }

   private boolean powerstateNoDisplay(int var1) {
      if (var1 >= 0 && var1 < 8) {
         switch (this.getPowerState(var1)) {
            case 1:
               return true;
            default:
               return false;
         }
      } else {
         return false;
      }
   }

   private int getPowerState(int var1) {
      synchronized (this.powerEventLock) {
         return this.powerState[var1];
      }
   }

   private void fireTouchEvent(int var1, int var2, int var3, int var4, int var5, int var6) {
      int var7 = KeyMap.getHmiKeyCode(var2);
      if (var7 == 0) {
         this.logChannelKeyEvent.log(100000, "KbdListener.fireTouchEvent: Ignore input key! (keyCode=%1)", var2);
      } else {
         this.keyBoardManager.fireTouchEvent(var1, this.fwServices.getMonotonicTime(), var7, var3, var4, var5, var6);
      }
   }

   private void fireTouchEvent(int var1, int var2, int var3, int var4, int var5) {
      this.keyBoardManager.fireTouchEvent(var1, this.fwServices.getMonotonicTime(), 0, var2, var3, var4, var5);
   }

   private void fireTouchEvent(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      this.keyBoardManager.fireTouchEvent(var1, this.fwServices.getMonotonicTime(), 0, var2, var3, var4, var5, var6, var7, var8);
   }

   private void fireTouchEvent(int var1, int var2, int var3, int var4, boolean var5, int var6, int var7, int var8, int var9) {
      this.keyBoardManager.fireTouchEvent(var1, this.fwServices.getMonotonicTime(), 0, var2, var3, var4, var5, var6, var7, var8, var9);
   }

   private void fireRecognizedGestureEvent(String[] var1, int[] var2, int var3) {
      this.keyBoardManager.fireGestureEvent(10910, this.fwServices.getMonotonicTime(), 0, 0, var1, var2, var3);
   }

   private void fireRecognizedTouchEvent(String var1, int[] var2, int var3) {
      this.keyBoardManager.fireTouchEvent(10908, this.fwServices.getMonotonicTime(), 0, 0, 0, var1, var2, var3);
   }

   private void fireKeyPressedEvent(int var1, int var2) {
      int var3 = KeyMap.getHmiKeyCode(var1);
      if (var3 == 0) {
         this.logChannelKeyEvent.log(100000, "KbdListener.fireKeyPressedEvent: Ignore input key! (keyCode=%1)", var1);
      } else {
         this.abortSDSsession(var3);
         if (var1 != 33) {
            this.keyBoardManager.fireKeyEvent(10401, this.fwServices.getMonotonicTime(), var3, var2);
         }
      }
   }

   private Job fireKeyLongPressedEvent(int var1, long var2, int var4) {
      if (var1 == 50) {
         return this.keyBoardManager.fireKeyEvent(10401, this.fwServices.getMonotonicTime(), 23, var2, var4);
      } else {
         return KeyMap.getHmiKeyCode(var1) == 50 ? this.keyBoardManager.fireKeyEvent(10401, this.fwServices.getMonotonicTime(), 81, var4) : null;
      }
   }

   private void fireKeyDoublePressedEvent(int var1, int var2) {
      if (var1 == 50) {
         this.keyBoardManager.fireKeyEvent(10401, this.fwServices.getMonotonicTime(), 28, var2);
      }
   }

   private void fireKeyReleasedEvent(int var1, int var2) {
      int var3 = KeyMap.getHmiKeyCode(var1);
      if (var3 == 0) {
         this.logChannelKeyEvent.log(100000, "KbdListener.fireKeyReleasedEvent: Ignore input key! (keyCode=%1)", var1);
      } else {
         this.keyBoardManager.fireKeyEvent(10402, this.fwServices.getMonotonicTime(), var3, var2);
      }
   }

   private void fireWheelButtonEvent(int var1, int var2, int var3, int var4, int var5) {
      this.keyBoardManager.fireWheelButtonEvent(10403, this.fwServices.getMonotonicTime(), var1, var2, var3, var4, var5);
   }

   private void fireWheelButtonEvent(int var1, int var2, int var3, int var4) {
      this.keyBoardManager.fireWheelButtonEvent(10403, this.fwServices.getMonotonicTime(), var1, var2, var3, var4);
   }

   private void fireJoystickEvent(int var1, int var2) {
      int var3 = KeyMap.joystickKey2EventID(var1);
      this.keyBoardManager.fireJoyStickEvent(10404, this.fwServices.getMonotonicTime(), 22, var3, var2);
   }

   private void fireProximityEvent(int var1, int var2) {
      this.keyBoardManager.fireProximityEvent(var1, var2);
   }

   private void abortSDSsession(int var1) {
      if (this.sdsService != null && KeyMap.isSdsAbortKey(var1)) {
         this.logChannelInput.log(10000000, "KbdListener.abortSDSsession: Forward abort command! (service=%1)", this.sdsService);
         this.sdsService.abortSDSSession(false, (byte)2);
      }
   }

   private boolean checkMFWFilters(int var1, int var2, boolean var3) {
      if (this.isMFWBoard(var1) && var3) {
         this.logChannelInput.log(100000, "KbdListener.checkMFWFilters: Ignore MFW keys during standby!");
         return true;
      }

      if (this.isMIB2HighMMICombi() || this.fwServices.isShowDDP2Combi() || var2 != 99 && var2 != 100 && var2 != 40) {
         if (var2 == 37) {
            this.logChannelInput.log(100000, "KbdListener.checkMFWFilters: Ignore MFW left arrow!");
            return true;
         } else if (var2 == 36) {
            this.logChannelInput.log(100000, "KbdListener.checkMFWFilters: Ignore MFW right arrow!");
            return true;
         } else {
            return false;
         }
      } else {
         this.logChannelInput
            .log(
               100000, "KbdListener.checkMFWFilters: Ignore MFW side menu keys and MFW left roller for all systems apart from MIB2HighMMICombi and DDP2Combi!"
            );
         return true;
      }
   }

   private boolean isMFWBoard(int var1) {
      return var1 == 4 || var1 == 8;
   }

   private boolean isMIB2HighMMICombi() {
      return this.fwServices.getKombiType() == 4;
   }

   public void blinkInfo(String var1) {
      this.getFramework().getHMIService().showVisualFeedback(3000L, var1, 0);
   }

   public void blinkScreen() {
      this.getFramework().getHMIService().showVisualFeedback(1000L, null, 0);
   }

   public String getName() {
      return "KbdListener";
   }

   public String[] getData() {
      return new String[0];
   }

   public void setIgnoreNextMutePressButtonFlag() {
      this.logChannelInput.log(10000000, "KbdListener.setIgnoreNextMutePressButtonFlag()");
      this.ignoreNextMutePressButtonFlag = true;
   }

   public void setViewSizeManager(IViewSizeManager var1) {
      this.viewSizeManager = var1;
   }

   public void getProperty(int var1, int var2, int var3, int var4, byte[] var5) {
   }

   public void updateAdvancedProximity(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10) {
   }

   public void processMsg(int var1) {
      switch (var1) {
         case 101:
            ChoiceModel var2 = (ChoiceModel)this.getFramework().getHMIService().getModel(4076);
            if (var2 != null) {
               var2.setValue(this.currentKeyboardType);
            }
      }
   }

   public Object addingService(ServiceReference var1) {
      Object var2 = this.fwServices.getBundleCxt().getService(var1);
      if (var2 instanceof SDISRangeListener) {
         this.logChannelInput.log(10000000, "KbdListener#addingService() - SDISRangeListener has been registered.");
         this.volumeChangerService = (SDISRangeListener)var2;
      }

      return var2;
   }

   public void modifiedService(ServiceReference var1, Object var2) {
   }

   public void removedService(ServiceReference var1, Object var2) {
      this.fwServices.getBundleCxt().ungetService(var1);
      if (var2 instanceof SDISRangeListener) {
         this.logChannelInput.log(10000000, "KbdListener#removedService() - SDISRangeListener has been unregistered.");
         this.volumeChangerService = null;
      }
   }
}
