SUMMARY = "VDR HD output device for Raspberry Pi"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=892f569a555ba9c07a568a7c0c4fa63a"
COMPATIBLE_MACHINE = "raspberrypi"

SRC_URI = "http://projects.vdr-developer.org/attachments/download/1676/vdr-rpihddevice-0.0.9.tgz"
SRC_URI[md5sum] = "0c1042b874edf2a22920fbf332f95e66"
SRC_URI[sha256sum] = "74468c1f5e1a6c770738e8c95c43d78a5c754002eee43294f78b79fba340984a"

SRC_URI += " \
	file://rpihddevice-opt-vc.diff \
	file://rpihddevice-new-ffmpeg.diff \
"

PV = "0.0.9"

S = "${WORKDIR}/rpihddevice-${PV}"

ASNEEDED = ""

DEPENDS = " \
	vdr \
	virtual/egl \
"

EXTRA_OEMAKE = ' \
	SDKSTAGE="${STAGING_DIR_HOST}" \
'

do_install() {
	oe_runmake DESTDIR=${D} install
}

FILES_${PN} += " \
	${libdir}/vdr \
"

FILES_${PN}-dbg += " \
	${libdir}/vdr/.debug/* \
"

