#!/usr/bin/env bash
echo "ls"
cp sources/Dockerfile build/
mkdir build/dist
cp dist/* build/dist
date +"%Y%m%d%H%M%S" > build/tag
