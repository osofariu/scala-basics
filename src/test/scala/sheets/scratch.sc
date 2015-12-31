private def modifiers =  Map(1 -> -5, 2 -> -4, 3-> -4, 4 -> -3, 5 -> -3,
  6 -> -2, 7 -> -2, 8 -> -1, 9 -> -1, 10 -> 0,
  11 -> 0, 12 -> 1, 13 -> 1, 14 -> 2, 15 -> 2,
  16 -> 3, 17 -> 3, 18 -> 4, 19 -> 4, 20 -> 5)

def ditto(value: Int) : Int = {
    Math.floor((value - 10) / 2.0).toInt
}

(1 to 20).foreach { i =>
  println("expected:" + modifiers(i) + " actual: " + ditto(i))
  assert(modifiers(i) == ditto(i))
}
Math.floor(-3.5)