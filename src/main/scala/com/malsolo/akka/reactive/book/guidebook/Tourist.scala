package com.malsolo.akka.reactive.book.guidebook

import akka.actor.{Actor, ActorRef, Props}
import com.malsolo.akka.reactive.book.guidebook.Guidebook.Inquiry
import com.malsolo.akka.reactive.book.guidebook.Tourist.{Guidance, Start}

object Tourist {
  case class Guidance(code: String, description: String)
  case class Start(codes: Seq[String])

  def props(guidebook: ActorRef) = Props(new Tourist(guidebook))
}

class Tourist(guidebook: ActorRef) extends Actor {

  override def receive = {
    case Start(codes) =>
      codes.foreach(guidebook ! Inquiry(_))
    case Guidance(code, description) =>
      println(s"$code: $description")
  }
}
