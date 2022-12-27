package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.Position

/**
 * Jigsaw Generate | 0x11 | play | serverbound
 *
 * @property location location
 * @property levels levels
 * @property keepJigsaws keepJigsaws
 * @see <a href="https://wiki.vg/Protocol#Jigsaw_Generate">https://wiki.vg/Protocol#Jigsaw_Generate</a>
 */

@MinecraftPacket(id = 0x11, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class GenerateStructurePacket(
    val location: Position,
    val levels: Int, // varint
    val keepJigsaws: Boolean,
) : ServerBoundPacket {

    companion object : PacketSerializer<GenerateStructurePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): GenerateStructurePacket {
            val location = input.readPosition()
            val levels = input.readVarInt()
            val keepJigsaws = input.readBoolean()

            return GenerateStructurePacket(location, levels, keepJigsaws)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: GenerateStructurePacket) {
            output.writePosition(value.location)
            output.writeVarInt(value.levels)
            output.writeBoolean(value.keepJigsaws)
        }
    }
}