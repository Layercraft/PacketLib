package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Update Attributes | 0x68 | play | clientbound
 *
 * @property entityId entityId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Update_Attributes">https://wiki.vg/Protocol#Update_Attributes</a>
 */

@MinecraftPacket(id = 0x68, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class EntityUpdateAttributesPacket(
    val entityId: Int, // varint
) : ClientBoundPacket {

    companion object : PacketSerializer<EntityUpdateAttributesPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): EntityUpdateAttributesPacket {
            val entityId = input.readVarInt()

            return EntityUpdateAttributesPacket(entityId)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: EntityUpdateAttributesPacket) {
            output.writeVarInt(value.entityId)
        }
    }
}