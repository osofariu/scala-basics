package katas

import scala.collection.mutable

class FizzBuzz(inputRange:Range) {

  def fizzBuzz() : List[String]  = {
    var output  = scala.collection.mutable.ArrayBuffer.empty[String]
    for (i <-inputRange) {
      if (i % 3 == 0)
        output += "FIZZ"
      else
        output += i.toString
    }
    output.toList
  }
}
