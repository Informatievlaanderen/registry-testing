package registry.building

import io.gatling.core.Predef._
import infrastructure._
import registries._

class StandardTrafficLoadForBuilding extends RegistrySimulation { 
  setUp("Building - standard load", Load.Standard, Building.possibleCalls)
}

class HighTrafficLoadForBuilding extends RegistrySimulation {
  setUp("Building - high load", Load.High, Building.possibleCalls)
}

class PeakTrafficLoadForBuilding extends RegistrySimulation {
  setUp("Building - peak load", Load.Peak, Building.possibleCalls)
}

