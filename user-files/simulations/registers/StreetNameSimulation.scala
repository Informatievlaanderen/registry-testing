package registers

import io.gatling.core.Predef._

import basisregisters.configuration._
import simulations.infrastructure._

class StreetnameSimulation extends RegistrySimulation(Load.Standard) {
  setUp(
    weightedScenario(
      "StreetNames",
      Possibility(StreetName.list, 30), 
      Possibility(StreetName.detail, 70)
    )
  )
}
