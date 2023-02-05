package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Render Distance | 0x4b | play | clientbound
 *
 * @param viewDistance viewDistance
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17964#Set_Render_Distance">https://wiki.vg/Protocol#Set_Render_Distance</a>
 */

@MinecraftPacket(id = 0x4b, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class UpdateViewDistancePacket(
    val viewDistance: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<UpdateViewDistancePacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): UpdateViewDistancePacket {
            val viewDistance = input.readVarInt()

            return UpdateViewDistancePacket(viewDistance)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: UpdateViewDistancePacket) {
            output.writeVarInt(value.viewDistance)
        }
    }
}