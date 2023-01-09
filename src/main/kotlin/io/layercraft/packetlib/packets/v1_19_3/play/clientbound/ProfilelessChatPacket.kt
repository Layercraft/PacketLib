package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Disguised Chat Message | 0x18 | play | clientbound
 *
 * @param message message
 * @param type type
 * @param name name
 * @param hasTarget target is present
 * @param target target
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17932#Disguised_Chat_Message">https://wiki.vg/Protocol#Disguised_Chat_Message</a>
 */

@MinecraftPacket(id = 0x18, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class ProfilelessChatPacket(
    val message: String,
    val type: Int, // varint
    val name: String,
    val hasTarget: Boolean,
    val target: String?,
) : ClientBoundPacket {
    companion object : PacketSerializer<ProfilelessChatPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): ProfilelessChatPacket {
            val message = input.readString()
            val type = input.readVarInt()
            val name = input.readString()
            val hasTarget = input.readBoolean()
            val target = if (hasTarget) input.readString() else null

            return ProfilelessChatPacket(message, type, name, hasTarget, target)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: ProfilelessChatPacket) {
            output.writeString(value.message)
            output.writeVarInt(value.type)
            output.writeString(value.name)
            output.writeBoolean(value.hasTarget)
            if (value.hasTarget) output.writeString(value.target!!)
        }
    }
}