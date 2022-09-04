package io.layercraft.translator.types

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*

internal fun Double.toPositionInt(): Int = (this * 32.0).toInt()
internal fun Int.toPositionDouble(): Double = this / 32.0

fun Position(
    x: Double,
    y: Double,
    z: Double
) = Position(
    x.toPositionInt(),
    y.toPositionInt(),
    z.toPositionInt()
)


/**
 * Position
 *
 * @property x
 * @property y
 * @property z
 * @see <a href="https://wiki.vg/Protocol#Position">https://wiki.vg/Protocol#Position</a>
 */
@Serializable
data class Position(
    val x: Int,
    val y: Int,
    val z: Int
) {

    @Serializer(forClass = Position::class)
    companion object : KSerializer<Position> {
        override val descriptor: SerialDescriptor =
            buildClassSerialDescriptor("io.layercraft.translator.types.Position") {
                element<Long>("position")
            }

        @OptIn(ExperimentalSerializationApi::class)
        override fun deserialize(decoder: Decoder): Position =
            decoder.decodeStructure(descriptor) {
                var positionContent: Long? = null

                if (decodeSequentially()) { // sequential decoding protocol
                    positionContent = decodeLongElement(descriptor, 0)
                } else while (true) {
                    when (val index = decodeElementIndex(descriptor)) {
                        0 -> positionContent = decodeLongElement(descriptor, 0)
                        CompositeDecoder.DECODE_DONE -> break
                        else -> error("Unexpected index: $index")
                    }
                }

                requireNotNull(positionContent)

                longToPosition(positionContent)
            }


        override fun serialize(encoder: Encoder, value: Position) {
            encoder.encodeStructure(descriptor) {
                encodeLongElement(descriptor, 0, positionToLong(value))
            }
        }

        private fun positionToLong(position: Position): Long = with(position) {
            x.toLong() and 0x3FFFFFF shl 38 or (z.toLong() and 0x3FFFFFF shl 12) or (y.toLong() and 0xFFF)
        }

        private fun longToPosition(long: Long): Position = Position(
            (long shr 38).toInt(),
            (long and 0xFFF).toInt(),
            (long shl 26 shr 38).toInt()
        )

    }
}
