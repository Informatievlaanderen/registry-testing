package registers

import io.gatling.core.Predef._

import basisregisters.configuration._
import simulations.infrastucture._

class ParcelSimulation extends RegistrySimulation(Load.Standard) {
  setUp(
    weightedScenario(
      "Parcels",
      Possibility(Parcel.list, 30), 
      Possibility(Parcel.detail, 70)
    )
  )
}
