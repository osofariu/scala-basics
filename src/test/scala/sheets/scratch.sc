case class Greets[T]( private val name: T ) {
  def hello() : Unit = println("Hello " + name)
  def getName: T = name
}

val greets1: Greets[String] = Greets("John")
val greets2: Greets[Symbol] = Greets('Jack)

val greetsList1 = List( greets1, greets2 ) // Does not compile
greetsList1.head.getClass.getSimpleName
greetsList1.head.getName.getClass.getSimpleName
greetsList1.tail.head.getName.getClass.getSimpleName

//val greetsList1b : List[Greets[Any]] = greetsList1
val greetsList1b : List[Greets[_]] = greetsList1


/*
Compilation error:
Error:(10, 50) type mismatch;
 found   : A$A1.this.Greets[String]
 required: A$A1.this.Greets[Any]
Note: String <: Any, but class Greets is invariant in type T.
You may wish to define T as +T instead. (SLS 4.5)
*/
val greetsList2: List[Greets[_]] = List( greets1, greets2 )
case class BarList[T](s: T) {
  def hello = "HelloL " + s
  def getS: T = s
}
BarList(List("a", "b'")).hello
BarList(List("a", "b'")).getS
//
case class BarSet[T <: Set[_]](s: T) {
  def hello = "HelloS " + s
  def getS: T = s
}
BarSet(Set("a", "b'")).hello
BarSet(Set("a", "b'")).getS

