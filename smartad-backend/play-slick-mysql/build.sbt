name := """play-slick-mysql"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.4"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "com.typesafe.slick" %% "slick"      % "3.1.1",
  "org.slf4j"           % "slf4j-nop"  % "1.6.4",
  "mysql" % "mysql-connector-java" % "5.1.36"
)
