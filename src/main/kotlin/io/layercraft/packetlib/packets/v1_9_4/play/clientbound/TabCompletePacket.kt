package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Encryption Response | 0x0e | play | clientbound
 *

 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(packetId = 0x0e, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
 class TabCompletePacket(

) : ClientBoundPacket {

    companion object : PacketSerializer<TabCompletePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): TabCompletePacket {

            return TabCompletePacket()
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: TabCompletePacket) {

        }
    }
}
