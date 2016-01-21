package preso.types.alias

import org.scalatest.{Matchers, path}

class BagSpec extends path.FunSpec with Matchers {

  describe("bag") {
    class Item(val name: String, val volume: Double, val count: Int)

    class FoodBag[T](volumeCapacity: Double)(items: List[Item]) {
      def itemsCount: Int = items.size
      def filledVolume: Double = items.foldLeft(0.0)((acc, item) => acc + (item.volume * item.count))
      def filledPercent: String = (100.0 * filledVolume / volumeCapacity).formatted("%.2f%%")
    }

    object FoodBag {
      type ItemTuple = (String, Double, Int)
      def apply(volumeCapacity: Int)(pairs: ItemTuple*) = {
        val items = pairs.map(p => new Item(p._1, p._2, p._3)).toList
        new FoodBag(volumeCapacity)(items)
      }
    }

    it("with no items is 0% filled") {
      val bag = FoodBag(1500)()
      bag.filledPercent shouldBe "0.00%"
    }

    it("computes volume correctly") {
      val bag = FoodBag(700)(("chilli", 32, 10), ("snack bars", 16, 21), ("jerky", 16, 2))
      32.0 * 10 + 16 * 21 + 16 * 2 shouldBe bag.filledVolume
    }

    it("with several items it gest full quickly") {
      val bag = FoodBag(700)(("chilli", 32, 10), ("snack bars", 16, 21), ("jerky", 16, 2))
      bag.filledPercent shouldBe "98.29%"
    }

    it("even with few items, creating the FoodBag without help from apply is tedious") {
      new FoodBag(1200)(List(new Item("item 1", 12.3, 3), new Item("item 2", 344.0, 1)))
    }
  }
}
