package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Camera | 0x49 | play | clientbound
 *
 * @property cameraId cameraId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Set_Camera">https://wiki.vg/Protocol#Set_Camera</a>
 */

@MinecraftPacket(id = 0x49, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class CameraPacket(
    val cameraId: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<CameraPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): CameraPacket {
            val cameraId = input.readVarInt()

            return CameraPacket(cameraId)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: CameraPacket) {
            output.writeVarInt(value.cameraId)
        }
    }
}