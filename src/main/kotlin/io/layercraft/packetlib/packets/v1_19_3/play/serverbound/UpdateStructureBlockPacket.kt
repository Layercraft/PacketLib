package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer
import io.layercraft.packetlib.types.Position
/**
 * Program Structure Block | 0x2d | play | serverbound
 *
 * @param location location
 * @param action action
 * @param mode mode
 * @param name name
 * @param offsetX offset_x
 * @param offsetY offset_y
 * @param offsetZ offset_z
 * @param sizeX size_x
 * @param sizeY size_y
 * @param sizeZ size_z
 * @param mirror mirror
 * @param rotation rotation
 * @param metadata metadata
 * @param integrity integrity
 * @param seed seed
 * @param flags flags
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Program_Structure_Block">https://wiki.vg/Protocol#Program_Structure_Block</a>
 */

data class UpdateStructureBlockPacket(
    val location: Position,
    val action: Int, // varint
    val mode: Int, // varint
    val name: String,
    val offsetX: Byte,
    val offsetY: Byte,
    val offsetZ: Byte,
    val sizeX: Byte,
    val sizeY: Byte,
    val sizeZ: Byte,
    val mirror: Int, // varint
    val rotation: Int, // varint
    val metadata: String,
    val integrity: Float,
    val seed: Int, // varint
    val flags: UByte,
) : ServerBoundPacket {
    companion object : PacketSerializer<UpdateStructureBlockPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): UpdateStructureBlockPacket {
            val location = input.readPosition()
            val action = input.readVarInt()
            val mode = input.readVarInt()
            val name = input.readString()
            val offsetX = input.readByte()
            val offsetY = input.readByte()
            val offsetZ = input.readByte()
            val sizeX = input.readByte()
            val sizeY = input.readByte()
            val sizeZ = input.readByte()
            val mirror = input.readVarInt()
            val rotation = input.readVarInt()
            val metadata = input.readString()
            val integrity = input.readFloat()
            val seed = input.readVarInt()
            val flags = input.readUByte()

            return UpdateStructureBlockPacket(location, action, mode, name, offsetX, offsetY, offsetZ, sizeX, sizeY, sizeZ, mirror, rotation, metadata, integrity, seed, flags)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: UpdateStructureBlockPacket) {
            output.writePosition(value.location)
            output.writeVarInt(value.action)
            output.writeVarInt(value.mode)
            output.writeString(value.name)
            output.writeByte(value.offsetX)
            output.writeByte(value.offsetY)
            output.writeByte(value.offsetZ)
            output.writeByte(value.sizeX)
            output.writeByte(value.sizeY)
            output.writeByte(value.sizeZ)
            output.writeVarInt(value.mirror)
            output.writeVarInt(value.rotation)
            output.writeString(value.metadata)
            output.writeFloat(value.integrity)
            output.writeVarInt(value.seed)
            output.writeUByte(value.flags)
        }
    }
}