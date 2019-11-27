package simulations.infrastructure

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

import io.gatling.core.check._
import io.gatling.core.check.time._
import io.gatling.core.stats.message._

object RegistryRequestChecks {

  implicit class RegistryResponseTimeChecks(val responseTime: FindCheckBuilder[ResponseTimeCheckType, ResponseTimings, Int]) extends AnyVal {
    def doesNotExceed(maximumResponseTime: FiniteDuration, endPoint: String) = 
      responseTime
        .lte(maximumResponseTime.toMillis.toInt)
        .name(s"[$endPoint] Request too slow") 

    def isValidForList(responseTimes: MaximumResponseTimes, registy: RegistryName) = 
        doesNotExceed(responseTimes.list, registy.list)

    def isValidForDetail(responseTimes: MaximumResponseTimes, registy: RegistryName) = 
        doesNotExceed(responseTimes.detail, registy.detail)
  }

}