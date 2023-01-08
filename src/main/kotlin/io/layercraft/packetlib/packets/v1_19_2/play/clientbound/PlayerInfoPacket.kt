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
                val properties = when (action) {
                    0 -> arrayInput.readVarIntArray { arrayInput1 ->
                        val name = arrayInput1.readString()
                        val value = arrayInput1.readString()
                        val hasSignature = arrayInput1.readBoolean()
                        val signature = if (hasSignature) arrayInput1.readString() else null

                        return@readVarIntArray PlayerInfoPacketProperties(name, value, hasSignature, signature)
                    }
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
                val hasDisplayName = when (action) {
                    0 -> arrayInput.readBoolean()
                    3 -> arrayInput.readBoolean()
                    else -> null
                }
                val displayName = when (action) {
                    0 -> if (hasDisplayName!!) arrayInput.readString() else null
                    3 -> if (hasDisplayName!!) arrayInput.readString() else null
                    else -> null
                }
                val hasTimestamp = when (action) {
                    0 -> arrayInput.readBoolean()
                    else -> null
                }
                val timestamp = when (action) {
                    0 -> if (hasTimestamp!!) arrayInput.readLong() else null
                    else -> null
                }
                val hasPublicKey = when (action) {
                    0 -> arrayInput.readBoolean()
                    else -> null
                }
                val publicKey = when (action) {
                    0 -> if (hasPublicKey!!) arrayInput.readVarIntByteArray() else null
                    else -> null
                }
                val hasSignature = when (action) {
                    0 -> arrayInput.readBoolean()
                    else -> null
                }
                val signature = when (action) {
                    0 -> if (hasSignature!!) arrayInput.readVarIntByteArray() else null
                    else -> null
                }

                return@readVarIntArray PlayerInfoPacketData(uuid, name, properties, gamemode, ping, hasDisplayName, displayName, hasTimestamp, timestamp, hasPublicKey, publicKey, hasSignature, signature)
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
                    0 ->
                        arrayOutput.writeVarIntArray(arrayValue.properties!!) { arrayValue1, arrayOutput1 ->
                            arrayOutput1.writeString(arrayValue1.name)
                            arrayOutput1.writeString(arrayValue1.value)
                            arrayOutput1.writeBoolean(arrayValue1.hasSignature)
                            if (arrayValue1.hasSignature) arrayOutput1.writeString(arrayValue1.signature!!)
                        }

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
                when (value.action) {
                    0 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    3 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    else -> {}
                }
                when (value.action) {
                    0 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    3 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    else -> {}
                }
                when (value.action) {
                    0 -> arrayOutput.writeBoolean(arrayValue.hasTimestamp!!)
                    else -> {}
                }
                when (value.action) {
                    0 -> if (arrayValue.hasTimestamp!!) arrayOutput.writeLong(arrayValue.timestamp!!)
                    else -> {}
                }
                when (value.action) {
                    0 -> arrayOutput.writeBoolean(arrayValue.hasPublicKey!!)
                    else -> {}
                }
                when (value.action) {
                    0 -> if (arrayValue.hasPublicKey!!) arrayOutput.writeVarIntByteArray(arrayValue.publicKey!!)
                    else -> {}
                }
                when (value.action) {
                    0 -> arrayOutput.writeBoolean(arrayValue.hasSignature!!)
                    else -> {}
                }
                when (value.action) {
                    0 -> if (arrayValue.hasSignature!!) arrayOutput.writeVarIntByteArray(arrayValue.signature!!)
                    else -> {}
                }
            }
        }
    }
}

/**
 * PlayerInfoPacketProperties
 *
 * @param name name
 * @param value value
 * @param hasSignature signature is present
 * @param signature signature
*/
data class PlayerInfoPacketProperties(
    val name: String,
    val value: String,
    val hasSignature: Boolean,
    val signature: String?,
)

/**
 * PlayerInfoPacketData
 *
 * @param uuid uuid
 * @param name name
 * @param properties list of PlayerInfoPacketProperties
 * @param gamemode gamemode
 * @param ping ping
 * @param hasDisplayName hasDisplayName
 * @param displayName displayName
 * @param hasTimestamp hasTimestamp
 * @param timestamp timestamp
 * @param hasPublicKey hasPublicKey
 * @param publicKey publicKey
 * @param hasSignature hasSignature
 * @param signature signature
*/
data class PlayerInfoPacketData(
    val uuid: UUID,
    val name: String?,
    val properties: List<PlayerInfoPacketProperties>?,
    val gamemode: Int?, // varint
    val ping: Int?, // varint
    val hasDisplayName: Boolean?,
    val displayName: String?,
    val hasTimestamp: Boolean?,
    val timestamp: Long?,
    val hasPublicKey: Boolean?,
    val publicKey: ByteArray?,
    val hasSignature: Boolean?,
    val signature: ByteArray?,
)