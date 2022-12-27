package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Respawn | 0x33 | play | clientbound
 *
 * @property dimension dimension
 * @property difficulty difficulty
 * @property gamemode gamemode
 * @property levelType levelType
 * @see <a href="https://wiki.vg/Protocol#Respawn">https://wiki.vg/Protocol#Respawn</a>
 */

@MinecraftPacket(id = 0x33, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class RespawnPacket(
    val dimension: Int,
    val difficulty: UByte,
    val gamemode: UByte,
    val levelType: String,
) : ClientBoundPacket {

    companion object : PacketSerializer<RespawnPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): RespawnPacket {
            val dimension = input.readInt()
            val difficulty = input.readUByte()
            val gamemode = input.readUByte()
            val levelType = input.readString()

            return RespawnPacket(dimension, difficulty, gamemode, levelType)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: RespawnPacket) {
            output.writeInt(value.dimension)
            output.writeUByte(value.difficulty)
            output.writeUByte(value.gamemode)
            output.writeString(value.levelType)
        }
    }
}
