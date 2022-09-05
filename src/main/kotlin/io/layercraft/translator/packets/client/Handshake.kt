package io.layercraft.translator.packets.client



import io.layercraft.translator.packets.ClientPacket
import io.layercraft.translator.serialization.processing.MinecraftNumber
import io.layercraft.translator.serialization.processing.MinecraftNumberType
import io.layercraft.translator.serialization.processing.MinecraftString
import io.layercraft.translator.serialization.processing.SerialOrdinal
import kotlinx.serialization.Serializable


@Serializable
enum class HandshakeNextState {
    @SerialOrdinal(1) STATUS,
    @SerialOrdinal(2) LOGIN
}

/**
 * Handshake | server-bound | Packet ID: 0x00 | State: Handshaking | Answer with nothing.
 *
 * @property version See protocol version numbers (currently 760 in Minecraft 1.19.2).
 * @property address Hostname or IP, e.g. localhost or 127.0.0.1, that was used to connect. The Notchian server does not use this information. Note that SRV records are a simple redirect, e.g. if _minecraft._tcp.example.com points to mc.example.org, users connecting to example.com will provide example.org as server address in addition to connecting to it.
 * @property port Default is 25565. The Notchian server does not use this information.
 * @property nextState 1 for Status, 2 for Login. [HandshakeNextState]
 * @see <a href="https://wiki.vg/Protocol#Handshake">https://wiki.vg/Protocol#Handshake</a>
 */
@Serializable
data class Handshake(
    @MinecraftNumber(MinecraftNumberType.VAR)
    val version: Int,

    @MinecraftString(255)
    val address: String,

    val port: UShort,

    val nextState: HandshakeNextState
) : ClientPacket
