DESCRIPTION = "Python based testframework."
LICENSE = "gpl"

inherit dpkg debianize-python

DEB_RDEPENDS += "python3-can python3-serial python3 iproute2"

URL = "git://git.pixel-group.de/bennie/python-testframework.git"
BRANCH = "master"
SRCREV = "${BRANCH}"

SRC_DIR = "git"
SRC_URI = "${URL};branch=${BRANCH};protocol=https \
           file://debian \
          "

SECTION = "utils"
PRIORITY = "optional"

###                              ###
### debianize makefile functions ###
###                              ###

debianize_install[target] = "install"
debianize_install[tdeps] = "build"
debianize_install() {
	@echo "Running install target."
	install -m 755 -d debian/${BPN}/opt/test
	cp -r ${PPS}/src/* debian/${BPN}/opt/test
}

debianize_clean[target] = "clean"
debianize_clean() {
	@echo "Running clean target."
}


debianize_build[target] = "build"
debianize_build() {
	@echo "Running build target."
}