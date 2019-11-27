package basisregisters.configuration

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import simulations.infrastructure._
import simulations.infrastructure.RegistryRequestChecks._

object Address {
  private val address = RegistryName("address")

  val feeder = csv("all-address-ids.csv.zip").unzip.batch.random

  val list = (responseTimes: MaximumResponseTimes) =>
    exec(
      http(session => "Vraag alle adressen op")
        .get("/adressen")
        .check(status.isValidForList(address))
        .check(responseTimeInMillis.isValidForList(responseTimes, address))
    )

  val detail = (responseTimes: MaximumResponseTimes) =>
    feed(feeder)
    .exec(
      http(session => "Vraag een adres op")
        .get("/adressen/${addressId}")
        .check(status.isValidForDetail(address))
        .checkWhenStatus(200)(jsonPath("$..identificator.objectId").is("${addressId}"))
        .check(responseTimeInMillis.isValidForDetail(responseTimes, address))
    )
}