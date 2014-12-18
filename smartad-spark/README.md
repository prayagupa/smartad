smartad-spark wordcount
==========================

install spark
-----------------

I put spark to `/usr/local/spark-1.1.1` and at `/usr/local/spark-1.1.1`

```
sbt/sbt assembly
```

install sbt, scala
--------------------

`sbt 0.13.7`

`scala 2.10.4`

package and run app
------------------------

```bash
sbt package && sbt run
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
