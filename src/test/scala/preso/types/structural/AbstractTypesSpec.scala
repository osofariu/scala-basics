package preso.types.structural

class AbstractTypesSpec {

  trait Food
  object Grass extends Food
  object Meat extends Food

  trait Animal {
    def eats: Food
  }

  class Cow extends Animal {
    def eats = Grass
  }

  class Lion extends Animal {
    def eats = Meat
  }

  // implement example with Animal hierarchy, each having a Food type they can eat.
  // Structural typing can limit the type of food an animal can eat at the type level, which eliminates having
  // to validate that a cow cannot eat meat, etc.

    /*

    the idea is that you don't have to pass this sub-type as an argument to your constructor, and delay
    assigning it to a concrete type until later.  This makes it easier to write generic code

     */

  /* From Scala School:


  scala> trait Foo { type A; val x: A; def getX: A = x }
  defined trait Foo

  scala> (new Foo { type A = Int; val x = 123 }).getX
  res3: Int = 123

  scala> (new Foo { type A = String; val x = "hey" }).getX
  res4: java.lang.String = hey
   */

}
