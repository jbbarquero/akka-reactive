package com.malsolo.akka.reactive.book.guidebook

import java.util.{Locale, Scanner}

import akka.actor.ActorSystem
import akka.util.Timeout
import com.malsolo.akka.reactive.book.guidebook.Tourist.Start

import scala.concurrent.duration.SECONDS
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

//Run with sbt –DPORT=2552 "runMain com.malsolo.akka.reactive.book.guidebook.TouristMain"
object TouristMain extends App {

  val system = ActorSystem("TouristSystem")

  val path = "akka.tcp://BookSystem@127.0.0.1:2553/user/guidebook"

  implicit val timeout = Timeout(5, SECONDS)

  system.actorSelection(path).resolveOne().onComplete {
    case Success(guidebook) =>
      val tourist = system.actorOf(Tourist.props(guidebook), "tourist")
      tourist ! Start(Locale.getISOCountries)
    case Failure(e) =>
      println(e)
  }

  Thread.sleep(200)
  println("Press any key to exit...")
  new Scanner(System.in).nextLine()
  system.terminate()
  println("End TouristMain")
}
