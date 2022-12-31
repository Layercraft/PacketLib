package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.Position
/**
 * Open Sign Editor | 0x2e | play | clientbound
 *
 * @property location location
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Open_Sign_Editor">https://wiki.vg/Protocol#Open_Sign_Editor</a>
 */

@MinecraftPacket(id = 0x2e, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class OpenSignEntityPacket(
    val location: Position,
) : ClientBoundPacket {
    companion object : PacketSerializer<OpenSignEntityPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): OpenSignEntityPacket {
            val location = input.readPosition()

            return OpenSignEntityPacket(location)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: OpenSignEntityPacket) {
            output.writePosition(value.location)
        }
    }
}
