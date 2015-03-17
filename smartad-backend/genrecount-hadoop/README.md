GenreCount scaldoop
=========================

ScalaDoop GenreCount example from the [Apache Hadoop Map/Reduce Tutorial](http://hadoop.apache.org/docs/r1.2.1/mapred_tutorial.html) which
counts the music genres played each minute in spotify.


Tools
=========

Scala 2.10.3
hadoop 2.2.0

running scala GenreCount Job
=================================

1. install hadoop and make sure the hadoop script is on your path


start hadoop cluster

copy input resources

```
hdfs dfs -mkdir -p /user/hduser/genrecount/input
hdfs dfs -copyFromLocal src/main/Resources/file01 /user/hduser/genrecount/input
hdfs dfs -copyFromLocal src/main/Resources/file02 /user/hduser/genrecount/input
```

2. install scala and make sure scalac is on your path

3. create assembled GenreCount.jar

```
sbt assembly
```
4. Run job from hdfs

```
hadoop jar target/scala-2.10/GenreCount-assembly-1.0.jar GenreCount genrecount/input genrecount/output
```

output
==================

```
hduser@prayagupd:/p/w/w/GenreCount-scaldoop$ hdfs dfs -ls /user/hduser/genrecount/output/
15/03/17 00:39:16 WARN util.NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
Found 2 items
-rw-r--r--   1 hduser supergroup          0 2015-03-17 00:34 /user/hduser/genrecount/output/_SUCCESS
-rw-r--r--   1 hduser supergroup         41 2015-03-17 00:34 /user/hduser/genrecount/output/part-r-00000


hduser@prayagupd:/p/w/w/GenreCount-scaldoop$ hdfs dfs -cat /user/hduser/genrecount/output/part-r-00000
15/03/17 00:39:58 WARN util.NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
Folk-rock	  1
Doom-metal	  2
Independent-rock  1
Post-rock	  2
Progressive-rock  2

```
