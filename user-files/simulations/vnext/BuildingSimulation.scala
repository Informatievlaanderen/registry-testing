package vnext

import io.gatling.core.Predef._

import scala.concurrent.duration._

import basisregisters._

class BuildingSimulation extends Simulation with SimulationConfiguration {
  setUp(
    scenario("Buildings")
      // executes list, than detail => for load using a random action instead of same scenario each time?
      .exec(Building.list, Building.detail)
      .inject(
        incrementUsersPerSec(5)
          .times(100)
          .eachLevelLasting(30 seconds)
          .separatedByRampsLasting(10 seconds)
          .startingFrom(5)
      ).protocols(httpProtocol))
}
