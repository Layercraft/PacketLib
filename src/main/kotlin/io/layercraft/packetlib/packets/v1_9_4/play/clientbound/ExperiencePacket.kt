package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Encryption Response | 0x3d | play | clientbound
 *
 * @property experienceBar experienceBar
 * @property level level
 * @property totalExperience totalExperience
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(id = 0x3d, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class ExperiencePacket(
    val experienceBar: Float,
    val level: Int, // varint
    val totalExperience: Int, // varint
) : ClientBoundPacket {

    companion object : PacketSerializer<ExperiencePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): ExperiencePacket {
            val experienceBar = input.readFloat()
            val level = input.readVarInt()
            val totalExperience = input.readVarInt()

            return ExperiencePacket(experienceBar, level, totalExperience)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: ExperiencePacket) {
            output.writeFloat(value.experienceBar)
            output.writeVarInt(value.level)
            output.writeVarInt(value.totalExperience)
        }
    }
}
