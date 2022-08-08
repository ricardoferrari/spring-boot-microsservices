#!/bin/bash
echo "Running local config server for JAVA aplication"

export CLIENT_SECRET=teste
printenv | grep CLIENT_SECRET

mvn clean install
mvn spring-boot:run