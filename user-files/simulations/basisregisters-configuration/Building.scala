package basisregisters.configuration

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import simulations.infrastructure._
import simulations.infrastructure.RegistryRequestChecks._
import simulations.infrastructure.CheckIfConditions.{hasStatus}

object Building {
  private val building = new RegistryName("building")

  val feeder = csv("all-building-ids.csv.zip").unzip.batch.random

  val list = (responseTimes: MaximumResponseTimes) =>
    exec(
      http(session => "Vraag alle gebouwen op")
        .get("/gebouwen")
        .check(status.is(200))
        .check(responseTimeInMillis.isValidForList(responseTimes, building))
    )

  val detail = (responseTimes: MaximumResponseTimes) =>
    feed(feeder)
    .exec(
      http(session => "Vraag een gebouw op")
        .get("/gebouwen/${buildingId}")
        .check(status.in(200, 404, 410))
        .check(checkIf(hasStatus(200)) { jsonPath("$..identificator.objectId").is("${buildingId}") })
        .check(responseTimeInMillis.isValidForDetail(responseTimes, building))
    )
}