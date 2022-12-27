package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.Position 

/**
 * Block Break Animation | 0x08 | play | clientbound
 *
 * @property entityId entityId
 * @property location location
 * @property destroyStage destroyStage
 * @see <a href="https://wiki.vg/Protocol#Block_Break_Animation">https://wiki.vg/Protocol#Block_Break_Animation</a>
 */

@MinecraftPacket(packetId = 0x08, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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
