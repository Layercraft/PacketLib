package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Interact | 0x10 | play | serverbound
 *
 * @property target target
 * @property mouse mouse
 * @property sneaking sneaking
 * @see <a href="https://wiki.vg/Protocol#Interact">https://wiki.vg/Protocol#Interact</a>
 */

@MinecraftPacket(packetId = 0x10, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class UseEntityPacket(
    val target: Int, // varint
    val mouse: Int, // varint
    val sneaking: Boolean,
) : ServerBoundPacket {

    companion object : PacketSerializer<UseEntityPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): UseEntityPacket {
            val target = input.readVarInt()
            val mouse = input.readVarInt()
            val sneaking = input.readBoolean()

            return UseEntityPacket(target, mouse, sneaking)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: UseEntityPacket) {
            output.writeVarInt(value.target)
            output.writeVarInt(value.mouse)
            output.writeBoolean(value.sneaking)
        }
    }
}