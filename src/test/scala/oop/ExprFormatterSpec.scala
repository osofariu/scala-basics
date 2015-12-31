package oop

import org.scalatest.{Matchers, FlatSpec}

class ExprFormatterSpec extends FlatSpec with Matchers {

  "Expr for sum and division" should "properly format expression based on operator presidence" in {
    val exp1 = BinOp("/", BinOp("+", Number(12), Number(4)), Number(2))
    println(new ExprFormatter().format(exp1).toString)
  }
}
