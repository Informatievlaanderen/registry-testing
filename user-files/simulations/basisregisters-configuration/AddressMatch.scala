package basisregisters.configuration

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object AddressMatch {
  val feeder = csv("addressmatch.csv.zip").unzip.batch.random

  val search =
    feed(feeder)
    .exec(
      http(session => "Voer een adres match uit")
        .get("/adresmatch")
        .queryParam("Straatnaam", "${IN_Straatnaam}")
        .queryParam("Huisnummer", "${IN_Huisnummer}")
        .queryParam("Niscode", "${IN_NIS-Gemeentecode}")
        .queryParam("Postcode", "${IN_Postcode}")
        .queryParam("Gemeentenaam", "${IN_Gemeentenaam}")
        .check(status is 200)
    )
}

// api/v{version}/adresmatch?Gemeentenaam={Gemeentenaam}&Niscode={Niscode}&Postcode={Postcode}&KadStraatcode={KadStraatcode}&RrStraatcode={RrStraatcode}&Straatnaam={Straatnaam}&Huisnummer={Huisnummer}&Index={Index}&Busnummer={Busnummer}

/*
KadStraatcode
Straatnaam
Huisnummer
Niscode
Postcode
Gemeentenaam
*/
