package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Player On Ground | 0x16 | play | serverbound
 *
 * @param onGround onGround
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Player_On_Ground">https://wiki.vg/Protocol#Set_Player_On_Ground</a>
 */

data class FlyingPacket(
    val onGround: Boolean,
) : ServerBoundPacket {
    companion object : PacketSerializer<FlyingPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): FlyingPacket {
            val onGround = input.readBoolean()

            return FlyingPacket(onGround)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: FlyingPacket) {
            output.writeBoolean(value.onGround)
        }
    }
}