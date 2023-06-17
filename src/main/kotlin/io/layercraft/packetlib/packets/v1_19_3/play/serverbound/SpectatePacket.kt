package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer
import java.util.UUID
/**
 * Teleport To Entity | 0x30 | play | serverbound
 *
 * @param target target
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Teleport_To_Entity">https://wiki.vg/Protocol#Teleport_To_Entity</a>
 */

data class SpectatePacket(
    val target: UUID,
) : ServerBoundPacket {
    companion object : PacketSerializer<SpectatePacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): SpectatePacket {
            val target = input.readUUID()

            return SpectatePacket(target)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: SpectatePacket) {
            output.writeUUID(value.target)
        }
    }
}