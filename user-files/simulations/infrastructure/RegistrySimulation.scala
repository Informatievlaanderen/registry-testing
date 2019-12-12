package infrastructure

import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.core.structure.PopulationBuilder

import scala.concurrent.duration._
import scala.math.{min, max}

abstract class RegistrySimulation extends Simulation {
  
  private val loadTestApiKey = "REPLACEME"

  val httpProtocol = http
  // val httpProtocol = httpConfigFlood
    .baseUrl("https://api.basisregisters.vlaanderen/v1")
    .header("x-api-key", loadTestApiKey)
    .warmUp("https://www.vlaanderen.be/nl")
    .acceptHeader("application/json, text/javascript, */*; q=0.5")
    .acceptLanguageHeader("en-US,en;q=0.9,nl;q=0.8")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("Basisregisters Vlaanderen Gatling - Load Test Simulation")
    .doNotTrackHeader("1")
    .disableCaching

  def setUp(
    name: String, 
    load: TrafficLoadConfiguration, 
    possibilities: List[Possibility]): SetUp = {          
    
    val numberOfUsers = atLeast1(load.numberOfUsers)
    val userIncrementation = atLeast1(atMost20(numberOfUsers / 5))
    val numberOfLevels = atLeast1(numberOfUsers / userIncrementation) 
    println("numberOfUsers: " + numberOfUsers + ", incrementNumberOfUsersPerCycle: " + userIncrementation + ", NumberOfCycles: " + numberOfLevels)

    setUp(
      scenario(name)
        .exec(createSwitch(possibilities, load))
        .inject(
          incrementUsersPerSec(userIncrementation)
            .times(numberOfLevels)
            .eachLevelLasting(10 seconds)
            .startingFrom(userIncrementation)
        ).protocols(httpProtocol)
    )
  }  

  private def createSwitch(chains: List[Possibility], load: TrafficLoadConfiguration) = {
    
    var (totalWeight, possibilities) = chains
      .foldLeft[(Double, List[Possibility])](0, Nil){ (result, chain) => (result._1 + chain.weight, result._2 :+ chain) }
    
    val balancedPossibilities = possibilities.map(
      possibility => {
        val weight = if (totalWeight == 0) possibility.weight else possibility.weight / totalWeight * 100  
        (weight, possibility.createChain(load.responseTimes))
      })

    randomSwitch(balancedPossibilities: _*)
  }

  private def atLeast1(value: Int) = max(1, value)
  private def atMost20(value: Int) = min(20, value)
}
