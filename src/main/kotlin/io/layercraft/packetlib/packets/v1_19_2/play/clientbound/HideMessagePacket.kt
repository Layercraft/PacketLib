package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Hide Message | 0x18 | play | clientbound
 *

 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Hide_Message">https://wiki.vg/Protocol#Hide_Message</a>
 */

@MinecraftPacket(id = 0x18, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
class HideMessagePacket() : ClientBoundPacket {

    companion object : PacketSerializer<HideMessagePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): HideMessagePacket {
            return HideMessagePacket()
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: HideMessagePacket) {
        }
    }
}