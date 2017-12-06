#!/bin/sh

cd swagger
    cd survey-specs
        mvn clean install -DskipTests
    cd ..
    cd spring-server
        mvn clean install -DskipTests
    cd ..
cd ..

docker-compose down
docker-compose up --build