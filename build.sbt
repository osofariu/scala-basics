name := "basics"

version := "1.0"

scalaVersion := "2.11.7"

//https://oss.sonatype.org/content/groups/scala-tools

//libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.5"

libraryDependencies += "org.mongodb" % "mongo-java-driver" % "3.2.0-rc0"

//libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.0"

//libraryDependencies += "org.scalamock" %% "scalamock-scalatest-support" % "3.2" % "test"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.1",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test",
  "org.scala-lang" % "scala-reflect" % "2.11.7",
  "org.scala-lang.modules" %% "scala-xml" % "1.0.4"
)