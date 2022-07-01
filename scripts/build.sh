#!/bin/bash
echo "Creating local docker image for JAVA aplication"

mvn package
docker build -t inventory-server .
docker tag inventory-server client-server:latest
docker run -it --rm -p 8080:8080 --name client-server client-server:latest
