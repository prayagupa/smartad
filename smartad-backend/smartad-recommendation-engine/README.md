recommendation engine
========================

```
/usr/local/spark-1.2.0/sbin/start-master.sh
/usr/local/spark-1.2.0/bin/spark-class org.apache.spark.deploy.worker.Worker spark://prayagupd:7077

hdfs dfs -copyFromLocal src/main/resources/ml-100k/ua.base /user/hduser/
hdfs dfs -copyFromLocal src/main/resources/ml-100k/ua.test /user/hduser/
hdfs dfs -copyFromLocal src/main/resources/ml-100k/u.item /user/hduser/


sbt package


/usr/local/spark-1.2.0/bin/spark-submit --class MovieSimilarities --master spark://prayagupd:7077 target/scala-2.10/recommendation-engine_2.10-1.0.jar spark://prayagupd:7077
...
...
...

15/05/16 17:57:54 INFO scheduler.DAGScheduler: Job 1 finished: collect at MovieSimilarities.scala:134, took 373.019793 s
Star Wars (1977) | Fathers' Day (1997) | -0.6625 | -0.4417 | 0.9074 | 0.0397
Star Wars (1977) | Jason's Lyric (1994) | -0.9661 | -0.3978 | 0.8110 | 0.0141
Star Wars (1977) | Lightning Jack (1994) | -0.7906 | -0.3953 | 0.9361 | 0.0202
Star Wars (1977) | Marked for Death (1990) | -0.5922 | -0.3807 | 0.8729 | 0.0361
Star Wars (1977) | Mixed Nuts (1994) | -0.6219 | -0.3731 | 0.8806 | 0.0303
Star Wars (1977) | Poison Ivy II (1995) | -0.7443 | -0.3722 | 0.7169 | 0.0201
Star Wars (1977) | In the Realm of the Senses (Ai no corrida) (1976) | -0.8090 | -0.3596 | 0.8108 | 0.0162
Star Wars (1977) | What Happened Was... (1994) | -0.9045 | -0.3392 | 0.8781 | 0.0121
Star Wars (1977) | Female Perversions (1996) | -0.8039 | -0.3310 | 0.8670 | 0.0141
Star Wars (1977) | Celtic Pride (1996) | -0.6062 | -0.3175 | 0.8998 | 0.0220

```