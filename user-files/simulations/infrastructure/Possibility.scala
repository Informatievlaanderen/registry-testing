package infrastructure

import io.gatling.core.structure.ChainBuilder
import scala.concurrent.duration._

case class Possibility(
    createChain: MaximumResponseTimes => ChainBuilder, 
    weight: Double)
