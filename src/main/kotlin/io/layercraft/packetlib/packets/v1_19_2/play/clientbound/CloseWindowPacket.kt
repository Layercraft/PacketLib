package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Close Container (clientbound) | 0x10 | play | clientbound
 *
 * @property windowId windowId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Close_Container_.28clientbound.29">https://wiki.vg/Protocol#Close_Container_.28clientbound.29</a>
 */

@MinecraftPacket(id = 0x10, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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