package infrastructure

import io.gatling.core.structure.ChainBuilder
import scala.concurrent.duration._

case class Possibility(
  createChain: MaximumResponseTimes => ChainBuilder, 
  weight: Double)

case class PossibilityList(possibilities: List[Possibility])
object PossibilityList {
  implicit def fromList(list: List[Possibility]) = PossibilityList(list)
}

case class RegistryPossibilities(
  possibleCalls: List[Possibility], 
  weight: Double)

case class RegistryPossibilitiesList(registries: List[RegistryPossibilities])
object RegistryPossibilitiesList {
  implicit def fromList(list: List[RegistryPossibilities]) = RegistryPossibilitiesList(list)
}

object Possibilities {
  def balance(load: TrafficLoadConfiguration, list: PossibilityList) : List[(Double, ChainBuilder)] = {
    
    var (totalWeight, possibilities) = list
      .possibilities
      .foldLeft[(Double, List[Possibility])](0, Nil){ (result, chain) => (result._1 + chain.weight, result._2 :+ chain) }

    val balancedPossibilities = possibilities.map(
    possibility => {
      val weight = if (totalWeight == 0) possibility.weight else possibility.weight / totalWeight * 100  
      (weight, possibility.createChain(load.responseTimes))
    })

    balancedPossibilities
  }

  def balance(load: TrafficLoadConfiguration, list: RegistryPossibilitiesList) : List[(Double, ChainBuilder)] = {

    val possibilities = list
      .registries
      .flatMap(registry => {
        registry
          .possibleCalls
          .map(possibility => Possibility(possibility.createChain, registry.weight * possibility.weight))
        }
      )

    balance(load, possibilities)
  }
}
