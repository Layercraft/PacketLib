package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Statistics | 0x07 | play | clientbound
 *

 * @see <a href="https://wiki.vg/Protocol#Statistics">https://wiki.vg/Protocol#Statistics</a>
 */

@MinecraftPacket(id = 0x07, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
 class StatisticsPacket(

) : ClientBoundPacket {

    companion object : PacketSerializer<StatisticsPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): StatisticsPacket {

            return StatisticsPacket()
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: StatisticsPacket) {

        }
    }
}
