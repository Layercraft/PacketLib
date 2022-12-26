package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x2a | play | serverbound
 *
 * @property entityId entityId
 * @property command command
 * @property track_output track_output
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(packetId = 0x2a, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class UpdateCommandBlockMinecartPacket(
    val entityId: Int, // varint
    val command: String,
    val track_output: Boolean,
) : ServerBoundPacket {

    companion object : PacketSerializer<UpdateCommandBlockMinecartPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): UpdateCommandBlockMinecartPacket {
            val entityId = input.readVarInt()
            val command = input.readString()
            val track_output = input.readBoolean()

            return UpdateCommandBlockMinecartPacket(entityId, command, track_output)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: UpdateCommandBlockMinecartPacket) {
            output.writeVarInt(value.entityId)
            output.writeString(value.command)
            output.writeBoolean(value.track_output)
        }
    }
}