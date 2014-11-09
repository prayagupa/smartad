logLevel := Level.Debug

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.2.2")

libraryDependencies ++= Seq("com.google.zxing" % "core" % "2.0",
 	                    "mysql" % "mysql-connector-java" % "5.1.12",
                            "com.typesafe.slick" %% "slick" % "2.1.0",
			    "org.slf4j" % "slf4j-nop" % "1.6.4"
			   )
