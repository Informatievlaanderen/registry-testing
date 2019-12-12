package registry.address

import io.gatling.core.Predef._
import infrastructure._
import registries._

class StandardTrafficLoadForAddress extends RegistrySimulation { 
  setUp("Address - standard load", Load.Standard, Address.possibleCalls)
}

class HighTrafficLoadForAddress extends RegistrySimulation { 
  setUp("Address - high load", Load.High, Address.possibleCalls) 
} 

class PeakTrafficLoadForAddress extends RegistrySimulation { 
  setUp("Address - peak load", Load.Peak, Address.possibleCalls)
} 
