package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Experience | 0x52 | play | clientbound
 *
 * @param experienceBar experienceBar
 * @param totalExperience totalExperience
 * @param level level
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Experience">https://wiki.vg/Protocol#Set_Experience</a>
 */

data class ExperiencePacket(
    val experienceBar: Float,
    val totalExperience: Int, // varint
    val level: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<ExperiencePacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): ExperiencePacket {
            val experienceBar = input.readFloat()
            val totalExperience = input.readVarInt()
            val level = input.readVarInt()

            return ExperiencePacket(experienceBar, totalExperience, level)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: ExperiencePacket) {
            output.writeFloat(value.experienceBar)
            output.writeVarInt(value.totalExperience)
            output.writeVarInt(value.level)
        }
    }
}