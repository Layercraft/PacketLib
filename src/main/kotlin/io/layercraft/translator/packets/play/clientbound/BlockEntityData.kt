package io.layercraft.translator.packets.play.clientbound

import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.*
import io.layercraft.translator.types.Position
import io.layercraft.translator.utils.mc

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
data class BlockEntityData(
    val location: Position,
    val type: Int,
    val nbtData: ByteArray //TODO TO OWN NBT
): ClientBoundPacket{
    companion object: PacketSerializer<BlockEntityData>{
        override fun serialize(input: Input): BlockEntityData {
            val location = input.mc.readPosition()
            val type = input.mc.readVarInt()
            val nbt = input.mc.readRemainingByteArray()

            return BlockEntityData(location, type, nbt)
        }

        override fun deserialize(output: Output, value: BlockEntityData) {
            output.mc.writePosition(value.location)
            output.mc.writeVarInt(value.type)
            output.mc.writeRemainingByteArray(value.nbtData)
        }
    }
}
