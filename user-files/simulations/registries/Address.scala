package registries

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import infrastructure._
import infrastructure.RegistryRequestChecks._

object Address {
  private val address = RegistryName("address")
  
  val feeder = csv("all-address-ids.csv.zip").unzip.batch.random

  val possibleCalls = List(
      Possibility(list, 30),
      Possibility(detail, 70)
    )

  private def list(responseTimes: MaximumResponseTimes) = {
    exec(
      http(session => "Vraag alle adressen op")
        .get("/adressen")
        .check(
          status.isValidForList(address),
          responseTimeInMillis.isValidForList(responseTimes, address)
        )
    )
  }

  private def detail(responseTimes: MaximumResponseTimes) = {
    feed(feeder)
    .exec(
      http(session => "Vraag een adres op")
        .get("/adressen/${addressId}")
        .check(
          status.isValidForDetail(address),
          responseTimeInMillis.isValidForDetail(responseTimes, address)
        )
        .checkWhenStatus(200)(jsonPath("$..identificator.objectId").is("${addressId}"))
    )
  }
}
