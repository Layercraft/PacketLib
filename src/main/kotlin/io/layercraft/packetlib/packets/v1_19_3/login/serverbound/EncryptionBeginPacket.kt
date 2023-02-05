package io.layercraft.packetlib.packets.v1_19_3.login.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Encryption Response | 0x01 | login | serverbound
 *
 * @param sharedSecret sharedSecret
 * @param verifyToken verifyToken
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17964#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(id = 0x01, state = PacketState.LOGIN, direction = PacketDirection.SERVERBOUND)
data class EncryptionBeginPacket(
    val sharedSecret: ByteArray,
    val verifyToken: ByteArray,
) : ServerBoundPacket {
    companion object : PacketSerializer<EncryptionBeginPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): EncryptionBeginPacket {
            val sharedSecret = input.readVarIntByteArray()
            val verifyToken = input.readVarIntByteArray()

            return EncryptionBeginPacket(sharedSecret, verifyToken)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: EncryptionBeginPacket) {
            output.writeVarIntByteArray(value.sharedSecret)
            output.writeVarIntByteArray(value.verifyToken)
        }
    }
}