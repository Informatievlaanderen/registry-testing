package api

import io.gatling.core.Predef._
import io.gatling.core.structure._

import basisregisters.configuration._
import simulations.infrastructure._

class HighTrafficLoadSimulation extends RegistrySimulation(Load.High) {
  setUp(
    weightedScenario(
      "MixedSimulation",
      Possibility(Address.list, 30), 
      Possibility(Address.detail, 70),
      Possibility(Building.list, 30), 
      Possibility(Building.detail, 70),
      Possibility(BuildingUnit.list, 30), 
      Possibility(BuildingUnit.detail, 70),
      Possibility(CrabLegacy.listHouseNumbers, 30), 
      Possibility(CrabLegacy.listSubaddresses, 70),
      Possibility(Municipality.list, 30), 
      Possibility(Municipality.detail, 70),
      Possibility(Parcel.list, 30), 
      Possibility(Parcel.detail, 70),
      Possibility(Postinfo.list, 30), 
      Possibility(Postinfo.detail, 70),
      Possibility(StreetName.list, 30), 
      Possibility(StreetName.detail, 70)
    )
  )
}
