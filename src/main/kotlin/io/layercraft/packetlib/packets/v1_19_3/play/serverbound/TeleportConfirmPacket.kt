package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Confirm Teleportation | 0x00 | play | serverbound
 *
 * @param teleportId teleportId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Confirm_Teleportation">https://wiki.vg/Protocol#Confirm_Teleportation</a>
 */

data class TeleportConfirmPacket(
    val teleportId: Int, // varint
) : ServerBoundPacket {
    companion object : PacketSerializer<TeleportConfirmPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): TeleportConfirmPacket {
            val teleportId = input.readVarInt()

            return TeleportConfirmPacket(teleportId)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: TeleportConfirmPacket) {
            output.writeVarInt(value.teleportId)
        }
    }
}