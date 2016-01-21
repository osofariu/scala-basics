package preso

import java.util.TimeZone.getAvailableIDs

import org.scalatest.{Matchers, FlatSpec}

class ScratchSpec extends FlatSpec with Matchers {


  def findFirst[T](at: Array[T], f: T => Boolean) : Option[Int] = {
    def findHelper(pos: Int): Option[Int] =  {
      if (pos >= at.length) Option.empty
      else if (f(at(pos))) Option(pos)
      else findHelper(pos + 1)
    }
    findHelper(0)
  }

  "findFirst" should "find element when match exists" in {
    val at = Array[String]("hello", "there", "world", "okay?")
    findFirst(at, (arg: String) => arg.equals("world")) shouldBe Option(2)
    findFirst(at, (arg: String) => arg.equals("worlds")) shouldBe Option.empty
  }

  "java timezone class" should "return list of ids for America without prefix" in {

    val l = makeAndPrintTimezoneIds()
    l.length should be(164)
    printf(l.mkString("[", ",", "]"))
  }


  "Y experiments" should "reveal how the Y combinator works" in {


    def fact(n : Int) : Int = {if (n == 0) 1 else n * fact(n-1)}

    fact(5) should be(120)
  }


  def makeAndPrintTimezoneIds() : Array[String]= {
    getAvailableIDs.toStream.filter(id => id.contains("America/")).map(id => id.stripPrefix("America/")).toArray
  }

  def isSorted[A](as: Array[A], ordered: (A,A) => Boolean): Boolean = {
    def helper(as: Array[A]) : Boolean = {
      if (as.length <= 1) true
      else ordered(as(0), as(1)) && helper(as.tail)
    }
    helper(as)
  }

  "isSorted" should "return appropriate value indicating whether array is sorted or not" in {
    isSorted(Array("this", "zero"), (a: String, b: String) => a <= b) shouldBe true
    isSorted(Array("zero", "this"), (a: String, b: String) => a <= b) shouldBe false
    isSorted(Array("this", "this"), (a: String, b: String) => a <= b) shouldBe true
  }



}

