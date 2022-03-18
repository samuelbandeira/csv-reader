#!/bin/bash
./mvnw clean package -DskipTests
cp target/csv-reader*.jar src/main/docker
docker-compose -f ./src/main/docker/docker-compose.yml up &
