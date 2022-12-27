package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x1c | play | serverbound
 *
 * @property flags flags
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x1c, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class AbilitiesPacket(
    val flags: Byte,
) : ServerBoundPacket {

    companion object : PacketSerializer<AbilitiesPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): AbilitiesPacket {
            val flags = input.readByte()

            return AbilitiesPacket(flags)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: AbilitiesPacket) {
            output.writeByte(value.flags)
        }
    }
}