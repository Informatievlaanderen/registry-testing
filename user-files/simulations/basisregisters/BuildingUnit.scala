package basisregisters

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object BuildingUnit {
  val feeder = csv("all-buildingunit-ids.csv.zip").unzip.batch.random

  val list =
    exec(
      http(session => "Vraag alle gebouweenheiden op")
        .get("/gebouweenheden")
        .check(status.is(200))
    )

  val detail =
    feed(feeder)
    .exec(
      http(session => "Vraag een gebouweenheid op")
        .get("/gebouweenheden/${buildingUnitId}")
        .check(status.is(200))
        .check(jsonPath("$..identificator.objectId").is("${buildingUnitId}"))
    )
}
