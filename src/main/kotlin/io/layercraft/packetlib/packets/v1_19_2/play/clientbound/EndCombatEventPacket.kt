package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * End Combat | 0x34 | play | clientbound
 *
 * @param duration duration
 * @param entityId entityId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#End_Combat">https://wiki.vg/Protocol#End_Combat</a>
 */

@MinecraftPacket(id = 0x34, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class EndCombatEventPacket(
    val duration: Int, // varint
    val entityId: Int,
) : ClientBoundPacket {
    companion object : PacketSerializer<EndCombatEventPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): EndCombatEventPacket {
            val duration = input.readVarInt()
            val entityId = input.readInt()

            return EndCombatEventPacket(duration, entityId)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: EndCombatEventPacket) {
            output.writeVarInt(value.duration)
            output.writeInt(value.entityId)
        }
    }
}