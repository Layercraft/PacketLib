package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Hide Message | 0x18 | play | clientbound
 *
 * @param signature signature
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Hide_Message">https://wiki.vg/Protocol#Hide_Message</a>
 */

@MinecraftPacket(id = 0x18, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class HideMessagePacket(
    val signature: List<UByte>, // varint array
) : ClientBoundPacket {
    companion object : PacketSerializer<HideMessagePacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): HideMessagePacket {
            val signature = input.readVarIntArray { arrayInput -> arrayInput.readUByte() }

            return HideMessagePacket(signature)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: HideMessagePacket) {
            output.writeVarIntArray(value.signature) { arrayValue, arrayOutput -> arrayOutput.writeUByte(arrayValue) }
        }
    }
}