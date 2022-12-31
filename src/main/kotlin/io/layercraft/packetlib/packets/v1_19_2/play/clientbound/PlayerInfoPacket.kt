package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.util.UUID
/**
 * Player Info | 0x37 | play | clientbound
 *
 * @property action action
 * @property data data
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Player_Info">https://wiki.vg/Protocol#Player_Info</a>
 */

@MinecraftPacket(id = 0x37, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class PlayerInfoPacket(
    val action: Int, // varint
    val data: List<PlayerInfoPacketData>, // varint array
) : ClientBoundPacket {
    companion object : PacketSerializer<PlayerInfoPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): PlayerInfoPacket {
            val action = input.readVarInt()
            val data = input.readVarIntArray { arrayInput -> 
                val uuid = arrayInput.readUUID()

                return@readVarIntArray PlayerInfoPacketData(uuid)
            }

            return PlayerInfoPacket(action, data)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: PlayerInfoPacket) {
            output.writeVarInt(value.action)
            output.writeVarIntArray(value.data) { arrayValue, arrayOutput -> 
                arrayOutput.writeUUID(arrayValue.uuid)
            }
        }
    }
}

/**
 * PlayerInfoPacketData | data
 *
 * @property uuid UUID
*/
data class PlayerInfoPacketData(
    val uuid: UUID,
)
