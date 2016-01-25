package preso.types.exist

import org.scalatest.{Matchers, path}

import scala.language.{existentials, higherKinds}

class HOMapSpec extends path.FunSpec with Matchers {

  describe("a map from Option of T to List of T, where they always refer to the same type") {

    val map: Map[Option[Any], List[Any]] = Map(
      Some("foo") -> List("foo", "bar", "baz"),
      Some(42) -> List(1, 2, 3, 4),
      Some(true) -> List(true, false, true))

    it("allows you to get values out of the map, but you have to cast them to expected type") {

      val xs: List[String] = map(Some("foo")).asInstanceOf[List[String]]
      assert(xs === List("foo", "bar", "baz"))

      val ys: List[Int] = map(Some(42)).asInstanceOf[List[Int]]
      assert(ys === List(1, 2, 3, 4))

      // you can do this, but x is of type List[Any]
      val x = map(Some("foo"))
    }
  }

  describe("With some help from higher types you can eliminate the need to cast") {

    // HOMap : (( * => *) x (* => *)) => *
    class HOMap[K[_], V[_]](delegate: Map[K[_], V[_]]) {
      def apply[A](key: K[A]): V[A] = {
        delegate(key.asInstanceOf[K[A]]).asInstanceOf[V[A]]
      }
    }

    object HOMap {
      type Pair[K[_], V[_]] = (K[A], V[A]) forSome {type A}
      def apply[K[_], V[_]](tuples: Pair[K, V]*) = new HOMap[K, V](Map(tuples: _*))
    }

    val map: HOMap[Option, List] = HOMap[Option, List](
      Some("foo") -> List("foo", "bar", "baz"),
      Some(42) -> List(1, 2, 3, 4),
      Some(true) -> List(true, false, true))


    it("Improves dramatically if you can help Scala determine types") {
       // type of Option is consistent with type of value, but Scala doesn't kwow that constraint!

      val xs = map(Some("foo"))
      assert (xs === List("foo", "bar", "baz"))

      val ys: List[Int] = map(Some(42))
      assert (ys === List(1, 2, 3, 4))
    }
  }
}
