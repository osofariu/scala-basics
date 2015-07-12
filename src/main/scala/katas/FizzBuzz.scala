package katas

import scala.collection.mutable

class FizzBuzz(inputRange:Range) {
  
  def transform(i: Int): String = {
    if (i % 15 == 0)
      "FIZZBUZZ"
    else if (i % 3 == 0)
      "FIZZ"
    else if (i % 5 == 0)
      "BUZZ"
      else
      i.toString
  }
  
  def fizzBuzz() : List[String]  = inputRange.map(transform(_)).toList
}
