package basisregisters

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Address {
  val feeder = csv("all-address-ids.csv.zip").unzip.batch.random

  val detail =
    feed(feeder)
    .exec(
      http(session => "Vraag een adres op")
        .get("/adressen/${addressId}")
        .check(status.is(200))
        .check(jsonPath("$..identificator.objectId").is("${addressId}"))
    )
}
