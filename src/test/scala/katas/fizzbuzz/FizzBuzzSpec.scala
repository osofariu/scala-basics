package katas.fizzbuzz

import org.scalatest.{FlatSpec, Matchers}

class FizzBuzzSpec extends FlatSpec with Matchers {

  "fizz buzz" should "return Fizz every multiple of 3 for range 1 to 3" in {
     val fb  = new FizzBuzzMaker(1 to 3)
     fb.make() should be (List("1", "2", "FIZZ"))
  }

  "fizz buzz" should "return Buzz every multiple of 5 for range 1 to 5" in {
    val fb = new FizzBuzzMaker(1 to 5)
    fb.make() should be (List("1", "2", "FIZZ", "4", "BUZZ"))
  }

  "fizz buzz" should "return FizzBuzz every multiple of 3 and 5 for range 1 to 15" in {
    val fb = new FizzBuzzMaker(1 to 15)
    fb.make() should be (List("1", "2", "FIZZ", "4", "BUZZ", "FIZZ", "7", "8", "FIZZ", "BUZZ", "11", "FIZZ", "13", "14", "FIZZBUZZ"))
  }
}
