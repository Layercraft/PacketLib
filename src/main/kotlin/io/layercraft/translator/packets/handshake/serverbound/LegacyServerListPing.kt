package io.layercraft.translator.packets.handshake.serverbound

import io.layercraft.translator.data.ProtocolVersion
import io.layercraft.translator.packets.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface

@MinecraftPacket(packetId = 0xFE, state = PacketState.HANDSHAKE, direction = PacketDirection.SERVERBOUND)
data class LegacyServerListPing(
    val protocolVersion: ProtocolVersion,
    val hostname: String,
    val port: Int
) : ServerBoundPacket {
    companion object : PacketSerializer<LegacyServerListPing> {

        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): LegacyServerListPing {
            TODO("Not yet implemented")
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: LegacyServerListPing) {
            TODO("Not yet implemented")
        }
    }
}