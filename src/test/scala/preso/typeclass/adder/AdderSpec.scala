package preso.typeclass.adder

import org.scalatest.Matchers
import preso.funstruct.{List => FunList}
import preso.typeclass.adder.Adder._

class AdderSpec extends org.scalatest.path.FunSpec with Matchers {

  describe("sum") {

    describe("with Scala List") {
      it("adds Int types") {
        sum(List(1, 2, 3)) shouldBe 6
      }

      it("adds Double types") {
        sum(List(1.1, 2.2)) shouldBe 1.1 + 2.2
      }

      it("adds strings!") {
        sum(List("foo", "bar")) shouldBe "foobar"
      }
    }

    describe("with FunList") {
      it("adds Int types") {
        sum(FunList(1, 2, 3, 4)) shouldBe 10
      }

      it("adds Double types") {
        sum(FunList(1.1, 2.2)) shouldBe 1.1 + 2.2
      }

      it("adds strings!") {
        sum(FunList("foo", "bar")) shouldBe "foobar"
      }
    }

    describe("with generic List") {
      it("adds Int types") {
        sum(FunList(1, 2, 3, 4)) shouldBe 10
      }
    }
  }
}
