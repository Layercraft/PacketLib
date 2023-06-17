package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Action Bar Text | 0x42 | play | clientbound
 *
 * @param text text
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Action_Bar_Text">https://wiki.vg/Protocol#Set_Action_Bar_Text</a>
 */

data class ActionBarPacket(
    val text: String,
) : ClientBoundPacket {
    companion object : PacketSerializer<ActionBarPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): ActionBarPacket {
            val text = input.readString()

            return ActionBarPacket(text)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: ActionBarPacket) {
            output.writeString(value.text)
        }
    }
}