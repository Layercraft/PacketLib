package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.Position

/**
 * Set Block Destroy Stage | 0x06 | play | clientbound
 *
 * @property entityId entityId
 * @property location location
 * @property destroyStage destroyStage
 * @see <a href="https://wiki.vg/Protocol#Set_Block_Destroy_Stage">https://wiki.vg/Protocol#Set_Block_Destroy_Stage</a>
 */

@MinecraftPacket(packetId = 0x06, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class BlockBreakAnimationPacket(
    val entityId: Int, // varint
    val location: Position,
    val destroyStage: Byte,
) : ClientBoundPacket {

    companion object : PacketSerializer<BlockBreakAnimationPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): BlockBreakAnimationPacket {
            val entityId = input.readVarInt()
            val location = input.readPosition()
            val destroyStage = input.readByte()

            return BlockBreakAnimationPacket(entityId, location, destroyStage)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: BlockBreakAnimationPacket) {
            output.writeVarInt(value.entityId)
            output.writePosition(value.location)
            output.writeByte(value.destroyStage)
        }
    }
}