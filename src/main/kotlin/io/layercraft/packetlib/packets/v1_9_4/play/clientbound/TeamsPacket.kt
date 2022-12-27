package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Teams | 0x41 | play | clientbound
 *
 * @property team team
 * @property mode mode
 * @see <a href="https://wiki.vg/Protocol#Teams">https://wiki.vg/Protocol#Teams</a>
 */

@MinecraftPacket(packetId = 0x41, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class TeamsPacket(
    val team: String,
    val mode: Byte,
) : ClientBoundPacket {

    companion object : PacketSerializer<TeamsPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): TeamsPacket {
            val team = input.readString()
            val mode = input.readByte()

            return TeamsPacket(team, mode)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: TeamsPacket) {
            output.writeString(value.team)
            output.writeByte(value.mode)
        }
    }
}
