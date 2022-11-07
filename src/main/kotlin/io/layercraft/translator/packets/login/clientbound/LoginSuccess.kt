package io.layercraft.translator.packets.login.clientbound

import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.translator.utils.minecraft
import java.util.*

/**
 * Login success | 0x02 | login | client-bound
 *
 * @property uuid
 * @property username String (16)
 * @property properties [Arrays] of [LoginProperty]s Array of [LoginProperty]s
 * @see <a href="https://wiki.vg/Protocol#Login_Success">Login Success</a>
 */
@MinecraftPacket(packetId = 0x02, state = PacketState.LOGIN, direction = PacketDirection.CLIENTBOUND)
data class LoginSuccess(
    val uuid: UUID,
    val username: String, //string(16)
    val properties: List<LoginProperty>, //varint array of login properties
): ClientBoundPacket {
    companion object: PacketSerializer<LoginSuccess> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): LoginSuccess {
            val uuid = input.readUUID()
            val username = input.readString(16)
            val properties = input.readVarIntArray {
                val name = it.readString(32767)
                val value = it.readString(32767)
                val isSigned = it.readBoolean()
                val signature = if (isSigned) it.readString(32767) else null
                LoginProperty(name, value, signature)
            }
            return LoginSuccess(uuid, username, properties)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: LoginSuccess) {
            output.writeUUID(value.uuid)
            output.writeString(value.username, 16)
            output.writeVarIntArray(value.properties) { arrayValue, arrayOutput ->
                arrayOutput.writeString(arrayValue.name, 32767)
                arrayOutput.writeString(arrayValue.value, 32767)
                arrayOutput.writeBoolean(arrayValue.signed)
                if (arrayValue.signed) arrayOutput.writeString(arrayValue.signature!!, 32767)
            }
        }
    }
}


/**
 * Login property
 *
 * @property name String (32767) -
 * @property value String (32767) -
 * @property signed Boolean -
 * @property signature [Optional] - String (32767) - Only if Is Signed is true.
 * @see <a href="https://wiki.vg/Protocol#Login_Success">Login Success</a>
 */
data class LoginProperty(
    val name: String,
    val value: String,
    val signature: String?,
){
    val signed
        get() = signature != null
}
