package registries

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import infrastructure._
import infrastructure.RegistryRequestChecks._

object Postinfo {
  private val postinfo = new RegistryName("postinfo")
  
  val possibleCalls = List(
      Possibility(list, 10),
      Possibility(filteredList, 30),
      Possibility(detail, 60)
    )

  private def list(responseTimes: MaximumResponseTimes) = {
    exec(
      http(session => "Vraag alle percelen op")
        .get("/postinfo")
        .check(
          status.isValidForList(postinfo),
          responseTimeInMillis.isValidForList(responseTimes, postinfo)
        )
    )
  }

  private def filteredList(responseTimes: MaximumResponseTimes) = {
    feed(Feeders.addressMatchParameters) // todo: repalce with specific municipality name feeder
    .exec(
      http(session => "Vraag alle percelen op")
        .get("/postinfo")
        .queryParam("MunicipalityName", "${IN_Gemeentenaam}")
        .check(
          status.isValidForFilteredList(postinfo),
          responseTimeInMillis.isValidForFilteredList(responseTimes, postinfo)
        )
    )
  }

  private def detail(responseTimes: MaximumResponseTimes) = {
    feed(Feeders.postalCodes)
    .exec(
      http(session => "Vraag een perceel op")
        .get("/postinfo/${postalCode}")
        .check(
          status.isValidForDetail(postinfo),
          responseTimeInMillis.isValidForDetail(responseTimes, postinfo)
        )
        .checkWhenStatus(200)(jsonPath("$..identificator.objectId").is("${postalCode}"))
    )
  }
}