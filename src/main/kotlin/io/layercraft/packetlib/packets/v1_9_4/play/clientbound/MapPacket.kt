package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Map | 0x24 | play | clientbound
 *
 * @property itemDamage itemDamage
 * @property scale scale
 * @property trackingPosition trackingPosition
 * @property columns columns
 * @see <a href="https://wiki.vg/Protocol#Map">https://wiki.vg/Protocol#Map</a>
 */

@MinecraftPacket(id = 0x24, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class MapPacket(
    val itemDamage: Int, // varint
    val scale: Byte,
    val trackingPosition: Boolean,
    val columns: Byte,
) : ClientBoundPacket {

    companion object : PacketSerializer<MapPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): MapPacket {
            val itemDamage = input.readVarInt()
            val scale = input.readByte()
            val trackingPosition = input.readBoolean()
            val columns = input.readByte()

            return MapPacket(itemDamage, scale, trackingPosition, columns)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: MapPacket) {
            output.writeVarInt(value.itemDamage)
            output.writeByte(value.scale)
            output.writeBoolean(value.trackingPosition)
            output.writeByte(value.columns)
        }
    }
}
