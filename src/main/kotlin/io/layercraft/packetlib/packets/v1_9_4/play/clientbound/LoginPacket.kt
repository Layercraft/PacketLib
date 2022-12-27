package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Join Game | 0x23 | play | clientbound
 *
 * @property entityId entityId
 * @property gameMode gameMode
 * @property dimension dimension
 * @property difficulty difficulty
 * @property maxPlayers maxPlayers
 * @property levelType levelType
 * @property reducedDebugInfo reducedDebugInfo
 * @see <a href="https://wiki.vg/Protocol#Join_Game">https://wiki.vg/Protocol#Join_Game</a>
 */

@MinecraftPacket(packetId = 0x23, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class LoginPacket(
    val entityId: Int,
    val gameMode: UByte,
    val dimension: Int,
    val difficulty: UByte,
    val maxPlayers: UByte,
    val levelType: String,
    val reducedDebugInfo: Boolean,
) : ClientBoundPacket {

    companion object : PacketSerializer<LoginPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): LoginPacket {
            val entityId = input.readInt()
            val gameMode = input.readUByte()
            val dimension = input.readInt()
            val difficulty = input.readUByte()
            val maxPlayers = input.readUByte()
            val levelType = input.readString()
            val reducedDebugInfo = input.readBoolean()

            return LoginPacket(entityId, gameMode, dimension, difficulty, maxPlayers, levelType, reducedDebugInfo)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: LoginPacket) {
            output.writeInt(value.entityId)
            output.writeUByte(value.gameMode)
            output.writeInt(value.dimension)
            output.writeUByte(value.difficulty)
            output.writeUByte(value.maxPlayers)
            output.writeString(value.levelType)
            output.writeBoolean(value.reducedDebugInfo)
        }
    }
}
