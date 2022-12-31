package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.Position
/**
 * Program Command Block | 0x29 | play | serverbound
 *
 * @property location location
 * @property command command
 * @property mode mode
 * @property flags flags
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Program_Command_Block">https://wiki.vg/Protocol#Program_Command_Block</a>
 */

@MinecraftPacket(id = 0x29, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class UpdateCommandBlockPacket(
    val location: Position,
    val command: String,
    val mode: Int, // varint
    val flags: UByte,
) : ServerBoundPacket {
    companion object : PacketSerializer<UpdateCommandBlockPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): UpdateCommandBlockPacket {
            val location = input.readPosition()
            val command = input.readString()
            val mode = input.readVarInt()
            val flags = input.readUByte()

            return UpdateCommandBlockPacket(location, command, mode, flags)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: UpdateCommandBlockPacket) {
            output.writePosition(value.location)
            output.writeString(value.command)
            output.writeVarInt(value.mode)
            output.writeUByte(value.flags)
        }
    }
}
