package registries

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import infrastructure._
import infrastructure.RegistryRequestChecks._

object Building {
  private val building = RegistryName("building")
  
  val possibleCalls = List(
      Possibility(list, 30),
      Possibility(detail, 70)
    )

  private def list(responseTimes: MaximumResponseTimes) = {
    exec(
      http(session => "Vraag alle gebouwen op")
        .get("/gebouwen")
        .check(
          status.isValidForList(building),
          responseTimeInMillis.isValidForList(responseTimes, building)
        )
    )
  }

  private def detail(responseTimes: MaximumResponseTimes) = {
    feed(Feeders.buildingIds)
    .exec(
      http(session => "Vraag een gebouw op")
        .get("/gebouwen/${buildingId}")
        .check(
          status.isValidForDetail(building),
          responseTimeInMillis.isValidForDetail(responseTimes, building)
        )
        .checkWhenStatus(200)(jsonPath("$..identificator.objectId").is("${buildingId}"))
    )
  }
}
