package simulations.infrastructure

import io.gatling.core.check._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.commons.validation._

object CheckIfConditions {
  def hasStatus(statusCodes: Int*) : (Response, Session) => io.gatling.commons.validation.Validation[Boolean] = {
    (response: Response, _: Session) => statusCodes.toList.contains(response.status.code)
  }

  def doesNotHaveStatus(statusCodes: Int*) : (Response, Session) => io.gatling.commons.validation.Validation[Boolean] = {
    (response: Response, _: Session) => !statusCodes.toList.contains(response.status.code)
  }
}
