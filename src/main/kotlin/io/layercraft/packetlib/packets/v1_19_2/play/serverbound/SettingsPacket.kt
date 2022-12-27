package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Client Information | 0x08 | play | serverbound
 *
 * @property locale locale
 * @property viewDistance viewDistance
 * @property chatFlags chatFlags
 * @property chatColors chatColors
 * @property skinParts skinParts
 * @property mainHand mainHand
 * @property enableTextFiltering enableTextFiltering
 * @property enableServerListing enableServerListing
 * @see <a href="https://wiki.vg/Protocol#Client_Information">https://wiki.vg/Protocol#Client_Information</a>
 */

@MinecraftPacket(id = 0x08, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class SettingsPacket(
    val locale: String,
    val viewDistance: Byte,
    val chatFlags: Int, // varint
    val chatColors: Boolean,
    val skinParts: UByte,
    val mainHand: Int, // varint
    val enableTextFiltering: Boolean,
    val enableServerListing: Boolean,
) : ServerBoundPacket {

    companion object : PacketSerializer<SettingsPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SettingsPacket {
            val locale = input.readString()
            val viewDistance = input.readByte()
            val chatFlags = input.readVarInt()
            val chatColors = input.readBoolean()
            val skinParts = input.readUByte()
            val mainHand = input.readVarInt()
            val enableTextFiltering = input.readBoolean()
            val enableServerListing = input.readBoolean()

            return SettingsPacket(locale, viewDistance, chatFlags, chatColors, skinParts, mainHand, enableTextFiltering, enableServerListing)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SettingsPacket) {
            output.writeString(value.locale)
            output.writeByte(value.viewDistance)
            output.writeVarInt(value.chatFlags)
            output.writeBoolean(value.chatColors)
            output.writeUByte(value.skinParts)
            output.writeVarInt(value.mainHand)
            output.writeBoolean(value.enableTextFiltering)
            output.writeBoolean(value.enableServerListing)
        }
    }
}