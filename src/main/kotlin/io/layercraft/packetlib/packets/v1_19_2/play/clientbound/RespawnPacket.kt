package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x3e | play | clientbound
 *
 * @property dimension dimension
 * @property worldName worldName
 * @property hashedSeed hashedSeed
 * @property gamemode gamemode
 * @property previousGamemode previousGamemode
 * @property isDebug isDebug
 * @property isFlat isFlat
 * @property copyMetadata copyMetadata
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
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
) : ClientBoundPacket {

    companion object : PacketSerializer<RespawnPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): RespawnPacket {
            val dimension = input.readString()
            val worldName = input.readString()
            val hashedSeed = input.readLong()
            val gamemode = input.readByte()
            val previousGamemode = input.readUByte()
            val isDebug = input.readBoolean()
            val isFlat = input.readBoolean()
            val copyMetadata = input.readBoolean()

            return RespawnPacket(dimension, worldName, hashedSeed, gamemode, previousGamemode, isDebug, isFlat, copyMetadata)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: RespawnPacket) {
            output.writeString(value.dimension)
            output.writeString(value.worldName)
            output.writeLong(value.hashedSeed)
            output.writeByte(value.gamemode)
            output.writeUByte(value.previousGamemode)
            output.writeBoolean(value.isDebug)
            output.writeBoolean(value.isFlat)
            output.writeBoolean(value.copyMetadata)
        }
    }
}