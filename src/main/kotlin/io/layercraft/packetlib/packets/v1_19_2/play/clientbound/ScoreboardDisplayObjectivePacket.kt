package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Display Objective | 0x4f | play | clientbound
 *
 * @property position position
 * @property name name
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Display_Objective">https://wiki.vg/Protocol#Display_Objective</a>
 */

@MinecraftPacket(id = 0x4f, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class ScoreboardDisplayObjectivePacket(
    val position: Byte,
    val name: String,
) : ClientBoundPacket {
    companion object : PacketSerializer<ScoreboardDisplayObjectivePacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): ScoreboardDisplayObjectivePacket {
            val position = input.readByte()
            val name = input.readString()

            return ScoreboardDisplayObjectivePacket(position, name)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: ScoreboardDisplayObjectivePacket) {
            output.writeByte(value.position)
            output.writeString(value.name)
        }
    }
}
