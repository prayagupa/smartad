AlsoBought MR Stripe approach
============================

AlsoBought counts the relative freq for products bought in amazon.


Tools
=========

```
(Scala 2.10.3)[http://www.scala-lang.org/download/]
sbt, http://www.scala-sbt.org/download.html
scala intellij plugin, https://confluence.jetbrains.com/display/SCA/Scala+Plugin+for+IntelliJ+IDEA
sbt intellij plugin, https://plugins.jetbrains.com/plugin/5007
hadoop 2.2.0
```

running AlsoBought Job
=================================

1. install hadoop and make sure the hadoop script is on your PATH


start hadoop cluster

```
/usr/local/hadoop-2.2.0/sbin/start-dfs.sh
/usr/local/hadoop-2.2.0/sbin/start-yarn.sh
```

copy input resources

```
 hdfs dfs -ls /user/hduser
hdfs dfs -mkdir -p /user/hduser/alsobought/input
hdfs dfs -copyFromLocal -f src/main/Resources/file01 /user/hduser/alsobought/input
hdfs dfs -copyFromLocal -f src/main/Resources/file02 /user/hduser/alsobought/input

hdfs dfs -rm -r /user/hduser/alsobought/output/
```

2. install scala and make sure scalac is on your path

3. create assembled AlsoBoughtPrediction.jar

```
sbt clean && sbt assembly
```

4. Run job from hdfs

```
hadoop jar target/scala-2.10/AlsoBought-assembly-1.0.jar AlsoBoughtPrediction alsobought/input alsobought/output
```

output
==================

list files

```
hduser@prayagupd:/p/w/w/GenreCount-scaldoop$ hdfs dfs -ls /user/hduser/alsobought/output/
15/03/17 00:39:16 WARN util.NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
Found 2 items
-rw-r--r--   1 hduser supergroup          0 2015-03-17 00:34 /user/hduser/alsobought/output/_SUCCESS
-rw-r--r--   1 hduser supergroup         41 2015-03-17 00:34 /user/hduser/alsobought/output/part-r-00000

```

cat output
 
 ```
 hduser@prayagupd:/p/w/w/GenreCount-scaldoop$ hdfs dfs -cat /user/hduser/alsobought/output/part-r-00000
 15/03/17 00:39:58 WARN util.NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
 
 10	(34, 0.5) 
 10	(12, 0.5) 
 
 12	(92, 0.18181818181818182) 
 12	(56, 0.18181818181818182) 
 12	(79, 0.09090909090909091) 
 12	(34, 0.18181818181818182) 
 12	(10, 0.09090909090909091) 
 12	(18, 0.09090909090909091) 
 
 18	(92, 0.125) 
 18	(29, 0.125) 
 18	(56, 0.125) 
 18	(79, 0.125) 
 18	(34, 0.125) 
 18	(12, 0.125) 
 
 29	(92, 0.13333333333333333) 
 29	(56, 0.13333333333333333) 
 29	(79, 0.06666666666666667) 
 29	(34, 0.13333333333333333) 
 29	(10, 0.06666666666666667) 
 29	(18, 0.06666666666666667) 
 29	(12, 0.13333333333333333) 
 
 34	(92, 0.16666666666666666) 
 34	(29, 0.08333333333333333) 
 34	(56, 0.16666666666666666) 
 34	(79, 0.08333333333333333) 
 34	(10, 0.08333333333333333) 
 34	(18, 0.08333333333333333) 
 34	(12, 0.16666666666666666) 
 
 56	(92, 0.2) 
 56	(29, 0.1) 
 56	(34, 0.2) 
 56	(10, 0.1) 
 56	(12, 0.2) 
 79	(92, 0.2) 
 79	(56, 0.2) 
 79	(34, 0.2) 
 79	(18, 0.2) 
 79	(12, 0.2) 
 
 92	(34, 0.3333333333333333) 
 92	(10, 0.3333333333333333) 
 92	(12, 0.3333333333333333) 

 ```