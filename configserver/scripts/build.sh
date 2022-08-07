#!/bin/bash
echo "Creating local docker image for JAVA aplication"

mvn package
docker build -t config-server .
docker tag config-server config-server:latest
docker run -it --rm -p 8071:8071 --name config-server config-server:latest
