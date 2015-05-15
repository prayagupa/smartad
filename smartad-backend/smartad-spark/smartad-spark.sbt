name := "smartad-spark-songplaycount"

version := "1.0"

scalaVersion := "2.10.4"

//libraryDependencies += "org.apache.spark" %% "spark-core" % "1.1.1"
libraryDependencies += "org.apache.spark" % "spark-core_2.10" % "1.2.0"

libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "2.2.0"

libraryDependencies += "org.apache.hadoop" % "hadoop-hdfs" % "2.2.0"

resolvers += "Akka Repository" at "http://repo.akka.io/releases/"
