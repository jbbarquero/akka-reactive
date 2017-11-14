package com.malsolo.akka.reactive.book.guidebook

import java.util.{Currency, Locale}

import akka.actor.{Actor, Props}
import com.malsolo.akka.reactive.book.guidebook.Guidebook.Inquiry
import com.malsolo.akka.reactive.book.guidebook.Tourist.Guidance

object Guidebook {
  case class Inquiry(code: String)

  def props() = Props[Guidebook]
}

class Guidebook extends Actor {

  def describe(locale: Locale) = {
    s"""In ${locale.getDisplayCountry},
        ${locale.getDisplayLanguage()} is spoken and the currency
        is the ${Currency.getInstance(locale).getDisplayName}
      """
  }

  override def receive = {
    case Inquiry(code) =>
      println(s"Actor ${self.path.name}")
      Locale.getAvailableLocales.filter(_.getCountry == code).foreach {
        locale => sender() ! Guidance(code, describe(locale))
      }

  }
}
