package registers

import io.gatling.core.Predef._

import basisregisters.configuration._
import simulations._

class ParcelSimulation extends Simulation with Configuration {
  setUp(
    weightedScenario(
      "Parcels",
      Possibility(Parcel.list, 30), 
      Possibility(Parcel.detail, 70)
    )
  )
}
