package infrastructure

import io.gatling.core.Predef._

object Feeders {
  val addressIds = new SharedFeeder("all-address-ids.csv.zip")
  val addressMatchParameters = new SharedFeeder("addressmatch.csv.zip")
  val buildingIds = new SharedFeeder("all-building-ids.csv.zip")
  val buildingUnitIds = new SharedFeeder("all-buildingunit-ids.csv.zip")
  val parcelIds = new SharedFeeder("all-parcel-ids.csv.zip")
  val streetnameIds = new SharedFeeder("all-streetname-ids.csv.zip")
  val postalInfo = new SharedFeeder("postal-info.csv.zip")

  object Crab {
    val housenumberIds = new SharedFeeder("all-crab-housenumber-ids.csv.zip")
    val subaddressIds = new SharedFeeder("all-crab-subaddress-ids.csv.zip")
  }
}

class SharedFeeder(zip: String) {
    private var feeder: io.gatling.core.feeder.FeederBuilder = null 

    def getFeeder = {      
      if (feeder == null) {
        val resource = Option(System.getProperty("feeder_prefix"))
          .filterNot(_.isEmpty)
          .map(prefix => (prefix.trim + "/" + zip).replace("//", "/"))
          .getOrElse(zip)

        try {
          feeder = csv(resource).unzip.batch.random
        } catch {
          case e : Throwable =>
            feeder = csv(resource.replace(".zip", "")).batch.random
        }
      } 
      
      feeder    
    }
}

object SharedFeeder {
  implicit def cast(factory: SharedFeeder) : io.gatling.core.feeder.FeederBuilder = factory.getFeeder  
}
