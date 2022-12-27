package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Window Property | 0x15 | play | clientbound
 *
 * @property windowId windowId
 * @property property property
 * @property value value
 * @see <a href="https://wiki.vg/Protocol#Window_Property">https://wiki.vg/Protocol#Window_Property</a>
 */

@MinecraftPacket(packetId = 0x15, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class CraftProgressBarPacket(
    val windowId: UByte,
    val property: Short,
    val value: Short,
) : ClientBoundPacket {

    companion object : PacketSerializer<CraftProgressBarPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): CraftProgressBarPacket {
            val windowId = input.readUByte()
            val property = input.readShort()
            val value = input.readShort()

            return CraftProgressBarPacket(windowId, property, value)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: CraftProgressBarPacket) {
            output.writeUByte(value.windowId)
            output.writeShort(value.property)
            output.writeShort(value.value)
        }
    }
}
