package preso.types.exist

import org.scalatest.{Matchers, path}

import scala.language.existentials

class ExistentialIntroSpec extends path.FunSpec with Matchers{

  describe("Greet examples - using existential types to preserve types") {

    case class Greets[T](private val name: T) {
      def hello(): Unit = println("Hello " + name)
      def getName: T = name
    }

    val greets_string: Greets[String] = Greets("John")
    val greets_symbol: Greets[Symbol] = Greets('Jack)
    val greetsList = List(greets_string, greets_symbol)

    they("each have the right type in List[Greets]") {
      greetsList.head.getClass.getSimpleName matches "Greets[.*]"
      greetsList.head.getName.getClass.getSimpleName shouldBe "String"
      greetsList.tail.head.getName.getClass.getSimpleName shouldBe "Symbol"
    }



    // this is what Intellij has suggested to me, but it's not very generic:
    val greetsList3a: List[Greets[_ >: Char with String with Symbol]] = greetsList

    // you use a wildcard to indicate you don't care what type Greets holds:
    val greetsList3b: List[Greets[_]] = greetsList

    // this is the same thing, but it's more explicit (and would allow you to set bounds on T)
    val greetsList3c: List[Greets[T] forSome {type T}] = greetsList
  }
}
