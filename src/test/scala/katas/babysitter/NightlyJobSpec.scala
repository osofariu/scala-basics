package katas.babysitter

import org.scalatest.{Matchers, FlatSpec}

class NightlyJobSpec extends FlatSpec with Matchers {

  "babysitter" should "earn $12 per hour for first shift between start-time and bed-time when bedtime before midnight" in {
    new NightlyJob(5, 12, 11).firstShiftPay() should be (72)
  }

  "babysitter" should "earn $12 per hour between start-time and end-time when bedtime before midnight and bed-time after end-time" in {
    new NightlyJob(5, 11, 12).firstShiftPay() should be (72)
  }

  "basysitter" should "earn $0 per hour for first shift between start-time and bed-time when start-time after bed-time" in {
    new NightlyJob(1, 2, 10).firstShiftPay() should be (0)
  }

  "babysitter" should "earn $8 per hours for second shift between bed-time and midnight when bed-time before midnight" in {
    new NightlyJob(5, 12, 11).secondShiftPay() should be (8)
  }

  "babysitter" should "earn $0 per hours between bed-time and midnight when bed-time after midnight" in {
    new NightlyJob(5, 1, 1).secondShiftPay() should be (0)
  }

  "babysitter" should "correctly use hours after midnight" in {
    def job = new NightlyJob(5, 1, 1)
    job.endTime should be(13)
  }
  
}


/*
- start after 5pm
- leave no later than 4am
- $12 start-time to bedtime
- $8 bedtime to midnight
- $16 midnight to end
- get paid for full hours
 */