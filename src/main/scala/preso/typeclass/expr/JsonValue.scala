package preso.typeclass.expr

sealed trait JsonValue
case class JsonObject(entries: Map[String, JsonValue]) extends JsonValue
case class JsonArray(entries: Seq[JsonValue]) extends JsonValue
case class JsonString(value: String) extends JsonValue
case class JsonNumber(value: BigDecimal) extends JsonValue
case class JsonBoolean(value: Boolean) extends JsonValue
case object JsonNull extends JsonValue

object JsonObject {
  type Pair = (String, JsonValue)
  def apply(pairs: Pair*): JsonValue = {
    new JsonObject(pairs.toMap)
  }
}

object JsonArray {
  def apply(seq: JsonValue*): JsonValue = {
    new JsonArray(seq)
  }
}