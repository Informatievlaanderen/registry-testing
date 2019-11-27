package basisregisters.configuration

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import simulations.infrastructure._
import simulations.infrastructure.RegistryRequestChecks._

object CrabLegacy {
  private val crabHouseNumber = new CrabName("housenumber")
  private val crabSubaddress = new CrabName("subaddress")

  val listHouseNumbers = (responseTimes: MaximumResponseTimes) =>
    exec(
      http(session => "Vraag alle huisnummer addressen op")
        .get("/crabhuisnummers")
        .check(status.isValidForList(crabHouseNumber))
        .check(responseTimeInMillis.isValidForList(responseTimes, crabHouseNumber))
    )

  val listSubaddresses = (responseTimes: MaximumResponseTimes) =>
    exec(
      http(session => "Vraag alle subadres addressen op")
        .get("/crabsubadressen")
        .check(status.isValidForList(crabSubaddress))
        .check(responseTimeInMillis.isValidForList(responseTimes, crabSubaddress))
    )

  private class CrabName(listName: String) extends RegistryName("crab") {
    override val list = s"$name/$listName"
  }
}
