package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x2f | play | clientbound
 *
 * @property id id
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x2f, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class PingPacket(
    val id: Int,
) : ClientBoundPacket {

    companion object : PacketSerializer<PingPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): PingPacket {
            val id = input.readInt()

            return PingPacket(id)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: PingPacket) {
            output.writeInt(value.id)
        }
    }
}