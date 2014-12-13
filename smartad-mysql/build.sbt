name := """connect-to-mysql-with-jdbc"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  //javaJdbc
)

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.27"