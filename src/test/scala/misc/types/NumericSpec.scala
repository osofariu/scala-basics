package misc.types

import org.scalatest.{path, Matchers}

class NumericSpec extends path.FunSpec with Matchers {

  describe("Numeric allows related types to be combined") {

    trait Numeric[T] {
      def +[_](other: Numeric[_]): Numeric[_]
      def get: T
    }

    object IntNumeric {
      def apply(i: Int) = new IntNumeric(i)
    }

    object DoubleNumeric {
      def apply(d: Double) = new DoubleNumeric(d)
    }

    class IntNumeric[_] (val value: Int) extends Numeric[Int] {
      override def +[_](other: Numeric[_]): Numeric[_] = other match {
        case n: IntNumeric[_] => IntNumeric(value + n.value)
        case d: DoubleNumeric[_] => DoubleNumeric(value + d.value)
      }

      implicit override def get = value
    }

    class DoubleNumeric[_](val value: Double) extends Numeric[Double] {
      override def +[_](other: Numeric[_]): Numeric[_] = other match {
          case n: IntNumeric[_] =>  DoubleNumeric(n.value + value)
          case d: DoubleNumeric[_] => DoubleNumeric(value + d.value)
      }

      implicit override def get = value
    }


    it("adds int to int") {
      val v  = IntNumeric(12) + IntNumeric(3)
      v.get shouldBe 15
      v.get.isInstanceOf[Int] shouldBe true
    }

    it("adds int to double") {
      val v = IntNumeric(12) + DoubleNumeric(12.5)
      v.get shouldBe 24.5
    }

    it("adds double to double") {
      val v = DoubleNumeric(.5) + DoubleNumeric(.5)
      v.get shouldBe 1.0
      v.get.isInstanceOf[Double] shouldBe true
    }
  }
}
