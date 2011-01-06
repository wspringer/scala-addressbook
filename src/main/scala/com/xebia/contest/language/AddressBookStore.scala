package com.xebia.contest.language

import io.Source._
import java.lang.String
import java.io.{FileWriter, Writer, File}

class AddressBookStore(file: File) {

  def store(book: AddressBook) {
    require(file.canWrite)
    var writer: Writer = null
    try {
      writer = new FileWriter(file)
      for (line <- book.contacts.map(lineFromContact)) {
        writer.write(line)
        writer.write("\n")
      }
    } finally {
      writer.close
    }
  }

  def load() = {
    require(file.exists)
    require(file.canRead)
    new AddressBook(fromFile(file).getLines.map(contactFromLine).toList)
  }

  private def contactFromLine(line: String) = {
    val entries = line.split(",")
    assume(entries.size >= 4)
    new Contact(
      lastName = entries(0),
      middleName = if (entries(1).isEmpty) None else Some(entries(1)),
      firstName = entries(2),
      phoneNumber = if (entries(3).isEmpty) None else Some(entries(3))
    )
  }

  private def lineFromContact(contact: Contact) =
    List(contact.lastName,
      contact.middleName.getOrElse(""),
      contact.firstName,
      contact.phoneNumber.getOrElse("")).mkString(",")


}