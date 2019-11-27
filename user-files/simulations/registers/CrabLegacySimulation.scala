package registers

import io.gatling.core.Predef._

import basisregisters.configuration._
import simulations.infrastructure._

class CrabLegacySimulation extends RegistrySimulation(Load.Standard) {
  setUp(
    weightedScenario(
      "CRAB_Addresses",
      Possibility(CrabLegacy.listHouseNumbers(trafficLoad.responseTime), 30),
      Possibility(CrabLegacy.listSubaddresses(trafficLoad.responseTime), 70)
    )
  )
}
