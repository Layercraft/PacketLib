package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Remove Entity Effect | 0x3b | play | clientbound
 *
 * @param entityId entityId
 * @param effectId effectId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Remove_Entity_Effect">https://wiki.vg/Protocol#Remove_Entity_Effect</a>
 */

data class RemoveEntityEffectPacket(
    val entityId: Int, // varint
    val effectId: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<RemoveEntityEffectPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): RemoveEntityEffectPacket {
            val entityId = input.readVarInt()
            val effectId = input.readVarInt()

            return RemoveEntityEffectPacket(entityId, effectId)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: RemoveEntityEffectPacket) {
            output.writeVarInt(value.entityId)
            output.writeVarInt(value.effectId)
        }
    }
}