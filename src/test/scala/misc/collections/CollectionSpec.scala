package misc.collections

import org.scalatest.{Matchers, FlatSpec}

class CollectionSpec  extends FlatSpec with Matchers {

  "List" should "be initialized as expected" in {

    val l : List[String] = List()
    l.length should equal(0)

    val l2 : List[String] = List("Elastic")
    l2.length should equal(1)
    l2(0) should equal("Elastic")
  }
}
