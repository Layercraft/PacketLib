package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Update Tags | 0x6a | play | clientbound
 *
 * @param tags list of TagsPacketTags
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Update_Tags">https://wiki.vg/Protocol#Update_Tags</a>
 */

data class TagsPacket(
    val tags: List<TagsPacketTags>, // varint array
) : ClientBoundPacket {
    companion object : PacketSerializer<TagsPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): TagsPacket {
            val tags = input.readVarIntArray { arrayInput ->
                val tagType = arrayInput.readString()

                TagsPacketTags(tagType)
            }

            return TagsPacket(tags)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: TagsPacket) {
            output.writeVarIntArray(value.tags) { arrayValue, arrayOutput ->
                arrayOutput.writeString(arrayValue.tagType)
            }
        }
    }
}

/**
 * TagsPacketTags
 *
 * @param tagType tagType
*/
data class TagsPacketTags(
    val tagType: String,
)