package registry.addressmatch

import io.gatling.core.Predef._
import infrastructure._
import registries._

class StandardTrafficLoadForAddressMatch extends RegistrySimulation { 
  setUp("Address - standard load", Load.Standard, AddressMatch.possibleCalls)
}

class HighTrafficLoadForAddressMatch extends RegistrySimulation { 
  setUp("Address - high load", Load.High, AddressMatch.possibleCalls) 
} 

class PeakTrafficLoadForAddressMatch extends RegistrySimulation { 
  setUp("Address - peak load", Load.Peak, AddressMatch.possibleCalls)
} 
