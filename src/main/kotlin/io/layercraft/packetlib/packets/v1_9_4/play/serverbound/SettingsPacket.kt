package io.layercraft.packetlib.packets.v1_9_4.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Client Settings | 0x04 | play | serverbound
 *
 * @property locale locale
 * @property viewDistance viewDistance
 * @property chatFlags chatFlags
 * @property chatColors chatColors
 * @property skinParts skinParts
 * @property mainHand mainHand
 * @see <a href="https://wiki.vg/Protocol#Client_Settings">https://wiki.vg/Protocol#Client_Settings</a>
 */

@MinecraftPacket(packetId = 0x04, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class SettingsPacket(
    val locale: String,
    val viewDistance: Byte,
    val chatFlags: Int, // varint
    val chatColors: Boolean,
    val skinParts: UByte,
    val mainHand: Int, // varint
) : ServerBoundPacket {

    companion object : PacketSerializer<SettingsPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SettingsPacket {
            val locale = input.readString()
            val viewDistance = input.readByte()
            val chatFlags = input.readVarInt()
            val chatColors = input.readBoolean()
            val skinParts = input.readUByte()
            val mainHand = input.readVarInt()

            return SettingsPacket(locale, viewDistance, chatFlags, chatColors, skinParts, mainHand)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SettingsPacket) {
            output.writeString(value.locale)
            output.writeByte(value.viewDistance)
            output.writeVarInt(value.chatFlags)
            output.writeBoolean(value.chatColors)
            output.writeUByte(value.skinParts)
            output.writeVarInt(value.mainHand)
        }
    }
}
