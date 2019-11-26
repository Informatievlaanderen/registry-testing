package basisregisters.configuration

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import simulations.infrastructure.CheckIfConditions.{hasStatus}

object Postinfo {
  val feeder = csv("all-postal-codes.csv").batch.random

  val list =
    exec(
      http(session => "Vraag alle percelen op")
        .get("/postinfo")
        .check(status.is(200))
    )

  val detail =
    feed(feeder)
    .exec(
      http(session => "Vraag een perceel op")
        .get("/postinfo/${postalCode}")
        .check(status.in(200, 404, 410))
        .check(checkIf(hasStatus(200)) { jsonPath("$..identificator.objectId").is("${postalCode}") })
    )
}
