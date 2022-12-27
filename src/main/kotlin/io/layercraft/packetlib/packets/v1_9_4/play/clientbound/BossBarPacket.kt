package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.util.UUID 

/**
 * Encryption Response | 0x0c | play | clientbound
 *
 * @property entityUUID entityUUID
 * @property action action
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(packetId = 0x0c, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class BossBarPacket(
    val entityUUID: UUID,
    val action: Int, // varint
) : ClientBoundPacket {

    companion object : PacketSerializer<BossBarPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): BossBarPacket {
            val entityUUID = input.readUUID()
            val action = input.readVarInt()

            return BossBarPacket(entityUUID, action)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: BossBarPacket) {
            output.writeUUID(value.entityUUID)
            output.writeVarInt(value.action)
        }
    }
}
