package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * End Combat | 0x32 | play | clientbound
 *
 * @param duration duration
 * @param entityId entityId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#End_Combat">https://wiki.vg/Protocol#End_Combat</a>
 */

data class EndCombatEventPacket(
    val duration: Int, // varint
    val entityId: Int,
) : ClientBoundPacket {
    companion object : PacketSerializer<EndCombatEventPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): EndCombatEventPacket {
            val duration = input.readVarInt()
            val entityId = input.readInt()

            return EndCombatEventPacket(duration, entityId)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: EndCombatEventPacket) {
            output.writeVarInt(value.duration)
            output.writeInt(value.entityId)
        }
    }
}