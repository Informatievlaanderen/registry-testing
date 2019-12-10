package registries

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import infrastructure._
import infrastructure.RegistryRequestChecks._

object StreetName {
  private val streetName = new RegistryName("streetname")
  
  val possibleCalls = List(
      Possibility(list, 10),
      Possibility(filteredList, 20),
      Possibility(detail, 70)
    )

  private def list(responseTimes: MaximumResponseTimes) = {
    exec(
      http(session => "Vraag alle straatnamen op")
        .get("/straatnamen")
        .check(
          status.isValidForList(streetName),
          responseTimeInMillis.isValidForList(responseTimes, streetName)
        )
    )
  }

  private def filteredList(responseTimes: MaximumResponseTimes) = {
    feed(Feeders.postalInfo)
    .exec(
      http(session => "Vraag alle straatnamen op voor een gemeente")
        .get("/straatnamen")
        .queryParam("Gemeentenaam", "${municipalityName}")
        .check(
          status.isValidForFilteredList(streetName),
          responseTimeInMillis.isValidForFilteredList(responseTimes, streetName)
        )
    )
  }

  private def detail(responseTimes: MaximumResponseTimes) = {
    feed(Feeders.streenameIds)
    .exec(
      http(session => "Vraag een straatnaam op")
        .get("/straatnamen/${straatnaamId}")
        .check(
          status.isValidForDetail(streetName),
          responseTimeInMillis.isValidForDetail(responseTimes, streetName)
        )
        .checkWhenStatus(200)(jsonPath("$..identificator.objectId").is("${straatnaamId}"))
    )
  }
}
