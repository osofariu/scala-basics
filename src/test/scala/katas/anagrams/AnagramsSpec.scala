package katas.anagrams

import org.scalatest.{Matchers, FlatSpec}

class AnagramsSpec extends FlatSpec with Matchers {

  def combine(x: Char, anagram: String): List[String] = {
    (0 to anagram.length).toStream.map(i => insertCharAt(x, anagram, i)).toList
  }

  def combineWithList(x: Char, strings: List[String]) = {
    strings.flatMap { str => combine(x, str) }
  }

  def insertCharAt(x: Char, s: String, i: Int): String = {
    val (first, second) = s.splitAt(i)
    first + String.valueOf(x) + second
  }

  def anagrams(s: String): List[String] = {
    if (s.length <= 0) {
      List()
    } else if (s.length == 1) {
      List(s)
    }
    else {
      s.toStream.flatMap { x =>
        val sWithoutX = s.split(x).reduce(_ + _)
        combineWithList(x, anagrams(sWithoutX))
      }.toList.distinct.sorted
    }
  }

  "combine" should "create a list of the same length as original string with new char inserted between each character" in {
    combine('A', "BCD").sorted should equal(List("ABCD", "BACD", "BCAD", "BCDA"))
  }

  "combineWithList" should "make a flat list with character applied" in {
    combineWithList('X', List("ABC", "12", "#", "")) should equal(List("XABC", "AXBC", "ABXC", "ABCX", "X12", "1X2", "12X", "X#", "#X", "X"))
  }

  "insertCharAt" should "insertCharacter at the proper place" in {
    insertCharAt('X', "123", 0) should equal("X123")
    insertCharAt('X', "123", 1) should equal("1X23")
    insertCharAt('X', "123", 2) should equal("12X3")
    insertCharAt('X', "123", 3) should equal("123X")
  }

  "anagram" should "produce array with one result of same value for strings of length 1" in {
    anagrams("X") should equal(List("X"))
  }

  "anagram" should "produce two unique combinations for input of length 2" in {
    anagrams("AB") should equal(List("AB", "BA"))
  }

  "anagram" should "produce six unique combinations for input of length 3" in {
    anagrams("ABC") should equal(List("ABC", "ACB", "BAC", "BCA", "CAB", "CBA"))
  }
}
