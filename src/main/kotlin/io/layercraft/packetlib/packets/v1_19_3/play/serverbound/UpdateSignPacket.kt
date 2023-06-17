package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer
import io.layercraft.packetlib.types.Position
/**
 * Update Sign | 0x2e | play | serverbound
 *
 * @param location location
 * @param text1 text1
 * @param text2 text2
 * @param text3 text3
 * @param text4 text4
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Update_Sign">https://wiki.vg/Protocol#Update_Sign</a>
 */

data class UpdateSignPacket(
    val location: Position,
    val text1: String,
    val text2: String,
    val text3: String,
    val text4: String,
) : ServerBoundPacket {
    companion object : PacketSerializer<UpdateSignPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): UpdateSignPacket {
            val location = input.readPosition()
            val text1 = input.readString()
            val text2 = input.readString()
            val text3 = input.readString()
            val text4 = input.readString()

            return UpdateSignPacket(location, text1, text2, text3, text4)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: UpdateSignPacket) {
            output.writePosition(value.location)
            output.writeString(value.text1)
            output.writeString(value.text2)
            output.writeString(value.text3)
            output.writeString(value.text4)
        }
    }
}