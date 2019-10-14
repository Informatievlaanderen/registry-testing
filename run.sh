#!/bin/sh

GATLING_CONFIGURATION="$(pwd)/conf"
GATLING_USERFILES="$(pwd)/user-files"
GATLING_RESULTS="$(pwd)/results"

if [ ! -d $GATLING_CONFIGURATION ]; then
    echo "Could not find $GATLING_CONFIGURATION"
    exit 1
fi

if [ ! -d $GATLING_USERFILES ]; then
    echo "Could not find $GATLING_USERFILES"
    exit 1
fi

if [ ! -d $GATLING_RESULTS ]; then
    echo "Creating $GATLING_RESULTS"
    mkdir $GATLING_RESULTS
fi

DOCKER_IMAGE="denvazh/gatling:3.2.1"
docker pull $DOCKER_IMAGE
docker run -i --rm \
-v $GATLING_CONFIGURATION:/opt/gatling/conf \
-v $GATLING_USERFILES:/opt/gatling/user-files \
-v $GATLING_RESULTS:/opt/gatling/results \
$DOCKER_IMAGE