

//trait Plus[A] {
//  def plus(a1: A, a2: A): A
//}
//
//def plus[A : Plus](a1: A, a2: A): A = implicitly[Plus[A]].plus(a1, a2)
//
//class I[Int] extends Plus[Int] {
//  override def plus(a1: Int, a2: Int): Int = {
//    a1 + a2
//  }
//}


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
  override val song = "chicken no song"
  override val hatch = new Hatch(12, "keeps going")
}
class SongBird extends Bird {
  override val sound = "song"
  override val hatch: Hatch = new Hatch(1, "one")
  override val song: String = "override songbird song"
}

class Lark extends SongBird {
  override val song = "beautiful song"
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

List(new Chicken).::(new Chicken).::(new SongBird).map(b => b.sound)

val v = new SongBird :: new Chicken :: new Lark :: new Animal :: List[Bird]()
val v2 = v map (b => b.sound)
