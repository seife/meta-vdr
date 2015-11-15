SUMMARY = "VDR HD output device for Raspberry Pi"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=892f569a555ba9c07a568a7c0c4fa63a"
COMPATIBLE_MACHINE = "raspberrypi"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PV = "1.0.0"
SRC_URI = "http://projects.vdr-developer.org/attachments/download/1966/vdr-rpihddevice-${PV}.tgz"
SRC_URI[md5sum] = "4c3d040812872897abaffa7cf01d9b7a"
SRC_URI[sha256sum] = "b7bbc29fe75434caaa0f8157a701be31553e6763f94c39e2755ded03354f9ab1"

S = "${WORKDIR}/rpihddevice-${PV}"

ASNEEDED = ""

DEPENDS = " \
	vdr \
	virtual/egl \
	ffmpeg \
"

# VDR has a Make.config and with "-e" this does not get used :-(
EXTRA_OEMAKE_remove = "-e"

EXTRA_OEMAKE_append = ' \
	SDKSTAGE="${STAGING_DIR_HOST}" \
	VCINCDIR="${STAGING_DIR_HOST}/usr/include" \
	VCLIBDIR="${STAGING_DIR_HOST}/usr/lib" \
'

do_configure_prepend() {
	# lame hack to avoid patching the Makefile...
	sed -i -e 's#/opt/vc#/usr#g' ilclient/Makefile
}

do_install() {
	oe_runmake DESTDIR=${D} install
}

FILES_${PN} += " \
	${libdir}/vdr \
"

FILES_${PN}-dbg += " \
	${libdir}/vdr/.debug/* \
"

