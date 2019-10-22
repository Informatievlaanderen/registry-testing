package basisregisters

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Parcel {
  val feeder = csv("all-parcel-ids.csv.zip").unzip.batch.random

  val list =
    exec(
      http(session => "Vraag alle percelen op")
        .get("/percelen")
        .check(status.is(200))
    )

  val detail =
    feed(feeder)
    .exec(
      http(session => "Vraag een perceel op")
        .get("/percelen/${capaKey}")
        .check(status.is(200))
        .check(jsonPath("$..identificator.objectId").is("${capaKey}"))
    )
}
