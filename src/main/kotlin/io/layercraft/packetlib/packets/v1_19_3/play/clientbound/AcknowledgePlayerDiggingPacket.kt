package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Acknowledge Block Change | 0x05 | play | clientbound
 *
 * @param sequenceId sequenceId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Acknowledge_Block_Change">https://wiki.vg/Protocol#Acknowledge_Block_Change</a>
 */

data class AcknowledgePlayerDiggingPacket(
    val sequenceId: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<AcknowledgePlayerDiggingPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): AcknowledgePlayerDiggingPacket {
            val sequenceId = input.readVarInt()

            return AcknowledgePlayerDiggingPacket(sequenceId)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: AcknowledgePlayerDiggingPacket) {
            output.writeVarInt(value.sequenceId)
        }
    }
}