package com.malsolo.akka.reactive.book.guidebook

import java.util.Scanner

import akka.actor.ActorSystem

import scala.io.StdIn

//Run with sbt -DPORT=2553 "runMain com.malsolo.akka.reactive.book.guidebook.GuidebookMain"
object GuidebookMain extends App {

  val system = ActorSystem("BookSystem")
  val guidebook = system.actorOf(Guidebook.props(), "guidebook")

  Thread.sleep(200)
  println("Press any key to exit...")
  StdIn.readLine()
  system.terminate()
  println("End GuidebookMain")
}
