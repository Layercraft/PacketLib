fun main(args: Array<String>) {
    val versionArg = args.getOrNull(0) ?: error("Missing version argument")
    val version = "V${versionArg.uppercase().replace(".", "_")}"
    val protocolVersion = ProtocolVersion.values().find { it.name == version } ?: error("Unknown protocol version")


    val generator = PacketsGenerator(protocolVersion)
    generator.generate()
}