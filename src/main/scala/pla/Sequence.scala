package pla


class Sequence(val low: Int, val stride: Int, val high: Int) {

  val direction = stride / stride.abs
  def generate() = gen(low)

  private def gen(low: Int) : List[Int] = {
    def shouldStop = low * direction > high * direction
    if (shouldStop)  List()
    else low :: gen(low + stride)
  }
}

object Sequence {
  def sequence(low: Int, stride: Int, high: Int) : List[Int] = {
    if (stride == 0) throw new RuntimeException
    new Sequence(low, stride, high).generate()}
}
