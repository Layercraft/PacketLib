package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x1c | play | clientbound
 *
 * @property windowId windowId
 * @property nbSlots nbSlots
 * @property entityId entityId
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x1c, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class OpenHorseWindowPacket(
    val windowId: UByte,
    val nbSlots: Int, // varint
    val entityId: Int,
) : ClientBoundPacket {

    companion object : PacketSerializer<OpenHorseWindowPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): OpenHorseWindowPacket {
            val windowId = input.readUByte()
            val nbSlots = input.readVarInt()
            val entityId = input.readInt()

            return OpenHorseWindowPacket(windowId, nbSlots, entityId)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: OpenHorseWindowPacket) {
            output.writeUByte(value.windowId)
            output.writeVarInt(value.nbSlots)
            output.writeInt(value.entityId)
        }
    }
}