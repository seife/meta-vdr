SUMMARY = "SAT>IP input plugin for VDR"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=892f569a555ba9c07a568a7c0c4fa63a"

SRC_URI = "http://www.saunalahti.fi/~rahrenbe/vdr/satip/files/vdr-satip-${PV}.tgz"
SRC_URI[md5sum] = "e3ce734e15c544f2adc57b651702d48e"
SRC_URI[sha256sum] = "5cb2e5d8be8fa7884ee8c9104cd0db6fd357f4c2d50fc7b4207e8f535426e111"

PV = "2.2.3"

inherit vdr-plugin

DEPENDS = " \
	curl \
	pugixml \
"

# as long as we don't build a git version, reset this to avoid picking up the yocto git tag
EXTRA_OEMAKE_append = " GITTAG='' "
EXTRA_OEMAKE_append = " STRIP=/bin/true "
