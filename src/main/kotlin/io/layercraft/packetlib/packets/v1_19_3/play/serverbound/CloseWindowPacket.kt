package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Close Container | 0x0b | play | serverbound
 *
 * @param windowId windowId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17932#Close_Container_2">https://wiki.vg/Protocol#Close_Container_2</a>
 */

@MinecraftPacket(id = 0x0b, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class CloseWindowPacket(
    val windowId: UByte,
) : ServerBoundPacket {
    companion object : PacketSerializer<CloseWindowPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): CloseWindowPacket {
            val windowId = input.readUByte()

            return CloseWindowPacket(windowId)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: CloseWindowPacket) {
            output.writeUByte(value.windowId)
        }
    }
}