package basisregisters.configuration

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import simulations.infrastructure._
import simulations.infrastructure.RegistryRequestChecks._

object Parcel {
  private val parcel = new RegistryName("parcel")

  val feeder = csv("all-parcel-ids.csv.zip").unzip.batch.random

  val list = (responseTimes: MaximumResponseTimes) =>
    exec(
      http(session => "Vraag alle percelen op")
        .get("/percelen")
        .check(
          status.isValidForList(parcel),
          responseTimeInMillis.isValidForList(responseTimes, parcel)
        )
    )

  val detail = (responseTimes: MaximumResponseTimes) =>
    feed(feeder)
    .exec(
      http(session => "Vraag een perceel op")
        .get("/percelen/${capaKey}")
        .check(
          status.isValidForDetail(parcel),
          responseTimeInMillis.isValidForDetail(responseTimes, parcel)
        )
        .checkWhenStatus(200)(jsonPath("$..identificator.objectId").is("${capaKey}"))
    )
}
