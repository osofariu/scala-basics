package pla

import org.scalatest.{Matchers, FlatSpec}

object ConcatUtil {

  trait Concat[T] {
    def add(t1: T, t2: T) : T
  }

  implicit object ConcatInt extends Concat[Int] {

    def add(t1: Int, t2: Int) : Int = {
      (t1.toString + t2.toString).toInt
    }
  }
}

object Util {

  import ConcatUtil.Concat

  def ++[T](v1: T, v2: T)(implicit ci: Concat[T]) : T = {
    ci.add(v1, v2)
  }
  def +-[T: Concat](v1: T, v2: T) = {
    implicitly[Concat[T]].add(v1, v2)
  }
}

class TypeClassSpec extends FlatSpec with Matchers {

  "Concat adding integers 1 and 23" should "return integer 123" in {
    Util.++(1, 23) should equal(123)
    Util.+-(23, 1) should equal(231)
  }

}

object Scratch {
  class Animal {
    val sound = "rustle"
  }

  case class Hatch(duration: Int, description: String)

  abstract class Bird extends Animal {
    override val sound = "chirp"
    val song  : String
    val hatch : Hatch
  }
  class Chicken extends Bird {
    override val sound = "Cluck"
    override val song = "none"
    override val hatch = new Hatch(12, "keeps going")
  }
  class SongBird extends Bird {
    override val sound = "song"
    override val hatch: Hatch = new Hatch(1, "one")
    override val song: String = "override me"
  }

  class Lark extends SongBird {
    override val song = "beautiful"
  }

  class NorthernLark extends Lark {
    override val song = "most beautiful"
  }

  type BB[-S,B] =  S => B
  val b : BB[Lark,Bird] = (s: Lark) => {
    s.asInstanceOf[SongBird]
  }

  b(new NorthernLark).song
  def biophony[T <: Animal](things: Seq[T]) = things map (_.sound)

  val birds = List[Bird]().::(new Lark)

  birds.map(b => b.sound)
}
