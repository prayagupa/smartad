//name := "smartad-recommendation-engine"

version := "1.0"

scalaVersion := "2.10.4"

val sparkVersion = "1.2.0"

libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion % "provided"

//libraryDependencies += "org.apache.spark" %% "spark-mllib" % sparkVersion

libraryDependencies += "org.reactivemongo" %% "reactivemongo" % "0.10.5.0.akka23"

resolvers += "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/"

//resolvers += "Akka Repository" at "http://repo.akka.io/releases/"
