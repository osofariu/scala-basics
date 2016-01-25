package preso.typeclass.tuple

import org.scalatest.{Matchers, path}

import scala.language.higherKinds

class TupelizeSpec extends path.FunSpec with Matchers {

  describe("wrap two values into a container") {

    // wrapper of a pair of two types
    trait Container[C[_]] {
      def put[A](x: A): C[A]
      def get[A](m: C[A]): A
    }

    // preserves type of each argument in pair
    def make_tuple[C[_] : Container, A, B]
    (fst: C[A], snd: C[B]): C[(A, B)] = {
      val c = implicitly[Container[C]]
      c.put(c.get(fst), c.get(snd))
    }

    describe("List-related tuple-making") {

      implicit val listContainer = new Container[List] {
        override def put[A](x: A): List[A] = List(x)
        override def get[A](m: List[A]): A = m.head
      }

      it("can make_tuple lists of strings") {
        make_tuple(List("first"), List("second")) shouldBe List(("first", "second"))
      }

      it("can create a tuple of List of whatever type") {
        val t = make_tuple(List(1), List("two"))
        t shouldBe List((1, "two"))
        t.head._1.isInstanceOf[Int]
        t.head._2.isInstanceOf[String]
      }
    }

    describe("Option-related tuple-making") {

      implicit val optContainer = new Container[Some] {
        override def put[A](x: A): Some[A] = Some(x)
        override def get[A](m: Some[A]): A = m.get
      }
      it("can create a tuple with Option[Int]") {
        make_tuple(Some(1), Some(2)) shouldBe Some((1, 2))
      }
    }
  }
}
