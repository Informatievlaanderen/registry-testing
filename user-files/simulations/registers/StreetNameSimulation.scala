package registers

import io.gatling.core.Predef._

import basisregisters.configuration._
import simulations.infrastructure._

class StreetnameSimulation extends RegistrySimulation(Load.Standard) {
  setUp(
    weightedScenario(
      "StreetNames",
      Possibility(StreetName.list(trafficLoad.responseTime), 30),
      Possibility(StreetName.detail(trafficLoad.responseTime), 70)
    )
  )
}
