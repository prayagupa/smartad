import play.Project._

name := "smartad-backend"

version := "1.0"

playScalaSettings

libraryDependencies ++= Seq(
	                    jdbc,
			    anorm,
	                    "com.google.zxing"   % "core"                 % "2.0",
 	                    "mysql"              % "mysql-connector-java" % "5.1.27",
                            "com.typesafe.slick" %% "slick"               % "2.1.0",
			    "com.typesafe.play"  %% "play-slick"          % "0.6.1",
			    "org.slf4j"          % "slf4j-nop"            % "1.6.4",
			    "com.cloudphysics" % "jerkson_2.10" % "0.6.3" 
//			    "io.spray" %%  "spray-json" % "1.3.1"
			   )

