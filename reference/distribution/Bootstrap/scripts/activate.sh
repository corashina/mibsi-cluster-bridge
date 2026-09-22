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

if test ! -f "$VERSION_FILE" || ! is_supported_family; then
    echo "MIBSI: activation reservee aux familles MHI2/MHI2Q"
    exit 10
fi

if test ! -x "$CLIENT"; then
    echo "MIBSI: client reseau absent dans cette premiere tranche"
    echo "Aucun binaire produit n'a ete installe."
    exit 11
fi

"$CLIENT" activate
exit $?
