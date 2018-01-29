#!/bin/sh

echo "----- Building server JAR ------"
cd swagger/spring-server/
mvn clean install

echo "----- Copying JAR -----"
cp target/swagger-spring-1.0.0.jar ../../images/server/survey-server.jar

cd ../../images/server/

echo "----- Building image ------"
docker build -t lozzikit/surveys .

cd ../../

echo "----- Copying client package.JSON ------"
cp user_interface/package.json images/client/package.json
cp -r user_interface/ images/client/

cd images/client/

echo "----- Building image ------"
docker build -t lozzikit/surveys-client .