package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Remove Entity Effect | 0x31 | play | clientbound
 *
 * @property entityId entityId
 * @property effectId effectId
 * @see <a href="https://wiki.vg/Protocol#Remove_Entity_Effect">https://wiki.vg/Protocol#Remove_Entity_Effect</a>
 */

@MinecraftPacket(id = 0x31, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class RemoveEntityEffectPacket(
    val entityId: Int, // varint
    val effectId: Byte,
) : ClientBoundPacket {

    companion object : PacketSerializer<RemoveEntityEffectPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): RemoveEntityEffectPacket {
            val entityId = input.readVarInt()
            val effectId = input.readByte()

            return RemoveEntityEffectPacket(entityId, effectId)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: RemoveEntityEffectPacket) {
            output.writeVarInt(value.entityId)
            output.writeByte(value.effectId)
        }
    }
}
