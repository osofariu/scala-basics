package preso.types.phantom

import org.scalatest.{Matchers, path}

class PhantomSpec extends path.FunSpec with Matchers {

  sealed trait DoorState
  final class Open extends DoorState
  final class Closed extends DoorState

  class Door[State <: DoorState] private() {
    def open[T >: State <: Closed](): Door[Open] = this.asInstanceOf[Door[Open]]
    def close[T >: State <: Open](): Door[Closed] = this.asInstanceOf[Door[Closed]]
  }

  object Door {
    def apply() = new Door[Closed]
  }

  describe("Door") {
    it("blah blah") {
    }
    val closed = Door() // it's closed
    val opened = closed.open()
    val closedAgain = opened.close()

    //closedAgain.close()
    // Error:(30, 17) inferred type arguments [PhantomSpec.this.Closed] do not conform to method close's type parameter bounds [T >: PhantomSpec.this.Closed <: PhantomSpec.this.Open]
  }
}
