#!/bin/sh
echo "Building dist"
cd sources
gradle assemble
cp ./build/libs/admin-api.jar ../dist
