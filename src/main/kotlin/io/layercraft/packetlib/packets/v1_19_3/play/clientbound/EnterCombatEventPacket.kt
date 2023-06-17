package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Enter Combat | 0x33 | play | clientbound
 *

 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Enter_Combat">https://wiki.vg/Protocol#Enter_Combat</a>
 */

class EnterCombatEventPacket() : ClientBoundPacket {
    companion object : PacketSerializer<EnterCombatEventPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): EnterCombatEventPacket {
            return EnterCombatEventPacket()
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: EnterCombatEventPacket) {
        }
    }
}