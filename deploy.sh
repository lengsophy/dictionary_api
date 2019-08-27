#!/bin/sh

TAG_VERSION=0.0.7

# remove target folder
rm -rf target/ &&

# build project to .jar file
mvn install &&

# build image from Dockerfile
docker build --tag=dictionary_api:$TAG_VERSION . &&

# run container from student_affair image
docker run -itd -p 8080:8080 dictionary_api:$TAG_VERSION &&

# check port running
netstat -pnlt &&

# list docker running
docker ps
