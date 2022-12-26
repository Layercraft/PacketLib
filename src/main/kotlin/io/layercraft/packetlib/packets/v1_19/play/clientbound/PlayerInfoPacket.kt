package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Player Info | 0x34 | play | clientbound
 *
 * @property action action
 * @see <a href="https://wiki.vg/Protocol#Player_Info">https://wiki.vg/Protocol#Player_Info</a>
 */

@MinecraftPacket(packetId = 0x34, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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