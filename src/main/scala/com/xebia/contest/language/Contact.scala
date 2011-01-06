package com.xebia.contest.language

class Contact(val firstName: String,
              val middleName: Option[String] = None,
              val lastName: String,
              val phoneNumber: Option[String] = None) {

  def this(firstName: String, middleName: String, lastName: String) =
    this(firstName, Option(middleName), lastName)

  def this(firstName: String, lastName: String) =
    this(firstName, None, lastName)

  def fullName =
    if (middleName.isDefined)
      firstName + " " + middleName + " " + lastName else
      firstName + " " + middleName

  def changePhoneNumberTo(phoneNumber: String) =
    new Contact(firstName, middleName, lastName, Some(phoneNumber))

  def hasSameNameAs(contact: Contact) =
    firstName == contact.firstName &&
    middleName == contact.middleName &&
    lastName == contact.lastName

  def hasDifferentNameThan(contact: Contact) =
    !hasSameNameAs(contact)

}


