package registries

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import infrastructure._
import infrastructure.RegistryRequestChecks._

object CrabLegacy {
  private val crabHouseNumber = new CrabName("housenumbers")
  private val crabSubaddress = new CrabName("subaddresses")
  private val housenumberFeeder = csv("all-crab-housenumber-ids.csv.zip").unzip.batch.random
  private val subaddressFeeder = csv("all-crab-subaddress-ids.csv.zip").unzip.batch.random
  
  val possibleCalls = List(
      Possibility(listHouseNumbers, 10),
      Possibility(filteredListHouseNumbers, 40),
      Possibility(listSubaddresses, 10),
      Possibility(filteredListSubaddresses, 40)
    )
    
  private def listHouseNumbers(responseTimes: MaximumResponseTimes) = {
    exec(
      http(session => "Vraag alle huisnummer addressen op")
        .get("/crabhuisnummers")
        .check(
          status.isValidForList(crabHouseNumber),
          responseTimeInMillis.isValidForList(responseTimes, crabHouseNumber)
        )
    )
  }

  private def filteredListHouseNumbers(responseTimes: MaximumResponseTimes) = {
    feed(housenumberFeeder)
    .exec(
      http(session => "Vraag alle subadres addressen op")
        .get("/crabhuisnummers")
        .queryParam("CrabHouseNumberId", "${HouseNumberId}")
        .check(
          status.isValidForFilteredList(crabHouseNumber),
          responseTimeInMillis.isValidForFilteredList(responseTimes, crabHouseNumber)
        )
    )
  }

  private def listSubaddresses(responseTimes: MaximumResponseTimes) = {
    exec(
      http(session => "Vraag alle subadres addressen op")
        .get("/crabsubadressen")
        .check(
          status.isValidForList(crabSubaddress),
          responseTimeInMillis.isValidForList(responseTimes, crabSubaddress)
        )
    )
  }

  private def filteredListSubaddresses(responseTimes: MaximumResponseTimes) = {
    feed(subaddressFeeder)
    .exec(
      http(session => "Vraag alle subadres addressen op")
        .get("/crabsubadressen")
        .queryParam("CrabSubaddressId", "${SubaddressId}")
        .check(
          status.isValidForFilteredList(crabSubaddress),
          responseTimeInMillis.isValidForFilteredList(responseTimes, crabSubaddress)
        )
    )
  }

  private class CrabName(listName: String) extends RegistryName("crab") {
    override val list = s"$name/$listName"
    override val filteredList = s"$name/filtered$listName"
  }
}
