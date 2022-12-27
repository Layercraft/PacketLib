package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.Position 

/**
 * Effect | 0x21 | play | clientbound
 *
 * @property effectId effectId
 * @property location location
 * @property data data
 * @property global global
 * @see <a href="https://wiki.vg/Protocol#Effect">https://wiki.vg/Protocol#Effect</a>
 */

@MinecraftPacket(packetId = 0x21, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class WorldEventPacket(
    val effectId: Int,
    val location: Position,
    val data: Int,
    val global: Boolean,
) : ClientBoundPacket {

    companion object : PacketSerializer<WorldEventPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): WorldEventPacket {
            val effectId = input.readInt()
            val location = input.readPosition()
            val data = input.readInt()
            val global = input.readBoolean()

            return WorldEventPacket(effectId, location, data, global)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: WorldEventPacket) {
            output.writeInt(value.effectId)
            output.writePosition(value.location)
            output.writeInt(value.data)
            output.writeBoolean(value.global)
        }
    }
}
