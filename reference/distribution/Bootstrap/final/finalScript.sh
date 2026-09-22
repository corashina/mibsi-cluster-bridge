#!/bin/sh
# Target-owned SWDL entry point. Menu/scripts belong to the product transaction.
VOLUME="$2"
if test -z "$VOLUME"; then
    if test -d /net/mmx/fs/sda0; then VOLUME=/net/mmx/fs/sda0
    elif test -d /net/mmx/fs/sdb0; then VOLUME=/net/mmx/fs/sdb0
    else echo "MIBSI: carte SD absente"; exit 20; fi
fi
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
run_mmx /bin/mount -uw "$VOLUME" || exit 24
/bin/mkdir -p "$VOLUME/MIBSI-Logs" || exit 25
exec 3>&1
exec >> "$VOLUME/MIBSI-Logs/install.log" 2>&1
finish()
{
    run_mmx /bin/sync
    run_mmx /bin/mount -ur "$VOLUME"
}
trap finish 0
trap 'exit 70' 1 2 15
echo "MIBSI: debut installation/desinstallation P5092" >&3
sh "$VOLUME/Bootstrap/final/product/install_product.sh" "$VOLUME"
RESULT=$?
if test "$RESULT" -eq 0; then
    : > /tmp/SWDLScript.Result
    echo "MIBSI: operation terminee" >&3
else
    echo "MIBSI: operation interrompue/refusee; voir MIBSI-Logs/install.log" >&3
fi
exit "$RESULT"
