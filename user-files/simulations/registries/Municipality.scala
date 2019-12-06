package registries

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import infrastructure._
import infrastructure.RegistryRequestChecks._

object Municipality {
  private val municipality = new RegistryName("municipality")
   
  val possibleCalls = List(
      Possibility(list, 30),
      Possibility(detail, 70)
    )

  private def list(responseTimes: MaximumResponseTimes) = {
    exec(
      http(session => "Vraag alle gemeenten op")
        .get("/gemeenten")
        .check(
          status.isValidForList(municipality),
          responseTimeInMillis.isValidForDetail(responseTimes, municipality)
        )
    )
  }
    
  private def detail(responseTimes: MaximumResponseTimes) = {
    feed(Feeders.postalInfo)
    .exec(
      http(session => "Vraag een gemeente op")
        .get("/gemeenten/${NisCode}")
        .check(
          status.isValidForDetail(municipality),
          responseTimeInMillis.isValidForDetail(responseTimes, municipality)
        )
        .checkWhenStatus(200)(jsonPath("$..identificator.objectId").is("${NisCode}"))
    )
  }
}
