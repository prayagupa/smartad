smartad-spark song plays count
==============================

1 - build/install spark
-----------------

```

## I put spark to `/usr/local/spark-1.2.0` and at `/usr/local/spark-1.2.0` and build it for hadoop 2.2.0

mvn -Pyarn -Phadoop-2.2 -Dhadoop.version=2.2.0 -DskipTests clean package

```

2 - install sbt, scala
---------------------------

```
## sbt 0.13.7
wget https://dl.bintray.com/sbt/native-packages/sbt/0.13.7/sbt-0.13.7.tgz
tar zxvf sbt-0.13.7.tgz
mv sbt /usr/local/


## scala 2.10.4

```

3 - [start spark master and workers in standalone mode](http://spark.apache.org/docs/1.2.0/spark-standalone.html)
----------------------------------------------------

```
$ /usr/local/spark-1.2.0/sbin/start-master.sh 
starting org.apache.spark.deploy.master.Master, logging to /usr/local/spark-1.1.1/sbin/../logs/spark-prayagupd-org.apache.spark.deploy.master.Master-1-prayagupd.out


# Check log at `tail -f /usr/local/spark-1.2.0/sbin/../logs/spark-prayagupd-org.apache.spark.deploy.master.Master-1-prayagupd.out`

# goto `curl http://localhost:8080/`, something as below will be seen, 

URL: spark://prayagupd:7077
Workers: 0
Cores: 0 Total, 0 Used
Memory: 0.0 B Total, 0.0 B Used
Applications: 0 Running, 0 Completed
Drivers: 0 Running, 0 Completed
Status: ALIVE

# start workers at `http://localhost:8081/`

/usr/local/spark-1.2.0/bin/spark-class org.apache.spark.deploy.worker.Worker spark://prayagupd:7077

```

package and submit app
------------------------

```bash
sbt package

hdfs dfs -copyFromLocal -f src/main/resources/clientdata.txt /user/hduser/alsobought/input

#from hdfs 2.2.0

/usr/local/spark-submit --class MainExample --master spark://prayagupd:7077 target/scala-2.10/smartad-spark-songplaycount_2.10-1.0.jar /user/hduser/alsobought/input /user/hduser/alsobought/output

#or 

/usr/local/spark-submit --class MainExample --master yarn-cluster target/scala-2.10/smartad-spark-songplaycount_2.10-1.0.jar /user/hduser/alsobought/input /user/hduser/alsobought/output

...
...
...

15/05/15 00:04:08 INFO ui.SparkUI: Started SparkUI at http://prayagupd.local:4040
15/05/15 00:04:09 INFO spark.SparkContext: Added JAR file:/packup/workspace.programming/workspace.scala/smartad/smartad-backend/smartad-spark/target/scala-2.10/smartad-spark-songplaycount_2.10-1.0.jar at http://172.17.0.50:36456/jars/smartad-spark-songplaycount_2.10-1.0.jar with timestamp 1431666249075
15/05/15 00:04:09 INFO client.AppClient$ClientActor: Connecting to master spark://prayagupd:7077...
15/05/15 00:04:09 INFO cluster.SparkDeploySchedulerBackend: Connected to Spark cluster with app ID app-20150515000409-0001
15/05/15 00:04:09 INFO client.AppClient$ClientActor: Executor added: app-20150515000409-0001/0 on worker-20150514235631-prayagupd.local-43289 (prayagupd.local:43289) with 4 cores
15/05/15 00:04:09 INFO cluster.SparkDeploySchedulerBackend: Granted executor ID app-20150515000409-0001/0 on hostPort prayagupd.local:43289 with 4 cores, 512.0 MB RAM
15/05/15 00:04:09 INFO client.AppClient$ClientActor: Executor updated: app-20150515000409-0001/0 is now LOADING
15/05/15 00:04:09 INFO client.AppClient$ClientActor: Executor updated: app-20150515000409-0001/0 is now RUNNING
15/05/15 00:04:10 INFO netty.NettyBlockTransferService: Server created on 40001
15/05/15 00:04:10 INFO storage.BlockManagerMaster: Trying to register BlockManager
15/05/15 00:04:10 INFO storage.BlockManagerMasterActor: Registering block manager prayagupd.local:40001 with 265.0 MB RAM, BlockManagerId(<driver>, prayagupd.local, 40001)
15/05/15 00:04:10 INFO storage.BlockManagerMaster: Registered BlockManager
15/05/15 00:04:10 INFO cluster.SparkDeploySchedulerBackend: SchedulerBackend is ready for scheduling beginning after reached minRegisteredResourcesRatio: 0.0
15/05/15 00:04:10 INFO SongPlayCountApp$: => jobName "SongPlayCountApp"
15/05/15 00:04:10 INFO SongPlayCountApp$: => pathToFiles "/user/hduser/alsobought/input"
15/05/15 00:04:10 INFO storage.MemoryStore: ensureFreeSpace(146491) called with curMem=0, maxMem=277842493
15/05/15 00:04:10 INFO storage.MemoryStore: Block broadcast_0 stored as values in memory (estimated size 143.1 KB, free 264.8 MB)
15/05/15 00:04:11 INFO storage.MemoryStore: ensureFreeSpace(18571) called with curMem=146491, maxMem=277842493
15/05/15 00:04:11 INFO storage.MemoryStore: Block broadcast_0_piece0 stored as bytes in memory (estimated size 18.1 KB, free 264.8 MB)
15/05/15 00:04:11 INFO storage.BlockManagerInfo: Added broadcast_0_piece0 in memory on prayagupd.local:40001 (size: 18.1 KB, free: 265.0 MB)
15/05/15 00:04:11 INFO storage.BlockManagerMaster: Updated info of block broadcast_0_piece0
15/05/15 00:04:11 INFO spark.SparkContext: Created broadcast 0 from textFile at SongPlayCountApp.scala:49
15/05/15 00:04:12 INFO Configuration.deprecation: mapred.tip.id is deprecated. Instead, use mapreduce.task.id
15/05/15 00:04:12 INFO Configuration.deprecation: mapred.task.id is deprecated. Instead, use mapreduce.task.attempt.id
15/05/15 00:04:12 INFO Configuration.deprecation: mapred.task.is.map is deprecated. Instead, use mapreduce.task.ismap
15/05/15 00:04:12 INFO Configuration.deprecation: mapred.task.partition is deprecated. Instead, use mapreduce.task.partition
15/05/15 00:04:12 INFO Configuration.deprecation: mapred.job.id is deprecated. Instead, use mapreduce.job.id
15/05/15 00:04:13 INFO mapred.FileInputFormat: Total input paths to process : 1
15/05/15 00:04:13 INFO spark.SparkContext: Starting job: saveAsTextFile at SongPlayCountApp.scala:55
15/05/15 00:04:13 INFO scheduler.DAGScheduler: Got job 0 (saveAsTextFile at SongPlayCountApp.scala:55) with 2 output partitions (allowLocal=false)
15/05/15 00:04:13 INFO scheduler.DAGScheduler: Final stage: Stage 0(saveAsTextFile at SongPlayCountApp.scala:55)
15/05/15 00:04:13 INFO scheduler.DAGScheduler: Parents of final stage: List()
15/05/15 00:04:13 INFO scheduler.DAGScheduler: Missing parents: List()
15/05/15 00:04:13 INFO scheduler.DAGScheduler: Submitting Stage 0 (MappedRDD[3] at saveAsTextFile at SongPlayCountApp.scala:55), which has no missing parents
15/05/15 00:04:14 INFO storage.MemoryStore: ensureFreeSpace(95520) called with curMem=165062, maxMem=277842493
15/05/15 00:04:14 INFO storage.MemoryStore: Block broadcast_1 stored as values in memory (estimated size 93.3 KB, free 264.7 MB)
15/05/15 00:04:14 INFO storage.MemoryStore: ensureFreeSpace(57489) called with curMem=260582, maxMem=277842493
15/05/15 00:04:14 INFO storage.MemoryStore: Block broadcast_1_piece0 stored as bytes in memory (estimated size 56.1 KB, free 264.7 MB)
15/05/15 00:04:14 INFO storage.BlockManagerInfo: Added broadcast_1_piece0 in memory on prayagupd.local:40001 (size: 56.1 KB, free: 264.9 MB)
15/05/15 00:04:14 INFO storage.BlockManagerMaster: Updated info of block broadcast_1_piece0
15/05/15 00:04:14 INFO spark.SparkContext: Created broadcast 1 from broadcast at DAGScheduler.scala:838
15/05/15 00:04:14 INFO scheduler.DAGScheduler: Submitting 2 missing tasks from Stage 0 (MappedRDD[3] at saveAsTextFile at SongPlayCountApp.scala:55)
15/05/15 00:04:14 INFO scheduler.TaskSchedulerImpl: Adding task set 0.0 with 2 tasks
15/05/15 00:04:16 INFO cluster.SparkDeploySchedulerBackend: Registered executor: Actor[akka.tcp://sparkExecutor@prayagupd.local:35482/user/Executor#1792479395] with ID 0
15/05/15 00:04:16 INFO scheduler.TaskSetManager: Starting task 0.0 in stage 0.0 (TID 0, prayagupd.local, ANY, 1410 bytes)
15/05/15 00:04:16 INFO scheduler.TaskSetManager: Starting task 1.0 in stage 0.0 (TID 1, prayagupd.local, ANY, 1410 bytes)
15/05/15 00:04:16 INFO storage.BlockManagerMasterActor: Registering block manager prayagupd.local:45142 with 265.4 MB RAM, BlockManagerId(0, prayagupd.local, 45142)
15/05/15 00:04:17 INFO storage.BlockManagerInfo: Added broadcast_1_piece0 in memory on prayagupd.local:45142 (size: 56.1 KB, free: 265.4 MB)
15/05/15 00:04:18 INFO storage.BlockManagerInfo: Added broadcast_0_piece0 in memory on prayagupd.local:45142 (size: 18.1 KB, free: 265.3 MB)
15/05/15 00:04:23 INFO scheduler.TaskSetManager: Finished task 1.0 in stage 0.0 (TID 1) in 7712 ms on prayagupd.local (1/2)
15/05/15 00:04:23 INFO scheduler.DAGScheduler: Stage 0 (saveAsTextFile at SongPlayCountApp.scala:55) finished in 9.589 s
15/05/15 00:04:23 INFO scheduler.TaskSetManager: Finished task 0.0 in stage 0.0 (TID 0) in 7753 ms on prayagupd.local (2/2)
15/05/15 00:04:23 INFO scheduler.TaskSchedulerImpl: Removed TaskSet 0.0, whose tasks have all completed, from pool 
15/05/15 00:04:23 INFO scheduler.DAGScheduler: Job 0 finished: saveAsTextFile at SongPlayCountApp.scala:55, took 10.003888 s



## output 
hduser@prayagupd:/packup/workspace.programming/workspace.scala/smartad/smartad-backend/smartad-spark$ hdfs dfs -cat /user/hduser/alsobought/output/part-00000

(Young And Old,2)
(Miniature 6,1)
(The Warm Familiar Smell Of September,1)


hduser@prayagupd:/packup/workspace.programming/workspace.scala/smartad/smartad-backend/smartad-spark$ hdfs dfs -cat /user/hduser/alsobought/output/part-00001

(Vienna,4)
(Drain in Flood,1)
(This Place,1)


```

start spark-jobserver
-----------------------

```
git clone https://github.com/spark-jobserver/spark-jobserver.git
cd spark-jobserver
sbt publish-local

## for dev
sbt ~reStart ## let it hang there, or
sbt 
> reStart config/local.conf
> reStop

## check log at `tail -f job-server/job-server-local.log`, server must be running at port `8090`. (verify with `netstat -vatn | grep 8090`)

```


Also spark-worker UI will give information for this app.

[for spark-jobserver SongPlayCountJob](https://github.com/spark-jobserver/spark-jobserver#wordcountexample-walk-through)
-------------------------------------

eg. to submit existing sparkjobserver apps

```
$ sbt job-server-tests/package

## upload jar as appName "smartad-test"
$ curl --data-binary @job-server-tests/target/job-server-tests-0.4.2-SNAPSHOT.jar localhost:8090/jars/smartad-test
## OK

$ curl -d "input.string = a b c a b see" 'localhost:8090/jobs?appName=smartad-test&classPath=spark.jobserver.WordCountExample'
$ curl -d "input.string = a b c a b see" 'localhost:8090/jobs?appName=smartad-spark-songplaycount&classPath=SongPlayCountApp'

#
# {
#  "status": "STARTED",
#  "result": {
#    "jobId": "4b1531b1-453e-4bc7-a0c0-408477e7c5f8",
#    "context": "496c53a5-spark.jobserver.WordCountExample"
#  }
# }


$ curl localhost:8090/jobs/4b1531b1-453e-4bc7-a0c0-408477e7c5f8

#
#  {
#    "status": "OK",
#    "result": {
#      "a": 2,
#      "b": 2,
#      "see": 1,
#      "c": 1
#    }
#  }
#

```


With context

```
curl -d "" 'localhost:8090/contexts/smartad-context?num-cpu-cores=4&memory-per-node=512m'
curl -XGET "localhost:8090/contexts"

curl -d "input.string = a b c a b see" 'localhost:8090/jobs?appName=smartad-test&classPath=spark.jobserver.WordCountExample&context=smartad-context&sync=true'

curl -XPOST 'localhost:8090/jobs?appName=smartad-spark-songplaycount&classPath=SongPlayCountApp&context=smartad-context&sync=true'

#check jobs 
curl -XGET "localhost:8090/jobs"

```


architecture
-------------------

```
--------------------------------------------------------------------------------
                                             |
                                             |                         |
clientdata.txt                               |    SparkContext         | filter
(src/main/resources/clientdata.txt)          |   (SongPlayCountApp)    |
                                             |
--------------------------------------------------------------------------------
```

Time taken
--------------

```
[success] Total time: 12 s, completed Dec 18, 2014 2:34:57 PM
```

References
-------------

https://github.com/H4ml3t/spark-scala-selective-wordcount/blob/master/src/main/scala/com/example/SparkSelectiveWordcount.scala

https://github.com/H4ml3t/spark-scala-maven-boilerplate-project/blob/master/src/main/scala/com/examples/MainExample.scala

https://nosqlnocry.wordpress.com/2015/02/27/how-to-build-a-spark-fat-jar-in-scala-and-submit-a-job/

https://spark.apache.org/docs/1.0.2/quick-start.html

http://spark.apache.org/examples.html

https://github.com/apache/spark/tree/master/examples/src/main/scala/org/apache/spark/examples

http://alvinalexander.com/scala/scala-apache-access-log-parser-library-java-jvm
