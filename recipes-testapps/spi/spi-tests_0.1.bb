DESCRIPTION = "Application for running basic spi latency tests."
LICENSE = "gpl2"

inherit dpkg debianize

URL="local"
SECTION  = "admin"
PRIORITY = "standard"

SRC_DIR="src"

SRC_URI += " \
        file://src \
        file://debian \
        "

###                              ###
### debianize makefile functions ###
###                              ###
debianize_build[target] = "build"
debianize_build() {
	@echo "Running build target."
	gcc -o spi_test -lrt -lpthread spi_latency.c
	gcc -o simple_spi_test simple_spi.c
}

debianize_install[target] = "install"
debianize_install[tdeps] = "build"
debianize_install() {
	@echo "Running install target."
	dh_testdir
	dh_testroot
	dh_clean  -k

	install -d debian/${BPN}/opt/tests
	install -m 0755 simple_spi_test debian/${BPN}/opt/tests
	install -m 0755 spi_test        debian/${BPN}/opt/tests
}

debianize_clean[target] = "clean"
debianize_clean() {
	@echo "Running clean target."
	rm -rf spi_test
	rm -rf simple_spi_test
}