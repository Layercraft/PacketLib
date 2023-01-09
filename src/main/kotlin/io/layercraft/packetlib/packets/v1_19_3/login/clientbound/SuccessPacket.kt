package io.layercraft.packetlib.packets.v1_19_3.login.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.util.UUID
/**
 * Login Success | 0x02 | login | clientbound
 *
 * @param uuid uuid
 * @param username username
 * @param properties list of SuccessPacketProperties
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17932#Login_Success">https://wiki.vg/Protocol#Login_Success</a>
 */

@MinecraftPacket(id = 0x02, state = PacketState.LOGIN, direction = PacketDirection.CLIENTBOUND)
data class SuccessPacket(
    val uuid: UUID,
    val username: String,
    val properties: List<SuccessPacketProperties>, // varint array
) : ClientBoundPacket {
    companion object : PacketSerializer<SuccessPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): SuccessPacket {
            val uuid = input.readUUID()
            val username = input.readString()
            val properties = input.readVarIntArray { arrayInput ->
                val name = arrayInput.readString()
                val value = arrayInput.readString()
                val hasSignature = arrayInput.readBoolean()
                val signature = if (hasSignature) arrayInput.readString() else null

                return@readVarIntArray SuccessPacketProperties(name, value, hasSignature, signature)
            }

            return SuccessPacket(uuid, username, properties)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: SuccessPacket) {
            output.writeUUID(value.uuid)
            output.writeString(value.username)

            output.writeVarIntArray(value.properties) { arrayValue, arrayOutput ->
                arrayOutput.writeString(arrayValue.name)
                arrayOutput.writeString(arrayValue.value)
                arrayOutput.writeBoolean(arrayValue.hasSignature)
                if (arrayValue.hasSignature) arrayOutput.writeString(arrayValue.signature!!)
            }
        }
    }
}

/**
 * SuccessPacketProperties
 *
 * @param name name
 * @param value value
 * @param hasSignature signature is present
 * @param signature signature
*/
data class SuccessPacketProperties(
    val name: String,
    val value: String,
    val hasSignature: Boolean,
    val signature: String?,
)