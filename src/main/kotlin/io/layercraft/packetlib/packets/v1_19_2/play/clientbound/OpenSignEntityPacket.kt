package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.Position

/**
 * Use Item | 0x2e | play | clientbound
 *
 * @property location location
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x2e, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class OpenSignEntityPacket(
    val location: Position,
) : ClientBoundPacket {

    companion object : PacketSerializer<OpenSignEntityPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): OpenSignEntityPacket {
            val location = input.readPosition()

            return OpenSignEntityPacket(location)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: OpenSignEntityPacket) {
            output.writePosition(value.location)
        }
    }
}