package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x1e | play | serverbound
 *
 * @property entityId entityId
 * @property actionId actionId
 * @property jumpBoost jumpBoost
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(packetId = 0x1e, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class EntityActionPacket(
    val entityId: Int, // varint
    val actionId: Int, // varint
    val jumpBoost: Int, // varint
) : ServerBoundPacket {

    companion object : PacketSerializer<EntityActionPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): EntityActionPacket {
            val entityId = input.readVarInt()
            val actionId = input.readVarInt()
            val jumpBoost = input.readVarInt()

            return EntityActionPacket(entityId, actionId, jumpBoost)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: EntityActionPacket) {
            output.writeVarInt(value.entityId)
            output.writeVarInt(value.actionId)
            output.writeVarInt(value.jumpBoost)
        }
    }
}