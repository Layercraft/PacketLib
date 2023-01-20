package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Open Screen | 0x2c | play | clientbound
 *
 * @param windowId windowId
 * @param inventoryType inventoryType
 * @param windowTitle windowTitle
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17935#Open_Screen">https://wiki.vg/Protocol#Open_Screen</a>
 */

@MinecraftPacket(id = 0x2c, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class OpenWindowPacket(
    val windowId: Int, // varint
    val inventoryType: Int, // varint
    val windowTitle: String,
) : ClientBoundPacket {
    companion object : PacketSerializer<OpenWindowPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): OpenWindowPacket {
            val windowId = input.readVarInt()
            val inventoryType = input.readVarInt()
            val windowTitle = input.readString()

            return OpenWindowPacket(windowId, inventoryType, windowTitle)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: OpenWindowPacket) {
            output.writeVarInt(value.windowId)
            output.writeVarInt(value.inventoryType)
            output.writeString(value.windowTitle)
        }
    }
}