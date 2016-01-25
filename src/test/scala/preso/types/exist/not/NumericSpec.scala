package preso.types.exist.not

import org.scalatest.{Matchers, path}

import scala.language.implicitConversions

// combining different types, and returning different types

class NumericSpec extends path.FunSpec with Matchers {

  trait Numeric {
    def +(other: Numeric): Numeric
  }

  case class IntNumeric(value: Int) extends Numeric {
    override def +(other: Numeric): Numeric = other match {
      case n: IntNumeric => IntNumeric(value + n.value)
      case d: DoubleNumeric => DoubleNumeric(value + d.value)
    }
  }

  case class DoubleNumeric(value: Double) extends Numeric {
    override def +(other: Numeric): Numeric = other match {
      case n: IntNumeric => DoubleNumeric(n.value + value)
      case d: DoubleNumeric => DoubleNumeric(value + d.value)
    }
  }

  implicit def toInt(n: IntNumeric): Int = n.value
  implicit def toDouble(n: DoubleNumeric): Double = n.value

  describe("Numeric allows related types to be combined") {

    it("adds int to int") {
      0 + IntNumeric(12) + IntNumeric(3) shouldBe 15
    }

    it("even converts to Int automatically") {
      0 + IntNumeric(12) shouldBe 12
    }

    it("adds int to double") {
      val v: Double = 0 + IntNumeric(12) + DoubleNumeric(12.5)
      v shouldBe 24.5
    }

    it("adds double to double") {
      val v = 0 + DoubleNumeric(.5) + DoubleNumeric(.5)
      v shouldBe 1.0
    }
  }
}
