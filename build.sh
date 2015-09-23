#!/bin/bash

./mvnw -P target-install install
docker pull quay.io/pharmpress/nexus
docker build -t nexus .