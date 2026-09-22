#!/bin/sh

MENU_FILE="/mnt/app/eso/hmi/engdefs/mibsi-main.esd"
DISABLED_FILE="/mnt/app/eso/hmi/engdefs/mibsi-main.esd.disabled"

echo "MIBSI: desactivation recuperable du menu"
on -f mmx /bin/mount -uw /mnt/app || exit 50

if test -f "/net/mmx$MENU_FILE"; then
    on -f mmx /bin/mv "$MENU_FILE" "$DISABLED_FILE" || {
        on -f mmx /bin/mount -ur /mnt/app
        exit 51
    }
fi

on -f mmx /bin/sync
on -f mmx /bin/mount -ur /mnt/app || exit 52
echo "MIBSI: menu desactive, scripts conserves"
exit 0
