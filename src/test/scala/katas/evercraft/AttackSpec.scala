package katas.evercraft

import katas.evercraft.Alignment.{Evil, Neutral}
import org.scalatest.{Matchers, path}

class AttackSpec extends path.FunSpec with Matchers {

  describe("Attack") {

    val attacker = Hero(name = "Bully", alignment = Neutral)
    val defender = Hero(name = "Rose", alignment = Neutral)

    it("allows player to attack opponent with a 20-sided die") {
      Attack(attacker, defender, 15)
    }

    it("wins when score beats opponent's armor class") {
      Attack(attacker, defender, 11).wasSuccessful shouldBe true
    }

    it("wins when score matches defender's armor class") {
      Attack(attacker, defender, 10).wasSuccessful shouldBe true
    }

    it("loses when score is less than defender's armor class") {
      Attack(attacker, defender, 9).wasSuccessful shouldBe false
    }

    it("adds strength modifier to attack roll") {
      val attacker = Hero(name = "Bully", alignment = Evil, strength = 12)
      Attack(attacker, defender, 9).wasSuccessful shouldBe true
    }

    it("adds strength to damage dealt") {
      val attacker = Hero(name = "Bully", alignment = Evil, strength = 12)
      Attack(attacker, defender, 10).defenderAfterHit.hitPoints shouldBe 3
    }
  }
}
