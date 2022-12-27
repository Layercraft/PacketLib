package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Player List Header And Footer | 0x47 | play | clientbound
 *
 * @property header header
 * @property footer footer
 * @see <a href="https://wiki.vg/Protocol#Player_List_Header_And_Footer">https://wiki.vg/Protocol#Player_List_Header_And_Footer</a>
 */

@MinecraftPacket(id = 0x47, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class PlayerlistHeaderPacket(
    val header: String,
    val footer: String,
) : ClientBoundPacket {

    companion object : PacketSerializer<PlayerlistHeaderPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): PlayerlistHeaderPacket {
            val header = input.readString()
            val footer = input.readString()

            return PlayerlistHeaderPacket(header, footer)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: PlayerlistHeaderPacket) {
            output.writeString(value.header)
            output.writeString(value.footer)
        }
    }
}
