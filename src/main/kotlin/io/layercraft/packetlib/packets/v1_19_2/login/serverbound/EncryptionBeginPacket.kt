package io.layercraft.packetlib.packets.v1_19_2.login.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Encryption Response | 0x01 | login | serverbound
 *
 * @property sharedSecret sharedSecret
 * @property hasVerifyToken hasVerifyToken
 * @property salt salt
 * @property messageSignature messageSignature
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(id = 0x01, state = PacketState.LOGIN, direction = PacketDirection.SERVERBOUND)
data class EncryptionBeginPacket(
    val sharedSecret: ByteArray,
    val hasVerifyToken: Boolean,
    val salt: Long?,
    val messageSignature: ByteArray?,
) : ServerBoundPacket {
    companion object : PacketSerializer<EncryptionBeginPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): EncryptionBeginPacket {
            val sharedSecret = input.readVarIntByteArray()
            val hasVerifyToken = input.readBoolean()
            val salt = when (hasVerifyToken) {
                true -> input.readLong()
                false -> input.readLong()
            }
            val messageSignature = when (hasVerifyToken) {
                true -> input.readVarIntByteArray()
                false -> input.readVarIntByteArray()
            }

            return EncryptionBeginPacket(sharedSecret, hasVerifyToken, salt, messageSignature)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: EncryptionBeginPacket) {
            output.writeVarIntByteArray(value.sharedSecret)
            output.writeBoolean(value.hasVerifyToken)
            when (value.hasVerifyToken) {
                true -> output.writeLong(value.salt!!)
                false -> output.writeLong(value.salt!!)
            }
            when (value.hasVerifyToken) {
                true -> output.writeVarIntByteArray(value.messageSignature!!)
                false -> output.writeVarIntByteArray(value.messageSignature!!)
            }
        }
    }
}