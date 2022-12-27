package io.layercraft.packetlib.packets.v1_19.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x1f | play | serverbound
 *
 * @property id id
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x1f, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class PongPacket(
    val id: Int,
) : ServerBoundPacket {

    companion object : PacketSerializer<PongPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): PongPacket {
            val id = input.readInt()

            return PongPacket(id)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: PongPacket) {
            output.writeInt(value.id)
        }
    }
}