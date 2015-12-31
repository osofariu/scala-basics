
import scalaz.Monoid

object First {
  def sumGeneric[A](l: List[A])(implicit A: Monoid[A]): A =
    l.foldLeft(A.zero)((x, y) => A.append(x, y))
}


val stringMonoid = new Monoid[String] {
  def zero : String = ""
  def append(f1: String, f2: => String): String = f1  + f2
}

val intMonoid = new Monoid[Int] {
  def zero : Int = 0
  def append(f1: Int, f2 : => Int): Int = f1 + f2
}

First.sumGeneric(List("A", "B"))(stringMonoid)
First.sumGeneric(List(1, 2, 3, 4 ,5))(intMonoid)


val divide2By = new PartialFunction[Int, Double] {
  override def isDefinedAt(x: Int): Boolean = x != 0
  override def apply(v1: Int): Double = 2.0 / v1
}

val divide2 : PartialFunction[Int, Double] = {
  case i:Int if i != 0 => 2.0 / i
}

divide2By.isDefinedAt(0)
divide2By.apply(0).isInfinite


divide2.isDefinedAt(0)
List(0, 1, 2, 3).collect(divide2By) == List(0, 1, 2, 3).collect(divide2)
val incAny: PartialFunction[Any, Int] =
{ case i: Int ⇒ i + 1 }
List(1, "a", 2.0, "fooo").collect(incAny)
val second: List[Int] => Int = {
  case x :: y :: _ => y
}
second(List(10, 20, 30 ))

