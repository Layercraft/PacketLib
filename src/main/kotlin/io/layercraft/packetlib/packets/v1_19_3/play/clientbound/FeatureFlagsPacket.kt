package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Feature Flags | 0x67 | play | clientbound
 *
 * @param features features
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Feature_Flags">https://wiki.vg/Protocol#Feature_Flags</a>
 */

data class FeatureFlagsPacket(
    val features: List<String>, // varint array
) : ClientBoundPacket {
    companion object : PacketSerializer<FeatureFlagsPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): FeatureFlagsPacket {
            val features = input.readVarIntArray { arrayInput -> arrayInput.readString() }

            return FeatureFlagsPacket(features)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: FeatureFlagsPacket) {
            output.writeVarIntArray(value.features) { arrayValue, arrayOutput -> arrayOutput.writeString(arrayValue) }
        }
    }
}