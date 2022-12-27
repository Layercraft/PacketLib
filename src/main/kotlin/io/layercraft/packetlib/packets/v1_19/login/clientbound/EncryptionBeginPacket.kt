package io.layercraft.packetlib.packets.v1_19.login.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Encryption Request | 0x01 | login | clientbound
 *
 * @property serverId serverId
 * @see <a href="https://wiki.vg/Protocol#Encryption_Request">https://wiki.vg/Protocol#Encryption_Request</a>
 */

@MinecraftPacket(id = 0x01, state = PacketState.LOGIN, direction = PacketDirection.CLIENTBOUND)
data class EncryptionBeginPacket(
    val serverId: String,
) : ClientBoundPacket {

    companion object : PacketSerializer<EncryptionBeginPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): EncryptionBeginPacket {
            val serverId = input.readString()

            return EncryptionBeginPacket(serverId)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: EncryptionBeginPacket) {
            output.writeString(value.serverId)
        }
    }
}