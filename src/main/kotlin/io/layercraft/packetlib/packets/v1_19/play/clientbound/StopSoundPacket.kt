package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x5e | play | clientbound
 *
 * @property flags flags
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(packetId = 0x5e, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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