package registry.buildingunit

import io.gatling.core.Predef._
import infrastructure._
import registries._

class StandardTrafficLoadForBuildingUnit extends RegistrySimulation {
  setUp("BuildingUnit - standard load", Load.Standard, BuildingUnit.possibleCalls)
}

class HighTrafficLoadForBuildingUnit extends RegistrySimulation {
  setUp("BuildingUnit - high load", Load.High, BuildingUnit.possibleCalls)
}

class PeakTrafficLoadForBuildingUnit extends RegistrySimulation {
  setUp("BuildingUnit - peak load", Load.Peak, BuildingUnit.possibleCalls)
}

