package preso.typeclass.expr

import org.scalatest.{Matchers, path}

class JsonValueSpec extends path.FunSpec with Matchers {

  describe("JsonValue") {

    it("create JsonObject from pair") {
      val p = ("hello", JsonNull)
      JsonObject(p).isInstanceOf[JsonObject]
    }

    it("creates JsonObject from pair*") {
      val p1 = ("hello", JsonNull)
      val p2 = ("there", JsonNull)
      JsonObject(p1, p2).isInstanceOf[JsonObject]
    }

    it("creates JsonArray from JsonValues*") {
      val v1 = JsonObject(("Hi", JsonNull))
      val v2 = JsonObject(("Hi", JsonNull))
      JsonArray(v1, v2).isInstanceOf[JsonArray]
    }
  }
}

