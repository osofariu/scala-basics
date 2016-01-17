package misc.collections.typec

import misc.collections.funstruc.{List => FunList}

trait Adder[T] {
  def zero: T
  def add(v1: T, v2: T): T
}

object Adder {

  implicit object IntAdder extends Adder[Int] {
    override def zero: Int = 0
    override def add(v1: Int, v2: Int): Int = v1 + v2
  }

  implicit object DoubleAdder extends Adder[Double] {
    override def zero: Double = 0D
    override def add(v1: Double, v2: Double): Double = v1 + v2
  }

  implicit object StringAdder extends Adder[String] {
    override def zero: String = ""
    override def add(v1: String, v2: String): String = v1 + v2
  }

  def sum[T](l: List[T])(implicit s: Adder[T]): T = {
    l.foldLeft[T](s.zero)(s.add)
  }

  def sum[T](l : FunList[T])(implicit s: Adder[T]): T = {
    l.foldLeft[T](s.zero)(s.add)
  }
}
