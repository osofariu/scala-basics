package preso.typeclass.exp.inherit

import scala.language.implicitConversions

object Expression {

  sealed trait Evaluator {
    def value: Int
  }

  sealed trait Expression extends Evaluator
  case class Number(value: Int) extends Expression
  case class Plus(lhs: Expression, rhs: Expression) extends Expression {
    override def value: Int = lhs.value + rhs.value
  }
  case class Minus(lhs: Expression, rhs: Expression) extends Expression {
    override def value: Int = lhs.value - rhs.value
  }

  def evaluate(exp: Expression): Int = {
    exp.value
  }
}
