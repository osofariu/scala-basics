package misc

import java.util.TimeZone.getAvailableIDs

import org.scalatest.{Matchers, FlatSpec}

class ScratchSpec extends FlatSpec with Matchers {

  "java timezone class" should "return list of ids for America without prefix" in {

    val l = makeAndPrintTimezoneIds()
    l.length should be(164)
    printf(l.mkString("[", ",", "]"))
  }

  def makeAndPrintTimezoneIds() : Array[String]= {
    getAvailableIDs.toStream.filter(id => id.contains("America/")).map(id => id.stripPrefix("America/")).toArray
  }
}
