package io.layercraft.packetlib.packets.v1_9_4.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.Position

/**
 * Update Sign | 0x19 | play | serverbound
 *
 * @property location location
 * @property text1 text1
 * @property text2 text2
 * @property text3 text3
 * @property text4 text4
 * @see <a href="https://wiki.vg/Protocol#Update_Sign">https://wiki.vg/Protocol#Update_Sign</a>
 */

@MinecraftPacket(id = 0x19, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class UpdateSignPacket(
    val location: Position,
    val text1: String,
    val text2: String,
    val text3: String,
    val text4: String,
) : ServerBoundPacket {

    companion object : PacketSerializer<UpdateSignPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): UpdateSignPacket {
            val location = input.readPosition()
            val text1 = input.readString()
            val text2 = input.readString()
            val text3 = input.readString()
            val text4 = input.readString()

            return UpdateSignPacket(location, text1, text2, text3, text4)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: UpdateSignPacket) {
            output.writePosition(value.location)
            output.writeString(value.text1)
            output.writeString(value.text2)
            output.writeString(value.text3)
            output.writeString(value.text4)
        }
    }
}
