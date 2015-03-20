####
#### this should make building standard VDR plugins trivial
####

DEPENDS_prepend = " \
	vdr \
"

RDEPENDS_${PN} += " \
	${VDR_APIVER_DEP} \
"

ASNEEDED = ""

# VDR has a Make.config and with "-e" this does not get used :-(
EXTRA_OEMAKE_remove = "-e"

do_install() {
	oe_runmake DESTDIR=${D} install
}

FILES_${PN} += " \
	${libdir}/vdr \
"

FILES_${PN}-dbg += " \
	${libdir}/vdr/.debug/* \
"

S = "${WORKDIR}/${@get_basename(d)}-${PV}"

def get_basename(d):
    import bb

    pn = d.getVar('PN', True) or ""
    return pn[len("vdr-plugin-"):]

### this does not work
#VDR_APIVER_PROV = "vdrapi-${@get_apiversion(d, False)}"
VDR_APIVER_DEP  = "vdrapi-${@get_apiversion(d, True)}"

def get_apiversion(d, plugin):
    import os
    import bb
    from pipes import quote

    if plugin:
        src_dir = (d.getVar('STAGING_INCDIR', True) or "")
        src_dir += "/vdr"
    else:
        src_dir = (d.getVar('S', True) or "")

    if len(src_dir) == 0:
        return None

    config_h = src_dir + "/config.h"

    if not os.path.exists(config_h):
        return config_h

    for line in open(config_h):
        if "#define APIVERSION" in line:
            apiver = line.split(" ")[-1]
            return apiver.split("\"")[1]

    return None

