package katas.evercraft

class Hero private (val name: String, val hitPoints: Int, val alignment: Alignment, val strength: Ability) {
  def armorClass = 10

  def dexterity = Ability()
  def constitution = Ability()
  def wisdom = Ability()
  def intelligence = Ability()
  def charisma = Ability()

  def copy(name: String = name, hitPoints : Int = hitPoints, alignment: Alignment = alignment, strength: Ability = strength) = {
    new Hero(name, hitPoints,alignment, strength)
  }
}

object Hero {
  def apply(name: String, hitPoints: Int = 5, alignment: Alignment, strength: Ability = Ability()): Hero = {
    new Hero(name = name, hitPoints = hitPoints, alignment = alignment, strength = strength)
  }
}