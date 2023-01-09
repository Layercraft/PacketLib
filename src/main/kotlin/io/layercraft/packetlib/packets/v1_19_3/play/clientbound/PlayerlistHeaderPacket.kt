package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Tab List Header And Footer | 0x61 | play | clientbound
 *
 * @param header header
 * @param footer footer
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17932#Set_Tab_List_Header_And_Footer">https://wiki.vg/Protocol#Set_Tab_List_Header_And_Footer</a>
 */

@MinecraftPacket(id = 0x61, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class PlayerlistHeaderPacket(
    val header: String,
    val footer: String,
) : ClientBoundPacket {
    companion object : PacketSerializer<PlayerlistHeaderPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): PlayerlistHeaderPacket {
            val header = input.readString()
            val footer = input.readString()

            return PlayerlistHeaderPacket(header, footer)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: PlayerlistHeaderPacket) {
            output.writeString(value.header)
            output.writeString(value.footer)
        }
    }
}