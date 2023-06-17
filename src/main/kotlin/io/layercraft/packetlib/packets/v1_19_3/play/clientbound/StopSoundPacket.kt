package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Stop Sound | 0x5f | play | clientbound
 *
 * @param flags flags
 * @param source source
 * @param sound sound
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Stop_Sound">https://wiki.vg/Protocol#Stop_Sound</a>
 */

data class StopSoundPacket(
    val flags: Byte,
    val source: Int?, // varint
    val sound: String?,
) : ClientBoundPacket {
    companion object : PacketSerializer<StopSoundPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): StopSoundPacket {
            val flags = input.readByte()
            val source = when (flags.toInt()) {
                3 -> input.readVarInt()
                1 -> input.readVarInt()
                else -> null
            }
            val sound = when (flags.toInt()) {
                3 -> input.readString()
                2 -> input.readString()
                else -> null
            }

            return StopSoundPacket(flags, source, sound)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: StopSoundPacket) {
            output.writeByte(value.flags)
            when (value.flags.toInt()) {
                3 -> output.writeVarInt(value.source!!)
                1 -> output.writeVarInt(value.source!!)
                else -> {}
            }
            when (value.flags.toInt()) {
                3 -> output.writeString(value.sound!!)
                2 -> output.writeString(value.sound!!)
                else -> {}
            }
        }
    }
}