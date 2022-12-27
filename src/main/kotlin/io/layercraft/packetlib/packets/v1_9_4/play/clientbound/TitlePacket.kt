package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Title | 0x45 | play | clientbound
 *
 * @property action action
 * @see <a href="https://wiki.vg/Protocol#Title">https://wiki.vg/Protocol#Title</a>
 */

@MinecraftPacket(id = 0x45, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class TitlePacket(
    val action: Int, // varint
) : ClientBoundPacket {

    companion object : PacketSerializer<TitlePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): TitlePacket {
            val action = input.readVarInt()

            return TitlePacket(action)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: TitlePacket) {
            output.writeVarInt(value.action)
        }
    }
}
