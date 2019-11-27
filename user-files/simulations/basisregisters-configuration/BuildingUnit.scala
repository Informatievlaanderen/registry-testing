package basisregisters.configuration

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import simulations.infrastructure._
import simulations.infrastructure.RegistryRequestChecks._

object BuildingUnit {
  private val buildingUnit = RegistryName("buildingUnit")

  val feeder = csv("all-buildingunit-ids.csv.zip").unzip.batch.random

  val list = (responseTimes: MaximumResponseTimes) =>
    exec(
      http(session => "Vraag alle gebouweenheiden op")
        .get("/gebouweenheden")
        .check(
          status.isValidForList(buildingUnit),
          responseTimeInMillis.isValidForList(responseTimes, buildingUnit)
        )
    )

  val detail = (responseTimes: MaximumResponseTimes) =>
    feed(feeder)
    .exec(
      http(session => "Vraag een gebouweenheid op")
        .get("/gebouweenheden/${buildingUnitId}")
        .check(
          status.isValidForDetail(buildingUnit),
          responseTimeInMillis.isValidForDetail(responseTimes, buildingUnit)
        )
        .checkWhenStatus(200)(jsonPath("$..identificator.objectId").is("${buildingUnitId}"))
    )
}
