package simulations.infrastructure

class RegistryName(name: String) {
    val detail = s"$name/detail"
    val list = s"$name/list"

    override def toString() : String = name
    implicit def asString(name: RegistryName) : String = toString
}
