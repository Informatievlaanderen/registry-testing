package registers

import io.gatling.core.Predef._

import basisregisters.configuration._
import simulations.infrastructure._

class MunicipalitySimulation extends RegistrySimulation(Load.Standard) {  
  setUp(
    weightedScenario(
      "Municipalities",
      Possibility(Municipality.list(trafficLoad.responseTime), 30),
      Possibility(Municipality.detail(trafficLoad.responseTime), 70)
    )
  )
}
