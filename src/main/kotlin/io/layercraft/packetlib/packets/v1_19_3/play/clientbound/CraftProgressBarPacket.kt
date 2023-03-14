package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Container Property | 0x11 | play | clientbound
 *
 * @param windowId windowId
 * @param property property
 * @param value value
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18043#Set_Container_Property">https://wiki.vg/Protocol#Set_Container_Property</a>
 */

@MinecraftPacket(id = 0x11, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class CraftProgressBarPacket(
    val windowId: UByte,
    val property: Short,
    val value: Short,
) : ClientBoundPacket {
    companion object : PacketSerializer<CraftProgressBarPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): CraftProgressBarPacket {
            val windowId = input.readUByte()
            val property = input.readShort()
            val value = input.readShort()

            return CraftProgressBarPacket(windowId, property, value)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: CraftProgressBarPacket) {
            output.writeUByte(value.windowId)
            output.writeShort(value.property)
            output.writeShort(value.value)
        }
    }
}