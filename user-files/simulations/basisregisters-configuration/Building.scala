package basisregisters.configuration

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import simulations.infrastructure._
import simulations.infrastructure.RegistryRequestChecks._

object Building {
  private val building = RegistryName("building")

  val feeder = csv("all-building-ids.csv.zip").unzip.batch.random

  val list = (responseTimes: MaximumResponseTimes) =>
    exec(
      http(session => "Vraag alle gebouwen op")
        .get("/gebouwen")
        .check(
          status.isValidForList(building),
          responseTimeInMillis.isValidForList(responseTimes, building)
        )
    )

  val detail = (responseTimes: MaximumResponseTimes) =>
    feed(feeder)
    .exec(
      http(session => "Vraag een gebouw op")
        .get("/gebouwen/${buildingId}")
        .check(
          status.isValidForDetail(building),
          responseTimeInMillis.isValidForDetail(responseTimes, building)
        )
        .checkWhenStatus(200)(jsonPath("$..identificator.objectId").is("${buildingId}"))
    )
}