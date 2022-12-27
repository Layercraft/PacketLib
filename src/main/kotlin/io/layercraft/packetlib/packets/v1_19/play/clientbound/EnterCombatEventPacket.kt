package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Enter Combat | 0x32 | play | clientbound
 *

 * @see <a href="https://wiki.vg/Protocol#Enter_Combat">https://wiki.vg/Protocol#Enter_Combat</a>
 */

@MinecraftPacket(id = 0x32, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
class EnterCombatEventPacket() : ClientBoundPacket {

    companion object : PacketSerializer<EnterCombatEventPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): EnterCombatEventPacket {
            return EnterCombatEventPacket()
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: EnterCombatEventPacket) {
        }
    }
}