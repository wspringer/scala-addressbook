package com.xebia.contest.language

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class ContactSpec extends FlatSpec with ShouldMatchers {

  "Contact" should "be constructed with names " in {
    val contact = new Contact("Youp", "van't", "Hek")
    contact.firstName should equal("Youp")
    contact.middleName should equal(Some("van't"))
    contact.lastName should equal("Hek")
    contact.phoneNumber should equal(None)
  }

  "Contact" should "allow phonenumber to be altered " in {
    val contact = new Contact("Youp", "van't", "Hek")
    val number = "0829384"
    val updated = contact.changePhoneNumberTo(number)
    updated.phoneNumber should equal(Some(number))
  }

  "Contact" should "have fullName composed correctly" in {
    val contact = new Contact("Youp", "van't", "Hek")
    contact.fullName should startWith(contact.firstName)
    contact.fullName should startWith(contact.firstName + " " + contact.middleName)
    contact.fullName should endWith(contact.lastName)
  }

}