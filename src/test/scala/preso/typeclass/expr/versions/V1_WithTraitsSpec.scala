package preso.typeclass.expr.versions

import org.scalatest.{Matchers, path}
import preso.typeclass.expr._

class V1_WithTraitsSpec extends path.FunSpec with Matchers {

  describe("Expression with added functionality") {


    sealed trait NumericConvertible {
      def asInt: Int
    }

    sealed trait JsonConvertible {
      def asJson: JsonValue
    }

    sealed trait Expression extends NumericConvertible with JsonConvertible

    case class Number(value: Int) extends Expression {
      override def asJson = JsonNumber(value)
      override def asInt: Int = value
    }
    case class Plus(lhs: Expression, rhs: Expression) extends Expression {
      override def asInt: Int = lhs.asInt + rhs.asInt
      override def asJson = JsonObject(("operation", JsonString("+")), ("left", lhs.asJson), ("right", rhs.asJson))
    }
    case class Minus(lhs: Expression, rhs: Expression) extends Expression {
      override def asInt: Int = lhs.asInt - rhs.asInt
      override def asJson = JsonObject(("operation", JsonString("-")), ("left", lhs.asJson), ("right", rhs.asJson))
    }

    def evaluate(exp: Expression): Int = {
      exp.asInt
    }

    def jsonExtract(exp: Expression): String = {
      JsonWriter.write(exp.asJson)
    }

    val exp1 = Minus(Plus(Number(5), Number(3)), Number(1))

    it("can add two and subtract sub-expressions") {
      evaluate(exp1) shouldBe 7
    }

    it("can convert expression to Json") {
      jsonExtract(exp1) shouldBe """{"operation": "-", "left": {"operation": "+", "left": 5, "right": 3}, "right": 1}"""
    }
  }
}

