
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.TypeSpec
import fields.FieldsHelper
import fields.GenerationContext
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.*

class PacketsGenerator(
    private val protocolVersion: ProtocolVersion,
) {

    private val wikiVgSerializer = WikiVgSerializer(protocolVersion)

    private val packetData: JsonObject = runBlocking { Downloader.getProtocolSpec(protocolVersion) }

    fun generate() {
        val packets = getPackets(packetData)
        val types = getTypes(packetData).map { it.name }
        val registeredTypes = FieldsHelper.register().map { it.key }
        val missingTypes = types.filter { !registeredTypes.contains(it) }

        println("Found ${packets.size} packets and ${types.size} types")
        println("Missing types: $missingTypes")
        if (missingTypes.isNotEmpty()) {
            error("Missing types")
        }

        for (packet in packets) {
            val packagePath = "io.layercraft.packetlib.packets.${protocolVersion.packageVersion}.${packet.state.name.lowercase()}.${packet.direction.name.replace("_", "").lowercase()}"

            val file = FileSpec.builder(packagePath, packet.javaClassName)
            val clazz = TypeSpec
                .classBuilder(packet.javaClassName)
                .addModifiers(KModifier.DATA)

            val ctx = GenerationContext(file, clazz)

            FieldsHelper.generateClass(ctx, packet)

            file.addType(clazz.build())
            file.build().writeTo(System.out)
        }
    }

    private fun getPackets(jsonObject: JsonObject): List<PacketData> {
        val keysList = listOf("handshaking", "status", "login", "play")

        return keysList.flatMap { key ->
            val data = key to jsonObject[key]!!.jsonObject
            transformPackets(data.first, data.second)
        }
    }

    private fun getTypes(jsonObject: JsonObject): List<PacketType> {
        return jsonObject["types"]!!.jsonObject.map {
            PacketType(name = it.key, it.value)
        }
    }

    private fun transformPackets(state: String, jsonObject: JsonObject): List<PacketData> {
        val toClient = jsonObject["toClient"]!!.jsonObject["types"]!!.jsonObject
        val toServer = jsonObject["toServer"]!!.jsonObject["types"]!!.jsonObject

        return iterateThrowPackets(state, "toClient", toClient) + iterateThrowPackets(state, "toServer", toServer)
    }

    private fun iterateThrowPackets(stateStr: String, directionStr: String, jsonObject: JsonObject): List<PacketData> {
        val packetData = getPacketInfoLayer(jsonObject["packet"]!!.jsonArray)
        val direction = PacketGeneratorDirection.fromString(directionStr)
        val state = PacketGeneratorState.fromString(stateStr)

        val packets = jsonObject.filter { it.key != "packet" }.map { entry ->
            val name = entry.key
            val packet = entry.value.jsonArray[1].jsonArray
            val packetInfoLayer = packetData.first { it.className == name }

            val id = packetInfoLayer.id.replaceFirst("0x", "").toInt(16)

            val wikiVgData = wikiVgSerializer.get(packetInfoLayer.id, state, direction)

            PacketData(
                id = id,
                direction = direction,
                state = state,
                fields = packet,
                wikiVgData = wikiVgData,
                name = packetInfoLayer.name,
                className = packetInfoLayer.className,
            )
        }

        return packets
    }

    private fun getPacketInfoLayer(packetData: JsonArray): List<PacketInfoLayer> {
        val second = packetData[1].jsonArray
        val ids = second[0].jsonObject["type"]!!.jsonArray[1].jsonObject["mappings"]!!.jsonObject
        val names = second[1].jsonObject["type"]!!.jsonArray[1].jsonObject["fields"]!!.jsonObject

        return ids.map { (id, value) ->
            val name = value.jsonPrimitive.content
            val className = names[name]!!.jsonPrimitive.content

            PacketInfoLayer(id, name, className)
        }
    }
}

data class PacketInfoLayer(
    val id: String,
    val name: String,
    val className: String, // same as name but with packet_ prefix
)

data class PacketData(
    val id: Int,
    val idString: String = "0x${id.toString(16)}",
    val direction: PacketGeneratorDirection,
    val name: String,
    val className: String, // same as name but with packet_ prefix
    val javaClassName: String = name.replace("_", "").replaceFirstChar { it.titlecase() }.replace(" ", "") + "Packet",
    val state: PacketGeneratorState,
    val fields: JsonArray, // Allways inside a ["container", [$fields] ]
    val wikiVgData: WikiVgData,
)

enum class PacketGeneratorDirection(
    val direction: String,
) {
    CLIENT_BOUND("toClient"),
    SERVER_BOUND("toServer"),
    ;

    companion object {
        fun fromString(direction: String): PacketGeneratorDirection {
            return values().first { it.direction == direction }
        }
    }
}

enum class PacketGeneratorState(
    val state: String,
) {
    HANDSHAKING("handshaking"), STATUS("status"), LOGIN("login"), PLAY("play");

    companion object {
        fun fromString(state: String): PacketGeneratorState {
            return values().first { it.state == state }
        }
    }
}

data class PacketType(
    val name: String,
    val data: JsonElement,
)