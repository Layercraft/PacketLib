package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.Position
import io.layercraft.packetlib.types.NBT
/**
 * Block Entity Data | 0x07 | play | clientbound
 *
 * @property location location
 * @property action action
 * @property nbtData nbtData
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Block_Entity_Data">https://wiki.vg/Protocol#Block_Entity_Data</a>
 */

@MinecraftPacket(id = 0x07, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class TileEntityDataPacket(
    val location: Position,
    val action: Int, // varint
    val nbtData: NBT,
) : ClientBoundPacket {
    companion object : PacketSerializer<TileEntityDataPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): TileEntityDataPacket {
            val location = input.readPosition()
            val action = input.readVarInt()
            val nbtData = input.readNbt()

            return TileEntityDataPacket(location, action, nbtData)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: TileEntityDataPacket) {
            output.writePosition(value.location)
            output.writeVarInt(value.action)
            output.writeNbt(value.nbtData)
        }
    }
}
