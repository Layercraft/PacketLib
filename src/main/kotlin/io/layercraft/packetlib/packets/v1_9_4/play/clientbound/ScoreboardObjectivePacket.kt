package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Encryption Response | 0x3f | play | clientbound
 *
 * @property name name
 * @property action action
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(packetId = 0x3f, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class ScoreboardObjectivePacket(
    val name: String,
    val action: Byte,
) : ClientBoundPacket {

    companion object : PacketSerializer<ScoreboardObjectivePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): ScoreboardObjectivePacket {
            val name = input.readString()
            val action = input.readByte()

            return ScoreboardObjectivePacket(name, action)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: ScoreboardObjectivePacket) {
            output.writeString(value.name)
            output.writeByte(value.action)
        }
    }
}
