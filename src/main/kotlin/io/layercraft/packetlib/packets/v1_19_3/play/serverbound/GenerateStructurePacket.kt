package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.Position
/**
 * Jigsaw Generate | 0x10 | play | serverbound
 *
 * @param location location
 * @param levels levels
 * @param keepJigsaws keepJigsaws
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Jigsaw_Generate">https://wiki.vg/Protocol#Jigsaw_Generate</a>
 */

@MinecraftPacket(id = 0x10, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class GenerateStructurePacket(
    val location: Position,
    val levels: Int, // varint
    val keepJigsaws: Boolean,
) : ServerBoundPacket {
    companion object : PacketSerializer<GenerateStructurePacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): GenerateStructurePacket {
            val location = input.readPosition()
            val levels = input.readVarInt()
            val keepJigsaws = input.readBoolean()

            return GenerateStructurePacket(location, levels, keepJigsaws)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: GenerateStructurePacket) {
            output.writePosition(value.location)
            output.writeVarInt(value.levels)
            output.writeBoolean(value.keepJigsaws)
        }
    }
}