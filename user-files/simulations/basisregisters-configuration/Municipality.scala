package basisregisters.configuration

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Municipality {
  val feeder = csv("all-municipality-niscodes.csv").batch.random

  val list =
    exec(
      http(session => "Vraag alle gemeenten op")
        .get("/gemeenten")
        .check(status.is(200))
    )
    
  val detail =
    feed(feeder)
    .exec(
      http(session => "Vraag een gemeente op")
        .get("/gemeenten/${nisCode}")
        .check(status.is(200))
        .check(jsonPath("$..identificator.objectId").is("${nisCode}"))
    )
}
