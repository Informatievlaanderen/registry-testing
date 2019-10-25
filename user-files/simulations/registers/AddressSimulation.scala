package registers

import io.gatling.core.Predef._

import basisregisters.configuration._
import simulations._

class AddressSimulation extends Simulation with Configuration {
  setUp(
    weightedScenario(
      "Addresses",
      Possibility(Address.list, 30), 
      Possibility(Address.detail, 70)
    )
  )
}
