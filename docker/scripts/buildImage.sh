#!/usr/bin/env bash
PART=
APP_NAME=active-shoppe
mvn clean install
VERSION=`grep -oP 'version=\K.*' target/maven-archiver/pom.properties`
FULL_NAME=mmi/${APP_NAME}:${VERSION}
docker build -t ${FULL_NAME} .
