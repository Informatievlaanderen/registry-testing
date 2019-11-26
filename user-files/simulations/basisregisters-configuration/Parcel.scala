package basisregisters.configuration

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import simulations.infrastructure.CheckIfConditions.{hasStatus}

object Parcel {
  val feeder = csv("all-parcel-ids.csv.zip").unzip.batch.random

  val list =
    exec(
      http(session => "Vraag alle percelen op")
        .get("/percelen")
        .check(status.is(200))
    )

  val detail =
    feed(feeder)
    .exec(
      http(session => "Vraag een perceel op")
        .get("/percelen/${capaKey}")
        .check(status.in(200, 404, 410))
        .check(checkIf(hasStatus(200)) { jsonPath("$..identificator.objectId").is("${capaKey}") })
    )
}
