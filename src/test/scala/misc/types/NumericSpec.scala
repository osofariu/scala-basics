package misc.types

import org.scalatest.{path, Matchers}

class NumericSpec extends path.FunSpec with Matchers {

  trait Numeric[T] {
    def +[_](other: Numeric[_]): Numeric[_]
  }

  case class IntNumeric[_](value: Int) extends Numeric[Int] {
    override def +[_](other: Numeric[_]): Numeric[_] = other match {
      case n: IntNumeric[_] => IntNumeric(value + n.value)
      case d: DoubleNumeric[_] => DoubleNumeric(value + d.value)
    }
  }

  case class DoubleNumeric[_](value: Double) extends Numeric[Double] {
    override def +[_](other: Numeric[_]): Numeric[_] = other match {
      case n: IntNumeric[_] => DoubleNumeric(n.value + value)
      case d: DoubleNumeric[_] => DoubleNumeric(value + d.value)
    }
  }

  implicit def toInt(n: IntNumeric[_]): Int =  n.value
  implicit def toDouble(n: DoubleNumeric[_]) : Double = n.value

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
      val v: Double = 0 + DoubleNumeric(.5) + DoubleNumeric(.5)
      v shouldBe 1.0
    }
  }
}
