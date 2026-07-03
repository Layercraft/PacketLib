package io.layercraft.packetlib.generator.protodef

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

/**
 * Resolves [TypeReference]s against the protocol namespace: the local `types` object of a
 * state/direction first, then the top-level `types` object.
 */
class TypeResolver(
    private val globalTypes: JsonObject,
    private val localTypes: JsonObject,
) {

    private val cache = mutableMapOf<String, ProtoType>()

    fun resolve(reference: TypeReference): ProtoType = cache.getOrPut(reference.name) {
        val definition: JsonElement = localTypes[reference.name]
            ?: globalTypes[reference.name]
            ?: throw UnsupportedProtoException("Unknown type '${reference.name}'")

        if (definition is JsonPrimitive && definition.content == "native") {
            // Declared native but not part of the runtime-supported leaf set.
            throw UnsupportedProtoException("native type '${reference.name}'")
        }

        ProtoTypeParser.parse(definition)
    }

    /** Resolves references (including alias chains) until a structural type is reached. */
    fun deepResolve(type: ProtoType, depth: Int = 0): ProtoType {
        if (depth > 16) throw UnsupportedProtoException("type alias cycle")
        return when (type) {
            is TypeReference -> deepResolve(resolve(type), depth + 1)
            else -> type
        }
    }
}
