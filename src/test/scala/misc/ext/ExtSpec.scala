package misc.ext

import org.scalatest.{path, Matchers}

class ExtSpec extends path.FunSpec with Matchers {


  trait VirtualMachine[A] {
    def compile: A
    def run: A => Int
    def something : this.type
  }

  case class SimpleVirtualMachine[A](compile: A, run: A => Int)
    extends VirtualMachine[A] {
    override def something: SimpleVirtualMachine.this.type = this
  }

  def helloWorldVM(s: String) = SimpleVirtualMachine(s, (s: String) => 10)
  def intVM(i: Int) = SimpleVirtualMachine(i, (s: Int) => 10)

  def compileAndRun1[A](vm: VirtualMachine[A]) = vm.run(vm.compile)
  def compileAndRun2(vm: VirtualMachine[A forSome {type A}]) = vm.run(vm.compile)

  // won't compile
  //def compileAndRun3(vm: VirtualMachine[A] forSome {type A}) = vm.run(vm.compile)
  describe("Virtual Machine"){

    it("can compileAndRun1 on SimpleVirtualMachine") {
      val comp = "stuff"
      def run[A] = (a: A) => 20
      compileAndRun1(SimpleVirtualMachine(comp, run)) shouldBe 20
    }

    it("can compileAndRun2 on SimpleVirtualMachine") {
      val comp = "stuff"
      def run[A] = (a: A) => 20
      compileAndRun2(SimpleVirtualMachine(comp, run)) shouldBe 20
    }
  }

  describe("calculating length for lists containing type that extends HasLength") {

    trait HasLength {
      def length : Int
    }

    def lengths0[T <: HasLength](l: List[T]) : Int = {
      l.foldLeft(0)((acc, v) => acc + v.length)
    }

    def lengths1(l: List[T] forSome {type T <: HasLength}) : Int = {
      l.foldLeft(0)((acc, v) => acc + v.length)
    }

    // TODO: which one is right in general above or below?
    def lengths2(l: List[T forSome {type T <: HasLength}]) : Int = {
      l.foldLeft(0)((acc, v) => acc + v.length)
    }

    it("adds lengths for items that have length") {

      case class Foo(s: String) extends HasLength {
        override def length: Int = s.length
      }

      lengths0(List[Foo](Foo("hello"), Foo("there"))) shouldBe 10
      lengths1(List[Foo](Foo("hello"), Foo("there"))) shouldBe 10
      lengths2(List[Foo](Foo("hello"), Foo("there"))) shouldBe 10
    }
  }

  describe("function arguments are contravariant and return types are covariant") {

    // if I am expected to return a Chicken             I can assign that to a Bird because a Bird is a Chicken
    // if I am expected to take   a Bird as an argument I can instead give it a Chicken because a chicken is a Bird (go down the hierarchy)

    class ParentArg {}
    class ChildArg extends ParentArg {}
    class ParentReturn {}
    class ChildReturn extends ParentReturn {}

    def f1(v: ParentArg) : ChildReturn = new ChildReturn

    val cr: ChildReturn = f1(new ParentArg)
    val pr: ParentReturn = f1(new ParentArg)
    val cr2: ChildReturn = f1(new ChildArg)
    val pr2: ParentReturn = f1(new ChildArg)

    def f2(v: ChildArg) : ParentReturn = new ParentReturn
    val pr3: ParentReturn = f2(new ChildArg)
    //val cr3: Cr = f2(new Ca)
    //val pr3 = f2(new Pa)


    // ChildArg is a ParentArg, so this would also work:
    val l3 : List[ChildArg] = List(new ChildArg)
    val l4 : List[ParentArg] = l3

  }
}
