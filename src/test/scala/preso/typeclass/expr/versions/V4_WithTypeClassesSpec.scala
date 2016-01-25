package preso.typeclass.expr.versions

import org.scalatest.{Matchers, path}
import preso.typeclass.expr._

class V4_WithTypeClassesSpec extends path.FunSpec with Matchers {

  describe("with type classes") {

    object JsonWriter {

      def wrap(s: String) = "\"" + s + "\""

      def write(value: JsonValue): String = value match {
        case JsonString(str) => wrap(str)
        case JsonNumber(num) => num.toString
        case JsonBoolean(bool) => wrap(bool.toString)
        case JsonNull => wrap("null")
        case JsonObject(entries) => val serializedEntries =
          for ((key, value) <- entries) yield wrap(key) + ": " + write(value)
          s"{${serializedEntries mkString ", "}}"
        case JsonArray(entries) => val serializedEntries = entries map write
          s"[${serializedEntries mkString ", "}]"
      }

      def write[T](value: T)(implicit converter: JsonConverter[T]) : String = {
        write(converter.asJson(value))
      }
    }

    trait JsonConverter[T] {
      def asJson(v: T): JsonValue
    }

    sealed trait Expression
    case class Number(value: Int) extends Expression
    case class Plus(lhs: Expression, rhs: Expression) extends Expression
    case class Minus(lhs: Expression, rhs: Expression) extends Expression

    object ExprEvaluator {
      def toInt(expr: Expression): Int = expr match {
        case Number(n) => n
        case Plus(lhs, rhs) => toInt(lhs) + toInt(rhs)
        case Minus(lhs, rhs) => toInt(lhs) - toInt(rhs)
      }
    }

    implicit val asJsonConverter = new JsonConverter[Expression] {
      override def asJson(v: Expression): JsonValue = v match {
        case Number(n) => JsonNumber(n)
        case Plus(lhs, rhs) => JsonObject(("operation", JsonString("+")), ("first", asJson(lhs)), ("second", asJson(rhs)))
        case Minus(lhs, rhs) => JsonObject(("operation", JsonString("-")), ("first", asJson(lhs)), ("second", asJson(rhs)))
      }
    }

    // the converter is found implicitly
    object ExprToJsonFormatter {
      def toJson(expr: Expression): String = JsonWriter.write(expr)
    }

    val expr = Minus(Plus(Number(5), Number(3)), Number(1))

    it("can add two and subtract sub-expressions") {
      ExprEvaluator.toInt(expr) shouldBe 7
    }

    it("can convert expression to Json") {
      ExprToJsonFormatter.toJson(expr) shouldBe """{"operation": "-", "first": {"operation": "+", "first": 5, "second": 3}, "second": 1}"""
    }
  }
}