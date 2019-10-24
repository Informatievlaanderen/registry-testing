package registers

import io.gatling.core.Predef._

import basisregisters.configuration._
import simulations._

class ParcelSimulation extends Simulation with Configuration {
  setUp(
    weightedScenario(
      "Parcels",
      WeightedPossibility(Parcel.list, 30), 
      WeightedPossibility(Parcel.detail, 70)
    )
  )
}
