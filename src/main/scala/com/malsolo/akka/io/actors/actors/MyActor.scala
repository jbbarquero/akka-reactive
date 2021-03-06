package com.malsolo.akka.io.actors.actors

import akka.actor.Actor
import akka.event.Logging

class MyActor extends Actor {

  val log = Logging(context.system, this)

  override def receive = {
    case "test" => log.info("received test")
    case _ => log.info("received unknown message")
  }
}
