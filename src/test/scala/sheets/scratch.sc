
def asInt[T](t: T): Int = t match {
  case t: Int => t
  case _ => 0
}

object O {
  def f[_](l: List[_]) = l.size
}

// the trouble with Any is that it erases type

val li = List(1, 2)
val l: List[Any] = li
val i: Int = l.head.asInstanceOf[Int]


// calculating
trait Robot extends Lasers
trait Humanoid
trait Lasers

object X extennds Robot with Humanoid with Lasers

/*
This works find under Scala WorkSheets but not in a ScalaTest

import scala.reflect.runtime.universe._
typeOf[X.type].baseClasses.map(_.name).mkString("\n extends ")

res2: String = X
 extends Humanoid
 extends Robot
 extends Lasers
 extends Object
 extends Any
 */



