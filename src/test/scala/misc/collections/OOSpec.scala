package misc.collections

import org.scalatest.{Matchers, FlatSpec}

class OOSpec extends FlatSpec with Matchers {

  class ListOfPairs(pairs: List[(Int, Int)]) {
    def myPairs = pairs
    def size = pairs.length
    def this(a: Int, b: Int) = this(List(Tuple2(a, b)))
    override def toString = pairs.mkString(",")
  }

  object ListOfPairs {
    def apply(l: List[Int]): ListOfPairs = l match {
      case Nil => new ListOfPairs(Nil)
      case first :: second :: rest =>  combine(new ListOfPairs(first, second), apply(rest))
      case first :: rest => combine(new ListOfPairs(first, 0), apply(rest))
    }

    def combine(originalPairs: ListOfPairs, otherPairs: ListOfPairs): ListOfPairs = {
      new ListOfPairs(originalPairs.myPairs ::: otherPairs.myPairs)
    }
  }

  "ListOfPairs" should "allow no pairs in constructor when provided as a list" in {
    val v = new ListOfPairs(List())
    v.size should equal(0)
    println(v)
  }

  "ListOfPairs" should "allow one pair as argument and have size of 1" in {
    val v = new ListOfPairs(1, 2)
    v.size should equal(1)
    println(v)
  }

  "ListOfPairs" should "allow a list of pairs as argument and have size of that list" in {
    val v = ListOfPairs(List(1, 2, 3, 4))
    v.size should equal(2)
    println(v)
  }

  "ListOfPairs" should "be creatable from a list of its of odd size" in {
    val v = ListOfPairs(List(1, 2, 3, 4, 5))
    v.size should equal(3)
    println(v)
  }
}
