package misc.collections

import org.scalatest.{Matchers, FlatSpec}

class FunctionalSpec extends FlatSpec with Matchers {

  "Looping Ints" should "allow me to add them" in {
    (1 to 5).reduce(_+_) should equal(15)
  }

  "Looping Ints with Function" should "allow me to apply function to reduce them" in {

    def min (i : Int, j: Int) : Boolean = {i <= j}

    (23 to 25 ).foldLeft(23) {(acc, i) => if (acc <= i) acc else i} should equal(23)
    (23 to 30).reduceLeft {(first, second) => if (first <= second) first else second} should equal(23)
    //(23 to 25).reduceLeft {min} should equal(23)
  }
}
