package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Player Info | 0x37 | play | clientbound
 *
 * @property action action
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Player_Info">https://wiki.vg/Protocol#Player_Info</a>
 */

@MinecraftPacket(id = 0x37, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class PlayerInfoPacket(
    val action: Int, // varint
) : ClientBoundPacket {

    companion object : PacketSerializer<PlayerInfoPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): PlayerInfoPacket {
            val action = input.readVarInt()

            return PlayerInfoPacket(action)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: PlayerInfoPacket) {
            output.writeVarInt(value.action)
        }
    }
}