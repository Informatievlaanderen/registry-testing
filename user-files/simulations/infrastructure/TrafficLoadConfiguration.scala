package infrastructure

import scala.concurrent.duration._

object Load {
  val Standard = TrafficLoadConfiguration(
    10,
    MaximumResponseTimes(
      250 milliseconds,
      250 milliseconds,
      250 milliseconds))

  val High = TrafficLoadConfiguration(
    20,
    MaximumResponseTimes(
      500 milliseconds,
      500 milliseconds,
      500 milliseconds))

  val Peak = TrafficLoadConfiguration(
    380,
    MaximumResponseTimes(
      30000 milliseconds,
      30000 milliseconds,
      30000 milliseconds))
}

case class TrafficLoadConfiguration(
  numberOfUsers: Int,
  responseTimes: MaximumResponseTimes)

case class MaximumResponseTimes(
  detail: FiniteDuration,
  list: FiniteDuration,
  filteredList: FiniteDuration)