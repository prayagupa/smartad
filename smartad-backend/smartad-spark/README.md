smartad-spark wordcount
==========================

build/install spark
-----------------

I put spark to `/usr/local/spark-1.1.1` and at `/usr/local/spark-1.1.1`

```
sbt/sbt assembly
```

start spark-jobserver
-----------------------

```
cd spark-jobserver
sbt publish-local
sbt 
> reStart config/local.conf
> reStop
```

check log at `tail -f job-server/job-server-local.log`, server must be running at port `8090`. (verify with `netstat -vatn | grep 8090`)


install sbt, scala
--------------------

`sbt 0.13.7`

`scala 2.10.4`

[start spark master and workers in standalone mode](http://spark.apache.org/docs/1.0.1/spark-standalone.html)
----------------------------------------------------

```
$ /usr/local/spark-1.1.1/sbin/start-master.sh 
starting org.apache.spark.deploy.master.Master, logging to /usr/local/spark-1.1.1/sbin/../logs/spark-prayagupd-org.apache.spark.deploy.master.Master-1-prayagupd.out

```

Check log at `tail -f /usr/local/spark-1.1.1/sbin/../logs/spark-prayagupd-org.apache.spark.deploy.master.Master-1-prayagupd.out`

goto `http://localhost:8080/`, something as below will be seen, 

```
URL: spark://prayagupd:7077
Workers: 0
Cores: 0 Total, 0 Used
Memory: 0.0 B Total, 0.0 B Used
Applications: 0 Running, 0 Completed
Drivers: 0 Running, 0 Completed
Status: ALIVE
```

start workers at `http://localhost:8081/`

```
/usr/local/spark-1.1.1/bin/spark-class org.apache.spark.deploy.worker.Worker spark://prayagupd:7077
```

package and submit app
------------------------

```bash
sbt package

/usr/local/spark-1.1.1/bin/spark-submit  --class "WordCountApp" --master spark://prayagupd:7077 target/scala-2.10/smartad-spark-wordcount_2.10-1.0.jar 

##without starting spark master, workers
/usr/local/spark-1.1.1/bin/spark-submit  --class "WordCountApp" --master local[4] target/scala-2.10/smartad-spark-wordcount_2.10-1.0.jar 
```

spark-worker stdout will be as below, 

```bash
14/12/21 16:32:47 INFO worker.Worker: Asked to launch executor app-20141221163247-0000/0 for Smartad Spark Wordcount
14/12/21 16:32:48 INFO worker.ExecutorRunner: Launch command: "/usr/local/jdk1.7.0_05/bin/java" "-cp" "::/usr/local/spark-1.1.1/conf:/usr/local/spark-1.1.1/assembly/target/scala-2.10/spark-assembly-1.1.1-hadoop1.0.4.jar:/usr/local/hadoop-2.3.0-cdh5.0.1//etc/hadoop:/usr/local/hadoop-2.3.0-cdh5.0.1//etc/hadoop" "-XX:MaxPermSize=128m" "-Dspark.driver.port=48894" "-Xms512M" "-Xmx512M" "org.apache.spark.executor.CoarseGrainedExecutorBackend" "akka.tcp://sparkDriver@prayagupd.local:48894/user/CoarseGrainedScheduler" "0" "prayagupd.local" "4" "akka.tcp://sparkWorker@prayagupd.local:58149/user/Worker" "app-20141221163247-0000"
14/12/21 16:33:32 INFO worker.Worker: Asked to kill executor app-20141221163247-0000/0
14/12/21 16:33:32 INFO worker.ExecutorRunner: Runner thread for executor app-20141221163247-0000/0 interrupted
14/12/21 16:33:32 INFO worker.ExecutorRunner: Killing process!
14/12/21 16:33:32 INFO worker.Worker: Executor app-20141221163247-0000/0 finished with state KILLED exitStatus 1
14/12/21 16:33:32 INFO actor.LocalActorRef: Message [akka.remote.transport.ActorTransportAdapter$DisassociateUnderlying] from Actor[akka://sparkWorker/deadLetters] to Actor[akka://sparkWorker/system/transports/akkaprotocolmanager.tcp0/akkaProtocol-tcp%3A%2F%2FsparkWorker%40192.168.1.2%3A56242-2#767330346] was not delivered. [1] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
14/12/21 16:33:33 ERROR remote.EndpointWriter: AssociationError [akka.tcp://sparkWorker@prayagupd.local:58149] -> [akka.tcp://sparkExecutor@prayagupd.local:45652]: Error [Association failed with [akka.tcp://sparkExecutor@prayagupd.local:45652]] [
akka.remote.EndpointAssociationException: Association failed with [akka.tcp://sparkExecutor@prayagupd.local:45652]
Caused by: akka.remote.transport.netty.NettyTransport$$anonfun$associate$1$$anon$2: Connection refused: prayagupd.local/192.168.1.2:45652
]
14/12/21 16:33:33 ERROR remote.EndpointWriter: AssociationError [akka.tcp://sparkWorker@prayagupd.local:58149] -> [akka.tcp://sparkExecutor@prayagupd.local:45652]: Error [Association failed with [akka.tcp://sparkExecutor@prayagupd.local:45652]] [
akka.remote.EndpointAssociationException: Association failed with [akka.tcp://sparkExecutor@prayagupd.local:45652]
Caused by: akka.remote.transport.netty.NettyTransport$$anonfun$associate$1$$anon$2: Connection refused: prayagupd.local/192.168.1.2:45652
]
14/12/21 16:33:33 ERROR remote.EndpointWriter: AssociationError [akka.tcp://sparkWorker@prayagupd.local:58149] -> [akka.tcp://sparkExecutor@prayagupd.local:45652]: Error [Association failed with [akka.tcp://sparkExecutor@prayagupd.local:45652]] [
akka.remote.EndpointAssociationException: Association failed with [akka.tcp://sparkExecutor@prayagupd.local:45652]
Caused by: akka.remote.transport.netty.NettyTransport$$anonfun$associate$1$$anon$2: Connection refused: prayagupd.local/192.168.1.2:45652
]

```

Also spark-worker UI will give information this app.

for spark-jobserver WordCountJob
-------------------------------------

```
$ sbt job-server-tests/package

$ curl --data-binary @job-server-tests/target/job-server-tests-0.4.2-SNAPSHOT.jar localhost:8090/jars/smartad-test

$ curl -d "input.string = a b c a b see" 'localhost:8090/jobs?appName=smartad-test&classPath=spark.jobserver.WordCountExample'

```

check jobs 

```
curl -XGET "localhost:8090/jobs"
```

architecture
-------------------

```
--------------------------------------------------------------------------------
                                             |
                                             |                     |
clientdata.txt                               |    SparkContext     | filter
(src/main/resources/clientdata.txt)          |   (WordCountApp)    |
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

https://spark.apache.org/docs/1.0.2/quick-start.html

http://spark.apache.org/examples.html

https://github.com/apache/spark/tree/master/examples/src/main/scala/org/apache/spark/examples

http://alvinalexander.com/scala/scala-apache-access-log-parser-library-java-jvm
