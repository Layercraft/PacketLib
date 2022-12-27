package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Encryption Response | 0x2c | play | clientbound
 *
 * @property event event
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(id = 0x2c, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class CombatEventPacket(
    val event: Int, // varint
) : ClientBoundPacket {

    companion object : PacketSerializer<CombatEventPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): CombatEventPacket {
            val event = input.readVarInt()

            return CombatEventPacket(event)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: CombatEventPacket) {
            output.writeVarInt(value.event)
        }
    }
}
