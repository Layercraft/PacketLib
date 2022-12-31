package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.Position
/**
 * Respawn | 0x3e | play | clientbound
 *
 * @property dimension dimension
 * @property worldName worldName
 * @property hashedSeed hashedSeed
 * @property gamemode gamemode
 * @property previousGamemode previousGamemode
 * @property isDebug isDebug
 * @property isFlat isFlat
 * @property copyMetadata copyMetadata
 * @property hasDeath death is present
 * @property dimensionName dimensionName
 * @property location location
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Respawn">https://wiki.vg/Protocol#Respawn</a>
 */

@MinecraftPacket(id = 0x3e, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class RespawnPacket(
    val dimension: String,
    val worldName: String,
    val hashedSeed: Long,
    val gamemode: Byte,
    val previousGamemode: UByte,
    val isDebug: Boolean,
    val isFlat: Boolean,
    val copyMetadata: Boolean,
    val hasDeath: Boolean,
    val dimensionName: String?,
    val location: Position?,
) : ClientBoundPacket {
    companion object : PacketSerializer<RespawnPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): RespawnPacket {
            val dimension = input.readString()
            val worldName = input.readString()
            val hashedSeed = input.readLong()
            val gamemode = input.readByte()
            val previousGamemode = input.readUByte()
            val isDebug = input.readBoolean()
            val isFlat = input.readBoolean()
            val copyMetadata = input.readBoolean()
            val hasDeath = input.readBoolean()
            val dimensionName = if (hasDeath) input.readString() else null
            val location = if (hasDeath) input.readPosition() else null

            return RespawnPacket(dimension, worldName, hashedSeed, gamemode, previousGamemode, isDebug, isFlat, copyMetadata, hasDeath, dimensionName, location)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: RespawnPacket) {
            output.writeString(value.dimension)
            output.writeString(value.worldName)
            output.writeLong(value.hashedSeed)
            output.writeByte(value.gamemode)
            output.writeUByte(value.previousGamemode)
            output.writeBoolean(value.isDebug)
            output.writeBoolean(value.isFlat)
            output.writeBoolean(value.copyMetadata)
            output.writeBoolean(value.hasDeath)
            if (value.hasDeath) output.writeString(value.dimensionName!!)
            if (value.hasDeath) output.writePosition(value.location!!)
        }
    }
}
