
def asInt[T](t: T) : Int = t match  {
  case t: Int => t
  case _ => 0
}

