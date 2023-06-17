package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Border Warning Delay | 0x46 | play | clientbound
 *
 * @param warningTime warningTime
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Border_Warning_Delay">https://wiki.vg/Protocol#Set_Border_Warning_Delay</a>
 */

data class WorldBorderWarningDelayPacket(
    val warningTime: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<WorldBorderWarningDelayPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): WorldBorderWarningDelayPacket {
            val warningTime = input.readVarInt()

            return WorldBorderWarningDelayPacket(warningTime)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: WorldBorderWarningDelayPacket) {
            output.writeVarInt(value.warningTime)
        }
    }
}