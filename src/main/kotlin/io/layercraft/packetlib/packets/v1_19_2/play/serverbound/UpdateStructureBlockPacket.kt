package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.Position
/**
 * Program Structure Block | 0x2d | play | serverbound
 *
 * @property location location
 * @property action action
 * @property mode mode
 * @property name name
 * @property offsetX offset_x
 * @property offsetY offset_y
 * @property offsetZ offset_z
 * @property sizeX size_x
 * @property sizeY size_y
 * @property sizeZ size_z
 * @property mirror mirror
 * @property rotation rotation
 * @property metadata metadata
 * @property integrity integrity
 * @property seed seed
 * @property flags flags
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Program_Structure_Block">https://wiki.vg/Protocol#Program_Structure_Block</a>
 */

@MinecraftPacket(id = 0x2d, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
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
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): UpdateStructureBlockPacket {
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

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: UpdateStructureBlockPacket) {
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