package registries

import infrastructure._

object Api {
    val possibleRegistries = List(
        RegistryPossibilities(Address.possibleCalls, 25),
        RegistryPossibilities(AddressMatch.possibleCalls, 8),
        RegistryPossibilities(Building.possibleCalls, 15),
        RegistryPossibilities(BuildingUnit.possibleCalls, 20),
        RegistryPossibilities(CrabLegacy.possibleCalls, 2.5),
        RegistryPossibilities(Municipality.possibleCalls, 1),
        RegistryPossibilities(Parcel.possibleCalls, 10),
        RegistryPossibilities(Postinfo.possibleCalls, 1),
        RegistryPossibilities(StreetName.possibleCalls, 5),
    )
}