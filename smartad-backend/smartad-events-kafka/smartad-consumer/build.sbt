name := "smartad-consumer"

version := "1.0"

scalaVersion := "2.10.4"

//kafka_2.10-0.8.1.1
libraryDependencies ++= Seq("org.apache.kafka" % "kafka_2.10" % "0.8.1.1"
  exclude("javax.jms", "jms")
  exclude("com.sun.jdmk", "jmxtools")
  exclude("com.sun.jmx", "jmxri"))

mainClass in (Compile, run) := Some("event.pipeline.consumer.EventConsumerApp")