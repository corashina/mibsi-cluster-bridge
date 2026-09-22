#!/bin/sh

SUPPORTED_FAMILY_MHI2="MHI2_"
SUPPORTED_FAMILY_MHI2Q="MHI2Q_"
VERSION_FILE="/net/rcc/dev/shmem/version.txt"
VOLUME="$1"
SCRIPT_DIR="/mnt/app/eso/hmi/engdefs/scripts/mibsi/v1"
MENU_FILE="/mnt/app/eso/hmi/engdefs/mibsi-main.esd"
MENU_STAGE="/mnt/app/eso/hmi/engdefs/mibsi-main.esd.new"
NODE_NAME=""
if test -x /bin/uname; then
    NODE_NAME=$(/bin/uname -n 2>/dev/null)
fi
case "$NODE_NAME" in
mmx|mmx.*)
    RUNNING_ON_RCC=0
    MMX_PREFIX=""
    ;;
*)
    RUNNING_ON_RCC=1
    MMX_PREFIX="/net/mmx"
    ;;
esac

run_mmx()
{
    if test "$RUNNING_ON_RCC" -eq 1; then
        on -f mmx "$@"
    else
        "$@"
    fi
}

is_supported_family()
{
    grep "$SUPPORTED_FAMILY_MHI2" "$VERSION_FILE" >/dev/null 2>&1 ||
        grep "$SUPPORTED_FAMILY_MHI2Q" "$VERSION_FILE" >/dev/null 2>&1
}

if test -z "$VOLUME"; then
    echo "MIBSI bootstrap: volume absent"
    exit 30
fi

if test ! -f "$VERSION_FILE"; then
    echo "MIBSI bootstrap: identite firmware indisponible"
    exit 31
fi

if ! is_supported_family; then
    echo "MIBSI bootstrap: famille MHI2/MHI2Q absente"
    exit 32
fi

if test ! -f "$MMX_PREFIX$MENU_STAGE"; then
    echo "MIBSI bootstrap: menu SWDL en staging absent"
    exit 33
fi

echo "MIBSI bootstrap: montage application en ecriture"
run_mmx /bin/mount -uw /mnt/app || exit 34

run_mmx /bin/mkdir -p "$SCRIPT_DIR" || {
    run_mmx /bin/mount -ur /mnt/app
    exit 35
}

run_mmx /bin/cp "$VOLUME/Bootstrap/scripts/status.sh" "$SCRIPT_DIR/status.sh" || {
    run_mmx /bin/mount -ur /mnt/app
    exit 36
}
run_mmx /bin/cp "$VOLUME/Bootstrap/scripts/activate.sh" "$SCRIPT_DIR/activate.sh" || {
    run_mmx /bin/mount -ur /mnt/app
    exit 37
}
run_mmx /bin/cp "$VOLUME/Bootstrap/scripts/disable_menu.sh" "$SCRIPT_DIR/disable_menu.sh" || {
    run_mmx /bin/mount -ur /mnt/app
    exit 38
}
run_mmx /bin/chmod 755 "$SCRIPT_DIR/status.sh" "$SCRIPT_DIR/activate.sh" "$SCRIPT_DIR/disable_menu.sh" || {
    run_mmx /bin/mount -ur /mnt/app
    exit 39
}

# SWDL a copie le menu sous l'extension inerte `.new`. Il est publie en
# dernier. Un echec anterieur ne rend donc jamais visible une entree GEM dont
# les scripts seraient absents ou partiels.
run_mmx /bin/chmod 644 "$MENU_STAGE" || {
    run_mmx /bin/mount -ur /mnt/app
    exit 41
}
run_mmx /bin/mv "$MENU_STAGE" "$MENU_FILE" || {
    run_mmx /bin/mount -ur /mnt/app
    exit 42
}

run_mmx /bin/sync
run_mmx /bin/mount -ur /mnt/app || exit 43
echo "MIBSI bootstrap: menu installe, rouvrir le GEM"
exit 0
