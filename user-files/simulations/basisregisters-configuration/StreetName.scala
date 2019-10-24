package basisregisters.configuration

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object StreetName {
  val feeder = csv("all-streetname-ids.csv.zip").unzip.batch.random

  val list =
    exec(
      http(session => "Vraag alle straatnamen op")
        .get("/straatnamen")
        .check(status.is(200))
    )

  val detail =
    feed(feeder)
    .exec(
      http(session => "Vraag een straatnaam op")
        .get("/straatnamen/${straatnaamId}")
        .check(status.is(200))
        .check(jsonPath("$..identificator.objectId").is("${straatnaamId}"))
    )
}
