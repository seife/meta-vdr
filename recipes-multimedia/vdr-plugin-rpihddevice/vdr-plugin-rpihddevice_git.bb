SUMMARY = "VDR HD output device for Raspberry Pi"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=892f569a555ba9c07a568a7c0c4fa63a"
COMPATIBLE_MACHINE = "raspberrypi"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCREV = "${AUTOREV}"
PV = "0.0.9+git${SRCPV}"

SRC_URI = " \
	git://projects.vdr-developer.org/vdr-plugin-rpihddevice.git;protocol=git \
"

S = "${WORKDIR}/git"

ASNEEDED = ""

DEPENDS = " \
	vdr \
	virtual/egl \
"

EXTRA_OEMAKE = ' \
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

