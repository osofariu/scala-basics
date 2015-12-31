package katas.evercraft

class Attack(attacker: Hero, defender: Hero, score: Int) {
  def wasSuccessful = score + attacker.strength.modifier >= defender.armorClass

  def damage = 1 + attacker.strength

  def defenderAfterHit = defender.copy(hitPoints = defender.hitPoints - damage)
}

object Attack {
  def apply(attacker: Hero, defender: Hero, score: Int): Attack = {
    new Attack(attacker: Hero, defender: Hero, score: Int)
  }
}
