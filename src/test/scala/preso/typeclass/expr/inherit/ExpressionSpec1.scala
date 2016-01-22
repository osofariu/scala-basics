package preso.typeclass.expr.inherit

import org.scalatest.{Matchers, path}

class ExpressionSpec1 extends path.FunSpec with Matchers {

  describe("Expression with added functionality") {

    /*
     * Expression --> Evaluator, JsonExtractor
     */

    sealed trait Evaluator {
      def value: Int
    }

    sealed trait JsonExtractor {
      def asJson: String
    }

    sealed trait Expression extends Evaluator with JsonExtractor
    case class Number(value: Int) extends Expression {
      override def asJson: String = "{Number: " + value.toString + "}"
    }
    case class Plus(lhs: Expression, rhs: Expression) extends Expression {
      override def value: Int = lhs.value + rhs.value
      override def asJson: String = "{Plus: " + lhs.asJson + "," + rhs.asJson + "}"
    }
    case class Minus(lhs: Expression, rhs: Expression) extends Expression {
      override def value: Int = lhs.value - rhs.value
      override def asJson: String = "{Minus: " + lhs.asJson + ", " + rhs.asJson + "}"
    }

    def evaluate(exp: Expression): Int = {
      exp.value
    }

    def jsonExtract(exp: Expression): String = {
      exp.asJson
    }

    val exp1 = Minus(Plus(Number(5), Number(3)), Number(1))

    it("can add two and subtract sub-expressions") {
      evaluate(exp1) shouldBe 7
    }

    it("can convert expression to Json") {
      jsonExtract(exp1) shouldBe "{Minus: {Plus: {Number: 5},{Number: 3}}, {Number: 1}}"
    }
  }

  describe("Expression with external Evaluators") {
    sealed trait Expression
    case class Number(value: Int) extends Expression
    case class Plus(lhs: Expression, rhs: Expression) extends Expression
    case class Minus(lhs: Expression, rhs: Expression) extends Expression

    object Evaluator {
      def value(exp: Expression) : Int = exp match {
        case Number(n) => n
        case Plus(fst, snd) => value(fst) + value(snd)
        case Minus(fst, snd) => value(fst) - value(snd)
      }
    }

    val exp1 = Minus(Plus(Number(5), Number(3)), Number(1))

    it("can add two and subtract sub-expressions") {
      Evaluator.value(exp1) shouldBe 7
    }
  }
}

