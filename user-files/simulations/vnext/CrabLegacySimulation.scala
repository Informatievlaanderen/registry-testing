package vnext

import io.gatling.core.Predef._

import scala.concurrent.duration._

import basisregisters._

class CrabLegacySimulation extends Simulation with SimulationConfiguration {
  setUp(
    scenario("CRAB_Addresses")
      // executes list, than detail => for load using a random action instead of same scenario each time?
      .exec(CrabLegacy.listHouseNumbers, CrabLegacy.listSubaddresses)
      .inject(
        incrementUsersPerSec(5)
          .times(100)
          .eachLevelLasting(30 seconds)
          .separatedByRampsLasting(10 seconds)
          .startingFrom(5)
      ).protocols(httpProtocol))
}
