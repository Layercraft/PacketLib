package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Client Command | 0x07 | play | serverbound
 *
 * @param actionId actionId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Client_Command">https://wiki.vg/Protocol#Client_Command</a>
 */

@MinecraftPacket(id = 0x07, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class ClientCommandPacket(
    val actionId: Int, // varint
) : ServerBoundPacket {
    companion object : PacketSerializer<ClientCommandPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): ClientCommandPacket {
            val actionId = input.readVarInt()

            return ClientCommandPacket(actionId)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: ClientCommandPacket) {
            output.writeVarInt(value.actionId)
        }
    }
}