#!/bin/sh

echo "----- Building server JAR ------"
cd swagger/spring-server/
mvn clean install

echo "----- Copying JAR -----"
cp target/swagger-spring-1.0.0.jar ../../images/server/survey-server.jar

cd ../../images/server/

echo "----- Building image ------"
docker build -t lozzikit/surveys .