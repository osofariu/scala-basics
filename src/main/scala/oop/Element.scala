package oop

import Element.element

class Element(val items: Array[String]) {

  def above(other: Element) = {
    element(this.items ++ other.items)
  }

  def beside(other: Element) = {

    val meHeightenedItems = heighten(other.height)
    val otherHeightenedItems = other.heighten(this.height)

    val a = for (p <- meHeightenedItems zip otherHeightenedItems)
      yield widen(p._1, width) + "|" + widen(p._2, other.width)
    element(a)
  }

  def height = items.length
  def width = items.foldLeft(0)((i, s) => math.max(i, s.length))
  override def toString = items.mkString("\n")

  private def widen(str: String, width: Int) : String  = {
    if (str.length >= width)
      str
    else
      str ++ "." * (width - str.length)
  }

  private def heighten(height: Int) : Array[String] = {
    if (this.height >= height)
      this.items
    else {
      this.items ++ Array.fill[String](height - this.height)(".")
    }
  }
}


object Element {

  def element(elems: String*): Element = {
    new Element(elems.toArray)
  }

  def element(elems: Array[String]) = {
    new Element(elems)
  }
}
