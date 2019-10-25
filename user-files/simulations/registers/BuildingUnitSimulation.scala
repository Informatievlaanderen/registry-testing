package registers

import io.gatling.core.Predef._

import basisregisters.configuration._
import simulations._

class BuildingUnitSimulation extends Simulation with Configuration {
  setUp(
    weightedScenario(
      "BuildingUnits",
      Possibility(BuildingUnit.list, 30), 
      Possibility(BuildingUnit.detail, 70)
    )
  )
}
