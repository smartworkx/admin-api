#!/bin/sh
echo "Building dist"
cd sources
gradle clean assemble
mkdir dist
cp ./dist/* ../dist
