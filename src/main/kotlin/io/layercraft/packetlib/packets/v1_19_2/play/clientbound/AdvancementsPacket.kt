package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Update Advancements | 0x67 | play | clientbound
 *
 * @property reset reset
 * @property advancementMapping advancementMapping
 * @property identifiers identifiers
 * @property progressMapping progressMapping
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Update_Advancements">https://wiki.vg/Protocol#Update_Advancements</a>
 */

@MinecraftPacket(id = 0x67, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class AdvancementsPacket(
    val reset: Boolean,
    val advancementMapping: List<AdvancementsPacketAdvancementMapping>, // varint array
    val identifiers: List<String>, // varint array
    val progressMapping: List<AdvancementsPacketProgressMapping>, // varint array
) : ClientBoundPacket {
    companion object : PacketSerializer<AdvancementsPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): AdvancementsPacket {
            val reset = input.readBoolean()
            val advancementMapping = input.readVarIntArray { arrayInput ->
                val key = arrayInput.readString()

                return@readVarIntArray AdvancementsPacketAdvancementMapping(key)
            }
            val identifiers = input.readVarIntArray { arrayInput -> arrayInput.readString() }
            val progressMapping = input.readVarIntArray { arrayInput ->
                val key = arrayInput.readString()

                return@readVarIntArray AdvancementsPacketProgressMapping(key)
            }

            return AdvancementsPacket(reset, advancementMapping, identifiers, progressMapping)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: AdvancementsPacket) {
            output.writeBoolean(value.reset)
            output.writeVarIntArray(value.advancementMapping) { arrayValue, arrayOutput ->
                arrayOutput.writeString(arrayValue.key)
            }
            output.writeVarIntArray(value.identifiers) { arrayValue, arrayOutput -> arrayOutput.writeString(arrayValue)}
            output.writeVarIntArray(value.progressMapping) { arrayValue, arrayOutput ->
                arrayOutput.writeString(arrayValue.key)
            }
        }
    }
}

/**
 * AdvancementsPacketAdvancementMapping | advancementMapping
 *
 * @property key key
*/
data class AdvancementsPacketAdvancementMapping(
    val key: String,
)

/**
 * AdvancementsPacketProgressMapping | progressMapping
 *
 * @property key key
*/
data class AdvancementsPacketProgressMapping(
    val key: String,
)
