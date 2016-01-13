package misc.collections

import org.scalatest.{path, Matchers}

class FunctionalSpec extends path.FunSpec with Matchers {

  describe("Ugly cases") {

    it("Ugly casts example shows how explicit you need to be to help Scala figure out types")  {
      val map: Map[Option[Any], List[Any]] = Map(
        Some("foo") -> List("foo", "bar", "baz"),
        Some(42) -> List(1, 2, 3, 4),
        Some(true) -> List(true, false, true))

      // type of Option is consistent with type of value, but Scala doesn't kwow that constraint!

      val xs: List[String] = map(Some("foo")).asInstanceOf[List[String]]
      assert (xs === List("foo", "bar", "baz"))

      val ys: List[Int] = map(Some(42)).asInstanceOf[List[Int]]
      assert (ys === List(1, 2, 3, 4))

      // you can do this, but x is of type List[Any]
      val x = map(Some("foo"))
    }

    // HOMap : (( * => *) x (* => *)) => *
    class HOMap[K[_], V[_]](delegate: Map[K[Any], V[Any]]) {
      def apply[A](key: K[A]): V[A] =
        delegate(key.asInstanceOf[K[Any]]).asInstanceOf[V[A]]
    }

    object HOMap {
      type Pair[K[_], V[_]] = (K[A], V[A]) forSome {type A}
      //def apply[K[_], V[_]](tuples: Pair[K, V]*) = new HOMap[K, V](Map(tuples: _*))
    }


    //it("Improves dramatically if you can help Scala determine types") {
    //  val map: HOMap[Option, List] = HOMap[Option, List]
       // Some("foo") -> List("foo", "bar", "baz"),
       // Some(42) -> List(1, 2, 3, 4),
       // Some(true) -> List(true, false, true))

      // type of Option is consistent with type of value, but Scala doesn't kwow that constraint!

      //val xs: List[String] = map(Some("foo"))
      //assert (xs === List("foo", "bar", "baz"))

      //val ys: List[Int] = map(Some(42))
      //assert (ys === List(1, 2, 3, 4))
    //}
  }
}
