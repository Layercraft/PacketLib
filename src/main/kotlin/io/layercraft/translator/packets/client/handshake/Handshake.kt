package io.layercraft.translator.packets.client.handshake



import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.*
import io.layercraft.translator.utils.mc


enum class HandshakeNextState {
    STATUS,
    LOGIN
}

/**
 * Handshake | server-bound | Packet ID: 0x00 | State: Handshaking | Answer with nothing.
 *
 * @property version VarInt - See protocol version numbers (currently 760 in Minecraft 1.19.2).
 * @property address String (255) - Hostname or IP, e.g. localhost or 127.0.0.1, that was used to connect. The Notchian server does not use this information. Note that SRV records are a simple redirect, e.g. if _minecraft._tcp.example.com points to mc.example.org, users connecting to example.com will provide example.org as server address in addition to connecting to it.
 * @property port Unsigned Short - Default is 25565. The Notchian server does not use this information.
 * @property nextState VarInt Enum - 1 for Status, 2 for Login. [HandshakeNextState]
 * @see <a href="https://wiki.vg/Protocol#Handshake">https://wiki.vg/Protocol#Handshake</a>
 */
@MinecraftPacket(packetId = 0x00, state = PacketState.HANDSHAKE, direction = PacketDirection.SERVERBOUND)
data class Handshake(
    val version: Int,
    val address: String,
    val port: UShort,
    val nextState: HandshakeNextState
) : ClientPacket {
    companion object: PacketSerializer<Handshake> {

        override fun serialize(input: Input): Handshake {
            val version = input.readInt()
            val address = input.mc.readString(255)
            val port = input.readUShort()
            val nextState = HandshakeNextState.values()[input.readInt() + 1]


            return Handshake(version, address, port, nextState)
        }

        override fun deserialize(output: Output, value: Handshake) {
            output.writeInt(value.version)
            output.mc.writeString(value.address, 255)
            output.writeUShort(value.port)
            output.writeInt(value.nextState.ordinal - 1)
        }
    }
}
