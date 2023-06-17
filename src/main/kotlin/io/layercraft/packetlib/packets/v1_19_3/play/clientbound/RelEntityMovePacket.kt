package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Update Entity Position | 0x27 | play | clientbound
 *
 * @param entityId entityId
 * @param dX dX
 * @param dY dY
 * @param dZ dZ
 * @param onGround onGround
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Update_Entity_Position">https://wiki.vg/Protocol#Update_Entity_Position</a>
 */

@MinecraftPacket(id = 0x27, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class RelEntityMovePacket(
    val entityId: Int, // varint
    val dX: Short,
    val dY: Short,
    val dZ: Short,
    val onGround: Boolean,
) : ClientBoundPacket {
    companion object : PacketSerializer<RelEntityMovePacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): RelEntityMovePacket {
            val entityId = input.readVarInt()
            val dX = input.readShort()
            val dY = input.readShort()
            val dZ = input.readShort()
            val onGround = input.readBoolean()

            return RelEntityMovePacket(entityId, dX, dY, dZ, onGround)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: RelEntityMovePacket) {
            output.writeVarInt(value.entityId)
            output.writeShort(value.dX)
            output.writeShort(value.dY)
            output.writeShort(value.dZ)
            output.writeBoolean(value.onGround)
        }
    }
}