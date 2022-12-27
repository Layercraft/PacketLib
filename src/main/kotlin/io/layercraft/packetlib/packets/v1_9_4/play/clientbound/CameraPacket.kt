package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Camera | 0x36 | play | clientbound
 *
 * @property cameraId cameraId
 * @see <a href="https://wiki.vg/Protocol#Camera">https://wiki.vg/Protocol#Camera</a>
 */

@MinecraftPacket(packetId = 0x36, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class CameraPacket(
    val cameraId: Int, // varint
) : ClientBoundPacket {

    companion object : PacketSerializer<CameraPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): CameraPacket {
            val cameraId = input.readVarInt()

            return CameraPacket(cameraId)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: CameraPacket) {
            output.writeVarInt(value.cameraId)
        }
    }
}
