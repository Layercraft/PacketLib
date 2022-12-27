package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Encryption Response | 0x2d | play | clientbound
 *
 * @property action action
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(packetId = 0x2d, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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
