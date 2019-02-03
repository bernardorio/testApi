#!/usr/bin/env bash

sh ./build_image.sh
docker run -d -p 8080:8080 autogeneraltestapi