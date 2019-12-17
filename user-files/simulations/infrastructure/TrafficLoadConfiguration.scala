package infrastructure

import scala.concurrent.duration._
import scala.math.{min, max}

object Load {
  val Standard = new TrafficLoadConfiguration(
    10,
    MaximumResponseTimes(
      250 milliseconds,
      250 milliseconds,
      250 milliseconds))

  val High = new TrafficLoadConfiguration(
    20,
    MaximumResponseTimes(
      500 milliseconds,
      500 milliseconds,
      500 milliseconds))

  val Peak = new TrafficLoadConfiguration(
    380,
    MaximumResponseTimes(
      30000 milliseconds,
      30000 milliseconds,
      30000 milliseconds))
}

class TrafficLoadConfiguration(
  numberOfUsers: Int,
  val responseTimes: MaximumResponseTimes) {

  val totalNumberOfUsers = atLeast1(numberOfUsers)
  val incrementUsersPerCycleBy = atLeast1(atMost20(totalNumberOfUsers / 5))
  val numberOfCycles = atLeast1(totalNumberOfUsers / incrementUsersPerCycleBy) 

  private def atLeast1(value: Int) = max(1, value)
  private def atMost20(value: Int) = min(20, value)
}

case class MaximumResponseTimes(
  detail: FiniteDuration,
  list: FiniteDuration,
  filteredList: FiniteDuration)