package io.layercraft.packetlib.packets.v1_19_3.login.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Encryption Request | 0x01 | login | clientbound
 *
 * @param serverId serverId
 * @param publicKey publicKey
 * @param verifyToken verifyToken
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17932#Encryption_Request">https://wiki.vg/Protocol#Encryption_Request</a>
 */

@MinecraftPacket(id = 0x01, state = PacketState.LOGIN, direction = PacketDirection.CLIENTBOUND)
data class EncryptionBeginPacket(
    val serverId: String,
    val publicKey: ByteArray,
    val verifyToken: ByteArray,
) : ClientBoundPacket {
    companion object : PacketSerializer<EncryptionBeginPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): EncryptionBeginPacket {
            val serverId = input.readString()
            val publicKey = input.readVarIntByteArray()
            val verifyToken = input.readVarIntByteArray()

            return EncryptionBeginPacket(serverId, publicKey, verifyToken)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: EncryptionBeginPacket) {
            output.writeString(value.serverId)
            output.writeVarIntByteArray(value.publicKey)
            output.writeVarIntByteArray(value.verifyToken)
        }
    }
}