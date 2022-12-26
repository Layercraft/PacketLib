package io.layercraft.packetlib.packets.v1_19.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Beacon Effect | 0x26 | play | serverbound
 *

 * @see <a href="https://wiki.vg/Protocol#Set_Beacon_Effect">https://wiki.vg/Protocol#Set_Beacon_Effect</a>
 */

@MinecraftPacket(packetId = 0x26, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
class SetBeaconEffectPacket() : ServerBoundPacket {

    companion object : PacketSerializer<SetBeaconEffectPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SetBeaconEffectPacket {
            return SetBeaconEffectPacket()
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SetBeaconEffectPacket) {
        }
    }
}