package basisregisters.configuration

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import simulations.infrastructure._
import simulations.infrastructure.RegistryRequestChecks._

object CrabLegacy {
  val listHouseNumbers = (responseTimes: MaximumResponseTimes) =>
    exec(
      http(session => "Vraag alle huisnummer addressen op")
        .get("/crabhuisnummers")
        .check(status.is(200))
        .check(responseTimeInMillis.doesNotExceed(responseTimes.list, "crab/housenumber"))
    )

  val listSubaddresses = (responseTimes: MaximumResponseTimes) =>
    exec(
      http(session => "Vraag alle subadres addressen op")
        .get("/crabsubadressen")
        .check(status.is(200))
        .check(responseTimeInMillis.doesNotExceed(responseTimes.list, "crab/subaddress"))
    )
}
