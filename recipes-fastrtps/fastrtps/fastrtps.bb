inherit pkgconfig cmake

DESCRIPTION = "eProsima FastRTPS"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

PV = "1.4.0-git${SRCPV}"

S = "${WORKDIR}/git"

SRCREV="d0133e00ab32ef9dc107364393efff201b5a3663"

SRC_URI = "git://github.com/eProsima/Fast-RTPS.git"

EXTRA_OECMAKE = "-DTHIRDPARTY=ON -DASIO_INCLUDE_DIR=${S}/thirdparty/asio/asio/include -DTINYXML2_INCLUDE_DIR=${S}/thirdparty/tinyxml2"

do_install_append() {

    # The examples seem to be installed even though the default is "NO"
    rm -rf ${D}/usr/examples

    # The library is not versioned, which breaks the Yocto build
    mv ${D}/usr/lib/libfastrtps.so ${D}/usr/lib/libfastrtps.so.0.0.0
    pushd ${D}/usr/lib/
    ln -s libfastrtps.so.0.0.0 libfastrtps.so.0.0
    ln -s libfastrtps.so.0.0.0 libfastrtps.so.0
    ln -s libfastrtps.so.0.0.0 libfastrtps.so
    popd
}
