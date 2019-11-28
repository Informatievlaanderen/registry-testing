package registry.parcel

import io.gatling.core.Predef._
import infrastructure._
import registries._

class StandardTrafficLoadForParcel extends RegistrySimulation {
  setUp("Parcel - standard load", Load.Standard, Parcel.possibleCalls)
}

class HighTrafficLoadForParcel extends RegistrySimulation {
  setUp("Parcel - high load", Load.High, Parcel.possibleCalls)
}

class PeakTrafficLoadForParcel extends RegistrySimulation {
  setUp("Parcel - peak load", Load.Peak, Parcel.possibleCalls)
}

