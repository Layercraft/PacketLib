package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Player Command | 0x1e | play | serverbound
 *
 * @param entityId entityId
 * @param actionId actionId
 * @param jumpBoost jumpBoost
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Player_Command">https://wiki.vg/Protocol#Player_Command</a>
 */

@MinecraftPacket(id = 0x1e, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class EntityActionPacket(
    val entityId: Int, // varint
    val actionId: Int, // varint
    val jumpBoost: Int, // varint
) : ServerBoundPacket {
    companion object : PacketSerializer<EntityActionPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): EntityActionPacket {
            val entityId = input.readVarInt()
            val actionId = input.readVarInt()
            val jumpBoost = input.readVarInt()

            return EntityActionPacket(entityId, actionId, jumpBoost)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: EntityActionPacket) {
            output.writeVarInt(value.entityId)
            output.writeVarInt(value.actionId)
            output.writeVarInt(value.jumpBoost)
        }
    }
}