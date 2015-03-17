#!/bin/sh -x

set -e

#export HADOOP_CLASSPATH=$(dirname $0)/scala-library.jar

#rm -rf input output classes wordcount.jar
mkdir -p input classes
echo "Post-rock Progressive-rock Folk-rock Progressive-rock" > src/main/Resources/file01
echo "Post-rock Doom-metal Independent-rock Doom-metal" > src/main/Resource/file02

#scalac -cp hadoop-*.jar:commons-logging-*.jar:commons-cli-*.jar -d classes WordCount.scala
#jar -cvf wordcount.jar -C classes/ .
#hadoop jar wordcount.jar WordCount input output
#hadoop dfs -cat output/part-r-00000
#hadoop dfs -rmr output
