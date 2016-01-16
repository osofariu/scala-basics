package misc.collections.funstruc

import misc.collections.funstruc.List._
import org.scalatest.{Matchers, path}


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
      tail(List(1)) shouldBe Nil
      tail(List(2, 3)) shouldBe List(3)
      tail(List(4, 5, 6)) shouldBe List(5, 6)
    }

    it("throws exception when tailing an empty list") {
      a [RuntimeException] should be thrownBy tail(Nil)
      the [RuntimeException] thrownBy tail(Nil) should have message "Cannot tail an empty list"
    }

    it("safely tails empty list with tailOpt") {
      tailOpt(Nil) shouldBe Option.empty
    }

    it("setHead changes head") {
      setHead(12, List(10, 14)) shouldBe List(12, 14)
    }

    it("has a drop function") {
      drop(List(1, 2), 2) shouldBe Nil
      drop(List(1, 2), 1) shouldBe List(2)
      drop(List(1, 2), 0) shouldBe List(1, 2)
    }

    it("has a head function to take the first item in the list") {
      head(List("one", "two", "three")) shouldBe "one"
    }

    it("can even call head directly on a List") {
      List(32, 1, 2, 3).head shouldBe 32
    }

    it("has a size") {
      len(List()) shouldBe 0
      len(List(1)) shouldBe 1
      len(List(1, 2, 3, 4, 5)) shouldBe 5
    }

    it("can check size directly on list") {
      List(1, 2, 3, 4, 5, 5).len shouldBe 6
    }
  }
}
