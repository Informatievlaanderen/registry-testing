package registry.streetname

import io.gatling.core.Predef._
import infrastructure._
import registries._

class StandardTrafficLoadForStreetName extends RegistrySimulation {
  setUp("StreetName - standard load", Load.Standard, StreetName.possibleCalls)
}

class HighTrafficLoadForStreetName extends RegistrySimulation {
  setUp("StreetName - high load", Load.High, StreetName.possibleCalls)
}

class PeakTrafficLoadForStreetName extends RegistrySimulation {
  setUp("StreetName - peak load", Load.Peak, StreetName.possibleCalls)
}

