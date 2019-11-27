package basisregisters.configuration

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import simulations.infrastructure._
import simulations.infrastructure.RegistryRequestChecks._

object Municipality {
  private val municipality = new RegistryName("municipality")
  
  val feeder = csv("all-municipality-niscodes.csv").batch.random

  val list = (responseTimes: MaximumResponseTimes) =>
    exec(
      http(session => "Vraag alle gemeenten op")
        .get("/gemeenten")
        .check(status.isValidForList(municipality))
        .check(responseTimeInMillis.isValidForDetail(responseTimes, municipality))
    )
    
  val detail = (responseTimes: MaximumResponseTimes) =>
    feed(feeder)
    .exec(
      http(session => "Vraag een gemeente op")
        .get("/gemeenten/${nisCode}")
        .check(status.isValidForDetail(municipality))
        .checkWhenStatus(200)(jsonPath("$..identificator.objectId").is("${nisCode}"))
        .check(responseTimeInMillis.isValidForDetail(responseTimes, municipality))
    )
}
