package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Camera | 0x48 | play | clientbound
 *
 * @param cameraId cameraId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Camera">https://wiki.vg/Protocol#Set_Camera</a>
 */

data class CameraPacket(
    val cameraId: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<CameraPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): CameraPacket {
            val cameraId = input.readVarInt()

            return CameraPacket(cameraId)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: CameraPacket) {
            output.writeVarInt(value.cameraId)
        }
    }
}