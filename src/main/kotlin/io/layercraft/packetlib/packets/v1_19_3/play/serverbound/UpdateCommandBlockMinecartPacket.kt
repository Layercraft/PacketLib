package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Program Command Block Minecart | 0x2a | play | serverbound
 *
 * @param entityId entityId
 * @param command command
 * @param trackOutput track_output
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Program_Command_Block_Minecart">https://wiki.vg/Protocol#Program_Command_Block_Minecart</a>
 */

data class UpdateCommandBlockMinecartPacket(
    val entityId: Int, // varint
    val command: String,
    val trackOutput: Boolean,
) : ServerBoundPacket {
    companion object : PacketSerializer<UpdateCommandBlockMinecartPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): UpdateCommandBlockMinecartPacket {
            val entityId = input.readVarInt()
            val command = input.readString()
            val trackOutput = input.readBoolean()

            return UpdateCommandBlockMinecartPacket(entityId, command, trackOutput)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: UpdateCommandBlockMinecartPacket) {
            output.writeVarInt(value.entityId)
            output.writeString(value.command)
            output.writeBoolean(value.trackOutput)
        }
    }
}