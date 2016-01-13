
def run (l: List[Any]) : Unit = println(l.mkString(", "))
run(List("1", "2", "boo"))
run(List(1, 2, 3))
//val as : Array[String] = Array("1", "2")

// arguments to a function are covariant and results are variant

class Pa
class Ca extends Pa

class Pr
class Cr extends Pr

def f(val: Ca) = new Cr

val c: Cr = f(new Pa)




def f[T] = (a: Array[T]) => a

val aa : Array[Any]  = Array("Foo")
val as2 : Array[String] = Array("hhhhh", "gg")
f(aa)
f(as2)


//
//
//def findFirst[T](at: Array[T], f: T => Boolean): Option[T] = {
//  def findHelper(pos: Int): Option[T] = {
//    if (pos >= at.length) Option.empty
//    else if (f(at(pos))) Option(at(pos))
//    else findHelper(pos + 1)
//  }
//  findHelper(0)
//}
//
//val at = Array[String]("hello", "there", "world", "okay?")
//findFirst(at, (arg: String) => arg.equals("world"))
//val at2 = Array[Int](1, 2, 3, 4, 5)
//findFirst(at2, (arg: Int) => arg == 5)
//findFirst(at2, (arg: Int) => arg == 6)
//def partial1[A, B, C](a: A, f: (A, B) => C): (B => C) = {
//  b => f(a, b)
//}
//
//def curry[A, B, C](f: (A, B) => C) = {
//  (a: A, b: B) => f(a, b)
//}
//
//def uncurry[A, B, C](f: A => B => C): (A, B) => C = {
//  (a: A, b: B) => {
//    f(a)(b)
//  }
//}
//
//def foo1[T](x : Array[T]) = x.length
//foo1(Array[String]("one", "thirteen"))
//def foo2(x : Array[T] forSome {type T}) = x.length
//foo2(Array[String]("one", "thirteen"))
//def concat[T](l1: List[T], l2: List[T]) : List[T] = (l1, l2) match {
//  case (head :: rest, r) => head :: concat(rest, r)
//  case (Nil, l2) => l2
//}
//
//concat(List(1, 2), List(3, 4))
//concat(List(), List(3, 4))
//concat(List(1, 2), List())
//concat(List(), List())
//concat(List(1, 2), List("hi", "joe")).tail.tail.head.getClass.getSimpleName
