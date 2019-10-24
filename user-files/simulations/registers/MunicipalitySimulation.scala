package registers

import io.gatling.core.Predef._

import basisregisters.configuration._
import simulations._

class MunicipalitySimulation extends Simulation with Configuration {  
  setUp(
    weightedScenario(
      "Municipalities",
      WeightedPossibility(Municipality.list, 30), 
      WeightedPossibility(Municipality.detail, 70)
    )
  )
}
