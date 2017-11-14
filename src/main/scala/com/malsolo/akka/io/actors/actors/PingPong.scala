package com.malsolo.akka.io.actors.actors

import akka.actor.{Actor, ActorRef, ActorSystem, PoisonPill, Props}
import scala.concurrent.duration._

case object Ping
case object Pong

class Pinger extends Actor{

  var countDown = 100

  override def receive = {
    case Pong =>
      println(s"${self.path} received pong, down ${countDown}" )

      if (countDown > 0) {
        countDown -= 1
        sender() ! Ping
      }
      else {
        println("End game")
        sender() ! PoisonPill
        self ! PoisonPill
      }
  }
}

class Ponger(pinger: ActorRef) extends Actor {
  override def receive = {
    case Ping =>
      println(s"${self.path} received ping")
      pinger ! Pong
  }
}

object PingPong extends App {
  val system = ActorSystem("pingPong")

  val pinger = system.actorOf(Props[Pinger], "pinger")
  val ponger = system.actorOf(Props(classOf[Ponger], pinger), "ponger")

  import system.dispatcher
  system.scheduler.scheduleOnce(500 millis) {
    ponger ! Ping
  }

  Thread.sleep(2000)
  println("END PING PONG")
  system.terminate()

}
