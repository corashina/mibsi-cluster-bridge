"""Hook the maintained support script into the upstream install_product.sh.

Each hook is inserted at one anchor line that must occur exactly once. The
upstream text is otherwise unchanged, so removing the hooks restores it byte
for byte (verify_package.py checks this).
"""
from pathlib import Path

SUPPORT_SOURCE = Path(__file__).resolve().parents[1] / 'package/mibsi-support.sh'
SUPPORT_NAME = 'mibsi-support.sh'
INSTALL_SCRIPT = 'Bootstrap/final/product/install_product.sh'
SUPPORT_PATH = 'Bootstrap/final/product/' + SUPPORT_NAME

# (anchor, text, position): the text goes before or after the anchor line.
HOOKS = (
    ('. "$PRODUCT_DIR/release.env"\n',
     '# MIBSI Cluster Bridge: status/cleanup markers and uninstall preflight.\n'
     'test -f "$PRODUCT_DIR/mibsi-support.sh" || fail "payload SD incomplet"\n'
     '. "$PRODUCT_DIR/mibsi-support.sh"\n'
     'if mibsi_support_operation; then exit 0; fi\n',
     'after'),
    ('# No predecessor allowlist: native lifecycle captures bytes, mode, owner,\n',
     'mibsi_uninstall_preflight\n',
     'before'),
    ('RESULT=$?\n',
     'if test "$RESULT" -eq 0; then mibsi_remove_menu_stage; fi\n',
     'after'),
)


def apply(text):
    for anchor, hook, position in HOOKS:
        if text.count(anchor) != 1:
            raise RuntimeError('install_product.sh anchor not found exactly once: ' + anchor.strip())
        replacement = anchor + hook if position == 'after' else hook + anchor
        text = text.replace(anchor, replacement)
    return text


def strip(text):
    for anchor, hook, position in HOOKS:
        patched = anchor + hook if position == 'after' else hook + anchor
        if text.count(patched) != 1:
            raise RuntimeError('install_product.sh hook not found exactly once: ' + hook.strip())
        text = text.replace(patched, anchor)
    return text
