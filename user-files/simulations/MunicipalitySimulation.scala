package registry.municipality

import io.gatling.core.Predef._
import infrastructure._
import registries._

class StandardTrafficLoadForMunicipality extends RegistrySimulation {
  setUp("Municipality - standard load", Load.Standard, Municipality.possibleCalls)
}

class HighTrafficLoadForMunicipality extends RegistrySimulation {
  setUp("Municipality - high load", Load.High, Municipality.possibleCalls)
}

class PeakTrafficLoadForMunicipality extends RegistrySimulation {
  setUp("Municipality - peak load", Load.Peak, Municipality.possibleCalls)
}

class AnonymousValidTrafficLoadForMunicipality extends RegistrySimulation {
  setUp("Municipality - anonymous valid load", Load.AnonymousValid, Municipality.possibleCalls)
}

class AnonymousInvalidTrafficLoadForMunicipality extends RegistrySimulation {
  setUp("Municipality - anonymous invalid load", Load.AnonymousInvalid, Municipality.possibleCalls)
}

