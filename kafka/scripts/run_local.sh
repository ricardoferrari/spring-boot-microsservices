#!/bin/bash
echo "Running local Kafka Service"

export CONFIG_HOST=localhost
printenv | grep CONFIG_HOST

docker compose up --detach