package io.layercraft.translator.packets.play.clientbound

import io.layercraft.translator.packets.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.translator.types.Position

/**
 * Block entity data | 0x07 | play | client-bound
 *
 * Sets the block entity associated with the block at the given location.
 *
 * @property location
 * @property type The type of the block entity
 * @property nbtData Data to set. Maybe a TAG_END (0), in which case the block entity at the given location is removed (though this is not required since the client will remove the block entity automatically on chunk unload or block removal).
 * @see <a href="https://wiki.vg/Protocol#Block_Entity_Data">https://wiki.vg/Protocol#Block_Entity_Data</a>
 */
@MinecraftPacket(0x07, PacketState.PLAY, PacketDirection.CLIENTBOUND)
data class BlockEntityDataPacket(
    val location: Position,
    val type: Int,
    val nbtData: ByteArray,
) : ClientBoundPacket {
    companion object : PacketSerializer<BlockEntityDataPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): BlockEntityDataPacket {
            val location = input.readPosition()
            val type = input.readVarInt()
            val nbt = input.readRemainingByteArray()

            return BlockEntityDataPacket(location, type, nbt)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: BlockEntityDataPacket) {
            output.writePosition(value.location)
            output.writeVarInt(value.type)
            output.writeRemainingByteArray(value.nbtData)
        }
    }
}