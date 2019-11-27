package basisregisters.configuration

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import simulations.infrastructure._
import simulations.infrastructure.RegistryRequestChecks._
import simulations.infrastructure.CheckIfConditions.{hasStatus}

object Postinfo {
  private val postinfo = new RegistryName("postinfo")

  val feeder = csv("all-postal-codes.csv").batch.random

  val list = (responseTimes: MaximumResponseTimes) =>
    exec(
      http(session => "Vraag alle percelen op")
        .get("/postinfo")
        .check(status.is(200))
        .check(responseTimeInMillis.isValidForDetail(responseTimes, postinfo))
    )

  val detail = (responseTimes: MaximumResponseTimes) =>
    feed(feeder)
    .exec(
      http(session => "Vraag een perceel op")
        .get("/postinfo/${postalCode}")
        .check(status.in(200, 404, 410))
        .check(checkIf(hasStatus(200)) { jsonPath("$..identificator.objectId").is("${postalCode}") })
        .check(responseTimeInMillis.isValidForDetail(responseTimes, postinfo))
    )
}
