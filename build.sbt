name := "goliath"

version := "0.1"

scalaVersion := "2.12.4"


libraryDependencies += "io.undertow" % "undertow-core" % "1.4.22.Final"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.4"

libraryDependencies += "io.spray" %%  "spray-json" % "1.3.3"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2"
)

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"
libraryDependencies += "org.scalamock" %% "scalamock" % "4.0.0" % Test

resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"
addSbtPlugin("com.artima.supersafe" % "sbtplugin" % "1.1.3")