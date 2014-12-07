name := "DblpBackend"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  "mysql" % "mysql-connector-java" % "5.1.18",
  "org.mongodb" % "mongo-java-driver" % "2.12.3",
  "org.springframework.data" % "spring-data-mongodb" % "1.6.0.RELEASE",
  "com.google.code.gson" % "gson" % "2.3",
  "org.codehaus.jackson" % "jackson-core-asl" % "1.9.13",
  cache
)     

play.Project.playJavaSettings
