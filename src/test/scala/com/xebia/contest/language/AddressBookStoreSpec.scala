package com.xebia.contest.language

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{BeforeAndAfterEach, FlatSpec}
import org.junit.rules.TemporaryFolder
import org.apache.commons.io.FileUtils

class AddressBookStoreSpec extends FlatSpec with ShouldMatchers with BeforeAndAfterEach with CustomMatchers {

  "AddressBookStore" should "allow an AddressBook to be written" in {
    val file = folder.newFile("store.txt")
    val store = new AddressBookStore(file)
    val book = new AddressBook(List(new Contact("Wilfred", "Springer")))
    store.store(book)
    file should exist
  }

  "AddressBookStore" should "be loadable" in {
    val file = folder.newFile("store.txt")
    FileUtils.writeStringToFile(file, """Holsteijn,van,Mark,0622374114
Rijk,,Maurits,0645261990
Teunissen,,Daan,0355381921""");
    val store = new AddressBookStore(file)
    val book = store.load
    val contact = book.get(new Contact("Daan", "Teunissen"))
    contact should be ('defined)
  }

  "AddressBookStore" should "allow an AddressBook to re-read" in {
    val file = folder.newFile("store.txt")
    val store = new AddressBookStore(file)
    val firstBook = new AddressBook(List(new Contact("Wilfred", None, "Springer", Some("09823092"))))
    store.store(firstBook)
    file should exist
    val secondBook = store.load
    val contact = secondBook.get(new Contact("Wilfred", "Springer"))
    contact should be ('defined)
    contact.get.phoneNumber should be (Some("09823092"))
  }

  val folder: TemporaryFolder = new TemporaryFolder

  override protected def beforeEach() {
    folder.create
  }

  override protected def afterEach() {
    folder.delete
  }

}