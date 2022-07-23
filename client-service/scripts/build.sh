#!/bin/bash
echo "Creating local docker image for JAVA aplication"

mvn package
docker build -t client-server .
docker tag client-server client-server:latest
docker run -it --rm -p 8000:8000 --name client-server client-server:latest
