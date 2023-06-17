package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Title Text | 0x5b | play | clientbound
 *
 * @param text text
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Title_Text">https://wiki.vg/Protocol#Set_Title_Text</a>
 */

data class SetTitleTextPacket(
    val text: String,
) : ClientBoundPacket {
    companion object : PacketSerializer<SetTitleTextPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): SetTitleTextPacket {
            val text = input.readString()

            return SetTitleTextPacket(text)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: SetTitleTextPacket) {
            output.writeString(value.text)
        }
    }
}