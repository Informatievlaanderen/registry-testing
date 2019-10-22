package basisregisters

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Building {
  val feeder = csv("all-building-ids.csv.zip").unzip.batch.random

  val list =
    exec(
      http(session => "Vraag alle gebouwen op")
        .get("/gebouwen")
        .check(status.is(200))
    )

  val detail =
    feed(feeder)
    .exec(
      http(session => "Vraag een gebouw op")
        .get("/gebouwen/${buildingId}")
        .check(status.is(200))
        .check(jsonPath("$..identificator.objectId").is("${buildingId}"))
    )
}
