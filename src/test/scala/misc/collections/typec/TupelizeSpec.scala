package misc.collections.typec

import org.scalatest.{Matchers, path}

import scala.language.higherKinds

class TupelizeSpec extends path.FunSpec with Matchers {

  describe("wrap two values into a container") {

    trait Container[M[_]] {
      def put[A](x: A): M[A]
      def get[A](m: M[A]): A
    }

    def tupelize[M[_]: Container, A, B]
    (fst: M[A], snd: M[B])
    (implicit c: Container[M]) : M[(A, B)] =
      c.put(c.get(fst), c.get(snd))

//    it("can create a tuple with Option[Int]") {
//      tupelize(Some(1), Some(2))
//    }

      it("can tupelize lists of strings") {
        tupelize(List("first"), List("second"))
      }

//    it("can create a List[_]") {
//      tupelize(List(1), List("two"))
//    }
  }
}
