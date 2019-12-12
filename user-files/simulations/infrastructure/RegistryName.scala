package infrastructure

case class RegistryName(name: String) {
    val detail = s"$name/detail"
    val list = s"$name/list"
    val filteredList = s"$name/list_filtered"
}

