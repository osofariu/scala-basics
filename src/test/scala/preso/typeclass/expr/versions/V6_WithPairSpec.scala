package preso.typeclass.expr.versions

import org.scalatest.{Matchers, path}
import preso.typeclass.expr._

class V6_WithPairSpec extends path.FunSpec with Matchers {
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

      // Expression is a member of the Json type class
      // Tuples are members of the Json type class
      def write[T: Json](value: T): String = {
        write(implicitly[Json[T]].json(value))
      }
    }

    sealed trait Expression[T] {
      def value(expr: T): Int
    }

    object ExprEvaluator {
      def evaluate[T: Expression](expr: T): Int = {
        implicitly[Expression[T]].value(expr)
      }
    }

    object Expression {
      implicit val intExpression = new Expression[Int] {
        def value(n: Int): Int = n
      }

      implicit def pairPlusExpression[T1: Expression, T2: Expression] = new Expression[(T1, T2)] {
        def value(pair: (T1, T2)): Int =
          implicitly[Expression[T1]].value(pair._1) +
            implicitly[Expression[T2]].value(pair._2)
      }
    }

    it("Expression will evaluate an int as itself") {
      ExprEvaluator.evaluate(3) shouldBe 3
    }

    it("Expression will add all values in a tuple") {
      val expr = ((12, 2), 1)
      ExprEvaluator.evaluate(expr) shouldBe 15
    }

    sealed trait Json[T] {
      def json(v: T): JsonValue
    }

    object Json {
      implicit val intJson = new Json[Int] {
        def json(n: Int): JsonValue = JsonNumber(n)
      }
      implicit def pairJson[T1: Json, T2: Json] = new Json[(T1, T2)] {
        def json(pair: (T1, T2)): JsonValue = JsonObject(
          ("fst", implicitly[Json[T1]].json(pair._1)),
          ("snd", implicitly[Json[T2]].json(pair._2)))
      }
    }

    it("JsonWriter can write Ints") {
      JsonWriter.write(23) shouldBe "23"
    }

    it("JsonWriter can write tuples") {
      JsonWriter.write((1, (2, 22))) shouldBe """{"fst": 1, "snd": {"fst": 2, "snd": 22}}"""
    }

    it("JsonWriter can write longer tuples") {
      JsonWriter.write(((1, 3), (2, 22))) shouldBe """{"fst": {"fst": 1, "snd": 3}, "snd": {"fst": 2, "snd": 22}}"""
    }
  }
}