package basisregisters

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object CrabLegacy {
  
  val listHouseNumbers =
    exec(
      http(session => "Vraag alle huisnummer addressen op")
        .get("/crabhuisnummers")
        .check(status.is(200))
    )

  val listSubaddresses =
    exec(
      http(session => "Vraag alle subadres addressen op")
        .get("/crabsubadressen")
        .check(status.is(200))
    )
}
