package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Beacon Effect | 0x27 | play | serverbound
 *

 * @see <a href="https://wiki.vg/Protocol#Set_Beacon_Effect">https://wiki.vg/Protocol#Set_Beacon_Effect</a>
 */

@MinecraftPacket(id = 0x27, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
class SetBeaconEffectPacket() : ServerBoundPacket {

    companion object : PacketSerializer<SetBeaconEffectPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SetBeaconEffectPacket {
            return SetBeaconEffectPacket()
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SetBeaconEffectPacket) {
        }
    }
}