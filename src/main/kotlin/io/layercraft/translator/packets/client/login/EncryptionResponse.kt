package io.layercraft.translator.packets.client.login

import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.*
import io.layercraft.translator.utils.mc


/**
 * Encryption response | 0x01 | login | server-bound
 *
 * @property sharedSecret VarInt ByteArray - Shared Secret value, encrypted with the server's public key.
 * @property hasVerifyToken Boolean - Whether the Verify Token should be sent. If not, then the salt and signature will be sent.
 * @property verifyToken VarInt ByteArray - Verify Token value, encrypted with the same public key as the shared secret. Optional and only sent if Has Verified Token is true.
 * @property salt Long - Cryptography, used for validating the message signature. Optional and only sent if Has Verified Token is false.
 * @property messageSignature VarInt ByteArray - The bytes of the public key signature the client received from Mojang. Optional and only sent if Has Verified Token is false.
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(packetId = 0x01, state = PacketState.LOGIN, direction = PacketDirection.SERVERBOUND)
data class EncryptionResponse(
    val sharedSecret: ByteArray,
    val hasVerifyToken: Boolean,
    val verifyToken: ByteArray?,
    val salt: Long?,
    val messageSignature: ByteArray?,
) : ClientPacket {
    companion object: PacketSerializer<EncryptionResponse> {

        override fun serialize(input: Input): EncryptionResponse {
            val sharedSecret = input.mc.readVarIntByteArray()
            val hasVerifyToken = input.mc.readBoolean()
            val verifyToken = if (hasVerifyToken) input.mc.readVarIntByteArray() else null
            val salt = if (!hasVerifyToken) null else input.readLong()
            val messageSignature = if (!hasVerifyToken) input.mc.readVarIntByteArray() else null
            return EncryptionResponse(sharedSecret, hasVerifyToken, verifyToken, salt, messageSignature)
        }

        override fun deserialize(output: Output, value: EncryptionResponse) {
            output.mc.writeVarIntByteArray(value.sharedSecret)
            output.mc.writeBoolean(value.hasVerifyToken)
            if (value.hasVerifyToken) {
                output.mc.writeVarIntByteArray(value.verifyToken!!)
            }
            if (!value.hasVerifyToken) {
                output.mc.writeLong(value.salt!!)
            }
            if (!value.hasVerifyToken) {
                output.mc.writeVarIntByteArray(value.messageSignature!!)
            }
        }
    }
}
