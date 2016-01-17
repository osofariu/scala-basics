package misc.collections.typec

import org.scalatest.Matchers
import misc.collections.typec.Adder._
import misc.collections.funstruc.{List => FunList}

class AdderSpec extends org.scalatest.path.FunSpec with Matchers {

  describe("sum") {

    it("adds Int types") {
      sum[Int, FunList[Int]](FunList(1, 2, 3, 4)) shouldBe 10
    }

    it("adds Double types") {
      sum[Double, FunList[Double]](FunList(1.1, 2.2)) shouldBe 1.1 + 2.2
    }

    it("adds strings!") {
      sum[String, FunList[String]](FunList("foo", "bar")) shouldBe "foobar"
    }
  }
}
