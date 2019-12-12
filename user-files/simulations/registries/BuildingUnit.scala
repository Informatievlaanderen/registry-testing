package registries

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import infrastructure._
import infrastructure.RegistryRequestChecks._

object BuildingUnit {
  private val buildingUnit = RegistryName("buildingUnit")
    
  val possibleCalls = List(
      Possibility(list, 10),
      Possibility(filteredList, 20),
      Possibility(detail, 70)
    )

  private def list(responseTimes: MaximumResponseTimes) = {
    exec(
      http(session => "Vraag alle gebouweenheiden op")
        .get("/gebouweenheden")
        .check(
          status.isValidForList(buildingUnit),
          responseTimeInMillis.isValidForList(responseTimes, buildingUnit)
        )
    )
  }

  private def filteredList(responseTimes: MaximumResponseTimes) = {
    feed(Feeders.addressIds)
    .exec(
      http(session => "Vraag alle gebouweenheiden op voor adres")
        .get("/gebouweenheden")
        .queryParam("adresobjectid", "${addressId}")
        .check(
          status.isValidForList(buildingUnit),
          responseTimeInMillis.isValidForList(responseTimes, buildingUnit)
        )
    )
  }
  
  private def detail(responseTimes: MaximumResponseTimes) = {
    feed(Feeders.buildingUnitIds)
    .exec(
      http(session => "Vraag een gebouweenheid op")
        .get("/gebouweenheden/${buildingUnitId}")
        .check(
          status.isValidForDetail(buildingUnit),
          responseTimeInMillis.isValidForDetail(responseTimes, buildingUnit)
        )
        .checkWhenStatus(200)(jsonPath("$..identificator.objectId").is("${buildingUnitId}"))
    )
  }
}
