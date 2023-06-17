import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

class PacketsGenerator(
    private val protocolVersion: ProtocolVersion,
) {

    private val wikiVgSerializer = WikiVgSerializer(protocolVersion)

    private val packetData: JsonObject = runBlocking { Downloader.getProtocolSpec(protocolVersion) }

    fun generate() {

    }

}

class PacketGenerator(
    private val packetJsonObject: JsonObject,
    private val wikiVgData: WikiVgData,
) {
    private val id = packetJsonObject["id"]!!.jsonPrimitive.content
    private val name = packetJsonObject["name"]!!.jsonPrimitive.content
    private val direction = packetJsonObject["direction"]!!.jsonPrimitive.content

}