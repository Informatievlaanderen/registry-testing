package simulations.infrastucture

import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.core.structure.PopulationBuilder

import scala.concurrent.duration._

class RegistrySimulation(trafficLoad: TrafficLoadConfiguration) extends Simulation {
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

  private val createSwitch = (chains: Seq[Possibility]) => {
    
    var (totalWeight, possibilities) = chains
      .foldLeft[(Double, List[Possibility])](0, Nil){ (result, chain) => (result._1 + chain.weight, result._2 :+ chain) }
    
    val balancedPossibilities = possibilities.map(
      possibility => {
        val weight = if (totalWeight == 0) possibility.weight else possibility.weight / totalWeight * 100  
        (weight, possibility.chain)
      })

    randomSwitch(balancedPossibilities: _*)
  }

  def weightedScenario(name: String, possibilities: Possibility*): PopulationBuilder = {          
    scenario(name)
      .exec(createSwitch(possibilities))
      .inject(
        // rampUsers(10)
        //   .during(60 seconds)

        incrementUsersPerSec(5)
          .times(1)
          .eachLevelLasting(10 seconds)
          .startingFrom(5)
          
        // incrementUsersPerSec(5)
        //   // .times(100)
        //   .times(3)
        //   .eachLevelLasting(10 seconds)
        //   .separatedByRampsLasting(45 seconds)
        //   .startingFrom(5)
      ).protocols(httpProtocol)
  }
}
