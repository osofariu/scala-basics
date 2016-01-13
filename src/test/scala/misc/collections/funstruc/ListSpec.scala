package misc.collections.funstruc

import org.scalatest.{Matchers, path}

sealed trait List[+A]

case object Nil extends List[Nothing]

case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {

  def tail[A](l: List[A]) : List[A]= l match {
    case Nil => throw new RuntimeException("Cannot tail an empty list")
    case Cons(head, tail) => tail
  }

  def setHead[A](a: A, l:List[A]) = {
    Cons(a, List.tail(l))
  }

  def drop[A](l: List[A], n: Int): List[A] = n match {
    case 0 => l
    case n => drop(List.tail(l), n - 1)
  }

  def sum(l: List[Int]): Int = l match {
    case Nil => 0
    case Cons(head, tail) => head + sum(tail)
  }

  def apply[A](l: A*): List[A] = {
    if (l.isEmpty) Nil
    else Cons(l.head, apply(l.tail: _*))
  }
}

class ListSpec extends path.FunSpec with Matchers {

  describe("List") {
    import List.sum

    it("adds an empty list of Ints returning 0") {
      sum(Nil) shouldBe 0
    }

    it("adds a list of one Int element") {
      sum(Cons(12, Nil)) shouldBe 12
    }

    it("adds more than one element") {
      sum(Cons(12, Cons(10, Nil))) shouldBe 22
    }

    it("using apply I can simplify syntax") {
      sum(List()) shouldBe 0
      sum(List(12)) shouldBe 12
      sum(List(12, 21)) shouldBe 33
    }

    it("can be tailed when hit has more than 0 elements") {
      List.tail(List(1)) shouldBe Nil
      List.tail(List(2, 3)) shouldBe List(3)
      List.tail(List(4, 5, 6)) shouldBe List(5, 6)
    }

    it("throws exception when tailing an empty list") {
      a [RuntimeException] should be thrownBy List.tail(Nil)
      the [RuntimeException] thrownBy List.tail(Nil) should have message "Cannot tail an empty list"
    }

    it("setHead changes head") {
      List.setHead(12, List(10, 14)) shouldBe List(12, 14)
    }

    it("has a drop function") {
      List.drop(List(1, 2), 2) shouldBe Nil
      List.drop(List(1, 2), 1) shouldBe List(2)
      List.drop(List(1, 2), 0) shouldBe List(1, 2)
    }
  }
}
