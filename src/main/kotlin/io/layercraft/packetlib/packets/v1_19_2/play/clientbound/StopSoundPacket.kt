package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Stop Sound | 0x61 | play | clientbound
 *
 * @property flags flags
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Stop_Sound">https://wiki.vg/Protocol#Stop_Sound</a>
 */

@MinecraftPacket(id = 0x61, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class StopSoundPacket(
    val flags: Byte,
) : ClientBoundPacket {

    companion object : PacketSerializer<StopSoundPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): StopSoundPacket {
            val flags = input.readByte()

            return StopSoundPacket(flags)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: StopSoundPacket) {
            output.writeByte(value.flags)
        }
    }
}