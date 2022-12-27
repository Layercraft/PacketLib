package io.layercraft.packetlib.packets.v1_9_4.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Teleport Confirm | 0x00 | play | serverbound
 *
 * @property teleportId teleportId
 * @see <a href="https://wiki.vg/Protocol#Teleport_Confirm">https://wiki.vg/Protocol#Teleport_Confirm</a>
 */

@MinecraftPacket(packetId = 0x00, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class TeleportConfirmPacket(
    val teleportId: Int, // varint
) : ServerBoundPacket {

    companion object : PacketSerializer<TeleportConfirmPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): TeleportConfirmPacket {
            val teleportId = input.readVarInt()

            return TeleportConfirmPacket(teleportId)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: TeleportConfirmPacket) {
            output.writeVarInt(value.teleportId)
        }
    }
}
