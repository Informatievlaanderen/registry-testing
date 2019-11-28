package registries

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import infrastructure._
import infrastructure.RegistryRequestChecks._

object AddressMatch {
  private val addressmatch = RegistryName("addressmatch")

  val feeder = csv("addressmatch.csv.zip").unzip.batch.random

  val possibleCalls = List(
      Possibility(search, 100)
    )

  private def search(responseTimes: MaximumResponseTimes) = {
    feed(feeder)
    .exec(
      http(session => "Voer een adres match uit")
        .get("/adresmatch")
        .queryParam("Straatnaam", "${IN_Straatnaam}")
        .queryParam("Huisnummer", "${IN_Huisnummer}")
        .queryParam("Niscode", "${IN_NIS-Gemeentecode}")
        .queryParam("Postcode", "${IN_Postcode}")
        .queryParam("Gemeentenaam", "${IN_Gemeentenaam}")
        .check(
          status.isValidForFilteredList(addressmatch),
          responseTimeInMillis.isValidForFilteredList(responseTimes, addressmatch)
        )
    )
  }
}
