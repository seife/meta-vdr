SUMMARY = "Displays teletext pages directly on VDR's OSD"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "http://projects.vdr-developer.org/attachments/download/1881/vdr-osdteletext-${PV}.tgz"
SRC_URI[md5sum] = "e0416cd162dc8f1f8d437709fb40889c"
SRC_URI[sha256sum] = "fd607076d58e3742e7264ce8d548abc06b6717f83e7153d60bd4542655c0b306"

PV = "0.9.5"

inherit vdr-plugin
