# MIBSI Cluster Bridge support operations.
#
# Sourced by install_product.sh after release.env. It relies on these names
# from that script: VOLUME, PRODUCT_DIR, TOOL, OPERATION, APP_RW, run_mmx,
# fail and the SUPPORTED_* / VERSION_FILE firmware identity.
#
# SD card markers (empty files at the card root, next to metainfo2.txt):
#   MIBSI-STATUS   read-only report in MIBSI-Logs/status.log; nothing is changed
#   MIBSI-CLEANUP  archive the on-unit MIBSI state to the card, then remove
#                  inert leftovers; refused while any MIBSI binary is active
#
# Every file system access goes through run_mmx with mmx paths, like the
# upstream installer, so the same code runs from the RCC or the MMX.

MIBSI_STATE_DIR=/mnt/app/root/mibsi
MIBSI_ENGDEFS=/mnt/app/eso/hmi/engdefs
MIBSI_MENU_STAGE=$MIBSI_ENGDEFS/mibsi-main.esd.new

# Paths written by the V2 lifecycle (same order as mibsi-install-tool).
# stock: an OEM file MIBSI replaces; it must exist and must not hold a MIBSI hash.
# owned: a file only MIBSI creates; any copy is a MIBSI leftover.
# Kind binary/menu/state decides whether MIBSI-CLEANUP may remove it.
MIBSI_MANAGED_PATHS='stock config /mnt/system/etc/eso/production/dio_manager.json
stock config /mnt/app/eso/hmi/lsd/lsd.sh
owned binary /eso/lib/libmhi2qaacluster.so
owned binary /eso/lib/libmhi2qcarplaycluster.so
owned binary /eso/bin/apps/mhi2q-aa-turn-card-renderer
owned binary /eso/bin/apps/mhi2q-aa-cluster-display
owned binary /mnt/app/eso/hmi/lsd/jars/001_mhi2q_aa_observer.jar
owned binary /mnt/app/eso/hmi/lsd/jars/002_mhi2q_aa_navigation_proxy.jar
stock binary /eso/bin/apps/gal
stock binary /eso/bin/apps/dio_manager
owned menu /mnt/app/eso/hmi/engdefs/mibsi-main.esd
owned menu /mnt/app/eso/hmi/engdefs/scripts/mibsi/v1/status.sh
owned menu /mnt/app/eso/hmi/engdefs/scripts/mibsi/v1/activate.sh
owned menu /mnt/app/eso/hmi/engdefs/scripts/mibsi/v1/disable_menu.sh
owned state /mnt/app/root/mibsi/origin
owned state /mnt/app/root/mibsi/installed.release
owned state /mnt/app/root/mibsi/version'

# Leftovers outside the lifecycle list. All are inert: nothing loads them.
MIBSI_EXTRA_PATHS="$MIBSI_MENU_STAGE
$MIBSI_ENGDEFS/mibsi-main.esd.disabled
$MIBSI_ENGDEFS/scripts/mibsi
$MIBSI_STATE_DIR/installer.lock
$MIBSI_STATE_DIR/pending
$MIBSI_STATE_DIR/history
$MIBSI_STATE_DIR"

mibsi_exists()
{
    run_mmx /bin/ls -d "$1" </dev/null >/dev/null 2>&1
}

mibsi_hash()
{
    run_mmx "$TOOL" sha256 "$1" </dev/null 2>/dev/null
}

# A hash from any column of any release this package recognizes, or a file
# shipped in this package.
mibsi_known_hash()
{
    test -n "$1" || return 1
    grep " $1" "$PRODUCT_DIR/known-releases.txt" >/dev/null 2>&1 ||
        grep "^$1 " "$PRODUCT_DIR/install.sha256" >/dev/null 2>&1
}

mibsi_firmware_supported()
{
    test -f "$VERSION_FILE" || return 1
    { grep "Current train = '$SUPPORTED_TRAIN'" "$VERSION_FILE" >/dev/null 2>&1 ||
      grep "Current train = '$SUPPORTED_TRAIN_ALIAS'" "$VERSION_FILE" >/dev/null 2>&1 ||
      grep "Current train = '$SUPPORTED_TRAIN_P5152'" "$VERSION_FILE" >/dev/null 2>&1; } &&
        grep "MainUnit version = '$SUPPORTED_MU'" "$VERSION_FILE" >/dev/null 2>&1
}

# Scan the unit and print one line per path. Sets these counters:
#   MIBSI_ACTIVE   stock files still modified, or MIBSI binaries present
#   MIBSI_INERT    menu, state and other leftovers that nothing loads
#   MIBSI_ORIGIN   1 when a V2 backup pointer exists
#   MIBSI_PENDING  1 when an interrupted operation journal exists
mibsi_scan()
{
    MIBSI_ACTIVE=0
    MIBSI_INERT=0
    MIBSI_ORIGIN=0
    MIBSI_PENDING=0
    MIBSI_SCAN_LIST="$MIBSI_MANAGED_PATHS"
    while read -r MIBSI_ROLE MIBSI_KIND MIBSI_PATH; do
        test -n "$MIBSI_PATH" || continue
        if ! mibsi_exists "$MIBSI_PATH"; then
            if test "$MIBSI_ROLE" = stock; then
                echo "  MISSING      $MIBSI_PATH (OEM file absent)"
                MIBSI_ACTIVE=$((MIBSI_ACTIVE + 1))
            else
                echo "  absent       $MIBSI_PATH"
            fi
            continue
        fi
        MIBSI_SUM=$(mibsi_hash "$MIBSI_PATH")
        test -n "$MIBSI_SUM" || MIBSI_SUM=unreadable
        if test "$MIBSI_ROLE" = stock; then
            MIBSI_V1=0
            if test "$MIBSI_PATH" = /mnt/app/eso/hmi/lsd/lsd.sh &&
               run_mmx /bin/cat "$MIBSI_PATH" </dev/null 2>/dev/null | grep MIBSI_JAR_BOOTSTRAP_V1 >/dev/null 2>&1; then
                MIBSI_V1=1
            fi
            if test "$MIBSI_V1" = 1; then
                echo "  MIBSI        $MIBSI_PATH (MIBSI_JAR_BOOTSTRAP_V1 line) $MIBSI_SUM"
                MIBSI_ACTIVE=$((MIBSI_ACTIVE + 1))
            elif mibsi_known_hash "$MIBSI_SUM"; then
                echo "  MIBSI        $MIBSI_PATH $MIBSI_SUM"
                MIBSI_ACTIVE=$((MIBSI_ACTIVE + 1))
            else
                echo "  not MIBSI    $MIBSI_PATH $MIBSI_SUM"
            fi
            continue
        fi
        echo "  LEFTOVER     $MIBSI_PATH ($MIBSI_KIND) $MIBSI_SUM"
        if test "$MIBSI_KIND" = binary; then
            MIBSI_ACTIVE=$((MIBSI_ACTIVE + 1))
        else
            MIBSI_INERT=$((MIBSI_INERT + 1))
        fi
        if test "$MIBSI_PATH" = "$MIBSI_STATE_DIR/origin"; then MIBSI_ORIGIN=1; fi
    done <<EOF
$MIBSI_SCAN_LIST
EOF
    while read -r MIBSI_PATH; do
        test -n "$MIBSI_PATH" || continue
        if mibsi_exists "$MIBSI_PATH"; then
            echo "  LEFTOVER     $MIBSI_PATH (inert)"
            MIBSI_INERT=$((MIBSI_INERT + 1))
            if test "$MIBSI_PATH" = "$MIBSI_STATE_DIR/pending"; then MIBSI_PENDING=1; fi
        else
            echo "  absent       $MIBSI_PATH"
        fi
    done <<EOF
$MIBSI_EXTRA_PATHS
EOF
    if mibsi_exists "$MIBSI_STATE_DIR/history"; then
        echo "  backup history entries:"
        run_mmx /bin/ls "$MIBSI_STATE_DIR/history" </dev/null 2>/dev/null | sed 's/^/    /'
    fi
}

mibsi_verdict()
{
    if test "$MIBSI_PENDING" = 1; then
        echo "VERDICT: interrupted operation. Run the same package again to let it recover."
    elif test "$MIBSI_ORIGIN" = 1; then
        echo "VERDICT: active V2 installation with its backup. Use the matching uninstall package."
    elif test "$MIBSI_ACTIVE" -gt 0; then
        echo "VERDICT: MIBSI or unexpected files are active without a V2 backup pointer."
        echo "         Do not delete files by hand. Send this log to support."
    elif test "$MIBSI_INERT" -gt 0; then
        echo "VERDICT: only inert MIBSI leftovers remain. MIBSI-CLEANUP can archive and remove them."
    else
        echo "VERDICT: no MIBSI files found."
    fi
}

# Write the report to MIBSI-Logs/status.log and to the install log.
mibsi_status_report()
{
    MIBSI_REPORT="$VOLUME/MIBSI-Logs/status.log"
    {
        echo "MIBSI status report"
        echo "package release: $MIBSI_RELEASE_ID"
        if test -f "$VERSION_FILE"; then
            grep "Current train = " "$VERSION_FILE" 2>/dev/null | sed 's/^/firmware: /'
            grep "MainUnit version = " "$VERSION_FILE" 2>/dev/null | sed 's/^/firmware: /'
        else
            echo "firmware: identity unavailable"
        fi
        if mibsi_firmware_supported; then
            echo "firmware supported by this package: yes"
        else
            echo "firmware supported by this package: no"
        fi
        echo "note: every SD update run, including this one, copies $MIBSI_MENU_STAGE"
        mibsi_scan
        mibsi_verdict
    } > "$MIBSI_REPORT" 2>&1
    cat "$MIBSI_REPORT"
}

# Compare every regular file below $1 with its copy below $2.
mibsi_compare_tree()
{
    for MIBSI_NAME in $(run_mmx /bin/ls -a "$1" 2>/dev/null); do
        case "$MIBSI_NAME" in .|..) continue ;; esac
        if run_mmx /bin/ls -d "$1/$MIBSI_NAME/." >/dev/null 2>&1; then
            mibsi_compare_tree "$1/$MIBSI_NAME" "$2/$MIBSI_NAME" || return 1
        else
            MIBSI_LEFT=$(mibsi_hash "$1/$MIBSI_NAME")
            MIBSI_RIGHT=$(mibsi_hash "$2/$MIBSI_NAME")
            if test -z "$MIBSI_LEFT" || test "$MIBSI_LEFT" != "$MIBSI_RIGHT"; then
                echo "MIBSI: archive mismatch: $1/$MIBSI_NAME"
                return 1
            fi
        fi
    done
    return 0
}

mibsi_cleanup()
{
    mibsi_firmware_supported || fail "firmware hors liste Europe P5092/P5152 MU1329"
    echo "MIBSI: cleanup scan"
    mibsi_scan
    if test "$MIBSI_PENDING" = 1 || test "$MIBSI_ORIGIN" = 1 || test "$MIBSI_ACTIVE" -gt 0; then
        mibsi_verdict
        mibsi_status_report >/dev/null
        fail "cleanup refused; MIBSI is still active or an operation is pending (see MIBSI-Logs/status.log)"
    fi
    if test "$MIBSI_INERT" -eq 0; then
        echo "MIBSI: nothing to clean"
        mibsi_status_report
        return 0
    fi

    run_mmx /bin/mount -uw /mnt/app || fail "montage app impossible"
    APP_RW=1

    if mibsi_exists "$MIBSI_STATE_DIR"; then
        MIBSI_STAMP=$(date +%Y%m%d-%H%M%S 2>/dev/null)
        test -n "$MIBSI_STAMP" || MIBSI_STAMP=$$
        MIBSI_ARCHIVE="$VOLUME/MIBSI-Logs/backup-archive-$MIBSI_STAMP"
        run_mmx /bin/mkdir -p "$MIBSI_ARCHIVE" || fail "archive directory on SD impossible"
        run_mmx /bin/cp -R "$MIBSI_STATE_DIR" "$MIBSI_ARCHIVE/" ||
            fail "archive copy to SD failed; nothing removed"
        run_mmx /bin/sync
        mibsi_compare_tree "$MIBSI_STATE_DIR" "$MIBSI_ARCHIVE/mibsi" ||
            fail "archive verification failed; nothing removed"
        echo "MIBSI: on-unit state archived to MIBSI-Logs/backup-archive-$MIBSI_STAMP"
    fi

    MIBSI_CLEAN_RESULT=0
    for MIBSI_PATH in "$MIBSI_STATE_DIR" "$MIBSI_ENGDEFS/scripts/mibsi" \
        "$MIBSI_ENGDEFS/mibsi-main.esd" "$MIBSI_ENGDEFS/mibsi-main.esd.disabled" "$MIBSI_MENU_STAGE"; do
        if mibsi_exists "$MIBSI_PATH"; then
            if run_mmx /bin/rm -rf "$MIBSI_PATH"; then
                echo "MIBSI: removed $MIBSI_PATH"
            else
                echo "MIBSI: could not remove $MIBSI_PATH"
                MIBSI_CLEAN_RESULT=1
            fi
        fi
    done
    run_mmx /bin/sync
    echo "MIBSI: cleanup result"
    mibsi_status_report
    test "$MIBSI_CLEAN_RESULT" -eq 0 && test "$MIBSI_INERT" -eq 0 && test "$MIBSI_ACTIVE" -eq 0 ||
        fail "cleanup incomplete; see MIBSI-Logs/status.log"
    return 0
}

# Entry point for marker operations. Returns 1 when no marker is present.
mibsi_support_operation()
{
    MIBSI_STATUS_REQUESTED=0
    MIBSI_CLEANUP_REQUESTED=0
    if test -f "$VOLUME/MIBSI-STATUS"; then MIBSI_STATUS_REQUESTED=1; fi
    if test -f "$VOLUME/MIBSI-CLEANUP"; then MIBSI_CLEANUP_REQUESTED=1; fi
    case "$MIBSI_STATUS_REQUESTED:$MIBSI_CLEANUP_REQUESTED" in
        0:0) return 1 ;;
        1:1) fail "marqueurs SD incoherents (MIBSI-STATUS et MIBSI-CLEANUP)" ;;
        1:0)
            echo "MIBSI: status report only; nothing is changed"
            mibsi_status_report
            echo "MIBSI: status written to MIBSI-Logs/status.log"
            ;;
        0:1)
            mibsi_cleanup
            echo "MIBSI: cleanup termine et relu; redemarrage MMI normal requis"
            ;;
    esac
    return 0
}

# Uninstall with no V2 backup pointer restores nothing. Refuse before the
# native tool creates /mnt/app/root/mibsi, and report what remains instead.
mibsi_uninstall_preflight()
{
    test "$OPERATION" = uninstall || return 0
    if mibsi_exists "$MIBSI_STATE_DIR/origin" || mibsi_exists "$MIBSI_STATE_DIR/pending"; then
        return 0
    fi
    echo "MIBSI: no active V2 installation; nothing to restore"
    mibsi_status_report
    fail "desinstallation sans sauvegarde active; voir MIBSI-Logs/status.log"
}

# SWDL stages the GEM menu as mibsi-main.esd.new on every run. The lifecycle
# never publishes it, so drop it while /mnt/app is still writable.
mibsi_remove_menu_stage()
{
    if mibsi_exists "$MIBSI_MENU_STAGE"; then
        if run_mmx /bin/rm -f "$MIBSI_MENU_STAGE"; then
            echo "MIBSI: removed staging copy $MIBSI_MENU_STAGE"
        else
            echo "MIBSI: could not remove staging copy $MIBSI_MENU_STAGE"
        fi
    fi
    return 0
}
