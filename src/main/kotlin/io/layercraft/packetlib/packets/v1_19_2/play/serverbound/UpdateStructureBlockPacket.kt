package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.Position

/**
 * Use Item | 0x2d | play | serverbound
 *
 * @property location location
 * @property action action
 * @property mode mode
 * @property name name
 * @property offset_x offset_x
 * @property offset_y offset_y
 * @property offset_z offset_z
 * @property size_x size_x
 * @property size_y size_y
 * @property size_z size_z
 * @property mirror mirror
 * @property rotation rotation
 * @property metadata metadata
 * @property integrity integrity
 * @property seed seed
 * @property flags flags
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x2d, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class UpdateStructureBlockPacket(
    val location: Position,
    val action: Int, // varint
    val mode: Int, // varint
    val name: String,
    val offset_x: Byte,
    val offset_y: Byte,
    val offset_z: Byte,
    val size_x: Byte,
    val size_y: Byte,
    val size_z: Byte,
    val mirror: Int, // varint
    val rotation: Int, // varint
    val metadata: String,
    val integrity: Float,
    val seed: Int, // varint
    val flags: UByte,
) : ServerBoundPacket {

    companion object : PacketSerializer<UpdateStructureBlockPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): UpdateStructureBlockPacket {
            val location = input.readPosition()
            val action = input.readVarInt()
            val mode = input.readVarInt()
            val name = input.readString()
            val offset_x = input.readByte()
            val offset_y = input.readByte()
            val offset_z = input.readByte()
            val size_x = input.readByte()
            val size_y = input.readByte()
            val size_z = input.readByte()
            val mirror = input.readVarInt()
            val rotation = input.readVarInt()
            val metadata = input.readString()
            val integrity = input.readFloat()
            val seed = input.readVarInt()
            val flags = input.readUByte()

            return UpdateStructureBlockPacket(location, action, mode, name, offset_x, offset_y, offset_z, size_x, size_y, size_z, mirror, rotation, metadata, integrity, seed, flags)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: UpdateStructureBlockPacket) {
            output.writePosition(value.location)
            output.writeVarInt(value.action)
            output.writeVarInt(value.mode)
            output.writeString(value.name)
            output.writeByte(value.offset_x)
            output.writeByte(value.offset_y)
            output.writeByte(value.offset_z)
            output.writeByte(value.size_x)
            output.writeByte(value.size_y)
            output.writeByte(value.size_z)
            output.writeVarInt(value.mirror)
            output.writeVarInt(value.rotation)
            output.writeString(value.metadata)
            output.writeFloat(value.integrity)
            output.writeVarInt(value.seed)
            output.writeUByte(value.flags)
        }
    }
}