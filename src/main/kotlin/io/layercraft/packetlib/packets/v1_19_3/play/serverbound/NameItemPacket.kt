package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Rename Item | 0x23 | play | serverbound
 *
 * @param name name
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Rename_Item">https://wiki.vg/Protocol#Rename_Item</a>
 */

data class NameItemPacket(
    val name: String,
) : ServerBoundPacket {
    companion object : PacketSerializer<NameItemPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): NameItemPacket {
            val name = input.readString()

            return NameItemPacket(name)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: NameItemPacket) {
            output.writeString(value.name)
        }
    }
}