import org.scalatest.{FlatSpec, Matchers}

class ConditionalSpec extends FlatSpec with Matchers {

  def and(x: Boolean, y: => Boolean) = {
    if (x) y else false
  }

  def or(x: Boolean, y: => Boolean) = {
    if (x) true else y
  }

  def infinite :Boolean = {if (true) infinite else true}

  "hand-rolled and " should "work when short-circuits don't matter" in {
    and(true, true) should be (true)
    and(true, false) should be (false)
    and(false, true) should be (false)
    and(false, false) should be (false)
  }

  "hand-rolled and" should "work with short-circuiting" in {
    and(false, infinite) should be(false)
  }

  "hand-rolled or" should "work when short-circuits don't matter" in {
    or(true, true) should be (true)
    or(true, false) should be (true)
    or(false, true) should be (true)
    or(false, false) should be (false)
  }

  "hand-rolled org" should "work with short-circuiting" in {
    or(true, infinite) should be (true)
  }
}