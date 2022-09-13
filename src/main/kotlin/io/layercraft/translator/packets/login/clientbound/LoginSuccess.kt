package io.layercraft.translator.packets.login.server

import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.*
import io.layercraft.translator.utils.mc
import java.util.*

/**
 * Login success | 0x02 | login | client-bound
 *
 * @property uuid UUID
 * @property username String (16)
 * @property properties [Arrays] of [LoginProperty]s Array of [LoginProperty]s
 * @see <a href="https://wiki.vg/Protocol#Login_Success">Login Success</a>
 */
@MinecraftPacket(packetId = 0x02, state = PacketState.LOGIN, direction = PacketDirection.CLIENTBOUND)
data class LoginSuccess(
    val uuid: UUID,
    val username: String,
    val properties: ArrayList<LoginProperty>
): ClientBoundPacket {
    companion object: PacketSerializer<LoginSuccess> {
        override fun serialize(input: Input): LoginSuccess {
            val uuid = input.mc.readUUID()
            val username = input.mc.readString(16)
            val properties = input.mc.readVarIntArray {
                val name = it.mc.readString(32767)
                val value = it.mc.readString(32767)
                val isSigned = it.mc.readBoolean()
                val signature = if (isSigned) it.mc.readString(32767) else null
                LoginProperty(name, value, isSigned, signature)
            }
            return LoginSuccess(uuid, username, properties)
        }

        override fun deserialize(output: Output, value: LoginSuccess) {
            output.mc.writeUUID(value.uuid)
            output.mc.writeString(value.username, 16)
            output.mc.writeVarIntArray(value.properties) { arrayValue, arrayOutput ->
                arrayOutput.mc.writeString(arrayValue.name, 32767)
                arrayOutput.mc.writeString(arrayValue.value, 32767)
                arrayOutput.mc.writeBoolean(arrayValue.signed)
                if (arrayValue.signed) arrayOutput.mc.writeString(arrayValue.signature!!, 32767)
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
    val signed: Boolean,
    val signature: String?,
)
