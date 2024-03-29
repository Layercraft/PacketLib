package io.layercraft.packetlib.packets.v1_19_3.handshaking.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Handshake | 0x00 | handshaking | serverbound
 *
 * @param protocolVersion protocolVersion
 * @param serverHost serverHost
 * @param serverPort serverPort
 * @param nextState nextState
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Handshake">https://wiki.vg/Protocol#Handshake</a>
 */

data class SetProtocolPacket(
    val protocolVersion: Int, // varint
    val serverHost: String,
    val serverPort: UShort,
    val nextState: Int, // varint
) : ServerBoundPacket {
    companion object : PacketSerializer<SetProtocolPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): SetProtocolPacket {
            val protocolVersion = input.readVarInt()
            val serverHost = input.readString()
            val serverPort = input.readUShort()
            val nextState = input.readVarInt()

            return SetProtocolPacket(protocolVersion, serverHost, serverPort, nextState)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: SetProtocolPacket) {
            output.writeVarInt(value.protocolVersion)
            output.writeString(value.serverHost)
            output.writeUShort(value.serverPort)
            output.writeVarInt(value.nextState)
        }
    }
}