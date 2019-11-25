package registers

import io.gatling.core.Predef._

import basisregisters.configuration._
import simulations.infrastructure._

class CrabLegacySimulation extends RegistrySimulation(Load.Standard) {
  setUp(
    weightedScenario(
      "CRAB_Addresses",
      Possibility(CrabLegacy.listHouseNumbers, 30), 
      Possibility(CrabLegacy.listSubaddresses, 70)
    )
  )
}
