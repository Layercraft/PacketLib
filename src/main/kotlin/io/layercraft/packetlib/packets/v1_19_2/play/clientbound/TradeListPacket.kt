package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Merchant Offers | 0x27 | play | clientbound
 *
 * @property windowId windowId
 * @property villagerLevel villagerLevel
 * @property experience experience
 * @property isRegularVillager isRegularVillager
 * @property canRestock canRestock
 * @see <a href="https://wiki.vg/Protocol#Merchant_Offers">https://wiki.vg/Protocol#Merchant_Offers</a>
 */

@MinecraftPacket(id = 0x27, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class TradeListPacket(
    val windowId: Int, // varint
    val villagerLevel: Int, // varint
    val experience: Int, // varint
    val isRegularVillager: Boolean,
    val canRestock: Boolean,
) : ClientBoundPacket {

    companion object : PacketSerializer<TradeListPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): TradeListPacket {
            val windowId = input.readVarInt()
            val villagerLevel = input.readVarInt()
            val experience = input.readVarInt()
            val isRegularVillager = input.readBoolean()
            val canRestock = input.readBoolean()

            return TradeListPacket(windowId, villagerLevel, experience, isRegularVillager, canRestock)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: TradeListPacket) {
            output.writeVarInt(value.windowId)
            output.writeVarInt(value.villagerLevel)
            output.writeVarInt(value.experience)
            output.writeBoolean(value.isRegularVillager)
            output.writeBoolean(value.canRestock)
        }
    }
}