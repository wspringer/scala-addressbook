package com.xebia.contest.language

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec

class AddressBookSpec extends FlatSpec with ShouldMatchers {

  "AddressBook" should "allow contacts to be added" in {
    val book = new AddressBook
    val contact = new Contact("Wilfred", "Springer")
    val updated = book.add(contact)
    updated.get(contact) should be ('defined)
  }

  "AddressBook" should "allow contacts to be removed" in {
    val contact = new Contact("Wilfred", "Springer")
    val book = new AddressBook(List(contact))
    val updated = book.remove(contact)
    updated.get(contact) should be (None)
  }

}