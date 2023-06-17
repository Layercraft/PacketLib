package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer
import java.util.UUID
/**
 * Player Info Remove | 0x35 | play | clientbound
 *
 * @param players players
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Player_Info_Remove">https://wiki.vg/Protocol#Player_Info_Remove</a>
 */

data class PlayerRemovePacket(
    val players: List<UUID>, // varint array
) : ClientBoundPacket {
    companion object : PacketSerializer<PlayerRemovePacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): PlayerRemovePacket {
            val players = input.readVarIntArray { arrayInput -> arrayInput.readUUID() }

            return PlayerRemovePacket(players)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: PlayerRemovePacket) {
            output.writeVarIntArray(value.players) { arrayValue, arrayOutput -> arrayOutput.writeUUID(arrayValue) }
        }
    }
}