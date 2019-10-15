package vnext

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

import flood._

import basisregisters._

class MunicipalitySimulation extends Simulation {
  val testApiKey = "cafebabe-1337-1337-1337-cdcdcdcdcdcd"

  // val httpProtocol = http
  val httpProtocol = httpConfigFlood
    .baseUrl("https://api.basisregisters.vlaanderen/v1")
    .header("x-api-key", testApiKey)
    .warmUp("https://www.vlaanderen.be/nl")
    .acceptHeader("application/json, text/javascript, */*; q=0.5")
    .acceptLanguageHeader("en-US,en;q=0.9,nl;q=0.8")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("Basisregisters Vlaanderen Gatling - Municipality Simulation")
    .doNotTrackHeader("1")
    .disableCaching

  val municipalityScenario = scenario("Municipalities")
    .exec(Municipality.list, Municipality.detail)

  setUp(
    municipalityScenario.inject(
      incrementUsersPerSec(5)
        .times(100)
        .eachLevelLasting(30 seconds)
        .separatedByRampsLasting(10 seconds)
        .startingFrom(5)
    ).protocols(httpProtocol))
}
