name := "DblpFrontend"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "com.google.code.gson" % "gson" % "2.3",
  "org.codehaus.jackson" % "jackson-core-asl" % "1.9.13",
  cache
)     

play.Project.playJavaSettings
