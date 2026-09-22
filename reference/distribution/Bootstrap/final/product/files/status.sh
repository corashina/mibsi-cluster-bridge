#!/bin/sh

SUPPORTED_FAMILY_MHI2="MHI2_"
SUPPORTED_FAMILY_MHI2Q="MHI2Q_"
VERSION_FILE="/net/rcc/dev/shmem/version.txt"
CLIENT="/eso/bin/apps/mibsi-bootstrap-client"

is_supported_family()
{
    grep "$SUPPORTED_FAMILY_MHI2" "$VERSION_FILE" >/dev/null 2>&1 ||
        grep "$SUPPORTED_FAMILY_MHI2Q" "$VERSION_FILE" >/dev/null 2>&1
}

echo "---------------- MIBSI ----------------"
if test -f "$VERSION_FILE" && is_supported_family; then
    echo "Famille firmware: MHI2/MHI2Q detectee"
else
    echo "Famille firmware: non reconnue, activation interdite"
fi

if test -x "$CLIENT"; then
    echo "Client d'activation: installe"
    "$CLIENT" status
else
    echo "Client d'activation: pas encore installe"
fi
echo "Bootstrap GEM: 0.2.0"
echo "---------------------------------------"
exit 0
