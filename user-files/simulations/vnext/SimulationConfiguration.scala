package vnext

import io.gatling.http.Predef._
import io.gatling.core.Predef._

//import flood._

trait SimulationConfiguration {
  private val loadTestApiKey = "cafebabe-1337-1337-1337-cdcdcdcdcdcd"

  val httpProtocol = http
  // val httpProtocol = httpConfigFlood
    .baseUrl("https://api.basisregisters.vlaanderen/v1")
    .header("x-api-key", loadTestApiKey)
    .warmUp("https://www.vlaanderen.be/nl")
    .acceptHeader("application/json, text/javascript, */*; q=0.5")
    .acceptLanguageHeader("en-US,en;q=0.9,nl;q=0.8")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("Basisregisters Vlaanderen Gatling - Municipality Simulation")
    .doNotTrackHeader("1")
    .disableCaching
}