package registers

import io.gatling.core.Predef._

import basisregisters.configuration._
import simulations.infrastructure._

class PostinfoSimulation extends RegistrySimulation(Load.Standard) {
  setUp(
    weightedScenario(
      "Postinfos",
      Possibility(Postinfo.list(trafficLoad.responseTime), 30),
      Possibility(Postinfo.detail(trafficLoad.responseTime), 70)
    )
  )
}
