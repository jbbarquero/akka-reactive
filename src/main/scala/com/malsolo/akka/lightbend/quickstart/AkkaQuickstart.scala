package com.malsolo.akka.lightbend.quickstart

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}

object Greeter {
  final case class WhoToGreet(who: String)
  def props(message: String, printerActor: ActorRef): Props = Props(new Greeter(message, printerActor))
  case object Greet
}

class Greeter(message: String, printerActor: ActorRef) extends Actor {

  import Greeter._
  import Printer._

  var greeting = ""

  override def receive = {
    case WhoToGreet(who) =>
      greeting = s"$message $who"
    case Greet =>
      printerActor ! Greeting(greeting)
  }
}

object Printer {
  def props(): Props = Props[Printer]
  final case class Greeting(message: String)
}

class Printer extends Actor with ActorLogging {
  import Printer._

  override def receive = {
    case Greeting(msg) =>
      log.info(s"Greeting received from ${sender()}: $msg")
  }
}

object AkkaQuickStart extends App {
  import Greeter._

  val system = ActorSystem("akkaQuickStart")

  val printer: ActorRef = system.actorOf(Printer.props, "printerACtor")

  val helloGreeter: ActorRef = system.actorOf(Greeter.props("Hello", printer), "helloActor")
  val holaActor: ActorRef = system.actorOf(Greeter.props("Hola", printer), "holActor")

  helloGreeter ! WhoToGreet("Akka")
  helloGreeter ! Greet

  holaActor ! WhoToGreet("Scala")
  holaActor ! Greet

  Thread.sleep(1000)

  system.terminate()
}

