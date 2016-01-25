package preso.types.higher

import org.scalatest.{Matchers, path}

import scala.language.{higherKinds, reflectiveCalls}

class HigherTypesSpec extends path.FunSpec with Matchers {

  describe("examples of using type aliases") {

    it("Identity function type returns type it was given") {
      type Id[A] = A
      val v: Id[String] = "foo"
      v shouldBe "foo"
    }

    it("given id function and value, apply function to value - more type aliases") {
      type F[_] = (Int) => Int
      type I = Int

      val inc: F[I] = (i: Int) => i + 1
      inc(12) shouldBe 13

      type Id[F[_], B] = F[B]
      def incFun: Id[F, Int] = (i: Int) => i + 1
      incFun(12) shouldBe 13
    }
  }

  describe("Functor as higher type with map on a List with a specialized map function") {

    trait Functor[List[_]] {
      def map[Int, String](fn: Int => String)
                          (fa: List[Int]): List[String]
    }

    val functor = new Functor[List] {
      def map[String, Int]
      (f: String => Int)
      (fa: List[String])
      : List[Int] = fa map f // implement myself instead
    }

    it("can run map over list of Strings") {
      functor.map((str: String) => str.length)(List[String]("Hi", "there")) shouldBe List(2, 5)
    }
  }

  describe("Functor trait can be generalized further by parameterizing the map function") {

    trait Functor[F[_]] {
      def map[A, B](fn: A => B)(fa: F[A]): F[B]
    }

    it("use generic Functor over a List of Strings") {

      val functor = new Functor[List] {
        def map[String, Int](f: String => Int)(fa: List[String]): List[Int] = {
          for (s <- fa) yield f(s)
        }
      }
      functor.map((str: String) => str.length)(List[String]("Hi", "there")) shouldBe List(2, 5)
    }
  }
}
