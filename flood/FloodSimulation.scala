
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import infrastructure._
import registries._

class FloodSimulation extends RegistrySimulation {
  setUp("Municipality - high load", Load.High, Municipality.possibleCalls)
}