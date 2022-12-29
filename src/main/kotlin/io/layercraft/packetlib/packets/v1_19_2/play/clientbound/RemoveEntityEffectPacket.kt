package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x3c | play | clientbound
 *
 * @property entityId entityId
 * @property effectId effectId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x3c, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class RemoveEntityEffectPacket(
    val entityId: Int, // varint
    val effectId: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<RemoveEntityEffectPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): RemoveEntityEffectPacket {
            val entityId = input.readVarInt()
            val effectId = input.readVarInt()

            return RemoveEntityEffectPacket(entityId, effectId)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: RemoveEntityEffectPacket) {
            output.writeVarInt(value.entityId)
            output.writeVarInt(value.effectId)
        }
    }
}