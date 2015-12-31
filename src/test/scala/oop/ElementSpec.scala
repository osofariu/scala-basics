package oop

import org.scalatest.{Matchers, FlatSpec}
import oop.Element.element

class ElementSpec extends FlatSpec with Matchers {

  "element created from a string" should "have height of one and width of the number of characters in string" in {
    val e = element("turnip")
    e.width should equal(6)
    e.height should equal(1)
  }

  "element put beside another element" should "create another element that has the text of both elements and height 1" in {
    val e = element("puppy") beside element("justice")
    println(e)
    e.width should equal(12)
    e.height should equal(1)
  }

  "element put above another element" should "create another element of height 2 containing the text of both elements with width being that of longest element" in {
    val e = element("puppy") above element("justice")
    e.width should equal(7)
    e.height should equal(2)
  }

  "element put above aother element" should "center the shorter element with spaces on either side" in  {
    val e = element("in") above element("perpetuity")
    println(e)
    e.items(0) should equal("    in    ")
  }

  "element of height 2 beside another element of height 2" should "create element with height to 2 with text intermingled" in {
    val e = element("puppy") above element("forever")
    val e2 = element("justice") above element("and ever")
    val ee2 = e beside e2
    ee2.height should equal(2)
    ee2.items(0) should equal(" puppy justice")
    ee2.items(1) should equal("foreverand ever")
  }

  "element of height 2 beside another element of height 1" should "create element with height to 2 with text intermingled" in {
    val e = element("puppy") above element("justice")
    val e2 = element("forever")
    val ee2 = e beside e2
    println(ee2)
    ee2.height should equal(2)
    ee2.items(0) should equal(" puppy forever")
    ee2.items(1) should equal("justice      ")
  }

  "complex elements" should "x" in {
    val elt = element("Hello", "there")
    val ert = element("mister")
    val elb = element("X")
    val erb = element("This", "should", "work")
    val result = (elt beside ert) above (elb beside erb)
    val compound = result beside result beside result
    println(result)
    println(compound)
  }
}
