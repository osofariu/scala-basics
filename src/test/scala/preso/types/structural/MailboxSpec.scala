package preso.types.structural

import org.scalatest.{path, Matchers}

import scala.language.reflectiveCalls

class MailboxSpec extends path.FunSpec with Matchers {

  describe("type keyword can simulate duck typing") {

    type MailBoxLike = {
      def receive(a: String): String
    }

    object Home {
      def receive(a: String) = s"HOME: $a"
    }
    object Work {
      def receive(a: String) = s"WORK: $a"
    }

    it("implement Structural type example with MailBoxLike") {

      def send(msg: String, box: MailBoxLike): String = box receive msg

      send("hello at home", Home) shouldBe "HOME: hello at home"
      send("Hello at work", Work) shouldBe "WORK: Hello at work"

    }

    it("you can also do it without type, passing in an expected interface") {

      def send(msg: String, box: {def receive(msg: String): String}) = box receive msg

      send("hello at home", Home) shouldBe "HOME: hello at home"
      send("Hello at work", Work) shouldBe "WORK: Hello at work"
    }
  }
}
