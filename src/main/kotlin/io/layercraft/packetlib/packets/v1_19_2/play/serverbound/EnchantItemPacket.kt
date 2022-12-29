package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x0a | play | serverbound
 *
 * @property windowId windowId
 * @property enchantment enchantment
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x0a, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class EnchantItemPacket(
    val windowId: Byte,
    val enchantment: Byte,
) : ServerBoundPacket {
    companion object : PacketSerializer<EnchantItemPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): EnchantItemPacket {
            val windowId = input.readByte()
            val enchantment = input.readByte()

            return EnchantItemPacket(windowId, enchantment)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: EnchantItemPacket) {
            output.writeByte(value.windowId)
            output.writeByte(value.enchantment)
        }
    }
}