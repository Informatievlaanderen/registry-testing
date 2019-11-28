package registries

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import infrastructure._
import infrastructure.RegistryRequestChecks._

object CrabLegacy {
  private val crabHouseNumber = new CrabName("housenumber")
  private val crabSubaddress = new CrabName("subaddress")
  
  val possibleCalls = List(
      Possibility(listHouseNumbers, 50),
      Possibility(listSubaddresses, 50)
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

  private class CrabName(listName: String) extends RegistryName("crab") {
    override val list = s"$name/$listName"
  }
}
