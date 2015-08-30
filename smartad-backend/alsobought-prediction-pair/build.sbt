name := "AlsoBought"

version := "1.0"

scalaVersion := "2.10.3"

//esVersion := "2.0.2"

libraryDependencies += "org.apache.hadoop" % "hadoop-core" % "1.2.0" % "provided"

libraryDependencies += "org.elasticsearch" % "elasticsearch-hadoop-mr" % "2.0.2"

resolvers += "Apache Repository" at "https://repository.apache.org/content/repositories"

resolvers += "cloudera" at "https://repository.cloudera.com/artifactory/cloudera-repos"