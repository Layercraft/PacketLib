package io.layercraft.translator.types

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import java.util.UUID

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = UUID::class)
object UuidSerializer : KSerializer<UUID> {

    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("java.util.UUID:Binary") {
            element<Long>("msb")
            element<Long>("lsb")
        }

    override fun serialize(encoder: Encoder, value: UUID) {
        encoder.encodeStructure(descriptor) {
            encodeLongElement(descriptor, 0, value.mostSignificantBits)
            encodeLongElement(descriptor, 1, value.leastSignificantBits)
        }
    }

    override fun deserialize(decoder: Decoder): UUID =
        decoder.decodeStructure(descriptor) {
            var mostSignificationBits: Long? = null
            var leastSignificationBits: Long? = null

            if (decodeSequentially()) { // sequential decoding protocol
                mostSignificationBits = decodeLongElement(descriptor, 0)
                leastSignificationBits = decodeLongElement(descriptor, 1)
            } else while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> mostSignificationBits = decodeLongElement(descriptor, 0)
                    1 -> leastSignificationBits = decodeLongElement(descriptor, 1)
                    CompositeDecoder.DECODE_DONE -> break
                    else -> error("Unexpected index: $index")
                }
            }

            requireNotNull(mostSignificationBits)
            requireNotNull(leastSignificationBits)

            UUID(mostSignificationBits, leastSignificationBits)
        }
}

@Serializer(forClass = UUID::class)
object UuidStringSerializer : KSerializer<UUID> {
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("java.util.UUID:String") {
            element<String>("uuid")
        }

    override fun serialize(encoder: Encoder, value: UUID) {
        encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, 0, value.toString())
        }
    }

    override fun deserialize(decoder: Decoder): UUID =
        decoder.decodeStructure(descriptor) {
            var uuid: String? = null

            if (decodeSequentially()) { // sequential decoding protocol
                uuid = decodeStringElement(UuidSerializer.descriptor, 0)
            } else while (true) {
                when (val index = decodeElementIndex(UuidSerializer.descriptor)) {
                    0 -> uuid = decodeStringElement(UuidSerializer.descriptor, 0)
                    CompositeDecoder.DECODE_DONE -> break
                    else -> error("Unexpected index: $index")
                }
            }

            requireNotNull(uuid)

            UUID.fromString(uuid)
        }
}
