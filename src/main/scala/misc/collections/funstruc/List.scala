package misc.collections.funstruc

sealed trait List[+A] {
  def head : A
  def len: Int
}

case object Nil extends List[Nothing] {
  override def head: Nothing = ???
  override def len: Int = ???
}

case class Cons[+A](head: A, tail: List[A]) extends List[A] {
  override def len: Int = ???
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

  def tailOpt[A](l: List[A]) : Option[List[A]] = l match {
    case Nil => Option.empty
    case Cons(head, tail) => Option(tail)
  }

  def setHead[A](a: A, l:List[A]) = {
    Cons(a, tail(l))
  }

  def drop[A](l: List[A], n: Int): List[A] = n match {
    case 0 => l
    case n => drop(tail(l), n - 1)
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

