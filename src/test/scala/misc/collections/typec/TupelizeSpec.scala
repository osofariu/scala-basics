package misc.collections.typec

import org.scalatest.{Matchers, path}

import scala.language.higherKinds

class TupelizeSpec extends path.FunSpec with Matchers {

  describe("wrap two values into a container") {

    trait Container[M[_]] {
      def put[A](x: A): M[A]

      def get[A](m: M[A]): A
    }

    def make_tuple[M[_] : Container, A, B]
    (fst: M[A], snd: M[B])
    (implicit c: Container[M]): M[(A, B)] =
      c.put(c.get(fst), c.get(snd))



    describe("List-related tupelizations") {
      implicit val listContainer = new Container[List] {
        override def put[A](x: A): List[A] = List(x)
        override def get[A](m: List[A]): A = m.head
      }

      it("can make_tuple lists of strings") {
        make_tuple(List("first"), List("second")) shouldBe List(("first", "second"))
      }

      it("can create a List[_]") {
        val t = make_tuple(List(1), List("two"))
        t shouldBe List((1, "two"))
        val i: Int  = t.head._1
        val s: String = t.head._2
      }
    }

    describe("Option-related tupelization") {

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
