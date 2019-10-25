package registers

import io.gatling.core.Predef._

import basisregisters.configuration._
import simulations._

class BuildingSimulation extends Simulation with Configuration {
  setUp(
    weightedScenario(
      "Buildings",
      Possibility(Building.list, 30), 
      Possibility(Building.detail, 70)
    )
  )
}
