package registries

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Bosa {
  // var feeder = Feeders.addressIds

  // https://docs.basisregisters.vlaanderen/docs/api-documentation.html#operation/SearchBestAddAddress
  // val findAddress =
  //   feed(feeder)
  //   .exec(
  //     http(session => "Zoek een adres op")
  //       .post("/bosa/adressen") // add post-body
  //       .check(status.isValidForList(todo))
  //       .check(jsonPath("$..identificator.objectId").is("${addressId}"))
  //   )

  // https://docs.basisregisters.vlaanderen/docs/api-documentation.html#operation/SearchBestAddAddressRepresentation
  // val findAddressRepresentation =
  //   feed(feeder)
  //   .exec(
  //     http(session => "Zoek een adres voorstelling op")
  //       .post("/bosa/adresvoorstellingen") // add post-body
  //       .check(status.isValidForList(todo))
  //       .check(jsonPath("$..identificator.objectId").is("${addressId}"))
  //   )


  // https://docs.basisregisters.vlaanderen/docs/api-documentation.html#operation/SearchBestAddMunicipality
  // val findMunicipality =
  //   feed(feeder)
  //   .exec(
  //     http(session => "Zoek een gemeente op")
  //       .post("/bosa/gemeenten") // add post-body
  //       .check(status.isValidForList(todo))
  //       .check(jsonPath("$..identificator.objectId").is("${addressId}"))
  //   )
}
