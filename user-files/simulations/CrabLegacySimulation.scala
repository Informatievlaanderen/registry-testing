package registry.crablegacy

import io.gatling.core.Predef._
import infrastructure._
import registries._

class StandardTrafficLoadForCrabLegcay extends RegistrySimulation {
  setUp("CRAB - standard load", Load.Standard, CrabLegacy.possibleCalls)
}

class HighTrafficLoadForCrabLegcay extends RegistrySimulation {
  setUp("CRAB - high load", Load.High, CrabLegacy.possibleCalls)
}

class PeakTrafficLoadForCrabLegcay extends RegistrySimulation {
  setUp("CRAB - peak load", Load.Peak, CrabLegacy.possibleCalls)
}

