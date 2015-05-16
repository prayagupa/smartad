recommendation engine
========================

Movies (u.item)
==================

a tab separated list of movies, read more at http://grouplens.org/datasets/movielens/

```
movieid|movietitle|releasedate|videoreleasedate| IMDb URL                            | genres....
1|Toy Story (1995)|01-Jan-1995||http://us.imdb.com/M/title-exact?Toy%20Story%20(1995)|0|0|0|1|1|1|0|0|0|0|0|0|0|0|0|0|0|0|0
2|GoldenEye (1995)|01-Jan-1995||http://us.imdb.com/M/title-exact?GoldenEye%20(1995)|0|1|1|0|0|0|0|0|0|0|0|0|0|0|0|0|1|0|0
3|Four Rooms (1995)|01-Jan-1995||http://us.imdb.com/M/title-exact?Four%20Rooms%20(1995)|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|1|0|0
4|Get Shorty (1995)|01-Jan-1995||http://us.imdb.com/M/title-exact?Get%20Shorty%20(1995)|0|1|0|0|0|1|0|0|1|0|0|0|0|0|0|0|0|0|0
5|Copycat (1995)|01-Jan-1995||http://us.imdb.com/M/title-exact?Copycat%20(1995)|0|0|0|0|0|0|1|0|1|0|0|0|0|0|0|0|1|0|0
6|Shanghai Triad (Yao a yao yao dao waipo qiao) (1995)|01-Jan-1995||http://us.imdb.com/Title?Yao+a+yao+yao+dao+waipo+qiao+(1995)|0|0|0|0|0|0|0|0|1|0|0|0|0|0|0|0|0|0|0
7|Twelve Monkeys (1995)|01-Jan-1995||http://us.imdb.com/M/title-exact?Twelve%20Monkeys%20(1995)|0|0|0|0|0|0|0|0|1|0|0|0|0|0|0|1|0|0|0
8|Babe (1995)|01-Jan-1995||http://us.imdb.com/M/title-exact?Babe%20(1995)|0|0|0|0|1|1|0|0|1|0|0|0|0|0|0|0|0|0|0
9|Dead Man Walking (1995)|01-Jan-1995||http://us.imdb.com/M/title-exact?Dead%20Man%20Walking%20(1995)|0|0|0|0|0|0|0|0|1|0|0|0|0|0|0|0|0|0|0
10|Richard III (1995)|22-Jan-1996||http://us.imdb.com/M/title-exact?Richard%20III%20(1995)|0|0|0|0|0|0|0|0|1|0|0|0|0|0|0|0|0|1|0
11|Seven (Se7en) (1995)|01-Jan-1995||http://us.imdb.com/M/title-exact?Se7en%20(1995)|0|0|0|0|0|0|1|0|0|0|0|0|0|0|0|0|1|0|0
12|Usual Suspects, The (1995)|14-Aug-1995||http://us.imdb.com/M/title-exact?Usual%20Suspects,%20The%20(1995)|0|0|0|0|0|0|1|0|0|0|0|0|0|0|0|0|1|0|0
13|Mighty Aphrodite (1995)|30-Oct-1995||http://us.imdb.com/M/title-exact?Mighty%20Aphrodite%20(1995)|0|0|0|0|0|1|0|0|0|0|0|0|0|0|0|0|0|0|0
14|Postino, Il (1994)|01-Jan-1994||http://us.imdb.com/M/title-exact?Postino,%20Il%20(1994)|0|0|0|0|0|0|0|0|1|0|0|0|0|0|1|0|0|0|0
15|Mr. Holland's Opus (1995)|29-Jan-1996||http://us.imdb.com/M/title-exact?Mr.%20Holland's%20Opus%20(1995)|0|0|0|0|0|0|0|0|

...
50|Star Wars (1977)|01-Jan-1977||http://us.imdb.com/M/title-exact?Star%20Wars%20(1977)|0|1|1|0|0|0|0|0|0|0|0|0|0|0|1|1|0|1|0
...
...
1682|Scream of Stone (Schrei aus Stein) (1991)|08-Mar-1996||http://us.imdb.com/M/title-exact?Schrei%20aus%20Stein%20(1991)|0|0|0|0|0|0|0|0|1|0|0|0|0|0|0|0|0|0|0

```

Train file(ua.base)
============
*.base and *.test split the u data into a training set and a test set with exactly 10 ratings per user in the test set.

```

1	1	5	874965758
1	2	3	876893171
1	3	4	878542960
1	4	3	876893119
1	5	3	889751712
1	6	5	887431973
1	7	4	875071561
1	8	1	875072484
1	9	5	878543541
1	10	3	875693118
1	11	2	875072262
1	12	5	878542960
1	13	5	875071805
1	14	5	874965706
1	15	5	875071608
1	16	5	878543541
...
...
943	1330	3	888692465

```

Test file (ua.test)
============

```

1	20	4	887431883
1	33	4	878542699
1	61	4	878542420
1	117	3	874965739
1	155	2	878542201
1	160	4	875072547
1	171	5	889751711
1	189	3	888732928
1	202	5	875072442
1	265	4	878542441
2	13	4	888551922
2	50	5	888552084
2	251	5	888552084
2	280	3	888551441
2	281	3	888980240
2	290	3	888551441
2	292	4	888550774

```

get recommendation
=======================

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