package registers

import io.gatling.core.Predef._

import basisregisters.configuration._
import simulations.infrastructure._

class ParcelSimulation extends RegistrySimulation(Load.Standard) {
  setUp(
    weightedScenario(
      "Parcels",
      Possibility(Parcel.list(trafficLoad.responseTime), 30),
      Possibility(Parcel.detail(trafficLoad.responseTime), 70)
    )
  )
}
