#!/bin/bash
echo "Creating local docker image for JAVA aplication"

mvn package
docker build -t balance-server .
docker tag balance-server balance-server:latest
docker run -it --rm -p 8081:8081 --name balance-server balance-server:latest
