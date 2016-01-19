package misc.types

import org.scalatest.{path, Matchers}

import scala.language.{higherKinds, reflectiveCalls}

class TypesSpec extends path.FunSpec with Matchers {

  describe("higher types examples") {

    it("simple Id type returns type it was given") {
      type Id[A] = A
      val v: Id[String] = "foo"
      v shouldBe "foo"
    }

    it("given id function and value, apply function to value") {
      // simple example:
      type F[_] = (Int) => Int
      type I = Int
      val f: F[I] = (i: Int) => i + 1
      f(12) shouldBe 13

      // building on simpler types:
      type Id[A[_], B] = A[B]
      def incFun: Id[F, Int] = (i: Int) => i + 1
      incFun(12) shouldBe 13
    }

    it("implement Structural type example with MailBoxLike") {

      type MailBoxLike = {
        def receive(a: String): String
      }

      def send(msg: String, box: MailBoxLike) : String = box receive msg

      // don't actually need to use type alias here:
      def send2(msg: String, box: {def receive(msg: String) : String}) = box receive msg

      object Home {def receive(a: String) = s"HOME: $a"}
      object Work {def receive(a: String) = s"WORK: $a"}

      send("hello at home", Home) shouldBe "HOME: hello at home"
      send("Hello at work", Work) shouldBe "WORK: Hello at work"

      send2("hello at home", Home) shouldBe send("hello at home", Home)
      send2("Hello at work", Work) shouldBe send("Hello at work", Work)
    }
  }

  describe("variance in subtyping affects what sort of subtypes can be accepted") {

    describe("co-variance") {
      class Cage[+T](val v: T)
      class Animal
      class Lion extends Animal

      it("a co-variant type can be accepted by a more generic type") {
        def cagedLion = new Cage[Lion](new Lion)
        def cagedAnimal: Cage[Animal] = cagedLion

        // a familiar example with List, which is co-variant:
        def l: List[Animal] = List[Animal](new Lion, new Animal)
      }

      it("co-variant type cannot be accepted by a more specific type") {
        def cagedAnimal = new Cage[Animal](new Lion)
        //def cagedLion : Cage[Lion] = c1                         // does NOT compile
        //def l: List[Lion] = List[Animal](new Lion, new Animal)  // does NOT compile
      }
    }

    describe("invariance") {
      class Cage[T](val v: T)
      class Animal
      class Lion extends Animal

      it("invariant type cannot be accepted by a more general type") {
        def cagedLion = new Cage[Lion](new Lion)
        //def cagedAnimal : Cage[Animal] = c1          // does NOT compile
      }

      it("invariant type cannot be accepted by a more specialized type") {
        def c1 = new Cage[Animal](new Lion)
        //def cagedLion : Cage[Lion] = c1          // does NOT compile
      }
    }

    describe("contra-variance") {
      abstract class Container[-T] {
        def animal: Animal
      }
      class Animal
      class Lion extends Animal

      class AnimalCage[-T] extends Container {
        def animal = new Animal
      }
      class LionCage[-T] extends Container {
        def animal = new Animal
      }

      it("contra-variant type can be accepted by more specialized type") {
        def cagedAnimal = new AnimalCage[Animal]
        def cagedLion: AnimalCage[Lion] = cagedAnimal
      }

      it("contra-variant type cannot be accepted by a more general types") {
        def cagedLion = new LionCage
        //def cagedAnimal: AnimalCage[Animal] = cagedLion         // does NOT compile
      }
    }
  }
}
