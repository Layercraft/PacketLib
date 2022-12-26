package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * End Combat | 0x31 | play | clientbound
 *
 * @property duration duration
 * @property entityId entityId
 * @see <a href="https://wiki.vg/Protocol#End_Combat">https://wiki.vg/Protocol#End_Combat</a>
 */

@MinecraftPacket(packetId = 0x31, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class EndCombatEventPacket(
    val duration: Int, // varint
    val entityId: Int,
) : ClientBoundPacket {

    companion object : PacketSerializer<EndCombatEventPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): EndCombatEventPacket {
            val duration = input.readVarInt()
            val entityId = input.readInt()

            return EndCombatEventPacket(duration, entityId)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: EndCombatEventPacket) {
            output.writeVarInt(value.duration)
            output.writeInt(value.entityId)
        }
    }
}