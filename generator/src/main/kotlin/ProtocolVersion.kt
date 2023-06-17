enum class ProtocolVersion(val url: String, private val wikiVgVersionId: String) {
    // V1_20("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.20/protocol.json", "18067"),
    V1_19_3("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.19.3/protocol.json", "18067"),
    V1_19_2("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.19.2/protocol.json", "17873"),
    V1_19_1("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.19.1/protocol.json", "17873"),
    V1_19("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.19/protocol.json", "17753"),
    V1_18_2("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.18.1/protocol.json", "17499"),
    V1_18_1("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.18.1/protocol.json", "17341"),
    V1_18("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.18/protocol.json", "17341"),
    V1_17_1("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.17.1/protocol.json", "16918"),
    V1_17("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.17/protocol.json", "16866"),
    V1_16_5("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.16.5/protocol.json", "16681"),
    V1_16_4("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.16.4/protocol.json", "16317"),
    V1_16_3("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.16.3/protocol.json", "16091"),
    V1_16_2("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.16.2/protocol.json", "16001"),
    V1_16_1("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.16.1/protocol.json", "15895"),
    V1_16("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.16/protocol.json", "15878"),
    V1_15_2("https://raw.githubusercontent.com/Layercraft/minecraft-data/master/data/pc/1.15.2/protocol.json", "16067"),
    ;

    val wikiVgUrl = "https://wiki.vg/index.php?title=Protocol&oldid=$wikiVgVersionId"
    val packageVersion = name.lowercase()

    companion object {
        fun latest() = V1_19_3
    }
}