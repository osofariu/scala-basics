package preso.typeclass.expr.versions

import org.scalatest.{Matchers, path}
import preso.typeclass.expr._

class Expr2ExtEvaluatorSpec extends path.FunSpec with Matchers {

  describe("Expression with external Evaluators") {
    sealed trait Expression
    case class Number(value: Int) extends Expression
    case class Plus(lhs: Expression, rhs: Expression) extends Expression
    case class Minus(lhs: Expression, rhs: Expression) extends Expression

    object ExprEvaluator {
      def value(expr: Expression) : Int = expr match {
        case Number(n) => n
        case Plus(fst, snd) => value(fst) + value(snd)
        case Minus(fst, snd) => value(fst) - value(snd)
      }
    }

    object ExprToJson {
      def toJson(expr: Expression): JsonValue = expr match {
        case Number(n) => JsonNumber(n)
        case Plus(fst, snd)  => JsonObject(("operation", JsonString("+")), ("first", toJson(fst)), ("second", toJson(snd)))
        case Minus(fst, snd) =>JsonObject(("operation", JsonString("-")), ("first", toJson(fst)), ("second", toJson(snd)))
      }
    }

    val exp1 = Minus(Plus(Number(5), Number(3)), Number(1))

    it("can add two and subtract sub-expressions") {
      ExprEvaluator.value(exp1) shouldBe 7
    }

    it("can convert expression to Json") {
      JsonWriter.write(ExprToJson.toJson(exp1)) shouldBe """{"operation": "-", "first": {"operation": "+", "first": 5, "second": 3}, "second": 1}"""
    }
  }
}