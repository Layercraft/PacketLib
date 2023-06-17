package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Player Abilities | 0x30 | play | clientbound
 *
 * @param flags flags
 * @param flyingSpeed flyingSpeed
 * @param walkingSpeed walkingSpeed
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Player_Abilities">https://wiki.vg/Protocol#Player_Abilities</a>
 */

data class AbilitiesPacket(
    val flags: Byte,
    val flyingSpeed: Float,
    val walkingSpeed: Float,
) : ClientBoundPacket {
    companion object : PacketSerializer<AbilitiesPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): AbilitiesPacket {
            val flags = input.readByte()
            val flyingSpeed = input.readFloat()
            val walkingSpeed = input.readFloat()

            return AbilitiesPacket(flags, flyingSpeed, walkingSpeed)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: AbilitiesPacket) {
            output.writeByte(value.flags)
            output.writeFloat(value.flyingSpeed)
            output.writeFloat(value.walkingSpeed)
        }
    }
}