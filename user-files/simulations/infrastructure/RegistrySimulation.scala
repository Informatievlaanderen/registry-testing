package infrastructure

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.core.structure.PopulationBuilder

import scala.concurrent.duration._
import scala.math.{min, max}

abstract class RegistrySimulation extends Simulation {
    
  def setUp(
    name: String, 
    load: TrafficLoadConfiguration, 
    possibilities: List[Possibility]): SetUp = {          

    setUp(
      scenario(name)
        .exec(createSwitch(possibilities, load))
        .inject(
          incrementUsersPerSec(load.incrementUsersPerCycleBy)
            .times(load.numberOfCycles)
            .eachLevelLasting(10 seconds)
            .startingFrom(load.incrementUsersPerCycleBy)
        ).protocols(Protocols.default)
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
