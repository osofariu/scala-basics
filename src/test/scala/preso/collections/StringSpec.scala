package preso.collections

import org.scalatest.{Matchers, FlatSpec}

class StringSpec extends FlatSpec with Matchers {

  "String" should "allow me to split it at designated characters" in {
    val parts = "This good".split(" ")
    parts.length should equal(2)
    parts(0) should equal("This")
    parts(1) should equal("good")


    val twoParts = "Mother knows best".split(" ", 2)
    twoParts.length should equal(2)
    twoParts(0) should equal("Mother")
    twoParts(1) should equal("knows best")
  }

  "String" should "allow me to split it at index" in {
    val (part1, part2) = "Longerberger".splitAt(6)
    part1 should equal("Longer")
    part2 should equal("berger")
  }

}
