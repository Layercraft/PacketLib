package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer
import io.layercraft.packetlib.types.Position
/**
 * Program Command Block | 0x29 | play | serverbound
 *
 * @param location location
 * @param command command
 * @param mode mode
 * @param flags flags
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Program_Command_Block">https://wiki.vg/Protocol#Program_Command_Block</a>
 */

data class UpdateCommandBlockPacket(
    val location: Position,
    val command: String,
    val mode: Int, // varint
    val flags: UByte,
) : ServerBoundPacket {
    companion object : PacketSerializer<UpdateCommandBlockPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): UpdateCommandBlockPacket {
            val location = input.readPosition()
            val command = input.readString()
            val mode = input.readVarInt()
            val flags = input.readUByte()

            return UpdateCommandBlockPacket(location, command, mode, flags)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: UpdateCommandBlockPacket) {
            output.writePosition(value.location)
            output.writeString(value.command)
            output.writeVarInt(value.mode)
            output.writeUByte(value.flags)
        }
    }
}