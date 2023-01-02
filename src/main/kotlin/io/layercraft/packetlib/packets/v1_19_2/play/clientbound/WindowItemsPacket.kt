package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Container Content | 0x11 | play | clientbound
 *
 * @param windowId windowId
 * @param stateId stateId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Set_Container_Content">https://wiki.vg/Protocol#Set_Container_Content</a>
 */

@MinecraftPacket(id = 0x11, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class WindowItemsPacket(
    val windowId: UByte,
    val stateId: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<WindowItemsPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): WindowItemsPacket {
            val windowId = input.readUByte()
            val stateId = input.readVarInt()

            return WindowItemsPacket(windowId, stateId)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: WindowItemsPacket) {
            output.writeUByte(value.windowId)
            output.writeVarInt(value.stateId)
        }
    }
}