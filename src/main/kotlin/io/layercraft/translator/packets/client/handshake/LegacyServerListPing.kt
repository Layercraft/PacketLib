package io.layercraft.translator.packets.client.handshake

import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.*

@MinecraftPacket(packetId = 0xFE, state = PacketState.HANDSHAKE, direction = PacketDirection.SERVERBOUND)
data class LegacyServerListPing(
    val protocolVersion: Int,
    val hostname: String,
    val port: Int
): ClientPacket {
    companion object: PacketSerializer<LegacyServerListPing> {

        override fun serialize(input: Input): LegacyServerListPing {
            TODO("Not yet implemented")
        }

        override fun deserialize(output: Output, value: LegacyServerListPing) {
            TODO("Not yet implemented")
        }
    }
}
