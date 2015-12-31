package katas.evercraft

import katas.evercraft.Alignment.Neutral
import org.scalatest.{path, Matchers}

class HeroSpec extends path.FunSpec with Matchers {

  describe("a Hero") {

    val myHero = Hero(name = "Sue", alignment = Neutral)

    it("has a name") {
      myHero.name shouldBe "Sue"
    }

    it("has an alignment") {
      myHero.alignment shouldBe Neutral
      Hero(name = "Shady Jim", alignment = Alignment.Evil).alignment shouldBe Alignment.Evil
      Hero(name = "DoGood", alignment = Alignment.Good).alignment shouldBe Alignment.Good

    }

    it("has armor class and hit points") {
      myHero.armorClass shouldBe 10
      myHero.hitPoints shouldBe 5
    }

    it("has abilities: Strength, Dexterity, Constitution, Wisdom, Intelligence, Charisma") {
      myHero.strength.score shouldBe 10
      myHero.dexterity.score shouldBe 10
      myHero.constitution.score shouldBe 10
      myHero.wisdom.score shouldBe 10
      myHero.intelligence.score shouldBe 10
      myHero.charisma.score shouldBe 10
    }
  }
}
