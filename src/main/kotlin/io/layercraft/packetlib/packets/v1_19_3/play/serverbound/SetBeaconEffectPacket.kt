package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Beacon Effect | 0x27 | play | serverbound
 *
 * @param hasPrimaryEffect primary_effect is present
 * @param primaryEffect primary_effect
 * @param hasSecondaryEffect secondary_effect is present
 * @param secondaryEffect secondary_effect
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Beacon_Effect">https://wiki.vg/Protocol#Set_Beacon_Effect</a>
 */

data class SetBeaconEffectPacket(
    val hasPrimaryEffect: Boolean,
    val primaryEffect: Int?, // varint
    val hasSecondaryEffect: Boolean,
    val secondaryEffect: Int?, // varint
) : ServerBoundPacket {
    companion object : PacketSerializer<SetBeaconEffectPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): SetBeaconEffectPacket {
            val hasPrimaryEffect = input.readBoolean()
            val primaryEffect = if (hasPrimaryEffect) input.readVarInt() else null
            val hasSecondaryEffect = input.readBoolean()
            val secondaryEffect = if (hasSecondaryEffect) input.readVarInt() else null

            return SetBeaconEffectPacket(hasPrimaryEffect, primaryEffect, hasSecondaryEffect, secondaryEffect)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: SetBeaconEffectPacket) {
            output.writeBoolean(value.hasPrimaryEffect)
            if (value.hasPrimaryEffect) output.writeVarInt(value.primaryEffect!!)
            output.writeBoolean(value.hasSecondaryEffect)
            if (value.hasSecondaryEffect) output.writeVarInt(value.secondaryEffect!!)
        }
    }
}