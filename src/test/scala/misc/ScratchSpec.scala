package misc

import java.util.TimeZone.getAvailableIDs

import org.scalatest.{Matchers, FlatSpec}

class ScratchSpec extends FlatSpec with Matchers {

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
}
