SUMMARY = "Light-weight C++ XML Processing Library"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://readme.txt;beginline=33;endline=52;md5=0e7d05454930d4a0ddbc582254c6cba2"
PV = "1.5"

SRC_URI = "https://github.com/zeux/pugixml/releases/download/v${PV}/pugixml-${PV}.tar.gz"
SRC_URI[md5sum] = "77a69633cc2fe0858fd177e01e95cb54"
SRC_URI[sha256sum] = "d70b98002968d9431e4166e6a76486a6d346eb9a76d1a0e7be58e786d3dee670"

DEPENDS = "cmake"

inherit cmake
OECMAKE_SOURCEPATH = "${S}/scripts"
EXTRA_OECMAKE = " -DBUILD_SHARED_LIBS=ON "

FILES_${PN}-dev += " \
	${libdir}/cmake \
"
