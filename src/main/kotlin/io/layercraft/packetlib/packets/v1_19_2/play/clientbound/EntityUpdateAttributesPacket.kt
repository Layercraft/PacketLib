package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Update Attributes | 0x68 | play | clientbound
 *
 * @property entityId entityId
 * @property properties properties
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Update_Attributes">https://wiki.vg/Protocol#Update_Attributes</a>
 */

@MinecraftPacket(id = 0x68, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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

                return@readVarIntArray EntityUpdateAttributesPacketProperties(key, value)
            }

            return EntityUpdateAttributesPacket(entityId, properties)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: EntityUpdateAttributesPacket) {
            output.writeVarInt(value.entityId)
            output.writeVarIntArray(value.properties) { arrayValue, arrayOutput ->
                arrayOutput.writeString(arrayValue.key)
                arrayOutput.writeDouble(arrayValue.value)
            }
        }
    }
}

/**
 * EntityUpdateAttributesPacketProperties | properties
 *
 * @property key key
 * @property value value
*/
data class EntityUpdateAttributesPacketProperties(
    val key: String,
    val value: Double,
)