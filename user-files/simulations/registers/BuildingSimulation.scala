package registers

import io.gatling.core.Predef._

import basisregisters.configuration._
import simulations.infrastructure._

class BuildingSimulation extends RegistrySimulation(Load.Standard) {
  setUp(
    weightedScenario(
      "Buildings",
      Possibility(Building.list, 30),
      Possibility(Building.detail, 70)
    )
  )
}
