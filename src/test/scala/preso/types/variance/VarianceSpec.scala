package preso.types.variance

import org.scalatest.{Matchers, path}

class VarianceSpec extends path.FunSpec with Matchers {
  describe("variance in subtyping affects what sort of subtypes can be accepted") {

    describe("co-variance") {
      class Cage[+T](val v: T)
      class Animal
      class Lion extends Animal

      it("a co-variant type can be accepted by a more generic type") {
        def cagedLion = new Cage[Lion](new Lion)
        def cagedAnimal: Cage[Animal] = cagedLion

        // a familiar example with List, which is co-variant:
        def animals: List[Animal] = List[Animal](new Lion, new Animal)
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