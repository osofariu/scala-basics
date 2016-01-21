package preso.typeclass.exp.inherit

import org.scalatest.{Matchers, path}
import Expression.{Minus, Number, Plus}

class ExpressionSpec extends path.FunSpec with Matchers {

  describe("Expression") {


    it("can add two sub-expressions") {
      val v = Minus(Plus(Number(5), Number(3)), Number(1))
      Expression.evaluate(v) shouldBe 7
    }
  }
}
