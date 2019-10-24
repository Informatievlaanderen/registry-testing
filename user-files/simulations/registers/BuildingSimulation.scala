package registers

import io.gatling.core.Predef._

import basisregisters.configuration._
import simulations._

class BuildingSimulation extends Simulation with Configuration {
  setUp(
    weightedScenario(
      "Buildings",
      WeightedPossibility(Building.list, 30), 
      WeightedPossibility(Building.detail, 70)
    )
  )
}
