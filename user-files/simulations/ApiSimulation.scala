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
      RegisterySetup(Address.possibleCalls, 10),
      // todo: add addressmatch
      RegisterySetup(Building.possibleCalls, 10),
      RegisterySetup(BuildingUnit.possibleCalls, 10),
      RegisterySetup(CrabLegacy.possibleCalls, 10),
      RegisterySetup(Municipality.possibleCalls, 10),
      RegisterySetup(Parcel.possibleCalls, 10),
      RegisterySetup(Postinfo.possibleCalls, 10),
      RegisterySetup(StreetName.possibleCalls, 10),
    )
  )

  private case class RegisterySetup(
    possibleCalls: List[Possibility], 
    weight: Double)
}