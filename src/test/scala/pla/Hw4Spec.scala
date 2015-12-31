package pla

import org.scalatest.{ShouldMatchers, FlatSpec}

class Hw4Spec extends FlatSpec with ShouldMatchers {

  /*
    ;; stride can be negative, in which case you can assume low is greater than high
    ;; must return an error if the stride is zero
   */

  import pla.Sequence.sequence

  "sequence" should "throw exception when stride is zero" in {
    a [RuntimeException] should be thrownBy  { sequence(4, 0, 10) }
  }

  "sequence" should "handle negative stride" in {
    sequence(10, -2, 5) should equal(List(10, 8, 6))
  }

  "sequence" should "detect infinite output and throw exception" in {
    sequence(5, 1, 4)
  }

  "sequence" should "generate list taking into account positive stride" in {
    sequence(3, 2, 7) should equal(List(3, 5, 7))
  }
}
