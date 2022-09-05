package io.layercraft.translator.packets.client.login

import io.layercraft.translator.packets.ClientPacket
import io.layercraft.translator.packets.server.login.LoginSuccess
import io.layercraft.translator.serialization.MinecraftPacketSerializer
import io.layercraft.translator.serialization.processing.*
import io.layercraft.translator.types.UUIDSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import java.util.*

/**
 * Login start | server-bound | Packet ID 0x00 | State: Login | Answer with [LoginSuccess].
 *
 * @property name Player's Username.
 * @property hasSigData Whether the next 3 fields should be sent.
 * @property timestamp When the key data will expire. Optional. Only sent if Has Sig Data is true.
 * @property publicKey The encoded bytes of the public key the client received from Mojang. Optional. Only sent if Has Sig Data is true.
 * @property signature The bytes of the public key signature the client received from Mojang. Optional. Only sent if Has Sig Data is true.
 * @property hasPlayerUUID Whether the next field should be sent.
 * @property playerUUID The UUID of the player logging in. Optional. Only sent if Has Player UUID is true.
 * @see <a href="https://wiki.vg/Protocol#Login_Start">https://wiki.vg/Protocol#Login_Start</a>
 */
@Serializable
data class LoginStart(
    @MinecraftString(16)
    val name: String,
    val hasSigData: Boolean,
    @MinecraftNumber(MinecraftNumberType.DEFAULT)
    val timestamp: Long?,
    @MinecraftArray(MinecraftArraySizeType.VARINT)
    val publicKey: ByteArray?,
    @MinecraftArray(MinecraftArraySizeType.VARINT)
    val signature: ByteArray?,
    val hasPlayerUUID: Boolean,
    @Serializable(with = UUIDSerializer::class)
    val playerUUID: UUID?
) : ClientPacket {

    @Serializer(forClass = LoginStart::class)
    companion object : MinecraftPacketSerializer<LoginStart> {
        override val descriptor: SerialDescriptor = serializer().descriptor

        override fun serialize(encoder: MinecraftProtocolEncoder, value: LoginStart) {
            encoder.encodeTaggedString(value.name, 16)
            encoder.encodeTaggedBoolean(ProtocolDescriptor.DEFAULT, value.hasSigData)
            if (value.hasSigData) {
                encoder.encodeTaggedLong(ProtocolDescriptor.DEFAULT, value.timestamp ?: throw SerializationException("Timestamp is null but hasSigData is true"))
                encoder.encodeTaggedVarIntByteArray(value.publicKey ?: throw SerializationException("Public key is null but hasSigData is true"))
                encoder.encodeTaggedVarIntByteArray(value.signature ?: throw SerializationException("Signature is null but hasSigData is true"))
            }
            encoder.encodeTaggedBoolean(ProtocolDescriptor.DEFAULT, value.hasPlayerUUID)
            if (value.hasPlayerUUID) {
                encoder.encodeTaggedUUID(value.playerUUID ?: throw SerializationException("Player UUID is null but hasPlayerUUID is true"))
            }
        }

        override fun deserialize(decoder: MinecraftProtocolDecoder): LoginStart {

            print("LoginStartDeserializer")

            val name = decoder.decodeTaggedString(16)
            val hasSigData = decoder.decodeTaggedBoolean(ProtocolDescriptor.DEFAULT)
            val timestamp = if (hasSigData) decoder.decodeTaggedLong(ProtocolDescriptor.DEFAULT) else null
            val publicKey = if (hasSigData) decoder.decodeTaggedVarIntByteArray() else null
            val signature = if (hasSigData) decoder.decodeTaggedVarIntByteArray() else null
            val hasPlayerUUID = decoder.decodeTaggedBoolean(ProtocolDescriptor.DEFAULT)
            val playerUUID = if (hasPlayerUUID) decoder.decodeTaggedUUID() else null

            return LoginStart(name, hasSigData, timestamp, publicKey, signature, hasPlayerUUID, playerUUID)
        }
    }
}
