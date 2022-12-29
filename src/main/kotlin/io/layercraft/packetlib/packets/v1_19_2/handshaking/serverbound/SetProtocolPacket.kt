package io.layercraft.packetlib.packets.v1_19_2.handshaking.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Handshake | 0x00 | handshaking | serverbound
 *
 * @property protocolVersion protocolVersion
 * @property serverHost serverHost
 * @property serverPort serverPort
 * @property nextState nextState
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Handshake">https://wiki.vg/Protocol#Handshake</a>
 */

@MinecraftPacket(id = 0x00, state = PacketState.HANDSHAKING, direction = PacketDirection.SERVERBOUND)
data class SetProtocolPacket(
    val protocolVersion: Int, // varint
    val serverHost: String,
    val serverPort: UShort,
    val nextState: Int, // varint
) : ServerBoundPacket {
    companion object : PacketSerializer<SetProtocolPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): SetProtocolPacket {
            val protocolVersion = input.readVarInt()
            val serverHost = input.readString()
            val serverPort = input.readUShort()
            val nextState = input.readVarInt()

            return SetProtocolPacket(protocolVersion, serverHost, serverPort, nextState)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: SetProtocolPacket) {
            output.writeVarInt(value.protocolVersion)
            output.writeString(value.serverHost)
            output.writeUShort(value.serverPort)
            output.writeVarInt(value.nextState)
        }
    }
}