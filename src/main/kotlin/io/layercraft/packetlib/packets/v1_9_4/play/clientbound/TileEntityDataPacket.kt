package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.Position

/**
 * Update Block Entity | 0x09 | play | clientbound
 *
 * @property location location
 * @property action action
 * @property nbtData nbtData
 * @see <a href="https://wiki.vg/Protocol#Update_Block_Entity">https://wiki.vg/Protocol#Update_Block_Entity</a>
 */

@MinecraftPacket(id = 0x09, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class TileEntityDataPacket(
    val location: Position,
    val action: UByte,
    val nbtData: ByteArray,
) : ClientBoundPacket {

    companion object : PacketSerializer<TileEntityDataPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): TileEntityDataPacket {
            val location = input.readPosition()
            val action = input.readUByte()
            val nbtData = input.readNBT()

            return TileEntityDataPacket(location, action, nbtData)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: TileEntityDataPacket) {
            output.writePosition(value.location)
            output.writeUByte(value.action)
            output.writeBytes(value.nbtData)
        }
    }
}
