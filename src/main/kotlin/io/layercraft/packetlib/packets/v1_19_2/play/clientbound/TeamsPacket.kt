package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Update Teams | 0x58 | play | clientbound
 *
 * @property team team
 * @property mode mode
 * @see <a href="https://wiki.vg/Protocol#Update_Teams">https://wiki.vg/Protocol#Update_Teams</a>
 */

@MinecraftPacket(id = 0x58, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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