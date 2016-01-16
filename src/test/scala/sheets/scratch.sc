trait F {
  def upcase: String
}

case class Foo(s: String) extends F

object F {
  def upCase(s: String) = s.toUpperCase()
  def apply(s: String) = Foo(s)
}

F("hello").upcase



