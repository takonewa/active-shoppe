#!/usr/bin/env bash
sh docker/scripts/buildImage.sh
APP_NAME=active-shoppe
VERSION=`grep -oP 'version=\K.*' target/maven-archiver/pom.properties`
FULL_NAME=mmi/${APP_NAME}:${VERSION}
docker stop ${APP_NAME}
docker rm ${APP_NAME}
docker run -e SPRING_PROFILES_ACTIVE=prod -e api.version=${VERSION} -p 8080:8080 --name ${APP_NAME} ${FULL_NAME}
