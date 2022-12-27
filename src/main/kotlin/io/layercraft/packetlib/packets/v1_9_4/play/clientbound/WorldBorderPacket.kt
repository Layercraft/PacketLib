package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * World Border | 0x35 | play | clientbound
 *
 * @property action action
 * @see <a href="https://wiki.vg/Protocol#World_Border">https://wiki.vg/Protocol#World_Border</a>
 */

@MinecraftPacket(packetId = 0x35, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class WorldBorderPacket(
    val action: Int, // varint
) : ClientBoundPacket {

    companion object : PacketSerializer<WorldBorderPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): WorldBorderPacket {
            val action = input.readVarInt()

            return WorldBorderPacket(action)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: WorldBorderPacket) {
            output.writeVarInt(value.action)
        }
    }
}
