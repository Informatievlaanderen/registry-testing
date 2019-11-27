package basisregisters.configuration

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import simulations.infrastructure._
import simulations.infrastructure.RegistryRequestChecks._
import simulations.infrastructure.CheckIfConditions.{hasStatus}

object BuildingUnit {
  private val buildingUnit = new RegistryName("buildingUnit")

  val feeder = csv("all-buildingunit-ids.csv.zip").unzip.batch.random

  val list = (responseTimes: MaximumResponseTimes) =>
    exec(
      http(session => "Vraag alle gebouweenheiden op")
        .get("/gebouweenheden")
        .check(status.is(200))
        .check(responseTimeInMillis.isValidForList(responseTimes, buildingUnit))
    )

  val detail = (responseTimes: MaximumResponseTimes) =>
    feed(feeder)
    .exec(
      http(session => "Vraag een gebouweenheid op")
        .get("/gebouweenheden/${buildingUnitId}")
        .check(status.in(200, 404, 410))
        .check(checkIf(hasStatus(200)) { jsonPath("$..identificator.objectId").is("${buildingUnitId}") })
        .check(responseTimeInMillis.isValidForDetail(responseTimes, buildingUnit))
    )
}
