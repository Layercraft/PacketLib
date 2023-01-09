package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Program Command Block Minecart | 0x2a | play | serverbound
 *
 * @param entityId entityId
 * @param command command
 * @param trackOutput track_output
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17932#Program_Command_Block_Minecart">https://wiki.vg/Protocol#Program_Command_Block_Minecart</a>
 */

@MinecraftPacket(id = 0x2a, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class UpdateCommandBlockMinecartPacket(
    val entityId: Int, // varint
    val command: String,
    val trackOutput: Boolean,
) : ServerBoundPacket {
    companion object : PacketSerializer<UpdateCommandBlockMinecartPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): UpdateCommandBlockMinecartPacket {
            val entityId = input.readVarInt()
            val command = input.readString()
            val trackOutput = input.readBoolean()

            return UpdateCommandBlockMinecartPacket(entityId, command, trackOutput)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: UpdateCommandBlockMinecartPacket) {
            output.writeVarInt(value.entityId)
            output.writeString(value.command)
            output.writeBoolean(value.trackOutput)
        }
    }
}