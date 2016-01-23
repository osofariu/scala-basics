package preso.typeclass.expr

object JsonWriter {

  def wrap(s: String) = "\"" + s + "\""

  def write(value: JsonValue): String = value match {
    case JsonString(str) => {
      wrap(str)
    }
    case JsonNumber(num) => {
      num.toString
    }
    case JsonBoolean(bool) => {
      wrap(bool.toString)
    }
    case JsonNull => {
      wrap("null")
    }
    case JsonObject(entries) => {
      val serializedEntries =
        for ((key, value) <- entries) yield wrap(key) + ": " + write(value)
      s"{${serializedEntries mkString ", "}}"
    }
    case JsonArray(entries) => {
      val serializedEntries = entries map write
      s"[${serializedEntries mkString ", "}]"
    }
  }
}