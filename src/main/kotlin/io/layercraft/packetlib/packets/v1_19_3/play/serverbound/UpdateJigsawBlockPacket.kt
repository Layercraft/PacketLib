package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer
import io.layercraft.packetlib.types.Position
/**
 * Program Jigsaw Block | 0x2c | play | serverbound
 *
 * @param location location
 * @param name name
 * @param target target
 * @param pool pool
 * @param finalState finalState
 * @param jointType jointType
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Program_Jigsaw_Block">https://wiki.vg/Protocol#Program_Jigsaw_Block</a>
 */

data class UpdateJigsawBlockPacket(
    val location: Position,
    val name: String,
    val target: String,
    val pool: String,
    val finalState: String,
    val jointType: String,
) : ServerBoundPacket {
    companion object : PacketSerializer<UpdateJigsawBlockPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): UpdateJigsawBlockPacket {
            val location = input.readPosition()
            val name = input.readString()
            val target = input.readString()
            val pool = input.readString()
            val finalState = input.readString()
            val jointType = input.readString()

            return UpdateJigsawBlockPacket(location, name, target, pool, finalState, jointType)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: UpdateJigsawBlockPacket) {
            output.writePosition(value.location)
            output.writeString(value.name)
            output.writeString(value.target)
            output.writeString(value.pool)
            output.writeString(value.finalState)
            output.writeString(value.jointType)
        }
    }
}