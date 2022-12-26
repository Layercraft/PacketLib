package io.layercraft.packetlib.packets.v1_19.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.Position

/**
 * Use Item | 0x2b | play | serverbound
 *
 * @property location location
 * @property name name
 * @property target target
 * @property pool pool
 * @property finalState finalState
 * @property jointType jointType
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(packetId = 0x2b, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class UpdateJigsawBlockPacket(
    val location: Position,
    val name: String,
    val target: String,
    val pool: String,
    val finalState: String,
    val jointType: String,
) : ServerBoundPacket {

    companion object : PacketSerializer<UpdateJigsawBlockPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): UpdateJigsawBlockPacket {
            val location = input.readPosition()
            val name = input.readString()
            val target = input.readString()
            val pool = input.readString()
            val finalState = input.readString()
            val jointType = input.readString()

            return UpdateJigsawBlockPacket(location, name, target, pool, finalState, jointType)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: UpdateJigsawBlockPacket) {
            output.writePosition(value.location)
            output.writeString(value.name)
            output.writeString(value.target)
            output.writeString(value.pool)
            output.writeString(value.finalState)
            output.writeString(value.jointType)
        }
    }
}