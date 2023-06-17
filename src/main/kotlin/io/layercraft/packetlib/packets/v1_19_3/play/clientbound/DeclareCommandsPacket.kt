package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Commands | 0x0e | play | clientbound
 *
 * @param rootIndex rootIndex
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Commands">https://wiki.vg/Protocol#Commands</a>
 */

data class DeclareCommandsPacket(
    val rootIndex: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<DeclareCommandsPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): DeclareCommandsPacket {
            val rootIndex = input.readVarInt()

            return DeclareCommandsPacket(rootIndex)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: DeclareCommandsPacket) {
            output.writeVarInt(value.rootIndex)
        }
    }
}