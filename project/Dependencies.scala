import sbt._

object Dependencies {
  val scalaTestVersion = "3.0.3"
  val akkaActorVersion = "2.5.6"

  lazy val scalaTest = "org.scalatest" %% "scalatest" % scalaTestVersion

  lazy val akkaActor =  "com.typesafe.akka" %% "akka-actor" % akkaActorVersion
  lazy val akkaActorTest = "com.typesafe.akka" %% "akka-testkit" % akkaActorVersion
}
