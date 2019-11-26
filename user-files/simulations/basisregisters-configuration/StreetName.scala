package basisregisters.configuration

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import simulations.infrastructure.CheckIfConditions.{hasStatus}

object StreetName {
  val feeder = csv("all-streetname-ids.csv.zip").unzip.batch.random

  val list =
    exec(
      http(session => "Vraag alle straatnamen op")
        .get("/straatnamen")
        .check(status.is(200))
    )

  val detail =
    feed(feeder)
    .exec(
      http(session => "Vraag een straatnaam op")
        .get("/straatnamen/${straatnaamId}")
        .check(status.in(200, 404, 410))
        .check(checkIf(hasStatus(200)) { jsonPath("$..identificator.objectId").is("${straatnaamId}") })
    )
}
