package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.util.UUID
/**
 * Player Info | 0x37 | play | clientbound
 *
 * @param action action
 * @param data list of PlayerInfoPacketData
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Player_Info">https://wiki.vg/Protocol#Player_Info</a>
 */

@MinecraftPacket(id = 0x37, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class PlayerInfoPacket(
    val action: Int, // varint
    val data: List<PlayerInfoPacketData>, // varint array
) : ClientBoundPacket {
    companion object : PacketSerializer<PlayerInfoPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): PlayerInfoPacket {
            val action = input.readVarInt()
            val data = input.readVarIntArray { arrayInput ->
                val uuid = arrayInput.readUUID()
                val name = when (action) {
                    0 -> arrayInput.readString()
                    else -> null
                }
                val gamemode = when (action) {
                    0 -> arrayInput.readVarInt()
                    1 -> arrayInput.readVarInt()
                    else -> null
                }
                val ping = when (action) {
                    0 -> arrayInput.readVarInt()
                    2 -> arrayInput.readVarInt()
                    else -> null
                }
                val hasDisplayName = arrayInput.readBoolean()
                val displayName = when (action) {
                    0 -> if (hasDisplayName) arrayInput.readString() else null
                    3 -> if (hasDisplayName) arrayInput.readString() else null
                    else -> null
                }

                return@readVarIntArray PlayerInfoPacketData(uuid, name, gamemode, ping, hasDisplayName, displayName)
            }

            return PlayerInfoPacket(action, data)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: PlayerInfoPacket) {
            output.writeVarInt(value.action)

            output.writeVarIntArray(value.data) { arrayValue, arrayOutput ->
                arrayOutput.writeUUID(arrayValue.uuid)
                when (value.action) {
                    0 -> arrayOutput.writeString(arrayValue.name!!)
                    else -> {}
                }
                when (value.action) {
                    0 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    1 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    else -> {}
                }
                when (value.action) {
                    0 -> arrayOutput.writeVarInt(arrayValue.ping!!)
                    2 -> arrayOutput.writeVarInt(arrayValue.ping!!)
                    else -> {}
                }
                arrayOutput.writeBoolean(arrayValue.hasDisplayName)
                when (value.action) {
                    0 -> if (arrayValue.hasDisplayName) arrayOutput.writeString(arrayValue.displayName!!)
                    3 -> if (arrayValue.hasDisplayName) arrayOutput.writeString(arrayValue.displayName!!)
                    else -> {}
                }
            }
        }
    }
}

/**
 * PlayerInfoPacketData
 *
 * @param uuid uuid
 * @param name name
 * @param gamemode gamemode
 * @param ping ping
 * @param hasDisplayName displayName is present
 * @param displayName displayName
*/
data class PlayerInfoPacketData(
    val uuid: UUID,
    val name: String?,
    val gamemode: Int?, // varint
    val ping: Int?, // varint
    val hasDisplayName: Boolean,
    val displayName: String?,
)