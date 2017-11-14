package com.malsolo.akka.reactive.book.guidebook

import java.util.Locale

import akka.actor.ActorSystem
import com.malsolo.akka.reactive.book.guidebook.Tourist.Start

object Main extends App {

  val system = ActorSystem("guidebook")

  val guidebook = system.actorOf(Guidebook.props(), "guidebook")
  val tourist = system.actorOf(Tourist.props(guidebook), "tourist")

  tourist ! Start(Locale.getISOCountries)

  Thread.sleep(2000)
  system.terminate()
  println("END MAIN GUIDEBOOk AND TOURIST")
}
