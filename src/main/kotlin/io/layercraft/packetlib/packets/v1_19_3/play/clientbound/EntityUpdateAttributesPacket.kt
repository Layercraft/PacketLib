package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.util.UUID
/**
 * Update Attributes | 0x66 | play | clientbound
 *
 * @param entityId entityId
 * @param properties list of EntityUpdateAttributesPacketProperties
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18043#Update_Attributes">https://wiki.vg/Protocol#Update_Attributes</a>
 */

@MinecraftPacket(id = 0x66, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class EntityUpdateAttributesPacket(
    val entityId: Int, // varint
    val properties: List<EntityUpdateAttributesPacketProperties>, // varint array
) : ClientBoundPacket {
    companion object : PacketSerializer<EntityUpdateAttributesPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): EntityUpdateAttributesPacket {
            val entityId = input.readVarInt()
            val properties = input.readVarIntArray { arrayInput ->
                val key = arrayInput.readString()
                val value = arrayInput.readDouble()
                val modifiers = arrayInput.readVarIntArray { arrayInput1 ->
                    val uuid = arrayInput1.readUUID()
                    val amount = arrayInput1.readDouble()
                    val operation = arrayInput1.readByte()

                    return@readVarIntArray EntityUpdateAttributesPacketModifiers(uuid, amount, operation)
                }

                return@readVarIntArray EntityUpdateAttributesPacketProperties(key, value, modifiers)
            }

            return EntityUpdateAttributesPacket(entityId, properties)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: EntityUpdateAttributesPacket) {
            output.writeVarInt(value.entityId)

            output.writeVarIntArray(value.properties) { arrayValue, arrayOutput ->
                arrayOutput.writeString(arrayValue.key)
                arrayOutput.writeDouble(arrayValue.value)

                arrayOutput.writeVarIntArray(arrayValue.modifiers) { arrayValue1, arrayOutput1 ->
                    arrayOutput1.writeUUID(arrayValue1.uuid)
                    arrayOutput1.writeDouble(arrayValue1.amount)
                    arrayOutput1.writeByte(arrayValue1.operation)
                }
            }
        }
    }
}

/**
 * EntityUpdateAttributesPacketModifiers
 *
 * @param uuid uuid
 * @param amount amount
 * @param operation operation
*/
data class EntityUpdateAttributesPacketModifiers(
    val uuid: UUID,
    val amount: Double,
    val operation: Byte,
)

/**
 * EntityUpdateAttributesPacketProperties
 *
 * @param key key
 * @param value value
 * @param modifiers list of EntityUpdateAttributesPacketModifiers
*/
data class EntityUpdateAttributesPacketProperties(
    val key: String,
    val value: Double,
    val modifiers: List<EntityUpdateAttributesPacketModifiers>, // varint array
)