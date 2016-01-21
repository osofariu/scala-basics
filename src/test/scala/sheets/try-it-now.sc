sealed trait Expression
case class Number(value: Int) extends Expression
case class Plus(lhs: Expression, rhs: Expression) extends Expression
case class Minus(lhs: Expression, rhs: Expression) extends Expression



implicit class PlusExpression(lhs: Expression, rhs: Expression) {
  def value =
}