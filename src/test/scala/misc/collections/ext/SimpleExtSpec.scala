package misc.collections.ext

import org.scalatest.{path, Matchers}

class SimpleExtSpec extends path.FunSpec with Matchers{

  describe("Greet examples - using existential types to preserve types") {

    case class Greets[T](private val name: T) {
      def hello(): Unit = println("Hello " + name)

      def getName: T = name
    }

    val greets1: Greets[String] = Greets("John")
    val greets2: Greets[Symbol] = Greets('Jack)

    val greetsList1 = List(greets1, greets2)

    they("each have the right type in List[Greets]") {
      greetsList1.head.getClass.getSimpleName matches "Greets[.*]"
      greetsList1.head.getName.getClass.getSimpleName shouldBe "String"
      greetsList1.tail.head.getName.getClass.getSimpleName shouldBe "Symbol"
    }

    // This does NOT compile:
    //val greetsList2 : List[Greets[Any]] = greetsList1

    /*
    Compilation error:
    Error:(10, 50) type mismatch;
     found   : A$A1.this.Greets[String]
     required: A$A1.this.Greets[Any]
    Note: String <: Any, but class Greets is invariant in type T.
    You may wish to define T as +T instead. (SLS 4.5)
    */

    val greetsList3a: List[Greets[_ >: Char with String with Symbol]] = greetsList1
    val greetsList3b: List[Greets[_]] = greetsList1
    val greetsList3c: List[Greets[T] forSome {type T}] = greetsList1


    // this works:
    val greetsList4: List[Greets[_]] = List(greets1, greets2)
  }
}
