case class Greets[T]( private val name: T ) {
  def hello() {
    println("Hello " + name) }
  def getName: T = name
}

val greets1: Greets[String] = Greets("John")
val greets2: Greets[Symbol] = Greets('Jack)

//val greetsList1: List[Greets[Any]] = List( greets1, greets2 ) // Does not compile
val greetsList2: List[Greets[_]] = List( greets1, greets2 ) // Does not compile


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

