package io.layercraft.packetlib.packets.v1_9_4.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Encryption Response | 0x0a | play | serverbound
 *
 * @property target target
 * @property mouse mouse
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(id = 0x0a, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class UseEntityPacket(
    val target: Int, // varint
    val mouse: Int, // varint
) : ServerBoundPacket {

    companion object : PacketSerializer<UseEntityPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): UseEntityPacket {
            val target = input.readVarInt()
            val mouse = input.readVarInt()

            return UseEntityPacket(target, mouse)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: UseEntityPacket) {
            output.writeVarInt(value.target)
            output.writeVarInt(value.mouse)
        }
    }
}
