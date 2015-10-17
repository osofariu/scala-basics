package oop

import Element.element

abstract class Element {

  def items: Array[String]

  def above(other: Element) = {
    element(this.items ++ other.items)
  }

  def beside(other: Element) = {
    val meHeightened = element(this, other.height).items
    val otherHeightened = element(other, this.height).items

    val a = for (p <- meHeightened zip otherHeightened)
      yield element(p._1, width) + "|" + element(p._2, other.width)
    element(a)
  }

  def height = items.length
  def width = items.foldLeft(0)((i, s) => math.max(i, s.length))
  override def toString = items.mkString("\n")
}

object Element {

  def element(elem: Array[String]) : Element = {
    new ArrayElement(elem)
  }

  def element(elem: String, width: Int) : Element = {
    new WidenedElement(element(elem), width)
  }

  def element(elem: Element, height: Int) : Element = {
    new HeightenedElement(elem, height)
  }

  class WidenedElement(el: Element, width: Int) extends Element {
    private def widen(str: String, width: Int) : String  = {
      if (str.length >= width)
        str
      else
        str ++ "." * (width - str.length)
    }


    def items = for (e <- el.items) yield widen(e, width)

  }

  class HeightenedElement(el: Element, length: Int) extends Element {
    private def heighten(height: Int) : Array[String] = {
      if (el.height >= height)
        el.items
      else {
        el.items ++ Array.fill[String](height - el.height)(".")
      }
    }
    def items = heighten(length)
  }

  class ArrayElement(val items: Array[String]) extends Element {
  }

  def element(elems: String*): Element = {
    element(elems.toArray)
  }
}
