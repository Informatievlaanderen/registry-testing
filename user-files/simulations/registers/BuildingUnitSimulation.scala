package registers

import io.gatling.core.Predef._

import basisregisters.configuration._
import simulations.infrastructure._

class BuildingUnitSimulation extends RegistrySimulation(Load.Standard) {
  setUp(
    weightedScenario(
      "BuildingUnits",
      Possibility(BuildingUnit.list, 30), 
      Possibility(BuildingUnit.detail, 70)
    )
  )
}
