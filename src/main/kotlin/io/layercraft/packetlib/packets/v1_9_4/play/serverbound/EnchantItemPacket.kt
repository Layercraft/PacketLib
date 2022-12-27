package io.layercraft.packetlib.packets.v1_9_4.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Enchant Item | 0x06 | play | serverbound
 *
 * @property windowId windowId
 * @property enchantment enchantment
 * @see <a href="https://wiki.vg/Protocol#Enchant_Item">https://wiki.vg/Protocol#Enchant_Item</a>
 */

@MinecraftPacket(id = 0x06, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class EnchantItemPacket(
    val windowId: Byte,
    val enchantment: Byte,
) : ServerBoundPacket {

    companion object : PacketSerializer<EnchantItemPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): EnchantItemPacket {
            val windowId = input.readByte()
            val enchantment = input.readByte()

            return EnchantItemPacket(windowId, enchantment)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: EnchantItemPacket) {
            output.writeByte(value.windowId)
            output.writeByte(value.enchantment)
        }
    }
}
