package io.layercraft.packetlib.generator

import io.layercraft.packetlib.generator.codegen.Direction
import io.layercraft.packetlib.generator.codegen.PacketDefinition
import io.layercraft.packetlib.generator.protodef.TypeResolver
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

/** The known protocol states, in wire order. */
val STATES = listOf("handshaking", "status", "login", "configuration", "play")

data class ExtractedState(
    val state: String,
    val direction: Direction,
    val resolver: TypeResolver,
    val packets: List<PacketDefinition>,
)

/**
 * Pulls the packet list out of a protocol.json namespace: the synthetic `packet` container maps
 * packet ids to names (mapper) and names to `packet_*` types (switch).
 */
object ProtocolExtractor {

    fun extract(protocol: JsonObject): List<ExtractedState> {
        val globalTypes = protocol["types"]!!.jsonObject

        return STATES.filter { it in protocol }.flatMap { state ->
            Direction.entries.map { direction ->
                val types = protocol[state]!!.jsonObject[direction.key]!!.jsonObject["types"]!!.jsonObject
                ExtractedState(
                    state = state,
                    direction = direction,
                    resolver = TypeResolver(globalTypes, types),
                    packets = extractPackets(types, globalTypes, state, direction),
                )
            }
        }
    }

    private fun extractPackets(
        types: JsonObject,
        globalTypes: JsonObject,
        state: String,
        direction: Direction,
    ): List<PacketDefinition> {
        val packetFields = types["packet"]!!.jsonArray[1].jsonArray

        var idToName: Map<String, String>? = null
        var nameToType: Map<String, String>? = null

        for (field in packetFields) {
            val obj = field.jsonObject
            when (obj["name"]?.jsonPrimitive?.content) {
                "name" -> idToName = obj["type"]!!.jsonArray[1].jsonObject["mappings"]!!.jsonObject
                    .entries.associate { (id, name) -> id to name.jsonPrimitive.content }
                "params" -> nameToType = obj["type"]!!.jsonArray[1].jsonObject["fields"]!!.jsonObject
                    .entries.associate { (name, type) -> name to type.jsonPrimitive.content }
            }
        }

        checkNotNull(idToName) { "No packet id mappings for $state/${direction.key}" }

        return idToName.map { (idHex, protoName) ->
            val typeName = nameToType?.get(protoName) ?: "packet_$protoName"
            PacketDefinition(
                id = idHex.removePrefix("0x").toInt(16),
                protoName = protoName,
                className = pascalCase(protoName) + "Packet",
                state = state,
                direction = direction,
                fields = types[typeName] ?: globalTypes[typeName],
            )
        }
    }
}
