package basisregisters.configuration

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import simulations.infrastructure._
import simulations.infrastructure.RegistryRequestChecks._

object AddressMatch {
  val feeder = csv("addressmatch.csv.zip").unzip.batch.random

  val search = (responseTimes: MaximumResponseTimes) =>
    feed(feeder)
    .exec(
      http(session => "Voer een adres match uit")
        .get("/adresmatch")
        .queryParam("Straatnaam", "${IN_Straatnaam}")
        .queryParam("Huisnummer", "${IN_Huisnummer}")
        .queryParam("Niscode", "${IN_NIS-Gemeentecode}")
        .queryParam("Postcode", "${IN_Postcode}")
        .queryParam("Gemeentenaam", "${IN_Gemeentenaam}")
        .check(status is 200)
        .check(responseTimeInMillis.doesNotExceed(responseTimes.filteredList, "addressmatch"))
    )
}