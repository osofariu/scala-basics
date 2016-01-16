package misc.collections.typec

import org.scalatest.Matchers

class AdderSpec extends org.scalatest.path.FunSpec with Matchers {

  trait Adder[T] {
    def zero: T
    def add(v1: T, v2: T): T
  }

  implicit object IntAdder extends Adder[Int] {
    override def zero: Int = 0
    override def add(v1: Int, v2: Int): Int = v1 + v2
  }

  implicit object DoubleAdder extends Adder[Double] {
    override def zero: Double = 0D
    override def add(v1: Double, v2: Double): Double = v1 + v2
  }

  implicit object StringAdder extends Adder[String] {
    override def zero: String = ""
    override def add(v1: String, v2: String): String = v1 + v2
  }

  def sum[T](l: List[T])(implicit s: Adder[T]): T = {
    l.foldLeft(s.zero)(s.add)
  }

  describe("sum") {

    it("adds Int types") {
      sum(List(1, 2, 3)) shouldBe 6
    }

    it("adds Double types") {
      sum(List(1.1, 2.2)) shouldBe 1.1 + 2.2
    }

    it("adds strings!") {
      sum(List("foo", "bar")) shouldBe "foobar"
    }
  }
}
