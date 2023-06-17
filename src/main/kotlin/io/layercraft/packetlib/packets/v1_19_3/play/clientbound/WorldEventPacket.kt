package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer
import io.layercraft.packetlib.types.Position
/**
 * World Event | 0x21 | play | clientbound
 *
 * @param effectId effectId
 * @param location location
 * @param data data
 * @param global global
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#World_Event">https://wiki.vg/Protocol#World_Event</a>
 */

data class WorldEventPacket(
    val effectId: Int,
    val location: Position,
    val data: Int,
    val global: Boolean,
) : ClientBoundPacket {
    companion object : PacketSerializer<WorldEventPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): WorldEventPacket {
            val effectId = input.readInt()
            val location = input.readPosition()
            val data = input.readInt()
            val global = input.readBoolean()

            return WorldEventPacket(effectId, location, data, global)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: WorldEventPacket) {
            output.writeInt(value.effectId)
            output.writePosition(value.location)
            output.writeInt(value.data)
            output.writeBoolean(value.global)
        }
    }
}