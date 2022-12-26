package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Map Data | 0x24 | play | clientbound
 *
 * @property itemDamage itemDamage
 * @property scale scale
 * @property locked locked
 * @property columns columns
 * @see <a href="https://wiki.vg/Protocol#Map_Data">https://wiki.vg/Protocol#Map_Data</a>
 */

@MinecraftPacket(packetId = 0x24, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class MapPacket(
    val itemDamage: Int, // varint
    val scale: Byte,
    val locked: Boolean,
    val columns: UByte,
) : ClientBoundPacket {

    companion object : PacketSerializer<MapPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): MapPacket {
            val itemDamage = input.readVarInt()
            val scale = input.readByte()
            val locked = input.readBoolean()
            val columns = input.readUByte()

            return MapPacket(itemDamage, scale, locked, columns)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: MapPacket) {
            output.writeVarInt(value.itemDamage)
            output.writeByte(value.scale)
            output.writeBoolean(value.locked)
            output.writeUByte(value.columns)
        }
    }
}