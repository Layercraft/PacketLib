package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Passengers | 0x54 | play | clientbound
 *
 * @property entityId entityId
 * @see <a href="https://wiki.vg/Protocol#Set_Passengers">https://wiki.vg/Protocol#Set_Passengers</a>
 */

@MinecraftPacket(packetId = 0x54, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class SetPassengersPacket(
    val entityId: Int, // varint
) : ClientBoundPacket {

    companion object : PacketSerializer<SetPassengersPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SetPassengersPacket {
            val entityId = input.readVarInt()

            return SetPassengersPacket(entityId)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SetPassengersPacket) {
            output.writeVarInt(value.entityId)
        }
    }
}