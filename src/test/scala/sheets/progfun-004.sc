def precision = 0.001

def good_enough(n: Double, r:Double) : Boolean = {
  Math.abs(n - r*r) < precision
}

def improve(n: Double, r:Double) : Double = {
  (r + n/r) / 2
}

def sqrt(n: Double) : Double = {
  def sqrt_helper(n: Double, r: Double): Double = {
    if (good_enough(n, r)) {
      return r
    } else {
      sqrt_helper(n, improve(n, r))
    }
  }
  return sqrt_helper(n, 1)
}


sqrt(2)

