package io.layercraft.packetlib.generator.protodef

import kotlinx.serialization.json.*

/**
 * Thrown while generating a packet whenever a ProtoDef construct cannot be expressed as typed
 * Kotlin. The packet is then emitted as a raw payload fallback.
 */
class UnsupportedProtoException(val reason: String) : RuntimeException(reason)

/** Parsed ProtoDef type tree. */
sealed interface ProtoType

/** A leaf type handled directly by the runtime (varint, u8, string, position, ...). */
data class NativeType(val name: String) : ProtoType

/** A reference to a type declared in the protocol namespace, resolved lazily. */
data class TypeReference(val name: String) : ProtoType

data class ProtoField(val name: String?, val anon: Boolean, val type: ProtoType)

data class ContainerType(val fields: List<ProtoField>) : ProtoType

data class ArrayType(val countType: ProtoType?, val count: CountSpec?, val element: ProtoType) : ProtoType

data class OptionType(val inner: ProtoType) : ProtoType

data class BufferType(val countType: ProtoType?, val count: CountSpec?) : ProtoType

data class PStringType(val countType: ProtoType?) : ProtoType

data class SwitchType(val compareTo: String, val cases: Map<String, ProtoType>, val default: ProtoType?) : ProtoType

data class BitfieldEntry(val name: String, val size: Int, val signed: Boolean)

data class BitfieldType(val entries: List<BitfieldEntry>) : ProtoType

data class BitflagsType(val underlying: ProtoType, val flags: List<String>) : ProtoType

data class MapperType(val underlying: ProtoType, val mappings: Map<String, String>) : ProtoType

/** `void` — carries no data. Only meaningful inside switch branches. */
object VoidType : ProtoType

sealed interface CountSpec {
    data class Fixed(val count: Int) : CountSpec
    data class FieldReference(val path: String) : CountSpec
}

/** Leaf types the generated code reads/writes directly through the runtime interfaces. */
val NATIVE_LEAF_TYPES: Set<String> = setOf(
    "varint", "optvarint", "varlong",
    "i8", "u8", "i16", "u16", "i32", "u32", "i64", "u64",
    "f32", "f64", "bool",
    "UUID", "string", "restBuffer",
    "nbt", "optionalNbt", "anonymousNbt", "anonOptionalNbt",
    "position", "chunkBlockEntity",
)

object ProtoTypeParser {

    fun parse(json: JsonElement): ProtoType = when (json) {
        is JsonPrimitive -> parseName(json.content)
        is JsonArray -> parseComplex(json)
        else -> throw UnsupportedProtoException("Unexpected type json: $json")
    }

    private fun parseName(name: String): ProtoType = when {
        name == "void" -> VoidType
        name in NATIVE_LEAF_TYPES -> NativeType(name)
        else -> TypeReference(name)
    }

    private fun parseComplex(json: JsonArray): ProtoType {
        val kind = (json.getOrNull(0) as? JsonPrimitive)?.content
            ?: throw UnsupportedProtoException("Type array without name: $json")
        val params = json.getOrNull(1)

        return when (kind) {
            "container" -> parseContainer(params)
            "array" -> parseArray(params)
            "option" -> OptionType(parse(params ?: throw UnsupportedProtoException("option without type")))
            "buffer" -> parseBuffer(params)
            "pstring" -> PStringType(countTypeOf(params))
            "switch" -> parseSwitch(params)
            "bitfield" -> parseBitfield(params)
            "bitflags" -> parseBitflags(params)
            "mapper" -> parseMapper(params)
            else -> throw UnsupportedProtoException("ProtoDef construct '$kind'")
        }
    }

    private fun parseContainer(params: JsonElement?): ContainerType {
        val fields = (params as? JsonArray ?: throw UnsupportedProtoException("container without fields")).map { field ->
            val obj = field.jsonObject
            val anon = obj["anon"]?.jsonPrimitive?.booleanOrNull == true
            val name = obj["name"]?.jsonPrimitive?.content
            if (!anon && name == null) throw UnsupportedProtoException("container field without name")
            ProtoField(name, anon, parse(obj["type"] ?: throw UnsupportedProtoException("container field without type")))
        }
        return ContainerType(fields)
    }

    private fun parseArray(params: JsonElement?): ArrayType {
        val obj = params as? JsonObject ?: throw UnsupportedProtoException("array without params")
        val element = parse(obj["type"] ?: throw UnsupportedProtoException("array without element type"))
        return ArrayType(countTypeOf(obj), countOf(obj), element)
    }

    private fun parseBuffer(params: JsonElement?): ProtoType {
        val obj = params as? JsonObject ?: throw UnsupportedProtoException("buffer without params")
        if (obj["rest"]?.jsonPrimitive?.booleanOrNull == true) return NativeType("restBuffer")
        return BufferType(countTypeOf(obj), countOf(obj))
    }

    private fun countTypeOf(params: JsonElement?): ProtoType? {
        val countType = (params as? JsonObject)?.get("countType") ?: return null
        return parse(countType)
    }

    private fun countOf(obj: JsonObject): CountSpec? = when (val count = obj["count"]) {
        null -> null
        is JsonPrimitive -> when {
            count.intOrNull != null -> CountSpec.Fixed(count.int)
            count.isString -> CountSpec.FieldReference(count.content)
            else -> throw UnsupportedProtoException("Unsupported count: $count")
        }
        else -> throw UnsupportedProtoException("Unsupported count: $count")
    }

    private fun parseSwitch(params: JsonElement?): SwitchType {
        val obj = params as? JsonObject ?: throw UnsupportedProtoException("switch without params")
        val compareTo = obj["compareTo"]?.jsonPrimitive?.content
            ?: throw UnsupportedProtoException("switch without compareTo")
        val cases = (obj["fields"] as? JsonObject).orEmpty().mapValues { parse(it.value) }
        val default = obj["default"]?.let { parse(it) }
        return SwitchType(compareTo, cases, default)
    }

    private fun JsonObject?.orEmpty(): Map<String, JsonElement> = this ?: emptyMap()

    private fun parseBitfield(params: JsonElement?): BitfieldType {
        val entries = (params as? JsonArray ?: throw UnsupportedProtoException("bitfield without entries")).map {
            val obj = it.jsonObject
            BitfieldEntry(
                name = obj["name"]!!.jsonPrimitive.content,
                size = obj["size"]!!.jsonPrimitive.int,
                signed = obj["signed"]?.jsonPrimitive?.booleanOrNull == true,
            )
        }
        return BitfieldType(entries)
    }

    private fun parseBitflags(params: JsonElement?): BitflagsType {
        val obj = params as? JsonObject ?: throw UnsupportedProtoException("bitflags without params")
        val flags = obj["flags"] as? JsonArray ?: throw UnsupportedProtoException("bitflags without flags list")
        return BitflagsType(
            underlying = parse(obj["type"] ?: throw UnsupportedProtoException("bitflags without type")),
            flags = flags.map { it.jsonPrimitive.content },
        )
    }

    private fun parseMapper(params: JsonElement?): MapperType {
        val obj = params as? JsonObject ?: throw UnsupportedProtoException("mapper without params")
        val mappings = obj["mappings"]?.jsonObject ?: throw UnsupportedProtoException("mapper without mappings")
        return MapperType(
            underlying = parse(obj["type"] ?: throw UnsupportedProtoException("mapper without type")),
            mappings = mappings.entries.associate { (key, value) -> key to value.jsonPrimitive.content },
        )
    }
}
