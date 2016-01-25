package preso.ext

import org.scalatest.{Matchers, path}

import scala.language.existentials

class ExtSpec extends path.FunSpec with Matchers {

  describe("calculating length for lists containing a type that extends HasLength") {

    trait HasLength {
      def length : Int
    }

    def length_short[T <: HasLength](l: List[T]) : Int =
      l.foldLeft(0)((acc, v) => acc + v.length)

    def length_explicit(l: List[T] forSome {type T <: HasLength}) : Int =
      l.foldLeft(0)((acc, v) => acc + v.length)

    it("adds lengths for items that have length property") {
      case class StringWrap(s: String) extends HasLength {
        override def length: Int = s.length
      }

      val lst = List[StringWrap](StringWrap("hello"), StringWrap("there"))
      length_short(lst) shouldBe 10
      length_explicit(lst) shouldBe 10
    }
  }

  describe("function arguments are contravariant and return types are covariant") {

    // if I am expected to return a Chicken             I can assign that to a Bird because a Bird is a Chicken
    // if I am expected to take   a Bird as an argument I can instead give it a Chicken because a chicken is a Bird (go down the hierarchy)

    class ParentArg {}
    class ChildArg extends ParentArg {}
    class ParentReturn {}
    class ChildReturn extends ParentReturn {}

    def f1(v: ParentArg) : ChildReturn = new ChildReturn

    val cr: ChildReturn = f1(new ParentArg)
    val pr: ParentReturn = f1(new ParentArg)
    val cr2: ChildReturn = f1(new ChildArg)
    val pr2: ParentReturn = f1(new ChildArg)

    def f2(v: ChildArg) : ParentReturn = new ParentReturn
    val pr3: ParentReturn = f2(new ChildArg)
    //val cr3: Cr = f2(new Ca)
    //val pr3 = f2(new Pa)


    // ChildArg is a ParentArg, so this would also work:
    val l3 : List[ChildArg] = List(new ChildArg)
    val l4 : List[ParentArg] = l3

  }
}
