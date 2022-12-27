package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Container Property | 0x12 | play | clientbound
 *
 * @property windowId windowId
 * @property property property
 * @property value value
 * @see <a href="https://wiki.vg/Protocol#Set_Container_Property">https://wiki.vg/Protocol#Set_Container_Property</a>
 */

@MinecraftPacket(id = 0x12, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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