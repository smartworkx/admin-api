#!/bin/sh
echo "Building dist"
cd sources
gradle clean assemble
npm run deploy:prod
cp ./dist/* ../dist
