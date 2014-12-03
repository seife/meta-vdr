SUMMARY = "VDR HD output device for Raspberry Pi"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=892f569a555ba9c07a568a7c0c4fa63a"
COMPATIBLE_MACHINE = "raspberrypi"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "http://projects.vdr-developer.org/attachments/download/1817/vdr-rpihddevice-0.0.10.tgz"
SRC_URI[md5sum] = "29b50c0acd756565a7fd3633ee208e1c"
SRC_URI[sha256sum] = "3f6d9165e702d03e836e34531d5012825918613110f9e9215b8c48b9f5333e2e"

PV = "0.0.10"

S = "${WORKDIR}/rpihddevice-${PV}"

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

