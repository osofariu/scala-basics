package katas.babysitter

class NightlyJob(private var _startTime: Int, private var _endTime: Int, private var _bedTime: Int) {

  def startTime = {adjustAfterMidnight(_startTime)}
  def endTime   = {adjustAfterMidnight(_endTime)}
  def bedTime   = {adjustAfterMidnight(_bedTime)}


  def adjustAfterMidnight(aTime: Int) = {
    if (aTime <= 4) aTime + 12
    else aTime
  }

  def firstShiftPay(): Int = {
    12 * (bedTime.min(endTime) - startTime.min(bedTime))
  }

  def secondShiftPay() = {
    8 * (12 - bedTime.min(12))
  }

  def thirdShiftPay() = {
    16 * (endTime.max(12) - 12)
  }

  def pay() = {
    firstShiftPay() + secondShiftPay() + thirdShiftPay()
  }
}
