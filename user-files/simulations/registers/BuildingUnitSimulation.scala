package registers

import io.gatling.core.Predef._

import basisregisters.configuration._
import simulations._

class BuildingUnitSimulation extends Simulation with Configuration {
  setUp(
    weightedScenario(
      "BuildingUnits",
      WeightedPossibility(BuildingUnit.list, 30), 
      WeightedPossibility(BuildingUnit.detail, 70)
    )
  )
}
