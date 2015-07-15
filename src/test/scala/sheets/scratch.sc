import scala.collection.mutable.ArrayBuffer
object Scratch {
  val buf = ArrayBuffer(1, 7, 2, 9).sortWith((a, b) => a < b)
  buf(0)
  val buf2 = ArrayBuffer(1, 7, 2, 9).sorted
  List("Steve", "Tom", "John", "Bob").sortWith(_.compareTo(_) < 0)
  val ints = Array(1, 7, 2, 9)
  ints.mkString("<", ",", ">")
  scala.util.Sorting.quickSort(ints)
  ints.mkString("<", ",", ">")
}