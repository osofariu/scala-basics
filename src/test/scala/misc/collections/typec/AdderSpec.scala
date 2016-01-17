package misc.collections.typec

import org.scalatest.Matchers
import misc.collections.typec.Adder._
import misc.collections.funstruc.{List => FunList}

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
  }
}
