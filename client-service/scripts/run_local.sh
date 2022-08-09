#!/bin/bash
echo "Running local Client Service"

export CONFIG_HOST=localhost
printenv | grep CONFIG_HOST

mvn clean install
mvn spring-boot:run -Dspring-boot.run.profiles=dev