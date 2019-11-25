package simulations.infrastructure

import io.gatling.core.structure.ChainBuilder
import scala.concurrent.duration._

case class Possibility(chain: ChainBuilder, weight: Double)