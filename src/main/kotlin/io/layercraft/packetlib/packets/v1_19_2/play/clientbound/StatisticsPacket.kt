package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Award Statistics | 0x04 | play | clientbound
 *

 * @see <a href="https://wiki.vg/Protocol#Award_Statistics">https://wiki.vg/Protocol#Award_Statistics</a>
 */

@MinecraftPacket(packetId = 0x04, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
class StatisticsPacket() : ClientBoundPacket {

    companion object : PacketSerializer<StatisticsPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): StatisticsPacket {
            return StatisticsPacket()
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: StatisticsPacket) {
        }
    }
}