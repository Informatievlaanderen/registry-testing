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
        .exec(randomSwitch(Possibilities.balance(load, possibilities): _*))
        .inject(
          incrementUsersPerSec(load.incrementUsersPerCycleBy)
            .times(load.numberOfCycles)
            .eachLevelLasting(load.cycleDuration)
            .separatedByRampsLasting(load.rampDuration)
            .startingFrom(load.initialUsers)
        ).protocols(Protocols.default)
    )
  }  
}
