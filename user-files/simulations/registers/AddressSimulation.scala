package registers

import io.gatling.core.Predef._

import basisregisters.configuration._
import simulations.infrastructure._

class AddressSimulation extends RegistrySimulation(Load.Standard) {
  setUp(
    weightedScenario(
      "Addresses",
      Possibility(Address.list, 30), 
      Possibility(Address.detail, 70)
    )
  )
}
