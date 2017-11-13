import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.malsolo",
      scalaVersion := "2.12.3",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "akka-reactive",
    libraryDependencies ++= Seq(
      scalaTest % Test
    )
  )
