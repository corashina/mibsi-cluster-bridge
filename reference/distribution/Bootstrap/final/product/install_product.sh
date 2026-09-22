#!/bin/sh
# P5092 binary profile. Save replaced files, including third-party modifications.
SUPPORTED_TRAIN="MHI2Q_ER_AUG22_P5092"
SUPPORTED_TRAIN_ALIAS="MIB2Q_ER_AUG22_P5092"
SUPPORTED_TRAIN_P5152="MHI2Q_ER_AUG22_P5152"
SUPPORTED_MU="1329"
VERSION_FILE="/net/rcc/dev/shmem/version.txt"
VOLUME="$1"
PRODUCT_DIR="$VOLUME/Bootstrap/final/product"
TOOL="$PRODUCT_DIR/files/mibsi-install-tool"
LSD_SHA="8cd5994945fca9d983b268baa05c6ecad597fd2f99feaa2e83e831533e16fb48"
RECEIVER_SHA="6ef4abea8264ab7787b0ecb3fe278a65488301f703148d0175010f7b228bdaf6"
AIRPLAY_SHA="01c116b1674215daa0602d556a6d9b2eb48c2113ca294ea1f5d58787e3f656f2"
APP_RW=0
SYSTEM_RW=0
NODE_NAME=""
if test -x /bin/uname; then NODE_NAME=$(/bin/uname -n 2>/dev/null); fi
case "$NODE_NAME" in
    mmx|mmx.*) RUNNING_ON_RCC=0 ;;
    *) RUNNING_ON_RCC=1 ;;
esac

run_mmx()
{
    if test "$RUNNING_ON_RCC" -eq 1; then on -f mmx "$@"; else "$@"; fi
}

cleanup()
{
    CLEANUP_RESULT=0
    if test "$SYSTEM_RW" = 1; then
        if run_mmx /bin/mount -ur /mnt/system; then SYSTEM_RW=0; else CLEANUP_RESULT=1; fi
    fi
    if test "$APP_RW" = 1; then
        if run_mmx /bin/mount -ur /mnt/app; then APP_RW=0; else CLEANUP_RESULT=1; fi
    fi
    return "$CLEANUP_RESULT"
}

fail()
{
    echo "MIBSI: operation refusee: $*"
    exit 60
}
trap cleanup 0
trap 'exit 70' 1 2 15

if test -z "$VOLUME" || test ! -f "$PRODUCT_DIR/release.env" || test ! -x "$TOOL"; then
    fail "payload SD incomplet"
fi
. "$PRODUCT_DIR/release.env"
UNINSTALL_MARKER=0
FORCE_MARKER=0
if test -f "$VOLUME/MIBSI-UNINSTALL"; then UNINSTALL_MARKER=1; fi
if test -f "$VOLUME/MIBSI-FORCE"; then FORCE_MARKER=1; fi
case "$MIBSI_OPERATION:$UNINSTALL_MARKER:$FORCE_MARKER" in
    install:0:0|force-install:0:1) OPERATION=install ;;
    uninstall:1:0|force-uninstall:1:1) OPERATION=uninstall ;;
    *) fail "marqueurs SD incoherents" ;;
esac

if test ! -f "$VERSION_FILE" ||
   { ! grep "Current train = '$SUPPORTED_TRAIN'" "$VERSION_FILE" >/dev/null 2>&1 &&
     ! grep "Current train = '$SUPPORTED_TRAIN_ALIAS'" "$VERSION_FILE" >/dev/null 2>&1 &&
     ! grep "Current train = '$SUPPORTED_TRAIN_P5152'" "$VERSION_FILE" >/dev/null 2>&1; } ||
   ! grep "MainUnit version = '$SUPPORTED_MU'" "$VERSION_FILE" >/dev/null 2>&1; then
    fail "firmware hors liste Europe P5092/P5152 MU1329"
fi
# These dependencies are NOT replaced: hooks still require their exact ABI.
run_mmx "$TOOL" verify "$LSD_SHA" /ifs/lsd.jxe || fail "lsd.jxe incompatible"
run_mmx "$TOOL" verify "$RECEIVER_SHA" /eso/lib/libautoreceiver.so ||
    fail "libautoreceiver incompatible"
run_mmx "$TOOL" verify "$AIRPLAY_SHA" /eso/lib/libairplay.so ||
    fail "libairplay incompatible"
run_mmx "$TOOL" check-payload "$PRODUCT_DIR" || fail "payload SD altere"

# Optional garage package: refuse unknown/unreadable VIN before any writable
# product mount or transaction, including force-install. Recovery stays free.
if test "$OPERATION" = install && test "${MIBSI_VIN_NATIVE:-0}" = 1; then
    run_mmx "$TOOL" check-vin ||
        fail "VIN non autorise ou lecture VIN impossible"
fi

# No predecessor allowlist: native lifecycle captures bytes, mode, owner,
# symlink target and absence for EVERY managed path before publication.
run_mmx /bin/mount -uw /mnt/app || fail "montage app impossible"
APP_RW=1
run_mmx /bin/mount -uw /mnt/system || fail "montage system impossible"
SYSTEM_RW=1
run_mmx "$TOOL" lifecycle "$OPERATION" "$PRODUCT_DIR" "$MIBSI_RELEASE_ID"
RESULT=$?
run_mmx /bin/sync
if ! cleanup; then fail "remontage en lecture seule impossible"; fi
if test "$RESULT" -ne 0; then
    fail "operation non terminee ($RESULT); conserver la carte SD et relancer"
fi
echo "MIBSI: $OPERATION termine et relu; redemarrage MMI normal requis"
exit 0
