package basisregisters

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Address {
  val feeder = csv("all-address-ids.csv.zip").unzip.batch.random

  val list =
    exec(
      http(session => "Vraag alle adressen op")
        .get("/adressen")
        .check(status.is(200))
    )

  val detail =
    feed(feeder)
    .exec(
      http(session => "Vraag een adres op")
        .get("/adressen/${addressId}")
        .check(status.is(200))
        .check(jsonPath("$..identificator.objectId").is("${addressId}"))
    )
}
