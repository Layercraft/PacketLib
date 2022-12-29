package io.layercraft.packetlib.packets.v1_19_2.login.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Encryption Response | 0x01 | login | serverbound
 *
 * @property sharedSecret sharedSecret
 * @property hasVerifyToken hasVerifyToken
 * @property verifyToken verifyToken
 * @property salt salt
 * @property messageSignature messageSignature
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(id = 0x01, state = PacketState.LOGIN, direction = PacketDirection.SERVERBOUND)
data class EncryptionBeginPacket(
    val sharedSecret: ByteArray,
    val hasVerifyToken: Boolean,
    val verifyToken: ByteArray?,
    val salt: Long?,
    val messageSignature: ByteArray?,
) : ServerBoundPacket {
    companion object : PacketSerializer<EncryptionBeginPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): EncryptionBeginPacket {
            val sharedSecret = input.readVarIntByteArray()
            val hasVerifyToken = input.readBoolean()
            val verifyToken = when (hasVerifyToken) {
                true -> input.readVarIntByteArray()
                else -> null
            }
            val salt = when (hasVerifyToken) {
                false -> input.readLong()
                else -> null
            }
            val messageSignature = when (hasVerifyToken) {
                false -> input.readVarIntByteArray()
                else -> null
            }

            return EncryptionBeginPacket(sharedSecret, hasVerifyToken, verifyToken, salt, messageSignature)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: EncryptionBeginPacket) {
            output.writeVarIntByteArray(value.sharedSecret)
            output.writeBoolean(value.hasVerifyToken)
            when (value.hasVerifyToken) {
                true -> output.writeVarIntByteArray(value.verifyToken!!)
                else -> {}
            }
            when (value.hasVerifyToken) {
                false -> output.writeLong(value.salt!!)
                else -> {}
            }
            when (value.hasVerifyToken) {
                false -> output.writeVarIntByteArray(value.messageSignature!!)
                else -> {}
            }
        }
    }
}