#!/bin/sh
echo "Building dist"
cd sources
gradle assemble
cp ./build/libs/* ../dist
