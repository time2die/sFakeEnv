scalaVersion := "2.12.3"

name := "FalseEnv"
organization := "org.time2die"
version := "1.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.0.11",
)
libraryDependencies += "com.github.pathikrit" %% "better-files" % "3.4.0"

//addSbtPlugin("io.get-coursier" % "sbt-coursier" % "1.0.1")
//addSbtPlugin("com.lucidchart" % "sbt-scalafmt" % "1.15")
//libraryDependencies += "org.typelevel" %% "cats" % "0.9.0"
libraryDependencies += "org.scala-sbt" %% "zinc" % "1.0.0-X10"
//libraryDependencies += "net.debasishg" %% "redisclient" % "3.4"
