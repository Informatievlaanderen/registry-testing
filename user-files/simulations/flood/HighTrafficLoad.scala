import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.core.structure.ChainBuilder
import io.gatling.core.structure.PopulationBuilder

import registries._
import infrastructure._

class HighTrafficLoad extends Simulation {
  
  private val load = Load.High
    
  setUp(
    scenario("MixedSimulation - high load")
      .exec(randomSwitch(Possibilities.balance(load, Api.possibleRegistries): _*))
      .inject(
        incrementUsersPerSec(load.incrementUsersPerCycleBy)
          .times(load.numberOfCycles)
          .eachLevelLasting(10 seconds)
          .startingFrom(load.incrementUsersPerCycleBy)
      ).protocols(Protocols.default)
  )
}
