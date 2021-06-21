#!/bin/bash

set -Eeuo pipefail

echo "******** BUILDING & RUNNING TESTS..."
mvn clean install

#echo "******** CHECKING CODE STYLE..."
#mvn checkstyle:check

echo "******** COLLECT DEPENDENCIES..."
mvn dependency:copy-dependencies

echo "******** BUILD ENVIRONMENT ..."
export CLASSPATH=""
for file in `ls target/dependency`; do export CLASSPATH=$CLASSPATH:target/dependency/$file; done
export CLASSPATH=$CLASSPATH:target/classes

export ACTIVE_ENV="development"

echo "******** EXEC"
java -cp $CLASSPATH -Dactivejdbc.log -DACTIVE_ENV=$ACTIVE_ENV project.App
