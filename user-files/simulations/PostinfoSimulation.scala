package registry.postinfo

import io.gatling.core.Predef._
import infrastructure._
import registries._

class StandardTrafficLoadForPostinfo extends RegistrySimulation {
  setUp("Postinfo - standard load", Load.Standard, Postinfo.possibleCalls)
}

class HighTrafficLoadForPostinfo extends RegistrySimulation {
  setUp("Postinfo - high load", Load.High, Postinfo.possibleCalls)
}

class PeakTrafficLoadForPostinfo extends RegistrySimulation {
  setUp("Postinfo - peak load", Load.Peak, Postinfo.possibleCalls)
}

