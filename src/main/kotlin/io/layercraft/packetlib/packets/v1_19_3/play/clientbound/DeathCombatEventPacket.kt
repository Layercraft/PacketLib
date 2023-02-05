package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Combat Death | 0x34 | play | clientbound
 *
 * @param playerId playerId
 * @param entityId entityId
 * @param message message
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17964#Combat_Death">https://wiki.vg/Protocol#Combat_Death</a>
 */

@MinecraftPacket(id = 0x34, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class DeathCombatEventPacket(
    val playerId: Int, // varint
    val entityId: Int,
    val message: String,
) : ClientBoundPacket {
    companion object : PacketSerializer<DeathCombatEventPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): DeathCombatEventPacket {
            val playerId = input.readVarInt()
            val entityId = input.readInt()
            val message = input.readString()

            return DeathCombatEventPacket(playerId, entityId, message)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: DeathCombatEventPacket) {
            output.writeVarInt(value.playerId)
            output.writeInt(value.entityId)
            output.writeString(value.message)
        }
    }
}