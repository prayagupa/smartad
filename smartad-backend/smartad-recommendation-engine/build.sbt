name := "smartad-recommendation-engine"

version := "1.0"

scalaVersion := "2.10.4"

val sparkVersion = "1.2.0"

libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion

libraryDependencies += "org.apache.spark" %% "spark-mllib" % sparkVersion

//resolvers += "Akka Repository" at "http://repo.akka.io/releases/"
