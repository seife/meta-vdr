SUMMARY = "VDR HD output device for Raspberry Pi"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=892f569a555ba9c07a568a7c0c4fa63a"
COMPATIBLE_MACHINE = "raspberrypi"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PV = "0.0.11"
SRC_URI = "http://projects.vdr-developer.org/attachments/download/1861/vdr-rpihddevice-${PV}.tgz"
SRC_URI[md5sum] = "0960934379adf8bb5301b17caddd3ada"
SRC_URI[sha256sum] = "f2dd9d392ab5585fbdcc55ea3c22612f2becaf878db4b96b69bd603dc145d05b"

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

