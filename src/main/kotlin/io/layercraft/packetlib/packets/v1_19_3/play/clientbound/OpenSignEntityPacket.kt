package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer
import io.layercraft.packetlib.types.Position
/**
 * Open Sign Editor | 0x2d | play | clientbound
 *
 * @param location location
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Open_Sign_Editor">https://wiki.vg/Protocol#Open_Sign_Editor</a>
 */

data class OpenSignEntityPacket(
    val location: Position,
) : ClientBoundPacket {
    companion object : PacketSerializer<OpenSignEntityPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): OpenSignEntityPacket {
            val location = input.readPosition()

            return OpenSignEntityPacket(location)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: OpenSignEntityPacket) {
            output.writePosition(value.location)
        }
    }
}