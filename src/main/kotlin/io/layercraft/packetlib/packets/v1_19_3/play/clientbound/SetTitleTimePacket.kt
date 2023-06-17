package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Title Animation Times | 0x5c | play | clientbound
 *
 * @param fadeIn fadeIn
 * @param stay stay
 * @param fadeOut fadeOut
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Title_Animation_Times">https://wiki.vg/Protocol#Set_Title_Animation_Times</a>
 */

data class SetTitleTimePacket(
    val fadeIn: Int,
    val stay: Int,
    val fadeOut: Int,
) : ClientBoundPacket {
    companion object : PacketSerializer<SetTitleTimePacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): SetTitleTimePacket {
            val fadeIn = input.readInt()
            val stay = input.readInt()
            val fadeOut = input.readInt()

            return SetTitleTimePacket(fadeIn, stay, fadeOut)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: SetTitleTimePacket) {
            output.writeInt(value.fadeIn)
            output.writeInt(value.stay)
            output.writeInt(value.fadeOut)
        }
    }
}