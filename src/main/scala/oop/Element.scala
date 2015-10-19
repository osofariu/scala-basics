package oop

import Element.element

abstract class Element {

  def items: Array[String]

  def height = items.length

  def width = items.foldLeft(0)((i, s) => math.max(i, s.length))

  def above(other: Element) = {
    element(this.items ++ other.items)
  }

  def beside(other: Element) = {
    val meHeightened = heighten(other.height)
    val otherHeightened= other.heighten(this.height)

    element(for (p <- meHeightened.items zip otherHeightened.items)
      yield widen(p._1, this.width) + '|' + p._2)
  }

  private def widen(str: String, width: Int): String = {
    if (str.length >= width)
      str
    else
      str ++ "." * (width - str.length)
  }

  private def heighten(height: Int): Element = {
    if (this.height >= height)
      this
    else {
      this above element('.', height - this.height, this.width)
    }
  }

  override def toString = items.mkString("\n")
}


object Element {

  def element(elems: String*): Element = {
    new ArrayElement(elems.toArray)
  }

  def element(elems: Array[String]) = {
    new ArrayElement(elems)
  }

  def element(item: Char, width: Int, height: Int) = {
    new UniformElement(item, width, height)
  }

  class ArrayElement(val items: Array[String]) extends Element

  class UniformElement(item: Char, width: Int, height: Int) extends Element {
    val widenedItem =  item.toString * (width - 1)
    override def items: Array[String] = Array.fill(height)(widenedItem)
  }
}
