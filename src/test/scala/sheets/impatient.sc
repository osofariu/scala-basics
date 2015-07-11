def signum(n: Int) : Int = {
  if (n == 0)
    0
  else if (n > 0)
    1
  else
    -1
}

signum(10)
signum(-12)
signum(0)



for (i <- 10 to 0 by -1) {
  println (i)
}

//override def foldLeft[B](z: B)
//(op: (B, A) => B): B

"Hello".foldLeft(1L)(_ * _.toInt)

"Hello".map (_.toLong).product

def product(str: String) :Long = {
  if (str.isEmpty)
    1
  else
    str.charAt(0).toInt * product(str.substring(1))
}

product("Hello")

def sum(list: List[Int]): Int = list.foldLeft(0)( _ * _ )

//• xn = y2 if n is even and positive, where y = xn /  2.
//• xn = x·xn – 1 if n is odd and positive.
//• x0 = 1.
//• xn = 1 / x–n if n is negative.

def xn (x: Double, n: Int) : Double = {
  if (n == 0)
    1
  else  if (n < 0)
    1.0 / xn(x, n * -1)
  else if (n % 2 == 0 && n > 0) {
    val sq = xn(x, n / 2)
    sq * sq
  } else
    x * xn(x, n-1)
}

xn (2.1, 4)
