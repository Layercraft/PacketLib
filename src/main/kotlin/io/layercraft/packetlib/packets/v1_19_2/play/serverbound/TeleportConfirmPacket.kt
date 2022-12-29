package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Confirm Teleportation | 0x00 | play | serverbound
 *
 * @property teleportId teleportId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Confirm_Teleportation">https://wiki.vg/Protocol#Confirm_Teleportation</a>
 */

@MinecraftPacket(id = 0x00, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class TeleportConfirmPacket(
    val teleportId: Int, // varint
) : ServerBoundPacket {
    companion object : PacketSerializer<TeleportConfirmPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): TeleportConfirmPacket {
            val teleportId = input.readVarInt()

            return TeleportConfirmPacket(teleportId)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: TeleportConfirmPacket) {
            output.writeVarInt(value.teleportId)
        }
    }
}