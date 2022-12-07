package io.layercraft.translator.packets.handshake.serverbound

import io.layercraft.translator.data.ProtocolVersion
import io.layercraft.translator.packets.*
import io.layercraft.translator.packets.handshake.data.HandshakeNextState
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface

/**
 * Handshake | server-bound | Packet ID: 0x00 | State: Handshaking | Answer with nothing.
 *
 * @property protocolVersion See protocol version numbers (currently 760 in Minecraft 1.19.2).
 * @property address Hostname or IP, e.g. localhost or 127.0.0.1, that was used to connect. The Notchian server does not use this information. Note that SRV records are a simple redirect, e.g. if _minecraft._tcp.example.com points to mc.example.org, users connecting to example.com will provide example.org as server address in addition to connecting to it.
 * @property port Default is 25565. The Notchian server does not use this information.
 * @property nextState 1 for Status, 2 for Login. [HandshakeNextState]
 * @see <a href="https://wiki.vg/Protocol#Handshake">https://wiki.vg/Protocol#Handshake</a>
 */
@MinecraftPacket(packetId = 0x00, state = PacketState.HANDSHAKE, direction = PacketDirection.SERVERBOUND)
data class Handshake(
    val protocolVersion: ProtocolVersion, // varint
    val address: String, // string(255)
    val port: UShort,
    val nextState: HandshakeNextState // varint enum
) : ServerBoundPacket {
    companion object : PacketSerializer<Handshake> {

        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): Handshake {
            val version = input.readVarInt()
            val address = input.readString(255)
            val port = input.readUShort()
            val nextState = HandshakeNextState.values()[input.readVarInt() - 1]

            return Handshake(ProtocolVersion.fromProtocolNumber(version), address, port, nextState)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: Handshake) {
            output.writeVarInt(value.protocolVersion.protocolNumber)
            output.writeString(value.address, 255)
            output.writeUShort(value.port)
            output.writeVarInt(value.nextState.ordinal + 1)
        }
    }
}