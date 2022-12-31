package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Update Tags | 0x6b | play | clientbound
 *
 * @property tags tags
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Update_Tags">https://wiki.vg/Protocol#Update_Tags</a>
 */

@MinecraftPacket(id = 0x6b, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class TagsPacket(
    val tags: List<TagsPacketTags>, // varint array
) : ClientBoundPacket {
    companion object : PacketSerializer<TagsPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): TagsPacket {
            val tags = input.readVarIntArray { arrayInput -> 
                val tagType = arrayInput.readString()

                return@readVarIntArray TagsPacketTags(tagType)
            }

            return TagsPacket(tags)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: TagsPacket) {
            output.writeVarIntArray(value.tags) { arrayValue, arrayOutput -> 
                arrayOutput.writeString(arrayValue.tagType)
            }
        }
    }
}

/**
 * TagsPacketTags | tags
 *
 * @property tagType tagType
*/
data class TagsPacketTags(
    val tagType: String,
)
