package registers

import io.gatling.core.Predef._

import basisregisters.configuration._
import simulations._

class CrabLegacySimulation extends Simulation with Configuration {
  setUp(
    weightedScenario(
      "CRAB_Addresses",
      Possibility(CrabLegacy.listHouseNumbers, 30), 
      Possibility(CrabLegacy.listSubaddresses, 70)
    )
  )
}
