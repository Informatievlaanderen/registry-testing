package api

import io.gatling.core.Predef._
import io.gatling.core.structure._

import basisregisters.configuration._
import simulations.infrastructure._

class PeakTrafficLoadSimulation extends RegistrySimulation(Load.Peak) {
  setUp(
    weightedScenario(
      "MixedSimulation",
      Possibility(Address.list(trafficLoad.responseTime), 30),
      Possibility(Address.detail(trafficLoad.responseTime), 70),
      Possibility(Building.list(trafficLoad.responseTime), 30),
      Possibility(Building.detail(trafficLoad.responseTime), 70),
      Possibility(BuildingUnit.list(trafficLoad.responseTime), 30),
      Possibility(BuildingUnit.detail(trafficLoad.responseTime), 70),
      Possibility(CrabLegacy.listHouseNumbers(trafficLoad.responseTime), 30),
      Possibility(CrabLegacy.listSubaddresses(trafficLoad.responseTime), 70),
      Possibility(Municipality.list(trafficLoad.responseTime), 30),
      Possibility(Municipality.detail(trafficLoad.responseTime), 70),
      Possibility(Parcel.list(trafficLoad.responseTime), 30),
      Possibility(Parcel.detail(trafficLoad.responseTime), 70),
      Possibility(Postinfo.list(trafficLoad.responseTime), 30),
      Possibility(Postinfo.detail(trafficLoad.responseTime), 70),
      Possibility(StreetName.list(trafficLoad.responseTime), 30),
      Possibility(StreetName.detail(trafficLoad.responseTime), 70)
    )
  )
}
