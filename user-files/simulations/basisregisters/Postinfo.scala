package basisregisters

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Postinfo {
  val feeder = csv("all-postal-codes.csv").unzip.batch.random

  val list =
    exec(
      http(session => "Vraag alle percelen op")
        .get("/postinfo")
        .check(status.is(200))
    )

  val detail =
    feed(feeder)
    .exec(
      http(session => "Vraag een perceel op")
        .get("/postinfo/${postalCode}")
        .check(status.is(200))
        .check(jsonPath("$..identificator.objectId").is("${postalCode}"))
    )
}
