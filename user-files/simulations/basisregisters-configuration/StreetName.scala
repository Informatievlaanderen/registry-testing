package basisregisters.configuration

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import simulations.infrastructure._
import simulations.infrastructure.RegistryRequestChecks._
import simulations.infrastructure.CheckIfConditions.{hasStatus}

object StreetName {
  private val streetname = new RegistryName("streetname")

  val feeder = csv("all-streetname-ids.csv.zip").unzip.batch.random

  val list = (responseTimes: MaximumResponseTimes) =>
    exec(
      http(session => "Vraag alle straatnamen op")
        .get("/straatnamen")
        .check(status.is(200))
        .check(responseTimeInMillis.isValidForList(responseTimes, streetname))
    )

  val detail = (responseTimes: MaximumResponseTimes) =>
    feed(feeder)
    .exec(
      http(session => "Vraag een straatnaam op")
        .get("/straatnamen/${straatnaamId}")
        .check(status.in(200, 404, 410))
        .check(checkIf(hasStatus(200)) { jsonPath("$..identificator.objectId").is("${straatnaamId}") })
        .check(responseTimeInMillis.isValidForDetail(responseTimes, streetname))
    )
}
