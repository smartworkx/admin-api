#!/bin/sh
echo "Building dist"
cd sources
gradle clean assemble
cp ./build/libs/* ../dist
