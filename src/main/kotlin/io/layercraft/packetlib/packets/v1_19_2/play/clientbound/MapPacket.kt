package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Map Data | 0x26 | play | clientbound
 *
 * @property itemDamage itemDamage
 * @property scale scale
 * @property locked locked
 * @property columns columns
 * @property rows rows
 * @property x x
 * @property y y
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Map_Data">https://wiki.vg/Protocol#Map_Data</a>
 */

@MinecraftPacket(id = 0x26, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class MapPacket(
    val itemDamage: Int, // varint
    val scale: Byte,
    val locked: Boolean,
    val columns: UByte,
    val rows: UByte?,
    val x: UByte?,
    val y: UByte?,
) : ClientBoundPacket {
    companion object : PacketSerializer<MapPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): MapPacket {
            val itemDamage = input.readVarInt()
            val scale = input.readByte()
            val locked = input.readBoolean()
            val columns = input.readUByte()
            val rows = when (columns.toInt()) {
                0 -> null
                else -> input.readUByte()
            }
            val x = when (columns.toInt()) {
                0 -> null
                else -> input.readUByte()
            }
            val y = when (columns.toInt()) {
                0 -> null
                else -> input.readUByte()
            }

            return MapPacket(itemDamage, scale, locked, columns, rows, x, y)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: MapPacket) {
            output.writeVarInt(value.itemDamage)
            output.writeByte(value.scale)
            output.writeBoolean(value.locked)
            output.writeUByte(value.columns)
            when (value.columns) {
                else -> output.writeUByte(value.rows!!)
            }
            when (value.columns) {
                else -> output.writeUByte(value.x!!)
            }
            when (value.columns) {
                else -> output.writeUByte(value.y!!)
            }
        }
    }
}