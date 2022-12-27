package io.layercraft.packetlib.packets.v1_9_4.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Client Status | 0x03 | play | serverbound
 *
 * @property actionId actionId
 * @see <a href="https://wiki.vg/Protocol#Client_Status">https://wiki.vg/Protocol#Client_Status</a>
 */

@MinecraftPacket(id = 0x03, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class ClientCommandPacket(
    val actionId: Int, // varint
) : ServerBoundPacket {

    companion object : PacketSerializer<ClientCommandPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): ClientCommandPacket {
            val actionId = input.readVarInt()

            return ClientCommandPacket(actionId)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: ClientCommandPacket) {
            output.writeVarInt(value.actionId)
        }
    }
}
