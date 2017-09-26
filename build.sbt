name := """math-client"""
organization := "com.lifeway.math.client"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"
val scalapactVersion = "2.1.3"

libraryDependencies ++= Seq(
  guice, ws,
  "org.scalaj"     %% "scalaj-http"         % "2.3.0",
  "org.json4s"     %% "json4s-native"       % "3.3.0",
  "com.itv" %% "scalapact-core" % scalapactVersion % Test,
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
  "com.itv"        %% "scalapact-scalatest" % scalapactVersion % Test
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.lifeway.math.client.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.lifeway.math.client.binders._"
