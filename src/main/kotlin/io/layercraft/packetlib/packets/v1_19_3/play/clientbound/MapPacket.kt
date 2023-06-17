package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Map Data | 0x25 | play | clientbound
 *
 * @param itemDamage itemDamage
 * @param scale scale
 * @param locked locked
 * @param columns columns
 * @param rows rows
 * @param x x
 * @param y y
 * @param data data
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Map_Data">https://wiki.vg/Protocol#Map_Data</a>
 */

data class MapPacket(
    val itemDamage: Int, // varint
    val scale: Byte,
    val locked: Boolean,
    val columns: UByte,
    val rows: UByte?,
    val x: UByte?,
    val y: UByte?,
    val data: ByteArray?,
) : ClientBoundPacket {
    companion object : PacketSerializer<MapPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): MapPacket {
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
            val data = when (columns.toInt()) {
                0 -> null
                else -> input.readVarIntByteArray()
            }

            return MapPacket(itemDamage, scale, locked, columns, rows, x, y, data)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: MapPacket) {
            output.writeVarInt(value.itemDamage)
            output.writeByte(value.scale)
            output.writeBoolean(value.locked)
            output.writeUByte(value.columns)
            when (value.columns.toInt()) {
                else -> output.writeUByte(value.rows!!)
            }
            when (value.columns.toInt()) {
                else -> output.writeUByte(value.x!!)
            }
            when (value.columns.toInt()) {
                else -> output.writeUByte(value.y!!)
            }
            when (value.columns.toInt()) {
                else -> output.writeVarIntByteArray(value.data!!)
            }
        }
    }
}