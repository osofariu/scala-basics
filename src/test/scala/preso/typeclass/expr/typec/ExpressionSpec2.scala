package preso.typeclass.expr.typec

import org.scalatest.{Matchers, path}

class ExpressionSpec2 extends path.FunSpec with Matchers
{

  sealed trait Expression
  case class Number(value: Int) extends Expression
  case class Plus(lhs: Expression, rhs: Expression) extends Expression
  case class Minus(lhs: Expression, rhs: Expression) extends Expression

}
