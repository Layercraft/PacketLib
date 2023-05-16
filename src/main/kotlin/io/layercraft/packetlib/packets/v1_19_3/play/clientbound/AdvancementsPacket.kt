package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Update Advancements | 0x65 | play | clientbound
 *
 * @param reset reset
 * @param advancementMapping list of AdvancementsPacketAdvancementMapping
 * @param identifiers identifiers
 * @param progressMapping list of AdvancementsPacketProgressMapping
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18071#Update_Advancements">https://wiki.vg/Protocol#Update_Advancements</a>
 */

@MinecraftPacket(id = 0x65, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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
                val hasParentId = arrayInput.readBoolean()
                val parentId = if (hasParentId) arrayInput.readString() else null
                val hasDisplayData = arrayInput.readBoolean()
                val title = if (hasDisplayData) arrayInput.readString() else null
                val description = if (hasDisplayData) arrayInput.readString() else null
                val frameType = if (hasDisplayData) arrayInput.readVarInt() else null
                val xCord = if (hasDisplayData) arrayInput.readFloat() else null
                val yCord = if (hasDisplayData) arrayInput.readFloat() else null
                val criteria = arrayInput.readVarIntArray { arrayInput ->
                    val key = arrayInput.readString()

                    return@readVarIntArray AdvancementsPacketCriteria(key)
                }
                val requirements = arrayInput.readVarIntArray { arrayInput1 -> arrayInput1.readVarIntArray { arrayInput -> arrayInput.readString() } }

                return@readVarIntArray AdvancementsPacketAdvancementMapping(key, hasParentId, parentId, hasDisplayData, title, description, frameType, xCord, yCord, criteria, requirements)
            }
            val identifiers = input.readVarIntArray { arrayInput -> arrayInput.readString() }
            val progressMapping = input.readVarIntArray { arrayInput ->
                val key = arrayInput.readString()
                val value = arrayInput.readVarIntArray { arrayInput1 ->
                    val criterionIdentifier = arrayInput1.readString()
                    val hasCriterionProgress = arrayInput1.readBoolean()
                    val criterionProgress = if (hasCriterionProgress) arrayInput1.readLong() else null

                    return@readVarIntArray AdvancementsPacketValue(criterionIdentifier, hasCriterionProgress, criterionProgress)
                }

                return@readVarIntArray AdvancementsPacketProgressMapping(key, value)
            }

            return AdvancementsPacket(reset, advancementMapping, identifiers, progressMapping)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: AdvancementsPacket) {
            output.writeBoolean(value.reset)

            output.writeVarIntArray(value.advancementMapping) { arrayValue, arrayOutput ->
                arrayOutput.writeString(arrayValue.key)
                arrayOutput.writeBoolean(arrayValue.hasParentId)
                if (arrayValue.hasParentId) arrayOutput.writeString(arrayValue.parentId!!)
                arrayOutput.writeBoolean(arrayValue.hasDisplayData)
                if (arrayValue.hasDisplayData) arrayOutput.writeString(arrayValue.title!!)
                if (arrayValue.hasDisplayData) arrayOutput.writeString(arrayValue.description!!)
                if (arrayValue.hasDisplayData) arrayOutput.writeVarInt(arrayValue.frameType!!)
                if (arrayValue.hasDisplayData) arrayOutput.writeFloat(arrayValue.xCord!!)
                if (arrayValue.hasDisplayData) arrayOutput.writeFloat(arrayValue.yCord!!)

                arrayOutput.writeVarIntArray(arrayValue.criteria) { arrayValue, arrayOutput ->
                    arrayOutput.writeString(arrayValue.key)
                }

                arrayOutput.writeVarIntArray(arrayValue.requirements) { arrayValue1, arrayOutput1 -> arrayOutput1.writeVarIntArray(arrayValue1) { arrayValue, arrayOutput -> arrayOutput.writeString(arrayValue) } }
            }

            output.writeVarIntArray(value.identifiers) { arrayValue, arrayOutput -> arrayOutput.writeString(arrayValue) }

            output.writeVarIntArray(value.progressMapping) { arrayValue, arrayOutput ->
                arrayOutput.writeString(arrayValue.key)

                arrayOutput.writeVarIntArray(arrayValue.value) { arrayValue1, arrayOutput1 ->
                    arrayOutput1.writeString(arrayValue1.criterionIdentifier)
                    arrayOutput1.writeBoolean(arrayValue1.hasCriterionProgress)
                    if (arrayValue1.hasCriterionProgress) arrayOutput1.writeLong(arrayValue1.criterionProgress!!)
                }
            }
        }
    }
}

/**
 * AdvancementsPacketCriteria
 *
 * @param key key
*/
data class AdvancementsPacketCriteria(
    val key: String,
)

/**
 * AdvancementsPacketAdvancementMapping
 *
 * @param key key
 * @param hasParentId parentId is present
 * @param parentId parentId
 * @param hasDisplayData displayData is present
 * @param title title
 * @param description description
 * @param frameType frameType
 * @param xCord xCord
 * @param yCord yCord
 * @param criteria list of AdvancementsPacketCriteria
 * @param requirements requirements
*/
data class AdvancementsPacketAdvancementMapping(
    val key: String,
    val hasParentId: Boolean,
    val parentId: String?,
    val hasDisplayData: Boolean,
    val title: String?,
    val description: String?,
    val frameType: Int?, // varint
    val xCord: Float?,
    val yCord: Float?,
    val criteria: List<AdvancementsPacketCriteria>, // varint array
    val requirements: List<List<String>>, // varint array
)

/**
 * AdvancementsPacketValue
 *
 * @param criterionIdentifier criterionIdentifier
 * @param hasCriterionProgress criterionProgress is present
 * @param criterionProgress criterionProgress
*/
data class AdvancementsPacketValue(
    val criterionIdentifier: String,
    val hasCriterionProgress: Boolean,
    val criterionProgress: Long?,
)

/**
 * AdvancementsPacketProgressMapping
 *
 * @param key key
 * @param value list of AdvancementsPacketValue
*/
data class AdvancementsPacketProgressMapping(
    val key: String,
    val value: List<AdvancementsPacketValue>, // varint array
)