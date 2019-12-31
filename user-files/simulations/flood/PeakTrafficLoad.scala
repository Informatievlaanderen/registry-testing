import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.core.structure.ChainBuilder
import io.gatling.core.structure.PopulationBuilder

import registries._
import infrastructure._

class PeakTrafficLoad extends Simulation {
  
  private val load = Load.Peak
    
  setUp(
    scenario("MixedSimulation - peak load")
      .exec(randomSwitch(Possibilities.balance(load, Api.possibleRegistries): _*))
      .inject(
        incrementUsersPerSec(load.incrementUsersPerCycleBy)
          .times(load.numberOfCycles)
          .eachLevelLasting(load.cycleDuration)
          .separatedByRampsLasting(load.rampDuration)
          .startingFrom(load.initialUsers)
      ).protocols(Protocols.default)
  )
}
