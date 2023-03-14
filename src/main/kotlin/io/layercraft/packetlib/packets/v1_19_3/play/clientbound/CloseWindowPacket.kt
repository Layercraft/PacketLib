package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Close Container | 0x0f | play | clientbound
 *
 * @param windowId windowId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18043#Close_Container">https://wiki.vg/Protocol#Close_Container</a>
 */

@MinecraftPacket(id = 0x0f, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class CloseWindowPacket(
    val windowId: UByte,
) : ClientBoundPacket {
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