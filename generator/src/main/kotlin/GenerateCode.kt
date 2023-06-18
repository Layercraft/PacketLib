fun main() {
    val protocolVersion = ProtocolVersion.V1_19_3
    val generator = PacketsGenerator(protocolVersion)
    generator.generate()
}