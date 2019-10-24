package registers

import io.gatling.core.Predef._

import basisregisters.configuration._
import simulations._

class StreetnameSimulation extends Simulation with Configuration {
  setUp(
    weightedScenario(
      "StreetNames",
      WeightedPossibility(StreetName.list, 30), 
      WeightedPossibility(StreetName.detail, 70)
    )
  )
}
