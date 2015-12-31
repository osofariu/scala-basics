

val one: PartialFunction[Int, String] = { case 1 => "one" }
one.isDefinedAt(1)
one.isDefinedAt(2)


def two(first: String)(second: String) : String = first + " " + second

val t1 = two("hi")(_)
val name : String = "John"
t1(name)


val sample = 1 to 10
val isEven: PartialFunction[Int, String] = {
  case x if x % 2 == 0 => x+" is even"
}

// the method collect can use isDefinedAt to select which members to collect
val evenNumbers = sample collect isEven

val isOddSmall: PartialFunction[Int, String] = {
  case x if x <= 5 && x % 2 == 1 => x + " is odd"
}

val isOddLarge: PartialFunction[Int, String] = {
  case x if x > 5 && x % 2 == 1 => x + " is ODD"
}

// the method orElse allows chaining another partial function to handle
// input outside the declared domain
val numbers = sample map (isEven orElse isOddSmall orElse isOddLarge)


import scala.collection.immutable.Stream.Empty

val ln = List(10, 20, 40, 80)
val ls = List("one", "twelve", "nine", "two")
Seq(2, 3, 9) collect ln
Seq(2, 3, 9) collect ls
def plf = new PartialFunction[String, Int] {
  override def isDefinedAt(x: String): Boolean = true
  override def apply(v1: String): Int = v1.length
}

val plf2 : PartialFunction[String, Int] = {
  case s: String => s.length
}

ls.collect(plf)
ls.collect(plf2)
List(1, "one")
val v1 = ln.lift(1)
v1.get
val ls2 = List((1,2), (3,4), 7)
// with map, the domain of the function must be valid for every element
// else you get a MatchError
val vv = ls2 map  { case (k, v) => (v, k) ; case (h) => (h , h)}
// collect doesn't force you to have a match, because it expects a partial function
ls2 collect { case (k, v) => (v, k) }
//ls2  foreach { case (k, v) => println(k + " → " + v) }

val mycase : PartialFunction[Any, Any]= {case (h, k) => (k, h)}
ls2 map {t => if (mycase.isDefinedAt(t)) mycase.apply(t) else ("?")}
ls2 collect mycase
ls2 map {mycase orElse {case (h) => (h, h)}}


val indexes = List(1, 2, 42)
indexes map {ls2 orElse {case (_) => ls2.last}}
indexes map {i => ls2.lift(i)}
indexes flatMap {i => ls2.lift(i)}
indexes map {i => ls2.lift(i).getOrElse ()}
indexes collect ls2

