package com.xebia.contest.language

class AddressBook(val contacts: List[Contact] = Nil) {

  def add(contact: Contact) = {
    require(contact != null)
    require(get(contact).isEmpty)
    new AddressBook(contact :: contacts)
  }

  def remove(contact: Contact) = {
    require(contact != null)
    require(get(contact).isDefined)
    new AddressBook(contacts.filter(_ hasDifferentNameThan contact))
  }

  def get(contact: Contact) =
    contacts.find(_ hasSameNameAs contact)

}