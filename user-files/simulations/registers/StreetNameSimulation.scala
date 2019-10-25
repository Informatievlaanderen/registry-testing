package registers

import io.gatling.core.Predef._

import basisregisters.configuration._
import simulations._

class StreetnameSimulation extends Simulation with Configuration {
  setUp(
    weightedScenario(
      "StreetNames",
      Possibility(StreetName.list, 30), 
      Possibility(StreetName.detail, 70)
    )
  )
}
