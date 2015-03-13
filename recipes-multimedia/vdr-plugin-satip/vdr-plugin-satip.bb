SUMMARY = "SAT>IP input plugin for VDR"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=892f569a555ba9c07a568a7c0c4fa63a"

SRC_URI = "http://www.saunalahti.fi/~rahrenbe/vdr/satip/files/vdr-satip-${PV}.tgz"
SRC_URI[md5sum] = "38a81a3e0ccd73894cc4204c06f96b97"
SRC_URI[sha256sum] = "07c9ec9d438965ab09d17b00f32fbc1c28354771745db1fb21a421b5a61d268a"

PV = "2.2.0"

S = "${WORKDIR}/satip-${PV}"

ASNEEDED = ""

DEPENDS = " \
	vdr \
	curl \
	pugixml \
"

# VDR has a Make.config and with "-e" this does not get used :-(
EXTRA_OEMAKE_remove = "-e"

# as long as we don't build a git version, reset this to avoid picking up the yocto git tag
EXTRA_OEMAKE_append = " GITTAG='' "
EXTRA_OEMAKE_append = " SATIP_DEBUG=1 "

do_install() {
	oe_runmake DESTDIR=${D} install
}

FILES_${PN} += " \
	${libdir}/vdr \
"

FILES_${PN}-dbg += " \
	${libdir}/vdr/.debug/* \
"

