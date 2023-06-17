package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Client Command | 0x06 | play | serverbound
 *
 * @param actionId actionId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Client_Command">https://wiki.vg/Protocol#Client_Command</a>
 */

data class ClientCommandPacket(
    val actionId: Int, // varint
) : ServerBoundPacket {
    companion object : PacketSerializer<ClientCommandPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): ClientCommandPacket {
            val actionId = input.readVarInt()

            return ClientCommandPacket(actionId)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: ClientCommandPacket) {
            output.writeVarInt(value.actionId)
        }
    }
}