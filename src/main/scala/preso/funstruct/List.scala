package preso.funstruct

sealed trait List[+T] {
  def foldLeft[A](acc: A)(f: (A, T) => A) : A
}

case object Nil extends List[Nothing] {
  override def foldLeft[A](acc: A)(f: (A, Nothing) => A): A = acc
}

case class Cons[+T](head: T, tail: List[T]) extends List[T] {
  override def foldLeft[A](acc: A)(f: (A, T) => A): A = tail.foldLeft(f(acc, head))(f)
}

object List {

  def head[A](l: List[A]) : A = l match {
    case Nil => throw new RuntimeException("Cannot take head of an empty list")
    case (Cons (head, tail)) => head
  }

  def tail[A](l: List[A]) : List[A]= l match {
    case Nil => throw new RuntimeException("Cannot tail an empty list")
    case Cons(head, tail) => tail
  }

  def sum(l: List[Int]): Int = l match {
    case Nil => 0
    case Cons(head, tail) => head + sum(tail)
  }

  def len[A](l: List[A]) : Int = l match {
    case Nil => 0
    case Cons(head, tail) => 1 + len(tail)
  }

  def apply[A](l: A*): List[A] = {
    if (l.isEmpty) Nil
    else Cons(l.head, apply(l.tail: _*))
  }
}
