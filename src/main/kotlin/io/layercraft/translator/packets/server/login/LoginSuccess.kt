package io.layercraft.translator.packets.server.login

import io.layercraft.translator.MinecraftArray
import io.layercraft.translator.MinecraftArraySizeType
import io.layercraft.translator.MinecraftString
import io.layercraft.translator.types.UUIDSerializer
import kotlinx.serialization.Serializable
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
)


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
)