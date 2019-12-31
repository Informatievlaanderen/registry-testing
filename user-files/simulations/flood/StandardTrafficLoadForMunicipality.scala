import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.core.structure.ChainBuilder
import io.gatling.core.structure.PopulationBuilder

import registries._
import infrastructure._

class StandardTrafficLoadForMunicipality extends Simulation {
  
  private val load = Load.Standard
  private val name = "Municipality - standard load"
  private val possibilities = Municipality.possibleCalls

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
