package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.Position

/**
 * Block Entity Data | 0x07 | play | clientbound
 *
 * @property location location
 * @property action action
 * @property nbtData nbtData
 * @see <a href="https://wiki.vg/Protocol#Block_Entity_Data">https://wiki.vg/Protocol#Block_Entity_Data</a>
 */

@MinecraftPacket(packetId = 0x07, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class TileEntityDataPacket(
    val location: Position,
    val action: Int, // varint
    val nbtData: ByteArray,
) : ClientBoundPacket {

    companion object : PacketSerializer<TileEntityDataPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): TileEntityDataPacket {
            val location = input.readPosition()
            val action = input.readVarInt()
            val nbtData = input.readNBT()

            return TileEntityDataPacket(location, action, nbtData)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: TileEntityDataPacket) {
            output.writePosition(value.location)
            output.writeVarInt(value.action)
            output.writeBytes(value.nbtData)
        }
    }
}