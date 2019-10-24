package registers

import io.gatling.core.Predef._

import basisregisters.configuration._
import simulations._

class AddressSimulation extends Simulation with Configuration {
  setUp(
    weightedScenario(
      "Addresses",
      WeightedPossibility(Address.list, 30), 
      WeightedPossibility(Address.detail, 70)
    )
  )
}
