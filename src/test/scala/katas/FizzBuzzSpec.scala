package katas

import org.scalatest.{FlatSpec, Matchers}

class FizzBuzzSpec extends FlatSpec with Matchers {

   "fizz Buzz" should "return Fizz when appropriate for range" in {
      val fb  = new FizzBuzz(1 to 3)
      print(fb.fizzBuzz())
      fb.fizzBuzz() should be (List("1", "2", "FIZZ"))
  }
}
