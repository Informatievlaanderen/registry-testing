package registers

import io.gatling.core.Predef._

import basisregisters.configuration._
import simulations._

class PostinfoSimulation extends Simulation with Configuration {
  setUp(
    weightedScenario(
      "Postinfos",
      WeightedPossibility(Postinfo.list, 30), 
      WeightedPossibility(Postinfo.detail, 70)
    )
  )
}
