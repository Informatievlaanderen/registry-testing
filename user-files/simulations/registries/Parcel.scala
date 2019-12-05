package registries

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import infrastructure._
import infrastructure.RegistryRequestChecks._

object Parcel {
  private val parcel = new RegistryName("parcel")

  val possibleCalls = List(
      Possibility(list, 30),
      Possibility(detail, 70)
    )

  private def list(responseTimes: MaximumResponseTimes) = {
    exec(
      http(session => "Vraag alle percelen op")
        .get("/percelen")
        .check(
          status.isValidForList(parcel),
          responseTimeInMillis.isValidForList(responseTimes, parcel)
        )
    )
  }

  private def detail(responseTimes: MaximumResponseTimes) = {
    feed(Feeders.parcelIds)
    .exec(
      http(session => "Vraag een perceel op")
        .get("/percelen/${capaKey}")
        .check(
          status.isValidForDetail(parcel),
          responseTimeInMillis.isValidForDetail(responseTimes, parcel)
        )
        .checkWhenStatus(200)(jsonPath("$..identificator.objectId").is("${capaKey}"))
    )
  }
}
