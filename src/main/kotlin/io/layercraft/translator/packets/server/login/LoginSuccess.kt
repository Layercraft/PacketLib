package io.layercraft.translator.packets.server.login

import io.layercraft.translator.packets.ServerPacket
import io.layercraft.translator.serialization.MinecraftPacketSerializer
import io.layercraft.translator.serialization.processing.*
import io.layercraft.translator.types.UUIDSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import java.util.*

/**
 * Login success | client-bound | Packet ID: 0x02 | State: Login | Answer to [LoginStartPacket]. //TODO
 *
 * @property uuid
 * @property username
 * @property properties [Arrays] of [LoginProperty]s
 * @see <a href="https://wiki.vg/Protocol#Login_Success">Login Success</a>
 */
@Serializable
data class LoginSuccess(
    @Serializable(with = UUIDSerializer::class)
    val uuid: UUID,
    @MinecraftString(16)
    val username: String,
    @MinecraftArray(MinecraftArraySizeType.VARINT)
    val properties: Array<LoginProperty>
): ServerPacket


/**
 * Login property
 *
 * @property name
 * @property value
 * @property signed
 * @property signature [Optional] signature
 * @see <a href="https://wiki.vg/Protocol#Login_Success">Login Success</a>
 */
@Serializable
data class LoginProperty(
    @MinecraftString(32767)
    val name: String,
    @MinecraftString(32767)
    val value: String,
    val signed: Boolean,
    @MinecraftString(32767)
    val signature: String?,
){
    @Serializer(forClass = LoginProperty::class)
    companion object : MinecraftPacketSerializer<LoginProperty> {
        override val descriptor: SerialDescriptor
            get() = LoginProperty.serializer().descriptor

        override fun deserialize(decoder: MinecraftProtocolDecoder): LoginProperty {
            val name = decoder.decodeTaggedString(32767)
            val value = decoder.decodeTaggedString(32767)
            val signed = decoder.decodeTaggedBoolean(ProtocolDescriptor.DEFAULT)
            val signature = if (signed) decoder.decodeString() else null

            return LoginProperty(name, value, signed, signature)
        }

        override fun serialize(encoder: MinecraftProtocolEncoder, value: LoginProperty) {
            encoder.encodeTaggedString(value.name, 32767)
            encoder.encodeTaggedString(value.value, 32767)
            encoder.encodeTaggedBoolean(ProtocolDescriptor.DEFAULT, value.signed)
            if (value.signed) encoder.encodeTaggedString(value.signature!!, 32767)
        }
    }
}
