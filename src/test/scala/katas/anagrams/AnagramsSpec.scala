package katas.anagrams

import org.scalatest.{Matchers, FlatSpec}

class AnagramsSpec extends FlatSpec with Matchers {

  def combine(x: Char, strings: List[String]) = {
    strings.map {str => String.valueOf(x) + str}
  }
  
  def anagrams(s: String): List[String] = {
    if (s.length == 1) List(s)
    else {
      s.toStream.flatMap { x =>
        combine(x, anagrams(s.split(x).reduce(_ + _)))
      }.toList
    }
  }

  "combine" should "make a flat list with character applied" in {
    combine('X', List("ABC", "12", "#", "")) should equal(List("XABC", "X12", "X#", "X"))
  }

  "anagrams" should "handle the base case of empty string" in {
    anagrams("") should equal(List())
  }

  "anagram" should "produce array with one result of same value for strings of length 1" in {
    anagrams("X") should equal(List("X"))
  }

  "anagrams" should "produce two unique combinations for input of length 2" in {
    anagrams("AB") should equal(List("AB", "BA"))
  }

  "anagrams" should "produce six unique combinations for input of length 3" in {
    anagrams("ABC") should equal(List("ABC", "ACB", "BAC", "BCA", "CAB", "CBA"))
  }
}
