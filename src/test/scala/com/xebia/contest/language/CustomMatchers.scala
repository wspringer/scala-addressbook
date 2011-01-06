package com.xebia.contest.language

import org.scalatest.matchers.Matcher
import org.scalatest.matchers.MatchResult

trait CustomMatchers {
  class FileExistsMatcher extends Matcher[java.io.File] {
    def apply(left: java.io.File) = {
      val fileOrDir = if (left.isFile) "file" else "directory"
      val failureMessageSuffix =
        fileOrDir + " named " + left.getName + " did not exist"
      val negatedFailureMessageSuffix =
        fileOrDir + " named " + left.getName + " existed"

      MatchResult(
        left.exists,
        "The " + failureMessageSuffix,
        "The " + negatedFailureMessageSuffix,
        "the " + failureMessageSuffix,
        "the " + negatedFailureMessageSuffix
      )
    }
  }
  val exist = new FileExistsMatcher
}
// Make them easy to import with:
// import CustomMatchers._
object CustomMatchers extends CustomMatchers