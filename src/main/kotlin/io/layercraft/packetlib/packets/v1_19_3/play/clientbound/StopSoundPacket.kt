package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Stop Sound | 0x5f | play | clientbound
 *
 * @param flags flags
 * @param source source
 * @param sound sound
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17935#Stop_Sound">https://wiki.vg/Protocol#Stop_Sound</a>
 */

@MinecraftPacket(id = 0x5f, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class StopSoundPacket(
    val flags: Byte,
    val source: Int?, // varint
    val sound: String?,
) : ClientBoundPacket {
    companion object : PacketSerializer<StopSoundPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): StopSoundPacket {
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

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: StopSoundPacket) {
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