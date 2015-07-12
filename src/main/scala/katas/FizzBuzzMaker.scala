package katas

class FizzBuzzMaker(inputRange:Range) {

  class Fizz() {
    def is(i : Int) = i % 3 == 0
    def convert()  = "FIZZ"
  }

  class Buzz() {
    def is(i : Int)  = i % 5 == 0
    def convert()  = "BUZZ"
  }

  class FizzBuzz() {
    def is(i : Int) = i % 15 == 0
    def convert() = "FIZZBUZZ"
  }

  def fizz = new Fizz()
  def buzz  = new Buzz()
  def fizzBuzz = new FizzBuzz()

  def transform(i: Int): String = {
    if (fizzBuzz.is(i)) fizzBuzz.convert()
    else if (fizz.is(i)) fizz.convert()
    else if (buzz.is(i)) buzz.convert()
    else i.toString
  }
  
  def make() : List[String]  = inputRange.map(transform(_)).toList
}
