#!/bin/sh
set -e

mvn -q package

exec java -jar target/http-server-java.jar "$@"
