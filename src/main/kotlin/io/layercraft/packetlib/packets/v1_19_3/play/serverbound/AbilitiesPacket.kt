package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Player Abilities | 0x1b | play | serverbound
 *
 * @param flags flags
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Player_Abilities_2">https://wiki.vg/Protocol#Player_Abilities_2</a>
 */

data class AbilitiesPacket(
    val flags: Byte,
) : ServerBoundPacket {
    companion object : PacketSerializer<AbilitiesPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): AbilitiesPacket {
            val flags = input.readByte()

            return AbilitiesPacket(flags)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: AbilitiesPacket) {
            output.writeByte(value.flags)
        }
    }
}