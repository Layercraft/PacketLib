package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Combat Death | 0x36 | play | clientbound
 *
 * @property playerId playerId
 * @property entityId entityId
 * @property message message
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Combat_Death">https://wiki.vg/Protocol#Combat_Death</a>
 */

@MinecraftPacket(id = 0x36, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class DeathCombatEventPacket(
    val playerId: Int, // varint
    val entityId: Int,
    val message: String,
) : ClientBoundPacket {

    companion object : PacketSerializer<DeathCombatEventPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): DeathCombatEventPacket {
            val playerId = input.readVarInt()
            val entityId = input.readInt()
            val message = input.readString()

            return DeathCombatEventPacket(playerId, entityId, message)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: DeathCombatEventPacket) {
            output.writeVarInt(value.playerId)
            output.writeInt(value.entityId)
            output.writeString(value.message)
        }
    }
}