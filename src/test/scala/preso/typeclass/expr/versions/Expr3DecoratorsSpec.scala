package preso.typeclass.expr.versions

import org.scalatest.{Matchers, path}

class Expr3DecoratorsSpec extends path.FunSpec with Matchers
{

  sealed trait Expression
  case class Number(value: Int) extends Expression
  case class Plus(lhs: Expression, rhs: Expression) extends Expression
  case class Minus(lhs: Expression, rhs: Expression) extends Expression

  object Evaluator {
    def evaluateNumber(expr: Number) = expr.value
    def evaluatePlus(fst: Expression, snd: Expression) = {

    }

  }


  sealed trait JsonValue
  case class JsonObject(entries: Map[String, JsonValue]) extends JsonValue
  case class JsonArray(entries: Seq[JsonValue]) extends JsonValue
  case class JsonString(value: String) extends JsonValue
  case class JsonNumber(value: BigDecimal) extends JsonValue
  case class JsonBoolean(value: Boolean) extends JsonValue
  case object JsonNull extends JsonValue



}

