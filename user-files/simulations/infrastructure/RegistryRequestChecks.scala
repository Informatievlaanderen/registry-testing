package simulations.infrastructure

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import io.gatling.http.check.status._
import io.gatling.core.check._
import io.gatling.core.check.time._
import io.gatling.core.stats.message._

object RegistryRequestChecks {

implicit class RegistryResponseTimeChecks(val responseTime: FindCheckBuilder[ResponseTimeCheckType, ResponseTimings, Int]) extends AnyVal {
  private def doesNotExceed(maximumResponseTime: FiniteDuration, pathDefinition: String) = 
    responseTime
      .lte(maximumResponseTime.toMillis.toInt)
      .name(s"[$pathDefinition] Request too slow") 

  def isValidForList(responseTimes: MaximumResponseTimes, registy: RegistryName) = 
    doesNotExceed(responseTimes.list, registy.list)

  def isValidForFilteredList(responseTimes: MaximumResponseTimes, registy: RegistryName) = 
    doesNotExceed(responseTimes.filteredList, registy.filteredList)

  def isValidForDetail(responseTimes: MaximumResponseTimes, registy: RegistryName) = 
    doesNotExceed(responseTimes.detail, registy.detail)
}

private val listStatus = 200
private val detailStatuses = List(200, 404, 410)

implicit class RegistryResponseStatusChecks(val status: FindCheckBuilder[HttpStatusCheckType, Response, Int]) extends AnyVal {
  private def isValidForListWithPath(listPath: String) =  
    status
      .is(listStatus)
      .name(s"[$listPath] expected status $listStatus")  
  
  def isValidForList(registy: RegistryName) =   
    isValidForListWithPath(registy.list)

  def isValidForFilteredList(registy: RegistryName) =   
    isValidForListWithPath(registy.filteredList)  

  def isValidForDetail(registy: RegistryName) = 
    status
      .in(detailStatuses)
      .name(s"[${registy.detail}] expected status from (${detailStatuses.mkString(", ")})")
  }
}