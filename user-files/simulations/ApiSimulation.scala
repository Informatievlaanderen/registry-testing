package api

import io.gatling.core.Predef._
import io.gatling.core.structure._

import registries._
import infrastructure._

class StandardTrafficLoad extends ApiRegistrySimulation("MixedSimulation - standard load", Load.Standard)
class HighTrafficLoad extends ApiRegistrySimulation("MixedSimulation - high load", Load.High)  
class PeakTrafficLoad extends ApiRegistrySimulation("MixedSimulation - peak load", Load.Peak)

abstract class ApiRegistrySimulation(name: String, load: TrafficLoadConfiguration) extends RegistrySimulation {
  
  private def recalculateWeights(registerySetups: RegisterySetup*) : List[Possibility] = {
    registerySetups
      .toList
      .flatMap(registry => {
          registry
            .possibleCalls
            .map(p => Possibility(p.createChain, registry.weight * p.weight))
        }
      )
  }

  setUp(
    name,
    load,
    recalculateWeights(
      RegisterySetup(Address.possibleCalls, 25),
      RegisterySetup(AddressMatch.possibleCalls, 8),
      RegisterySetup(Building.possibleCalls, 15),
      RegisterySetup(BuildingUnit.possibleCalls, 20),
      RegisterySetup(CrabLegacy.possibleCalls, 2.5),
      RegisterySetup(Municipality.possibleCalls, 1),
      RegisterySetup(Parcel.possibleCalls, 10),
      RegisterySetup(Postinfo.possibleCalls, 1),
      RegisterySetup(StreetName.possibleCalls, 5),
    )
  )

  private case class RegisterySetup(
    possibleCalls: List[Possibility], 
    weight: Double)
}