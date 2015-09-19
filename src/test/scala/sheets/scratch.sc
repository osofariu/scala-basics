import scala.collection.mutable.ArrayBuffer

val buf = ArrayBuffer(1, 7, 2, 9).sortWith((a, b) => a < b)
buf(0)
val buf2 = ArrayBuffer(1, 7, 2, 9).sorted
List("Steve", "Tom", "John", "Bob").sortWith(_.compareTo(_) < 0)
val ints = Array(1, 7, 2, 9)
ints.mkString("<", ",", ">")
scala.util.Sorting.quickSort(ints)
ints.mkString("<", ",", ">")

val scores = Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
scores("Alice") // cannot change values or arr new ones

val mutableScores = scala.collection.mutable.Map(("Alice", 10), ("Bob", 11))
mutableScores("Alice") = 12
mutableScores += (("James", 1))
mutableScores += ("Brad" -> 15)
val newScores = scores + ("Alice" -> 11) // how to change immutable map by creating new one
newScores.mapValues(e => e * 2)

class Foo (x: Int, y: Int)

val foo = new Foo(1, 2)

"ABC".split("").reduce(_+_)
