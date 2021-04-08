FROM openjdk:8-jdk-alpine
USER root
MAINTAINER TK <takonewa.v@gmail.com>
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
