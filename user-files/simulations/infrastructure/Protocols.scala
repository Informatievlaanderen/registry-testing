package infrastructure

import io.gatling.http.Predef._
import io.gatling.core.Predef._

object Protocols {
  private val loadTestApiKey = System.getProperty("api_key")
  private val url = System.getProperty("base_url")
  private val warmupUrl = System.getProperty("warmup_url")

  val default = http
    .baseUrl(url)
    .header("x-api-key", loadTestApiKey)
    .warmUp(warmupUrl)
    .acceptHeader("application/json, text/javascript, */*; q=0.5")
    .acceptLanguageHeader("en-US,en;q=0.9,nl;q=0.8")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("Basisregisters Vlaanderen Gatling - Load Test Simulation")
    .doNotTrackHeader("1")
    .disableCaching
}
