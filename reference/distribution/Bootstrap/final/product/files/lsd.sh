#!/bin/sh

##
## Path setup:
##

# Note: The HMI expects certain directories below this base path. If you change this,
# you may need to change the paths declared in atip.properties (contained in lsd.jxe)
# also. This can be achieved without changing the property file by supplying the paths
# via -D command line options to the JVM.
# See section "Manually set path..." for an example.
export MOUNT_DIR=
export MOUNT_APP_DIR=/mnt/app
export BASE_DIR=$MOUNT_APP_DIR/eso/hmi
export LSD_DIR=$MOUNT_DIR/ifs
export GRAPHICS_DIR=$BASE_DIR/graphics
DEV_ACTIVATED=$BASE_DIR/lsd/development_activated

# Java home:
export JAVA_HOME=$MOUNT_DIR/ifs/jre
if [[ -f $DEV_ACTIVATED ]]; then
	if [[ -d $BASE_DIR/lsd/jre ]]; then
		export JAVA_HOME=$BASE_DIR/lsd/jre
	fi
fi

#export PATH=$JAVA_HOME/bin:$PATH
#export J9="on -C 0 $JAVA_HOME/bin/j9"
export J9="on -p 12 $JAVA_HOME/bin/j9"

## hybrid lib setup
export LIBIMG_CFGFILE=$GRAPHICS_DIR/img.conf

##
## JVM general setup:
##

## Setup library paths for Java generally, J9 and system:
LD_LIBRARY_PATH=.:$GRAPHICS_DIR:$MOUNT_DIR/root/lib-target:$JAVA_HOME/bin:$BASE_DIR/lsd:$MOUNT_DIR/armle/lib:$MOUNT_DIR/armle/lib/dll:$MOUNT_DIR/armle/usr/lib:$MOUNT_DIR/eso/lib:/navigation:/navigation/lib:$MOUNT_DIR/armle/graphics

## setup the JVM direct buffer guard size to enable GUARD checking
#export JVM_DIRECT_BUFFER_GUARD_SIZE=2

#enable micro jit
VMOPTIONS="$VMOPTIONS -Xmjit:code=2000,singleCache"
VMOPTIONS="$VMOPTIONS -Xquickstart"
VMOPTIONS="$VMOPTIONS -noverify"

#Compact on every call to System.gc().
VMOPTIONS="$VMOPTIONS -Xcompactexplicitgc"

# needed for -Xmn<size>:Sets the initial and maximum size of the new (nursery) heap to the specified value when using -Xgcpolicy:gencon
VMOPTIONS="$VMOPTIONS -Xgcpolicy:gencon"
VMOPTIONS="$VMOPTIONS -Xssi4K -Xss512K"
#VMOPTIONS="$VMOPTIONS -verbose:stack"
#VMOPTIONS="$VMOPTIONS -verbose:sizes"
#VMOPTIONS="$VMOPTIONS -verbose:dynload"

VMOPTIONS="$VMOPTIONS -Djava.library.path=$LD_LIBRARY_PATH"
VMOPTIONS="$VMOPTIONS -Dcom.ibm.oti.vm.bootstrap.library.path=$LD_LIBRARY_PATH"

## JVM memory management parameters:
# IMPORTANT: whenever you make adaptions here, please inform the HMI integration team before submitting!
# There are some automated processes recognizing the exact memory phrase.
VMOPTIONS="$VMOPTIONS -Xmca16k -Xmco16k -Xmoi0 -Xmn10m -Xmo50m -Xmx60m -Xmso1m"


# removed becauso of micro-jit -Xmjit:code=512 -Xmjit:codeTotal=2048"

##
## set some env variables as system properties
##

VMOPTIONS="$VMOPTIONS -DOEM=$OEM"
VMOPTIONS="$VMOPTIONS -DREGION=$REGION"
VMOPTIONS="$VMOPTIONS -DRUN_MODE=$RUN_MODE"

##
## set up GUI resource paths
##

case $RUN_MODE in
"swdl")
        echo "Start run mode swdl, copy fonts to RAMDISK"
		cp -R $BASE_DIR/fonts /ramdisk
		VMOPTIONS="$VMOPTIONS -Dhwg.font.path=/ramdisk/fonts"

        echo "copy images to RAMDISK"
        mkdir /ramdisk/images
        cp $BASE_DIR/lsd/images/default.png /ramdisk/images
		if [ -d $BASE_DIR/lsd/images/HMISystemEvoHigh ]; then
            echo "copy EvoHigh images to RAMDISK..."
            cp -R $BASE_DIR/lsd/images/HMISystemEvoHigh /ramdisk/images
		fi
        if [ -d $BASE_DIR/lsd/images/HMISystemEvoHighMMIKombi ]; then
            echo "copy EvoHighMMIKombi images to RAMDISK..."
            cp -R $BASE_DIR/lsd/images/HMISystemEvoHighMMIKombi /ramdisk/images
        fi
        if [ -d $BASE_DIR/lsd/images/HMISystemEvoHighScale ]; then
            echo "copy EvoHighScale images to RAMDISK..."
            cp -R $BASE_DIR/lsd/images/HMISystemEvoHighScale /ramdisk/images
        fi
		if [ -d $BASE_DIR/lsd/images/HMISystemPGen2High ]; then
            echo "copy PGen2 images to RAMDISK..."
            cp -R $BASE_DIR/lsd/images/HMISystemPGen2High /ramdisk/images
		fi
        if [ -d $BASE_DIR/lsd/images/0 ]; then
            echo "copy PorscheSystem images to RAMDISK..."
            cp -R $BASE_DIR/lsd/images/0 /ramdisk/images
        fi
        if [ -d $BASE_DIR/lsd/images/17 ]; then
            echo "copy PorscheSWDL images to RAMDISK..."
            cp -R $BASE_DIR/lsd/images/17 /ramdisk/images
        fi

        echo "copy kzbs to RAMDISK"
        mkdir /ramdisk/kzbs
        if [ -d $BASE_DIR/lsd/kzbs/HMISystemEvoHigh ]; then
            echo "copy EvoHigh kzbs to RAMDISK..."
            cp -R $BASE_DIR/lsd/kzbs/HMISystemEvoHigh /ramdisk/kzbs
        fi
        if [ -d $BASE_DIR/lsd/kzbs/HMISystemEvoHighMMIKombi ]; then
            echo "copy EvoHighMMIKombi kzbs to RAMDISK..."
            cp -R $BASE_DIR/lsd/kzbs/HMISystemEvoHighMMIKombi /ramdisk/kzbs
        fi
        if [ -d $BASE_DIR/lsd/kzbs/HMISystemEvoHighScale ]; then
            echo "copy EvoHighScale kzbs to RAMDISK..."
            cp -R $BASE_DIR/lsd/kzbs/HMISystemEvoHighScale /ramdisk/kzbs
        fi
		if [ -d $BASE_DIR/lsd/kzbs/HMISystemPGen2High ]; then
            echo "copy PGen2 kzbs to RAMDISK..."
            cp -R $BASE_DIR/lsd/kzbs/HMISystemPGen2High /ramdisk/kzbs
        fi

		VMOPTIONS="$VMOPTIONS -DImageRoot=/ramdisk/images"
		VMOPTIONS="$VMOPTIONS -DKzbRoot=/ramdisk/kzbs"
        ;;
*)
		VMOPTIONS="$VMOPTIONS -Dhwg.font.path=$BASE_DIR/fonts"
		VMOPTIONS="$VMOPTIONS -DImageRoot=$BASE_DIR/lsd/images"
		VMOPTIONS="$VMOPTIONS -DKzbRoot=$BASE_DIR/lsd/kzbs"
        ;;
esac

# IMPORTANT: whenever you make adaptions here, please inform the HMI integration team before submitting!
# There are some automated processes recognizing the exact memory phrase.
VMOPTIONS="$VMOPTIONS -DealMemorySize=80"


VMOPTIONS="$VMOPTIONS -DinitialKZBs=6"
VMOPTIONS="$VMOPTIONS -DmergeSelectionDrawerOnDemand=true"

VMOPTIONS="$VMOPTIONS -DErrorDumpDir=$LOGFILES_DIR/"

VMOPTIONS="$VMOPTIONS -Dexternalized.logs.path=$BASE_DIR/lsd/ext_logs/"

# Set fallback of 'fwservices.json' from within JXE/ZIP if the \MMC3 partition is not valid
VMOPTIONS="$VMOPTIONS -Dipl.config.resource=/resources"

# paths for hmi_startup config
VMOPTIONS="$VMOPTIONS -Dipl.config.dir.hmi_startup=$BASE_DIR/lsd"

# Speller Charset Path (Pinyin conversion files can be found there in CN system)
VMOPTIONS="$VMOPTIONS -DSpellerCharacterSetPath=$BASE_DIR/lsd/SpellerCharacterSets"

## temporary settings for developement
#VMOPTIONS="$VMOPTIONS -DStorageProviderConfig=Sim"
#VMOPTIONS="$VMOPTIONS -DDefaultStorageProvider-PhysicalPath=$BASE_DIR/persistence"

## Enable Kanzi 3D support
#VMOPTIONS="$VMOPTIONS -Dshow3D=true"
#VMOPTIONS="$VMOPTIONS -Dplatform=QNX"
#VMOPTIONS="$VMOPTIONS -Ddisplayplugin"
#VMOPTIONS="$VMOPTIONS -DActivateAllCarMenus=true"
#VMOPTIONS="$VMOPTIONS -DSetCarMenusVisible=true"
#VMOPTIONS="$VMOPTIONS -DOfficial_Release=true"
#VMOPTIONS="$VMOPTIONS -DSetMenuCoding=ACC:5,INT_LIGHT:5,PARKING:1,AWV:5,LDW:5,SWA:5,EXT_LIGHT:5,WINDOW:0,AIRCONDITION:5,AUXHEATER:1,BC_CLUSTER:5,RDK:9,WIPER:5,SIA:5,SEAT:1,CENTRAL_LOCKING:5,COMPASS:0,CHARISMA:5,OILLEVEL:1,VIN:5,CLOCK:5,AIRSUSPENSION:0,HUD:0,UNITMASTER:5,HYBRID:5,UGDO:0,NIGHTVISION:0,SIDEVIEW:0,RGS:5,MFL_JOKER:5,TSD:5,ATTENTION_IDENT:0,APTIVE_KEY_CLAMP:5,MIRROR:0,DRV_SCHOOL:0,MKE:5,BCME:0"
#VMOPTIONS="$VMOPTIONS -DDebug3DToConsole=true"

VMOPTIONS="$VMOPTIONS -DSYNC_EARLY_RVC=true"
VMOPTIONS="$VMOPTIONS -DWAIT_FOR_MAP_AVAILABLE=20000"
VMOPTIONS="$VMOPTIONS -DWAIT_FOR_AUDIO_TIMEOUT=1"
VMOPTIONS="$VMOPTIONS -DWAIT_FOR_SDS_AVAILABLE=1"

#VMOPTIONS="$VMOPTIONS -Dmedia.config.usb=installed"
#VMOPTIONS="$VMOPTIONS -Dmedia.config.aux=installed"
#VMOPTIONS="$VMOPTIONS -Dmedia.config.tv=installed"

## unkomment following 2 lines for HighScale
#VMOPTIONS="$VMOPTIONS -Dmedia.config.cd=installed"
#VMOPTIONS="$VMOPTIONS -Dmedia.config.dvd=notinstalled"

## Always use StartUpConfig with all TV functions enabled.
VMOPTIONS="$VMOPTIONS -Dtv.startup.config.force.enable=true"

## Disable RRD calculation for POI name lists
#VMOPTIONS="$VMOPTIONS -DdisableRRDforPOI=true"

## Display error popup when navigation command queue was not completely executed
#VMOPTIONS="$VMOPTIONS -DActivateNaviDebugPopup=true"

## Enable NLU for SDS
VMOPTIONS="$VMOPTIONS -DenableNLU=true"

## disable SDS pause
#VMOPTIONS="$VMOPTIONS -DsdsPauseActive=false"

## enable logical popup for SDS
VMOPTIONS="$VMOPTIONS -DsdsLogicalPopup=true"

## enable external SDS (e.g. Siri)
VMOPTIONS="$VMOPTIONS -DexternalSDS=true"

## use DSITelephone/DSINAD instead of DSIMobileEquipment/DSIMobileEquipmentTopology
#VMOPTIONS="$VMOPTIONS -DuseLegacyDSITelephone=true"

#GEM Settings
VMOPTIONS="$VMOPTIONS -Dde.audi.gem.path.scriptfifo=/var"
VMOPTIONS="$VMOPTIONS -Dde.audi.gem.path.esdfiles=$BASE_DIR/engdefs"
VMOPTIONS="$VMOPTIONS -Dde.audi.gem.image.path=$BASE_DIR/lsd/images/GEM"
VMOPTIONS="$VMOPTIONS -Dde.audi.gem.font.path=$BASE_DIR/fonts/"

## Workaround to enable GEM
VMOPTIONS="$VMOPTIONS -DenableGEM=true"

## Mark as production version which disables some developer features
VMOPTIONS="$VMOPTIONS -DIS_PRODUCTION_MODE=true"

## enable import/ripping
# 1 - enabled
# 0 - disabled
#VMOPTIONS="$VMOPTIONS -DEOLFLAG_IMPORT_MEDIA_DATA=1"
#VMOPTIONS="$VMOPTIONS -DEOLFLAG_RIPPING_MEDIA_DATA=1"

#use new TTS client implementation
VMOPTIONS="$VMOPTIONS -DuseNewTtsClient=true"

## setup logging
#VMOPTIONS="$VMOPTIONS -DLOG=Fw.Startup=5,Fw.Domain=5"
VMOPTIONS="$VMOPTIONS -DLOG=all=0,Ext.Power=5"
VMOPTIONS="$VMOPTIONS -DSLOG=Ext.Startup=5,Fw.Error=2"

# to get HMI Event in a kernel trace uncomment the following line and adjust the log channel settings
#VMOPTIONS="$VMOPTIONS -DProf -DKLOG=Ext.Startup=5,Fw.Startup=5,Fw.Domain=5"

# uncomment to get the possibility to trigger an error dump by connecting to this port
VMOPTIONS="$VMOPTIONS -DErrorDumpTriggerPort=6767"

## This option is for remote debugging
#VMOPTIONS="-Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=12345 $VMOPTIONS"

## SWDL auto retries (retries without user interaction)
VMOPTIONS="$VMOPTIONS -DSWDLAutoRetries=0"

VMOPTIONS="$VMOPTIONS -Dde.audi.tghu.traceConfig=$BASE_DIR/lsd/traceConfig.properties"

#dsi tracer
DSITRACER=$BASE_DIR/lsd/DSITracer.jar

if [[ ${RUN_MODE} = "eso_screening" ]]; then
  VMOPTIONS="$VMOPTIONS -Dlsd.bundles=$BASE_DIR/lsd/bundles_headless.properties"
else
  DSITRACER_PROPFILE=$BASE_DIR/lsd/bundles_dsitracer_enabled.properties
  if [[ -f $BASE_DIR/lsd/dsitracer_activated && -f "$DSITRACER" && -f $DSITRACER_PROPFILE ]]; then
    VMOPTIONS="$VMOPTIONS -Dlsd.bundles=$DSITRACER_PROPFILE"
  else
    if [[ -f $DEV_ACTIVATED ]]; then
      VMOPTIONS="$VMOPTIONS -Dlsd.bundles=$BASE_DIR/lsd/bundles.properties"
    fi
  fi
fi

##
## Green Menu
##
VMOPTIONS="$VMOPTIONS -Dde.audi.tghu.engineering.base_dir=$BASE_DIR/engdefs"
VMOPTIONS="$VMOPTIONS -Dgreenmenu.jobs.searchLocation=sd*/home/jobs/job.mf@/fs"
VMOPTIONS="$VMOPTIONS -Dde.audi.gem.SlideShowSearchPath=sd*/slideshow/*.png@/fs"

## Path for screenshots
#VMOPTIONS="$VMOPTIONS -Dscreenshot.dirs=/fs/sda0;/fs/sdb0;/fs/usb0;/fs/usb1;/tmp"

##
## Java DSI adapter configuration
##
#VMOPTIONS="$VMOPTIONS -Dipl.config.dir=$BASE_DIR/../config/production"
#VMOPTIONS="$VMOPTIONS -Dipl.config.myProcName=hmi"


##
## Temporary: Activating all car menus
##
#VMOPTIONS="$VMOPTIONS -DActivateAllCarMenus=true"
##
## Temporary: Set coding: car menu operation flags via lsd.sh
##
#VMOPTIONS="$VMOPTIONS -DSetMenuCoding=ACC:5,INT_LIGHT:5,PARKING:1,AWV:5,LDW:5,SWA:5,EXT_LIGHT:5,WINDOW:0,AIRCONDITION:5,AUXHEATER:5,BC_CLUSTER:5,RDK:9,WIPER:5,SIA:5,SEAT:1,CENTRAL_LOCKING:5,COMPASS:0,CHARISMA:5,OILLEVEL:0,VIN:5,CLOCK:5,AIRSUSPENSION:0,HUD:0,UNITMASTER:5,HYBRID:0,UGDO:0,NIGHTVISION:0,SIDEVIEW:0,RGS:0,MFL_JOKER:5,TSD:5,ATTENTION_IDENT:0,APTIVE_KEY_CLAMP:5,MIRROR:0,DRV_SCHOOL:0,MKE:5,BCME:0"

##
## ruco2433 Hack for vehicle id
##
VMOPTIONS="$VMOPTIONS -DmyAudi.VIN=BAUIEE4HZ09012808"
VMOPTIONS="$VMOPTIONS -DmyAudi.IMSI=2620225510"

##
## Pretend BT audio is enabled via diagnosis coding option even if in fact it isn't.
##
#VMOPTIONS="$VMOPTIONS -DoverrideBtMediaDiagCode=true"

##
## Ignore speed threshold violations in Bluetooth.
##
#VMOPTIONS="$VMOPTIONS -DbtIgnoreSpeedThreshold=true"

##
## Enable the fast inquiry mode for Bluetooth device scans (inquire names only, not the services).
##
#VMOPTIONS="$VMOPTIONS -Dbluetooth.enableFastInquiry=true"

##
## Profiler remote connection
##
#VMOPTIONS="$VMOPTIONS -Xrunprof:listen=5115"

##
## Switch screen resolution
##
#VMOPTIONS="$VMOPTIONS -DScreenRes=1"
#VMOPTIONS="$VMOPTIONS -DScreenRes=4"

##
## activate combi sync protocol (DSIKombiSync) evaluation
##
#VMOPTIONS="$VMOPTIONS -DTestCombiSync=true"

##
## Disable rubberband crosshair
##
if [[ -f "$BASE_DIR/disableRubberbandCrosshair" ]]; then
	echo "######################################"
	echo "# DISABLING RUBBERBAND CROSSHAIR"
	echo "######################################"
	VMOPTIONS="$VMOPTIONS -DdisableScrollByCrosshairs=true"
fi

##
## Activate partial rendering mode.
VMOPTIONS="$VMOPTIONS -DpartialRenderingEnabled=false"
##

## Enable tryBestMatch instead of liTryMatchLocation for addresses in Navi IntelliDest
##
#VMOPTIONS="$VMOPTIONS -DuseTryBestMatch=true"

## skip all license check screens (e.g. useful to start Google Earth without valid license)
#VMOPTIONS="$VMOPTIONS -DSkipLicenseCheck=true"

##
## Setup boot class path / conditionally include DSITracer:
##
LSD_JXE=$LSD_DIR/lsd.jxe

#development mode
if [[ -f $DEV_ACTIVATED ]]; then
	echo "######################################"
	echo "#     DEVELOPER MODE ACTIVE          #"
	echo "######################################"
	echo "BASEDIR = $BASE_DIR"

    VMOPTIONS="$VMOPTIONS -Ddev_mode=true"

	##
	## hmi.zip classpath
	##
	HMI_ZIP=$BASE_DIR/lsd/hmi.zip
	if [ -f "$HMI_ZIP" ]; then
		BOOTCLASSPATH="$BOOTCLASSPATH:$HMI_ZIP"
	fi

	# use JXE from basedir (eso/hmi) if it exists
	if [[ -f $BASE_DIR/lsd/lsd.jxe ]]; then
		LSD_JXE=$BASE_DIR/lsd/lsd.jxe
	fi
fi

if [[ -f $BASE_DIR/lsd/dsitracer_activated && -f "$DSITRACER" ]]; then
        BOOTCLASSPATH="$BOOTCLASSPATH:$DSITRACER"
fi
##
## Find and append jar files
##
MIBSI_JAR_CLASSPATH=""
if [[ -d $BASE_DIR/lsd/jars ]]; then
	JARS=$(find $BASE_DIR/lsd/jars/ -name '*.zip' -or -name '*.jar')
	if [[ ! "x$JARS" == "x" ]]; then
		for jar in $JARS; do
			BOOTCLASSPATH="$BOOTCLASSPATH:$jar"
			MIBSI_JAR_CLASSPATH="${MIBSI_JAR_CLASSPATH:+$MIBSI_JAR_CLASSPATH:}$jar"
		done
	fi
fi


#HYBRID_JAR=$BASE_DIR/lsd/hybrid.jar
#if [ -f "$HYBRID_JAR" ]; then
#  BOOTCLASSPATH="$BOOTCLASSPATH:$HYBRID_JAR"
#fi

#Diagnosis output in development mode:
if [[ -f $DEV_ACTIVATED ]]; then
	echo "JAVA_HOME=$JAVA_HOME"
	echo "VMOPTIONS=$VMOPTIONS"
	echo "BOOTCLASSPATH=$BOOTCLASSPATH (plus lsd.jxe)"
	echo "LD_LIBRARY_PATH=$LD_LIBRARY_PATH"
fi
##
## Launch J9
##

# ldd $MOUNT_DIR/j9/bin/j9
# ldd ../hybrid/libhybrid.so
# j9 -help

## start j9 in background to finish ksh process.
## if the ksh is left running, this seems to cause some memory management trouble in QNX

if [[ -f $DEV_ACTIVATED && -f "$HMI_ZIP" ]]; then
	# start hmi.zip
	info "starting j9 with $HMI_ZIP"
	$J9 $VMOPTIONS -Xbootclasspath:$BOOTCLASSPATH ${MIBSI_JAR_CLASSPATH:+-Xbootclasspath/p:$MIBSI_JAR_CLASSPATH} de.dreisoft.lsd.LSD &
else
	if [[ -f "$LSD_JXE" ]]; then
		# start lsd.jxe
		BOOTCLASSPATH="$BOOTCLASSPATH:$LSD_JXE"
		info "starting j9 ...."
		$J9 $VMOPTIONS -Xbootclasspath:$BOOTCLASSPATH ${MIBSI_JAR_CLASSPATH:+-Xbootclasspath/p:$MIBSI_JAR_CLASSPATH} -jxe:$LSD_JXE &
	else
		echo !!!!!  lsd.jxe not found !!!!!
	fi
fi
