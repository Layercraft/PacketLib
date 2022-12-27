package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x4c | play | clientbound
 *
 * @property viewDistance viewDistance
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x4c, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class UpdateViewDistancePacket(
    val viewDistance: Int, // varint
) : ClientBoundPacket {

    companion object : PacketSerializer<UpdateViewDistancePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): UpdateViewDistancePacket {
            val viewDistance = input.readVarInt()

            return UpdateViewDistancePacket(viewDistance)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: UpdateViewDistancePacket) {
            output.writeVarInt(value.viewDistance)
        }
    }
}