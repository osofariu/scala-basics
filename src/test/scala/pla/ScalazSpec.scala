package pla

import org.scalatest._

import scalaz._
import scalaz.std.option._

object Foo {
  def add = Apply[Option].apply2(some(1), some(2))((a, b) => a + b)
  //def zipper = Stream(1, 2, 3, 4).toZipper >>= {_.next} >>= {_.focus.some}
}

class ScalazSpec  extends FlatSpec with ShouldMatchers {
  "Foo.add" should "return add 1 and 2 " in {
    Foo.add should be(Some(3))
    //Foo.zipper should equal(2.some)
  }
}
