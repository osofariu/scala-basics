package katas.babysitter

import org.scalatest.{Matchers, FlatSpec}

class NightlyJobSpec extends FlatSpec with Matchers {

  "babysitter" should "earn $12 per hour for six hours in first shift between start-time and bed-time when bedtime before midnight" in {
    new NightlyJob(5, 12, 11).firstShiftPay() should be (72)
  }

  "babysitter" should "earn $12 per hour for six hours between start-time and end-time when bedtime before midnight and bed-time after end-time" in {
    new NightlyJob(5, 11, 12).firstShiftPay() should be (72)
  }

  "basysitter" should "earn $0 for first shift between start-time and bed-time when start-time after bed-time" in {
    new NightlyJob(1, 2, 10).firstShiftPay() should be (0)
  }

  "babysitter" should "earn $8 per hours for one hour in second shift between bed-time and midnight when bed-time before midnight" in {
    new NightlyJob(5, 12, 11).secondShiftPay() should be (8)
  }

  "babysitter" should "correctly use hours after midnight" in {
    def job = new NightlyJob(5, 1, 1)
    job.endTime should be(13)
  }

  "babysitter" should "earn $0 for second shift between bed-time and midnight when bed-time after midnight" in {
    new NightlyJob(5, 1, 1).secondShiftPay() should be (0)
  }

  "babysitter" should "earn $32 for third shift after midnight when working two additional hours after midnight" in {
    new NightlyJob(10, 2, 12).thirdShiftPay() should be (32)
  }

  "babysitter" should "earn $0 for third shift after midnight when no working after midnight" in {
    new NightlyJob(10, 11, 12).thirdShiftPay() should be (0)
  }

  "babysitter" should "earn money for all three shifts when working all three shifts" in {
    def nightlyJob =  new NightlyJob(8, 2, 10)
    nightlyJob.pay() should be (2 * 12 + 2 * 8 + 2 * 16)
  }
}
