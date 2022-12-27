package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x2d | play | clientbound
 *
 * @property windowId windowId
 * @property inventoryType inventoryType
 * @property windowTitle windowTitle
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x2d, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class OpenWindowPacket(
    val windowId: Int, // varint
    val inventoryType: Int, // varint
    val windowTitle: String,
) : ClientBoundPacket {

    companion object : PacketSerializer<OpenWindowPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): OpenWindowPacket {
            val windowId = input.readVarInt()
            val inventoryType = input.readVarInt()
            val windowTitle = input.readString()

            return OpenWindowPacket(windowId, inventoryType, windowTitle)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: OpenWindowPacket) {
            output.writeVarInt(value.windowId)
            output.writeVarInt(value.inventoryType)
            output.writeString(value.windowTitle)
        }
    }
}